package com.eyadalalimi.islamic.pro;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.eyadalalimi.islamic.pro.nbautoazkar.Azkar_Auto_Activity;
import com.eyadalalimi.islamic.pro.nbholyquran.E3rabActivity;
import com.eyadalalimi.islamic.pro.nbholyquran.M3aniActivity;
import com.eyadalalimi.islamic.pro.nbholyquran.MuslimDialog;
import com.eyadalalimi.islamic.pro.nbholyquran.QuranActivity;
import com.eyadalalimi.islamic.pro.nbholyquran.TafsirActivity;
import com.eyadalalimi.islamic.pro.nbmessage.activi.mainactivity;
import com.eyadalalimi.islamic.pro.nbprayer.PrayerActivity;
import com.eyadalalimi.islamic.pro.nbprayer.PrayerUtil;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.DBManager;
import com.eyadalalimi.islamic.pro.other.Arrays;
import com.eyadalalimi.islamic.pro.other.IndexActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class MainActivityFist extends AppCompatActivity implements DialogInterface.OnClickListener {
    String[] mTestArray;
    private final Context context = this;
    public static final String TAG = "Main_Log";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    TextView txt, txt2;
    SharedPreferences preference_shared, text_shared;
    SharedPreferences.Editor edit;
    Menu myMenu;
    LinearLayout ly;
    AdView adView;
    private InterstitialAd mInterstitialAd;  // استخدام الحزمة الجديدة

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apprate.app_launched(this);
        // تهيئة مكتبة الإعلانات دون تمرير appId كسلسلة
        MobileAds.initialize(this);

        // Banner Ad
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // تحميل الإعلان البيني
        loadInterstitial();

        // ربط الأزرار
        CardView azkar = findViewById(R.id.btn_azkar);
        CardView quran = findViewById(R.id.btn_quran);
        CardView btn_quranlire = findViewById(R.id.btn_quranlire);
        CardView reminder = findViewById(R.id.btn_reminder);
        CardView btn_stories = findViewById(R.id.btn_stories);
        CardView btn_subha = findViewById(R.id.btn_subha);
        CardView prayerbtn = findViewById(R.id.prayerbtn);
        CardView qibladirec = findViewById(R.id.qibladirec);
        CardView msgislamic = findViewById(R.id.msgislamic);
        CardView cat_albukhari = findViewById(R.id.cat_albukhari);
        CardView cat_nawawiya_40 = findViewById(R.id.cat_nawawiya_40);
        CardView cat_fiqh = findViewById(R.id.cat_fiqh);
        CardView library = findViewById(R.id.cat_library);
        CardView hijri = findViewById(R.id.cat_hijri);

        // مثال لتحديد موعد الصلاة
        Calendar nextPrayerTime = PrayerUtil.getNextPrayerTime(this);
        String format = DateFormat.getDateTimeInstance().format(nextPrayerTime.getTimeInMillis());
        Log.d(TAG, "onCreate: nextPrayerTime: " + format);

        if (!canDrawOverlays())
            showRequestDrawDialog();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager.canScheduleExactAlarms()) {
                PrayerUtil.setupNextPrayer(context);
            } else {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivity(intent);
            }
        } else {
            PrayerUtil.setupNextPrayer(context);
        }
        
        setupDatabase();

        ly = findViewById(R.id.mainactivity);
        mTestArray = getResources().getStringArray(R.array.HKM_ALYOM);
        txt = findViewById(R.id.dou3a);
        txt2 = findViewById(R.id.do3aalyoum);
        preference_shared = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        text_shared = this.getSharedPreferences("TEXT", MODE_PRIVATE);

        // جزء النصوص اليومية
        if (preference_shared.getBoolean("isFirstRun", true)) {
            txt.setText(mTestArray[0 % mTestArray.length]);
            text_shared.edit().putString("Text", txt.getText().toString()).apply();
            Save_Date();
        } else {
            if (!Objects.equals(preference_shared.getString("Date", ""), dateFormat.format(date))) {
                int idx = new Random().nextInt(mTestArray.length);
                txt.setText(mTestArray[idx]);
                text_shared.edit().putString("Text", txt.getText().toString()).apply();
                Save_Date();
            } else {
                txt.setText(text_shared.getString("Text", ""));
            }
        }
        preference_shared.edit().putBoolean("isFirstRun", false).apply();

        // معالجة الضغط على العناصر
        quran.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityFist.this, qurankarim.class));
            showInterstitial();
        });

        btn_quranlire.setOnClickListener(v -> showQuranDialog());

        reminder.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, Azkar_Auto_Activity.class);
            startActivity(i);
        });

        azkar.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, MyAdkarActivity.class);
            startActivity(i);
        });

        btn_stories.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, Stories.class);
            startActivity(i);
            showInterstitial();
        });

        btn_subha.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, Subha.class);
            startActivity(i);
            showInterstitial();
        });

        prayerbtn.setOnClickListener(v -> startActivity(new Intent(MainActivityFist.this, PrayerActivity.class)));

        qibladirec.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, CompassActivity.class);
            startActivity(i);
        });

        msgislamic.setOnClickListener(v -> {
            Intent i = new Intent(MainActivityFist.this, mainactivity.class);
            startActivity(i);
        });

        cat_albukhari.setOnClickListener(v -> {
            IndexActivity.newIntent(MainActivityFist.this, R.id.cat_albukhari, getString(R.string.cat_albukhari), R.drawable.cat_albukhari_color);
            showInterstitial();
        });

        cat_nawawiya_40.setOnClickListener(v -> {
            IndexActivity.newIntent(MainActivityFist.this, R.id.cat_nawawiya_40, getString(R.string.cat_nawawiya_40), R.drawable.cat_nawawiya_color);
            showInterstitial();
        });

        hijri.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityFist.this, HijriActivity.class));
            showInterstitial();
        });

        library.setOnClickListener(v -> {
            IndexActivity.newIntent(MainActivityFist.this, R.id.cat_library, getString(R.string.cat_library), R.drawable.cat_library_color);
            // لا نعرض إعلان هنا لضبط تجربة المستخدم
        });

        cat_fiqh.setOnClickListener(v -> {
            IndexActivity.newIntent(MainActivityFist.this, R.id.cat_fiqh, getString(R.string.cat_fiqh), R.drawable.cat_fiqh_color);
            showInterstitial();
        });
    }

    // تحميل الإعلان البيني
    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                getString(R.string.intersianal_ad_unit_id),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        mInterstitialAd = null;
                    }
                });
    }

    // عرض الإعلان إذا كان جاهزًا
    private void showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
            mInterstitialAd = null;
            loadInterstitial(); // تحميل إعلان جديد
        }
    }

    private void setupDatabase() {
        new Thread(() -> DBManager.getInstance(context)).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.firstmenu, menu);
        myMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            startActivity(new Intent(this, Sellings.class));
        }
        return super.onOptionsItemSelected(item);
    }

    // تحويل جملة switch إلى if-else لتفادي خطأ constant expression
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == R.id.cat_quran_item_read) {
            startActivity(new Intent(this, QuranActivity.class));
        } else if (which == R.id.cat_quran_item_tafsir) {
            startActivity(new Intent(this, TafsirActivity.class));
        } else if (which == R.id.cat_quran_item_e3rab) {
            startActivity(new Intent(this, E3rabActivity.class));
        } else if (which == R.id.cat_quran_item_m3ani) {
            startActivity(new Intent(this, M3aniActivity.class));
        }
        dialog.dismiss();
    }

    public void Save_Date() {
        preference_shared = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        edit = preference_shared.edit();
        edit.clear();
        edit.putString("Date", dateFormat.format(date));
        edit.apply();
    }


@Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(MainActivityFist.this);
        dialog.setContentView(R.layout.quit_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setTitle("تنبيه");
        dialog.getWindow().setTitleColor(Color.MAGENTA);
        TextView text = dialog.findViewById(R.id.text);
        text.setText("هل تريد الخروج من التطبيق");
        dialog.show();
        Button yesbutton = dialog.findViewById(R.id.yes_bt);
        // if button is clicked, close the custom dialog
        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityFist.super.onBackPressed();
                dialog.dismiss();
            }
        });


        Button nobutton = dialog.findViewById(R.id.no_bt);
        // if button is clicked, close the custom dialog
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showQuranDialog() {
        MuslimDialog muslimDialog = new MuslimDialog(this);
        muslimDialog.setCategories(Arrays.getQuranItemList());
        muslimDialog.setListener(this);
        muslimDialog.show();
    }

    private boolean canDrawOverlays() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(this);
    }

    private boolean requestDrawOverlays() {
        if (!canDrawOverlays()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
            return false;
        } else
            return true;
    }

    private void showRequestDrawDialog() {
        final Dialog dialog = new Dialog(MainActivityFist.this);
        dialog.setContentView(R.layout.quit_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setTitle("تنبيه");
        dialog.getWindow().setTitleColor(Color.MAGENTA);
        TextView textTitle = dialog.findViewById(R.id.text25);
        textTitle.setText("تنبيه");
        TextView text = dialog.findViewById(R.id.text);
        text.setText(R.string.request_draw_message);
        dialog.show();
        Button yesbutton = dialog.findViewById(R.id.yes_bt);
        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDrawOverlays();
                dialog.dismiss();
            }
        });
        Button nobutton = dialog.findViewById(R.id.no_bt);
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
