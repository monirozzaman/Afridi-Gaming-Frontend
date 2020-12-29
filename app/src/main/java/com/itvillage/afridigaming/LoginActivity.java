package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

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
                if(emailEditText.getText().toString().equals("admin") && emailEditText.getText().toString().equals("admin"))
                {
                    Log.e(emailEditText.getText().toString()+"---------",emailEditText.getText().toString());
                    startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
                }else {
                    Log.e(emailEditText.getText().toString()+"----else-----",emailEditText.getText().toString());
                    startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
                }
            }
        });
    }
}