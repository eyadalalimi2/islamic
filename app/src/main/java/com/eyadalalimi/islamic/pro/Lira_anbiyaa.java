package com.eyadalalimi.islamic.pro;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Lira_anbiyaa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lira_anbiyaa);
        WebView web=(WebView)findViewById(R.id.webanbiyaa);
        Intent i=getIntent();
        int pos=i.getExtras().getInt("pos");
        String dossier=i.getStringExtra("a2");
        pos++;
        web.loadUrl("file:///android_asset/"+dossier+"/"+pos+"/"+"index.html");
    }
}
