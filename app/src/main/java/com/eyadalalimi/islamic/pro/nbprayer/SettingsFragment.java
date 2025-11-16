package com.eyadalalimi.islamic.pro.nbprayer;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;

import com.eyadalalimi.islamic.pro.R;


public class SettingsFragment extends BasePrefFragment implements Preference.OnPreferenceClickListener {

    private static final String TAG = "SettingsFragment_Log";
    private TimeZoneAdapter zoneAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_prayer, rootKey);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_city_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_organization_key)));
        setPreferenceSummary(sharedPreferences, findPreference(getString(R.string.pref_asr_madhab_key)));
        findPreference(getString(R.string.pref_city_key)).setOnPreferenceClickListener(this);
        findPreference(getString(R.string.pref_offsets_settings_key)).setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.pref_city_key))) {
            showCityDialog();
        } else if (preference.getKey().equals(getString(R.string.pref_offsets_settings_key))) {
            Log.d(TAG, "onPreferenceClick: ");
            replaceFragment();
        }
        return false;
    }

    private void showCityDialog() {
        if (zoneAdapter == null)
            zoneAdapter = new TimeZoneAdapter();
        new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(zoneAdapter
                        , zoneAdapter.getTimeZoneIndex(PreferenceUtil.getTimezonePref(getActivity()))
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                String selectedID = zoneAdapter.getTimeZoneId(selectedPosition);
                                Log.d(TAG, "onClick: " + selectedID);
/*
                        sharedPreferences.edit()
                                .putString(getString(R.string.pref_city_key), selectedID)
                                .apply();
*/
                                PreferenceUtil.setTimezonePref(getActivity(), selectedID);
                            }
                        })
                .setPositiveButton(android.R.string.cancel, null)
                .show();
    }
}
