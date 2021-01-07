package com.itvillage.afridigaming.config;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.itvillage.afridigaming.R;

public class Utility {


    public static void wait(int time) {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                System.out.println("fdgdf");
                Log.e("Millisecond", "Pass");
            }
        }, time);
    }

}
