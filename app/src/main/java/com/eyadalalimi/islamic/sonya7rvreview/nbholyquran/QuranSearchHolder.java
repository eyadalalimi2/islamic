package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;


/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranSearchHolder extends RecyclerView.ViewHolder {

    private TextView tvAyaText;
    private TextView tvAyaNum;
    private TextView tvSuraName;

    public QuranSearchHolder(View itemView) {
        super(itemView);
    }

    public TextView getAyaText() {
        if (tvAyaText == null) {
            this.tvAyaText = itemView.findViewById(R.id.tv_qs_aya_text);
        }
        return tvAyaText;
    }

    public TextView getAyaNum() {
        if (tvAyaNum == null) {
            this.tvAyaNum = itemView.findViewById(R.id.tv_qs_aya_num);
        }
        return tvAyaNum;
    }

    public TextView getSuraName() {
        if (tvSuraName == null) {
            this.tvSuraName = itemView.findViewById(R.id.tv_qs_sura_name);
        }
        return tvSuraName;
    }
}
