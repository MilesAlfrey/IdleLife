package com.example.idlelife.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;


import com.example.idlelife.R;
import com.example.idlelife.databinding.FragmentTestsBinding;




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


        //WHERE I WILL PUT ALL OF VISIBILITY SETTINGS

        SharedPreferences score = requireContext().getSharedPreferences("Values", Context.MODE_PRIVATE);

        //GREYS OUT COMPLETED TESTS
        if (score.getBoolean("Test1Completed", false)) {
            greyCompleted(1);
        }
        if (score.getBoolean("Test2Completed", false)) {
            greyCompleted(2);
        }
        if (score.getBoolean("Test3Completed", false)) {
            greyCompleted(3);
        }
        if (score.getBoolean("Test4Completed", false)) {
            greyCompleted(4);
        }
        if (score.getBoolean("Test5Completed", false)) {
            greyCompleted(5);
        }
        if (score.getBoolean("Test6Completed", false)) {
            greyCompleted(6);
        }


        //END OF VISIBILITY SETTINGS

        int currentTest = score.getInt("Test", 0);

        binding.NameAndBuffText.setText(String.valueOf(currentTest));

        int TestSpeed = score.getInt("TestSpeed", 0);

        Pair<String, Long> result = SpeedUpCost(currentTest, TestSpeed);

        String currency = result.first;
        long cost = result.second;


        //For editing the increase speed button

        binding.IncreaseSpeed.setText(String.valueOf(cost));

        long owned = score.getLong(currency, 0);


        colourChange(cost, owned, currency); //changes the colours depending on currency and amount owned


        //Tests whether the increase speed and take test buttons can be pressed
        if (!score.getBoolean("EnableTestButtons", true)) {
            binding.IncreaseSpeed.setEnabled(false);
            binding.TakeTest.setEnabled(false);
        }


        //Set bar length and percentage amount to prevent jitter on new tab selection
        int TestProgress = score.getInt("TestProgress", 0);
        int TestLength = score.getInt("TestLength", 0);

        int MaxWidth = binding.ProgressBarToFill.getLayoutParams().width;
        float divide = (float) TestProgress / (float) TestLength;

        if (divide >= 1) { //Runs if the test is complete, setting it to 100 as maximum
            binding.FullBar.getLayoutParams().width = MaxWidth;
            binding.FullBar.requestLayout();
            binding.PercentageFill.setText(getString(R.string.Percentage, 100));
        } else {
            binding.FullBar.getLayoutParams().width = (int) Math.floor(divide * (float) MaxWidth);
            binding.FullBar.requestLayout();
            binding.PercentageFill.setText(getString(R.string.Percentage, (int) Math.floor(divide * 100)));
        }


        //Makes sure the correct tests are available at the right time

        int age = score.getInt("Age", 0);

        switch (age) {
            default:
                //Just makes sure everything runs.
            case 5:
                binding.Test5.setVisibility(View.VISIBLE); //May need to change the visibility of layouts to stop scrolling?
                binding.Test6.setVisibility(View.VISIBLE);
            case 4:
                //Nothing lol

            case 3:
                binding.Test4.setVisibility(View.VISIBLE);
                binding.SecondRow.setVisibility(View.VISIBLE);

            case 2:
                binding.Test3.setVisibility(View.VISIBLE);

            case 1:
                binding.FirstRow.setVisibility(View.VISIBLE);
                binding.Test1.setVisibility(View.VISIBLE);
                binding.Test2.setVisibility(View.VISIBLE);//On age 2 we show will2
            case 0:
                //None shows


        }


        binding.IncreaseSpeed.setOnClickListener(view18 -> {

            SharedPreferences UpdateAmount = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int TestSpeed1 = UpdateAmount.getInt("TestSpeed", 0);
            int currentTest12 = UpdateAmount.getInt("Test", 0);

            Pair<String, Long> result1 = SpeedUpCost(currentTest12, TestSpeed1);

            long CurrentScore = UpdateAmount.getLong(result1.first, 0);

            if (CurrentScore >= result1.second) {


                SharedPreferences.Editor AmountEditor = UpdateAmount.edit();

                long resultScore = CurrentScore - result1.second;

                AmountEditor.putLong(result1.first, resultScore);

                int Current = UpdateAmount.getInt("TestSpeed", 0);

                AmountEditor.putInt("TestSpeed", Current + 1);

                AmountEditor.apply();//Rest should work


                Pair<String, Long> result2 = SpeedUpCost(currentTest12, Current + 1); //TO GET THE NEW PRICE


                binding.IncreaseSpeed.setText(String.valueOf(result2.second));

                colourChange(result2.second, resultScore, result2.first);


            }


        });

        binding.TakeTest.setOnClickListener(view17 -> {

            SharedPreferences Values = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int currentTest1 = Values.getInt("Test", 0); //Gets the current test being performed

            if (currentTest1 != 0) {

                int ran = (int) Math.floor(Math.random() * 100) + 1;//GIVES A RANDOM NUMBER BETWEEN 1 and 100

                int total = Values.getInt("TestLength", 0);
                int current = Values.getInt("TestProgress", 0);
                int percentage = (int) Math.floor(((float) current / (float) total) * 100);//Gives a number between 0 and 100
                SharedPreferences.Editor change = Values.edit();
                if (percentage >= ran) {//Will only succeed the percentage number of times
                    //On pass

                    String TestCompleted = "Test" + currentTest1 + "Completed";
                    System.out.println(TestCompleted);
                    change.putBoolean(TestCompleted, true);

                    change.putInt("TestProgress", total);

                    binding.FullBar.getLayoutParams().width = binding.ProgressBarToFill.getLayoutParams().width;
                    binding.FullBar.requestLayout();//Set bar to appear full upon test completion
                    binding.PercentageFill.setText(getString(R.string.Percentage, 100));


                    binding.IncreaseSpeed.setEnabled(false);
                    binding.TakeTest.setEnabled(false);//WONT WORK WHEN LEAVING AND REJOINING
                    change.putBoolean("EnableTestButtons", false);

                    change.apply();

                    greyCompleted(currentTest1);
                } else {
                    System.out.println("LOOOSERRRR");
                    change.putInt("TestProgress", 0);
                    change.putInt("TestSpeed", 0);//RESET TO 0
                    change.apply();

                    Pair<String, Long> result2 = SpeedUpCost(currentTest1, 0);
                    binding.IncreaseSpeed.setText(String.valueOf(result2.second));//Makes sure the text is updated

                }


            }


        });


        binding.Test1.setOnClickListener(view16 -> ConfirmationDialog(1));

        binding.Test2.setOnClickListener(view15 -> ConfirmationDialog(2));

        binding.Test3.setOnClickListener(view14 -> ConfirmationDialog(3));

        binding.Test4.setOnClickListener(view13 -> ConfirmationDialog(4));

        binding.Test5.setOnClickListener(view12 -> ConfirmationDialog(5));

        binding.Test6.setOnClickListener(view1 -> ConfirmationDialog(6));
        //Sets all of the buttons to run the appropriate change test

        timerHandler.post(timerRunnable);
    }


    private final static int Update_Interval = 100; // IMPORTANT HOW OFTEN Checks for a change in button colour.

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() { //This will just always run and check everything as i am dumb and cant do it better.
            SharedPreferences saves = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

            int currentTest = saves.getInt("Test", 0);// 0 means no test current, and beyond that its numbered
            int TestSpeed = saves.getInt("TestSpeed", 0);


            Pair<String, Long> Cost = SpeedUpCost(currentTest, TestSpeed);
            colourChange(Cost.second, saves.getLong(Cost.first, 0), Cost.first);//Checks and sets the colour if  it can be bought or not


            //FOR THE CURRENT TEST, SPEEDING UP AND SETTING LENGTH OF THE BAR


            binding.NameAndBuffText.setText(String.valueOf(currentTest));

            if (currentTest != 0){

                int TestLength = saves.getInt("TestLength", 0);


                int TestProgress = saves.getInt("TestProgress", 0);

                //System.out.println(TestProgress);

                if (TestProgress >= TestLength) {
                    //I guess do nothing?

                    binding.FullBar.getLayoutParams().width = binding.ProgressBarToFill.getLayoutParams().width;
                    binding.FullBar.requestLayout(); //Just set bar to full

                    binding.PercentageFill.setText(getString(R.string.Percentage, 100)); //And percentage to 100

                } else {
                    int MaxWidth = binding.ProgressBarToFill.getLayoutParams().width;
                    float divide = (float) TestProgress / (float) TestLength;


                    binding.FullBar.getLayoutParams().width = (int) Math.floor(divide * (float) MaxWidth);
                    binding.FullBar.requestLayout(); // Simple division to set a fraction

                    //System.out.println((int) Math.floor(divide*100));

                    binding.PercentageFill.setText(getString(R.string.Percentage, (int) Math.floor(divide * 100)));
                }
                //Do what is needed when a test is actually present.
            }


            //RESTARTS THE RUNNER

            timerHandler.postDelayed(timerRunnable, Update_Interval);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void greyCompleted(int completedTest) {
        switch (completedTest) {
            case (1):
                ViewCompat.setBackgroundTintList(binding.Test1, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test1.setEnabled(false);
                break;
            case (2):

                ViewCompat.setBackgroundTintList(binding.Test2, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test2.setEnabled(false);
                break;
            case (3):

                ViewCompat.setBackgroundTintList(binding.Test3, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test3.setEnabled(false);
                break;
            case (4):
                ViewCompat.setBackgroundTintList(binding.Test4, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test4.setEnabled(false);
                break;
            case (5):
                ViewCompat.setBackgroundTintList(binding.Test5, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test5.setEnabled(false);
                break;
            case (6):
                ViewCompat.setBackgroundTintList(binding.Test6, ColorStateList.valueOf(getResources().getColor(R.color.CompleteTestGrey)));
                binding.Test6.setEnabled(false);
                break;
        }
    }

    private void changeTest(int newTest) {
        SharedPreferences Values = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Values.edit();
        editor.putInt("TestProgress", 0);
        editor.putInt("Test", newTest);
        editor.putInt("TestSpeed", 0);
        editor.putInt("TestLength", getTestLength(newTest));
        Pair<String, Long> cost = SpeedUpCost(newTest, 0);
        binding.IncreaseSpeed.setText(String.valueOf(cost.second));
        editor.putBoolean("EnableTestButtons", true);
        binding.TakeTest.setEnabled(true);
        binding.IncreaseSpeed.setEnabled(true);

        colourChange(cost.second, Values.getLong(cost.first, 0), cost.first);


        editor.apply();
    }

    private static int getTestLength(int TestNum) {
        switch (TestNum) {
            case 1:
                return 10000;
            case 2:
                return 50000;
            case 3:
                return 10001;
            case 4:
                return 50002;
            case 5:
                return 10005;
            case 6:
                return 50005;
        }
        return 0;

    }

    public static Pair<String, Long> SpeedUpCost(int Test, int amountBought) {

        long cost;
        switch (Test) {
            case 1:
                cost = 10000 + 5000 * amountBought + amountBought * amountBought * 1000;
                return new Pair<>("Will", cost);
            case 2:
                cost = 1000 + 5000 * amountBought + amountBought * amountBought * 1000;
                return new Pair<>("Int", cost);
            case 3:
                cost = 1;
                return new Pair<>("Will", cost);
            case 4:
                cost = 2;
                return new Pair<>("Will", cost);
            case 5:
                cost = 13;
                return new Pair<>("Will", cost);
            case 6:
                cost = 4;
                return new Pair<>("Will", cost);
            //TODO: ADD A FORMULA FOR EVERY TEST
        }
        return new Pair<>("Will", (long) 0);
        //Create individual costs for each test, similar to cost of bars.

        //First will have a will cost for now

    }


    private void colourChange(Long Cost, Long Owned, String Currency) {
        SharedPreferences Values = requireActivity().getSharedPreferences("Values", Context.MODE_PRIVATE);

        if (!Values.getBoolean("EnableTestButtons", true)) {
            //binding.IncreaseSpeed.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Darker_Grey));
            ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.Darker_Grey)));
            //binding.TakeTest.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Darker_Grey));
            ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.Darker_Grey)));
        } else if (Owned >= Cost) {//Stops it going on forever.
            //Colour change if can buy it

            switch (Currency) {
                case ("Will"):
                    //binding.IncreaseSpeed.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                    //binding.TakeTest.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.WillColour), PorterDuff.Mode.SRC_IN);
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.WillColour), PorterDuff.Mode.SRC_IN);
                    break;
                case ("Int"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    break;
                case ("Money"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    break;
                case ("Social"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    break;
            }
        } else {
            switch (Currency) {
                case ("Will"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.DarkWill)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.WillColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.WillColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.WillColour), PorterDuff.Mode.SRC_IN);
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.WillColour), PorterDuff.Mode.SRC_IN);
                    break;
                case ("Int"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.DarkInt)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.IntColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.IntColour));
                    break;
                case ("Money"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.DarkMoney)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.MoneyColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.MoneyColour));
                    break;
                case ("Social"):
                    ViewCompat.setBackgroundTintList(binding.IncreaseSpeed,ColorStateList.valueOf(getResources().getColor(R.color.DarkSocial)));
                    ViewCompat.setBackgroundTintList(binding.TakeTest,ColorStateList.valueOf(getResources().getColor(R.color.SocialColour)));
                    binding.PercentageFill.setTextColor(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.NameAndBuffText.setTextColor(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.FullBar.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    binding.ProgressBarToFill.setColorFilter(ContextCompat.getColor(requireContext(), R.color.SocialColour));
                    break;
            }
        }
    }

    private void ConfirmationDialog(int TestChoice){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View Message = inflater.inflate(R.layout.dialog_layout,null);
        TextView text = Message.findViewById(R.id.confirmationText);
        text.setText(getString(R.string.SwitchConfirmation, TestChoice));



        builder
                .setView(Message)
                .setPositiveButton(R.string.Yes, (dialogInterface, i) ->
                        changeTest(TestChoice))
                .setNegativeButton(R.string.No,(dialogInterface, i) -> {
                    //Do Nothing
                });
        builder.show();


    }

}






