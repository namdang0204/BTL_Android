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

import com.example.myzing.Activity.ListSongOfPlaylistOfTopicActivity;
import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlaylistOfTopicAdapter extends RecyclerView.Adapter<ListPlaylistOfTopicAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> arrayPlayist;

    public ListPlaylistOfTopicAdapter(Context context, ArrayList<Playlist> arrayPlayist) {
        this.context = context;
        this.arrayPlayist = arrayPlayist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.dong_playlist_oftopic,  viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist= arrayPlayist.get(i);
        Picasso.with(context).load(playlist.getImagePlaylist()).into(viewHolder.imageView_Playlist_OfTopic);
        viewHolder.textView_Name_Playlist_OfTopic.setText(playlist.getNamePlaylist());

    }

    @Override
    public int getItemCount() {
        return arrayPlayist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_Playlist_OfTopic;
        TextView textView_Name_Playlist_OfTopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_Playlist_OfTopic = itemView.findViewById(R.id.imageview_playlist_oftopic);
            textView_Name_Playlist_OfTopic = itemView.findViewById(R.id.textview_name_playlist_oftopic);
            imageView_Playlist_OfTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, ListSongOfPlaylistOfTopicActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
