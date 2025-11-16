package com.eyadalalimi.islamic.pro.nbholyquran;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by anwar_se on 6/15/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranPage implements Parcelable {

    public static final Creator<QuranPage> CREATOR = new Creator<QuranPage>() {
        @Override
        public QuranPage createFromParcel(Parcel in) {
            return new QuranPage(in);
        }

        @Override
        public QuranPage[] newArray(int size) {
            return new QuranPage[size];
        }
    };
    private String sura;
    private String juz;
    private int pageAya;
    private int pageRes;

    public QuranPage() {
    }

    protected QuranPage(Parcel in) {
        sura = in.readString();
        juz = in.readString();
        pageAya = in.readInt();
        pageRes = in.readInt();
    }

    public String getSura() {
        return sura;
    }

    public QuranPage setSura(String sura) {
        this.sura = sura;
        return this;
    }

    public String getJuz() {
        return juz;
    }

    public QuranPage setJuz(String juz) {
        this.juz = juz;
        return this;
    }

    public int getPageAya() {
        return pageAya;
    }

    public QuranPage setPageAya(int pageAya) {
        this.pageAya = pageAya;
        return this;
    }

    public int getPageRes() {
        return pageRes;
    }

    public QuranPage setPageRes(int pageRes) {
        this.pageRes = pageRes;
        return this;
    }

    public QuranPage print(String tag, String method) {
        Log.i(tag, method + toString());
        return this;
    }

    @Override
    public String toString() {
        return "QuranPage{" +
                "sura='" + sura + '\'' +
                ", juz='" + juz + '\'' +
                ", pageAya=" + pageAya +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sura);
        dest.writeString(juz);
        dest.writeInt(pageAya);
        dest.writeInt(pageRes);
    }
}
