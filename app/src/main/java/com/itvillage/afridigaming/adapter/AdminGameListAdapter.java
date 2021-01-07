package com.itvillage.afridigaming.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.dto.response.RegisterUsersInGameEntity;
import com.itvillage.afridigaming.services.UpdateGameSatusService;
import com.itvillage.afridigaming.services.UpdateRoomDetailsService;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AdminGameListAdapter extends ArrayAdapter<String> {

    private Activity context;

    private ArrayList<String> gameIdArray;
    private ArrayList<String> gameNameArray;
    private ArrayList<String> gameSubNameArray;
    private ArrayList<Integer> imageArray;

    private ArrayList<String> gameTotalPrizeArray;
    private ArrayList<String> gamePerKillPrizeArray;
    private ArrayList<String> gameEntryFeeArray;
    private ArrayList<String> gameTypeArray;
    private ArrayList<String> gameVersionArray;
    private ArrayList<String> gameMapArray;

    private ArrayList<String> winnerPrizeArray;
    private ArrayList<String> secondPrizeArray;
    private ArrayList<String> thirdPrizeArray;

    private ArrayList<String> roomIdAndPassList;

    private ArrayList<Boolean> gameIsActiveList;

    private List<RegisterUsersInGameEntity> registerUsersInGameEntityArray;

    public AdminGameListAdapter(Activity context,
                                ArrayList<String> gameIdArray, ArrayList<String> gameNameArray,
                                ArrayList<String> gameSubNameArray, ArrayList<Integer> imageArray,
                                ArrayList<String> gameTotalPrizeArray, ArrayList<String> gamePerKillPrizeArray,
                                ArrayList<String> gameEntryFeeArray, ArrayList<String> gameTypeArray,
                                ArrayList<String> gameVersionArray, ArrayList<String> gameMapArray,
                                ArrayList<String> winnerPrizeArray, ArrayList<String> secondPrizeArray,
                                ArrayList<String> thirdPrizeArray, List<RegisterUsersInGameEntity> registerUsersInGameEntityArray,
                                ArrayList<Boolean> gameIsActiveList,ArrayList<String> roomIdAndPassList) {
        super(context, R.layout.custom_admin_game_list_items, gameIdArray);

        this.context = context;
        this.gameIdArray = gameIdArray;
        this.gameNameArray = gameNameArray;
        this.gameSubNameArray = gameSubNameArray;
        this.imageArray = imageArray;
        this.gameTotalPrizeArray = gameTotalPrizeArray;
        this.gamePerKillPrizeArray = gamePerKillPrizeArray;
        this.gameEntryFeeArray = gameEntryFeeArray;
        this.gameTypeArray = gameTypeArray;
        this.gameVersionArray = gameVersionArray;
        this.gameMapArray = gameMapArray;
        this.winnerPrizeArray = winnerPrizeArray;
        this.secondPrizeArray = secondPrizeArray;
        this.thirdPrizeArray = thirdPrizeArray;
        this.registerUsersInGameEntityArray = registerUsersInGameEntityArray;
        this.gameIsActiveList = gameIsActiveList;
        this.roomIdAndPassList = roomIdAndPassList;

    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_admin_game_list_items, null, true);

        int numberOfPlayer = registerUsersInGameEntityArray.size();
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        TextView totalPrizeText = (TextView) rowView.findViewById(R.id.totalPrizeText);
        TextView totalKillText = (TextView) rowView.findViewById(R.id.totalKillText);
        TextView totalFeeText = (TextView) rowView.findViewById(R.id.totalFeeText);
        TextView typeText = (TextView) rowView.findViewById(R.id.typeText);
        TextView versionText = (TextView) rowView.findViewById(R.id.versionText);
        TextView mapText = (TextView) rowView.findViewById(R.id.mapText);

        TextView gameStatus = (TextView) rowView.findViewById(R.id.gameStatus);
        TextView totalRegistration = (TextView) rowView.findViewById(R.id.totalRegistration);
        TextView roomIdANdPass = (TextView) rowView.findViewById(R.id.roomIdANdPass);

        Button prizeDetailsShowBut = rowView.findViewById(R.id.prizeDetailsShowBut);
        Button publishOrUn = rowView.findViewById(R.id.publishOrUn);
        Button addRoomDetails = rowView.findViewById(R.id.addRoomDetails);
        Button showAllPlayers = rowView.findViewById(R.id.showAllPlayers);


        titleText.setText(gameNameArray.get(position));
        imageView.setImageResource(imageArray.get(position));
        subtitleText.setText(gameSubNameArray.get(position));

        totalPrizeText.setText(gameTotalPrizeArray.get(position));
        totalKillText.setText(gamePerKillPrizeArray.get(position));
        totalFeeText.setText(gameEntryFeeArray.get(position));
        typeText.setText(gameTypeArray.get(position));
        versionText.setText(gameVersionArray.get(position));
        mapText.setText(gameMapArray.get(position));

        roomIdANdPass.setText(roomIdAndPassList.get(position));

        gameStatus.setText(String.valueOf(gameIsActiveList.get(position)));
        totalRegistration.setText("" + numberOfPlayer);

        prizeDetailsShowBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialog_for_total_prz_show, viewGroup, false);

                TextView winnerPrize = (TextView) dialogView.findViewById(R.id.winnerPrize);
                TextView runnerUp1Prize = (TextView) dialogView.findViewById(R.id.runnerUp1Prize);
                TextView runnerUp2Prize = (TextView) dialogView.findViewById(R.id.runnerUp2Prize);

                winnerPrize.setText("Winner - " + winnerPrizeArray.get(position) + " tk");
                runnerUp1Prize.setText("Runner Up 1 - " + secondPrizeArray.get(position) + " tk");
                runnerUp2Prize.setText("Runner Up 2 - " + thirdPrizeArray.get(position) + " tk");

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        showAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialog_for_list_show, viewGroup, false);

                ArrayList<String> playerName = new ArrayList<>();
                for (RegisterUsersInGameEntity registerUsersInGameEntity : registerUsersInGameEntityArray) {
                    playerName.add(registerUsersInGameEntity.getPartnerOneName() + "," + registerUsersInGameEntity.getPartnerTwoName() + "," + registerUsersInGameEntity.getPartnerThreeName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_list_item_1, playerName);
                ListView playerList = dialogView.findViewById(R.id.playerList);
                playerList.setAdapter(arrayAdapter);


                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        addRoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoomDetails(gameIdArray.get(position));
            }
        });

        publishOrUn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGameStatus(gameIdArray.get(position), "false");
            }
        });
        return rowView;

    }

    private void addRoomDetails(String gameId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ViewGroup viewGroup = context.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_form_add_room_details, viewGroup, false);

        EditText room_id = dialogView.findViewById(R.id.room_id);
        EditText room_password = dialogView.findViewById(R.id.room_password);
        Button update_room_but = dialogView.findViewById(R.id.update_room_but);


        update_room_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoomDetailsInApi(gameId, room_id.getText().toString(), room_password.getText().toString());
                room_id.setText("");
                room_password.setText("");

            }
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @SuppressLint("CheckResult")
    private void addRoomDetailsInApi(String gameId, String room_id, String room_password) {

        UpdateRoomDetailsService updateRoomDetailsService = new UpdateRoomDetailsService(context);

        Observable<Void> responseObservable = updateRoomDetailsService.updateRoomDetailsService(gameId, room_id, room_password);


        responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {


                    onLoginSuccess();

                }, throwable -> {
                    onLoginFailure(throwable);
                }, () -> {

                });

    }

    @SuppressLint("CheckResult")
    private void updateGameStatus(String gameId, String status) {

        UpdateGameSatusService updateGameSatusService = new UpdateGameSatusService(context);

        Observable<Void> responseObservable = updateGameSatusService.updateGameSatusService(gameId, status);


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
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_LONG).show();

            }
            Log.e("Error", "" + throwable.getMessage());
        }
    }

    private void onLoginSuccess() {

        Toast.makeText(context, "Updated.", Toast.LENGTH_LONG).show();

    }
}