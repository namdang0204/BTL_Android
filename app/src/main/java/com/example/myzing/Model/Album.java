package com.example.myzing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Album implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameAlbum")
    @Expose
    private String nameAlbum;
    @SerializedName("nameSingerAlbum")
    @Expose
    private String nameSingerAlbum;
    @SerializedName("imageAlbum")
    @Expose
    private String imageAlbum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameSingerAlbum() {
        return nameSingerAlbum;
    }

    public void setNameSingerAlbum(String nameSingerAlbum) {
        this.nameSingerAlbum = nameSingerAlbum;
    }

    public String getImageAlbum() {
        return imageAlbum;
    }

    public void setImageAlbum(String imageAlbum) {
        this.imageAlbum = imageAlbum;
    }
}
