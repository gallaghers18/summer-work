package com.example.sean.apitest.data.data.remote;

import com.example.sean.apitest.data.data.model.AddCall;
import com.example.sean.apitest.data.data.model.Post;
import com.example.sean.apitest.data.data.model.Recommendations;
import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<Post> savePost(@Field("title") String title,
                        @Field("body") String body,
                        @Field("userId") long userId);

    @PUT("/posts/{id}")
    @FormUrlEncoded
    Call<Post> updatePost(@Path("id") long id,
                          @Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);

    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") long id);

    @Headers({
            "X-Mashape-Key: faRTATIlRRmshj0Se1v7DOmQjxHwp1B5SLsjsnzfpYLdpEKREj",
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/add/subjects")
    @FormUrlEncoded
    Call<AddCall> addSubjects(@Field("body")JsonObject stuff);
//    Call<Media> addSubjects(@Field("title") String title,
//                            @Field("genre") String genre,
//                            @Field("runtime") int runtime,
//                            @Field("rating") double rating);

    @POST("/rate/subject")
    @FormUrlEncoded
    Call<JsonObject> rateSubject(@Field("body")JsonObject stuff);

    @POST("/recommend")
    @FormUrlEncoded
    Call<Recommendations> recommend(@Field("body")JsonObject stuff);



}