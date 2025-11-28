package com.eyad.alalimi.sonya7rvreview.nbprayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbprayer.preferences.SeekBarPreference;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class BasePrefFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setPreferenceSummary(sharedPreferences, findPreference(key));
    }

    protected void setPreferenceSummary(SharedPreferences sharedPreferences, Preference preference) {
        if (preference == null)
            return;

        if (preference instanceof ListPreference) {
            String value = sharedPreferences.getString(preference.getKey(), "");
            // For list preferences, figure out the label of the selected value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0) {
                // Set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else if (preference instanceof SeekBarPreference) {
            int value = sharedPreferences.getInt(preference.getKey(), 0);
            setSeekSummary(preference, value);
        } else if (preference.getKey().equals(getString(R.string.pref_city_key))) {
            preference.setSummary(PreferenceUtil.getTimezonePref(getActivity()));
        } else {
            String value = sharedPreferences.getString(preference.getKey(), "");
            if (!TextUtils.isEmpty(value))
                preference.setSummary(value);
        }
    }

    protected void replaceFragment() {
        if (getActivity() instanceof PrayerSettingsActivity) {
            ((PrayerSettingsActivity) getActivity()).replaceFragment(new OffsetFragment(), true);
        }
    }

    protected void setSeekSummary(Preference preference, int parseInt) {
        if (parseInt == 0) {
            preference.setSummary(String.valueOf(0));
        } else {
            DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.FRENCH);
            decimalFormat.applyLocalizedPattern("+#; -#");
            preference.setSummary(decimalFormat.format(parseInt));
        }
    }

}
