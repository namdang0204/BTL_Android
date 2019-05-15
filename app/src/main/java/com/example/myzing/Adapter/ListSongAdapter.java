package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arrayListSong;

    public ListSongAdapter(Context context, ArrayList<Song> arrayListSong) {
        this.context = context;
        this.arrayListSong = arrayListSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View  view= layoutInflater.inflate(R.layout.dong_goiy,viewGroup, false );
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song= arrayListSong.get(i);
        viewHolder.txtView_NameSong.setText(song.getNameSong());
        viewHolder.txtView_NameSinger.setText(song.getSinger());
        Picasso.with(context).load(song.getImageSong()).into(viewHolder.imgView_image_GoiY);
    }

    @Override
    public int getItemCount() {
        return arrayListSong.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtView_NameSong, txtView_NameSinger;
        private ImageButton imgButton_MenuSong;
        private ImageView imgView_image_GoiY;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView_NameSong = itemView.findViewById(R.id.textview_name_song_goiy);
            txtView_NameSinger = itemView.findViewById(R.id.textview_name_singer_goiy);
            imgButton_MenuSong = itemView.findViewById(R.id.image_button_goiy);
            imgView_image_GoiY= itemView.findViewById(R.id.imageview_image_goiy);

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
                    bundle.putParcelableArrayList("listSongOn", arrayListSong);
                    bundle.putInt("position", getPosition());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            imgButton_MenuSong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu();
                }

                private void showPopupMenu() {
                    PopupMenu popupMenu= new PopupMenu(context, imgButton_MenuSong);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_song, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
//                                case R.id.item_share:
//                                    break;
                                case R.id.item_like:


                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }

            });

        }
    }
}
