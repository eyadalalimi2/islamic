package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.util.Log;

/**
 * Created by anwar_se on 6/14/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranAya {

    private int id;
    private String sura;
    private int suraNum;
    private String aya;
    private int ayaNum;
    private String searchAya;
    private String ma3nyAya;
    private String tafsirMoysar;
    private String tafsirSaadi;
    private String tafsirBaghawi;
    private String e3rabQuran;
    private String juz;
    private int pageAya;

    public QuranAya() {
    }

    public int getId() {
        return id;
    }

    public QuranAya setId(int id) {
        this.id = id;
        return this;
    }

    public String getSura() {
        return sura;
    }

    public QuranAya setSura(String sura) {
        this.sura = sura;
        return this;
    }

    public int getSuraNum() {
        return suraNum;
    }

    public QuranAya setSuraNum(int suraNum) {
        this.suraNum = suraNum;
        return this;
    }

    public String getAya() {
        return aya;
    }

    public QuranAya setAya(String aya) {
        this.aya = aya;
        return this;
    }

    public int getAyaNum() {
        return ayaNum;
    }

    public QuranAya setAyaNum(int ayaNum) {
        this.ayaNum = ayaNum;
        return this;
    }

    public String getSearchAya() {
        return searchAya;
    }

    public QuranAya setSearchAya(String searchAya) {
        this.searchAya = searchAya;
        return this;
    }

    public String getMa3nyAya() {
        return ma3nyAya;
    }

    public QuranAya setMa3nyAya(String ma3nyAya) {
        this.ma3nyAya = ma3nyAya;
        return this;
    }

    public String getTafsirMoysar() {
        return tafsirMoysar;
    }

    public QuranAya setTafsirMoysar(String tafsirMoysar) {
        this.tafsirMoysar = tafsirMoysar;
        return this;
    }

    public String getTafsirSaadi() {
        return tafsirSaadi;
    }

    public QuranAya setTafsirSaadi(String tafsirSaadi) {
        this.tafsirSaadi = tafsirSaadi;
        return this;
    }

    public String getTafsirBaghawi() {
        return tafsirBaghawi;
    }

    public QuranAya setTafsirBaghawi(String tafsirBaghawi) {
        this.tafsirBaghawi = tafsirBaghawi;
        return this;
    }

    public String getE3rabQuran() {
        return e3rabQuran;
    }

    public QuranAya setE3rabQuran(String e3rabQuran) {
        this.e3rabQuran = e3rabQuran;
        return this;
    }

    public String getJuz() {
        return juz;
    }

    public QuranAya setJuz(String juz) {
        this.juz = juz;
        return this;
    }

    public int getPageAya() {
        return pageAya;
    }

    public QuranAya setPageAya(int pageAya) {
        this.pageAya = pageAya;
        return this;
    }

    public QuranAya print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "QuranAya{" +
                "id=" + id +
                ", sura='" + sura + '\'' +
                ", suraNum=" + suraNum +
                ", aya='" + aya + '\'' +
                ", ayaNum=" + ayaNum +
                ", searchAya='" + searchAya + '\'' +
                ", ma3nyAya='" + ma3nyAya + '\'' +
                ", tafsirMoysar='" + tafsirMoysar + '\'' +
                ", tafsirSaadi='" + tafsirSaadi + '\'' +
                ", tafsirBaghawi='" + tafsirBaghawi + '\'' +
                ", e3rabQuran='" + e3rabQuran + '\'' +
                ", juz='" + juz + '\'' +
                ", pageAya='" + pageAya + '\'' +
                '}';
    }
}
