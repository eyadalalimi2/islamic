package com.eyadalalimi.islamic.pro.nbsahihboukhari;

import android.util.Log;

import com.eyadalalimi.islamic.pro.other.ContentUtil;


/**
 * Created by anwar_se on 6/14/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class DefaultIndex implements BaseSearch {
    private String title;
    private int id;

    public DefaultIndex() {
    }

    public String getTitle() {
        return title;
    }

    public DefaultIndex setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getCategoryId() {
        return id;
    }

    public DefaultIndex setCategoryId(int id) {
        this.id = id;
        return this;
    }

    public DefaultIndex print(String tag, String method) {
        Log.i(tag, method + toString());
        return this;
    }

    @Override
    public String toString() {
        return "DefaultIndex{" +
                "title='" + title + '\'' +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean isSame(String prefix) {
        return ContentUtil.normalize(title).contains(prefix);
    }
}
