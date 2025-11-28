package com.eyad.alalimi.sonya7rvreview.nbholyquran;

/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */

import android.view.View;
import android.widget.AdapterView;

public abstract class SpinnerListener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public abstract void onItemSelected(int position);
}
