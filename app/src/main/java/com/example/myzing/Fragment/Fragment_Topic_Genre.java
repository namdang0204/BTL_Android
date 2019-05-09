package com.example.myzing.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.DanhSachTopicGenreActivity;
import com.example.myzing.Activity.List_Album_OfGenre_Activity;
import com.example.myzing.Activity.List_Playlist_OfTopic_Activity;
import com.example.myzing.Model.Genre;
import com.example.myzing.Model.Topic;
import com.example.myzing.Model.TopicGenre;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Topic_Genre extends Fragment {
    View view;
    TextView tvtitle_topic_genre, tv_viewmore_topic_genre;
   HorizontalScrollView horizontalScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_genre, container, false);

        tvtitle_topic_genre = view.findViewById(R.id.txtview_title_topic_genre);
        tv_viewmore_topic_genre = view.findViewById(R.id.txtview_viewmore_topic_genre);
        horizontalScrollView = view.findViewById(R.id.horizontal_scrollview);
//        recyclerView_Topic_Genre= view.findViewById(R.id.recycleview_topic_genre);

        getData();

        tv_viewmore_topic_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachTopicGenreActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getDataService();
        Call<TopicGenre> listCall = dataService.GetTopicGenre();

        listCall.enqueue(new Callback<TopicGenre>() {
            @Override
            public void onResponse(Call<TopicGenre> call, Response<TopicGenre> response) {
                TopicGenre topicGenre = (TopicGenre) response.body();

                final ArrayList<Topic> listTopic = new ArrayList<>();
                listTopic.addAll(topicGenre.getTopic());

                final ArrayList<Genre> listGenre = new ArrayList<>();
                listGenre.addAll(topicGenre.getGenre());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(350, 200);
                layoutParams.setMargins(10, 20, 10, 30);

                for (int i = 0; i < listTopic.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView textView = new TextView(getActivity());
                    textView.setAllCaps(true);
                    textView.setTextColor(Color.WHITE);
                    textView.setPadding(10,150,0,0);
                    if (listTopic.get(0).getImageTopic() != null) {
                        textView.setText(listTopic.get(i).getNameTopic());
                        Picasso.with(getActivity()).load(listTopic.get(i).getImageTopic()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                    final int iFinal = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), List_Playlist_OfTopic_Activity.class);
                            intent.putExtra("topic", listTopic.get(iFinal));
                            startActivity(intent);
                        }
                    });

                    //Genre
                    CardView cardView1 = new CardView(getActivity());
                    cardView1.setRadius(10);
                    ImageView imageView1 = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    TextView textView1 = new TextView(getActivity());
                    textView1.setAllCaps(true);
                    textView1.setTextColor(Color.WHITE);
                    textView1.setPadding(10,150,0,0);
                    if(listGenre.get(0).getImageGenre()!=null){
                        textView1.setText(listGenre.get(i).getNameGenre());
                        Picasso.with(getActivity()).load(listGenre.get(i).getImageGenre()).into(imageView1);
                    }
                    cardView1.setLayoutParams(layoutParams);
                    cardView1.addView(imageView1);
                    cardView1.addView(textView1);
                    linearLayout.addView(cardView1);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), List_Album_OfGenre_Activity.class);
                            intent.putExtra("genre", listGenre.get(iFinal));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);

            }


            @Override
            public void onFailure(Call<TopicGenre> call, Throwable t) {

            }
        });
    }


}
