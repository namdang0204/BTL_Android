package com.example.myzing.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myzing.Activity.ListSongOffActivity;
import com.example.myzing.Adapter.LibraryAdapter;
import com.example.myzing.DAO.SongDAO;
import com.example.myzing.Model.Library;
import com.example.myzing.Model.Song;
import com.example.myzing.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Fragment_Library extends Fragment {
    View view;
    ListView listViewLibrary;
    ArrayList<Library> arrayListLibrary;
    ArrayList<Song> arrayListSong;
    LibraryAdapter libraryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library, null);
        anhxa();
        libraryAdapter = new LibraryAdapter(getContext(), R.layout.dong_library, arrayListLibrary);
        listViewLibrary.setAdapter(libraryAdapter);
        listViewLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getActivity(), ListSongOffActivity.class);
                    intent.putExtra("listSongOff", arrayListSong);
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }

    private void anhxa() {
        listViewLibrary = view.findViewById(R.id.listview_library);
        arrayListLibrary = new ArrayList<>();
        arrayListSong = new ArrayList<>();

        arrayListLibrary.add(new Library(0, R.drawable.icon_music_folder, "Songs", "0"));
        arrayListLibrary.add(new Library(0, R.drawable.icon_music_playlist, "Playlists", "0"));
        arrayListLibrary.add(new Library(0, R.drawable.icon_download, "Downloads", "0"));
        arrayListLibrary.add(new Library(0, R.drawable.icon_favorites, "Favorites", "0"));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        arrayListSong = new SongDAO().getSongOff(getActivity());
        arrayListLibrary.get(0).setNumberOfOneLibrary(arrayListSong.size()+"");
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        String selection = MediaStore.Audio.Media.IS_MUSIC ;
//        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            // Get columns
//            int nameColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
//            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//            int dataColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
//
//            do {
//                String name = cursor.getString(nameColumn);
//                String nameArtist = cursor.getString(artistColumn);
//                String url = cursor.getString(dataColumn);
//                arrayListSong.add(new Song(name,  url, nameArtist));
//            } while (cursor.moveToNext());
//            cursor.close();
//            Collections.sort(arrayListSong, new Comparator<Song>() {
//                @Override
//                public int compare(Song song1, Song song2) {
//                    return song1.getNameSong().compareToIgnoreCase(song2.getNameSong());
//                }
//            });
//            arrayListLibrary.get(0).setNumberOfOneLibrary(arrayListSong.size()+"");
//        }
    }
}
