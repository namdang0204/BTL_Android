package com.example.myzing.Service;

public class APIService {
    private static String base_url="";
    public static  DataService geDataService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
