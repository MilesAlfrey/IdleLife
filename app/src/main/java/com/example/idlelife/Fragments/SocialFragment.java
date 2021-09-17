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
import com.example.idlelife.databinding.FragmentSocialBinding;


public class SocialFragment extends Fragment  {



    private FragmentSocialBinding binding;

     //To test with view model

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {



        binding = FragmentSocialBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);


        SharedPreferences.Editor ButtonRemover = score.edit();
        ButtonRemover.putBoolean("ShowAgeUp", true);
        ButtonRemover.apply();


        if(score.getBoolean("ShowSocial1",false)){
            binding.SocialBuy1.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.SocialDescrip1.setVisibility(View.VISIBLE);
        }

        if(score.getBoolean("ShowSocial2",false)){
            binding.SocialBuy2.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.SocialDescrip2.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowSocial3",false)){
            binding.SocialBuy3.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.SocialDescrip3.setVisibility(View.VISIBLE);
        }
        if(score.getBoolean("ShowSocial4",false)){
            binding.SocialBuy4.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.SocialDescrip4.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS



        double cost1 = MiscMethods.Social1Cost(requireContext());
        double cost2 = MiscMethods.Social2Cost(requireContext());
        double cost3 = MiscMethods.Social3Cost(requireContext());
        double cost4 = MiscMethods.Social4Cost(requireContext());

        double will = MiscMethods.getDouble(score,"Will",0);
        double intelligence = MiscMethods.getDouble(score,"Int",0);
        double social = MiscMethods.getDouble(score,"Social",0);
        double money = MiscMethods.getDouble(score,"Money",0);

        if (will >= cost1) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.SocialBuy1, ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
            //binding.SocialBuy1.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.SocialBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
            //binding.SocialBuy1.setBackgroundColor(0xff555555);
        }

        if (social >= cost2) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.SocialBuy2,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
            //binding.SocialBuy2.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.SocialBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
            //binding.SocialBuy2.setBackgroundColor(0xff555555);
        }

        if (intelligence >= cost3) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.SocialBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
            //binding.SocialBuy3.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.SocialBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
            //binding.SocialBuy3.setBackgroundColor(0xff555555);
        }

        if (social >= cost4) {//Stops it going on forever.
            ViewCompat.setBackgroundTintList(binding.SocialBuy4,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
            //binding.SocialBuy4.setBackgroundColor(0xffff0000); //Stop if red
        }
        else{
            ViewCompat.setBackgroundTintList(binding.SocialBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
            //binding.SocialBuy4.setBackgroundColor(0xff555555);
        }





        double Social1MakeAmount = MiscMethods.Social1Gain(requireContext());
        int Social1Owned = score.getInt("Social1",0);
        binding.SocialBuy1.setText(MiscMethods.FormatNumber(MiscMethods.Social1Cost(requireContext())));
        binding.SocialDescrip1.setText(getString(R.string.DescriptionText,Social1Owned,MiscMethods.FormatNumber(Social1MakeAmount)));

        double Social2MakeAmount = MiscMethods.Social2Gain(requireContext());
        int Social2Owned = score.getInt("Social2",0);
        binding.SocialBuy2.setText(MiscMethods.FormatNumber(MiscMethods.Social2Cost(requireContext())));
        binding.SocialDescrip2.setText(getString(R.string.DescriptionText,Social2Owned,MiscMethods.FormatNumber(Social2MakeAmount)));

        double Social3MakeAmount = MiscMethods.Social3Gain(requireContext());
        int Social3Owned = score.getInt("Social3",0);
        binding.SocialBuy3.setText(MiscMethods.FormatNumber(MiscMethods.Social3Cost(requireContext())));
        binding.SocialDescrip3.setText(getString(R.string.DescriptionText,Social3Owned,MiscMethods.FormatNumber(Social3MakeAmount)));

        double Social4MakeAmount = MiscMethods.Social4Gain(requireContext());
        int Social4Owned = score.getInt("Social4",0);
        binding.SocialBuy4.setText(MiscMethods.FormatNumber(MiscMethods.Social4Cost(requireContext())));
        binding.SocialDescrip4.setText(getString(R.string.DescriptionText,Social4Owned,MiscMethods.FormatNumber(Social4MakeAmount)));




        timerHandler.post(timerRunnable);

        binding.SocialBuy1.setOnClickListener(view1 -> MiscMethods.ButtonPressAction(binding.SocialBuy1,MiscMethods.Social1Cost(requireContext()),"Social1","Will",requireActivity()));

        binding.SocialBuy2.setOnClickListener(view12 -> MiscMethods.ButtonPressAction(binding.SocialBuy2,MiscMethods.Social2Cost(requireContext()),"Social2","Social",requireActivity()));

        binding.SocialBuy3.setOnClickListener(view13 -> MiscMethods.ButtonPressAction(binding.SocialBuy3,MiscMethods.Social3Cost(requireContext()),"Social3","Int",requireActivity()));

        binding.SocialBuy4.setOnClickListener(view14 -> MiscMethods.ButtonPressAction(binding.SocialBuy4,MiscMethods.Social4Cost(requireContext()),"Social4","Social",requireActivity()));


    }

    private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This Social just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            double cost1 = MiscMethods.Social1Cost(requireContext());
            double cost2 = MiscMethods.Social2Cost(requireContext());
            double cost3 = MiscMethods.Social3Cost(requireContext());
            double cost4 = MiscMethods.Social4Cost(requireContext());




            double Social1MakeAmount = MiscMethods.Social1Gain(requireContext());
            int Social1Owned = saves.getInt("Social1",0);
            binding.SocialBuy1.setText(MiscMethods.FormatNumber(cost1));
            binding.SocialDescrip1.setText(getString(R.string.DescriptionText,Social1Owned,MiscMethods.FormatNumber(Social1MakeAmount)));



            double Social2MakeAmount = MiscMethods.Social2Gain(requireContext());
            int Social2Owned = saves.getInt("Social2",0);
            binding.SocialBuy2.setText(MiscMethods.FormatNumber(cost2));
            binding.SocialDescrip2.setText(getString(R.string.DescriptionText,Social2Owned,MiscMethods.FormatNumber(Social2MakeAmount)));


            double Social3MakeAmount = MiscMethods.Social3Gain(requireContext());
            int Social3Owned = saves.getInt("Social3",0);
            binding.SocialBuy3.setText(MiscMethods.FormatNumber(cost3));
            binding.SocialDescrip3.setText(getString(R.string.DescriptionText,Social3Owned,MiscMethods.FormatNumber(Social3MakeAmount)));

            double Social4MakeAmount = MiscMethods.Social4Gain(requireContext());
            int Social4Owned = saves.getInt("Social4",0);
            binding.SocialBuy4.setText(MiscMethods.FormatNumber(cost4));
            binding.SocialDescrip4.setText(getString(R.string.DescriptionText,Social4Owned,MiscMethods.FormatNumber(Social4MakeAmount)));


            double will = MiscMethods.getDouble(saves,"Will",0);
            double intelligence = MiscMethods.getDouble(saves,"Int",0);
            double social = MiscMethods.getDouble(saves,"Social",0);
            double money = MiscMethods.getDouble(saves,"Money",0);

            if (will >= cost1) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.SocialBuy1,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                //binding.SocialBuy1.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.SocialBuy1,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                //binding.SocialBuy1.setBackgroundColor(0xff555555);
            }

            if (social >= cost2) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.SocialBuy2,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                //binding.SocialBuy2.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.SocialBuy2,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
                //binding.SocialBuy2.setBackgroundColor(0xff555555);
            }

            if (intelligence >= cost3) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.SocialBuy3,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
                //binding.SocialBuy3.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.SocialBuy3,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
                //binding.SocialBuy3.setBackgroundColor(0xff555555);
            }

            if (social >= cost4) {//Stops it going on forever.
                ViewCompat.setBackgroundTintList(binding.SocialBuy4,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                //binding.SocialBuy4.setBackgroundColor(0xffff0000); //Stop if red
            }
            else{
                ViewCompat.setBackgroundTintList(binding.SocialBuy4,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
                //binding.SocialBuy4.setBackgroundColor(0xff555555);
            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non Social things.
            switch(age){
                case 5:
                    binding.SocialBuy1.setVisibility(View.VISIBLE);
                    binding.SocialDescrip1.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    binding.SocialBuy2.setVisibility(View.VISIBLE);
                    binding.SocialDescrip2.setVisibility(View.VISIBLE);
                    break;
                case 10:
                    binding.SocialBuy3.setVisibility(View.VISIBLE);
                    binding.SocialDescrip3.setVisibility(View.VISIBLE);
                    break;
                case 11:
                    binding.SocialBuy4.setVisibility(View.VISIBLE);
                    binding.SocialDescrip4.setVisibility(View.VISIBLE);
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