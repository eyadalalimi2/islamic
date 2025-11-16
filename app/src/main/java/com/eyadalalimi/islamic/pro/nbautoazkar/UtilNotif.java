package com.eyadalalimi.islamic.pro.nbautoazkar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.eyadalalimi.islamic.pro.MainActivityFist;
import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbprayer.AssetsProvider;
import com.eyadalalimi.islamic.pro.nbprayer.PreferenceUtil;

import java.io.File;
import java.util.Calendar;



/**
 * Created by anwar_se on 7/27/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class UtilNotif {
    private static final String ANDROID_CHANNEL_ID = "com.eyadalalimi.islamic.pro.ANDROID";
    private static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    private static final String ANDROID_ATHAN_CHANNEL_ID = "com.eyadalalimi.islamic.pro.ANDROID_ATHAN_CHANNEL_ID";
    private static final String ANDROID_ATHAN_CHANNEL_NAME = "ATHAN CHANNEL";
    private static final String PREF_FIRST_OPEN = "com.eyadalalimi.islamic.pro.PREF_FIRST_OPEN";


    public static void setupRepeatedNotifications(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.getBoolean(PREF_FIRST_OPEN, false)) {
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.cancel(newAlarmIntent(context, PendingIntent.FLAG_CANCEL_CURRENT));
            Calendar calendar = newCalender();
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_HOUR, newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
            Log.d("ReciverNoti", "newCalender: " + calendar.getTimeInMillis());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(PREF_FIRST_OPEN, true);
            editor.apply();
        }
    }

    public static void setupRepeatedNotifications(Context context, int checkedId) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(newAlarmIntent(context, PendingIntent.FLAG_CANCEL_CURRENT));
        Calendar calendar = newCalender();

        Log.d("ReciverNoti", "newCalender: " + calendar.getTimeInMillis());
        // ✅ تم تحويل switch إلى if‑else لتفادي خطأ constant expression
        if (checkedId == R.id.rdb_0) {
            // بدون تكرار
        } else if (checkedId == R.id.rdb_30) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_HALF_HOUR, newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
        } else if (checkedId == R.id.rdb_1) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_HOUR, newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
        } else if (checkedId == R.id.rdb_3) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_HOUR * 3, newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
        } else if (checkedId == R.id.rdb_6) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_HOUR * 6, newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
        }
    }

    private static Calendar newCalender() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    private static PendingIntent newAlarmIntent(Context context, int flags) {
        Intent alarmIntent = new Intent(context, ReciverNoti.class);
        return PendingIntent.getBroadcast
                (context, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
    }

   /* public static void showNotification(Context context, String message) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, newNotification(context, message, Firstt.class).build());
    }*/

    public static Uri getRawUri(Context context, String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + context.getPackageName() + "/raw/" + filename);
    }

    public static void showNotification(Context context, String message) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels(manager);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, ANDROID_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(message);

        Intent notificationIntent = new Intent(context, MainActivityFist.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contentIntent);

        // Add as notification
        manager.notify(0, builder.build());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createChannels(NotificationManager manager) {

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        manager.createNotificationChannel(androidChannel);

    }

    public static void newAthanNotification(Context context) {
        String message = "حان الآن ميقات الصلاة";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createAthanChannels(manager, context);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, ANDROID_ATHAN_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(message);

        if (PreferenceUtil.isAthanPref(context)) {
            Uri soundUri = AssetsProvider.getAssetsUri("athan.mp3");
            builder.setSound(soundUri);
        }

        Intent notificationIntent = new Intent(context, MainActivityFist.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        manager.notify(0, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createAthanChannels(NotificationManager manager, Context context) {

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_ATHAN_CHANNEL_ID,
                ANDROID_ATHAN_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        if (PreferenceUtil.isAthanPref(context)) {
            Uri soundUri = AssetsProvider.getAssetsUri("athan.mp3");
            androidChannel.setSound(soundUri, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        }
        manager.createNotificationChannel(androidChannel);

    }

    /*
        UtilNotif.newAthanNotification(context);
        AssetsProvider.shareFile(context, "s");
        Uri assetsUri = AssetsProvider.getAssetsUri("athan.mp3");
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(context, assetsUri);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    */

}
