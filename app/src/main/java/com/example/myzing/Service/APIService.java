package com.example.myzing.Service;

public class APIService {
    private static String base_url="https://zingmp3tomhap.000webhostapp.com/Server/";

    public static  DataService getDataService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
