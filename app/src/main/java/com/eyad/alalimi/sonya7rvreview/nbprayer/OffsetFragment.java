package com.eyad.alalimi.sonya7rvreview.nbprayer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.preference.Preference;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbprayer.preferences.PrayerOffsetPreferenceDialogFragment;

import java.util.ArrayList;

public class OffsetFragment extends BasePrefFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_offsets, rootKey);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_fajr_time_offset_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_sunrise_time_offset_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_duhr_time_offset_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_asr_time_offset_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_maghrib_time_offset_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_ishaa_time_offset_key)));
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        String key = preference.getKey();
        if (key.contains(getString(R.string.pref_time_offset_key))) {
            ArrayList<String> prayers = PrayerUtil.getTimes(getContext());
            Log.d("TAG_Log", "onDisplayPreferenceDialog: " + prayers.toString());
            PrayerOffsetPreferenceDialogFragment prayerOffsetPreferenceDialogFragment = null;
            switch (Integer.parseInt(key.substring(preference.getKey().length() - 1))) {
                case 0:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(0));
                    break;
                case 1:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(1));
                    break;
                case 2:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(2));
                    break;
                case 3:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(3));
                    break;
                case 4:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(5));
                    break;
                case 5:
                    prayerOffsetPreferenceDialogFragment = PrayerOffsetPreferenceDialogFragment.newInstance(key,
                            prayers.get(6));
                    break;
            }
            prayerOffsetPreferenceDialogFragment.setTargetFragment(this, 0);
            prayerOffsetPreferenceDialogFragment.show(getFragmentManager(),
                    "android.support.v7.preference.PreferenceFragment.DIALOG");
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }
}
