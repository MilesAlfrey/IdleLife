package com.example.idlelife.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import com.example.idlelife.MiscMethods;
import com.example.idlelife.R;
import com.example.idlelife.databinding.FragmentTestsBinding;

import java.util.Map;


public class TestsFragment extends Fragment {


    private FragmentTestsBinding binding;

    //To test with view model

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentTestsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences buttonRemove = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);
        SharedPreferences.Editor ButtonRemover = buttonRemove.edit();
        ButtonRemover.putBoolean("ShowAgeUp", false);
        ButtonRemover.apply();

        //TODO: TO EDIT TO MAKE IT SPECIFIC TO Tests remember this is unique one so funnnnn.


        //WHERE I WILL PUT ALL OF VISIBILITY SETTINGS

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);

        //GREYS OUT COMPLETED TESTS
        if (score.getBoolean("Test1Completed",false)){
            greyCompleted(1);
        }
        if (score.getBoolean("Test2Completed",false)){
            greyCompleted(2);
        }
        if (score.getBoolean("Test3Completed",false)){
            greyCompleted(3);
        }
        if (score.getBoolean("Test4Completed",false)){
            greyCompleted(4);
        }
        if (score.getBoolean("Test5Completed",false)){
            greyCompleted(5);
        }
        if (score.getBoolean("Test6Completed",false)){
            greyCompleted(6);
        }


        //END OF VISIBILITY SETTINGS

        int currentTest = score.getInt("Test",0);

        int TestSpeed = score.getInt("TestSpeed",0);

        Pair<String, Long> result = SpeedUpCost(currentTest,TestSpeed);

        String currency = result.first;
        long cost = result.second;

        binding.IncreaseSpeed.setText(String.valueOf(cost)); //TODO: IMPROVE THIS AND ADD COLOURING

        long owned = score.getLong(currency, 0);

        if (owned >= cost) {//Stops it going on forever.
            //Colour chagne if can buy it
        } else {
            //Colour change if cant buy it
        }

        int age = score.getInt("Age", 0);

        switch (age) {
            default:
                //Just makes sure everything runs.
            case 5:
                binding.Test5.setVisibility(View.VISIBLE); //May need to change the visibility of layouts to stop scrolling?
                binding.Test6.setVisibility(View.VISIBLE);
            case 4:
                //Nothing lol

            case 3:
                binding.Test4.setVisibility(View.VISIBLE);
                binding.SecondRow.setVisibility(View.VISIBLE);

            case 2:
                binding.Test3.setVisibility(View.VISIBLE);

            case 1:
                binding.FirstRow.setVisibility(View.VISIBLE);
                binding.Test1.setVisibility(View.VISIBLE);
                binding.Test2.setVisibility(View.VISIBLE);//On age 2 we show will2
            case 0:
                //None shows


        }


        binding.IncreaseSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

                int TestSpeed = UpdateAmount.getInt("TestSpeed",0);
                int currentTest = UpdateAmount.getInt("Test",0);

                Pair<String, Long> result = SpeedUpCost(currentTest,TestSpeed);

                long CurrentScore = UpdateAmount.getLong(result.first,0);

                if (CurrentScore>=result.second) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- result.second;

                    AmountEditor.putLong(result.first,resultScore);

                    int Current = UpdateAmount.getInt("TestSpeed", 0);

                    AmountEditor.putInt("TestSpeed", Current + 1);

                    AmountEditor.apply();//Rest should work



                    Pair<String, Long> result2 = SpeedUpCost(currentTest,Current+1); //TO GET THE NEW PRICE




                    binding.IncreaseSpeed.setText(String.valueOf(result2.second)); //TODO: IMPROVE THIS AND ADD COLOURING



                    //if (resultScore<cost) {
                    //    binding.IncreaseSpeed.setBackgroundColor(0xff555555);//Make mores specific for the increasde speed button.
                    //}


                }


            }
        });

        binding.TakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences Values = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

                int currentTest = Values.getInt("Test",0); //Gets the current test being performed

                if (currentTest!=0) {

                    int ran = (int) Math.floor(Math.random() * 100) + 1;//GIVES A RANDOM NUMBER BETWEEN 1 and 100

                    int total = Values.getInt("TestLength", 0); //NOTE HAVENT FORMALLY SET TEST LENGTHS YET
                    int current = Values.getInt("TestProgress", 0);
                    int percentage = (int) Math.floor(((float) current / (float) total)*100);//Gives a number between 0 and 100
                    SharedPreferences.Editor change = Values.edit();
                    if (percentage>=ran){//Will only succeed the percentage number of times
                        //On pass

                        String TestCompleted = "Test"+currentTest+"Completed";
                        System.out.println(TestCompleted);
                        change.putBoolean(TestCompleted,true);

                        change.apply();//TODO: ACTUALLY USE THESE COMPLETED TESTS and "Stop" the current test

                        greyCompleted(currentTest);
                    }
                    else{
                        System.out.println("LOOOSERRRR");
                        change.putInt("TestProgress",0);
                        change.putInt("TestSpeed",0);//RESET TO 0
                        change.apply();

                        Pair<String, Long> result2 = SpeedUpCost(currentTest,0);
                        binding.IncreaseSpeed.setText(String.valueOf(result2.second));//Makes sure the text is updated

                    }


                }





            }
        });

        binding.Test1.setOnClickListener(view16 -> changeTest(1));

        binding.Test2.setOnClickListener(view15 -> changeTest(2));

        binding.Test3.setOnClickListener(view14 -> changeTest(3));

        binding.Test4.setOnClickListener(view13 -> changeTest(4));

        binding.Test5.setOnClickListener(view12 -> changeTest(5));

        binding.Test6.setOnClickListener(view1 -> changeTest(6));
        //Sets all of the buttons to run the appropriate change test

        timerHandler.post(timerRunnable);
    }


    private final static int Update_Interval = 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);






            //FOR THE CURRENT TEST, SPEEDING UP AND SETTING LENGTH OF THE BAR


            int currentTest = saves.getInt("Test", 0);// 0 means no test current, and beyond that its numbered

            binding.NameAndBuffText.setText(String.valueOf(currentTest));

            if (currentTest == 0) {
                // Do whatever is needed if there is no current test.
            } else {

                int TestLength  = saves.getInt("TestLength",0);


                int TestProgress = saves.getInt("TestProgress",0);

                //System.out.println(TestProgress);

                if (TestProgress>=TestLength){//TODO: DO THE SET THING ON START SO IT DOESNT JUMP SO WHEN ENTERING TAB
                    //I guess do nothing?

                    binding.FullBar.getLayoutParams().width= binding.ProgressBarToFill.getLayoutParams().width;
                    binding.FullBar.requestLayout(); //Just set bar to full

                    binding.PercentageFill.setText(getString(R.string.Percentage,100)); //And percentage to 100

                }
                else{
                    int MaxWidth = binding.ProgressBarToFill.getLayoutParams().width; //Can sometimes crash ):
                    float divide = (float) TestProgress / (float) TestLength;


                    binding.FullBar.getLayoutParams().width= (int) Math.floor(divide * (float) MaxWidth);
                    binding.FullBar.requestLayout(); // Simple division to set a fraction (CHECK MORE ON MAXWIDTH)

                    //System.out.println((int) Math.floor(divide*100));

                    binding.PercentageFill.setText(getString(R.string.Percentage,(int) Math.floor(divide*100)));
                }
                //Do what is needed when a test is actually present.
            }



            //SETS WHAT IS VISIBLE IN TERMS OF AVAILABLE TESTS


            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non will things. Same way to unlock tests
            //WILL ALSO NEED TO HIDE THEM IN CASE OF COMPLETION









            //RESTARTS THE RUNNER

            timerHandler.postDelayed(timerRunnable, Update_Interval);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void greyCompleted(int completedTest){
        switch(completedTest) {
            case (1):
                binding.Test1.setBackgroundColor(0x44EEEEEE);
                binding.Test1.setEnabled(false);
                break;
            case (2):
                binding.Test2.setBackgroundColor(0x44EEEEEE);
                binding.Test2.setEnabled(false);
                break;
            case (3):
                binding.Test3.setBackgroundColor(0x44EEEEEE);
                binding.Test3.setEnabled(false);
                break;
            case (4):
                binding.Test4.setBackgroundColor(0x44EEEEEE);
                binding.Test4.setEnabled(false);
                break;
            case (5):
                binding.Test5.setBackgroundColor(0x44EEEEEE);
                binding.Test5.setEnabled(false);
                break;
            case (6):
                binding.Test6.setBackgroundColor(0x44EEEEEE);
                binding.Test6.setEnabled(false);
                break;
        }
    }

    private void changeTest(int newTest){
        SharedPreferences Values = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Values.edit();
        editor.putInt("TestProgress",0);
        editor.putInt("Test",newTest);
        editor.putInt("TestSpeed",0);
        editor.putInt("TestLength",getTestLength(newTest));//TODO: ADD DIALOG OR SOMETHING IDK
        binding.IncreaseSpeed.setText(String.valueOf(SpeedUpCost(newTest,0).second));
        editor.apply();
    }

    private static int getTestLength(int TestNum){
        switch (TestNum) {
            case 1:
                return 10000;
            case 2:
                return 50000;
            case 3:
                return 10001;
            case 4:
                return 50002;
            case 5:
                return 10005;
            case 6:
                return 50005;
        }
        return 0;

    }

    public static Pair<String,Long> SpeedUpCost(int Test, int amountBought){

        long cost = 0;
        switch (Test){
            case 1:
                cost = 10000+5000*amountBought+amountBought*amountBought*1000;
                return new Pair<>("Will",cost);
            case 2:
                cost = 1000+5000*amountBought+amountBought*amountBought*1000;
                return new Pair<>("Int",cost);
            case 3:
                cost = 1;
                return  new Pair<>("Will",cost);
            case 4:
                cost = 2;
                return  new Pair<>("Will",cost);
            case 5:
                cost = 13;
                return  new Pair<>("Will",cost);
            case 6:
                cost = 4;
                return  new Pair<>("Will",cost);
            //TODO: ADD A FORMULA FOR EVERY TEST
        }
        return new Pair<>("Will", (long) 0);
        //Create individual costs for each test, similar to cost of bars.

        //First will have a will cost for now

    }

}