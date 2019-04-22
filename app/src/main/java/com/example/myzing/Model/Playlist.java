package com.example.myzing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("topicId")
    @Expose
    private String topicId;
    @SerializedName("namePlaylist")
    @Expose
    private String namePlaylist;
    @SerializedName("imagePlaylist")
    @Expose
    private String imagePlaylist;
    @SerializedName("imageIcon")
    @Expose
    private String imageIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public String getImagePlaylist() {
        return imagePlaylist;
    }

    public void setImagePlaylist(String imagePlaylist) {
        this.imagePlaylist = imagePlaylist;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

}