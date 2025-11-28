package com.eyadalalimi.islamic.sonya7rvreview;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Lira_anbiyaa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lira_anbiyaa);
        WebView web= findViewById(R.id.webanbiyaa);
        Intent i=getIntent();
        int pos=i.getExtras().getInt("pos");
        String dossier=i.getStringExtra("a2");
        pos++;
        web.loadUrl("file:///android_asset/"+dossier+"/"+pos+"/"+"index.html");
    }
}
