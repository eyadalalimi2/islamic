package com.eyadalalimi.islamic.pro.nbprayer;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.other.ActivityUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by anwar_se on 7/5/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class PrayerActivity extends AppCompatActivity {

    //region Constants
    public static final String TAG = "PrayerActivity_Log";
    public static final int ACCESS_COURSE_LOCATION_CODE = 105;
    //endregion
    //region Variables
    private AppCompatActivity activity = this;
    private GPSTracker gps;
    private double latitude;
    private double longitude;
    private double timezone;
    private boolean isFromPermissions;
    //endregion
    TextView tvDate;
    Intent intent;
    String packageName;
    //region Override
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);
        ActivityUtils.setupToolbar(this, R.string.cat_prayer_times);
        gps = new GPSTracker(this);
     tvDate = findViewById(R.id.tv_date);
       tvDate.setText("مواقيت الصلاة");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent = new Intent();
            packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(PrayerActivity.this);
                builder1.setMessage("ليشتغل معك الاذان أو الاشعارات التلقائية بدون اي مشاكل تاكد انك جعلت التطبيق يشتغل في الخلفية لذا يرجى تفعيل هذه الخاصية لكي لاتواجهك اي مشاكل");
                builder1.setTitle("ملاحظة");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "حسنا",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                                intent.setData(Uri.parse("package:" + packageName));
                                startActivity(intent);
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isFromPermissions)
            isFromPermissions = false;
        else
            getLocation(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gps, menu);
        menu.findItem(R.id.action_settings).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gps:
                getLocation(true, true);
                Toast.makeText(this, "لقد تم تحديث موقعك و مواقيت الصلاة", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, PrayerSettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ACCESS_COURSE_LOCATION_CODE) {
            isFromPermissions = true;
            getLocation(false);
        }
    }
    //endregion

    private void requestLocation(boolean withRequest) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (withRequest) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_COURSE_LOCATION_CODE);
            } else
                Toast.makeText(activity, "Location must be enabled", Toast.LENGTH_SHORT).show();

        } else {
            getCurrentLocation(withRequest);
        }
    }

    private void getCurrentLocation(boolean withRequest) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();*/
        if (gps.canGetLocation()) {
//            gps.getLocation();
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            timezone = PrayTime.getDefaultTimeZoneDouble();
            PreferenceUtil.setLatitudePref(activity, latitude);
            PreferenceUtil.setLongitudePref(activity, longitude);
            PreferenceUtil.setTimezonePref(activity, TimeZone.getDefault().getID());
            initTimes();
            Log.d(TAG, "onCreate GPSTracker: latitude: " + latitude + " longitude: " + longitude);
        } else if (latitude == PrayerUtil.UNKNOWN_LOCATION || withRequest) {
            gps.showSettingsAlert(activity);
        }
    }

    private void getLocation(boolean withRequest) {
        getLocation(withRequest, false);
    }

    private void getLocation(boolean withRequest, boolean force) {
        latitude = PreferenceUtil.getLatitudePref(this);
        longitude = PreferenceUtil.getLongitudePref(this);
//        timezone = PreferenceUtil.getTimezonePref(this);
        timezone = PrayTime.getTimeZoneDouble(this);


        if (latitude == PrayerUtil.UNKNOWN_LOCATION || longitude == PrayerUtil.UNKNOWN_LOCATION || force) {
            gps.getLocation();
            requestLocation(withRequest);
        } else
            initTimes();

    }

    public void initTimes() {
        PrayTime prayers = PrayerUtil.getInstance(this);
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        ArrayList<TextView> prayerViews = getPrayerViews();
        ArrayList<String> prayerTimes = prayers.getPrayerTimes(cal, latitude, longitude, timezone);
        ArrayList<String> prayerNames = prayers.getTimeNames();
        for (int i = 0; i < prayerTimes.size(); i++) {
            if (prayerViews.get(i) != null)
                prayerViews.get(i).setText(prayerTimes.get(i));
            Log.d(TAG, "prayerName: " + prayerNames.get(i) + " - " + prayerTimes.get(i));
        }
        ArrayList<TextView> labelViews = getPrayerLabelViews();
        Pair<Integer, String> remainingTime = PrayerUtil.getRemainingPrayerTime(this);
        String nextPrayer = new StringBuilder()
                .append(getString(getPrayerLabels().get(remainingTime.first)))
                .append(" ")
                .append(" ")
                .append(" ")
                .append(" ")
                .append(remainingTime.second)
                .toString();
        if (labelViews.get(remainingTime.first) != null)
            labelViews.get(remainingTime.first).setText(nextPrayer);

    }

    private ArrayList<TextView> getPrayerViews() {
        ArrayList<TextView> views = new ArrayList<>();
        views.add((TextView) findViewById(R.id.tv_prayer_fajr));
        views.add((TextView) findViewById(R.id.tv_prayer_sunrise));
        views.add((TextView) findViewById(R.id.tv_prayer_dhuhr));
        views.add((TextView) findViewById(R.id.tv_prayer_asr));
        views.add(null);
        views.add((TextView) findViewById(R.id.tv_prayer_maghribt));
        views.add((TextView) findViewById(R.id.tv_prayer_isha));
        return views;
    }

    private ArrayList<TextView> getPrayerLabelViews() {
        ArrayList<TextView> views = new ArrayList<>();
        views.add((TextView) findViewById(R.id.tv_prayer_fajr_label));
        views.add((TextView) findViewById(R.id.tv_prayer_sunrise_label));
        views.add((TextView) findViewById(R.id.tv_prayer_dhuhr_label));
        views.add((TextView) findViewById(R.id.tv_prayer_asr_label));
        views.add(null);
        views.add((TextView) findViewById(R.id.tv_prayer_maghribt_label));
        views.add((TextView) findViewById(R.id.tv_prayer_isha_label));
        return views;
    }

    private ArrayList<Integer> getPrayerLabels() {
        ArrayList<Integer> views = new ArrayList<>();
        views.add(R.string.prayer_fajr);
        views.add(R.string.prayer_sunrise);
        views.add(R.string.prayer_dhuhr);
        views.add(R.string.prayer_asr);
        views.add(R.string.prayer_maghribt);
        views.add(R.string.prayer_maghribt);
        views.add(R.string.prayer_isha);
        return views;
    }


}
