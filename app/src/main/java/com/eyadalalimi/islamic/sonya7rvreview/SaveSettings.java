package com.eyadalalimi.islamic.sonya7rvreview;

/**
 * Created by wahib.dev on 03/06/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;


public class SaveSettings {

    Context context;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs3" ;
    public static int LanguageSelect=1;
    public static String ServerURL="https://server6.mp3quran.net/";
    public static boolean OnTimeAds=false;
    public static int IsRated=0;//app rate 0 not rate 1 is rate
    public SaveSettings(Context context) {
        this.context=context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


    }
    public void SaveData()  {

        try

        {

            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putInt("LanguageSelect", LanguageSelect);
            editor.putInt("IsRated", IsRated);
            editor.commit();
            LoadData( );
        }

        catch( Exception e)

        {

            // Toast.makeText(context, "Unable to write to the SettingFile file.", Toast.LENGTH_LONG).show();
        }
    }
    public   void LoadData( ) {


        // LanguageSelect=sharedpreferences.getInt("LanguageSelect", -1);
        //  if(LanguageSelect==-1) //first time
        //  {
        String lng= Locale.getDefault().getLanguage();
         lng.equalsIgnoreCase("ar");
            LanguageSelect=1;



        IsRated=sharedpreferences.getInt("IsRated", 0);


    }
}

