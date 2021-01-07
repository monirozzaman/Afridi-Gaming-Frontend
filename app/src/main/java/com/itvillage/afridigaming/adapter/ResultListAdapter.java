package com.itvillage.afridigaming.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itvillage.afridigaming.R;
import com.itvillage.afridigaming.dto.response.RegisterUsersInGameEntity;

import java.util.ArrayList;
import java.util.List;

public class ResultListAdapter extends ArrayAdapter<String> {

    private Activity context;

    private ArrayList<String> gameNameArray;
    private ArrayList<String> gameSubNameArray;
    private ArrayList<Integer> imageArray;



    private List<RegisterUsersInGameEntity> registerUsersInGameEntityArray;

    public ResultListAdapter(Activity context,
                                 ArrayList<String> gameNameArray,
                                ArrayList<String> gameSubNameArray, ArrayList<Integer> imageArray,List<RegisterUsersInGameEntity> registerUsersInGameEntityArray) {
        super(context, R.layout.custom_result_list_items, gameNameArray);

        this.context = context;
        this.gameNameArray = gameNameArray;
        this.gameSubNameArray = gameSubNameArray;
        this.imageArray = imageArray;
        this.registerUsersInGameEntityArray = registerUsersInGameEntityArray;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_result_list_items, null, true);

        int numberOfPlayer = registerUsersInGameEntityArray.size();
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);


        Button watch = rowView.findViewById(R.id.watch);
        Button totalPrz = rowView.findViewById(R.id.totalPrz1);


        titleText.setText(gameNameArray.get(position));
        imageView.setImageResource(imageArray.get(position));
        subtitleText.setText(gameSubNameArray.get(position));



        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = context.getResources().getString(R.string.youtubelink);
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
            }
        });

        totalPrz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialog_for_list_show, viewGroup, false);

                ArrayList<String> playerName = new ArrayList<>();
                for(RegisterUsersInGameEntity registerUsersInGameEntity : registerUsersInGameEntityArray)
                {
                    playerName.add(registerUsersInGameEntity.getPartnerOneName()+","+registerUsersInGameEntity.getPartnerTwoName()+","+registerUsersInGameEntity.getPartnerThreeName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(dialogView.getContext(),android.R.layout.simple_list_item_1, playerName);
                ListView playerList =  dialogView.findViewById(R.id.playerList);
                playerList.setAdapter(arrayAdapter);


                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return rowView;

    }

}