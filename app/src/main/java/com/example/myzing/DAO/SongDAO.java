package com.example.myzing.DAO;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.myzing.Activity.DanhSachGoiYActivity;
import com.example.myzing.Activity.ListSongActivity;
import com.example.myzing.Adapter.ListSongAdapter;
import com.example.myzing.Model.Song;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    }

    public void getSongGoiY(final ISongDAO callback){
        try {
            Call<List<Song>> listCall = dataService.GetListSongGoiY("getSongGoiY");
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
    }

    public void getAllListSongGoiY(final ISongDAO callback){
        DataService dataService= APIService.getDataService();
        Call<List<Song>> listCall= dataService.GetAllListSongGoiY("AllSong");
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
    }

    public void getDataListSong(String title, String id, final ISongDAO callback) {
        DataService dataService = APIService.getDataService();
        Call<List<Song>> callbackListSong = null;
        if(title.equalsIgnoreCase("advertise")){
            callbackListSong = dataService.GetListSongOfAdvertise(id);
        }

        if(title.equalsIgnoreCase("playlist")){
            callbackListSong = dataService.GetListSongOfPlaylist(id);
        }

        if(title.equalsIgnoreCase("album")){
            callbackListSong = dataService.GetListSongOfAlbum(id);
        }
        callbackListSong.enqueue(new Callback<List<Song>>() {
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
    }

    public ArrayList<Song> getSongOff(Activity activity){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC ;
        Cursor cursor = activity.getContentResolver().query(uri, null, selection, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Get columns
            int nameColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int dataColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do {
                String name = cursor.getString(nameColumn);
                String nameArtist = cursor.getString(artistColumn);
                String url = cursor.getString(dataColumn);
                arrayListSong.add(new Song(name,  url, nameArtist));
            } while (cursor.moveToNext());
            cursor.close();
            Collections.sort(arrayListSong, new Comparator<Song>() {
                @Override
                public int compare(Song song1, Song song2) {
                    return song1.getNameSong().compareToIgnoreCase(song2.getNameSong());
                }
            });
        }
        return arrayListSong;
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
