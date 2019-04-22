package com.example.myzing.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Adapter.ViewPagerPlaylistMusicAdapter;
import com.example.myzing.Fragment.Fragment_Disk_Music;
import com.example.myzing.Fragment.Fragment_List_Song_Play;
import com.example.myzing.Fragment.Fragment_Lyric_Song;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {
    private Toolbar toolbarPlayMusic;
    private TextView textViewTimeSong, textViewTimeTotalSong;
    private SeekBar seekBarTimeSong;
    private ImageButton imageButtonShuffle, imageButtonPreview, imageButtonPlay, imageButtonNext, imageButtonRepeat;
    private ViewPager viewPagerPlayMusic;
    public static ViewPagerPlaylistMusicAdapter viewPagerPlaylistMusicAdapter;
    private Fragment_List_Song_Play fragmentListSongPlay;
    private Fragment_Disk_Music fragmentDiskMusic;
    private Fragment_Lyric_Song fragmentLyricSong;
    private MediaPlayer mediaPlayer;
    public static ArrayList<Song> listSong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataFromIntent();
        init();
        evenClick();
    }

    private void evenClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlaylistMusicAdapter.getItem(0) != null) {
                    if (listSong.size() > 0) {
                        fragmentDiskMusic.PlayMusic(listSong.get(0).getImageSong());
                        fragmentLyricSong.setLyricSong(listSong.get(0).getLyric());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonPlay.setImageResource(R.drawable.icon_play);
                } else {
                    mediaPlayer.start();
                    imageButtonPlay.setImageResource(R.drawable.icon_pause);
                }
            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        listSong.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song song = (Song) intent.getParcelableExtra("song");
                listSong.add(song);
            }

            if (intent.hasExtra("listSong")) {
                listSong = intent.getParcelableArrayListExtra("listSong");
            }
        }
    }

    private void init() {
        toolbarPlayMusic = (Toolbar) findViewById(R.id.toolbar_play_music);
        textViewTimeSong = (TextView) findViewById(R.id.textview_time_song);
        textViewTimeTotalSong = (TextView) findViewById(R.id.textview_total_time_song);
        seekBarTimeSong = (SeekBar) findViewById(R.id.seekbar_time_song);
        imageButtonShuffle = (ImageButton) findViewById(R.id.imagebutton_shuffle);
        imageButtonPreview = (ImageButton) findViewById(R.id.imagebutton_preview);
        imageButtonPlay = (ImageButton) findViewById(R.id.imagebutton_play);
        imageButtonNext = (ImageButton) findViewById(R.id.imagebutton_next);
        imageButtonRepeat = (ImageButton) findViewById(R.id.imagebutton_repeat);
        viewPagerPlayMusic = (ViewPager) findViewById(R.id.viewpager_play_music);

        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbarPlayMusic.setTitleTextColor(Color.WHITE);

        fragmentListSongPlay = new Fragment_List_Song_Play();
        fragmentDiskMusic = new Fragment_Disk_Music();
        fragmentLyricSong = new Fragment_Lyric_Song();

        viewPagerPlaylistMusicAdapter = new ViewPagerPlaylistMusicAdapter(getSupportFragmentManager());
        viewPagerPlaylistMusicAdapter.addFragment(fragmentListSongPlay);
        viewPagerPlaylistMusicAdapter.addFragment(fragmentDiskMusic);
        viewPagerPlaylistMusicAdapter.addFragment(fragmentLyricSong);
        viewPagerPlayMusic.setAdapter(viewPagerPlaylistMusicAdapter);
        viewPagerPlayMusic.setCurrentItem(1);

        fragmentDiskMusic = (Fragment_Disk_Music) viewPagerPlaylistMusicAdapter.getItem(1);

        if (listSong.size() > 0) {
            getSupportActionBar().setTitle(listSong.get(0).getNameSong());
            new PlayMp3().execute(listSong.get(0).getLinkSong());
            imageButtonPlay.setImageResource(R.drawable.icon_pause);
        }
    }

    public class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String urlSong) {
            try {
                super.onPostExecute(urlSong);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(urlSong);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewTimeTotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTimeSong.setMax(mediaPlayer.getDuration());
    }
}
