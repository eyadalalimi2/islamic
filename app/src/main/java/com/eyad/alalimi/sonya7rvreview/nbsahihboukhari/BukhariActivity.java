package com.eyad.alalimi.sonya7rvreview.nbsahihboukhari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.other.ActivityUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class BukhariActivity extends SearchActivity<DefaultIndex> implements OnItemClickListener {

    private final Context context = this;
    private String bookName;
    private StringRecyclerAdapter countriesAdapter;

    public static void newIntent(Activity activity, String indexTitle) {
        Intent fatawyIntent = new Intent(activity, BukhariActivity.class);
        fatawyIntent.putExtra(Intent.EXTRA_TEXT, indexTitle);
        activity.startActivity(fatawyIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bookName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ActivityUtils.setupToolbar(this, R.string.cat_albukhari);

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
        countriesAdapter = new StringRecyclerAdapter(context, fItems, countriesRecycler);
        countriesRecycler.setAdapter(countriesAdapter);
        countriesAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public List<DefaultIndex> getList() {
        return DBManager.getInstance(context).getAlbukhariList(bookName);
    }

    @Override
    public void notifyItems(List<DefaultIndex> mRecyclerViewItems) {
        countriesAdapter.notifyItems(mRecyclerViewItems);
    }
}
