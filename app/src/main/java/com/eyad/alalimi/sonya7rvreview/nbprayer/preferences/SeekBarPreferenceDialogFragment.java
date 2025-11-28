package com.eyad.alalimi.sonya7rvreview.nbprayer.preferences;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog.Builder;

import java.util.Locale;

public class SeekBarPreferenceDialogFragment extends AppCompatPreferenceDialogFragment {
    protected ExtendedSeekBar mExtendedSeekBar;
    protected int mMin = 0;

    public static SeekBarPreferenceDialogFragment newInstance(String str) {
        SeekBarPreferenceDialogFragment seekBarPreferenceDialogFragment = new SeekBarPreferenceDialogFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString("key", str);
        seekBarPreferenceDialogFragment.setArguments(bundle);
        return seekBarPreferenceDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public final void onBindDialogView(View view) {
        super.onBindDialogView(view);
        SeekBarPreference seekBarPreference = (SeekBarPreference) getPreference();
        this.mExtendedSeekBar.getSeekBar().setMax(seekBarPreference.mMax);
        this.mExtendedSeekBar.setMin(seekBarPreference.mMin);
        this.mExtendedSeekBar.getSeekBar().setProgress(seekBarPreference.mValue);
    }

    /* access modifiers changed from: protected */
    public final void onPrepareDialogBuilder(Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setTitle("");
    }

    public void onDialogClosed(boolean z) {
        if (z) {
            int progress = this.mExtendedSeekBar.getSeekBar().getProgress();
            if (getPreference().callChangeListener(Integer.valueOf(progress))) {
                ((SeekBarPreference) getPreference()).setValue(progress);
            }
        }
    }

    public View onCreateDialogView(Context context) {
        SeekBarPreference seekBarPreference = (SeekBarPreference) getPreference();
        ExtendedSeekBar extendedSeekBar = new ExtendedSeekBar(context, Locale.getDefault().toString().contains("ar"),
                true, seekBarPreference.mSuffix, seekBarPreference.mDialogMessage);
        this.mExtendedSeekBar = extendedSeekBar;
        return this.mExtendedSeekBar;
    }
}
