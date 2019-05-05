package com.example.myzing.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Song implements Parcelable {

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
    @SerializedName("linkZing")
    @Expose
    private String linkZing;

    public Song(String nameSong, String linkSong, String singer) {
        this.nameSong = nameSong;
        this.linkSong = linkSong;
        this.singer = singer;
//        this.imageSong = imageSong;
    }

    protected Song(Parcel in) {
        idSong = in.readString();
        albumId = in.readString();
        nameSong = in.readString();
        imageSong = in.readString();
        linkSong = in.readString();
        singer = in.readString();
        lyric = in.readString();
        likes = in.readString();
        linkZing = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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

    public String getLinkZing() {
        return linkZing;
    }

    public void setLinkZing(String linkZing) {
        this.linkZing = linkZing;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(albumId);
        dest.writeString(nameSong);
        dest.writeString(imageSong);
        dest.writeString(linkSong);
        dest.writeString(singer);
        dest.writeString(lyric);
        dest.writeString(likes);
        dest.writeString(linkZing);
    }
}