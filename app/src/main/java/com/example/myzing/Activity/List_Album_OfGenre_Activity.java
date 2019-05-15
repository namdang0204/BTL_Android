package com.example.myzing.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myzing.Adapter.ListAlbumOfGenreAdapter;
import com.example.myzing.DAO.AlbumDAO;
import com.example.myzing.DAO.IAlbumDAO;
import com.example.myzing.Model.Album;
import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Album_OfGenre_Activity extends AppCompatActivity {
    Genre genre;
    Toolbar toolbar_List_Album__OfGenre;
    RecyclerView recyclerView_List_Album__OfGenre;
    ListAlbumOfGenreAdapter listAlbumOfGenreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__album__of_genre_);
        GetIntent();
        init();
        GetData();
    }

    private void GetData() {
        new AlbumDAO().getAlbumOfGenre(genre, new IAlbumDAO() {
            @Override
            public void returnListAlbum(ArrayList<Album> listAlbum) {
                while (listAlbum.size()>0){
                    listAlbumOfGenreAdapter = new ListAlbumOfGenreAdapter(List_Album_OfGenre_Activity.this, listAlbum);
                    recyclerView_List_Album__OfGenre.setLayoutManager(new GridLayoutManager(List_Album_OfGenre_Activity.this, 2));
                    recyclerView_List_Album__OfGenre.setAdapter(listAlbumOfGenreAdapter);
                    break;
                }
            }
        });
    }

    private void init() {
        toolbar_List_Album__OfGenre = findViewById(R.id.toolbar_list_album_ofgenre);
        recyclerView_List_Album__OfGenre = findViewById(R.id.recycleview_list_album_ofgenre);
        setSupportActionBar(toolbar_List_Album__OfGenre);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(genre.getNameGenre());
        toolbar_List_Album__OfGenre.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("genre")) {
            genre = (Genre) intent.getSerializableExtra("genre");
//            Toast.makeText(this,genre.getNameGenre(), Toast.LENGTH_SHORT).show();
        }
    }

}
