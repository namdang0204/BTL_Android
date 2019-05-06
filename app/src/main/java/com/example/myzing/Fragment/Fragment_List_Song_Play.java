package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Adapter.ListSongPlayAdapter;
import com.example.myzing.R;

public class Fragment_List_Song_Play extends Fragment {

    View view;
    RecyclerView recyclerViewListSongPlay;
    public static ListSongPlayAdapter listSongPlayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_song_play, container, false);
        recyclerViewListSongPlay = view.findViewById(R.id.recycleview_list_song_play);
        if (PlayMusicActivity.listSong.size() > 0) {
            listSongPlayAdapter = new ListSongPlayAdapter(getActivity(), PlayMusicActivity.listSong);
            recyclerViewListSongPlay.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewListSongPlay.setAdapter(listSongPlayAdapter);
        }
        recyclerViewListSongPlay.scrollToPosition(PlayMusicActivity.position);
//        listSongPlayAdapter.notifyItemChanged(PlayMusicActivity.position);
        return view;
    }
}
