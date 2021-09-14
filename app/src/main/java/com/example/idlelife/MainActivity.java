package com.example.idlelife;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Html;
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

            if (score.getBoolean("ShowAgeUp",true)){
                binding.AgeUp.setVisibility(View.VISIBLE);
            }
            else{
                binding.AgeUp.setVisibility(View.GONE);
            }






            //binding.imageView2.setColorFilter(new );

            long[] levelCost = MiscMethods.LevelCost(score.getInt("Age", 0));

            if (levelCost[1] == 0){
                binding.AgeUp.setText(Html.fromHtml("Age up? Cost: <br><font color='red'>" + levelCost[0]  + "</font>"));
            }
            else if (levelCost[2]==0){
                binding.AgeUp.setText(Html.fromHtml("Age up? Cost: <br><font color='red'>" + levelCost[0]  + "</font>"+
                        ", <font color='blue'>" + levelCost[1]  + "</font>"));
            }
            else if (levelCost[3]==0){
                binding.AgeUp.setText(Html.fromHtml("Age up? Cost: <br><font color='red'>" + levelCost[0]  + "</font>"+
                        ", <font color='blue'>" + levelCost[1]  + "</font>" +
                        ", <font color='yellow'>" + levelCost[2]  + "</font>"));
            }
            else {
                binding.AgeUp.setText(Html.fromHtml("Age up? Cost: <br><font color='red'>" + levelCost[0] + "</font>" +
                        ", <font color='blue'>" + levelCost[1] + "</font>" +
                        ", <font color='yellow'>" + levelCost[2] + "</font>" +
                        ", <font color='green'>" + levelCost[3] + "</font>"));
            }

            binding.Will.setText(numFormat.format(score.getLong("Will", 0)));

            binding.Intelligence.setText(numFormat.format(score.getLong("Int", 0)));


            int currentTest = score.getInt("Test",0);

            if (currentTest != 0){
                int TestLength = score.getInt("TestLength", 0);
                int TestProgress = score.getInt("TestProgress", 0);

                // Simple division to set a fraction
                if (TestProgress >= TestLength) {
                    binding.BlockColor.getLayoutParams().width = binding.Outlines.getLayoutParams().width;

                } else {
                    int MaxWidth = binding.Outlines.getLayoutParams().width;


                    float divide = (float) TestProgress / (float) TestLength;



                    binding.BlockColor.getLayoutParams().width = (int) Math.floor(divide * (float) MaxWidth);

                }
                binding.BlockColor.requestLayout(); //Either way the new width is updated
            }
            else{
                binding.BlockColor.getLayoutParams().width = 1;
                binding.BlockColor.requestLayout(); // Sets it back to empty if there is no test, resetting it.
            }


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

        SharedPreferences saves = getSharedPreferences("Values",Context.MODE_PRIVATE);

        int age = saves.getInt("Age",0);

        PlayerIconSelector(age);


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
                    PlayerIconSelector(age+1);//The age +1 is a new age

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

    private void PlayerIconSelector(int age){
        if (0<=age && age<=2){
            binding.playericon.setImageResource(R.drawable.babyplayersmaller4x);
        }
        else if (3<=age && age<=12){
            binding.playericon.setImageResource(R.drawable.newchildplayericon4xtrim);
        }
        else if (13<=age && age<=18){
            binding.playericon.setImageResource(R.drawable.teenplayericonx4trim);
        }
        else if (19<=age && age<=60){
            binding.playericon.setImageResource(R.drawable.adultplayericon4xtrim);
        }
        else if (61<=age){
            binding.playericon.setImageResource(R.drawable.oldplayericon4xtrim);
        }
    }

}