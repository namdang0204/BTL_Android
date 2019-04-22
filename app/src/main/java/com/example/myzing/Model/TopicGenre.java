package com.example.myzing.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicGenre {

    @SerializedName("Topic")
    @Expose
    private List<Topic> topic = null;
    @SerializedName("Genre")
    @Expose
    private List<Genre> genre = null;

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

}
