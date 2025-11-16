package com.eyadalalimi.islamic.pro.nbprayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.eyadalalimi.islamic.pro.R;

import java.util.TimeZone;


/**
 * Created by anwar_se on 7/11/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class PreferenceUtil {



    private static final String PREF_MARK = "com.eyadalalimi.islamic.pro.PREF_MARK";
    private static final String PREF_LAST_PAGE = "com.eyadalalimi.islamic.pro.PREF_LAST_PAGE";
    private static final String PREF_LATITUDE = "com.eyadalalimi.islamic.pro.PREF_LATITUDE";
    private static final String PREF_LONGITUDE = "com.eyadalalimi.islamic.pro.PREF_LONGITUDE";
    private static final String PREF_TIMEZONE = "com.eyadalalimi.islamic.pro.PREF_TIMEZONE";
    private static final String PREF_REMIND = "com.eyadalalimi.islamic.pro.PREF_REMIND";
    private static final String PREF_ATHAN = "com.eyadalalimi.islamic.pro.PREF_ATHAN";

    //endregion

    //region Preference
    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }
    //endregion

    //region Quran Marks
    public static void setMarkPref(Context context, int position) {
        getEditor(context).putInt(PREF_MARK, position).apply();
    }

    public static int getMarkPref(Context context) {
        return getPreferences(context).getInt(PREF_MARK, 0);
    }

    public static void setLastPagePref(Context context, int position) {
        getEditor(context).putInt(PREF_LAST_PAGE, position).apply();
    }

    public static int getLastPagePref(Context context) {
        return getPreferences(context).getInt(PREF_LAST_PAGE, 0);
    }
    //endregion

    //region Location

    public static void setLatitudePref(Context context, double latitude) {
        getEditor(context).putString(PREF_LATITUDE, String.valueOf(latitude)).apply();
    }

    public static double getLatitudePref(Context context) {
        String latString = getPreferences(context).getString(PREF_LATITUDE, "0");
        return (latString == null || latString.length() == 0) ? 0 : Double.valueOf(latString);
    }

    public static void setLongitudePref(Context context, double longitude) {
        getEditor(context).putString(PREF_LONGITUDE, String.valueOf(longitude)).apply();
    }

    public static double getLongitudePref(Context context) {
        String latString = getPreferences(context).getString(PREF_LONGITUDE, "0");
        return (latString == null || latString.length() == 0) ? 0 : Double.valueOf(latString);
    }
    public static void setTimezonePref(Context context, String timezone) {
        getEditor(context).putString(context.getString(R.string.pref_city_key), String.valueOf(timezone)).apply();
    }

    public static String getTimezonePref(Context context) {
        return getPreferences(context).getString(context.getString(R.string.pref_city_key), TimeZone.getDefault().getID());
    }


    public static void setRemindPref(Context context, int timezone) {
        getEditor(context).putString(PREF_REMIND, String.valueOf(timezone)).apply();
    }

    public static int getRemindPref(Context context) {
        String timeString = getPreferences(context).getString(PREF_REMIND, "0");
        return (timeString == null || timeString.length() == 0) ? R.id.rdb_1 : Integer.valueOf(timeString);
    }

    public static void setAthanPref(Context context, boolean enabled) {
        getEditor(context).putBoolean(PREF_ATHAN, enabled).apply();
    }

    public static boolean isAthanPref(Context context) {
        return getPreferences(context).getBoolean(PREF_ATHAN, false);
    }

    public static int getCalcMethodPref(Context context) {
        String method = getPreferences(context).getString
                (context.getString(R.string.pref_organization_key),
                        context.getString(R.string.default_organization));
        return (method == null || TextUtils.isEmpty(method)) ? 0 : Integer.parseInt(method);
    }

    public static int getJuristicMethodPref(Context context) {
        return Integer.parseInt(getPreferences(context).getString(context.getString(R.string.pref_asr_madhab_key), "0"));
    }

    public static int[] getOffsetsPref(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.pref_offsets_settings_key_array);
        int[] offsets = {0, 0, 0, 0, 0, 0, 0};
        StringBuilder offsetsStr = new StringBuilder();
        SharedPreferences pref = getPreferences(context);
        for (int i = 0; i < stringArray.length; i++) {
            String key = stringArray[i];
            offsets[i] = pref.getInt(key, 0);
            offsetsStr.append(offsets[i]).append("\n");
        }
        Log.i("PrayerUtil_Log", "getInstance: " + offsetsStr.toString());
        return offsets;
    }
    //endregion

    //region Auto Azkar

    //endregion

    //region Athan




    //endregion

        }