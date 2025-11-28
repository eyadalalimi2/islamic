package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;


/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranIndexHolder extends RecyclerView.ViewHolder {

    private View container;
    private TextView indexNum;
    private TextView indexName;
    private TextView indexAyat;
    private TextView indexType;

    public QuranIndexHolder(View itemView) {
        super(itemView);
    }

    public View getContainer() {
        if (container == null) {
            this.container = itemView.findViewById(R.id.tv_quran_index_container);
        }
        return container;
    }

    public TextView getIndexNum() {
        if (indexNum == null) {
            this.indexNum = itemView.findViewById(R.id.tv_quran_index_num);
        }
        return indexNum;
    }

    public TextView getIndexName() {
        if (indexName == null) {
            this.indexName = itemView.findViewById(R.id.tv_quran_index_name);
        }
        return indexName;
    }

    public TextView getIndexAyat() {
        if (indexAyat == null) {
            this.indexAyat = itemView.findViewById(R.id.tv_quran_index_ayat);
        }
        return indexAyat;
    }

    public TextView getIndexType() {
        if (indexType == null) {
            this.indexType = itemView.findViewById(R.id.tv_quran_index_type);
        }
        return indexType;
    }
}
