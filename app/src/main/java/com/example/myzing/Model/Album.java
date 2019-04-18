package com.example.myzing.Model;

public class Album {

    private int id;
    private String nameAlbum;
    private String nameSingerAlbum;
    private String imageAlbum;

    public Album() {
    }

    public Album(int id, String nameAlbum, String nameSingerAlbum, String imageAlbum) {
        this.id = id;
        this.nameAlbum = nameAlbum;
        this.nameSingerAlbum = nameSingerAlbum;
        this.imageAlbum = imageAlbum;
    }

    public Album(String nameAlbum, String nameSingerAlbum, String imageAlbum) {
        this.nameAlbum = nameAlbum;
        this.nameSingerAlbum = nameSingerAlbum;
        this.imageAlbum = imageAlbum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
