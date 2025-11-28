package com.eyad.alalimi.sonya7rvreview;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Lire_stories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_stories);
        WebView web = findViewById(R.id.webStories);
        Intent i = getIntent();
        int pos = i.getExtras().getInt("pos");
        String dossier = i.getStringExtra("a2");
        pos++;
        web.loadUrl("file:///android_asset/" + dossier + "/" + pos + ".html");
    }
}
