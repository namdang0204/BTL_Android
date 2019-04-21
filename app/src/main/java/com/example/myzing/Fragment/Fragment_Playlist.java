package com.example.myzing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myzing.Model.Playlist;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.List;

import retrofit2.Call;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lv_Playlist;
    TextView tv_Title_Playlist, tv_More_Playlist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lv_Playlist =view.findViewById(R.id.listView_Playlist);
        tv_Title_Playlist= view.findViewById(R.id.textView_Title_Playlist);
        tv_More_Playlist= view.findViewById(R.id.textView_More_Playlist);
        GetData();
        return view;
    }

    private void GetData() {

    }

}
