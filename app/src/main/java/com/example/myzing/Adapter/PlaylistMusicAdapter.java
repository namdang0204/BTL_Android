package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Fragment.Fragment_List_Song_Play;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;

public class PlaylistMusicAdapter extends RecyclerView.Adapter<PlaylistMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> listSong;

    public PlaylistMusicAdapter(Context context, ArrayList<Song> listSong) {
        this.context = context;
        this.listSong = listSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_playlist_music,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = listSong.get(i);
        viewHolder.textViewIndexPlaylistMusic.setText(i+1+"");
        viewHolder.textViewNameSongPlaylistMusic.setText(song.getNameSong());
        viewHolder.textViewNameSingerPlaylistMusic.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewIndexPlaylistMusic, textViewNameSongPlaylistMusic, textViewNameSingerPlaylistMusic;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewIndexPlaylistMusic = itemView.findViewById(R.id.textview_index_playlist_music);
            textViewNameSongPlaylistMusic = itemView.findViewById(R.id.textview_name_song_playlist_music);
            textViewNameSingerPlaylistMusic = itemView.findViewById(R.id.textview_name_singer_playlist_music);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Toast.makeText(context, listSong.get(getPosition()).getNameSong(), Toast.LENGTH_SHORT).show();
////                    Toast.makeText(context, getPosition()+"", Toast.LENGTH_SHORT).show();
//                    PlayMusicActivity.position = getPosition();
//                    PlayMusicActivity.select = true;
////                    Intent intent = new Intent(context, PlayMusicActivity.class);
////                    intent.putParcelableArrayListExtra("listSong", listSong);
////                    intent.putExtra("test", getPosition()+"");
////                    context.startActivity(intent);
//                }
//            });
        }
    }


}
