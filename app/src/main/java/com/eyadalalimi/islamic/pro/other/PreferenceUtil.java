package com.eyadalalimi.islamic.pro.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by anwar_se on 7/11/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class PreferenceUtil {


    private static final String PREF_HIJRI_ADJUST_DAYS = "com.eyadalalimi.islamic.pro.PREF_HIJRI_ADJUST_DAYS";

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

    //endregion

    //region Location





    public static void setHijriAdjustPref(Context context, int days) {
        getEditor(context).putInt(PREF_HIJRI_ADJUST_DAYS, days).apply();
    }

    public static int getHijriAdjustPref(Context context) {
        return getPreferences(context).getInt(PREF_HIJRI_ADJUST_DAYS, 0);
    }

        }