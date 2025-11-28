package com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eyadalalimi.islamic.sonya7rvreview.nbholyquran.QuranIndex;
import com.eyadalalimi.islamic.sonya7rvreview.nbholyquran.QuranPage;
import com.eyadalalimi.islamic.sonya7rvreview.nbholyquran.QuranSearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anwar-se on 4/30/2019
 * Email: anwar.dev.96@gmail.com
 */

public class DBManager extends SQLiteOpenHelper {

    private static final String TABLE_FIGH_INDEX = "fiqh_index";
    private static final String COL_FIGH_INDEX_TITLE = "name";

    private static final String TABLE_FIGH = "fiqh";
    private static final String COL_FIGH_TYPE = "type";
    private static final String COL_FIGH_TITLE = "title";
    private static final String COL_FIGH_STORY = "story";

    private static final String TABLE_LIBRARY = "library";

    private static final String COL_LIBRARY_TYPE = "type";
    private static final String COL_LIBRARY_TYPE_AS = "type_as";
    private static final String COL_LIBRARY_TITLE = "title";
    private static final String COL_LIBRARY_STORY = "story";



    private static final String TABLE_ALBUKHARI = "albukhari";
    private static final String COL_ALBUKHARI_TYPE = "type";
    private static final String COL_ALBUKHARI_STORY = "story";



    private static final String TABLE_QURAN_INDEX = "quran_index";
    private static final String COL_QURAN_INDEX_ID = "id_sura";
    private static final String COL_QURAN_INDEX_SURA = "sura";
    private static final String COL_QURAN_INDEX_NUM = "num_ayat";
    private static final String COL_QURAN_INDEX_TYPE = "sura_type";

    private static final String TABLE_QURAN_AYA = "quran";
    private static final String COL_QURAN_ID = "id_quran_ayat";
    private static final String COL_QURAN_SURA = "sura";
    private static final String COL_QURAN_SURA_NUM = "sura_num";
    private static final String COL_QURAN_AYA = "aya";
    private static final String COL_QURAN_AYA_NUM = "aya_num";
    private static final String COL_QURAN_SEARCH_AYA = "search_aya";
    private static final String COL_QURAN_MA3NY = "ma3ny_aya";
    private static final String COL_QURAN_MOYSAR = "tafsir_moysar";
    private static final String COL_QURAN_SAADI = "tafsir_saadi";
    private static final String COL_QURAN_BAGHAWI = "tafsir_baghawi";
    private static final String COL_QURAN_E3RAB = "e3rab_quran";
    private static final String COL_QURAN_JUZ = "juz";
    private static final String COL_QURAN_PAGE = "page_aya";





    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "databfh.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private static final String TAG = "DBManager_Log";
    private static DBManager INSTANTS;
    private final Context ctx;

    private DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
        openDataBase();
    }

    public static DBManager getInstance(Context context) {
        if (INSTANTS == null) {
            INSTANTS = new DBManager(context);
        }
        return INSTANTS;
    }

    private void copyFromAsset() throws IOException {
        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    private SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                copyFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /*-------------------->START Quran<--------------------*/

    public List<Integer> getQuranPageListInt() {
        List<Integer> pageRes = new ArrayList<>();
        for (int i = 1; i <= 604; i++) {
            String mDrawableName = "page" + i /*+".png"*/;
            int resID = ctx.getResources().getIdentifier(mDrawableName, "drawable", ctx.getPackageName());
            pageRes.add(resID);
        }
        return pageRes;
    }

    public List<QuranPage> getQuranPageList() {
        List<QuranPage> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MIN(" + COL_QURAN_PAGE + ") AS " + COL_QURAN_PAGE
                + ", " + COL_QURAN_SURA + ", " + COL_QURAN_JUZ +
                " FROM " + TABLE_QURAN_AYA +
                " GROUP BY " + COL_QURAN_PAGE;
        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            int page = c.getInt(c.getColumnIndex(COL_QURAN_PAGE));
            quranAyaList.add(new QuranPage()
                    .setSura(c.getString(c.getColumnIndex(COL_QURAN_SURA)))
                    .setJuz(c.getString(c.getColumnIndex(COL_QURAN_JUZ)))
                    .setPageAya(page)
                    .setPageRes(ctx.getResources().getIdentifier
                            ("page" + page, "drawable", ctx.getPackageName()))
                    .print(TAG, "getQuranPageList: ")
            );
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<QuranIndex> getQuranIndexList() {
        List<QuranIndex> quranIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  q.sura_num, q.sura, qi.num_ayat, qi.sura_type, MIN(q.page_aya) AS page_aya " +
                " FROM " + TABLE_QURAN_AYA + " q, " + TABLE_QURAN_INDEX + " qi" +
                " WHERE q.sura = qi.sura" +
                " GROUP BY q.sura" +
                " ORDER BY q.sura_num";
        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            QuranIndex defaultIndex = new QuranIndex()
                    .setId(c.getInt(c.getColumnIndex("sura_num")))
                    .setSura(c.getString(c.getColumnIndex(COL_QURAN_INDEX_SURA)))
                    .setNumAyat(c.getString(c.getColumnIndex(COL_QURAN_INDEX_NUM)))
                    .setSuraType(c.getString(c.getColumnIndex(COL_QURAN_INDEX_TYPE)))
                    .setSuraPage(c.getInt(c.getColumnIndex("page_aya")))
                    .print(TAG, "getQuranIndexList: ");
            quranIndexList.add(defaultIndex);
        }
        c.close();
        db.close();
        return quranIndexList;
    }

    public List<QuranSearch> getQuranSearchList() {
        List<QuranSearch> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "
                + COL_QURAN_SURA + ", "
                + COL_QURAN_AYA + ", "
                + COL_QURAN_AYA_NUM + ", "
                + COL_QURAN_SEARCH_AYA + ", "
                + COL_QURAN_PAGE
                + " FROM " + TABLE_QURAN_AYA;
        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            quranAyaList.add(new QuranSearch()
                    .setSura(c.getString(c.getColumnIndex(COL_QURAN_SURA)))
                    .setAya(c.getString(c.getColumnIndex(COL_QURAN_AYA)))
                    .setAyaNum(c.getInt(c.getColumnIndex(COL_QURAN_AYA_NUM)))
                    .setSearchAya(c.getString(c.getColumnIndex(COL_QURAN_SEARCH_AYA)))
                    .setPageAya(c.getInt(c.getColumnIndex(COL_QURAN_PAGE)))
                    .print(TAG, "getQuranSearchList: ")
            );
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<QuranSearch> getQuranSearchList(String prefix) {
        List<QuranSearch> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM muslim WHERE search_aya LIKE '%محمد%'";
        String query = "SELECT "
                + COL_QURAN_SURA + ", "
                + COL_QURAN_AYA + ", "
                + COL_QURAN_AYA_NUM + ", "
                + COL_QURAN_SEARCH_AYA + ", "
                + COL_QURAN_PAGE
                + " FROM " + TABLE_QURAN_AYA
                + " WHERE " + COL_QURAN_SEARCH_AYA + " LIKE '%" + prefix + "%'";
        Cursor c = db.rawQuery(query, null);

        while (c.moveToNext()) {
            quranAyaList.add(new QuranSearch()
                    .setSura(c.getString(c.getColumnIndex(COL_QURAN_SURA)))
                    .setAya(c.getString(c.getColumnIndex(COL_QURAN_AYA)))
                    .setAyaNum(c.getInt(c.getColumnIndex(COL_QURAN_AYA_NUM)))
                    .setSearchAya(c.getString(c.getColumnIndex(COL_QURAN_SEARCH_AYA)))
                    .setPageAya(c.getInt(c.getColumnIndex(COL_QURAN_PAGE)))
                    .print(TAG, "getQuranSearchList: ")
            );
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<String> getSuraList() {
        List<String> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QURAN_INDEX, null);

        while (c.moveToNext()) {
            quranAyaList.add(c.getString(c.getColumnIndex(COL_QURAN_INDEX_SURA)));
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<Integer> getSuraAyatNums() {
        List<Integer> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QURAN_INDEX, null);

        while (c.moveToNext()) {
            quranAyaList.add(c.getInt(c.getColumnIndex(COL_QURAN_INDEX_NUM)));
            int ss = c.getInt(c.getColumnIndex(COL_QURAN_INDEX_NUM));
            Log.d(TAG, "getSuraAyatNums: " + ss);
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<String> getTafsirList(int suraNum, int selectedTafsir) {
        List<String> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QURAN_AYA
                + " WHERE " + COL_QURAN_SURA_NUM + " = ? ", new String[]{String.valueOf(suraNum)});


        int columnIndex;
        switch (selectedTafsir) {
            case 0:
                columnIndex = c.getColumnIndex(COL_QURAN_MOYSAR);
                break;
            case 1:
                columnIndex = c.getColumnIndex(COL_QURAN_SAADI);
                break;
            case 2:
                columnIndex = c.getColumnIndex(COL_QURAN_BAGHAWI);
                break;
            default:
                columnIndex = c.getColumnIndex(COL_QURAN_MOYSAR);
                break;
        }
        while (c.moveToNext()) {
            quranAyaList.add(c.getString(columnIndex));
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<String> getE3rabList(int suraNum) {
        List<String> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QURAN_AYA
                + " WHERE " + COL_QURAN_SURA_NUM + " = ? ", new String[]{String.valueOf(suraNum)});

        int columnIndex = c.getColumnIndex(COL_QURAN_E3RAB);
        while (c.moveToNext()) {
            quranAyaList.add(c.getString(columnIndex));
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    public List<String> getM3aniList(int suraNum) {
        List<String> quranAyaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_QURAN_AYA
                        + " WHERE " + COL_QURAN_SURA_NUM + " = ? "
                        + "AND " + COL_QURAN_MA3NY + " IS NOT NULL "
                        + "AND " + COL_QURAN_MA3NY + " != ? "
                , new String[]{String.valueOf(suraNum), ""});

        int columnIndex = c.getColumnIndex(COL_QURAN_MA3NY);
        while (c.moveToNext()) {
            quranAyaList.add(c.getString(columnIndex));
        }
        c.close();
        db.close();
        return quranAyaList;
    }

    /*-------------------->END Quran<--------------------*/




    //region Library

    //region Description
    public List<DefaultIndex> getLibraryIndexList() {
        List<DefaultIndex> libraryIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MIN(" + COL_LIBRARY_TYPE + ") AS " + COL_LIBRARY_TYPE
                + " FROM " + TABLE_LIBRARY
                + " GROUP BY " + COL_LIBRARY_TYPE;

        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            libraryIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_LIBRARY_TYPE)))
                    .print(TAG, "getLibraryIndexList: "));
        }
        c.close();
        db.close();
        return libraryIndexList;
    }

    public List<DefaultIndex> getLibraryIndexList(String type) {
        List<DefaultIndex> libraryIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_LIBRARY
                + " WHERE " + COL_LIBRARY_TYPE + " = ? ", new String[]{String.valueOf(type)});

        while (c.moveToNext()) {
            libraryIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_LIBRARY_TITLE)))
                    .print(TAG, "getLibraryIndexList: "));
        }
        c.close();
        db.close();
        return libraryIndexList;
    }

    public Fiqh getLibrary(String type) {
        Fiqh fiqh = new Fiqh();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_LIBRARY
                + " WHERE " + COL_LIBRARY_TITLE + " = ? ", new String[]{String.valueOf(type)});


        if (c.moveToFirst()) {
            fiqh.setType(c.getString(c.getColumnIndex(COL_LIBRARY_TYPE)))
                    .setTitle(c.getString(c.getColumnIndex(COL_LIBRARY_TITLE)))
                    .setStory(c.getString(c.getColumnIndex(COL_LIBRARY_STORY)))
                    .print(TAG, "getLibrary: ");
        }
        c.close();
        db.close();
        return fiqh;
    }
    //endregion

    //endregion Library
    //region Fiqh

    public List<DefaultIndex> getFiqhIndexList() {
        List<DefaultIndex> fiqhIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FIGH_INDEX, null);

        while (c.moveToNext()) {
            fiqhIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_FIGH_INDEX_TITLE)))
                    .print(TAG, "getFiqhIndexList: "));
        }
        c.close();
        db.close();
        return fiqhIndexList;
    }

    public List<DefaultIndex> getFiqhIndexList(String type) {
        List<DefaultIndex> fiqhIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FIGH
                + " WHERE " + COL_FIGH_TYPE + " = ? ", new String[]{String.valueOf(type)});

        while (c.moveToNext()) {
            fiqhIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_FIGH_TITLE)))
                    .print(TAG, "getFiqhIndexList: "));
        }
        c.close();
        db.close();
        return fiqhIndexList;
    }

    public Fiqh getFiqh(String type) {
        Fiqh fiqh = new Fiqh();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FIGH
                + " WHERE " + COL_FIGH_TITLE + " = ? ", new String[]{String.valueOf(type)});


        if (c.moveToFirst()) {
            fiqh.setType(c.getString(c.getColumnIndex(COL_FIGH_TYPE)))
                    .setTitle(c.getString(c.getColumnIndex(COL_FIGH_TITLE)))
                    .setStory(c.getString(c.getColumnIndex(COL_FIGH_STORY)))
                    .print(TAG, "getFiqh: ");
        }
        c.close();
        db.close();
        return fiqh;
    }

    public List<Fiqh> getFiqhList() {
        List<Fiqh> fiqhList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FIGH, null);

        while (c.moveToNext()) {
            fiqhList.add(new Fiqh()
                    .setType(c.getString(c.getColumnIndex(COL_FIGH_TYPE)))
                    .setTitle(c.getString(c.getColumnIndex(COL_FIGH_TITLE)))
                    .setStory(c.getString(c.getColumnIndex(COL_FIGH_STORY)))
                    .print(TAG, "getFiqhList: "));
        }
        c.close();
        db.close();
        return fiqhList;
    }

    //endregion Fiqh



    /*-------------------->START Albukhari<--------------------*/

    public List<DefaultIndex> getAlbukhariIndexList() {
        List<DefaultIndex> albukhariIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MIN(" + COL_ALBUKHARI_TYPE + ") AS " + COL_ALBUKHARI_TYPE
                + " FROM " + TABLE_ALBUKHARI
                + " GROUP BY " + COL_ALBUKHARI_TYPE;

        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            albukhariIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_ALBUKHARI_TYPE)))
                    .print(TAG, "getAlbukhariList: "));
        }
        c.close();
        db.close();
        return albukhariIndexList;
    }

    public List<DefaultIndex> getAlbukhariList(String type) {
        List<DefaultIndex> albukhariIndexList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ALBUKHARI
                + " WHERE " + COL_ALBUKHARI_TYPE + " = ? ", new String[]{String.valueOf(type)});

        while (c.moveToNext()) {
            albukhariIndexList.add(new DefaultIndex()
                    .setTitle(c.getString(c.getColumnIndex(COL_ALBUKHARI_STORY)))
                    .print(TAG, "getAlbukhariIndexList2: "));
        }
        c.close();
        db.close();
        return albukhariIndexList;
    }

    public Fiqh getAlbukhari(String type) {
        Fiqh fiqh = new Fiqh();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ALBUKHARI
                + " WHERE " + COL_ALBUKHARI_STORY + " = ? ", new String[]{String.valueOf(type)});


        if (c.moveToFirst()) {
            fiqh.setType(c.getString(c.getColumnIndex(COL_ALBUKHARI_TYPE)))
                    .setTitle(c.getString(c.getColumnIndex(COL_ALBUKHARI_STORY)))
                    .setStory(c.getString(c.getColumnIndex(COL_ALBUKHARI_STORY)))
                    .print(TAG, "getAlbukhari: ");
        }
        c.close();
        db.close();
        return fiqh;
    }

    /*-------------------->END Albukhari<--------------------*/



}
