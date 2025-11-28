package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.util.Log;

/**
 * Created by anwar_se on 6/14/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranIndex {

    private int id;
    private String sura;
    private String numAyat;
    private String suraType;
    private int suraPage;

    public QuranIndex() {
    }

    public int getId() {
        return id;
    }

    public QuranIndex setId(int id) {
        this.id = id;
        return this;
    }

    public String getSura() {
        return sura;
    }

    public QuranIndex setSura(String sura) {
        this.sura = sura;
        return this;
    }

    public String getNumAyat() {
        return numAyat;
    }

    public QuranIndex setNumAyat(String numAyat) {
        this.numAyat = numAyat;
        return this;
    }

    public String getSuraType() {
        return suraType;
    }

    public QuranIndex setSuraType(String suraType) {
        this.suraType = suraType;
        return this;
    }

    public int getSuraPage() {
        return suraPage;
    }

    public QuranIndex setSuraPage(int suraPage) {
        this.suraPage = suraPage;
        return this;
    }

    public QuranIndex print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "QuranIndex{" +
                "id=" + id +
                ", sura='" + sura + '\'' +
                ", numAyat='" + numAyat + '\'' +
                ", suraType='" + suraType + '\'' +
                ", suraPage='" + suraPage + '\'' +
                '}';
    }
}
