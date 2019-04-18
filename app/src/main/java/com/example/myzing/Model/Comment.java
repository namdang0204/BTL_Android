package com.example.myzing.Model;

import java.util.Date;

public class Comment {
    private int id;
    private String contentComent;
    private Date timeComment;

    public Comment() {
    }

    public Comment(int id, String contentComent, Date timeComment) {
        this.id = id;
        this.contentComent = contentComent;
        this.timeComment = timeComment;
    }

    public Comment(String contentComent, Date timeComment) {
        this.contentComent = contentComent;
        this.timeComment = timeComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentComent() {
        return contentComent;
    }

    public void setContentComent(String contentComent) {
        this.contentComent = contentComent;
    }

    public Date getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(Date timeComment) {
        this.timeComment = timeComment;
    }
}
