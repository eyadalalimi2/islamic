package com.eyadalalimi.islamic.pro.nbmessage.activi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbmessage.database.Tablemsg;
import com.eyadalalimi.islamic.pro.nbmessage.database.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class activity_editsection extends AppCompatActivity {
    database db;
    ArrayList<String> Message = new ArrayList<String>();
    ImageView ChangeColor, CopyText, Share, Image;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_edit_secreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#142d4c"));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ChangeColor = (ImageView) findViewById(R.id.chane);
        ChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showsecrreen = new Intent(getApplicationContext(), activity_color.class);
                startActivityForResult(showsecrreen, 100);
            }
        });

        CopyText = (ImageView) findViewById(R.id.copy);
        CopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk = Build.VERSION.SDK_INT;
                if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(Message.get(myPager.getCurrentItem()));
                    Toast.makeText(getApplicationContext(), "تم نسخ الرساله", Toast.LENGTH_SHORT).show();
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("text label", Message.get(myPager.getCurrentItem()));
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "تم نسخ الرساله", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Share = (ImageView) findViewById(R.id.share);

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String shareBody = Message.get(myPager.getCurrentItem());
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "مشاركه"));

            }
        });

        Image = (ImageView) findViewById(R.id.text__image);
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress = ProgressDialog.show(activity_editsection.this, "جاري تكوين الصوره مع النص",
                        "بعد الانتهاء ستتمكن من مشاركة الرساله مدموجه مع الصوره من فضلك انتظر قليلا", true);
                progress.show();
                SaveaAndSare();

            }
        });
        try {
            db = new database(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Tablemsg> contacts = db.getmESSAGE(getIntent().getExtras().getInt("part"));
        for (Tablemsg cn : contacts) {
            Message.add(cn.getTag());
        }
        adapter = new pageviewer(this,
                Message, getIntent().getExtras().getInt("postion"), "#000000");
        myPager = (ViewPager) findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(getIntent().getExtras().getInt("postion"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);

                return;
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

                return;
            }
        }
//


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                    return;
                }
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
            }
//
        }
    }

    pageviewer adapter;
    ViewPager myPager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 100) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                adapter = new pageviewer(this,
                        Message, getIntent().getExtras().getInt("postion"), data.getExtras().getString("color"));
                myPager = (ViewPager) findViewById(R.id.reviewpager);
                myPager.setAdapter(adapter);
                myPager.setCurrentItem(getIntent().getExtras().getInt("postion"));


            }
        }
    }

    LinearLayout framelayout;

    public void SaveaAndSare() {
        framelayout = (LinearLayout) findViewById(R.id.layo);
        framelayout.setDrawingCacheEnabled(true);
        framelayout.buildDrawingCache(true);
        framelayout.buildDrawingCache(true);
        Bitmap saveBm = Bitmap.createBitmap(framelayout.getDrawingCache());
        framelayout.setDrawingCacheEnabled(false);
        OutputStream output;

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();
        // Create a new folder AndroidBegin in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/بوستات واتس اب/");
        dir.mkdirs();
        // Create a name for the saved image
        File file = new File(dir, "Pic" + new Random().nextInt(100000 - 1) + 1 + ".png");
        try {
            // Share Intent
            Intent share = new Intent(Intent.ACTION_SEND);
            // Type of file to share
            share.setType("image/jpeg");
            output = new FileOutputStream(file);
            // Compress into png format image from 0% - 100%
            saveBm.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();
            // Locate the image to Share
            Uri uri = Uri.fromFile(file);
            // Pass the image into an Intnet
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(share, "شارك الصوره عبر"));
            Toast.makeText(this, " تم حفظ الصوره في مجلد 'رسائل ومسجات مطور'", Toast.LENGTH_SHORT).show();
            progress.dismiss();

        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }



}
