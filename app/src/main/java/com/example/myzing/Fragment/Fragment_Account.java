package com.example.myzing.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myzing.Activity.LoginFbActivity;
import com.example.myzing.Model.User;
import com.example.myzing.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import java.util.Arrays;

public class Fragment_Account extends Fragment {

    View view;
    TextView textViewNameAccount, textViewStatusLogin;
    ImageView imageViewStatusLogin;
    LinearLayout linearLayout;
    ProfilePictureView profilePictureView;
    public static final int REQUEST_CODE = 24;
    private boolean checkLogin = false;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        anhxa();


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
            login();
        }
        return view;
    }

    private void anhxa() {
        textViewNameAccount = view.findViewById(R.id.textview_name_account);
        textViewStatusLogin = view.findViewById(R.id.textview_status_login);
        imageViewStatusLogin = view.findViewById(R.id.imageview_status_login);
        linearLayout = view.findViewById(R.id.linearlayout_account);
        profilePictureView = view.findViewById(R.id.profile_picture);
    }

    private void login(){
        if (textViewStatusLogin.getText().toString().equalsIgnoreCase("Login") && checkLogin == false) {
            Intent intent = new Intent(getActivity(), LoginFbActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            if (checkLogin == true) {
                LoginManager.getInstance().logOut();
                textViewStatusLogin.setText("Login");
                textViewNameAccount.setText("Đăng nhập");
                profilePictureView.setProfileId(null);
                imageViewStatusLogin.setImageResource(R.drawable.user_account);
                checkLogin = false;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("aaa", requestCode + "  " + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            checkLogin = true;
            user = (User) data.getSerializableExtra("userFacebook");
            textViewNameAccount.setText(user.getUserName());
            profilePictureView.setProfileId(user.getId());
            textViewStatusLogin.setText("Logout");
            imageViewStatusLogin.setImageResource(R.drawable.user_account_verified);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
