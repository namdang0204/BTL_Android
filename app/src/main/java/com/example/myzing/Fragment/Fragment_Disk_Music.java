package com.example.myzing.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myzing.Activity.CommentActivity;
import com.example.myzing.Activity.PlayMusicActivity;
import com.example.myzing.Model.Song;
import com.example.myzing.R;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Disk_Music extends Fragment {

    View view;
    CircleImageView circleImageViewDiskMusic;
    ImageButton imageButtonShare, imageButtonComment;
    ObjectAnimator objectAnimator;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;

    private LikeView btnLike;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_disk_music, container, false);

        circleImageViewDiskMusic = view.findViewById(R.id.circleimageview_disk_music);
        imageButtonShare = view.findViewById(R.id.imagebutton_share);
        imageButtonComment = view.findViewById(R.id.imagebutton_comment);
        btnLike = (LikeView) view.findViewById(R.id.btnLike);

        shareDialog = new ShareDialog(getActivity());

        objectAnimator = ObjectAnimator.ofFloat(circleImageViewDiskMusic, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());

        evenClick();
        return view;
    }

    private void evenClick(){
        final Song song = PlayMusicActivity.listSong.get(PlayMusicActivity.position);
        imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        shareLinkContent = new ShareLinkContent.Builder()
                                .setQuote(song.getNameSong() + "\n" + song.getSinger())
                                .setContentUrl(Uri.parse(song.getLinkZing()))
                                .build();
                    }
                    shareDialog.show(shareLinkContent);

            }
        });

        imageButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra("url", song.getLinkZing());
                startActivity(intent);
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLike.setObjectIdAndType(song.getLinkZing(), LikeView.ObjectType.PAGE);
            }
        });


    }

//    private void onClickDisk(View view) {
//
//        final Song song = PlayMusicActivity.listSong.get(PlayMusicActivity.position);
//        switch (view.getId()) {
//            case R.id.imagebutton_share:
//                if (Fragment_Account.typeUser.equalsIgnoreCase("facebook")) {
//                    if (ShareDialog.canShow(ShareLinkContent.class)) {
//                        shareLinkContent = new ShareLinkContent.Builder()
//                                .setQuote(song.getNameSong() + "\n" + song.getSinger())
//                                .setContentUrl(Uri.parse(song.getLinkZing()))
//                                .build();
//                    }
//                    shareDialog.show(shareLinkContent);
//                }
//                break;
//            case R.id.imagebutton_comment:
//                Intent intent = new Intent(getContext(), CommentActivity.class);
//                intent.putExtra("url", song.getLinkZing());
//                startActivity(intent);
//                break;
//        }
//    }

    public void PlayMusic(String imageSong) {
        Picasso.with(getContext()).load(imageSong).placeholder(R.drawable.disk_music_off).into(circleImageViewDiskMusic);
        objectAnimator.start();
    }
}
