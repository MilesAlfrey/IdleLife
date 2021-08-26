package com.example.idlelife;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;


public class MiscMethods {

    public static int Will1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will1",0);

        return (int) (level*10+ Math.floor(Math.pow((float)level,2.5)));
    }

    public static int Will2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will2",0);

        return (int) (1000+level*100+ Math.floor(Math.pow((float)level,2.5)));
    }

    public static int Will3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will3",0);

        return (int) (10000+level*1000+ Math.floor(Math.pow((float)level,3)));
    }

    public static int Will4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Will4",0);

        return (int) (50000+level*2000+ Math.floor(Math.pow((float)level,3.5)));
    }




    public static int Int1Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int1",0);

        return (int) (100000+level*4000+ Math.floor(Math.pow((float)level,3.8)));
    }

    public static int Int2Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int2",0);

        return (int) (100+level*200+ Math.floor(Math.pow((float)level,4)));
    }

    public static int Int3Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int3",0);

        return (int) (50000+level*2000+ Math.floor(Math.pow(2.,(float)level))); //NEED CHANGING
    }

    public static int Int4Cost(Context context){


        SharedPreferences data = context.getSharedPreferences("Values",Context.MODE_PRIVATE);

        int level = data.getInt("Int4",0);

        return (int) (50000+level*2000+ Math.floor(Math.pow((float)level,3.5))); // Need Changing
    }




    public static long[] LevelCost(int age){


        //Need to work out how im going to do the costs here (hard code these)

        if (age == 0) {
            return new long[]{1000, 0, 0, 0};
        }
        else if (age==1){
            return new long[]{10000,0,0,0};
        }
        else{
            return new long[]{100000,0,0,0};
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
                break;
            case 6:
                editor.putBoolean("ShowInt3",true);
                break;
            case 7:
                break;
            case 8:
                editor.putBoolean("ShowInt4",true);
                break;
        }
        editor.apply();
    }

    public static void ButtonPressAction(Button buyButton, long cost , String name, String currency, FragmentActivity activity){

        SharedPreferences UpdateAmount = activity.getSharedPreferences("Values", Context.MODE_PRIVATE );


        long CurrentScore = UpdateAmount.getLong(currency,0);

        if (CurrentScore>=cost) {


            SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

            long resultScore = CurrentScore- cost;

            AmountEditor.putLong(currency,resultScore);

            int Current = UpdateAmount.getInt(name, 0);

            AmountEditor.putInt(name, Current + 1);

            AmountEditor.apply();

            if (resultScore<cost) {
                buyButton.setBackgroundColor(0xff555555);
            }


        }


    }


}
