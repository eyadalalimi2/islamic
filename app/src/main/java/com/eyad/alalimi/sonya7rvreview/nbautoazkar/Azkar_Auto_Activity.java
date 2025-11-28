package com.eyad.alalimi.sonya7rvreview.nbautoazkar;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbprayer.PreferenceUtil;
import com.eyad.alalimi.sonya7rvreview.other.ActivityUtils;

/**
 * Created by anwar_se on 8/1/2019
 * Email: anwar.dev.96@gmail.com.
 */

public class Azkar_Auto_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_azkar);
        ActivityUtils.setupToolbar(this, R.string.cat_remind);

        RadioGroup radioGroup = findViewById(R.id.rdg_time);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                PreferenceUtil.setRemindPref(Azkar_Auto_Activity.this, checkedId);
                UtilNotif.setupRepeatedNotifications(Azkar_Auto_Activity.this, checkedId);
            }
        });
        int remindPref = PreferenceUtil.getRemindPref(Azkar_Auto_Activity.this);
        radioGroup.check(remindPref);

        CheckBox athanSoundCheck = findViewById(R.id.cbx_athan_sound);
        athanSoundCheck.setChecked(PreferenceUtil.isAthanPref(this));
        athanSoundCheck.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.cbx_athan_sound) {
            Toast.makeText(this, "Checked: " + isChecked, Toast.LENGTH_SHORT).show();
            PreferenceUtil.setAthanPref(this, isChecked);
        }
    }
}
