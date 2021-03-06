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


//test


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
        if(score.getBoolean("ShowWill5",false)){
            binding.WillBuy5.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.WillDescrip5.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS



        double cost1 = MiscMethods.Will1Cost(requireContext());
        double cost2 = MiscMethods.Will2Cost(requireContext());
        double cost3 = MiscMethods.Will3Cost(requireContext());
        double cost4 = MiscMethods.Will4Cost(requireContext());
        double cost5 = MiscMethods.Will5Cost(requireContext());


        double will = MiscMethods.getDouble(score,"Will", 0);
        double intelligence = MiscMethods.getDouble(score,"Int",0);
        double social = MiscMethods.getDouble(score,"Social",0);
        double money = MiscMethods.getDouble(score,"Money",0);

        if (will >= cost1) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy1.setBackgroundColor(0xff555555);
        }

        if (intelligence >= cost2) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            //binding.WillBuy2.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            //binding.WillBuy2.setBackgroundColor(0xff555555);
        }

        if (will >= cost3) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.WillBuy3.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.WillBuy3.setBackgroundColor(0xff555555);
        }

        if (social >= cost4) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
            //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
            //binding.WillBuy4.setBackgroundColor(0xff555555);
        }

        if (money >= cost5) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.WillBuy5,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
            //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.WillBuy5,ColorStateList.valueOf(getResources().getColor(R.color.DarkMoney)));
            //binding.WillBuy4.setBackgroundColor(0xff555555);
        }





        double Will1MakeAmount = MiscMethods.Will1Gain(requireContext());
        int Will1Owned = score.getInt("Will1",0);
        binding.WillBuy1.setText(MiscMethods.FormatNumber(MiscMethods.Will1Cost(requireContext())));
        binding.WillDescrip1.setText(getString(R.string.DescriptionText,Will1Owned,MiscMethods.FormatNumber(Will1MakeAmount)));

        double Will2MakeAmount = MiscMethods.Will2Gain(requireContext());
        int Will2Owned = score.getInt("Will2",0);
        binding.WillBuy2.setText(MiscMethods.FormatNumber(MiscMethods.Will2Cost(requireContext())));
        binding.WillDescrip2.setText(getString(R.string.DescriptionText,Will2Owned,MiscMethods.FormatNumber(Will2MakeAmount)));

        double Will3MakeAmount = MiscMethods.Will3Gain(requireContext());
        int Will3Owned = score.getInt("Will3",0);
        binding.WillBuy3.setText(MiscMethods.FormatNumber(MiscMethods.Will3Cost(requireContext())));
        binding.WillDescrip3.setText(getString(R.string.DescriptionText,Will3Owned,MiscMethods.FormatNumber(Will3MakeAmount)));

        double Will4MakeAmount = MiscMethods.Will4Gain(requireContext());
        int Will4Owned = score.getInt("Will4",0);
        binding.WillBuy4.setText(MiscMethods.FormatNumber(MiscMethods.Will4Cost(requireContext())));
        binding.WillDescrip4.setText(getString(R.string.DescriptionText,Will4Owned,MiscMethods.FormatNumber(Will4MakeAmount)));

        double Will5MakeAmount = MiscMethods.Will5Gain(requireContext());
        int Will5Owned = score.getInt("Will5",0);
        binding.WillBuy5.setText(MiscMethods.FormatNumber(MiscMethods.Will5Cost(requireContext())));
        binding.WillDescrip5.setText(getString(R.string.DescriptionText,Will5Owned,MiscMethods.FormatNumber(Will5MakeAmount)));




        timerHandler.post(timerRunnable);

        binding.WillBuy1.setOnClickListener(view1 -> MiscMethods.ButtonPressAction(binding.WillBuy1,MiscMethods.Will1Cost(requireContext()),"Will1","Will",requireActivity()));

        binding.WillBuy2.setOnClickListener(view12 -> MiscMethods.ButtonPressAction(binding.WillBuy2,MiscMethods.Will2Cost(requireContext()),"Will2","Int",requireActivity()));

        binding.WillBuy3.setOnClickListener(view13 -> MiscMethods.ButtonPressAction(binding.WillBuy3,MiscMethods.Will3Cost(requireContext()),"Will3","Will",requireActivity()));

        binding.WillBuy4.setOnClickListener(view14 -> MiscMethods.ButtonPressAction(binding.WillBuy4,MiscMethods.Will4Cost(requireContext()),"Will4","Social",requireActivity()));

        binding.WillBuy5.setOnClickListener(view15 -> MiscMethods.ButtonPressAction(binding.WillBuy5,MiscMethods.Will5Cost(requireContext()),"Will5","Money",requireActivity()));


    }

    private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            double cost1 = MiscMethods.Will1Cost(requireContext());
            double cost2 = MiscMethods.Will2Cost(requireContext());
            double cost3 = MiscMethods.Will3Cost(requireContext());
            double cost4 = MiscMethods.Will4Cost(requireContext());
            double cost5 = MiscMethods.Will5Cost(requireContext());



            double Will1MakeAmount = MiscMethods.Will1Gain(requireContext());
            int Will1Owned = saves.getInt("Will1",0);
            binding.WillBuy1.setText(MiscMethods.FormatNumber(cost1));
            binding.WillDescrip1.setText(getString(R.string.DescriptionText,Will1Owned,MiscMethods.FormatNumber(Will1MakeAmount)));


            double Will2MakeAmount = MiscMethods.Will2Gain(requireContext());
            int Will2Owned = saves.getInt("Will2",0);
            binding.WillBuy2.setText(MiscMethods.FormatNumber(cost2));
            binding.WillDescrip2.setText(getString(R.string.DescriptionText,Will2Owned,MiscMethods.FormatNumber(Will2MakeAmount)));


            double Will3MakeAmount = MiscMethods.Will3Gain(requireContext());
            int Will3Owned = saves.getInt("Will3",0);
            binding.WillBuy3.setText(MiscMethods.FormatNumber(cost3));
            binding.WillDescrip3.setText(getString(R.string.DescriptionText,Will3Owned,MiscMethods.FormatNumber(Will3MakeAmount)));


            double Will4MakeAmount = MiscMethods.Will4Gain(requireContext());
            int Will4Owned = saves.getInt("Will4",0);
            binding.WillBuy4.setText(MiscMethods.FormatNumber(cost4));
            binding.WillDescrip4.setText(getString(R.string.DescriptionText,Will4Owned,MiscMethods.FormatNumber(Will4MakeAmount)));

            double Will5MakeAmount = MiscMethods.Will5Gain(requireContext());
            int Will5Owned = saves.getInt("Will5",0);
            binding.WillBuy5.setText(MiscMethods.FormatNumber(MiscMethods.Will5Cost(requireContext())));
            binding.WillDescrip5.setText(getString(R.string.DescriptionText,Will5Owned,MiscMethods.FormatNumber(Will5MakeAmount)));

            double will = MiscMethods.getDouble(saves,"Will",0);
            double intelligence = MiscMethods.getDouble(saves,"Int",0);
            double social = MiscMethods.getDouble(saves,"Social",0);
            double money = MiscMethods.getDouble(saves,"Money",0);


            if (will >= cost1) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy1.setBackgroundColor(0xff555555);
            }

            if (intelligence >= cost2) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
                //binding.WillBuy2.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
                //binding.WillBuy2.setBackgroundColor(0xff555555);
            }

            if (will >= cost3) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.WillBuy3.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.WillBuy3.setBackgroundColor(0xff555555);
            }

            if (social >= cost4) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
                //binding.WillBuy4.setBackgroundColor(0xff555555);
            }

            if (money >= cost5) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.WillBuy5,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
                //binding.WillBuy4.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.WillBuy5,ColorStateList.valueOf(getResources().getColor(R.color.DarkMoney)));
                //binding.WillBuy4.setBackgroundColor(0xff555555);
            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non will things.
            switch(age){
                case 2:
                    binding.WillBuy2.setVisibility(View.VISIBLE);
                    binding.WillDescrip2.setVisibility(View.VISIBLE);//On age 1 we show will2
                    break;
                case 5:
                    binding.WillBuy3.setVisibility(View.VISIBLE);
                    binding.WillDescrip3.setVisibility(View.VISIBLE);//On age 2 we show will3
                    break;
                    //What do on age 2... and so on
                case 11:
                    binding.WillBuy4.setVisibility(View.VISIBLE);
                    binding.WillDescrip4.setVisibility(View.VISIBLE);//On age 3 we show will4
                    break;
                case 15:
                    binding.WillBuy5.setVisibility(View.VISIBLE);
                    binding.WillDescrip5.setVisibility(View.VISIBLE);//On age 3 we show will4
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