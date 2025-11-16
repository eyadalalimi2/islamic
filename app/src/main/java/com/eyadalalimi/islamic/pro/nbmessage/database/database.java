package com.eyadalalimi.islamic.pro.nbmessage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class database extends SQLiteOpenHelper {
    private Context mycontext;
    private static String DB_NAME = "msg.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private static final int DB_VERSION = 1;
    public SQLiteDatabase myDataBase;

    public database(Context context) throws IOException {
        super(context, DB_NAME, null, DB_VERSION);
        this.mycontext = context;
        openDataBase();
    }

    private SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = mycontext.getDatabasePath(DB_NAME);

        if (!dbFile.exists()) {
            try {
                copydatabase();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, 0);
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = mycontext.getAssets().open(DB_NAME);
        String outFileName = getDatabasePath();
        File f = new File(mycontext.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
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
        return mycontext.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DB_NAME;
    }
    public void SetFovarate(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE MESSAGES SET FAV=1   WHERE MESSAGE='" + Message + "'";
        db.execSQL(sql);
    }



    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }


    public void Fov(String Message) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE MESSAGES SET FAV=0   WHERE MESSAGE='" + Message + "'";
        db.execSQL(sql);

    }

    public List<Tablemsg> getmESSAGE(int id) {
        List<Tablemsg> contactList = new ArrayList<Tablemsg>();
        // Select All Query
        String selectQuery = "SELECT  MESSAGE,FAV FROM " + "MESSAGES where MSG_CAT="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tablemsg contact = new Tablemsg();
                contact.setTag(cursor.getString(0));
                contact.setMessage(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public List<Tablemsg> GetMessageSection() {
        SQLiteDatabase db = getWritableDatabase();

        List<Tablemsg> contactList = new ArrayList<Tablemsg>();
        // Select All Query
        String selectQuery = "SELECT  NAME FROM " + "MSG_CAT";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tablemsg contact = new Tablemsg();
                contact.setTag(cursor.getString(0));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public String getSizeMessage(int msc_cat) {
        String item = "";        // Select All Query
        String selectQuery = "SELECT  COUNT(MESSAGE) FROM " + "MESSAGES where MSG_CAT=" + msc_cat;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                item = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        // return contact list
        return item;
    }

    public List<Tablemsg> getFovarate() {
        List<Tablemsg> contactList = new ArrayList<Tablemsg>();
        // Select All Query
        String selectQuery = "SELECT  MESSAGE FROM " + "MESSAGES  where FAV=1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tablemsg contact = new Tablemsg();
                contact.setTag(cursor.getString(0));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

