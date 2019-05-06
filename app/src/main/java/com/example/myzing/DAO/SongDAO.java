package com.example.myzing.DAO;

import android.util.Log;

import com.example.myzing.Model.Song;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDAO {
    private DataService dataService;
    private ArrayList<Song> arrayListSong;

    public SongDAO() {
        dataService = APIService.getDataService();
        arrayListSong = new ArrayList<>();
    }

    public ArrayList<Song> getSongSearch(String stringSearchSong){
        Call<List<Song>> listCall = dataService.GetSongSearch("Chỉ");
        listCall.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arrayListSong = (ArrayList<Song>) response.body();
                Log.d("aaa", arrayListSong.get(0).getNameSong());
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
        return arrayListSong;
    }
}
