package com.eyadalalimi.islamic.pro.nbholyquran;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.other.ActivityUtils;
import com.eyadalalimi.islamic.pro.other.ContentUtil;


/**
 * Created by anwar_se on 6/21/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvText;
    private String stringExtra;

    public static void newIntent(Activity activity, String extraText, String extraTitle) {
        Intent fatawyIntent = new Intent(activity, TextActivity.class);
        fatawyIntent.putExtra(Intent.EXTRA_TITLE, extraTitle);
        fatawyIntent.putExtra(Intent.EXTRA_TEXT, extraText);
        activity.startActivity(fatawyIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        String stringTitle = getIntent().getStringExtra(Intent.EXTRA_TITLE);
        ActivityUtils.setupToolbar(this, stringTitle);

        tvText = findViewById(R.id.tv_text);

        stringExtra = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        tvText.setText(stringExtra);
    }

    @Override
    public void onClick(View v) {
        ContentUtil.shareText(this, stringExtra);
    }


}
