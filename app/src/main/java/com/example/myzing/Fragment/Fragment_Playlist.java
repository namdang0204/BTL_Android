package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myzing.Adapter.PlaylistAdapter;
import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lv_Playlist;
    TextView tv_Title_Playlist, tv_More_Playlist;

    ArrayList<Playlist> arrayPlaylist;
    PlaylistAdapter playlistAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lv_Playlist =view.findViewById(R.id.listview_playlist);
        tv_Title_Playlist= view.findViewById(R.id.textview_title_playlist);
        tv_More_Playlist= view.findViewById(R.id.textview_viewmore_playlist);
        GetData();
        return view;
    }

    private void GetData() {
    DataService dataService= APIService.getDataService();
    Call<List<Playlist>>  listCall= dataService.GetPlaylist();
    listCall.enqueue(new Callback<List<Playlist>>() {
        @Override
        public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
            arrayPlaylist= (ArrayList<Playlist>) response.body();
            playlistAdapter= new PlaylistAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayPlaylist);
            lv_Playlist.setAdapter(playlistAdapter);
        }

        @Override
        public void onFailure(Call<List<Playlist>> call, Throwable t) {

        }
    });
    }

}
