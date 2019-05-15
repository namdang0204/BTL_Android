package com.example.myzing.DAO;

import android.support.v7.widget.GridLayoutManager;

import com.example.myzing.Activity.List_Playlist_OfTopic_Activity;
import com.example.myzing.Adapter.ListPlaylistOfTopicAdapter;
import com.example.myzing.Model.Playlist;
import com.example.myzing.Model.Topic;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistDAO {
    private DataService dataService;
    private ArrayList<Playlist> arrayListPlaylist;

    public PlaylistDAO() {
        dataService = APIService.getDataService();
        arrayListPlaylist = new ArrayList<>();
    }

    public void getAllPlaylist(final IPlaylistDAO callback){
        dataService= APIService.getDataService();
        Call<List<Playlist>> listCall= dataService.GetAllPlaylist();
        listCall.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
               if(response.isSuccessful()){
                   arrayListPlaylist = (ArrayList<Playlist>) response.body();
                   callback.returnListPlaylist(arrayListPlaylist);
               }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    public void getPlaylistOfTopic(Topic topic, final IPlaylistDAO callback){
        dataService = APIService.getDataService();
        Call<List<Playlist>> listCall = dataService.GetListPlaylistOfTopic(topic.getIdTopic());
        listCall.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
               if (response.isSuccessful()){
                   arrayListPlaylist = (ArrayList<Playlist>) response.body();
                   callback.returnListPlaylist(arrayListPlaylist);
               }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    public void getPlaylistHome(final IPlaylistDAO callback){
        dataService= APIService.getDataService();
        Call<List<Playlist>> listCall= dataService.GetPlaylist();
        listCall.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                if(response.isSuccessful()){
                    arrayListPlaylist = (ArrayList<Playlist>) response.body();
                    callback.returnListPlaylist(arrayListPlaylist);
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
