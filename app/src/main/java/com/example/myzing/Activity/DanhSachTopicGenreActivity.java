package com.example.myzing.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.myzing.Adapter.ListGenreAdapter;
import com.example.myzing.Adapter.ListTopicAdapter;
import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;
import com.example.myzing.Model.TopicGenre;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTopicGenreActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewTopic, recyclerViewGenre;
    private ListTopicAdapter listTopicAdapter;
    private ListGenreAdapter listGenreAdapter;

    private TopicGenre topicGenre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_topic_genre);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        anhxa();
        init();
        getData();
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_list_topic_genre);
        recyclerViewGenre = (RecyclerView) findViewById(R.id.recyclerview_genre);
        recyclerViewTopic = (RecyclerView) findViewById(R.id.recyclerview_topic);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CHỦ ĐỀ VÀ THỂ LOẠI");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData(){
        DataService dataService = APIService.getDataService();
        Call<TopicGenre> call = dataService.GetAllTopicGenre();
        call.enqueue(new Callback<TopicGenre>() {
            @Override
            public void onResponse(Call<TopicGenre> call, Response<TopicGenre> response) {
                topicGenre = response.body();
                ArrayList<Topic> listTopic = (ArrayList<Topic>) topicGenre.getTopic();
                listTopicAdapter = new ListTopicAdapter(DanhSachTopicGenreActivity.this, listTopic);
                recyclerViewTopic.setLayoutManager(new LinearLayoutManager(DanhSachTopicGenreActivity.this));
                recyclerViewTopic.setAdapter(listTopicAdapter);

                ArrayList<Genre> listGenre = (ArrayList<Genre>) topicGenre.getGenre();
                listGenreAdapter = new ListGenreAdapter(DanhSachTopicGenreActivity.this, listGenre);
                recyclerViewGenre.setLayoutManager(new LinearLayoutManager(DanhSachTopicGenreActivity.this));
                recyclerViewGenre.setAdapter(listGenreAdapter);

            }

            @Override
            public void onFailure(Call<TopicGenre> call, Throwable t) {

            }
        });
    }
}
