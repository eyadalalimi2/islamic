package com.eyadalalimi.islamic.sonya7rvreview.nbprayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.eyadalalimi.islamic.sonya7rvreview.nbautoazkar.UtilNotif;


/**
 * Created by anwar_se on 8/29/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class PrayerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        UtilNotif.newAthanNotification(context);
        PrayerUtil.setupNextPrayer(context);


    }
}
