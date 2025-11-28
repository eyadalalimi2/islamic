package com.eyad.alalimi.sonya7rvreview.nbprayer.preferences;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

/**
 * عنصر SeekBar مخصص مع نصوص عربية/إنجليزية.
 */
public final class ExtendedSeekBar extends LinearLayout implements OnSeekBarChangeListener {

    private final boolean mIsArabic;
    private final String mMessage;
    private final SeekBar mSeekBar;
    private final boolean mShouldShowMinutes;
    private final TextView mSplashText;
    private final TextView mSuffixText;
    private final TextView mValueText;
    private int mMin = 0;
    private String mMinutes;
    private String mSuffix;

    public ExtendedSeekBar(Context context, boolean isArabic, boolean showMinutes, String suffix, String message) {
        super(context);
        this.mIsArabic = isArabic;
        this.mSuffix = suffix;
        this.mMessage = message;
        this.mShouldShowMinutes = showMinutes;
        setOrientation(LinearLayout.VERTICAL);
        setPadding(6, 6, 6, 6);

        // إعداد النص الأعلى
        this.mSplashText = new TextView(getContext());
        this.mSplashText.setSingleLine();
        this.mSplashText.setGravity(1);
        // بدلاً من setTextAppearance، نحدد حجم الخط يدويًا
        this.mSplashText.setTextSize(16);
        if (this.mMessage != null) {
            this.mSplashText.setText(this.mMessage);
        }
        addView(this.mSplashText, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        // إعداد حاوية النصوص الأخرى
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        int dp8 = (int) (8.0f * getContext().getResources().getDisplayMetrics().density);
        linearLayout.setPadding(dp8, dp8, dp8, dp8);
        linearLayout.setGravity(1);
        ViewCompat.setLayoutDirection(linearLayout, ViewCompat.LAYOUT_DIRECTION_LTR);

        this.mSuffixText = new TextView(getContext());
        this.mSuffixText.setSingleLine();
        this.mSuffixText.setTextSize(14); // تحديد حجم الخط

        this.mMinutes = "";
        this.mValueText = new TextView(getContext());
        this.mValueText.setTextSize(14); // تحديد حجم الخط

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (this.mIsArabic) {
            linearLayout.addView(this.mSuffixText);
            linearLayout.addView(this.mValueText);
        } else {
            linearLayout.addView(this.mValueText);
            linearLayout.addView(this.mSuffixText);
        }
        addView(linearLayout, layoutParams);

        this.mSeekBar = new SeekBar(getContext());
        this.mSeekBar.setOnSeekBarChangeListener(this);
        if (this.mIsArabic) {
            ViewCompat.setLayoutDirection(this.mSeekBar, ViewCompat.LAYOUT_DIRECTION_RTL);
        }
        addView(this.mSeekBar, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /**
     * تحدّث النصوص بناءً على قيمة مؤشر SeekBar.
     */
    public void prepareUi() {
        int progress = this.mSeekBar.getProgress();
        if (this.mSuffix == null) {
            this.mSuffix = "";
        }
        if (!this.mShouldShowMinutes) {
            this.mValueText.setText(String.valueOf(progress));
        } else if (!this.mIsArabic) {
            if (progress == 1) {
                this.mMinutes = "minute";
            } else {
                this.mMinutes = "minutes";
            }
            this.mSuffixText.setText(" " + this.mMinutes + " " + this.mSuffix);
            this.mValueText.setText(String.valueOf(progress));
        } else if (progress == 1) {
            this.mMinutes = "دقيقة";
            this.mSuffixText.setText(" " + this.mMinutes + " " + this.mSuffix);
            this.mValueText.setText("");
        } else if (progress == 2) {
            this.mMinutes = "دقيقتين";
            this.mSuffixText.setText(" " + this.mMinutes + " " + this.mSuffix);
            this.mValueText.setText("");
        } else {
            int i = progress % 100;
            if (i < 3 || i > 10) {
                this.mMinutes = "دقيقة";
                this.mSuffixText.setText(" " + this.mMinutes + " " + this.mSuffix);
                this.mValueText.setText(String.valueOf(progress));
                return;
            }
            this.mMinutes = "دقائق";
            this.mSuffixText.setText(" " + this.mMinutes + " " + this.mSuffix);
            this.mValueText.setText(String.valueOf(progress));
        }
    }

    /**
     * إرجاع مؤشر SeekBar للتحكم في قيمته.
     */
    public SeekBar getSeekBar() {
        return this.mSeekBar;
    }

    /**
     * تحديد الحد الأدنى لقيمة الـ SeekBar.
     */
    public void setMin(int min) {
        this.mMin = min;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress < this.mMin) {
            seekBar.setProgress(this.mMin);
        }
        prepareUi();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
