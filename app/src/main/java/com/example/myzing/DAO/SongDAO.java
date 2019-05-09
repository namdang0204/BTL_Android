package com.example.myzing.DAO;

import android.os.Handler;
import android.util.Log;

import com.example.myzing.Model.Song;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.io.IOException;
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

    public void getSongSearch(String stringSearchSong, final ISongDAO callback) {

        try {
            Call<List<Song>> listCall = dataService.GetSongSearch(stringSearchSong);
            listCall.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                    if (response.isSuccessful()) {
                        arrayListSong = (ArrayList<Song>) response.body();
                        callback.returnListSong(arrayListSong);
                    }
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Call<List<Song>> listCall = dataService.GetSongSearch(stringSearchSong);
//            Response<List<Song>> response = listCall.execute();
//            arrayListSong = (ArrayList<Song>) response.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Log.d("aaa", arrayListSong.size()+"as");
    }

//    public ArrayList<Song> getSongSearch(String stringSearchSong) {
//
////        try {
////            Call<List<Song>> listCall = dataService.GetSongSearch(stringSearchSong);
////            listCall.enqueue(new Callback<List<Song>>() {
////                @Override
////                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
////                    if (response.isSuccessful()) {
////                        arrayListSong = (ArrayList<Song>) response.body();
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<List<Song>> call, Throwable t) {
////
////                }
////            });
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        try {
//            Call<List<Song>> listCall = dataService.GetSongSearch(stringSearchSong);
//            Response<List<Song>> response = listCall.execute();
//            arrayListSong = (ArrayList<Song>) response.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        Log.d("aaa", arrayListSong.size()+"as");
//        return arrayListSong;
//    }
}
