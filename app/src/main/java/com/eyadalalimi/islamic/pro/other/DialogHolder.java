package com.eyadalalimi.islamic.pro.other;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eyadalalimi.islamic.pro.R;


/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class DialogHolder extends RecyclerView.ViewHolder {

    private TextView categoryName;
    private ImageView categoryImage;

    public DialogHolder(View itemView) {
        super(itemView);
    }

    public TextView getCategoryName() {
        if (categoryName == null) {
            this.categoryName = itemView.findViewById(R.id.tv_category);
        }
        return categoryName;
    }

    public ImageView getCategoryImage() {
        if (categoryImage == null) {
            this.categoryImage = itemView.findViewById(R.id.iv_row_right);
        }
        return categoryImage;
    }

}
