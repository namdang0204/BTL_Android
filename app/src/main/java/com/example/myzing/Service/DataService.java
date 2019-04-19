package com.example.myzing.Service;

import com.example.myzing.Model.Advertise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET ("getDataAdvertise.php")
    Call<List<Advertise>> GetDataAdvertise();
}
