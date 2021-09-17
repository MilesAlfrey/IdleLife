package com.example.idlelife;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        timerHandler.post(timerRunnable);

    }

    private final static int Update_Interval= 50; // IMPORTANT HOW OFTEN APP UPDATES.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //Could put the "brain" here, where it checks and updates every value, updating it elsewhere independently.
            //Right now this does the maths and that's it.


            SharedPreferences data = getSharedPreferences("Values", Context.MODE_PRIVATE );

            SharedPreferences.Editor editor = data.edit();

            double currentWill = MiscMethods.getDouble(data,"Will",0);

            double currentInt = MiscMethods.getDouble(data,"Int",0);

            double currentSocial = MiscMethods.getDouble(data,"Social",0);

            double currentMoney = MiscMethods.getDouble(data,"Money",0);


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

            //WILL ADDITION

            double WillAdd = MiscMethods.Will1Gain(getBaseContext())+
                    MiscMethods.Will2Gain(getBaseContext())+
                    MiscMethods.Will3Gain(getBaseContext())+
                    MiscMethods.Will4Gain(getBaseContext());

            //WillAdd is amount per second so i will need to divide it by update time.

            WillAdd = WillAdd/(1000./Update_Interval);

            MiscMethods.putDouble(editor,"Will", currentWill + WillAdd);

            //INT ADDITION

            double IntAdd = MiscMethods.Int1Gain(getBaseContext())+
                    MiscMethods.Int2Gain(getBaseContext())+
                    MiscMethods.Int3Gain(getBaseContext())+
                    MiscMethods.Int4Gain(getBaseContext());

            IntAdd = IntAdd/(1000./Update_Interval);

            MiscMethods.putDouble(editor,"Int",currentInt+IntAdd);

            //SOCIAL ADDITION

            double SocialAdd = MiscMethods.Social1Gain(getBaseContext())+
                    MiscMethods.Social2Gain(getBaseContext())+
                    MiscMethods.Social3Gain(getBaseContext())+
                    MiscMethods.Social4Gain(getBaseContext());


            SocialAdd = SocialAdd/(1000./Update_Interval);

            MiscMethods.putDouble(editor,"Social",currentSocial+SocialAdd);

            //MONEY ADDITION

            double MoneyAdd = MiscMethods.Money1Gain(getBaseContext())+
                    MiscMethods.Money2Gain(getBaseContext())+
                    MiscMethods.Money3Gain(getBaseContext())+
                    MiscMethods.Money4Gain(getBaseContext());


            MoneyAdd = MoneyAdd/(1000./Update_Interval);

            MiscMethods.putDouble(editor,"Money",currentMoney+MoneyAdd);



            //TEST ADDITION

            editor.putInt("TestProgress",TestProgress+TestSpeed);//May want to change this to help adjust for smaller values

            editor.apply();



            timerHandler.postDelayed(this,Update_Interval); //Repeat this runnable in the given time.





        }
    };

}
