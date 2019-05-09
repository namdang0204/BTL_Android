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

import com.example.myzing.Activity.ListSongActivity;
import com.example.myzing.Model.Album;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAlbumOfGenreAdapter extends RecyclerView.Adapter<ListAlbumOfGenreAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrayAlbum;

    public ListAlbumOfGenreAdapter(Context context, ArrayList<Album> arrayAlbum) {
        this.context = context;
        this.arrayAlbum = arrayAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater  inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_album_ofgenre, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album= arrayAlbum.get(i);
        Picasso.with(context).load(album.getImageAlbum()).into(viewHolder.imageView_List_Album_OfGenre);
        viewHolder.textView_List_Album_OfGenre.setText(album.getNameAlbum());
    }

    @Override
    public int getItemCount() {
        return arrayAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_List_Album_OfGenre;
        TextView textView_List_Album_OfGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_List_Album_OfGenre= itemView.findViewById(R.id.imageview_image_album_ofgenre);
            textView_List_Album_OfGenre= itemView.findViewById(R.id.textview_name_album_ofgenre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemAlbum", arrayAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }



}


