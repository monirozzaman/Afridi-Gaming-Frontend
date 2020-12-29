package com.itvillage.afridigaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.itvillage.afridigaming.adapter.ApprovalListAdapter;
import com.itvillage.afridigaming.adapter.GameListAdapter;

public class MoneyApprovalActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_money_approval);

        ApprovalListAdapter adapter=new ApprovalListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.approval_list);
        list.setAdapter(adapter);
    }
}