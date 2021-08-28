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

            long currentWill = data.getLong("Will",0);

            long currentInt = data.getLong("Int",0);

            int Will1 = data.getInt("Will1",0);

            int Will2 = data.getInt("Will2",0);

            int Will3 = data.getInt("Will3",0);

            int Will4 = data.getInt("Will4",0);

            int Int1 = data.getInt("Int1",0);

            int Int2 = data.getInt("Int2",0);

            int Int3 = data.getInt("Int3",0);

            int TestSpeed= data.getInt("TestSpeed",0);

            int TestProgress=data.getInt("TestProgress",0);


            editor.putLong("Will", currentWill + Will1+5*Will2 + 20*Will3+100*Will4);

            editor.putLong("Int",currentInt+Int1+10*Int2+20*Int3);

            editor.putInt("TestProgress",TestProgress+TestSpeed);//May want to change this to help adjust for smaller values

            editor.apply();



            timerHandler.postDelayed(this,Update_Interval); //Repeat this runnable in the given time.





        }
    };

}
