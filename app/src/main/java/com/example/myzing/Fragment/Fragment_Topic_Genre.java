package com.example.myzing.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myzing.Activity.DanhSachTopicGenreActivity;
import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;
import com.example.myzing.Model.TopicGenre;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Topic_Genre extends Fragment {
    View view;
    TextView tvtitle_topic_genre, tv_viewmore_topic_genre;
    RecyclerView recyclerView_Topic_Genre;
    TopicGenre topicGenre;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_topic_genre,container,false);

        tvtitle_topic_genre= view.findViewById(R.id.txtview_title_topic_genre);
        tv_viewmore_topic_genre= view.findViewById(R.id.txtview_viewmore_topic_genre);
        recyclerView_Topic_Genre= view.findViewById(R.id.recycleview_topic_genre);

        getData();

        tv_viewmore_topic_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DanhSachTopicGenreActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService= APIService.getDataService();
        Call<List<TopicGenre>> listCall = dataService.GetTopicGenre();
        listCall.enqueue(new Callback<List<TopicGenre>>() {
            @Override
            public void onResponse(Call<List<TopicGenre>> call, Response<List<TopicGenre>> response) {
                topicGenre = (TopicGenre) response.body();
                ArrayList<Topic> listTopic = (ArrayList<Topic>) topicGenre.getTopic();
                ArrayList<Genre> listGenre = (ArrayList<Genre>) topicGenre.getGenre();


            }

            @Override
            public void onFailure(Call<List<TopicGenre>> call, Throwable t) {

            }
        });
    }


}
