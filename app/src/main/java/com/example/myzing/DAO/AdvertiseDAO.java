package com.example.myzing.DAO;

import com.example.myzing.Model.Advertise;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvertiseDAO {
    private DataService dataService;
    private ArrayList<Advertise> arrayListAdvertise;

    public AdvertiseDAO() {
        dataService = APIService.getDataService();
        arrayListAdvertise = new ArrayList<>();
    }

    public void getAdvertise(final IAdvertiseDAO callback){
        dataService = APIService.getDataService();
        Call<List<Advertise>> listCall = dataService.GetDataAdvertise();
        listCall.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                if(response.isSuccessful()){
                    arrayListAdvertise = (ArrayList<Advertise>) response.body();
                    callback.returnListAdvertise(arrayListAdvertise);
                }
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {

            }
        });
    }
}
