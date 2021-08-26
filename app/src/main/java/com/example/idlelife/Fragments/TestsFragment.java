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


        //END OF VISIBILITY SETTINGS


        Pair<String, Long> result = new Pair<>("lol", (long) 4);//TODO: SET UP THE THING IN MISCMETHODS THAT STORES THESE.

        String currency = result.first;
        long cost = result.second;


        long owned = score.getLong("currency", 0);

        if (owned >= cost) {//Stops it going on forever.
            //Colour chagne if can buy it
        } else {
            //Colour change if cant buy it
        }

        timerHandler.post(timerRunnable);

        binding.IncreaseSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);


                long CurrentScore = UpdateAmount.getLong(currency,0);

                if (CurrentScore>=cost) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- cost;

                    AmountEditor.putLong(currency,resultScore);

                    int Current = UpdateAmount.getInt("TestSpeed", 0);

                    AmountEditor.putInt("TestSpeed", Current + 1);

                    AmountEditor.apply();//Rest should work
                    //TODO: PUT "BRAIN" IN APPLICATIoN

                    if (resultScore<cost) {
                        binding.IncreaseSpeed.setBackgroundColor(0xff555555);//Make mores specific for the increasde speed button.
                    }


                }


            }
        });

    }


    private final static int Update_Interval = 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int currentTest = saves.getInt("Test", 0);// 0 means no test current, and beyond that its numbered

            if (currentTest == 0) {
                // Do whatever is needed if there is no current test.
            } else {
                //Do what is needed when a test is actually present.
            }


            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non will things. Same way to unlock tests
            //WILL ALSO NEED TO HIDE THEM IN CASE OF COMPLETION

            int age = saves.getInt("Age", 0);

            switch (age) {
                case 1:
                    binding.Test1.setVisibility(View.VISIBLE);
                    binding.Test2.setVisibility(View.VISIBLE);//On age 2 we show will2
                    break;
                case 2:
                    binding.Test3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    binding.Test4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    binding.Test5.setVisibility(View.VISIBLE);
                    binding.Test6.setVisibility(View.VISIBLE);
            }

            timerHandler.postDelayed(timerRunnable, Update_Interval);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        //timerHandler.removeCallbacks(timerRunnable);
    }

}