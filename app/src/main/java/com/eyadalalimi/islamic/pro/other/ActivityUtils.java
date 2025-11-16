package com.eyadalalimi.islamic.pro.other;

import android.content.Context;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eyadalalimi.islamic.pro.R;


/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class ActivityUtils {

    public static final String EXTRA_INDEX_ID = "com.eyadalalimi.islamic.pro.EXTRA_INDEX_ID";
    public static final String EXTRA_INDEX_TEXT = "com.eyadalalimi.islamic.pro.EXTRA_INDEX_TEXT";
    public static final String EXTRA_INDEX_COLOR = "com.eyadalalimi.islamic.pro.EXTRA_INDEX_COLOR";
    public static final String EXTRA_INDEX_COUNT = "com.eyadalalimi.islamic.pro.EXTRA_INDEX_COUNT";

    public static void setupToolbar(AppCompatActivity activity, @StringRes int titleRes, boolean withBack) {
        ActivityUtils.setupToolbar(activity, activity.getResources().getString(titleRes), withBack);
    }

    public static void setupToolbar(AppCompatActivity activity, @StringRes int titleRes) {
        ActivityUtils.setupToolbar(activity, activity.getResources().getString(titleRes), true);
    }

    public static void setupToolbar(AppCompatActivity activity, String titleRes) {
        ActivityUtils.setupToolbar(activity, titleRes, true);
    }

    public static void setupToolbar(final AppCompatActivity activity, String title, boolean withBack) {
        Toolbar mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        mToolbar.setTitle(" ");
        activity.setSupportActionBar(mToolbar);
        TextView mToolBarTextView = (TextView) activity.findViewById(R.id.tv_toolbar_title);
        activity.setSupportActionBar(mToolbar);
        if (activity.getSupportActionBar() != null && withBack) {
            activity.getSupportActionBar().setHomeButtonEnabled(true);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();

                }
            });
        }
        /*Typeface tf2 = Typeface.createFromAsset(activity.getAssets(), "font/1.otf");
        mToolBarTextView.setTypeface(tf2);*/
        mToolBarTextView.setText(title);
    }
    public static void setupToolbar2(AppCompatActivity activity, @StringRes int titleRes) {
        Toolbar mToolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        mToolbar.setTitle(" ");
        activity.setSupportActionBar(mToolbar);
        TextView mToolBarTextView = (TextView) activity.findViewById(R.id.tv_toolbar_title);
        ImageView img=(ImageView) activity.findViewById(R.id.menu);
        img.setVisibility(View.INVISIBLE);
        activity.setSupportActionBar(mToolbar);
        mToolBarTextView.setText(titleRes);

    }
    public static void showCustomToast(Context context, String text) {
        View toastView = LayoutInflater.from(context).inflate(R.layout.content_toast, null);
        TextView tvText = toastView.findViewById(R.id.tv_message);
        tvText.setText(text);
        Toast toast = new Toast(context);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.END, 20, 100);
        toast.show();
    }
}
