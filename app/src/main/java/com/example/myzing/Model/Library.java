package com.example.myzing.Model;

import java.io.Serializable;

public class Library implements Serializable {
    private int id;
    private int icon;
    private String nameLibrary;
    private String numberOfOneLibrary;

    public Library() {
    }

    public Library(int id, int icon, String nameLibrary, String numberOfOneLibrary) {
        this.id = id;
        this.icon = icon;
        this.nameLibrary = nameLibrary;
        this.numberOfOneLibrary = numberOfOneLibrary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNameLibrary() {
        return nameLibrary;
    }

    public void setNameLibrary(String nameLibrary) {
        this.nameLibrary = nameLibrary;
    }

    public String getNumberOfOneLibrary() {
        return numberOfOneLibrary;
    }

    public void setNumberOfOneLibrary(String numberOfOneLibrary) {
        this.numberOfOneLibrary = numberOfOneLibrary;
    }
}
