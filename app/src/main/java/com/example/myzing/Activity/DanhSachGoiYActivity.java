package com.example.myzing.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.myzing.Adapter.DanhSachGoiYAdapter;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachGoiYActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_List_GoiY;
    DanhSachGoiYAdapter danhSachGoiYAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_goi_y);
        getData();
        anhxa();
        init();

    }





    private void getData() {
        DataService dataService= APIService.getDataService();
        Call<List<Song>> listCall= dataService.GetAllListSongGoiY("AllSong");
        listCall.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> listSong= (ArrayList<Song>) response.body();
                danhSachGoiYAdapter= new DanhSachGoiYAdapter(getApplication(),listSong);
                recyclerView_List_GoiY.setLayoutManager(new LinearLayoutManager(getApplication()));
                recyclerView_List_GoiY.setAdapter(danhSachGoiYAdapter);

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
    private void init() {
//        String title = "";
//        Intent intent = getIntent();
//        if(intent.hasExtra("title")){
//            title = intent.getStringExtra("title");
//        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GỢI Ý");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhxa() {
        toolbar= findViewById(R.id.toolbar_list_goi_y);
        recyclerView_List_GoiY= findViewById(R.id.recycleview_list_goiy);

    }
}
