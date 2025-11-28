package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.util.Log;

/**
 * Created by anwar_se on 6/24/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class Tafsir {

    private int id;
    private String tafsirMoysar;
    private String tafsirSaadi;
    private String tafsirBaghawi;


    public int getId() {
        return id;
    }

    public Tafsir setId(int id) {
        this.id = id;
        return this;
    }

    public String getTafsirMoysar() {
        return tafsirMoysar;
    }

    public Tafsir setTafsirMoysar(String tafsirMoysar) {
        this.tafsirMoysar = tafsirMoysar;
        return this;
    }

    public String getTafsirSaadi() {
        return tafsirSaadi;
    }

    public Tafsir setTafsirSaadi(String tafsirSaadi) {
        this.tafsirSaadi = tafsirSaadi;
        return this;
    }

    public String getTafsirBaghawi() {
        return tafsirBaghawi;
    }

    public Tafsir setTafsirBaghawi(String tafsirBaghawi) {
        this.tafsirBaghawi = tafsirBaghawi;
        return this;
    }

    public Tafsir print(String tag, String method) {
        Log.i(tag, method + this);
        return this;
    }

    @Override
    public String toString() {
        return "Tafsir{" +
                "tafsirMoysar='" + tafsirMoysar + '\'' +
                ", tafsirSaadi='" + tafsirSaadi + '\'' +
                ", tafsirBaghawi='" + tafsirBaghawi + '\'' +
                '}';
    }
}
