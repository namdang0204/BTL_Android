package com.example.myzing.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.myzing.Adapter.ListSongAdapter;
import com.example.myzing.DAO.ISongDAO;
import com.example.myzing.DAO.SongDAO;
import com.example.myzing.Model.Advertise;
import com.example.myzing.Model.Album;
import com.example.myzing.Model.Playlist;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerViewListSong;
    private FloatingActionButton floatingActionButton;
    private ImageView imageViewListSong;
    private Advertise advertise;
    private Playlist playlist;
    private Album album;
    private ListSongAdapter listSongAdapter;

    private ArrayList<Song> arrayListSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        anhXa();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        init();
        getDataListSong();

    }

    //get dữ liệu từ server gửi về
    private void getDataListSong() {
        if (advertise != null) {
            setValueInView(advertise.getNameSong(), advertise.getImageSong());
            new SongDAO().getDataListSong("advertise", advertise.getIdAdvertise(), new ISongDAO() {
                @Override
                public void returnListSong(ArrayList<Song> listSong) {
                    setAdapter(listSong);
                }
            });
        }

        if (playlist != null) {
            setValueInView(playlist.getNamePlaylist(), playlist.getImagePlaylist());
            new SongDAO().getDataListSong("playlist", playlist.getId(), new ISongDAO() {
                @Override
                public void returnListSong(ArrayList<Song> listSong) {
                    setAdapter(listSong);
                }
            });
        }

        if (album != null) {
            setValueInView(album.getNameAlbum(), album.getImageAlbum());
            new SongDAO().getDataListSong("album", album.getId(), new ISongDAO() {
                @Override
                public void returnListSong(ArrayList<Song> listSong) {
                    setAdapter(listSong);
                }
            });
        }
    }

    public void setAdapter(ArrayList<Song> listSong) {
        while (listSong.size() > 0) {
            arrayListSong = listSong;
            listSongAdapter = new ListSongAdapter(ListSongActivity.this, arrayListSong);
            recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
            recyclerViewListSong.setAdapter(listSongAdapter);
            evenClick();
            break;
        }
    }

    private void setValueInView(String name, String image) {
        collapsingToolbarLayout.setTitle(name);
        try {
            //image lưu dạng đường dẫn url -> cast về dạng url
            URL url = new URL(image);
            //convert từ url -> Bitmap để gắn dữ liệu lên
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            //android tự động kiểm tra sdk để sử dụng (từ 16 trở lên)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
            Picasso.with(this).load(image).into(imageViewListSong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar_list);
        recyclerViewListSong = (RecyclerView) findViewById(R.id.recycleview_list_song);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        imageViewListSong = (ImageView) findViewById(R.id.imageview_list_song);

    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("advertise")) {
                advertise = (Advertise) intent.getSerializableExtra("advertise");
            }
            if (intent.hasExtra("itemPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }

            if (intent.hasExtra("itemAlbum")) {
                album = (Album) intent.getSerializableExtra("itemAlbum");
            }
        }
    }

    private void evenClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this, PlayMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listSongOn", arrayListSong);
                bundle.putInt("position", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
