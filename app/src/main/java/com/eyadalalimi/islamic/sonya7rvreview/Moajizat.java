package com.eyadalalimi.islamic.sonya7rvreview;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Moajizat extends AppCompatActivity {
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anbiya);
        final String mo3="moajezat";
        typeface = Typeface.createFromAsset(getAssets(), "font3.otf");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ListView ls= findViewById(R.id.listtitlestories);
        TextView txt= findViewById(R.id.txtstories);
        txt.setText(getString(R.string.mo3jizat));
        txt.setTypeface(typeface);
        String[] items =getResources().getStringArray(R.array.moajizat);
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.stories_row,R.id.txttitle,items);
        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent lire=new Intent(Moajizat.this,Lire_stories.class);
                lire.putExtra("pos",position);
                lire.putExtra("a2",mo3);
                startActivity(lire);
            }
        });
    }

}
