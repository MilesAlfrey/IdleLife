package com.example.idlelife;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.idlelife.Dialog.IntroDialog;
import com.example.idlelife.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    private final static int Update_Interval= 50; // IMPORTANT HOW OFTEN APP UPDATES.

    Handler timerHandler = new Handler();
    final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {//Updates score Visually

            SharedPreferences score = getSharedPreferences("Values", Context.MODE_PRIVATE);

            NumberFormat numFormat = new DecimalFormat("0.###E0"); //Makes it into the right notation

            binding.Will.setText(numFormat.format(score.getLong("Will", 0)));

            binding.Intelligence.setText(numFormat.format(score.getLong("Int", 0)));

            binding.AgeUp.setText(String.valueOf((int) MiscMethods.LevelCost(score.getInt("Age", 0))[0]));

            timerHandler.postDelayed(this, Update_Interval); //Repeat this runnable in the given time.
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) { //onCreate means when the activity is created aka when the app is started.
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        // Starts timer when the activity is created



        timerHandler.post(timerRunnable);


        //NAVIGATION

        binding.NavWill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragMgr = getSupportFragmentManager();

                NavHostFragment.findNavController(fragMgr.getFragments().get(0))//Uses the fragmentManager to get a fragment in use
                        .navigate(R.id.action_global_WillFragment); //Then uses that as a base to navigate to desired fragment from.

            }
        });

        binding.NavIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragMgr = getSupportFragmentManager();

                NavHostFragment.findNavController(fragMgr.getFragments().get(0))
                        .navigate(R.id.action_global_IntelligenceFragment);

            }
        });

        binding.NavSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragMgr = getSupportFragmentManager();

                NavHostFragment.findNavController(fragMgr.getFragments().get(0))
                        .navigate(R.id.action_global_socialFragment);

            }
        });

        binding.NavMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragMgr = getSupportFragmentManager();

                NavHostFragment.findNavController(fragMgr.getFragments().get(0))
                        .navigate(R.id.action_global_moneyFragment);

            }
        });

        binding.NavTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragMgr = getSupportFragmentManager();

                NavHostFragment.findNavController(fragMgr.getFragments().get(0))
                        .navigate(R.id.action_global_testsFragment);

            }
        });


        //LEVEL UP


        binding.AgeUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                SharedPreferences level = getSharedPreferences("Values",Context.MODE_PRIVATE);

                int age = level.getInt("Age",0);

                long[] cost = MiscMethods.LevelCost(age);


                long will = level.getLong("Will",0);
                long intelligence = level.getLong("Int",0);
                long social = level.getLong("Social",0);
                long money = level.getLong("Money",0);

                if (will>=cost[0]&&intelligence>=cost[1]&&social>=cost[2]&&money>=cost[3]) {
                    //Still need colour changing ugh
                    System.out.println(age);
                    SharedPreferences.Editor editor = level.edit();
                    editor.putLong("Will",will-cost[0]);
                    editor.putLong("Int",intelligence-cost[1]);
                    editor.putLong("Social",social-cost[2]);
                    editor.putLong("Money",money-cost[3]);
                    editor.putInt("Age",age+1);
                    editor.apply();
                    MiscMethods.ageResult(MainActivity.super.getBaseContext());

                }


                //TODO: USE DIALOG CORRECTLY

                //IntroDialog test = new IntroDialog();
                //test.show(getSupportFragmentManager(),"test");



            }
        });
    }



    @Override
    public void onStart() {


        super.onStart();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



}