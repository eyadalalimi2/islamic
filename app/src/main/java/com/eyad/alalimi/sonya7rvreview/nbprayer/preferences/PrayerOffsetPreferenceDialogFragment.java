package com.eyad.alalimi.sonya7rvreview.nbprayer.preferences;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog.Builder;

import com.eyad.alalimi.sonya7rvreview.R;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class PrayerOffsetPreferenceDialogFragment extends AppCompatPreferenceDialogFragment
        implements OnSeekBarChangeListener {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    /* access modifiers changed from: private */
    public SeekBar mSeekBar;
    /* access modifiers changed from: private */
    public int mValue;
    private Button mDecreaseButton;
    private Button mIncreaseButton;
    private int mMax;
    private long mPrayerTime;
    private TextView mPrayerTimeTextView;
    private TextView mProgressTextView;
    private Calendar mUpdatedPrayerTime;

    public static PrayerOffsetPreferenceDialogFragment newInstance(String key, String time) {
        Log.d("TAG_Log", "newInstance:key " + key);
        PrayerOffsetPreferenceDialogFragment prayerOffsetPreferenceDialogFragment = new PrayerOffsetPreferenceDialogFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString("key", key);
        bundle.putString("prayer_time", time);
        prayerOffsetPreferenceDialogFragment.setArguments(bundle);
        return prayerOffsetPreferenceDialogFragment;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String mPrayerTimeStr = getArguments().getString("prayer_time");
        try {
            this.mPrayerTime = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).parse(mPrayerTimeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            this.mPrayerTime = new Date().getTime();
        }
        this.mUpdatedPrayerTime = Calendar.getInstance();
        this.mUpdatedPrayerTime.setTimeInMillis(this.mPrayerTime);
        this.mMax = ((SeekBarPreference) getPreference()).mMax;
    }

    public View onCreateDialogView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.prayer_offset_dialog_layout, null);
        this.mPrayerTimeTextView = inflate.findViewById(R.id.prayer_time);
        this.mProgressTextView = inflate.findViewById(R.id.progress);
        this.mSeekBar = inflate.findViewById(R.id.seekBar);
        this.mIncreaseButton = inflate.findViewById(R.id.increase);
        this.mIncreaseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PrayerOffsetPreferenceDialogFragment.this.mSeekBar
                        .setProgress(PrayerOffsetPreferenceDialogFragment.this.mValue + 1);
            }
        });
        this.mDecreaseButton = inflate.findViewById(R.id.decrease);
        this.mDecreaseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PrayerOffsetPreferenceDialogFragment.this.mSeekBar
                        .setProgress(PrayerOffsetPreferenceDialogFragment.this.mValue - 1);
            }
        });
        return inflate;
    }

    public void onBindDialogView(View view) {
        super.onBindDialogView(view);
        SeekBarPreference seekBarPreference = (SeekBarPreference) getPreference();
        this.mSeekBar.setMax(this.mMax * 2);
        this.mValue = seekBarPreference.mValue + this.mMax;
        this.mSeekBar.setProgress(this.mValue);
        TextView textView = this.mPrayerTimeTextView;
        long j = this.mPrayerTime;
        Calendar instance = Calendar.getInstance();

        DateFormatSymbols dateFormatSymbols = simpleDateFormat.getDateFormatSymbols();
        dateFormatSymbols.setAmPmStrings(new String[]{"ص", "م"});
        simpleDateFormat.setDateFormatSymbols(dateFormatSymbols);
        instance.setTimeInMillis(j);
        SpannableString spannableString = new SpannableString(simpleDateFormat.format(instance.getTime()));
        spannableString.setSpan(new StyleSpan(1), 0, 2, 0);
        textView.setText(spannableString);
        TextView textView2 = this.mProgressTextView;
        textView2.setText(String.valueOf(this.mValue - this.mMax));
        this.mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void onPrepareDialogBuilder(Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setTitle("");
    }

    public void onDialogClosed(boolean z) {
        if (z) {
            int progress = this.mSeekBar.getProgress() - this.mMax;
            if (getPreference().callChangeListener(Integer.valueOf(progress))) {
                ((SeekBarPreference) getPreference()).setValue(progress);
            }
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        SimpleDateFormat simpleDateFormat;
        this.mUpdatedPrayerTime.add(Calendar.MINUTE, i - this.mValue);
        TextView textView = this.mPrayerTimeTextView;
        Calendar calendar = this.mUpdatedPrayerTime;
        simpleDateFormat = new SimpleDateFormat("hh:mm a");
        DateFormatSymbols dateFormatSymbols = simpleDateFormat.getDateFormatSymbols();
        dateFormatSymbols.setAmPmStrings(new String[]{"ص", "م"});
        simpleDateFormat.setDateFormatSymbols(dateFormatSymbols);
        SpannableString spannableString = new SpannableString(simpleDateFormat.format(calendar.getTime()));
        spannableString.setSpan(new StyleSpan(1), 0, 2, 0);
        textView.setText(spannableString);
        if (i - this.mMax == 0) {
            this.mProgressTextView.setText("Max: " + mMax);
        } else {
            this.mProgressTextView.setText(new DecimalFormat("+#; -#").format(i - this.mMax));
        }
        this.mValue = i;
        this.mIncreaseButton.setEnabled(this.mValue != this.mMax * 2);
        this.mDecreaseButton.setEnabled(this.mValue != 0);
    }
}
