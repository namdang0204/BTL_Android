package com.example.myzing.Model;

public class Topic_Genre {
    private int id;
    private String nameToGe;
    private String imageToGe;

    public Topic_Genre() {
    }

    public Topic_Genre(int id, String nameToGe, String imageToGe) {
        this.id = id;
        this.nameToGe = nameToGe;
        this.imageToGe = imageToGe;
    }

    public Topic_Genre(String nameToGe, String imageToGe) {
        this.nameToGe = nameToGe;
        this.imageToGe = imageToGe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameToGe() {
        return nameToGe;
    }

    public void setNameToGe(String nameToGe) {
        this.nameToGe = nameToGe;
    }

    public String getImageToGe() {
        return imageToGe;
    }

    public void setImageToGe(String imageToGe) {
        this.imageToGe = imageToGe;
    }
}
