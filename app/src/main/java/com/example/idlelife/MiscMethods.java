package com.example.idlelife;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;


import androidx.fragment.app.FragmentActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MiscMethods {

    public static double Will1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will1",0);

        return (int) (level*10+ Math.floor(Math.pow((float)level,2.5)));
    }

    public static double Will1Gain(Context context){

        //Returns the total amount of will per second that will1 will give.
        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);
        int will1Amount = data.getInt("Will1",0);
        boolean Test1 = data.getBoolean("Test1Completed",false);


        if (Test1){
            return 4*will1Amount*20;//Times 20 because 20 updates a second and each update gives 1;
        }
        else{
            return will1Amount*20;
        }
    }

    public static double Will2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will2",0);

        return (int) (1000+level*100+ Math.floor(Math.pow((float)level,2.5)));
    }

    public static double Will2Gain(Context context){

        //Returns the total amount of will per second that will1 will give.
        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);
        int will2Amount = data.getInt("Will2",0);
        boolean Test1 = data.getBoolean("Test1Completed",false);


        if (Test1){
            return 4*will2Amount*100;
        }
        else{
            return will2Amount*100;
        }
    }

    public static double Will3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will3",0);

        return (int) (10000+level*1000+ Math.floor(Math.pow((float)level,3)));
    }

    public static double Will3Gain(Context context){

        //Returns the total amount of will per second that will1 will give.
        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);
        int will1Amount = data.getInt("Will3",0);
        boolean Test1 = data.getBoolean("Test1Completed",false);


        if (Test1){
            return 4*will1Amount*400;
        }
        else{
            return will1Amount*400;
        }
    }

    public static double Will4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will4",0);

        return (int) (50000+level*2000+ Math.floor(Math.pow((float)level,3.5)));
    }

    public static double Will4Gain(Context context){

        //Returns the total amount of will per second that will1 will give.
        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);
        int will1Amount = data.getInt("Will4",0);
        boolean Test1 = data.getBoolean("Test1Completed",false);


        if (Test1){
            return 4*will1Amount*2000;//Times 20 because 20 updates a second and each update gives 1;
        }
        else{
            return will1Amount*2000;
        }
    }




    public static double Int1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int1",0);

        return (int) (100000+level*4000+ Math.floor(Math.pow((float)level,3.8)));
    }

    public static double Int1Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int1",0);

        return level;
    }

    public static double Int2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int2",0);

        return (int) (100+level*200+ Math.floor(Math.pow((float)level,4)));
    }

    public static double Int2Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int2",0);

        return level *10;
    }

    public static double Int3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int3",0);

        return (int) (50000+level*2000+ Math.floor(Math.pow(2.,(float)level))); //NEED CHANGING
    }
    public static double Int3Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int3",0);

        return  20*level;
    }

    public static double Int4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int4",0);

        return (int) (200000+level*10000+ Math.floor(Math.pow((float)level,3.5))); // Need Changing
    }

    public static double Int4Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int4",0);

        return level*50;
    }

    public static double Social1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social1",0);

        return (int) (1000000+level*6000+ Math.floor(Math.pow((float)level,4)));
    }

    public static double Social1Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social1",0);

        return level;
    }

    public static double Social2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social2",0);

        return (int) (1000+level*300+ Math.floor(Math.pow((float)level,4)));
    }

    public static double Social2Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social2",0);

        return level *14;
    }

    public static double Social3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social3",0);

        return (int) (100000+level*20000+ Math.floor(Math.pow(2.5,(float)level))); //NEED CHANGING
    }
    public static double Social3Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social3",0);

        return  20*level;
    }

    public static double Social4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social4",0);

        return (int) (30000+level*10000+ Math.floor(Math.pow((float)level,7))); // Need Changing
    }

    public static double Social4Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Social4",0);

        return level*50;
    }


    public static double Money1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money1",0);

        return (int) (10000+level*4000+ Math.floor(Math.pow(2,(float)level)));
    }

    public static double Money1Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money1",0);

        return level;
    }

    public static double Money2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money2",0);

        return (int) (1000000+level*300000+ Math.floor(Math.pow((float)level,20)));
    }

    public static double Money2Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money2",0);

        return level *12;
    }

    public static double Money3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money3",0);

        return (int) (100000+level*20000+ Math.floor(Math.pow(3,(float)level))); //NEED CHANGING
    }
    public static double Money3Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money3",0);

        return  30*level;
    }

    public static double Money4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money4",0);

        return (int) (30000+level*30000+ Math.floor(Math.pow((float)level,5))); // Need Changing
    }

    public static double Money4Gain(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Money4",0);

        return level*10;
    }






    public static double[] LevelCost(int age){


        //Need to work out how im going to do the costs here (hard code these)

        if (age == 0) {
            return new double[]{1000, 0, 0, 0};
        }
        else if (age==1){
            return new double[]{10000,0,0,0};
        }
        else{
            return new double[]{100000,0,0,0};
        }
    }






    public static void ageResult(Context context){
        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);
        int age = data.getInt("Age",0);
        SharedPreferences.Editor editor = data.edit();
        switch(age){
            case 1:
                editor.putBoolean("ShowWill2",true); //On age 2 we show will2
                break;
            case 2:
                editor.putBoolean("ShowWill3",true); //On age 2 we show will2
                break;
            case 3:
                editor.putBoolean("ShowWill4",true); //On age 2 we show will2
                editor.putBoolean("ShowInt1",true);
                break;
            case 4:

                editor.putBoolean("ShowInt2",true);
                break;
            case 5:
                editor.putBoolean("ShowSocial1",true);
                break;
            case 6:
                editor.putBoolean("ShowInt3",true);
                break;
            case 7:
                editor.putBoolean("ShowSocial2",true);
                break;
            case 8:
                editor.putBoolean("ShowInt4",true);
                editor.putBoolean("ShowMoney1",true);
                break;
            case 9:
                break;
            case 10:
                editor.putBoolean("ShowSocial3",true);
                break;
            case 11:
                editor.putBoolean("ShowSocial4",true);
                break;
            case 14:
                editor.putBoolean("ShowMoney2",true);
                break;
            case 17:
                editor.putBoolean("ShowMoney3",true);
                break;
            case 19:
                editor.putBoolean("ShowMoney4",true);
                break;
        }
        editor.apply();
    }

    public static void ButtonPressAction(Button buyButton, double cost , String name, String currency, FragmentActivity activity){

        SharedPreferences UpdateAmount = activity.getSharedPreferences("Values", Context.MODE_PRIVATE );


        double CurrentScore = getDouble(UpdateAmount,currency,0);

        if (CurrentScore>=cost) {


            SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

            double resultScore = CurrentScore- cost;

            putDouble(AmountEditor, currency,resultScore);

            int Current = UpdateAmount.getInt(name, 0);

            AmountEditor.putInt(name, Current + 1);

            AmountEditor.apply();


            /*
            switch (currency){
                case("Will"):
                    ViewCompat.setBackgroundTintList(buyButton, ColorStateList.valueOf(activity.getResources().getColor(R.color.DarkWill)));
                    break;
                case("Int"):
                    ViewCompat.setBackgroundTintList(buyButton, ColorStateList.valueOf(activity.getResources().getColor(R.color.DarkInt)));
                    break;
                case("Social"):
                    ViewCompat.setBackgroundTintList(buyButton, ColorStateList.valueOf(activity.getResources().getColor(R.color.DarkSocial)));
                    break;
                case("Money"):
                    ViewCompat.setBackgroundTintList(buyButton, ColorStateList.valueOf(activity.getResources().getColor(R.color.DarkMoney)));
                    break;
            }

             */


        }


    }

    public static String FormatNumber(int number){
        if (number < 100000){
            return String.valueOf(number);
        }
        else{
            NumberFormat numFormat = new DecimalFormat("0.00E0");
            return numFormat.format(number);
        }

    }

    public static String FormatNumber(double number){
        if (number < 100000){
            return String.valueOf( (long) Math.floor(number)); //Can convert to long as it is less than 100,000 thus getting rid of the .
        }
        else{
            NumberFormat numFormat = new DecimalFormat("0.00E0");
            return numFormat.format(number);
        }

    }

    public static SharedPreferences.Editor putDouble(final SharedPreferences.Editor edit, final String saveLocation, final double value){
        return edit.putLong(saveLocation, Double.doubleToRawLongBits(value));
    }

    public static double getDouble(final SharedPreferences preferences, final String saveLocation, final double defaultValue){
        return Double.longBitsToDouble(preferences.getLong(saveLocation, Double.doubleToLongBits(defaultValue)));
    }

}
