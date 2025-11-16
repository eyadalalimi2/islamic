package com.eyadalalimi.islamic.pro;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyAdkarActivity extends AppCompatActivity {
    Menu myMenu;
    public static int numberClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adkar);


    }


    public void adkarSabah(View view) {
        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","sabah");
        startActivity(i);

    }

    public void massa(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","masaa");
        startActivity(i);

    }

    public void adkaradima(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","best");
        startActivity(i);

    }

    public void adkaralmanzel(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","manzel");
        startActivity(i);

    }

    public void adaan(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","adan");
        startActivity(i);

    }


    public void adkar_almasjid(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","masjid");
        startActivity(i);

    }

    public void salat1(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","salat1");
        startActivity(i);

    }

    public void salat2(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","salat2");
        startActivity(i);

    }

    public void wodoa(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","wodoa");
        startActivity(i);

    }

    public void nawm(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","nawm");
        startActivity(i);

    }

    public void nawm2(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","nawm2");
        startActivity(i);

    }

    public void eat(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","eat");
        startActivity(i);

    }

    public void nabiy(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","nabiy");
        startActivity(i);


    }

    public void outher(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","outher");
        startActivity(i);

    }

    public void finishquran(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","finishquran");
        startActivity(i);

    }


    public void koul(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","koul");
        startActivity(i);


    }

    public void dikrQuran(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","dikrQuran");
        startActivity(i);

    }

    public void fadldouaa(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","fadldouaa");
        startActivity(i);

    }

    public void fadldikr(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","fadldikr");
        startActivity(i);

    }

    public void aljomoaa(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","aljomoaa");
        startActivity(i);

    }

    public void fadlqurann(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","fadlqurann");
        startActivity(i);

    }

    public void mayit(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","mayit");
        startActivity(i);

    }

    public void rouqya(View view) {

        Intent i = new Intent(MyAdkarActivity.this, Adkar_det.class);
        i.putExtra("nameadkar","rouqya");
        startActivity(i);


    }

    public void asmaaaa(View view) {

        Intent i = new Intent(MyAdkarActivity.this, asmaallah.class);
        startActivity(i);

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

