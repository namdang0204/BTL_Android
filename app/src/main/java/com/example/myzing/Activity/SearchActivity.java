package com.example.myzing.Activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myzing.Adapter.DanhSachGoiYAdapter;
import com.example.myzing.DAO.ISongDAO;
import com.example.myzing.DAO.SongDAO;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        setSupport
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbarSearch.setTitle("");
//        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                new SongDAO().getSongSearch(s, new ISongDAO() {
                    @Override
                    public void returnListSong(ArrayList<Song> listSong) {
//                        Iterator it = listSong.iterator();
                        while (listSong.size() > 0){
                            arrayListSong = listSong;
                                danhSachGoiYAdapter = new DanhSachGoiYAdapter(SearchActivity.this, arrayListSong);
                                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                                recyclerViewSearch.setAdapter(danhSachGoiYAdapter);
                            break;
                        }
                    }
                });
//                Call<List<Song>> listCall = APIService.getDataService().GetSongSearch(s);
//                listCall.enqueue(new Callback<List<Song>>() {
//                    @Override
//                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
//                        if (response.isSuccessful()) {
//                            arrayListSong = (ArrayList<Song>) response.body();
//                            if(arrayListSong.size()>0){
//                                danhSachGoiYAdapter = new DanhSachGoiYAdapter(SearchActivity.this, arrayListSong);
//                                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
//                                recyclerViewSearch.setAdapter(danhSachGoiYAdapter);
//                            }else{
//
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Song>> call, Throwable t) {
//
//                    }
//                });
//                arrayListSong = new SongDAO().getSongSearch(s);
//                if(arrayListSong.size()>0){
//                    danhSachGoiYAdapter = new DanhSachGoiYAdapter(SearchActivity.this, arrayListSong);
//                    recyclerViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
//                    recyclerViewSearch.setAdapter(danhSachGoiYAdapter);
//                }else{
//
//                }
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
//        toolbarSearch = (Toolbar) findViewById(R.id.toolbar_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();
//        searchView.setIconifiedByDefault(false);
        return true;
    }
}
