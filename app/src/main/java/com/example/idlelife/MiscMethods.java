package com.example.idlelife;

import android.content.Context;
import android.content.SharedPreferences;




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
                break;
        }
        editor.apply();
    }


}
