package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.R;

public class Fragment_Lyric_Song extends Fragment {
    private TextView textViewLyricSong;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lyric_song, container, false);
        textViewLyricSong = view.findViewById(R.id.textview_lyric_song);
        setLyricSong(PlayMusicActivity.listSong.get(PlayMusicActivity.position).getLyric());
        return view;
    }


    public void setLyricSong(String lyricSong){
        textViewLyricSong.setText(lyricSong);
    }
}
