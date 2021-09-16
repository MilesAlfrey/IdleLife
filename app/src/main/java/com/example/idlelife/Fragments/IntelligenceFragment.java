package com.example.idlelife.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.example.idlelife.Dialog.IntroDialog;
import com.example.idlelife.MiscMethods;
import com.example.idlelife.R;
import com.example.idlelife.databinding.FragmentIntelligenceBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class IntelligenceFragment extends Fragment {

    private FragmentIntelligenceBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentIntelligenceBinding.inflate(inflater, container, false);

        //SharedPreferences saves = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);//Can get the sharedPreferences that are needed here


        return binding.getRoot();



    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        //WHERE I WILL PUT ALL OF VISIBILITY SETTINGS

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);


        SharedPreferences.Editor ButtonRemover = score.edit();
        ButtonRemover.putBoolean("ShowAgeUp",true);
        ButtonRemover.apply();

        if(score.getBoolean("ShowInt1",false)){
            binding.IntDescrip1.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.IntBuy1.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowInt2",false)){
            binding.IntDescrip2.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.IntBuy2.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowInt3",false)){
            binding.IntDescrip3.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.IntBuy3.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowInt4",false)){
            binding.IntDescrip4.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.IntBuy4.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS

        long cost1 = MiscMethods.Int1Cost(requireContext());
        long cost2 = MiscMethods.Int2Cost(requireContext());
        long cost3 = MiscMethods.Int3Cost(requireContext());
        long cost4 = MiscMethods.Int4Cost(requireContext());

        NumberFormat numFormat = new DecimalFormat("0.00E0");

        long Int1MakeAmount = MiscMethods.Int1Gain(requireContext());
        int Int1Owned = score.getInt("Int1",0);
        binding.IntBuy1.setText(String.valueOf(cost1));
        binding.IntDescrip1.setText(getString(R.string.DescriptionText,Int1Owned,numFormat.format(Int1MakeAmount)));

        long Int2MakeAmount = MiscMethods.Int2Gain(requireContext());
        int Int2Owned = score.getInt("Int2",0);
        binding.IntBuy2.setText(String.valueOf(cost2));
        binding.IntDescrip2.setText(getString(R.string.DescriptionText,Int2Owned,numFormat.format(Int2MakeAmount)));

        long Int3MakeAmount = MiscMethods.Int3Gain(requireContext());
        int Int3Owned = score.getInt("Int3",0);
        binding.IntBuy3.setText(String.valueOf(cost3));
        binding.IntDescrip3.setText(getString(R.string.DescriptionText,Int3Owned,numFormat.format(Int3MakeAmount)));

        long Int4MakeAmount = MiscMethods.Int4Gain(requireContext());
        int Int4Owned = score.getInt("Int4",0);
        binding.IntBuy4.setText(String.valueOf(cost4));
        binding.IntDescrip4.setText(getString(R.string.DescriptionText,Int4Owned,numFormat.format(Int4MakeAmount)));

        long will = score.getLong("Will",0);
        long intelligence = score.getLong("Int",0);

        if (will >= cost1) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.IntBuy1, ColorStateList.valueOf(getResources().getColor(R.color.WillColour))); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.IntBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
        }

        if (intelligence >= cost2) {
            ViewCompat.setBackgroundTintList(binding.IntBuy2,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.IntBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
        }

        if (intelligence >= cost3) {
            ViewCompat.setBackgroundTintList(binding.IntBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.IntBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
        }

        if (intelligence >= cost4) {
            ViewCompat.setBackgroundTintList(binding.IntBuy4,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.IntBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
        }


        binding.Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences toReset = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = toReset.edit();
                editor.putInt("Age", 0);

                editor.putLong("Will", 10000000);
                editor.putLong("Int", 0);

                editor.putInt("Will1", 0);
                editor.putInt("Will2", 0);
                editor.putInt("Will3", 0);
                editor.putInt("Will4", 0);

                editor.putInt("Int1", 0);
                editor.putInt("Int2", 0);
                editor.putInt("Int3", 0);
                editor.putInt("Int4", 0);

                editor.putBoolean("ShowWill2", false);
                editor.putBoolean("ShowWill3", false);
                editor.putBoolean("ShowWill4", false);

                editor.putBoolean("ShowInt1", false);
                editor.putBoolean("ShowInt2", false);
                editor.putBoolean("ShowInt3", false);
                editor.putBoolean("ShowInt4", false);

                editor.putInt("TestSpeed",0);
                editor.putInt("Test",0);
                editor.putInt("TestProgress",0);
                //editor.putInt("TestLength",10000); Shouldnt be needed

                editor.putBoolean("Test1Completed",false);
                editor.putBoolean("Test2Completed",false);
                editor.putBoolean("Test3Completed",false);
                editor.putBoolean("Test4Completed",false);
                editor.putBoolean("Test5Completed",false);
                editor.putBoolean("Test6Completed",false);

                editor.apply();
            }
        });



        binding.IntBuy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MiscMethods.ButtonPressAction(binding.IntBuy1,MiscMethods.Int1Cost(requireContext()),"Int1","Will",requireActivity());

                    /* Backup

                    SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                    int cost = MiscMethods.Int1Cost(requireContext());

                    long CurrentScore = UpdateAmount.getLong("Will",0);

                    if (CurrentScore>=cost) {


                        SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                        long resultScore = CurrentScore- cost;

                        AmountEditor.putLong("Will",resultScore);

                        int Current = UpdateAmount.getInt("Int1", 0);

                        AmountEditor.putInt("Int1", Current + 1);

                        AmountEditor.apply();

                        if (resultScore<cost) {
                            binding.IntBuy1.setBackgroundColor(0xff555555);
                        }


                        binding.IntBuy1.setText(String.valueOf(MiscMethods.Int1Cost(requireContext())));
                        binding.IntDescrip1.setText(String.valueOf(Current+1));
                    }

                     */
                }
            });

        binding.IntBuy2.setOnClickListener(view13 -> MiscMethods.ButtonPressAction(binding.IntBuy2,MiscMethods.Int2Cost(requireContext()),"Int2","Int",requireActivity()));

        binding.IntBuy3.setOnClickListener(view1 -> MiscMethods.ButtonPressAction(binding.IntBuy3,MiscMethods.Int3Cost(requireContext()),"Int3","Int",requireActivity()));

        binding.IntBuy4.setOnClickListener(view12 -> MiscMethods.ButtonPressAction(binding.IntBuy4,MiscMethods.Int4Cost(requireContext()),"Int4","Int",requireActivity()));

        timerHandler.post(timerRunnable);

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
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            long cost1 = MiscMethods.Int1Cost(requireContext());
            long cost2 = MiscMethods.Int2Cost(requireContext());
            long cost3 = MiscMethods.Int3Cost(requireContext());
            long cost4 = MiscMethods.Int4Cost(requireContext());
            long will = saves.getLong("Will", 0);
            long intelligence = saves.getLong("Int", 0);

            NumberFormat numFormat = new DecimalFormat("0.00E0");

            long Int1MakeAmount = MiscMethods.Int1Gain(requireContext());
            int Int1Owned = saves.getInt("Int1",0);
            binding.IntBuy1.setText(String.valueOf(cost1));
            binding.IntDescrip1.setText(getString(R.string.DescriptionText,Int1Owned,numFormat.format(Int1MakeAmount)));

            long Int2MakeAmount = MiscMethods.Int2Gain(requireContext());
            int Int2Owned = saves.getInt("Int2",0);
            binding.IntBuy2.setText(String.valueOf(cost2));
            binding.IntDescrip2.setText(getString(R.string.DescriptionText,Int2Owned,numFormat.format(Int2MakeAmount)));

            long Int3MakeAmount = MiscMethods.Int3Gain(requireContext());
            int Int3Owned = saves.getInt("Int3",0);
            binding.IntBuy3.setText(String.valueOf(cost3));
            binding.IntDescrip3.setText(getString(R.string.DescriptionText,Int3Owned,numFormat.format(Int3MakeAmount)));

            long Int4MakeAmount = MiscMethods.Int4Gain(requireContext());
            int Int4Owned = saves.getInt("Int4",0);
            binding.IntBuy4.setText(String.valueOf(cost4));
            binding.IntDescrip4.setText(getString(R.string.DescriptionText,Int4Owned,numFormat.format(Int4MakeAmount)));


            if (will >= cost1) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.IntBuy1, ColorStateList.valueOf(getResources().getColor(R.color.WillColour))); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.IntBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            }

            if (intelligence >= cost2) {
                ViewCompat.setBackgroundTintList(binding.IntBuy2,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.IntBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            }

            if (intelligence >= cost3) {
                ViewCompat.setBackgroundTintList(binding.IntBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.IntBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            }

            if (intelligence >= cost4) {
                ViewCompat.setBackgroundTintList(binding.IntBuy4,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.IntBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non int things.
            switch(age){
                case 3:
                    binding.IntBuy1.setVisibility(View.VISIBLE);
                    binding.IntDescrip1.setVisibility(View.VISIBLE);//On age 1 we show will2
                    break;
                case 4:
                    binding.IntBuy2.setVisibility(View.VISIBLE);
                    binding.IntDescrip2.setVisibility(View.VISIBLE);//On age 2 we show will3
                    break;
                //What do on age 2... and so on
                case 6:
                    binding.IntBuy3.setVisibility(View.VISIBLE);
                    binding.IntDescrip3.setVisibility(View.VISIBLE);//On age 3 we show will4
                    break;
                case 8:
                    binding.IntBuy4.setVisibility(View.VISIBLE);
                    binding.IntDescrip4.setVisibility(View.VISIBLE);//On age 3 we show will4
                    break;
            }

            timerHandler.postDelayed(timerRunnable,Update_Interval);
        }

    };


        @Override
    public void onDetach() {
        super.onDetach();
        timerHandler.removeCallbacks(timerRunnable);
    }


}