package com.itvillage.afridigaming.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.config.Utility;
import com.itvillage.afridigaming.services.UpdateGameResultService;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdminGameResultAddAdapter extends ArrayAdapter<String> {

    private Activity context;

    private String gameId;
    private ArrayList<String> userIdList;
    private ArrayList<String> userNameOfGame;
    private String squadPrize = null;


    public AdminGameResultAddAdapter(Activity context,
                                     ArrayList<String> userId, String gameId, ArrayList<String> userNameOfGame) {
        super(context, R.layout.custom_add_game_result_list_items, userId);

        this.context = context;
        this.userIdList = userId;
        this.gameId = gameId;
        this.userNameOfGame = userNameOfGame;

    }


    public View getView(int position, View view, ViewGroup parent) {
        if(userNameOfGame.isEmpty()) {
           Toast.makeText(context,"Player Not Found",Toast.LENGTH_SHORT).show();
           return null;
        }else {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.custom_add_game_result_list_items, null, true);

            TextView userNameOfGames = rowView.findViewById(R.id.userNameOfGames);
            EditText numberOfKill = rowView.findViewById(R.id.totalPrizeText);

            Button save = rowView.findViewById(R.id.saveResult);

            RadioGroup rg = (RadioGroup) rowView.findViewById(R.id.gameTypeGroup);

            userNameOfGames.setText(userNameOfGame.get(position));

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    squadPrize = ((RadioButton) rowView.findViewById(checkedId)).getText().toString();
                    Toast.makeText(context, squadPrize, Toast.LENGTH_SHORT).show();
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateGameResult(gameId, userIdList.get(position), squadPrize, numberOfKill.getText().toString());
                }
            });
            return rowView;
        }

    }


    @SuppressLint("CheckResult")
    private void updateGameResult(String gameId, String userId, String squadPrize, String numberOfKill) {

        UpdateGameResultService updateGameResultService = new UpdateGameResultService(context);

        Observable<String> responseObservable = updateGameResultService.updateGameResultService(gameId, userId, squadPrize, numberOfKill);


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
                Utility.onErrorAlert("Something Wrong",context);

            }
            Log.e("Error", "" + throwable.getMessage());
        }
    }

    private void onLoginSuccess() {

        Utility.onSuccessAlert("Update Successful",context);

    }
}