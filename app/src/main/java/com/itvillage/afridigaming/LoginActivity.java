package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.itvillage.afridigaming.dto.response.LoginResponse;
import com.itvillage.afridigaming.services.LoginService;
import com.itvillage.afridigaming.util.ApplicationSharedPreferencesUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private ApplicationSharedPreferencesUtil perfUtil;
    private TextView contact_us,sign_up,forget_password;
    private Button sign_in_but;
    private TextInputEditText emailEditText,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contact_us = findViewById(R.id.contact_us);
        sign_up = findViewById(R.id.sign_up);
        forget_password = findViewById(R.id.forget_password);
        sign_in_but = findViewById(R.id.sign_in_but);

        emailEditText = findViewById(R.id.emailEditText);
        password = findViewById(R.id.password);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
        sign_in_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              login(emailEditText.getText().toString(), password.getText().toString());

               /* if(emailEditText.getText().toString().equals("admin") && emailEditText.getText().toString().equals("admin"))
                {
                    Log.e(emailEditText.getText().toString()+"---------",emailEditText.getText().toString());
                    startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
                }else {
                    Log.e(emailEditText.getText().toString()+"----else-----",emailEditText.getText().toString());
                    startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
                }*/

            }
        });
        perfUtil = new ApplicationSharedPreferencesUtil(getApplicationContext());
    }


    private void login(String username, String password) {

        LoginService loginService = new LoginService(this);
        Observable<LoginResponse> responseObservable = loginService.login(username, password);


        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginIn -> {
                    onLoginSuccess(loginIn);

                }, throwable -> {

                }, () -> {

                });

    }

    private void onLoginSuccess(LoginResponse loginResponse) {


        perfUtil.saveAccessToken(loginResponse.getToken());

        /*JWT parsedJWT = new JWT(loginResponse.getToken());
        Claim subscriptionMetaData = parsedJWT.getClaim("scopes");
        String parsedValue = subscriptionMetaData.asString();
*/

        Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }
}