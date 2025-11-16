package com.eyadalalimi.islamic.pro;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Lire_stories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_stories);
        WebView web=(WebView)findViewById(R.id.webStories);
        Intent i=getIntent();
       int pos=i.getExtras().getInt("pos");
        String dossier=i.getStringExtra("a2");
        pos++;
        web.loadUrl("file:///android_asset/"+dossier+"/"+pos+".html");
    }
}
