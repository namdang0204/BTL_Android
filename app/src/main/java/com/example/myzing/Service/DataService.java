package com.example.myzing.Service;

import com.example.myzing.Model.Advertise;
import com.example.myzing.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET ("getDataAdvertise.php")
    Call<List<Advertise>> GetDataAdvertise();

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> GetListSongOfAdvertise(@Field("idAdvertise") String idAdvertise);

}
