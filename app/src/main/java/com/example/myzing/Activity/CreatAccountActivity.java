package com.example.myzing.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myzing.Model.User;
import com.example.myzing.R;
import com.example.myzing.Service.APIService;
import com.example.myzing.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout textInputLayoutUserName, textInputLayoutPassword, textInputLayoutEmail;
    Button buttonCreatAccount;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        anhxa();
    }

    private void anhxa() {
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.text_input_layout_username_new);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.text_input_layout_password_new);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.text_input_layout_email);
        buttonCreatAccount = (Button) findViewById(R.id.button_creat_account_new);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_creat_account_new:
                if(validateUserName() && validatePassword()){
                    String userName = textInputLayoutUserName.getEditText().getText().toString().trim();
                    String passWord = textInputLayoutPassword.getEditText().getText().toString().trim();
                    String email = textInputLayoutEmail.getEditText().getText().toString().trim();
                    user = new User();
                    user.setUserName(userName);
                    user.setPassword(passWord);
                    user.setEmail(email);
                    creatAccount(user);
                }
                break;
        }
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

    private void creatAccount(User user){
        DataService dataService = APIService.getDataService();
        Call<String> callBack = dataService.creatAccount(user.getUserName(), user.getPassword(), user.getEmail());
        callBack.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                if(result.equalsIgnoreCase("Success")){
                    Toast.makeText(CreatAccountActivity.this, "Creat account success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreatAccountActivity.this, LoginActivity.class );
                    startActivity(intent);
                    finish();
                }

                if(result.equalsIgnoreCase("UserExist")){
                    Toast.makeText(CreatAccountActivity.this, "Account Exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(CreatAccountActivity.this, "Creat account fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
