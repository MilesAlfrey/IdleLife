package com.example.idlelife.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.idlelife.databinding.FragmentMoneyBinding;


public class MoneyFragment extends Fragment  {



    private FragmentMoneyBinding binding;

     //To test with view model

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {



        binding = FragmentMoneyBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);


        SharedPreferences.Editor ButtonRemover = score.edit();
        ButtonRemover.putBoolean("ShowAgeUp", true);
        ButtonRemover.apply();

        //TODO: TO EDIT TO MAKE IT SPECIFIC TO Money.

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        //timerHandler.removeCallbacks(timerRunnable);
    }



}