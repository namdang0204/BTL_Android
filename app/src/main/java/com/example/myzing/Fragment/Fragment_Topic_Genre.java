package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myzing.R;

public class Fragment_Topic_Genre extends Fragment {
    View view;
    TextView tv_topic_genre, tv_viewmore_topic_genre;
    RecyclerView recyclerView_Topic_Genre;



    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_topic_genre,container,false);
        tv_topic_genre= view.findViewById(R.id.txtview_topic_genre);
        tv_viewmore_topic_genre= view.findViewById(R.id.txtview_viewmore_topic_genre);
        recyclerView_Topic_Genre= view.findViewById(R.id.recycleview_topic_genre);
        tv_viewmore_topic_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
