package com.example.myzing.Model;

public class Song {
    private int id;
    private String nameSong;
    private String imageSong;
    private String linkSong;
    private String singer;
    private int likes;

    public Song() {
    }

    public Song(int id, String nameSong, String imageSong, String linkSong, String singer, int likes) {
        this.id = id;
        this.nameSong = nameSong;
        this.imageSong = imageSong;
        this.linkSong = linkSong;
        this.singer = singer;
        this.likes = likes;
    }

    public Song(String nameSong, String imageSong, String linkSong, String singer, int likes) {
        this.nameSong = nameSong;
        this.imageSong = imageSong;
        this.linkSong = linkSong;
        this.singer = singer;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
