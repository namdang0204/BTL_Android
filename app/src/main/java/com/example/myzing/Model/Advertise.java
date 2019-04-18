package com.example.myzing.Model;

public class Advertise {
    private int id;
    private String imageAdvertise;
    private String content;

    public Advertise() {
    }

    public Advertise(int id, String imageAdvertise, String content) {
        this.id = id;
        this.imageAdvertise = imageAdvertise;
        this.content = content;
    }

    public Advertise(String imageAdvertise, String content) {
        this.imageAdvertise = imageAdvertise;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
