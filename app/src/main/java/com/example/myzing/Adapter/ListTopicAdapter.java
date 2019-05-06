package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Activity.List_Playlist_OfTopic_Activity;
import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Model.Topic;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTopicAdapter extends RecyclerView.Adapter<ListTopicAdapter.ViewHolder>{
    Context context;
    ArrayList<Topic> arrayListTopic;

    public ListTopicAdapter(Context context, ArrayList<Topic> arrayListTopic) {
        this.context = context;
        this.arrayListTopic = arrayListTopic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_topic_genre, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Topic topic = arrayListTopic.get(i);
        viewHolder.textViewNameTopic.setText(topic.getNameTopic());
        Picasso.with(context).load(topic.getImageTopic()).into(viewHolder.imageViewTopic);
    }

    @Override
    public int getItemCount() {
        return arrayListTopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNameTopic;
        private ImageView imageViewTopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameTopic = itemView.findViewById(R.id.tv_name_topic_genre);
            imageViewTopic = itemView.findViewById(R.id.imageview_topic_genre);
            imageViewTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, List_Playlist_OfTopic_Activity.class);
                    intent.putExtra("topic", arrayListTopic.get(getPosition()));
                    context.startActivity(intent);
                }

            });

        }
    }
}
