package com.eyadalalimi.islamic.pro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class ZikrView extends View {

    private final Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private int mColor = 0xFF33B5E5;
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

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(center / 10, center / 10, center / 10, mPaint);

        canvas.drawCircle(center, center, center, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(getColor());
        if ((mClickCount * mMax) != 0) {
            canvas.drawArc(mRectF, -90, (mClickCount * 360) / mMax, false, mPaint);
        }
        mPaint.setStrokeWidth(1);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize((center * 2) / 5);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(Math.round(mClickCount) + "", center, center, mPaint);

        mPaint.setTextSize((center * 2) / 20);
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
            this.mColor = 0xFF33B5E5;
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
