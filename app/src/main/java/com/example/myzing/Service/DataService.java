package com.example.myzing.Service;

import com.example.myzing.Model.Advertise;
import com.example.myzing.Model.Playlist;
import com.example.myzing.Model.Song;
import com.example.myzing.Model.TopicGenre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("getDataAdvertise.php")
    Call<List<Advertise>> GetDataAdvertise();

    @GET("getPlaylist.php")
    Call<List<Playlist>> GetPlaylist();

    @GET("getTopic_Genre.php")
    Call<TopicGenre> GetTopicGenre();

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> GetListSongOfAdvertise(@Field("idAdvertise") String idAdvertise);

    @GET("getDanhSachPlaylist.php")
    Call<List<Playlist>> GetDanhSachPlaylist();

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> GetListSongOfPlaylist(@Field("idPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> GetListSongGoiY(@Field("listSongGoiY") String stringBatKy);

    @FormUrlEncoded
    @POST("getListSong.php")
    Call<List<Song>> GetAllListSongGoiY(@Field("allListSongGoiY") String stringBatKy);
}
