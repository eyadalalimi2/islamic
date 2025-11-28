package com.eyadalalimi.islamic.sonya7rvreview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LocalService extends Service implements MediaPlayer.OnCompletionListener {
    private NotificationManager mNM;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private final int NOTIFICATION = 432;

    private static MediaPlayer mp;
    private SongsManager songManager;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private String recitesName;
    private int currentSongIndex = 0;
    private final int seekForwardTime = 5000; // 5000 milliseconds
    private final int seekBackwardTime = 5000; // 5000 milliseconds
    private boolean isRepeat = false;
    private boolean isShuffle = false;
    private boolean isLoading = false;

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
        mp = new MediaPlayer();
        songManager = new SongsManager();
        mp.setOnCompletionListener(this); // Important

        //mp3 will be started after completion of preparing...
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer player) {
                isLoading = false;
                mp.start();
            }

        });


    }


    public void btnPlayPressed() {
        try {
            if (mp != null) {
                if (mp.isPlaying()) {
                    mp.pause();
                } else {
                    // Resume song
                    mp.start();
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean isPlaying() {
        if (mp != null) {
            return mp.isPlaying();
        }
        return false;
    }

    public void btnNextPressed() {
        // check if next song is there or not
        if (currentSongIndex < (songsList.size() - 1)) {
            playSong(currentSongIndex + 1);
            currentSongIndex = currentSongIndex + 1;
        } else {
            // play first song
            playSong(0);
            currentSongIndex = 0;
        }
    }

    public void btnForwrdPressed() {
        // get current song position
        int currentPosition = mp.getCurrentPosition();
        // check if seekForward time is lesser than song duration
        if (currentPosition + seekForwardTime <= mp.getDuration()) {
            // forward song
            mp.seekTo(currentPosition + seekForwardTime);
        } else {
            // forward to end position
            mp.seekTo(mp.getDuration());
        }
    }


    public void btnRepeatPressed() {
        if (isRepeat) {
            isRepeat = false;
        } else {
            // make repeat to true
            isRepeat = true;
            // make shuffle to false
            isShuffle = false;
        }
    }

    public void btnShufflePressed() {
        if (isShuffle) {
            isShuffle = false;
        } else {
            // make repeat to true
            isShuffle = true;
            // make shuffle to false
            isRepeat = false;
        }
    }

    public boolean isRepeatEnabled() {
        return isRepeat;
    }

    public boolean isShuffleEnabled() {
        return isShuffle;
    }


    public void btnPreviousPressed() {
        if (currentSongIndex > 0) {
            playSong(currentSongIndex - 1);
            currentSongIndex = currentSongIndex - 1;
        } else {
            // play last song
            playSong(songsList.size() - 1);
            currentSongIndex = songsList.size() - 1;
        }
    }

    private String songTitle = "";

    public String getSongTitle() {
        return songTitle;
    }

    public void playSong(int songIndex) {
        // Play song
        try {
            currentSongIndex = songIndex;
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            isLoading = true;
            mp.prepareAsync();
            songTitle = songsList.get(songIndex).get("songTitle");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seekTo(int currentPosition) {
        if (mp != null) {
            mp.seekTo(currentPosition);
        }
    }

    public long getCurrentPosition() {
        if (mp != null) {
            return mp.getCurrentPosition();
        }
        return 0;
    }

    public long getDuration() {
        if (mp != null) {
            return mp.getDuration();
        }
        return 0;
    }


    public void btnBackwardPressed() {
        // get current song position
        int currentPosition = mp.getCurrentPosition();
        // check if seekBackward time is greater than 0 sec
        if (currentPosition - seekBackwardTime >= 0) {
            // forward song
            mp.seekTo(currentPosition - seekBackwardTime);
        } else {
            // backward to starting position
            mp.seekTo(0);
        }
    }


    /**
     * On Song Playing completed
     * if repeat is ON play same song again
     * if shuffle is ON play random song
     */
    @Override
    public void onCompletion(MediaPlayer arg0) {

        if (!isLoading) {


            // check for repeat is ON or OFF
            if (isRepeat) {
                // repeat is on play same song again
                playSong(currentSongIndex);
            } else if (isShuffle) {
                // shuffle is on - play a random song
                Random rand = new Random();
                currentSongIndex = rand.nextInt((songsList.size() - 1) + 1);
                playSong(currentSongIndex);
            } else {
                // no repeat or shuffle ON - play next song
                if (currentSongIndex < (songsList.size() - 1)) {
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                } else {
                    // play first song
                    playSong(0);
                    currentSongIndex = 0;
                }
            }

            Intent intent = new Intent("songChangedEvent");
            // You can also include some extra data.
            intent.putExtra("songChanged", "songChanged");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        mNM.cancel(NOTIFICATION);
        if (mp != null) {
            mp.release();
            mp = null;
        }

        // Tell the user we stopped.
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent.getExtras() != null) {
            recitesName = intent.getExtras().getString("RecitesName", "shatri");
            songsList = songManager.getPlayList(recitesName);

        }
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification

        // Set the info for the views that show in the notification panel.
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)  // the status icon
                    .setTicker("تشغيل")  // the status text
                    .setWhen(System.currentTimeMillis())  // the time stamp
                    .setContentTitle(songTitle)  // the label of the entry
                    .build();
            mNM.notify(NOTIFICATION, notification);
        }

        // Send the notification.
    }
}
