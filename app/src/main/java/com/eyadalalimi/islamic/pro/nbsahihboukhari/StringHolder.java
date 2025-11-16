package com.eyadalalimi.islamic.pro.nbsahihboukhari;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class StringHolder extends RecyclerView.ViewHolder {

    private TextView text;

    public StringHolder(View itemView) {
        super(itemView);
    }

    public TextView getText() {
        if (text == null) {
            this.text = itemView.findViewById(android.R.id.text1);
        }
        return text;
    }
}
