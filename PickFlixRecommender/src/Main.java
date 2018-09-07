package com.recombee.api_client.examples;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.*;
import com.recombee.api_client.bindings.RecommendationResponse;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.exceptions.ApiException;
import data.Discover;
import data.Result;
import data.SavedMovies;
import network.APIService;
import network.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    private static RecombeeClient client;
    private static APIService mAPIService;
    private static String API_KEY = "7cc441d4c80dc500e03786e94fd81402";

    private static SavedMovies savedMovies;
    public static void main(String[] args) {

        RecombeeClient client = new RecombeeClient("pickflix", "losQQYGzD4BXJbfNkxRFlciOACOKzGMKsSMoq2oW9gFjscXSoJWSmeWoKDaULFpu");
        mAPIService = ApiUtils.getAPIService();
        savedMovies = SavedMovies.getInstance();


        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        for (int i=0; i < 2; i++) {

        }

        String input2 = sc.nextLine();
        savedMovies.printAllTitles();

    }



    private static void getMoviePage(int pageNum) {
        mAPIService.getDiscover(API_KEY, "english", "popularity.desc", false, false, pageNum).enqueue(new Callback<Discover>() {
            List<Result> results;

            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                results = response.body().getResults();
                savedMovies.addAll(results);
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
                System.err.println("Did not find movie page");
            }

        });
    }

    private static void setupRecombeeDatabase() {
        try {
            client.send(new AddItemProperty("Title", "string"));
            client.send(new AddItemProperty("Genre", "string"));
            client.send(new AddItemProperty("Popularity", "double"));
            client.send(new AddItemProperty("Vote_Count", "integer"));
            client.send(new AddItemProperty("Vote_Average", "double"));
            client.send(new AddItemProperty("Release_Date", "string"));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private static void addMoviesToRecombee() {
        try {
            List<Request> addRequests = new ArrayList<Request>();
            List<Result> movieList = savedMovies.getList();
            for (int i = 0; i < movieList.size(); i++) {
                AddItem addRequest = new AddItem(movieList.get(i).getTitle());
                addRequests.add(addRequest);
            }
            client.send(new Batch(addRequests));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }


    private static void clearDatabase() {
        try {
            client.send(new ResetDatabase());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}