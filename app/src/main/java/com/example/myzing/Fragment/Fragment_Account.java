package com.example.myzing.Fragment;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myzing.Activity.LoginFbActivity;
import com.example.myzing.Model.User;
import com.example.myzing.R;
import com.squareup.picasso.Picasso;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Account extends Fragment {

    View view;
    CircleImageView circleImageView_account;
    TextView textViewNameAccount, textViewLogin;
    ImageView imageViewLogin;
    LinearLayout linearLayout;
    public static final int REQUEST_CODE = 24;

    Context context;

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        anhxa();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginFbActivity.class);
                startActivity(intent);
//                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        getAccount();
        return view;
    }

    private void anhxa() {
        circleImageView_account =  view.findViewById(R.id.circleimageview_account);
        textViewNameAccount = view.findViewById(R.id.textview_name_account);
        textViewLogin = view.findViewById(R.id.textview_login);
        imageViewLogin= view.findViewById(R.id.imageview_login);
        linearLayout = view.findViewById(R.id.linearlayout_account);
    }

    private void getAccount(){
//        URL imageURL = new URL("http://graph.facebook.com/" + id
//                + "/picture?type=large");
        Bundle bundle = getArguments();
        if(bundle != null){
            user = (User) bundle.getSerializable("userFacebook");
            textViewNameAccount.setText(user.getUserName());
//            Toast.makeText(getActivity(), user.getUserName(), Toast.LENGTH_SHORT).show();
        }
//        Intent intent = getActivity().getIntent();
//        if(intent.hasExtra("userFacbook")){
//            user = (User) intent.getSerializableExtra("userFacebook");
//            Toast.makeText(getActivity(), user.getUserName(), Toast.LENGTH_SHORT).show();
//        }
//        return user;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null ){
            Toast.makeText(getActivity(), data.getStringExtra("userFacebook"), Toast.LENGTH_SHORT).show();
//            user = (User) data.getSerializableExtra("userFacebook");
//            textViewNameAccount.setText(user.getUserName());
//            Picasso.with(getContext()).load(Uri.parse(user.getImageUser())).into(circleImageView_account);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
