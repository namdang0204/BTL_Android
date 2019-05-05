package com.example.myzing.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.myzing.Adapter.ListSongAdapter;
import com.example.myzing.Adapter.ListSongOffAdapter;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;

public class ListSongOffActivity extends AppCompatActivity {
    Toolbar toolbarListSongOff;
    RecyclerView recyclerViewListSongOff;
    ListSongOffAdapter listSongOffAdapter;

    ArrayList<Song> arrayListSongOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song_off);

        anhxa();
        getDateFromIntent();
        listSongOffAdapter = new ListSongOffAdapter(this, arrayListSongOff);
        recyclerViewListSongOff.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListSongOff.setAdapter(listSongOffAdapter);

    }

    private void anhxa() {
        toolbarListSongOff = (Toolbar) findViewById(R.id.toolbar_list_song_off);
        recyclerViewListSongOff = (RecyclerView) findViewById(R.id.recycleview_list_song_off);
        arrayListSongOff = new ArrayList<>();
    }

    private void getDateFromIntent(){
        Intent intent = getIntent();
        if(intent.hasExtra("listSongOff")){
            arrayListSongOff = intent.getParcelableArrayListExtra("listSongOff");
        }
    }
}
