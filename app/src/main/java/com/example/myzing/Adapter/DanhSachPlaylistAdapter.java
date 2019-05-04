package com.example.myzing.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.ListSongActivity;
import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DanhSachPlaylistAdapter extends RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> arrayPlaylist;

    public DanhSachPlaylistAdapter(Context context, ArrayList<Playlist> arrayPlaylist) {
        this.context = context;
        this.arrayPlaylist = arrayPlaylist;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_danhsach_playlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Log.d("aaa", arrayPlaylist.size()+"");

        Playlist playlist= arrayPlaylist.get(position);
        Picasso.with(context).load(playlist.getImagePlaylist()).into(holder.imgBackgroundPlaylist);
        holder.txtViewNamePlaylist.setText(playlist.getNamePlaylist());
    }

    @Override
    public int getItemCount() {
        return arrayPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBackgroundPlaylist;
        TextView txtViewNamePlaylist;


        public ViewHolder(final View itemView) {
            super(itemView);
            imgBackgroundPlaylist = itemView.findViewById(R.id.imageview_danhsach_playlist);
            txtViewNamePlaylist = itemView.findViewById(R.id.textview_name_danhsach_playlist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemPlaylist", arrayPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}


