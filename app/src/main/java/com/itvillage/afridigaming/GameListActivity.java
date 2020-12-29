package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itvillage.afridigaming.adapter.GameListAdapter;

public class GameListActivity extends AppCompatActivity {
    ListView list;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        GameListAdapter adapter=new GameListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.game_list);
        list.setAdapter(adapter);


//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO Auto-generated method stub
//                if(position == 0) {
//                    //code specific to first list item
//                    Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//                else if(position == 1) {
//                    //code specific to 2nd list item
//                    Toast.makeText(getApplicationContext(),"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//                else if(position == 2) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
//                }
//                else if(position == 3) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
//                }
//                else if(position == 4) {
//
//                    Toast.makeText(getApplicationContext(),"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

    }
}