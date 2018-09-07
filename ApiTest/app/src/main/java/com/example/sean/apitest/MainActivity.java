package com.example.sean.apitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sean.apitest.data.data.model.AddCall;
import com.example.sean.apitest.data.data.model.Media;
import com.example.sean.apitest.data.data.model.Post;
import com.example.sean.apitest.data.data.remote.APIService;
import com.example.sean.apitest.data.data.remote.ApiUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddItem;
import com.recombee.api_client.api_requests.AddItemProperty;
import com.recombee.api_client.api_requests.AddPurchase;
import com.recombee.api_client.api_requests.AddUser;
import com.recombee.api_client.api_requests.Batch;
import com.recombee.api_client.api_requests.RecommendItemsToUser;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.api_requests.ResetDatabase;
import com.recombee.api_client.api_requests.SetItemValues;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.exceptions.ApiException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private TextView mResponseTv;
    private APIService mAPIService;
    private List<Media> mediaListTrain;
    private List<Media> mediaListTest;
    private JsonObject obj;
    private RecombeeClientLocal client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        client = new RecombeeClientLocal("pickflix", "losQQYGzD4BXJbfNkxRFlciOACOKzGMKsSMoq2oW9gFjscXSoJWSmeWoKDaULFpu");


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
//                            clearDatabase();
//                            setUpMovieDatabase();
//                            addItem("item-1","Harry Potter 1", "Fantasy", 120, 8.2);
//                            addItem("item-2","Harry Potter 2", "Fantasy", 135, 8.5);
//                            addItem("item-3","Harry Potter 3", "Fantasy", 112, 7.9);
//                            addItem("item-4", "Die Hard", "Action", 96, 10.0);
//                            addItem("item-5", "The Hangover", "Comedy", 85, 7.5);
//                            addItem("item-6", "It", "Horror", 115, 8.0);
//                            addItem("item-7", "Love", "Romance", 75, 6.5);
//                            addItem("item-8", "Deadpool", "Comedy", 120, 9.7);
//                            addItem("item-9", "Fantasy Movie", "Fantasy", 120, 9.0);
//                            addItem("item-10", "Comedy Movie", "Comedy", 105, 8.1);
//                            addItem("item-11", "Action Movie", "Action", 111, 8.6);
//                            addItem("item-12", "Horror Movie", "Horror", 130, 7.7);
//                            addItem("item-13", "Romance Movie", "Romance", 84, 6.6);

//                            client.send(new AddUser("Sean"));
//                            client.send(new AddPurchase("Sean", "item-1"));
//                            client.send(new AddPurchase("Sean", "item-2"));
//                            client.send(new AddPurchase("Sean", "item-3"));
                            RecommendationResponse recommendationResponse = client.send(new RecommendItemsToUser("Sean", 2).setReturnProperties(true));
                            for(Recommendation rec: recommendationResponse) System.out.println(rec.getValues().get("Title"));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });




    }

    public void clearDatabase() {
        try {
            client.send(new ResetDatabase());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String userID) {
        try {
            client.send(new AddUser(userID));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String itemId, final String TITLE, final String GENRE, final int RUNTIME, final double RATING) {
        try {
            HashMap values = new HashMap<String, Object>() {{
                put("Title", TITLE);
                put("Genre", GENRE);
                put("Runtime", RUNTIME);
                put("Rating", RATING);
            }};
            final SetItemValues request = new SetItemValues(itemId, values).setCascadeCreate(true);
            String output = client.send(request);
            System.out.println(output);

        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void setUpMovieDatabase() {
        try {
            client.send(new AddItemProperty("Title", "string"));
            client.send(new AddItemProperty("Genre", "string"));
            client.send(new AddItemProperty("Runtime", "int"));
            client.send(new AddItemProperty("Rating", "double"));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public void printRecommendations() {

    }

    public void testDatabase() {
        try {
            client.send(new ResetDatabase());
            final int NUM = 100;
            // Generate some random purchases of items by users
            final double PROBABILITY_PURCHASED = 0.1;
            Random r = new Random();
            ArrayList<Request> addPurchaseRequests = new ArrayList<Request>();
            for (int i = 0; i < NUM; i++)
                for (int j = 0; j < NUM; j++)
                    if (r.nextDouble() < PROBABILITY_PURCHASED) {

                        AddPurchase request = new AddPurchase(String.format("user-%s", i),String.format("item-%s", j))
                                .setCascadeCreate(true); // Use cascadeCreate parameter to create
                        // the yet non-existing users and items
                        addPurchaseRequests.add(request);
                    }

            System.out.println("Send purchases");
            client.send(new Batch(addPurchaseRequests)); //Use Batch for faster processing of larger data

            // Get 5 recommendations for user 'user-25'
            RecommendationResponse recommendationResponse = client.send(new RecommendItemsToUser("user-25", 5));
            System.out.println("Recommended items:");
            for(Recommendation rec: recommendationResponse) System.out.println(rec.getId());

        } catch (ApiException e) {
            e.printStackTrace();
            //use fallback
        }
    }

    public void sendPost(String title, String body) {
        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("main", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("main", "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
