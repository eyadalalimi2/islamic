package com.eyadalalimi.islamic.sonya7rvreview.nbprayer.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.preference.DialogPreference;

import com.eyadalalimi.islamic.sonya7rvreview.R;


public class SeekBarPreference extends DialogPreference {
    private int mDefault;
    String mDialogMessage;
    public int mMax;
    int mMin = 0;
    String mSuffix;
    public int mValue = 0;

    public SeekBarPreference(Context context) {
        super(context, null);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBarPreference);
        if (obtainStyledAttributes != null) {
            this.mDialogMessage = obtainStyledAttributes.getString(R.styleable.SeekBarPreference_message);
            this.mSuffix = obtainStyledAttributes.getString(R.styleable.SeekBarPreference_suffix);
            this.mMax = obtainStyledAttributes.getInt(R.styleable.SeekBarPreference_seekbar_max, 10);
            obtainStyledAttributes.recycle();
        }
    }

    public final Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 1));
    }

    public final void setValue(int i) {
        boolean shouldDisableDependents = shouldDisableDependents();
        this.mValue = i;
        persistInt(i);
        boolean shouldDisableDependents2 = shouldDisableDependents();
        if (shouldDisableDependents2 != shouldDisableDependents) {
            notifyDependencyChange(shouldDisableDependents2);
        }
    }

    public final void onSetInitialValue(boolean z, Object obj) {
        super.onSetInitialValue(z, obj);
        if (z) {
            this.mValue = shouldPersist() ? getPersistedInt(this.mDefault) : 0;
        } else {
            this.mValue = ((Integer) obj).intValue();
        }
    }

    public final /* bridge */ /* synthetic */ CharSequence getDialogMessage() {
        return this.mDialogMessage;
    }


}
