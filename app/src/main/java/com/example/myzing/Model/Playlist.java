package com.example.myzing.Model;

public class Playlist {
    private int id;
    private String namePlaylist;
    private String imageBackground;
    private String imageIcon;

    public Playlist() {
    }

    public Playlist(int id, String namePlaylist, String imageBackground, String imageIcon) {
        this.id = id;
        this.namePlaylist = namePlaylist;
        this.imageBackground = imageBackground;
        this.imageIcon = imageIcon;
    }

    public Playlist(String namePlaylist, String imageBackground, String imageIcon) {
        this.namePlaylist = namePlaylist;
        this.imageBackground = imageBackground;
        this.imageIcon = imageIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
