package com.eyadalalimi.islamic.pro.nbsahihboukhari;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.eyadalalimi.islamic.pro.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anwar_se on 7/10/2019
 * Email: anwar.dev.96@gmail.com.
 */
public abstract class SearchActivity<T extends BaseSearch> extends AppCompatActivity implements SearchView.OnQueryTextListener {

    protected List<T> fItems = new ArrayList<>();
    private List<T> allItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allItems = getList();
        fItems = fillList() ? new ArrayList<>(allItems) : new ArrayList<T>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (!fillList())
            mSearch.expandActionView();
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(this);


        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }

    public abstract List<T> getList();

    public boolean fillList() {
        return true;
    }

    public void filter(String text) {
        String prefix = text.toLowerCase();
        /*if (fillList()) {
            if (TextUtils.isEmpty(prefix)) {
                fItems = new ArrayList<>(allItems);
                notifyItems(fItems);
            } else {
                doFilter(prefix);
            }
        } else {
            if (TextUtils.isEmpty(prefix)) {
                fItems = new ArrayList<>();
                notifyItems(fItems);
            } else if (prefix.length() > 2)
                doFilter(prefix);
        }*/
        if (TextUtils.isEmpty(prefix)) {
            fItems = fillList() ? new ArrayList<>(allItems) : new ArrayList<T>();
            notifyItems(fItems);
        } else if (prefix.length() > 2)
            doFilter(prefix);
    }

    private void doFilter(String prefix) {
        final ArrayList<T> aList = new ArrayList<>(allItems);
        final ArrayList<T> nList = new ArrayList<>();
        int count = aList.size();

        for (int i = 0; i < count; i++) {
            final T baseSearch = aList.get(i);
            Log.d("Search_Log", "filter: " + baseSearch.toString() + "  text: " + prefix);
            if (baseSearch.isSame(prefix)) {
                nList.add(baseSearch);
                Log.d("Search_Log2", "filter: " + baseSearch.toString());
            }
        }
        fItems.clear();
        fItems.addAll(nList);
        notifyItems(new ArrayList<>(fItems));
    }

    public abstract void notifyItems(List<T> mRecyclerViewItems);
}
