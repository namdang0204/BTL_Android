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
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myzing.Adapter.ViewPagerPlaylistMusicAdapter;
import com.example.myzing.Fragment.Fragment_Disk_Music;
import com.example.myzing.Fragment.Fragment_List_Song_Play;
import com.example.myzing.Fragment.Fragment_Lyric_Song;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    private Toolbar toolbarPlayMusic;
    private TextView textViewTimeSong, textViewTimeTotalSong;
    private SeekBar seekBarTimeSong;
    private ImageButton imageButtonRandom, imageButtonPreview, imageButtonPlay, imageButtonNext, imageButtonRepeat;
    private ViewPager viewPagerPlayMusic;
    public static ViewPagerPlaylistMusicAdapter viewPagerPlaylistMusicAdapter;
    private Fragment_List_Song_Play fragmentListSongPlay;
    private Fragment_Disk_Music fragmentDiskMusic;
    private Fragment_Lyric_Song fragmentLyricSong;
    public static MediaPlayer mediaPlayer;
    public static ArrayList<Song> listSong = new ArrayList<>();
    public static int position;
    public static boolean select = false;
    private boolean repeat = false;
    private boolean checkRandom = false;
    private boolean next = false;
    RelativeLayout dongPlaylistMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataFromIntent();
        init();
        evenClick();
        position = 0;

        dongPlaylistMusic = (RelativeLayout) findViewById(R.id.dong_playlist_music);

        // Khong refresh lai fragment
        int limit = (viewPagerPlaylistMusicAdapter.getCount() > 1 ? viewPagerPlaylistMusicAdapter.getCount() - 1 : 1);
        viewPagerPlayMusic.setOffscreenPageLimit(limit);
    }

    private void evenClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlaylistMusicAdapter.getItem(0) != null) {
                    if (listSong.size() > 0) {
                        fragmentDiskMusic.PlayMusic(listSong.get(position).getImageSong());
                        fragmentLyricSong.setLyricSong(listSong.get(position).getLyric());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        seekBarTimeSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBarTimeSong.getProgress());
            }
        });
    }

    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.imagebutton_play:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonPlay.setImageResource(R.drawable.icon_play);
                } else {
                    mediaPlayer.start();
                    imageButtonPlay.setImageResource(R.drawable.icon_pause);
                }
                break;
            case R.id.imagebutton_repeat:
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imageButtonRepeat.setImageResource(R.drawable.icon_repeated);
                        imageButtonRandom.setImageResource(R.drawable.icon_random);
                    }
                    imageButtonRepeat.setImageResource(R.drawable.icon_repeated);
                    repeat = true;
                } else {
                    imageButtonRepeat.setImageResource(R.drawable.icon_repeat);
                    repeat = false;
                }
                break;
            case R.id.imagebutton_shuffle:
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtonRandom.setImageResource(R.drawable.icon_randomed);
                        imageButtonRepeat.setImageResource(R.drawable.icon_repeat);
                    }
                    imageButtonRandom.setImageResource(R.drawable.icon_randomed);
                    checkRandom = true;
                } else {
                    imageButtonRandom.setImageResource(R.drawable.icon_random);
                    checkRandom = false;
                }
                break;
            case R.id.imagebutton_next:
                if (listSong.size() > 0) {

                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if (position < listSong.size()) {
                        imageButtonPlay.setImageResource(R.drawable.icon_pause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = listSong.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listSong.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > listSong.size() - 1) {
                            position = 0;
                        }

                        //chu y..........................
                        playMusic(listSong, position);
                        updateTime();
                        notifyListSongPlayChange();
                    }

                    imageButtonPreview.setClickable(false);
                    imageButtonNext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonPreview.setClickable(true);
                            imageButtonNext.setClickable(true);
                        }
                    }, 3000);
                }
                break;
            case R.id.imagebutton_preview:
                if (listSong.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if (position < listSong.size()) {
                        imageButtonPlay.setImageResource(R.drawable.icon_pause);
                        position--;
                        if (position < 0) {
                            position = listSong.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listSong.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        //chu y..........................
                        playMusic(listSong, position);
                        updateTime();
                        notifyListSongPlayChange();
                    }

                    imageButtonPreview.setClickable(false);
                    imageButtonNext.setClickable(false);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonPreview.setClickable(true);
                            imageButtonNext.setClickable(true);
                        }
                    }, 3000);
                }
                break;
        }
    }

    private void notifyListSongPlayChange() {
        Fragment_List_Song_Play.listSongPlayAdapter.notifyDataSetChanged();
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
//
//            if(intent.hasExtra("test")){
//                Toast.makeText(this, intent.getStringExtra("test"), Toast.LENGTH_SHORT).show();
////                playMusic(listSong, Integer.valueOf(intent.getStringExtra("test")));
//            }
        }
    }

    private void init() {
        toolbarPlayMusic = (Toolbar) findViewById(R.id.toolbar_play_music);
        textViewTimeSong = (TextView) findViewById(R.id.textview_time_song);
        textViewTimeTotalSong = (TextView) findViewById(R.id.textview_total_time_song);
        seekBarTimeSong = (SeekBar) findViewById(R.id.seekbar_time_song);
        imageButtonRandom = (ImageButton) findViewById(R.id.imagebutton_shuffle);
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
            new PlayMp3().execute(listSong.get(0).getLinkSong());
            getSupportActionBar().setTitle(listSong.get(0).getNameSong());
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
            updateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewTimeTotalSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTimeSong.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBarTimeSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
//-----------------------------------------------------------------------------------------------
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (listSong.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                        if (position < listSong.size()) {
                            imageButtonPlay.setImageResource(R.drawable.icon_pause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = listSong.size();
                                }
                                position -= 1;
                            }

                            if (checkRandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(listSong.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > listSong.size() - 1) {
                                position = 0;
                            }
                        }
                        //chu y..........................
                        playMusic(listSong, position);
                        notifyListSongPlayChange();

                        imageButtonPreview.setClickable(false);
                        imageButtonNext.setClickable(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imageButtonPreview.setClickable(true);
                                imageButtonNext.setClickable(true);
                            }
                        }, 3000);
                    }
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void playMusic(List<Song> listSong, int position) {
//        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
        new PlayMp3().execute(listSong.get(position).getLinkSong());
        fragmentDiskMusic.PlayMusic(listSong.get(position).getImageSong());
        fragmentLyricSong.setLyricSong(listSong.get(position).getLyric());
        getSupportActionBar().setTitle(listSong.get(position).getNameSong());
        imageButtonPlay.setImageResource(R.drawable.icon_pause);
    }

}
