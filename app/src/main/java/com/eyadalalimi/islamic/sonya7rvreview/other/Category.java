package com.eyadalalimi.islamic.sonya7rvreview.other;

import android.util.Log;

/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class Category {

    private int categoryId;
    private int title;
    private int imageRes;
    private int colorRes;

    public int getCategoryId() {
        return categoryId;
    }

    public Category setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public int getTitle() {
        return title;
    }

    public Category setTitle(int title) {
        this.title = title;
        return this;
    }

    public int getImageRes() {
        return imageRes;
    }

    public Category setImageRes(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }

    public int getColorRes() {
        return colorRes;
    }

    public Category setColorRes(int colorRes) {
        this.colorRes = colorRes;
        return this;
    }

    public Category print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", imageRes=" + imageRes +
                ", colorRes=" + colorRes +
                '}';
    }
}
