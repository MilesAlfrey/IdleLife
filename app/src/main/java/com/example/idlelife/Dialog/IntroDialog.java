package com.example.idlelife.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.idlelife.R;

public class IntroDialog extends androidx.fragment.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.LIVE).setNeutralButton(R.string.Ok, (dialogInterface, i) -> {
            //What do on click.
        });

    return builder.create(); //Here we actually create the dialog that has been set up above.
    }
}
