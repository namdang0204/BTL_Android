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
import android.widget.Toast;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.viewHolder>{
    Context context;
    ArrayList<Song> listSong;

    public ListSongAdapter(Context context, ArrayList<Song> listSong) {
        this.context = context;
        this.listSong = listSong;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_list_song, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Song song = listSong.get(i);
        viewHolder.textViewIndex.setText(i+1+"");
        viewHolder.textViewNameSong.setText(song.getNameSong());
        viewHolder.textViewNameSinger.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView textViewIndex, textViewNameSong, textViewNameSinger;
        private ImageView imageViewLike;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIndex = itemView.findViewById(R.id.textview_index_list_song);
            textViewNameSong = itemView.findViewById(R.id.textView_name_song);
            textViewNameSinger = itemView.findViewById(R.id.textview_name_singer);
            imageViewLike = itemView.findViewById(R.id.imageview_like);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song", listSong.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
