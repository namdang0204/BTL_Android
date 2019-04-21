package com.example.myzing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Advertise implements Serializable{

    @SerializedName("idAdvertise")
    @Expose
    private String idAdvertise;
    @SerializedName("imageAdvertise")
    @Expose
    private String imageAdvertise;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("nameSong")
    @Expose
    private String nameSong;
    @SerializedName("imageSong")
    @Expose
    private String imageSong;

    public String getIdAdvertise() {
        return idAdvertise;
    }

    public void setIdAdvertise(String idAdvertise) {
        this.idAdvertise = idAdvertise;
    }

    public String getImageAdvertise() {
        return imageAdvertise;
    }

    public void setImageAdvertise(String imageAdvertise) {
        this.imageAdvertise = imageAdvertise;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
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

}