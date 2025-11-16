package com.eyadalalimi.islamic.pro.nbprayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class PrayerSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new SettingsFragment(), false);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(PrayerSettingsActivity.this);
        builder1.setMessage("لتعديل مواقيت الصلاة بدويا \n" +
                "فالامر بسيط فقط اختار في الاولى توقيت بلدك ثم اختار طريقة الحساب الذي يعتمدها بلدك (يمكنك تجريبهم كلهم لترى وأنظر ايهم أقرب أو صحيحة حسب توقيتكم )\n " +
                "واذا لم يضبط عند استخدام طريقة الحساب عدل عليه من القسم الاخير أضف أو انقص دقائق حتى يتوافق معكم");
        builder1.setTitle("ملاحظة");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "حسنا",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void replaceFragment(Fragment fragment, boolean back) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment);
        if (back) {
            ft.addToBackStack(null);


        }
        ft.commit();

    }
}
