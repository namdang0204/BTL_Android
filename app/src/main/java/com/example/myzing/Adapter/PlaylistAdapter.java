package com.example.myzing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(Context context, int resource, List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        TextView txtview_NamePlaylist;
        ImageView imgBackgroundPlaylist, imgPlaylist;


    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        ViewHolder viewHolder= null;
        if(convertView==null){
            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.dong_playlist, null);
            viewHolder= new ViewHolder();
            viewHolder.txtview_NamePlaylist= convertView.findViewById(R.id.textView_Name_Playlist);
            viewHolder.imgBackgroundPlaylist= convertView.findViewById(R.id.imageview_background_playlist);
            viewHolder.imgPlaylist= convertView.findViewById(R.id.imageview_playlist);
            convertView.setTag(viewHolder);

        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Playlist playlist= getItem(position);
        Picasso.with(getContext()).load(playlist.getImagePlaylist()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getImagePlaylist()).into(viewHolder.imgPlaylist);
        viewHolder.txtview_NamePlaylist.setText(playlist.getNamePlaylist());
        return convertView;
    }
}
