package com.eyadalalimi.islamic.pro.nbprayer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by anwar_se on 8/29/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class PrayerUtil {

    public static final int REMAINING_PRAYER_TIME = 15;
    public static final String TIME_FORMATE = "hh:mm aa"; /*05:58 am*/
    public static final int UNKNOWN_LOCATION = -1;

    public static PrayTime getInstance(Context context) {
        PrayTime prayers = new PrayTime();
        prayers.setTimeFormat(prayers.Time12);
        prayers.setCalcMethod(PreferenceUtil.getCalcMethodPref(context));
        prayers.setAsrJuristic(PreferenceUtil.getJuristicMethodPref(context));
        prayers.setAdjustHighLats(prayers.AngleBased);
        prayers.tune(PreferenceUtil.getOffsetsPref(context));
        return prayers;
    }

    public static boolean isLocationPicked(Context ctx) {
        return PreferenceUtil.getLatitudePref(ctx) != UNKNOWN_LOCATION
                && PreferenceUtil.getLongitudePref(ctx) != UNKNOWN_LOCATION;
    }

    public static ArrayList<String> getTimes(Context context) {
        PrayTime prayers = PrayerUtil.getInstance(context);
        double latitude = PreferenceUtil.getLatitudePref(context);
        double longitude = PreferenceUtil.getLongitudePref(context);
        double timezone = PrayTime.getTimeZoneDouble(context);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        return prayers.getPrayerTimes(cal, latitude, longitude, timezone);
    }

    public static Calendar getNextPrayerTime(Context context) {
        ArrayList<String> times = PrayerUtil.getTimes(context);
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMATE, Locale.ENGLISH);
        String format1 = format.format(new Date());
        builder.append("\n").append(format1).append("\n");
        for (int i = 0; i < times.size(); i++) {
            String time = times.get(i);
            builder.append(time).append("    ").append(isPrevious(format, time)).append("    ").append("\n");
            if (isPrevious(format, time) && i != 1) {
                Calendar fixedCalender = getFixedCalender(format, time);
                Log.d("TAG_Log", "getNextPrayerTime: " + builder.append("fixedCalender: ").append(format.format(fixedCalender.getTimeInMillis())).toString());
                return fixedCalender;
            }
        }

        Log.d("TAG_Log", "getNextPrayerTime: " + builder.toString());

        String time = times.get(0);
        return getFixedCalender(format, time, true);
    }

    public static String getNextPrayerName(Context context) {
        int timeIndex = PrayerUtil.getNextPrayerIndex(context);
        return PrayerUtil.getInstance(context).getTimeArNames().get(timeIndex);
    }

    private static int getNextPrayerIndex(Context context) {
        ArrayList<String> times = PrayerUtil.getTimes(context);
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMATE, Locale.ENGLISH);
        for (int i = 0; i < times.size(); i++) {
            String time = times.get(i);
            if (isPrevious(format, time)) {
                return i;
            }
        }
        return 0;
    }

    public static Calendar getNearPrayerTime(Calendar calendar) {
        Calendar nearCalendar = Calendar.getInstance();
        nearCalendar.setTimeInMillis(calendar.getTimeInMillis());
        nearCalendar.add(Calendar.MINUTE, -REMAINING_PRAYER_TIME);
        return nearCalendar;
    }

    public static Pair<Integer, String> getRemainingPrayerTime(Context context) {
        ArrayList<String> times = PrayerUtil.getTimes(context);
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMATE, Locale.ENGLISH);
        String format1 = format.format(new Date());
        builder.append("\n").append(format1).append("\n");
        for (int i = 0; i < times.size(); i++) {
            if (i == 4)
                continue;
            String time = times.get(i);
            builder.append(time).append("    ").append(isPrevious(format, time)).append("    ").append("\n");
            if (isPrevious(format, time)) {
                Calendar fixedCalender = getFixedCalender(format, time);
                Log.d("TAG_Log", "getNextPrayerTime: " + builder.append("fixedCalender: ").append(format.format(fixedCalender.getTimeInMillis())).toString());
                return getRemainingPair(i, getFixedCalender(format, time));
            }
        }
        Log.d("TAG_Log", "getNextPrayerTime: " + builder.toString());
        String time = times.get(0);
        return getRemainingPair(0, getFixedCalender(format, time, true));
    }

    private static Pair<Integer, String> getRemainingPair(int index, Calendar calender) {
        long remaining = System.currentTimeMillis() - calender.getTimeInMillis();

        String remainingStr = milliToHour(remaining) + "-";
        return new Pair<>(index, remainingStr);
    }

    private static String milliToHour(long millis) {
        return String.format(Locale.ENGLISH, "%02d:%02d",
                Math.abs(TimeUnit.MILLISECONDS.toHours(millis)),
                Math.abs(TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))));
    }

    public static boolean isPrevious(SimpleDateFormat format, String time) {
        Calendar fixedCalendar = getFixedCalender(format, time);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        return fixedCalendar.after(currentCalendar);
    }

    public static Calendar getFixedCalender(SimpleDateFormat format, String time) {
        return getFixedCalender(format, time, false);
    }

    public static Calendar getFixedCalender(SimpleDateFormat format, String time
            , boolean addDay) {
        Date parseDate;
        try {
            parseDate = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            parseDate = new Date();
        }

        Calendar parseCalendar = Calendar.getInstance();
        parseCalendar.setTime(parseDate);
        Calendar fixedCalendar = Calendar.getInstance();
        fixedCalendar.set(Calendar.HOUR_OF_DAY, parseCalendar.get(Calendar.HOUR_OF_DAY));
        fixedCalendar.set(Calendar.MINUTE, parseCalendar.get(Calendar.MINUTE));
        fixedCalendar.set(Calendar.SECOND, 0);
        if (addDay)
            fixedCalendar.add(Calendar.DAY_OF_MONTH, 1);
        return fixedCalendar;
    }


    public static void setupNextPrayer(Context context) {
        Calendar nextPrayerTime = PrayerUtil.getNextPrayerTime(context);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(newAlarmIntent(context, PendingIntent.FLAG_CANCEL_CURRENT));
        setAlarm(manager, nextPrayerTime.getTimeInMillis(),
                newAlarmIntent(context, PendingIntent.FLAG_UPDATE_CURRENT));
        Log.d("ReciverNoti", "newCalender: " + nextPrayerTime.getTimeInMillis());
    }


    private static void setAlarm(AlarmManager am, long triggerAtMillis, PendingIntent pendingIntent) {
        int ALARM_TYPE = AlarmManager.RTC_WAKEUP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            am.setExactAndAllowWhileIdle(ALARM_TYPE, triggerAtMillis, pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            am.setExact(ALARM_TYPE, triggerAtMillis, pendingIntent);
        else
            am.set(ALARM_TYPE, triggerAtMillis, pendingIntent);
    }



    private static PendingIntent newAlarmIntent(Context context, int flags) {
        Intent alarmIntent = new Intent(context, PrayerReceiver.class);
        return PendingIntent.getBroadcast
                (context, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE);
    }

}
