package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;

public class ListSongOffAdapter extends RecyclerView.Adapter<ListSongOffAdapter.ViewHolder>{

 Context context;
    ArrayList<Song> listSong;

public ListSongOffAdapter(Context context, ArrayList<Song> listSong) {
        this.context = context;
        this.listSong = listSong;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_list_song_off, viewGroup, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = listSong.get(i);
        viewHolder.textViewNameSong.setText(song.getNameSong());
        viewHolder.textViewNameSinger.setText(song.getSinger());
        }

@Override
public int getItemCount() {
        return listSong.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView textViewNameSong, textViewNameSinger;
    private ImageButton imageButtonMenu;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewNameSong = itemView.findViewById(R.id.tv_name_song_off);
        textViewNameSinger = itemView.findViewById(R.id.tv_name_singer_off);
        imageButtonMenu = itemView.findViewById(R.id.image_button_menu_song_off);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayMusicActivity.class);
                if(PlayMusicActivity.mediaPlayer != null && PlayMusicActivity.mediaPlayer.isPlaying()){
                    PlayMusicActivity.mediaPlayer.stop();
                    PlayMusicActivity.mediaPlayer.release();
                    PlayMusicActivity.mediaPlayer = null;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listSongOff", listSong);
//                bundle.putParcelable("songOff", listSong.get(getPosition()));
                bundle.putInt("position", getPosition());
//                intent.putExtra("song", listSong.get(getPosition()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }
}
}
