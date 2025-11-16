package com.eyadalalimi.islamic.pro.nbprayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeZoneAdapter extends BaseAdapter {
    private String[] timezoneArray;
    private LayoutInflater inflater;

    public TimeZoneAdapter() {
        this.timezoneArray = TimeZone.getAvailableIDs();
    }

    @Override
    public int getCount() {
        return timezoneArray.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(android.R.layout.select_dialog_singlechoice, null);
        TextView names = (TextView) view.findViewById(android.R.id.text1);
        names.setText(displayTimeZone(TimeZone.getTimeZone(timezoneArray[i])));
        return view;
    }

    private String displayTimeZone(TimeZone tz) {
        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        minutes = Math.abs(minutes); // avoid -4:-30 issue
        String result;
        if (hours > 0) {
            result = String.format(Locale.ENGLISH, "(GMT +%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format(Locale.ENGLISH, "(GMT %d:%02d) %s", hours, minutes, tz.getID());
        }
        return result;
    }

    public String getTimeZoneId(int i) {
        return timezoneArray[i];
    }

    public int getTimeZoneIndex(String tzID) {
        int position = 0;
        for (int i = 0; i < timezoneArray.length; i++) {
            if (timezoneArray[i].equals(tzID)) {
                position = i;
                break;
            }
        }
        return position;
    }


}
