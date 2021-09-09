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


import com.example.idlelife.MiscMethods;
import com.example.idlelife.R;
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


        SharedPreferences.Editor ButtonRemover = score.edit();
        ButtonRemover.putBoolean("ShowAgeUp",true);
        ButtonRemover.apply();


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



        int cost1 = MiscMethods.Will1Cost(requireContext());
        int cost2 = MiscMethods.Will2Cost(requireContext());
        int cost3 = MiscMethods.Will3Cost(requireContext());
        int cost4 = MiscMethods.Will4Cost(requireContext());
        long willAmount = score.getLong("Will", 0);

        if (willAmount >= cost1) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy1.setBackgroundColor(0xff555555);
        }

        if (willAmount >= cost2) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy2.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy2.setBackgroundColor(0xff555555);
        }

        if (willAmount >= cost3) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy3.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy3.setBackgroundColor(0xff555555);
        }

        if (willAmount >= cost4) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy4.setBackgroundColor(0xff555555);
        }


        binding.WillBuy1.setText(String.valueOf(MiscMethods.Will1Cost(requireContext()))); //MAy not need as it is in run now.
        binding.WillDescrip1.setText(String.valueOf(score.getInt("Will1",0)));

        binding.WillBuy2.setText(String.valueOf(MiscMethods.Will2Cost(requireContext())));
        binding.WillDescrip2.setText(String.valueOf(score.getInt("Will2",0)));

        binding.WillBuy3.setText(String.valueOf(MiscMethods.Will3Cost(requireContext())));
        binding.WillDescrip3.setText(String.valueOf(score.getInt("Will3",0)));

        binding.WillBuy4.setText(String.valueOf(MiscMethods.Will4Cost(requireContext())));
        binding.WillDescrip4.setText(String.valueOf(score.getInt("Will4",0)));


        timerHandler.post(timerRunnable);

        binding.WillBuy1.setOnClickListener(view1 -> MiscMethods.ButtonPressAction(binding.WillBuy1,MiscMethods.Will1Cost(requireContext()),"Will1","Will",requireActivity()));

        binding.WillBuy2.setOnClickListener(view12 -> MiscMethods.ButtonPressAction(binding.WillBuy2,MiscMethods.Will2Cost(requireContext()),"Will2","Will",requireActivity()));

        binding.WillBuy3.setOnClickListener(view13 -> MiscMethods.ButtonPressAction(binding.WillBuy3,MiscMethods.Will3Cost(requireContext()),"Will3","Will",requireActivity()));

        binding.WillBuy4.setOnClickListener(view14 -> MiscMethods.ButtonPressAction(binding.WillBuy4,MiscMethods.Will4Cost(requireContext()),"Will4","Will",requireActivity()));


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

            binding.WillBuy1.setText(String.valueOf(cost1));
            binding.WillDescrip1.setText(String.valueOf(saves.getInt("Will1",0)));

            binding.WillBuy2.setText(String.valueOf(cost2));
            binding.WillDescrip2.setText(String.valueOf(saves.getInt("Will2",0)));

            binding.WillBuy3.setText(String.valueOf(cost3));
            binding.WillDescrip3.setText(String.valueOf(saves.getInt("Will3",0)));

            binding.WillBuy4.setText(String.valueOf(cost4));
            binding.WillDescrip4.setText(String.valueOf(saves.getInt("Will4",0)));


            if (score >= cost1) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy1.setBackgroundColor(0xff555555);
            }

            if (score >= cost2) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy2.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy2.setBackgroundColor(0xff555555);
            }

            if (score >= cost3) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy3.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy3.setBackgroundColor(0xff555555);
            }

            if (score >= cost4) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy4.setBackgroundColor(0xff555555);
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