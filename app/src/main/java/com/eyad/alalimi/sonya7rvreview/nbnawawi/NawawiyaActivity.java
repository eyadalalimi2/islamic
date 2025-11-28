package com.eyad.alalimi.sonya7rvreview.nbnawawi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.other.ActivityUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/**
 * Created by anwar_se on 6/30/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class NawawiyaActivity extends AppCompatActivity {

    public static final String EXTRA_Nawawiya_PAGE = "com.eyadalalimi.islamic.pro.EXTRA_Nawawiya_PAGE";

    public static void newIntent(Activity activity, String extraText, int extraPage) {
        Intent nawawiyaIntent = new Intent(activity, NawawiyaActivity.class);
        nawawiyaIntent.putExtra(NawawiyaActivity.EXTRA_Nawawiya_PAGE, "nawawiya_40/page" + extraPage + ".html");
        nawawiyaIntent.putExtra(Intent.EXTRA_TEXT, extraText);
        activity.startActivity(nawawiyaIntent);
    }

    public static void newIntent(Activity activity, String extraText, String extraPage) {
        Intent nawawiyaIntent = new Intent(activity, NawawiyaActivity.class);
        nawawiyaIntent.putExtra(NawawiyaActivity.EXTRA_Nawawiya_PAGE, extraPage);
        nawawiyaIntent.putExtra(Intent.EXTRA_TEXT, extraText);
        activity.startActivity(nawawiyaIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nawawiya);
        String title = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        ActivityUtils.setupToolbar(this, title);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        WebView wv = findViewById(R.id.wv_nawawiya_40);
        String file = getIntent().getStringExtra(EXTRA_Nawawiya_PAGE);
        wv.loadUrl("file:///android_asset/" + file);
    }

}
