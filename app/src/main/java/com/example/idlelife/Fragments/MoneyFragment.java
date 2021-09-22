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



        if(score.getBoolean("ShowMoney1",false)){
            binding.MoneyBuy1.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.MoneyDescrip1.setVisibility(View.VISIBLE);
        }

        if(score.getBoolean("ShowMoney2",false)){
            binding.MoneyBuy2.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.MoneyDescrip2.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowMoney3",false)){
            binding.MoneyBuy3.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.MoneyDescrip3.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowMoney4",false)){
            binding.MoneyBuy4.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.MoneyDescrip4.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowMoney5",false)){
            binding.MoneyBuy5.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.MoneyDescrip5.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS



        double cost1 = MiscMethods.Money1Cost(requireContext());
        double cost2 = MiscMethods.Money2Cost(requireContext());
        double cost3 = MiscMethods.Money3Cost(requireContext());
        double cost4 = MiscMethods.Money4Cost(requireContext());
        double cost5 = MiscMethods.Money5Cost(requireContext());

        double will = MiscMethods.getDouble(score,"Will",0);
        double intelligence = MiscMethods.getDouble(score,"Int",0);
        double social = MiscMethods.getDouble(score,"Social",0);
        double money = MiscMethods.getDouble(score,"Money",0);

        if (social >= cost1) {
            ViewCompat.setBackgroundTintList(binding.MoneyBuy1, ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.MoneyBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
        }

        if (will >= cost2) {
            ViewCompat.setBackgroundTintList(binding.MoneyBuy2,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.MoneyBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
        }

        if (intelligence >= cost3) {
            ViewCompat.setBackgroundTintList(binding.MoneyBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.MoneyBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
        }

        if (money >= cost4) {
            ViewCompat.setBackgroundTintList(binding.MoneyBuy4,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.MoneyBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkMoney)));
        }
        if (social >= cost4) {
            ViewCompat.setBackgroundTintList(binding.MoneyBuy5,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
        }
        else{
            ViewCompat.setBackgroundTintList(binding.MoneyBuy5,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
        }





        double Money1MakeAmount = MiscMethods.Money1Gain(requireContext());
        int Money1Owned = score.getInt("Money1",0);
        binding.MoneyBuy1.setText(MiscMethods.FormatNumber(MiscMethods.Money1Cost(requireContext())));
        binding.MoneyDescrip1.setText(getString(R.string.DescriptionText,Money1Owned,MiscMethods.FormatNumber(Money1MakeAmount)));

        double Money2MakeAmount = MiscMethods.Money2Gain(requireContext());
        int Money2Owned = score.getInt("Money2",0);
        binding.MoneyBuy2.setText(MiscMethods.FormatNumber(MiscMethods.Money2Cost(requireContext())));
        binding.MoneyDescrip2.setText(getString(R.string.DescriptionText,Money2Owned,MiscMethods.FormatNumber(Money2MakeAmount)));

        double Money3MakeAmount = MiscMethods.Money3Gain(requireContext());
        int Money3Owned = score.getInt("Money3",0);
        binding.MoneyBuy3.setText(MiscMethods.FormatNumber(MiscMethods.Money3Cost(requireContext())));
        binding.MoneyDescrip3.setText(getString(R.string.DescriptionText,Money3Owned,MiscMethods.FormatNumber(Money3MakeAmount)));

        double Money4MakeAmount = MiscMethods.Money4Gain(requireContext());
        int Money4Owned = score.getInt("Money4",0);
        binding.MoneyBuy4.setText(MiscMethods.FormatNumber(MiscMethods.Money4Cost(requireContext())));
        binding.MoneyDescrip4.setText(getString(R.string.DescriptionText,Money4Owned,MiscMethods.FormatNumber(Money4MakeAmount)));

        double Money5MakeAmount = MiscMethods.Money5Gain(requireContext());
        int Money5Owned = score.getInt("Money5",0);
        binding.MoneyBuy5.setText(MiscMethods.FormatNumber(MiscMethods.Money5Cost(requireContext())));
        binding.MoneyDescrip5.setText(getString(R.string.DescriptionText,Money5Owned,MiscMethods.FormatNumber(Money5MakeAmount)));




        timerHandler.post(timerRunnable);

        binding.MoneyBuy1.setOnClickListener(view1 -> MiscMethods.ButtonPressAction(binding.MoneyBuy1,MiscMethods.Money1Cost(requireContext()),"Money1","Social",requireActivity()));

        binding.MoneyBuy2.setOnClickListener(view12 -> MiscMethods.ButtonPressAction(binding.MoneyBuy2,MiscMethods.Money2Cost(requireContext()),"Money2","Will",requireActivity()));

        binding.MoneyBuy3.setOnClickListener(view13 -> MiscMethods.ButtonPressAction(binding.MoneyBuy3,MiscMethods.Money3Cost(requireContext()),"Money3","Int",requireActivity()));

        binding.MoneyBuy4.setOnClickListener(view14 -> MiscMethods.ButtonPressAction(binding.MoneyBuy4,MiscMethods.Money4Cost(requireContext()),"Money4","Money",requireActivity()));

        binding.MoneyBuy5.setOnClickListener(view15 -> MiscMethods.ButtonPressAction(binding.MoneyBuy5,MiscMethods.Money4Cost(requireContext()),"Money5","Social",requireActivity()));


    }

    private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This Money just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            double cost1 = MiscMethods.Money1Cost(requireContext());
            double cost2 = MiscMethods.Money2Cost(requireContext());
            double cost3 = MiscMethods.Money3Cost(requireContext());
            double cost4 = MiscMethods.Money4Cost(requireContext());
            double cost5 = MiscMethods.Money4Cost(requireContext());



            double Money1MakeAmount = MiscMethods.Money1Gain(requireContext());
            int Money1Owned = saves.getInt("Money1",0);
            binding.MoneyBuy1.setText(MiscMethods.FormatNumber(cost1));
            binding.MoneyDescrip1.setText(getString(R.string.DescriptionText,Money1Owned,MiscMethods.FormatNumber(Money1MakeAmount)));



            double Money2MakeAmount = MiscMethods.Money2Gain(requireContext());
            int Money2Owned = saves.getInt("Money2",0);
            binding.MoneyBuy2.setText(MiscMethods.FormatNumber(cost2));
            binding.MoneyDescrip2.setText(getString(R.string.DescriptionText,Money2Owned,MiscMethods.FormatNumber(Money2MakeAmount)));


            double Money3MakeAmount = MiscMethods.Money3Gain(requireContext());
            int Money3Owned = saves.getInt("Money3",0);
            binding.MoneyBuy3.setText(MiscMethods.FormatNumber(cost3));
            binding.MoneyDescrip3.setText(getString(R.string.DescriptionText,Money3Owned,MiscMethods.FormatNumber(Money3MakeAmount)));

            double Money4MakeAmount = MiscMethods.Money4Gain(requireContext());
            int Money4Owned = saves.getInt("Money4",0);
            binding.MoneyBuy4.setText(MiscMethods.FormatNumber(cost4));
            binding.MoneyDescrip4.setText(getString(R.string.DescriptionText,Money4Owned,MiscMethods.FormatNumber(Money4MakeAmount)));

            double Money5MakeAmount = MiscMethods.Money5Gain(requireContext());
            int Money5Owned = saves.getInt("Money5",0);
            binding.MoneyBuy5.setText(MiscMethods.FormatNumber(MiscMethods.Money5Cost(requireContext())));
            binding.MoneyDescrip5.setText(getString(R.string.DescriptionText,Money5Owned,MiscMethods.FormatNumber(Money5MakeAmount)));


            double will = MiscMethods.getDouble(saves,"Will",0);
            double intelligence = MiscMethods.getDouble(saves,"Int",0);
            double social = MiscMethods.getDouble(saves,"Social",0);
            double money = MiscMethods.getDouble(saves,"Money",0);

            if (social >= cost1) {
                ViewCompat.setBackgroundTintList(binding.MoneyBuy1, ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.MoneyBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
            }

            if (will >= cost2) {
                ViewCompat.setBackgroundTintList(binding.MoneyBuy2,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.MoneyBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            }

            if (intelligence >= cost3) {
                ViewCompat.setBackgroundTintList(binding.MoneyBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.MoneyBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            }

            if (money >= cost4) {
                ViewCompat.setBackgroundTintList(binding.MoneyBuy4,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.MoneyBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkMoney)));
            }
            if (social >= cost4) {
                ViewCompat.setBackgroundTintList(binding.MoneyBuy5,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
            }
            else{
                ViewCompat.setBackgroundTintList(binding.MoneyBuy5,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non Money things.
            switch(age){
                case 8:
                    binding.MoneyBuy1.setVisibility(View.VISIBLE);
                    binding.MoneyDescrip1.setVisibility(View.VISIBLE);
                    break;
                case 13:
                    binding.MoneyBuy2.setVisibility(View.VISIBLE);
                    binding.MoneyDescrip2.setVisibility(View.VISIBLE);
                    break;
                case 17:
                    binding.MoneyBuy3.setVisibility(View.VISIBLE);
                    binding.MoneyDescrip3.setVisibility(View.VISIBLE);
                    break;
                case 20:
                    binding.MoneyBuy4.setVisibility(View.VISIBLE);
                    binding.MoneyDescrip4.setVisibility(View.VISIBLE);
                    break;
                case 22:
                    binding.MoneyBuy5.setVisibility(View.VISIBLE);
                    binding.MoneyDescrip5.setVisibility(View.VISIBLE);
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