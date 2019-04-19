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

public class AdvertiseAdapter extends PagerAdapter {
    Context context;
    ArrayList<Advertise> arrayListAdvertise;

    public AdvertiseAdapter() {
    }

    public AdvertiseAdapter(Context context, ArrayList<Advertise> arrayListAdvertise) {
        this.context = context;
        this.arrayListAdvertise = arrayListAdvertise;
    }

    @Override
    public int getCount() {
        return arrayListAdvertise.size();
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

        ImageView imgBackgroundAdvertise= view.findViewById(R.id.imageview_background_advertise);
        ImageView imgIconAdvertise= view.findViewById(R.id.imageview_icon_advertise);
        TextView textViewTitleAdvertise=  view.findViewById(R.id.textview_title_advertise);
        TextView textviewContent= view. findViewById(R.id.textview_content);

        Picasso.with(context).load(arrayListAdvertise.get(position).getImageAdvertise()).into(imgBackgroundAdvertise);
        Picasso.with(context).load(arrayListAdvertise.get(position).getImageSong()).into(imgIconAdvertise);
        textViewTitleAdvertise.setText(arrayListAdvertise.get(position).getNameSong());
        textviewContent.setText(arrayListAdvertise.get(position).getContent());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
