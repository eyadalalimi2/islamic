package com.eyadalalimi.islamic.pro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class splash_screen extends Activity
{
    ImageView img_logo,loading;
    Animation rotation,rotation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img_logo=(ImageView)findViewById(R.id.imageView1);
        loading=(ImageView)findViewById(R.id.imageView45);
        rotation = AnimationUtils.loadAnimation(splash_screen.this, R.anim.rotate2);
        rotation2 = AnimationUtils.loadAnimation(splash_screen.this, R.anim.alpha);
        loading.startAnimation(rotation);
        img_logo.startAnimation(rotation2);
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 2000) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {

                    Intent i = new Intent(getApplicationContext(), MainActivityFist.class);
                    startActivity(i);
                    finish();

                }
            }
        };
        splashThread.start();
    }

}