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

import java.util.List;


public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@androidx.annotation.NonNull Context context, int resource, @androidx.annotation.NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txt_Playlist;
        ImageView imageView_Backgound_Playlist, imageView_Playlist;
    }

    @NonNull
    @Override
    public View getView(int position, @androidx.annotation.Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;

        return super.getView(position, convertView, parent);
    }
}

