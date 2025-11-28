package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.DBManager;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.OnItemClickListener;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.SearchActivity;
import com.eyadalalimi.islamic.sonya7rvreview.other.ActivityUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

/**
 * Created by anwar_se on 7/11/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranSearchActivity extends SearchActivity<QuranSearch> implements OnItemClickListener {

    private final Context context = this;
    private QuranSearchAdapter countriesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ActivityUtils.setupToolbar(this, " ");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        RecyclerView countriesRecycler = findViewById(R.id.rec_index);
        int spanCount = 1;
        countriesRecycler.setLayoutManager(new GridLayoutManager(context, spanCount, RecyclerView.VERTICAL, false));
        countriesAdapter = new QuranSearchAdapter(fItems, countriesRecycler);
        countriesRecycler.setAdapter(countriesAdapter);
        countriesAdapter.setOnItemClickListener(this);
    }

    @Override
    public List<QuranSearch> getList() {
        return DBManager.getInstance(this).getQuranSearchList();
    }

    @Override
    public boolean fillList() {
        return false;
    }

    @Override
    public void notifyItems(List<QuranSearch> mRecyclerViewItems) {
        countriesAdapter.notifyItems(mRecyclerViewItems);
    }

    @Override
    public void onItemClick(View view, int position) {
        QuranSearch quranIndex = fItems.get(position);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, quranIndex.getPageAya());
        setResult(RESULT_OK, intent);
        finish();
    }
}
