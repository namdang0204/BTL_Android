package com.example.myzing.Fragment;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import com.example.myzing.Activity.LoginFbActivity;
import com.example.myzing.Activity.MainActivity;
import com.example.myzing.Model.User;
import com.example.myzing.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class Fragment_Account extends Fragment {

    View view;
    TextView textViewNameAccount, textViewStatusLogin;
    ImageView imageViewStatusLogin, imageViewProfile;
    LinearLayout linearLayout;
    ProfilePictureView profilePictureView;
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
            Toast.makeText(getActivity(), "login", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void anhxa() {
        textViewNameAccount = view.findViewById(R.id.textview_name_account);
        textViewStatusLogin = view.findViewById(R.id.textview_status_login);
        imageViewStatusLogin = view.findViewById(R.id.imageview_status_login);
        imageViewProfile = view.findViewById(R.id.imageview_profile);
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

                LoginFbActivity.mGoogleSignInClient.signOut()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(), "Logout successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                textViewStatusLogin.setText("Login");
                textViewNameAccount.setText("Đăng nhập");
                profilePictureView.setProfileId(null);
                imageViewProfile.setImageResource(0);
                imageViewStatusLogin.setImageResource(R.drawable.user_account);
                checkLogin = false;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            checkLogin = true;
            if(data.hasExtra("userFacebook")){
                user = (User) data.getSerializableExtra("userFacebook");
                textViewNameAccount.setText(user.getUserName());
                profilePictureView.setProfileId(user.getId());
                typeUser = "facebook";
            }
            if(data.hasExtra("userGoogle")){
                user = (User) data.getSerializableExtra("userGoogle");
                textViewNameAccount.setText(user.getUserName());
                Picasso.with(getContext()).load(user.getImageUser()).into(imageViewProfile);
                typeUser = "google";
            }
            textViewStatusLogin.setText("Logout");
            imageViewStatusLogin.setImageResource(R.drawable.user_account_verified);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @Override
//    public void onStart() {
//
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
//        if(account!=null){
//            checkLogin = true;
//            textViewNameAccount.setText(account.getDisplayName());
//            Picasso.with(getContext()).load(account.getPhotoUrl()).into(imageViewProfile);
//            textViewStatusLogin.setText("Logout");
//            imageViewStatusLogin.setImageResource(R.drawable.user_account_verified);
//        }
//        super.onStart();
//    }
}
