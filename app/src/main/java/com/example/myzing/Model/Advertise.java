package com.example.myzing.Model;

public class Advertise {
    private int idAdvertise;
    private String imageAdvertise;


    private String contentAdvertise;

    public Advertise() {
    }

    public Advertise(int idAdvertise, String imageAdvertise, String contentAdvertise) {
        this.idAdvertise = idAdvertise;
        this.imageAdvertise = imageAdvertise;
        this.contentAdvertise = contentAdvertise;
    }

    public Advertise(String imageAdvertise, String contentAdvertise) {
        this.imageAdvertise = imageAdvertise;
        this.contentAdvertise = contentAdvertise;
    }

    public int getIdAdvertise() {
        return idAdvertise;
    }

    public void setIdAdvertise(int idAdvertise) {
        this.idAdvertise = idAdvertise;
    }

    public String getImageAdvertise() {
        return imageAdvertise;
    }

    public void setImageAdvertise(String imageAdvertise) {
        this.imageAdvertise = imageAdvertise;
    }

    public String getContentAdvertise() {
        return contentAdvertise;
    }

    public void setContentAdvertise(String contentAdvertise) {
        this.contentAdvertise = contentAdvertise;
    }
}
