package com.example.myzing.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myzing.Adapter.AdvertiseAdapter;
import com.example.myzing.Model.Advertise;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Advertise extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    AdvertiseAdapter advertiseAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advertise, container, false);
        GetData();
        anhxa();
        return view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicatorDefault);

    }

    private void GetData() {
        DataService dataService = APIService.getDataService();
        Call<List<Advertise>> listCall = dataService.GetDataAdvertise();
        listCall.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                ArrayList<Advertise> arrayListAdvertise = (ArrayList<Advertise>) response.body();
                advertiseAdapter = new AdvertiseAdapter(getActivity(), arrayListAdvertise);
                viewPager.setAdapter(advertiseAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(viewPager.getAdapter() != null){
                            if(currentItem >= viewPager.getAdapter().getCount()){
                                currentItem = 0;
                            }
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 5000);
                    }
                };
                handler.postDelayed(runnable, 5000);
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {
            }
        });

    }
}
