package com.eyadalalimi.islamic.pro.nbprayer.preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.preference.DialogPreference;
import androidx.preference.DialogPreference.TargetFragment;
import androidx.preference.PreferenceDialogFragmentCompat;

public abstract class AppCompatPreferenceDialogFragment extends PreferenceDialogFragmentCompat {
    private DialogPreference mPreference;
    private int mWhichButtonClicked;

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(Builder builder) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPreference = (DialogPreference) ((TargetFragment) getTargetFragment()).findPreference(getArguments().getString("key"));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        this.mWhichButtonClicked = -2;
        Builder negativeButton = new Builder(activity)
                .setTitle(this.mPreference.getDialogTitle())
                .setIcon(this.mPreference.getIcon())
                .setPositiveButton(this.mPreference.getPositiveButtonText(), (OnClickListener) this)
                .setNegativeButton(this.mPreference.getNegativeButtonText(), (OnClickListener) this);
        View onCreateDialogView = onCreateDialogView(activity);
        if (onCreateDialogView != null) {
            onBindDialogView(onCreateDialogView);
            negativeButton.setView(onCreateDialogView);
        } else {
            negativeButton.setMessage(this.mPreference.getDialogMessage());
        }
        onPrepareDialogBuilder(negativeButton);
        AlertDialog create = negativeButton.create();
        if (needInputMethod()) {
            create.getWindow().setSoftInputMode(5);
        }
        return negativeButton.create();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.mWhichButtonClicked = i;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        onDialogClosed(this.mWhichButtonClicked == -1);
    }
}
