package com.eyadalalimi.islamic.pro.nbholyquran;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eyadalalimi.islamic.pro.R;


/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class TafsirHolder extends RecyclerView.ViewHolder {

    private View container;
    private TextView tafsirAya;

    public TafsirHolder(View itemView) {
        super(itemView);
    }

    public View getContainer() {
        if (container == null) {
            this.container = itemView.findViewById(R.id.tv_tafsir_container);
        }
        return container;
    }

    public TextView getTafsirAya() {
        if (tafsirAya == null) {
            this.tafsirAya = itemView.findViewById(R.id.tv_tafsir_aya);
        }
        return tafsirAya;
    }
}
