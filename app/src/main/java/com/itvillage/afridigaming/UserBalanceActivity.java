package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.itvillage.afridigaming.dto.response.LoginResponse;
import com.itvillage.afridigaming.dto.response.RegisterUsersInGameEntity;
import com.itvillage.afridigaming.dto.response.UserCreateProfileResponse;
import com.itvillage.afridigaming.services.GetUserService;
import com.itvillage.afridigaming.services.LoginService;
import com.itvillage.afridigaming.services.PostMoneyRequestService;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserBalanceActivity extends AppCompatActivity {

    TextView amount,amountD,winning_amount;
    Button add_money_help_but;
    CardView bkash_but,rocket_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_balance);

        amount = findViewById(R.id.amount);
        amountD = findViewById(R.id.amountD);
        winning_amount = findViewById(R.id.winning_amount);
        getUserProfile();

        add_money_help_but = findViewById(R.id.add_money_help_but);
        bkash_but = (CardView)findViewById(R.id.bkash_but);
        rocket_but = (CardView)findViewById(R.id.rocket_but);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_add_monet_help, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();


        add_money_help_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
        bkash_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyAddRequest("bkash");
            }
        });
        rocket_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyAddRequest("rocket");
            }
        });
    }

    private void moneyAddRequest(String by) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_from_for_money_request, viewGroup, false);
        EditText payment_amount = dialogView.findViewById(R.id.payment_amount);
        EditText payment_last_digit = dialogView.findViewById(R.id.payment_last_digit);
        EditText payment_method = dialogView.findViewById(R.id.payment_method);
        Button sent_request = dialogView.findViewById(R.id.sent_request);
        payment_method.setText(by);


        sent_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyRequest(by,payment_amount.getText().toString(),payment_last_digit.getText().toString());
                payment_amount.setText("");
                payment_last_digit.setText("");

            }
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressLint("CheckResult")
    private void moneyRequest(String transactionMethod, String loadBalAmount, String loadBalAmlastThreeDigitount) {

        PostMoneyRequestService postMoneyRequestService = new PostMoneyRequestService(this);

        Observable<Void> responseObservable = postMoneyRequestService.moneyRequest(transactionMethod,loadBalAmount,loadBalAmlastThreeDigitount);


        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {

                    onLoginSuccess();

                }, throwable -> {
                    onLoginFailure(throwable);
                }, () -> {

                });

    }
    private void onLoginFailure(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;

            if (httpException.code() == 500 || httpException.code() == 401) {
                Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();

            }
            Log.e("Error", "" + throwable.getMessage());
        }
    }

    private void onLoginSuccess() {

        Toast.makeText(getApplicationContext(), "Sent.", Toast.LENGTH_LONG).show();

    }

    @SuppressLint("CheckResult")
    private void getUserProfile() {

        GetUserService getUserService = new GetUserService(this.getApplicationContext());
        Observable<UserCreateProfileResponse> userCreateProfileResponseObservable =
                getUserService.getUserProfile();

        userCreateProfileResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserProfile -> {

                    amount.setText(String.valueOf(getUserProfile.getAcBalance()));
                    amountD.setText("Deposited :"+String.valueOf(getUserProfile.getAcBalance()));
                    winning_amount.setText("Winning :"+String.valueOf(getUserProfile.getAcBalance()));


                }, throwable -> {
                    throwable.printStackTrace();
                }, () -> {

                });

    }
}