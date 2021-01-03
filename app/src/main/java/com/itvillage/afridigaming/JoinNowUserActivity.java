package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.itvillage.afridigaming.dto.response.UserCreateProfileResponse;
import com.itvillage.afridigaming.services.GetUserService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JoinNowUserActivity extends AppCompatActivity {

    private String gameId,gameName,totalEntryFee,entryFeePerPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_now_user);

         gameId = getIntent().getExtras().getString("gameId");
         gameName = getIntent().getExtras().getString("gameName");
         totalEntryFee = getIntent().getExtras().getString("totalEntryFee");
         entryFeePerPerson = getIntent().getExtras().getString("entryFeePerPerson");
        getUserProfileBalance();
        Log.e("dsfcdhgjgg",gameId);
    }

    @SuppressLint("CheckResult")
    private void getUserProfileBalance() {

        GetUserService getUserService = new GetUserService(getApplicationContext());
        Observable<UserCreateProfileResponse> userCreateProfileResponseObservable =
                getUserService.getUserProfile();

        userCreateProfileResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getUserProfile -> {

//                    userProfileName.setText(getUserProfile.getFirstName());
//                    availableBalance.setText(Double.toString(getUserProfile.getAcBalance()));

                }, throwable -> {
                    throwable.printStackTrace();
                }, () -> {

                });

    }
}