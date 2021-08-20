package com.example.idlelife;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        /*
        SharedPreferences data = getSharedPreferences("Values", Context.MODE_PRIVATE );

        SharedPreferences.Editor editor = data.edit();


        editor.apply();

        System.out.println(data.getLong("Data",5));
        */


        timerHandler.post(timerRunnable);

    }

    long startTime=0;

    private final static int Update_Interval= 50; // IMPORTANT HOW OFTEN APP UPDATES.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //Could put the "brain" here, where it checks and updates every value, updating it elsewhere independently.
            //Right now this does the maths and thats it.


            SharedPreferences data = getSharedPreferences("Values", Context.MODE_PRIVATE );

            SharedPreferences.Editor editor = data.edit();

            long currentScore = data.getLong("Will",0);

            int currentButtons = data.getInt("Will1",0);


            editor.putLong("Will", currentScore + currentButtons);

            editor.apply();



            timerHandler.postDelayed(this,Update_Interval); //Repeat this runnable in the given time.





        }
    };

}
