package com.example.myzing.Service;

import com.example.myzing.Model.Advertise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    Call<List<Advertise>> GetDataService();
}
