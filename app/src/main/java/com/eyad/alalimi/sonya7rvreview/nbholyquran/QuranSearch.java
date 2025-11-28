package com.eyad.alalimi.sonya7rvreview.nbholyquran;

import android.util.Log;

import com.eyad.alalimi.sonya7rvreview.nbsahihboukhari.BaseSearch;

/**
 * Created by anwar_se on 7/11/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranSearch implements BaseSearch {
    private String sura;
    private String aya;
    private String searchAya;
    private int ayaNum;
    private int pageAya;

    public QuranSearch() {
    }

    public String getSura() {
        return sura;
    }

    public QuranSearch setSura(String sura) {
        this.sura = sura;
        return this;
    }

    public String getAya() {
        return aya;
    }

    public QuranSearch setAya(String aya) {
        this.aya = aya;
        return this;
    }

    public int getAyaNum() {
        return ayaNum;
    }

    public QuranSearch setAyaNum(int ayaNum) {
        this.ayaNum = ayaNum;
        return this;
    }

    public String getSearchAya() {
        return searchAya;
    }

    public QuranSearch setSearchAya(String aya) {
        this.searchAya = aya;
        return this;
    }

    public int getPageAya() {
        return pageAya;
    }

    public QuranSearch setPageAya(int pageAya) {
        this.pageAya = pageAya;
        return this;
    }

    public QuranSearch print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "QuranSearch{" +
                "sura='" + sura + '\'' +
                ", aya='" + aya + '\'' +
                ", ayaNum=" + ayaNum + '\'' +
                ", searchAya='" + searchAya + '\'' +
                ", pageAya=" + pageAya +
                '}';
    }

    @Override
    public boolean isSame(String prefix) {
        return searchAya.contains(prefix);
    }
}
