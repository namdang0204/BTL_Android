package com.example.myzing.DAO;

import com.example.myzing.Model.Album;
import com.example.myzing.Model.Genre;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumDAO {
    private DataService dataService;
    private ArrayList<Album> arrayListAlbum;

    public AlbumDAO() {
        dataService = APIService.getDataService();
        arrayListAlbum = new ArrayList<>();
    }

    public void getAlbumOfGenre(Genre genre, final IAlbumDAO callback){
        dataService = APIService.getDataService();
        Call<List<Album>> listCall = dataService.GetListAlbumOfGenre(genre.getIdGenre());
        listCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()){
                    arrayListAlbum = (ArrayList<Album>) response.body();
                    callback.returnListAlbum(arrayListAlbum);
                }
            }
            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }


}
