package com.example.myzing.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myzing.Adapter.DanhSachPlaylistAdapter;
import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_Danhsach_Playlist;
    DanhSachPlaylistAdapter danhSachPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_playlist);
        anhxa();
        GetData();
        init();
    }

    private void GetData() {
        DataService dataService= APIService.getDataService();
        Call<List<Playlist>> listCall= dataService.GetDanhSachPlaylist();
        listCall.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();

                danhSachPlaylistAdapter = new DanhSachPlaylistAdapter(DanhSachPlaylistActivity.this,arrayPlaylist);
                recyclerView_Danhsach_Playlist.setLayoutManager(new GridLayoutManager(DanhSachPlaylistActivity.this,2));
                recyclerView_Danhsach_Playlist.setAdapter(danhSachPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }


    private void init() {
        String title = "";
        Intent intent = getIntent();
        if(intent.hasExtra("title")){
            title = intent.getStringExtra("title");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.tool_bar_danh_sach_playlist);
        recyclerView_Danhsach_Playlist = findViewById(R.id.recycleview_danhsach_playlist);
    }
}
