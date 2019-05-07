package com.example.myzing.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myzing.Adapter.DanhSachGoiYAdapter;
import com.example.myzing.DAO.SongDAO;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerViewSearch;
    private DanhSachGoiYAdapter danhSachGoiYAdapter;
    private ArrayList<Song> arrayListSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        anhxa();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                arrayListSong = new SongDAO().getSongSearch(s);
                if(arrayListSong.size()>0){
                    danhSachGoiYAdapter = new DanhSachGoiYAdapter(SearchActivity.this, arrayListSong);
                    recyclerViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerViewSearch.setAdapter(danhSachGoiYAdapter);
                }else{

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String s) {

                return false;
            }
        });


    }

    private void anhxa() {
        searchView = (SearchView) findViewById(R.id.search_view2);
        recyclerViewSearch = (RecyclerView) findViewById(R.id.recyclerview_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();
        return true;
    }
}
