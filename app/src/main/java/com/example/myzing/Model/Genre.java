package com.example.myzing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genre implements Serializable {

    @SerializedName("idGenre")
    @Expose
    private String idGenre;
    @SerializedName("nameGenre")
    @Expose
    private String nameGenre;
    @SerializedName("imageGenre")
    @Expose
    private String imageGenre;

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getImageGenre() {
        return imageGenre;
    }

    public void setImageGenre(String imageGenre) {
        this.imageGenre = imageGenre;
    }

}
