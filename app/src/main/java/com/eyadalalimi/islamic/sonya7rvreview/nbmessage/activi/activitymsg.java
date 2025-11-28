package com.eyadalalimi.islamic.sonya7rvreview.nbmessage.activi;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;
import com.eyadalalimi.islamic.sonya7rvreview.nbmessage.apapter.adap_msg;
import com.eyadalalimi.islamic.sonya7rvreview.nbmessage.database.Tablemsg;
import com.eyadalalimi.islamic.sonya7rvreview.nbmessage.database.database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class activitymsg extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager recylerViewLayoutManager;
    adap_msg adpater;
    ArrayList<String> Message = new ArrayList<String>();
    ArrayList<String> FavMessage = new ArrayList<String>();
    LinearLayout ly;
    database db;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        ly = findViewById(R.id.partmesz);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbar.inflateMenu(R.menu.itme_message_secreen);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        recyclerView = findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#142d4c"));
        }
        try {
            db = new database(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Tablemsg> contacts = db.getmESSAGE(getIntent().getExtras().getInt("part"));
        for (Tablemsg cn : contacts) {
            Message.add(cn.getTag());
            FavMessage.add(cn.getMessage());
        }

        adpater = new adap_msg(this, Message, FavMessage, getIntent().getExtras().getInt("part"));
        recyclerView.setAdapter(adpater);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ly.removeView(adView);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                ly.addView(adView);
            }
        }


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;




        }
        return (super.onOptionsItemSelected(menuItem));
    }

}