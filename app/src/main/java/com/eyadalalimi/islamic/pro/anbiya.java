package com.eyadalalimi.islamic.pro;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class anbiya extends AppCompatActivity {
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anbiya);
        final String anbi="alanbiyaa";
        typeface = Typeface.createFromAsset(getAssets(), "font3.otf");
        ListView ls=(ListView)findViewById(R.id.listtitlestories);
        TextView txt=(TextView)findViewById(R.id.txtstories);
        txt.setText(getString(R.string.qissaanbiya));
        txt.setTypeface(typeface);
        MobileAds.initialize(getApplicationContext());
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        String items[]=getResources().getStringArray(R.array.anbiya);
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.stories_row,R.id.txttitle,items);
        ls.setAdapter(adapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent lire=new Intent(anbiya.this,Lira_anbiyaa.class);
                lire.putExtra("pos",position);
                lire.putExtra("a2",anbi);
                startActivity(lire);
            }
        });
    }
}
