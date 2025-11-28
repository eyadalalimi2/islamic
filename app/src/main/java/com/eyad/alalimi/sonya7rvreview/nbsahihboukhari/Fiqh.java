package com.eyad.alalimi.sonya7rvreview.nbsahihboukhari;

import android.util.Log;

/**
 * Created by anwar_se on 6/14/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class Fiqh {

    private String type;
    private String title;
    private String story;

    public Fiqh() {
    }

    public String getType() {
        return type;
    }

    public Fiqh setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Fiqh setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStory() {
        return story;
    }

    public Fiqh setStory(String story) {
        this.story = story;
        return this;
    }

    public Fiqh print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "Fiqh{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", story='" + story + '\'' +
                '}';
    }
}
