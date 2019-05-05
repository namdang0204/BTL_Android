package com.example.myzing.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.DanhSachGoiYActivity;
import com.example.myzing.Adapter.GoiYAdapter;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_GoiY extends Fragment {
    View view;
    ArrayList<Song> arraySong;
    GoiYAdapter goiYAdapter;
    TextView tv_Title_GoiY, tv_ViewMore_GoiY;
    RecyclerView recyclerView_GoiY;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_goi_y,container,false);

        tv_Title_GoiY= view.findViewById(R.id.textview_goi_y);
        tv_ViewMore_GoiY= view.findViewById(R.id.textview_viewmore_goiy);
        recyclerView_GoiY= view.findViewById(R.id.recycleview_goiy);

        getData();

        tv_ViewMore_GoiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhSachGoiYActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService= APIService.getDataService();
        Call<List<Song>> callBack= dataService.GetListSongGoiY("ThaoXinhGai");
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong= (ArrayList<Song>) response.body();
                goiYAdapter= new GoiYAdapter(getActivity(), arraySong);
                recyclerView_GoiY.setLayoutManager(new LinearLayoutManager(getActivity()));
//                Toast.makeText(getContext(),arraySong.get(0).getNameSong()+"", Toast.LENGTH_SHORT).show();
                recyclerView_GoiY.setAdapter(goiYAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
