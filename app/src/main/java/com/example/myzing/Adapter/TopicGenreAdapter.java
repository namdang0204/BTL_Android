package com.example.myzing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;

import java.util.ArrayList;

public class TopicGenreAdapter  {
    Context context;
    ArrayList<Topic> arrayTopic;
    ArrayList<Genre> arrayGenre;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder( View itemView) {
            super(itemView);
        }
    }
}
