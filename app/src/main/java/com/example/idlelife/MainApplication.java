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

            long currentSocial = data.getLong("Social",0);

            long currentMoney = data.getLong("Money",0);


            int TestSpeed= data.getInt("TestSpeed",0);

            int TestProgress=data.getInt("TestProgress",0);

            /*
            boolean Test1 = data.getBoolean("Test1Completed",false);
            boolean Test2 = data.getBoolean("Test2Completed",false);
            boolean Test3 = data.getBoolean("Test3Completed",false);
            boolean Test4 = data.getBoolean("Test4Completed",false);
            boolean Test5 = data.getBoolean("Test5Completed",false);
            boolean Test6 = data.getBoolean("Test6Completed",false);

             */

/*
            long WillAdd = Will1 + 5 * Will2 + 20 * Will3 + 100 * Will4;

            if (Test1) {
                WillAdd*=4; //Can just add effects like this
            }

 */
            //WILL ADDITION

            long WillAdd = MiscMethods.Will1Gain(getBaseContext())+
                    MiscMethods.Will2Gain(getBaseContext())+
                    MiscMethods.Will3Gain(getBaseContext())+
                    MiscMethods.Will4Gain(getBaseContext());

            //WillAdd is amount per second so i will need to divide it by update time.

            WillAdd = WillAdd/(1000/Update_Interval);

            editor.putLong("Will", currentWill + WillAdd);

            //INT ADDITION

            long IntAdd = MiscMethods.Int1Gain(getBaseContext())+
                    MiscMethods.Int2Gain(getBaseContext())+
                    MiscMethods.Int3Gain(getBaseContext())+
                    MiscMethods.Int4Gain(getBaseContext());

            IntAdd = IntAdd/(1000/Update_Interval);

            editor.putLong("Int",currentInt+IntAdd);

            //SOCIAL ADDITION

            long SocialAdd = MiscMethods.Social1Gain(getBaseContext())+
                    MiscMethods.Social2Gain(getBaseContext())+
                    MiscMethods.Social3Gain(getBaseContext())+
                    MiscMethods.Social4Gain(getBaseContext());

            SocialAdd = SocialAdd/(1000/Update_Interval);

            editor.putLong("Social",currentSocial+SocialAdd);

            //TEST ADDITION

            editor.putInt("TestProgress",TestProgress+TestSpeed);//May want to change this to help adjust for smaller values

            editor.apply();



            timerHandler.postDelayed(this,Update_Interval); //Repeat this runnable in the given time.





        }
    };

}
