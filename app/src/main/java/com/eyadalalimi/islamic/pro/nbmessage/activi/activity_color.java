package com.eyadalalimi.islamic.pro.nbmessage.activi;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.eyadalalimi.islamic.pro.R;

public class activity_color extends Activity implements View.OnClickListener {

    ImageView blue, red, white, pink, aqua, brown, green, blu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        blue = (ImageView) findViewById(R.id.blue);
        red = (ImageView) findViewById(R.id.red);
        white = (ImageView) findViewById(R.id.black);
        pink = (ImageView) findViewById(R.id.pink);
        brown = (ImageView) findViewById(R.id.broiwn);
        green = (ImageView) findViewById(R.id.geen);
        blu = (ImageView) findViewById(R.id.darkslateblue);
        aqua = (ImageView) findViewById(R.id.aqua);
        blue.setOnClickListener(this);
        red.setOnClickListener(this);
        white.setOnClickListener(this);
        pink.setOnClickListener(this);
        brown.setOnClickListener(this);
        green.setOnClickListener(this);
        blu.setOnClickListener(this);
        aqua.setOnClickListener(this);
    }

    Intent intent;

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.aqua:
                intent = new Intent();
                intent.putExtra("color", "#09c3f1");
                setResult(RESULT_OK, intent);
                finish();
                break;


            case R.id.blue:
                intent = new Intent();
                intent.putExtra("color", "#001eff");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.black:
                intent = new Intent();
                intent.putExtra("color", "#000000");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.broiwn:
                intent = new Intent();
                intent.putExtra("color", "#A52A2A");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.pink:
                intent = new Intent();
                intent.putExtra("color", "#f109e9");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.red:
                intent = new Intent();
                intent.putExtra("color", "#f40f0f");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.darkslateblue:
                intent = new Intent();
                intent.putExtra("color", "#483D8B");
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.geen:
                intent = new Intent();
                intent.putExtra("color", "#006400");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }


    }
}

