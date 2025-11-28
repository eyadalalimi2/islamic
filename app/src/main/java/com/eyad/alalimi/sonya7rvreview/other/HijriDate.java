package com.eyad.alalimi.sonya7rvreview.other;

import android.content.Context;
import android.util.Log;

import com.eyad.alalimi.sonya7rvreview.R;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

import java.text.SimpleDateFormat;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by anwar_se on 6/30/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class HijriDate {

    private static final String TAG = "HijriDate_Log";

    private static double gmod(double n, double m) {
        return ((n % m) + m) % m;
    }

    private static int[] kuwaiticalendarInt(Calendar today, boolean adjust) {
        double[] iDate = kuwaiticalendar(today, adjust);
        int[] iDateInt = new int[iDate.length];
        iDateInt[0] = (int) iDate[0];
        iDateInt[1] = (int) iDate[1];
        iDateInt[2] = (int) iDate[2];
        iDateInt[3] = (int) iDate[3];
        iDateInt[4] = (int) iDate[4];
        iDateInt[5] = (int) iDate[5];
        iDateInt[6] = (int) iDate[6];
        iDateInt[7] = (int) iDate[7];
        return iDateInt;

    }

    private static double[] kuwaiticalendar(Calendar today, boolean adjust) {
        int adj;
        if (adjust) {
            adj = 0;
        } else {
            adj = 1;
        }

        if (adjust) {
            int adjustmili = 1000 * 60 * 60 * 24 * adj;
            long todaymili = today.getTimeInMillis() + adjustmili;
            today.setTimeInMillis(todaymili);
        }
        double day = today.get(Calendar.DAY_OF_MONTH);
        double month = today.get(Calendar.MONTH);
        double year = today.get(Calendar.YEAR);

        double m = month + 1;
        double y = year;
        if (m < 3) {
            y -= 1;
            m += 12;
        }

        double a = Math.floor(y / 100.);
        double b = 2 - a + Math.floor(a / 4.);

        if (y < 1583)
            b = 0;
        if (y == 1582) {
            if (m > 10)
                b = -10;
            if (m == 10) {
                b = 0;
                if (day > 4)
                    b = -10;
            }
        }

        double jd = Math.floor(365.25 * (y + 4716)) + Math.floor(30.6001 * (m + 1)) + day
                + b - 1524;

        b = 0;
        if (jd > 2299160) {
            a = Math.floor((jd - 1867216.25) / 36524.25);
            b = 1 + a - Math.floor(a / 4.);
        }
        double bb = jd + b + 1524;
        double cc = Math.floor((bb - 122.1) / 365.25);
        double dd = Math.floor(365.25 * cc);
        double ee = Math.floor((bb - dd) / 30.6001);
        day = (bb - dd) - Math.floor(30.6001 * ee);
        month = ee - 1;
        if (ee > 13) {
            cc += 1;
            month = ee - 13;
        }
        year = cc - 4716;

        double wd = gmod(jd + 1, 7) + 1;

        double iyear = 10631. / 30.;
        double epochastro = 1948084;
        double epochcivil = 1948085;

        double shift1 = 8.01 / 60.;

        double z = jd - epochastro;
        double cyc = Math.floor(z / 10631.);
        z = z - 10631 * cyc;
        double j = Math.floor((z - shift1) / iyear);
        double iy = 30 * cyc + j;
        z = z - Math.floor(j * iyear + shift1);
        double im = Math.floor((z + 28.5001) / 29.5);
        if (im == 13)
            im = 12;
        double id = z - Math.floor(29.5001 * im - 29);

        double[] myRes = new double[8];

        myRes[0] = day; // calculated day (CE)
        myRes[1] = month - 1; // calculated month (CE)
        myRes[2] = year; // calculated year (CE)
        myRes[3] = jd - 1; // julian day number
        myRes[4] = wd - 1; // weekday number
        myRes[5] = id; // islamic date
        myRes[6] = im - 1; // islamic month
        myRes[7] = iy; // islamic year

        return myRes;
    }

    public static String writeIslamicDate(Context context, Calendar today, long oldDate) {
        String[] wdNames = context.getResources().getStringArray(R.array.wd_names);
        String[] iMonthNames = context.getResources().getStringArray(R.array.ah_name);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(oldDate);
        // This Value is used to give the correct day +- 1 day
        boolean dayTest = true;
        int[] iDate = kuwaiticalendarInt(today, dayTest);
        for (int i = 0; i < iDate.length; i++) {
            Log.d(TAG, "writeIslamicDate: iDate" + i + ": " + iDate[i] + "  ");
        }
        String outputIslamicDate = wdNames[calendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + (iDate[5]) + " "
                + iMonthNames[iDate[6]] + " " + iDate[7];

        return outputIslamicDate;
    }

    public static String convertToHijriDate(Context context, Calendar cl) {
        try {
            Date oldDate = new Date(cl.getTimeInMillis());
            cl.add(Calendar.DAY_OF_MONTH, PreferenceUtil.getHijriAdjustPref(context));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String day = new SimpleDateFormat("EEEE", new Locale("ar")).format(oldDate);
                HijrahDate islamyDate = HijrahChronology.INSTANCE.date(java.time.LocalDate.of(cl.get(Calendar.YEAR),
                        cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
                String dateStr = day + " , "
                        + DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ar")).format(islamyDate);
                Log.d(TAG, "onCreate: " + dateStr);
                return dateStr;
            } else
                return writeIslamicDate(context, cl, oldDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getHijriDate(Context context, Calendar calendar) {
        String[] wdNames = context.getResources().getStringArray(R.array.wd_names);
        String[] iMonthNames = context.getResources().getStringArray(R.array.ah_name);
        calendar.setTimeInMillis(System.currentTimeMillis());
        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();
        // LocalDate todayIso = new LocalDate(calendar.get(Calendar.YEAR),
        // calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), iso);
        LocalDate todayIso = new LocalDate(calendar.getTimeInMillis(), iso);

        LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(), hijri);
        // LocalDate todayHijri =
        // LocalDate.fromCalendarFields(calendar.getTimeInMillis(), hijri);
        Log.d(TAG, "onCreate:todayHijri " + todayIso);
        String outputIslamicDate = wdNames[todayHijri.getDayOfWeek() - 1] + ", " + // اسم اليوم
                todayHijri.getDayOfMonth() + " " + // تاريخ اليوم
                iMonthNames[todayHijri.getMonthOfYear() - 1] + " " + // الشهر
                todayHijri.getYear() // السنة
                + " هجري";
        Log.d(TAG, "onCreate:todayHijri " + outputIslamicDate); // هجري
        return outputIslamicDate;
    }

    public static int getDaysInMonth(int month, int year) {
        int daysInMonth;
        switch (month) {
            case 2:
                if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;
                break;
            case 12:
            case 10:
            case 8:
            case 7:
            case 5:
            case 3:
            case 1:
            default:
                daysInMonth = 31;
        }
        return daysInMonth;
    }

}