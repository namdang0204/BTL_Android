package com.example.myzing.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myzing.Model.User;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import com.example.myzing.R;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    CallbackManager callbackManager;
    LoginButton buttonLoginFb;
    TextInputLayout textInputLayoutUserName, textInputLayoutPassword;
    Button buttonLogin;
    public static GoogleSignInClient mGoogleSignInClient;
    SignInButton buttonSignInGoogle;
    final int RC_SIGN_IN = 0;
    final String TAG = "Error";

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb);

        callbackManager = CallbackManager.Factory.create();

        user = new User();

        anhxa();

        buttonLoginFb.setReadPermissions(Arrays.asList("public_profile", "email"));
        buttonSignInGoogle.setOnClickListener(this);

        setLogin_ButtonFb();

    }

    private void anhxa() {
        buttonLoginFb = (LoginButton) findViewById(R.id.login_fb_button);
        buttonSignInGoogle = (SignInButton) findViewById(R.id.sign_in_google_button);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.text_input_layout_username);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.text_input_layout_password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonSignInGoogle.setSize(SignInButton.SIZE_STANDARD);
    }

    private void signInGoogle() {

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private boolean validateUserName(){
        String userName = textInputLayoutUserName.getEditText().getText().toString().trim();
        if(userName.isEmpty()){
            textInputLayoutUserName.setError("Không được để trống");
            return false;
        }else{
            textInputLayoutUserName.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String password = textInputLayoutPassword.getEditText().getText().toString().trim();
        if(password.isEmpty()){
            textInputLayoutPassword.setError("Không được để trống");
            return false;
        }else{
            textInputLayoutPassword.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_google_button:
                signInGoogle();
                break;
            case R.id.button_login:
                if(!validateUserName() | !validatePassword()){
                   return;
                }
                String userName = textInputLayoutUserName.getEditText().getText().toString().trim();
                String passWord = textInputLayoutPassword.getEditText().getText().toString().trim();
                user = new User();
                user.setUserName(userName);
                user.setPassword(passWord);
                checkLogin(user);
                break;
            case R.id.button_creat_account:
                Intent intent = new Intent(this, CreatAccountActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }



    private void checkLogin(User user) {
//        final boolean[] check = {false};
        DataService dataService = APIService.getDataService();
        Call<User> callBack = dataService.checkLogin(user.getUserName(), user.getPassword());
        callBack.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(!user.getUserName().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Login Thành công", Toast.LENGTH_SHORT).show();
//                    check[0] = true;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", user);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
//        return check[0];
    }

    private void signOutGoogle() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LoginActivity.this, "Sign out successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setLogin_ButtonFb() {
        buttonLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        getData(object);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("userFacebook", user);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

                // Request Graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void getData(JSONObject object) {
        try {
            String id = Profile.getCurrentProfile().getId();
            user.setId(id);
            user.setUserName(object.getString("name"));
            user.setPassword("facebook");
            user.setEmail(object.getString("email"));
            user.setImageUser("http://graph.facebook.com/" + id + "/picture?type=small");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        //Google
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            user = new User();
            user.setId(account.getId());
            user.setUserName(account.getDisplayName());
            user.setEmail(account.getEmail());
            user.setImageUser(String.valueOf(account.getPhotoUrl()));
            user.setPassword("google");

            Bundle bundle = new Bundle();
            bundle.putSerializable("userGoogle", user);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onStart() {
        //Check fb đã login
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            LoginManager.getInstance().logOut();
        }
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account!=null){
////            mGoogleSignInClient.revokeAccess();
//        }
////        updateUI(account);
        super.onStart();
    }
}
