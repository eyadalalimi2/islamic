package com.eyad.alalimi.sonya7rvreview;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(this);

    }
}
