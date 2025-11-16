package com.eyadalalimi.islamic.pro.nbholyquran;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.DBManager;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class M3aniActivity extends AppCompatActivity {

    private Spinner sprTafsierSura, sprTafsierAya;
    private List<Integer> suraAyatNumList;
    private TafsirAdapter tafsirAdapter;
    private List<String> suraTafsirList;
    private RecyclerView recyclerView;

    private StringArrayAdapter sprAyaAdapter;
    private List<String> sprAyaList = new ArrayList<>();

    private int suraNum = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafsier);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        findViewById(R.id.spr_tafsier_book).setVisibility(View.GONE);
        sprTafsierSura = findViewById(R.id.spr_tafsier_sura);
        sprTafsierAya = findViewById(R.id.spr_tafsier_aya);

        suraAyatNumList = DBManager.getInstance(this).getSuraAyatNums();
        sprTafsierSura.setAdapter(new StringArrayAdapter(this, DBManager.getInstance(this).getSuraList()));

        sprAyaAdapter = new StringArrayAdapter(this, sprAyaList);
        sprTafsierAya.setAdapter(sprAyaAdapter);

        initSpinnerAyatNum();
        initSpinnerListener();

        recyclerView = findViewById(R.id.rec_tafsir);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        suraTafsirList = DBManager.getInstance(this).getM3aniList(suraNum);
        tafsirAdapter = new TafsirAdapter(this, suraTafsirList, recyclerView);
        recyclerView.setAdapter(tafsirAdapter);
    }

    private void initSpinnerListener() {
        sprTafsierSura.setOnItemSelectedListener(new SpinnerListener() {
            @Override
            public void onItemSelected(int position) {
                suraNum = position + 1;
                suraTafsirList = DBManager.getInstance(M3aniActivity.this).getM3aniList(suraNum);
                tafsirAdapter.notifyItems(suraTafsirList);
                initSpinnerAyatNum();
            }
        });
        sprTafsierAya.setOnItemSelectedListener(new SpinnerListener() {
            @Override
            public void onItemSelected(int position) {
                recyclerView.scrollToPosition(position);
            }
        });
    }

    private void initSpinnerAyatNum() {
        sprAyaList = new ArrayList<>();
        for (Integer i = 0; i < suraAyatNumList.get(suraNum - 1); i++) {
            sprAyaList.add(String.valueOf(i + 1));
        }
        sprAyaAdapter.notifyItems(sprAyaList);

    }

}
