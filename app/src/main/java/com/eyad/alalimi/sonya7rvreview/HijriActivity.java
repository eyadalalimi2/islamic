package com.eyad.alalimi.sonya7rvreview;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eyad.alalimi.sonya7rvreview.nbholyquran.StringArrayAdapter;
import com.eyad.alalimi.sonya7rvreview.other.HijriDate;
import com.eyad.alalimi.sonya7rvreview.other.PreferenceUtil;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anwar_se on 6/30/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class HijriActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etCeYear;
    Spinner etCeMonth;
    Spinner etCeDay;
    TextView tvAhDate;
    StringArrayAdapter daysAdapter;
    List<String> daysList;
    int year = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijri);

        etCeYear = findViewById(R.id.et_ce_year);
        etCeMonth = findViewById(R.id.et_ce_month);
        etCeDay = findViewById(R.id.et_ce_day);
        tvAhDate = findViewById(R.id.tv_ah_date);
        findViewById(R.id.btn_change).setOnClickListener(this);
        change2Hijri(Calendar.getInstance());

        Calendar currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR);
        etCeYear.setText(String.valueOf(year));

        etCeMonth.setAdapter(new StringArrayAdapter(this, getNumList(12)));
        etCeMonth.setSelection(currentDate.get(Calendar.MONTH));
        etCeMonth.setOnItemSelectedListener(this);

        // int maximumDays = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        int maximumDays = HijriDate.getDaysInMonth(currentDate.get(Calendar.MONTH) + 1, year);
        daysList = getNumList(maximumDays);
        daysAdapter = new StringArrayAdapter(this, daysList);
        etCeDay.setAdapter(daysAdapter);
        etCeDay.setSelection(currentDate.get(Calendar.DAY_OF_MONTH) - 1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_change) {
            Calendar calender = getSelectedCalender();
            if (calender != null)
                change2Hijri(calender);
        } else if (id == R.id.ib_days_up) {
            PreferenceUtil.setHijriAdjustPref(this, PreferenceUtil.getHijriAdjustPref(this) + 1);
            Calendar calenderUp = getSelectedCalender();
            if (calenderUp != null)
                change2Hijri(calenderUp);
        } else if (id == R.id.ib_days_down) {
            PreferenceUtil.setHijriAdjustPref(this, PreferenceUtil.getHijriAdjustPref(this) - 1);
            Calendar calenderDown = getSelectedCalender();
            if (calenderDown != null)
                change2Hijri(calenderDown);
        }
    }

    private Calendar getSelectedCalender() {
        String yearString = etCeYear.getText().toString();
        String monthString = String.valueOf(etCeMonth.getSelectedItemPosition());
        String dayString = String.valueOf(etCeDay.getSelectedItemPosition() + 1);
        if (!TextUtils.isEmpty(yearString)
                && !TextUtils.isEmpty(monthString)
                && !TextUtils.isEmpty(dayString)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.YEAR, Integer.parseInt(yearString));
            calendar.set(Calendar.MONTH, Integer.parseInt(monthString));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayString));
            return calendar;
        }
        return null;
    }

    private void change2Hijri(Calendar date) {
        String format = DateFormat.getDateInstance().format(new Date(date.getTimeInMillis()));
        Log.d("HijriDate_Log", "change2Hijri: " + format);
        // String islamicDate = HijriDate.getHijriDate(this, date);
        // String islamicDate = HijriDate.writeIslamicDate(this, date);
        String islamicDate = HijriDate.convertToHijriDate(this, date);
        // String islamicDate = HijriDate.addday(this, date);

        tvAhDate.setText(islamicDate);
    }

    private List<String> getNumList(int num) {
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            numbers.add(String.valueOf(i));
        }
        return numbers;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, position);
        int maximumDays = HijriDate.getDaysInMonth(position + 1, year);

        Log.d("TAG", "onItemClick: " + position + " maximumDays => " + maximumDays);
        daysList = getNumList(maximumDays);
        daysAdapter.notifyItems(daysList);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
