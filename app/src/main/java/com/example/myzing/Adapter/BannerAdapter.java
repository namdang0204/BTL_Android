package com.example.myzing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myzing.Model.Advertise;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<Advertise> arrayListBanner;

    public BannerAdapter() {
    }

    public BannerAdapter(Context context, ArrayList<Advertise> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);
        ImageView imgbackgroundbanner= view.findViewById(R.id.imageviewbackgroundbanner);
        ImageView imgbanner= view.findViewById(R.id.imageviewbanner);
        TextView textviewtitle=  view.findViewById(R.id.textviewtitlebanner);
        TextView textviewcontent= view. findViewById(R.id.textviewcontent);
//        Picasso.with(context).load(arrayListBanner.get(position).get;
        return view;
    }
}
