package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myzing.Adapter.BannerAdapter;
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

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        GetData();
        anhxa();
        return view;
    }

    private void anhxa() {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicatorDefault);

    }

    private void GetData() {
        DataService dataService = APIService.geDataService();
        Call<List<Advertise>> listCall = dataService.GetDataService();
        listCall.enqueue(new Callback<List<Advertise>>() {
            @Override
            public void onResponse(Call<List<Advertise>> call, Response<List<Advertise>> response) {
                ArrayList<Advertise> arrayListBanner = (ArrayList<Advertise>) response.body();


                bannerAdapter = new BannerAdapter(getActivity(), arrayListBanner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<List<Advertise>> call, Throwable t) {

            }
        });

    }
}
