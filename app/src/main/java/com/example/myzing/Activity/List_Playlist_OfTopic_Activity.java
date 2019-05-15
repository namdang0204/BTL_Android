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

import com.example.myzing.Adapter.ListPlaylistOfTopicAdapter;
import com.example.myzing.DAO.IPlaylistDAO;
import com.example.myzing.DAO.PlaylistDAO;
import com.example.myzing.Model.Playlist;
import com.example.myzing.Model.Topic;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Playlist_OfTopic_Activity extends AppCompatActivity {
    Topic topic;
    RecyclerView recyclerView_List_Playlist_OfTopic;
    Toolbar toolbar_List_Playlist_OfTopic;
    ListPlaylistOfTopicAdapter listPlaylistOfTopicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__playlist__of_topic_);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        new PlaylistDAO().getPlaylistOfTopic(topic, new IPlaylistDAO() {
            @Override
            public void returnListPlaylist(ArrayList<Playlist> listPlaylist) {
                while (listPlaylist.size()>0){
                    listPlaylistOfTopicAdapter = new ListPlaylistOfTopicAdapter(List_Playlist_OfTopic_Activity.this, listPlaylist);
                    recyclerView_List_Playlist_OfTopic.setLayoutManager(new GridLayoutManager(List_Playlist_OfTopic_Activity.this, 2));
                    recyclerView_List_Playlist_OfTopic.setAdapter(listPlaylistOfTopicAdapter);
                    break;
                }
            }
        });
    }

    private void init() {
        recyclerView_List_Playlist_OfTopic = findViewById(R.id.recycleview_list_playlist_oftopic);
        toolbar_List_Playlist_OfTopic = findViewById(R.id.toolbar_list_playlist_oftopic);
        setSupportActionBar(toolbar_List_Playlist_OfTopic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(topic.getNameTopic());
        toolbar_List_Playlist_OfTopic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("topic")) {
            topic = (Topic) intent.getSerializableExtra("topic");
//        Toast.makeText(this,topic.getNameTopic(), Toast.LENGTH_SHORT).show();
        }

    }
}
