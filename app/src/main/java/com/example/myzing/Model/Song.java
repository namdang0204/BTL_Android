package com.example.myzing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("albumId")
    @Expose
    private String albumId;
    @SerializedName("nameSong")
    @Expose
    private String nameSong;
    @SerializedName("imageSong")
    @Expose
    private String imageSong;
    @SerializedName("linkSong")
    @Expose
    private String linkSong;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("lyric")
    @Expose
    private String lyric;
    @SerializedName("likes")
    @Expose
    private String likes;

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

}