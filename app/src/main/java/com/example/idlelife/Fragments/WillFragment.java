package com.example.idlelife.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.idlelife.Dialog.IntroDialog;
import com.example.idlelife.MiscMethods;
import com.example.idlelife.databinding.FragmentWillBinding;

public class WillFragment extends Fragment  {



    private FragmentWillBinding binding;

     //To test with view model

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {



        binding = FragmentWillBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        //WHERE I WILL PUT ALL OF VISIBILITY SETTINGS

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);

        if(score.getBoolean("ShowWill2",false)){
            binding.WillBuy2.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.WillDescrip2.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowWill3",false)){
            binding.WillBuy3.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.WillDescrip3.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowWill4",false)){
            binding.WillBuy4.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.WillDescrip4.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS








        binding.WillBuy1.setText(String.valueOf(MiscMethods.Will1Cost(requireContext())));
        binding.WillDescrip1.setText(String.valueOf(score.getInt("Will1",0)));

        binding.WillBuy2.setText(String.valueOf(MiscMethods.Will2Cost(requireContext())));
        binding.WillDescrip2.setText(String.valueOf(score.getInt("Will2",0)));

        binding.WillBuy3.setText(String.valueOf(MiscMethods.Will3Cost(requireContext())));
        binding.WillDescrip3.setText(String.valueOf(score.getInt("Will3",0)));

        binding.WillBuy4.setText(String.valueOf(MiscMethods.Will4Cost(requireContext())));
        binding.WillDescrip4.setText(String.valueOf(score.getInt("Will4",0)));



        int cost = MiscMethods.Will1Cost(requireContext());
        long will = score.getLong("Will", 0);

        if (will >= cost) {//Stops it going on forever.
            binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
        }
        else {
            binding.WillBuy1.setBackgroundColor(0xff555555);
        }

        timerHandler.post(timerRunnable);

        binding.WillBuy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //IntroDialog test = new IntroDialog();
                //test.show(getParentFragmentManager(),"test");

                //TODO: USE DIALOG CORRECTLY

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                int cost = MiscMethods.Will1Cost(requireContext());

                long CurrentScore = UpdateAmount.getLong("Will",0);

                if (CurrentScore>=cost) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- cost;

                    AmountEditor.putLong("Will",resultScore);

                    int Current = UpdateAmount.getInt("Will1", 0);

                    AmountEditor.putInt("Will1", Current + 1);

                    AmountEditor.apply();

                    if (resultScore<cost) {
                        binding.WillBuy1.setBackgroundColor(0xff555555);
                    }


                    binding.WillBuy1.setText(String.valueOf(MiscMethods.Will1Cost(requireContext())));
                    binding.WillDescrip1.setText(String.valueOf(Current+1));
                }


            }
        });

        binding.WillBuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                int cost = MiscMethods.Will2Cost(requireContext());

                long CurrentScore = UpdateAmount.getLong("Will",0);

                if (CurrentScore>=cost) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- cost;

                    AmountEditor.putLong("Will",resultScore);

                    int Current = UpdateAmount.getInt("Will2", 0);

                    AmountEditor.putInt("Will2", Current + 1);

                    AmountEditor.apply();

                    if (resultScore<cost) {
                        binding.WillBuy2.setBackgroundColor(0xff555555);
                    }


                    binding.WillBuy2.setText(String.valueOf(MiscMethods.Will2Cost(requireContext())));
                    binding.WillDescrip2.setText(String.valueOf(Current+1));
                }


            }
        });

        binding.WillBuy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                int cost = MiscMethods.Will3Cost(requireContext());

                long CurrentScore = UpdateAmount.getLong("Will",0);

                if (CurrentScore>=cost) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- cost;

                    AmountEditor.putLong("Will",resultScore);

                    int Current = UpdateAmount.getInt("Will3", 0);

                    AmountEditor.putInt("Will3", Current + 1);

                    AmountEditor.apply();

                    if (resultScore<cost) {
                        binding.WillBuy3.setBackgroundColor(0xff555555);
                    }


                    binding.WillBuy3.setText(String.valueOf(MiscMethods.Will3Cost(requireContext())));
                    binding.WillDescrip3.setText(String.valueOf(Current+1));
                }


            }
        });

        binding.WillBuy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                int cost = MiscMethods.Will4Cost(requireContext());

                long CurrentScore = UpdateAmount.getLong("Will",0);

                if (CurrentScore>=cost) {


                    SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                    long resultScore = CurrentScore- cost;

                    AmountEditor.putLong("Will",resultScore);

                    int Current = UpdateAmount.getInt("Will4", 0);

                    AmountEditor.putInt("Will4", Current + 1);

                    AmountEditor.apply();

                    if (resultScore<cost) {
                        binding.WillBuy4.setBackgroundColor(0xff555555);
                    }


                    binding.WillBuy4.setText(String.valueOf(MiscMethods.Will4Cost(requireContext())));
                    binding.WillDescrip4.setText(String.valueOf(Current+1));
                }


            }
        });


    }

    private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int cost1 = MiscMethods.Will1Cost(requireContext());
            int cost2 = MiscMethods.Will2Cost(requireContext());
            int cost3 = MiscMethods.Will3Cost(requireContext());
            int cost4 = MiscMethods.Will4Cost(requireContext());
            long score = saves.getLong("Will", 0);

            if (score >= cost1) {//Stops it going on forever.
                binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                binding.WillBuy1.setBackgroundColor(0xff555555);
            }

            if (score >= cost2) {//Stops it going on forever.
                binding.WillBuy2.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                binding.WillBuy2.setBackgroundColor(0xff555555);
            }

            if (score >= cost3) {//Stops it going on forever.
                binding.WillBuy3.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                binding.WillBuy3.setBackgroundColor(0xff555555);
            }

            if (score >= cost4) {//Stops it going on forever.
                binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                binding.WillBuy4.setBackgroundColor(0xff555555);
            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non will things.
            switch(age){
                case 1:
                    binding.WillBuy2.setVisibility(View.VISIBLE);
                    binding.WillDescrip2.setVisibility(View.VISIBLE);//On age 1 we show will2
                    break;
                case 2:
                    binding.WillBuy3.setVisibility(View.VISIBLE);
                    binding.WillDescrip3.setVisibility(View.VISIBLE);//On age 2 we show will3
                    break;
                    //What do on age 2... and so on
                case 3:
                    binding.WillBuy4.setVisibility(View.VISIBLE);
                    binding.WillDescrip4.setVisibility(View.VISIBLE);//On age 3 we show will4
                    break;
            }

            timerHandler.postDelayed(timerRunnable,Update_Interval);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        timerHandler.removeCallbacks(timerRunnable);
    }



}