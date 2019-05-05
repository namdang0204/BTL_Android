package com.example.myzing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachGoiYAdapter extends RecyclerView.Adapter<DanhSachGoiYAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arrayListSongGoiY;

    public DanhSachGoiYAdapter(Context context, ArrayList<Song> arrayListSongGoiY) {
        this.context = context;
        this.arrayListSongGoiY = arrayListSongGoiY;
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
        Song song=arrayListSongGoiY.get(i);
        viewHolder.txtView_NameSong_GoiY.setText(song.getNameSong());
        viewHolder.txtView_NameSinger_GoiY.setText(song.getSinger());
        Picasso.with(context).load(song.getImageSong()).into(viewHolder.imgView_image_GoiY);
    }

    @Override
    public int getItemCount() {
        return arrayListSongGoiY.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtView_NameSong_GoiY, txtView_NameSinger_GoiY, txtView_Viewmore_GoiY;
        private ImageButton imgButton_GoiY;
        private ImageView imgView_image_GoiY;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView_NameSong_GoiY = itemView.findViewById(R.id.textview_name_song_goiy);
            txtView_NameSinger_GoiY= itemView.findViewById(R.id.textview_name_singer_goiy);
            imgButton_GoiY= itemView.findViewById(R.id.image_button_goiy);
            imgView_image_GoiY= itemView.findViewById(R.id.imageview_image_goiy);
        }
    }
}
