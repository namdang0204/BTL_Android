package com.example.myzing.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.LoginActivity;
import com.example.myzing.Model.User;
import com.example.myzing.R;
import com.example.myzing.Utilities.Utilities;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import static com.example.myzing.Utilities.Utilities.SHARED_PREFERENCES_NAME;

public class Fragment_Account extends Fragment {

    View view;
    TextView textViewNameAccount, textViewStatusLogin;
    ImageView imageViewStatusLogin, imageViewProfile;
    LinearLayout linearLayout;
    ProfilePictureView profilePictureViewFB;
    CardView cardView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final int REQUEST_CODE = 24;
    private boolean checkLogin = false;
    public static String typeUser = "";

//    GoogleSignInClient mGoogleSignInClient;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        anhxa();

        sharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_birthday"));
            LoginManager.getInstance().logOut();
        }
        return view;
    }

    private void anhxa() {
        textViewNameAccount = view.findViewById(R.id.textview_name_account);
        textViewStatusLogin = view.findViewById(R.id.textview_status_login);
        imageViewStatusLogin = view.findViewById(R.id.imageview_status_login);
        imageViewProfile = view.findViewById(R.id.imageview_profile);
        linearLayout = view.findViewById(R.id.linearlayout_account);
        profilePictureViewFB = view.findViewById(R.id.profile_picture);
        cardView =  view.findViewById(R.id.cardview_image_user);
    }

    private void login(){
        Boolean statusLogin = sharedPreferences.getBoolean(Utilities.STATUS_LOGIN, false);
        if (!statusLogin) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            if (statusLogin) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("MyZing");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        String typeUser = sharedPreferences.getString(Utilities.TYPE_USER, "user");
                        if(typeUser.equalsIgnoreCase("facebook")){
                            LoginManager.getInstance().logOut();
                            profilePictureViewFB.setVisibility(View.GONE);
                            imageViewProfile.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                        }
                        if(typeUser.equalsIgnoreCase("google")){
                            LoginActivity.mGoogleSignInClient.revokeAccess()
                                    .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getActivity(), "Logout successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        textViewStatusLogin.setText("Login");
                        textViewNameAccount.setText("Đăng nhập");
                        profilePictureViewFB.setProfileId(null);
                        imageViewProfile.setImageResource(0);
                        imageViewStatusLogin.setImageResource(R.drawable.user_account);
                        clearUser();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            if(data.hasExtra("userFacebook")){
                user = (User) data.getSerializableExtra("userFacebook");
                textViewNameAccount.setText(user.getUserName());
                profilePictureViewFB.setVisibility(View.VISIBLE);
                imageViewProfile.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                profilePictureViewFB.setProfileId(user.getId());
                saveUser(user,"facebook");
            }
            if(data.hasExtra("userGoogle")){
                user = (User) data.getSerializableExtra("userGoogle");
                textViewNameAccount.setText(user.getUserName());
                profilePictureViewFB.setVisibility(View.GONE);
                imageViewProfile.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(user.getImageUser()).into(imageViewProfile);
                saveUser(user, "google");
            }
            if(data.hasExtra("user")){
                profilePictureViewFB.setVisibility(View.GONE);
                imageViewProfile.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
                user = (User) data.getSerializableExtra("user");
                textViewNameAccount.setText(user.getUserName());
                Picasso.with(getContext()).load(user.getImageUser()).into(imageViewProfile);
                profilePictureViewFB.removeAllViews();
                saveUser(user, "user");
            }
            textViewStatusLogin.setText("Logout");
            imageViewStatusLogin.setImageResource(R.drawable.user_account_verified);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveUser(User user, String typeUser){
        editor.putString(Utilities.ID_USER, user.getId());
        editor.putString(Utilities.NAME_USER, user.getUserName());
        editor.putString(Utilities.IMAGE_USER, user.getImageUser());
        editor.putString(Utilities.TYPE_USER, typeUser);
        editor.putBoolean(Utilities.STATUS_LOGIN, true);
        editor.apply();
    }

    private void clearUser(){
        editor.putString(Utilities.ID_USER, "");
        editor.putString(Utilities.NAME_USER, "");
        editor.putString(Utilities.IMAGE_USER, "");
        editor.putString(Utilities.TYPE_USER, "");
        editor.putBoolean(Utilities.STATUS_LOGIN, false);
        editor.apply();
    }

    @Override
    public void onStart() {
        boolean statusLogin = sharedPreferences.getBoolean(Utilities.STATUS_LOGIN, false);
        if(statusLogin){
            String nameUser = sharedPreferences.getString(Utilities.NAME_USER, "Name");
            String imageUser = sharedPreferences.getString(Utilities.IMAGE_USER, "image");
            String typeUser = sharedPreferences.getString(Utilities.TYPE_USER, "user");
            if(typeUser.equalsIgnoreCase("facebook")){
                profilePictureViewFB.setVisibility(View.VISIBLE);
                imageViewProfile.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
                profilePictureViewFB.setProfileId(sharedPreferences.getString(Utilities.ID_USER, ""));
            }else{
                profilePictureViewFB.setVisibility(View.GONE);
                imageViewProfile.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
                Picasso.with(getContext()).load(imageUser).into(imageViewProfile);
            }
            textViewNameAccount.setText(nameUser);
            textViewStatusLogin.setText("Logout");
            imageViewStatusLogin.setImageResource(R.drawable.user_account_verified);
        }
        super.onStart();
    }
}
