package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Activity.List_Album_OfGenre_Activity;
import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.ViewHolder>{

    Context context;
    ArrayList<Genre> arrayListGenre;

    public ListGenreAdapter(Context context, ArrayList<Genre> arrayListGenre) {
        this.context = context;
        this.arrayListGenre = arrayListGenre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_topic_genre, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListGenreAdapter.ViewHolder viewHolder, int i) {
        Genre genre = arrayListGenre.get(i);
        viewHolder.textViewNameGenre.setText(genre.getNameGenre());
        Picasso.with(context).load(genre.getImageGenre()).into(viewHolder.imageViewGenre);
    }

    @Override
    public int getItemCount() {
        return arrayListGenre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNameGenre;
        private ImageView imageViewGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameGenre = itemView.findViewById(R.id.tv_name_topic_genre);
            imageViewGenre = itemView.findViewById(R.id.imageview_topic_genre);
            imageViewGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, List_Album_OfGenre_Activity.class);
                    intent.putExtra("genre",arrayListGenre.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
