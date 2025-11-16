package com.eyadalalimi.islamic.pro.nbholyquran;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.DBManager;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.OnItemClickListener;

import java.util.List;



/**
 * Created by anwar_se on 6/21/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_index);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        RecyclerView recyclerView = findViewById(R.id.rec_quran_index);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<QuranIndex> quranIndexList = DBManager.getInstance(this).getQuranIndexList();
        QuranIndexAdapter indexAdapter = new QuranIndexAdapter(this, quranIndexList, recyclerView);
        recyclerView.setAdapter(indexAdapter);
        indexAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                QuranIndex quranIndex = quranIndexList.get(position);
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_TEXT, quranIndex.getSuraPage());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
