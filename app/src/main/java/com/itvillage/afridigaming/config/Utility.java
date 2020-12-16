package com.itvillage.afridigaming.config;

import android.os.Handler;
import android.util.Log;

public class Utility {

    /*
    Time In Millisecond
    * */
    public static void wait(int time)
    {

        new Handler().postDelayed(new Runnable() {
            public void run() {
               System.out.println("fdgdf");
                Log.e("Millisecond","Pass");
            }
        }, time);
    }
}
