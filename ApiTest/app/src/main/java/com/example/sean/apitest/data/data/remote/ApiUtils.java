package com.example.sean.apitest.data.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://noodlio-abracadabra-recommender-systems-v1.p.mashape.com";
    //public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
