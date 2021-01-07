package com.itvillage.afridigaming;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itvillage.afridigaming.dto.response.UserCreateProfileResponse;
import com.itvillage.afridigaming.services.GetUserService;
import com.itvillage.afridigaming.services.RegistrationInGameService;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JoinNowUserActivity extends AppCompatActivity {

    private String gameId, gameName, totalEntryFee, entryFeePerPerson, myBalance;
    private RadioGroup gameTypeGroup;
    private RadioButton gameTypeBut;
    private EditText playerId1EditText,playerId2EditText,playerId3EditText;
    private TextView myBalanceTextView,gameNameTextView,entryFeePerTotalMatchTextView,entryFeePerMatchTextView;
    private Button joinBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now_user);

        gameId = getIntent().getExtras().getString("gameId");
        gameName = getIntent().getExtras().getString("gameName");
        totalEntryFee = getIntent().getExtras().getString("totalEntryFee");
        entryFeePerPerson = getIntent().getExtras().getString("entryFeePerPerson");

        myBalanceTextView = findViewById(R.id.myBalanceTextView);
        gameNameTextView = findViewById(R.id.gameNameTextView);
        entryFeePerTotalMatchTextView = findViewById(R.id.entryFeePerTotalMatchTextView);
        entryFeePerMatchTextView = findViewById(R.id.entryFeePerMatchTextView);

        playerId1EditText = findViewById(R.id.playerId1EditText);
        playerId2EditText = findViewById(R.id.playerId2EditText);
        playerId3EditText = findViewById(R.id.playerId3EditText);

        //TODO : Fix Group Radio Button
        gameTypeGroup = (RadioGroup) findViewById(R.id.gameTypeGroup);
        gameTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=group.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton) findViewById(id);
                gameTypeBut =(RadioButton) findViewById(checkedId);


            }
        });

        joinBut = findViewById(R.id.joinBut);

        getUserProfileBalance();

        joinBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationInGame();
            }
        });
        Log.e("dsfcdhgjgg", gameId);
    }

    @SuppressLint("CheckResult")
    private void getUserProfileBalance() {


        GetUserService getUserService = new GetUserService(getApplicationContext());
        Observable<UserCreateProfileResponse> userCreateProfileResponseObservable =
                getUserService.getUserProfile();

        userCreateProfileResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserProfile -> {
                    myBalance = String.valueOf(getUserProfile.getAcBalance());
                    myBalanceTextView.setText("Available Balance : "+"00");
                    gameNameTextView.setText(gameName);
                    entryFeePerTotalMatchTextView.setText("Total Entry Fee : "+totalEntryFee);
                    entryFeePerMatchTextView.setText("Game Entry Fee Per Person: "+entryFeePerPerson);
                },err -> {

                });

    }

    @SuppressLint("CheckResult")
    private void registrationInGame() {

        gameTypeBut = findViewById(gameTypeGroup.getCheckedRadioButtonId());
        RegistrationInGameService getUserService = new RegistrationInGameService(getApplicationContext());
        Observable<Void> userCreateProfileResponseObservable =
                getUserService.registrationInGame(gameId," gameTypeBut.getText().toString()",playerId1EditText.getText().toString(),playerId2EditText.getText().toString(),playerId3EditText.getText().toString());

        userCreateProfileResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserProfile -> {

                }, throwable -> {
                    onLoginFailure(throwable);
                }, () -> {
                });

    }
    private void onLoginFailure(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;

            if (httpException.code() == 500 || httpException.code() == 403) {
                Toast.makeText(getApplicationContext(), "Insufficient Balance", Toast.LENGTH_LONG).show();

            }
            Log.e("Error", "" + throwable.getMessage());
        }
    }
}