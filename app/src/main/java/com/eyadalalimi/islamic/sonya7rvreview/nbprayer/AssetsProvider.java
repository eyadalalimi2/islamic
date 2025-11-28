package com.eyadalalimi.islamic.sonya7rvreview.nbprayer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;

import java.io.IOException;

/**
 * Created by anwar-se on 5/10/2019
 * Email: anwar.dev.96@gmail.com
 */

public class AssetsProvider extends ContentProvider {
    public static final String TAG = "AssetsProvider_Log";

    public static Uri getAssetsUri(String assetsFile) {
        return Uri.parse("content://com.eyadalalimi.islamic.pro/" + assetsFile);
    }

    public static void shareFile(Context context, String assetsFile) {
        Uri assetsUri = AssetsProvider.getAssetsUri("athan.mp3");
        Intent theIntent = new Intent(Intent.ACTION_SEND);
        theIntent.setType("audio/*");
        theIntent.putExtra(Intent.EXTRA_STREAM, assetsUri);
        theIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject for message");
        theIntent.putExtra(Intent.EXTRA_TEXT, "Body for message");
        theIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(theIntent);
    }

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) {
        Log.v(TAG, "AssetsGetter: Open asset file");
        AssetManager am = getContext().getAssets();
        String stringUri = uri.toString();

        String file_name = stringUri.replace("content://com.eyadalalimi.islamic.pro/", "");
        /*if( file_name == null )
            throw new FileNotFoundException( );*/
        AssetFileDescriptor afd = null;
        try {
            afd = am.openFd(file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return afd;//super.openAssetFile(uri, mode);
    }

    @Override
    public String getType(Uri p1) {
        return null;
    }

    @Override
    public int delete(Uri p1, String p2, String[] p3) {
        return 0;
    }

    @Override
    public Cursor query(Uri p1, String[] p2, String p3, String[] p4, String p5) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }

    @Override
    public Uri insert(Uri p1, ContentValues p2) {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public int update(Uri p1, ContentValues p2, String p3, String[] p4) {
        return 0;
    }

}
