package com.eyadalalimi.islamic.pro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class ZikrView extends View {

    private final Paint mPaint = new Paint();
    private final RectF mRectF = new RectF();
    private int mColor = 0xFFEBA800; // Changed default color to myorange
    private int mMax = 33;
    private int mClickCount;
    private int mCycleCount;
    private OnZekrClickListener zekrClickListener;

    public ZikrView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public ZikrView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZikrView(Context context) {
        this(context, null);
    }

    @Override
    public void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
        mRectF.set(0, 0, size, size);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int width = getWidth();
        int center = width / 2;
        canvas.scale(0.95f, 0.95f, center, center);

        mPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(center / 15);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(getColor()); // This will now use 0xFFEBA800 (myorange)
        if ((mClickCount * mMax) != 0) {
            canvas.drawArc(mRectF, -90, (mClickCount * 360) / mMax, false, mPaint);
        }
        mPaint.setStrokeWidth(1);

        mPaint.setColor(0xFFEBA800); // Set color to myorange for the click count
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize((center * 2) / 5);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(Math.round(mClickCount) + "", center, center, mPaint);

        mPaint.setTextSize((center * 2) / 20);
        mPaint.setColor(0xFFEBA800); // Set color to myorange for the cycle count
        canvas.drawText(mCycleCount + "", center / 10, center * 0.13f, mPaint);


        mPaint.setTextSize((center * 2) / 10);
        mPaint.setColor(Color.GRAY);
        canvas.drawText("/" + Math.round(mMax), center, (width * 2) / 3, mPaint);

    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        if (mColor == 0) {
            this.mColor = 0xFFEBA800; // Also update the default here for consistency
        }
        invalidate();
    }

    public int getClickCount() {
        return mClickCount;
    }

    public void setClickCount(int mCount) {
        this.mClickCount = mCount;
        invalidate();
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int mMax) {
        this.mMax = mMax;
    }

    public void click() {
        mClickCount++;

        if (mClickCount >= mMax) {
            mClickCount = 0;
            mCycleCount++;
            if (zekrClickListener != null)
                zekrClickListener.onFinishCycle();
        }
        invalidate();
    }

    public void resetClick() {
        mClickCount = 0;
        invalidate();
    }

    public void reset() {
        mMax = 33;
        mClickCount = 0;
        mCycleCount = 0;
        invalidate();
    }

    public void setZekrClickListener(OnZekrClickListener clickListener) {
        this.zekrClickListener = clickListener;
    }

    public interface OnZekrClickListener {
        void onFinishCycle();
    }
}
