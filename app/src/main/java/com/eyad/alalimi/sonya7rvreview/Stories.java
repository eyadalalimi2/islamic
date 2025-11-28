package com.eyad.alalimi.sonya7rvreview;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Stories extends AppCompatActivity {
    Typeface typeface;
    Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Button anbiya = findViewById(R.id.anbiyaa);
        TextView x = findViewById(R.id.st);
        Button sahaba = findViewById(R.id.sahaba);
        Button mo3jizat = findViewById(R.id.mo3jizat);
        Button islamic = findViewById(R.id.aislamic);
        typeface = Typeface.createFromAsset(getAssets(), "font3.otf");
        anbiya.setTypeface(typeface);
        x.setTypeface(typeface);
        sahaba.setTypeface(typeface);
        mo3jizat.setTypeface(typeface);
        islamic.setTypeface(typeface);
        anbiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Stories.this, anbiya.class);
                startActivity(i);

            }
        });
        sahaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Stories.this, sahaba.class);
                startActivity(i);

            }
        });
        mo3jizat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Stories.this, Moajizat.class);
                startActivity(i);

            }
        });
        islamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Stories.this, Islamic.class);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.firstmenu, menu);
        myMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu) {
            Intent intent = new Intent(this, Sellings.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
