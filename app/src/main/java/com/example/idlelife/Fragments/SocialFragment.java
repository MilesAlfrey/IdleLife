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
        ButtonRemover.putBoolean("ShowAgeUp",true);
        ButtonRemover.apply();

        //TODO: TO EDIT TO MAKE IT SPECIFIC TO SOCIAL.

/*


        //WHERE I WILL PUT ALL OF VISIBILITY SETTINGS

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);

        if(score.getBoolean("ShowWill2",false)){
            binding.WillBuy2.setVisibility(View.VISIBLE); //Makes sure it always appears correctly.
            binding.WillDescrip2.setVisibility(View.VISIBLE);
        }

        //END OF VISIBILITY SETTINGS








        binding.WillBuy1.setText(String.valueOf(MiscMethods.Button1Cost(requireContext())));
        binding.WillDescrip1.setText(String.valueOf(score.getInt("Will1",0)));

        int cost = MiscMethods.Button1Cost(requireContext());
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

                SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE );


                int cost = MiscMethods.Button1Cost(requireContext());

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
                        timerHandler.post(timerRunnable);//Starts the runnable for other way round
                    }


                    binding.WillBuy1.setText(String.valueOf(MiscMethods.Button1Cost(requireContext())));
                    binding.WillDescrip1.setText(String.valueOf(Current+1));
                }


            }
        });*/


    }

    /*
    private final static int Update_Interval= 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int cost = MiscMethods.Button1Cost(requireContext());
            long score = saves.getLong("Will", 0);

            if (score >= cost) {//Stops it going on forever.
                binding.WillBuy1.setBackgroundColor(0xffff0000); //Stop if red

            }

            int age = saves.getInt("Age",0);

            //JUST COPY AND PASTE THE ONE IN MISC METHODS HERE just do same and remove all non will things.
            switch(age){
                case 1:
                    binding.WillBuy2.setVisibility(View.VISIBLE);
                    binding.WillDescrip2.setVisibility(View.VISIBLE);//On age 2 we show will2
                    break;
                case 2:
                    //What do on age 2... and so on
            }

            timerHandler.postDelayed(timerRunnable,Update_Interval);
        }
    };

*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        //timerHandler.removeCallbacks(timerRunnable);
    }



}