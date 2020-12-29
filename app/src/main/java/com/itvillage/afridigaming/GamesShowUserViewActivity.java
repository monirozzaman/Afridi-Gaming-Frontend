package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.itvillage.afridigaming.adapter.GameListAdapter;

public class GamesShowUserViewActivity extends AppCompatActivity {
    String[] maintitle ={
            "All Weapons | Mobile Only | 3234","Title 2",
            "Title 3","Title 4",
            "Title 5",
    };

    String[] subtitle ={
            "Time: 29/29/2020 at 20:20 ","Sub Title 2",
            "Sub Title 3","Sub Title 4",
            "Sub Title 5",
    };

    Integer[] imgid={
            R.drawable.free_fire,R.drawable.free_fire,
            R.drawable.free_fire,R.drawable.free_fire,
            R.drawable.free_fire
    };

    private ListView game_list_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_show_user_view);
        GameListAdapter adapter=new GameListAdapter(this, maintitle, subtitle,imgid);
        game_list_show = findViewById(R.id.game_list_show);
        game_list_show.setAdapter(adapter);


    }
}