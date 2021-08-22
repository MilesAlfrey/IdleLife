package com.example.idlelife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.idlelife.databinding.FragmentIntelligenceBinding;



public class IntelligenceFragment extends Fragment {

    private FragmentIntelligenceBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIntelligenceBinding.inflate(inflater, container, false);

        SharedPreferences saves = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);//Can get the sharedPreferences that are needed here


        return binding.getRoot();



    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences toReset = requireContext().getSharedPreferences("Values",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = toReset.edit();
                editor.putInt("Age",0);
                editor.putLong("Will",0);
                editor.putInt("Will1",0);
                editor.putInt("Will2",0);
                editor.putInt("Will3",0);
                editor.putInt("Will4",0);
                editor.putBoolean("ShowWill2",false);
                editor.putBoolean("ShowWill3",false);
                editor.putBoolean("ShowWill4",false);
                editor.apply();
            }

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        timerHandler.removeCallbacks(timerRunnable);
    }







/*
A runnable if I want it
*/
private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, Update_Interval); //Repeat if still grey
        }
    };


        @Override
    public void onDetach() {
        super.onDetach();
        timerHandler.removeCallbacks(timerRunnable);
    }


}