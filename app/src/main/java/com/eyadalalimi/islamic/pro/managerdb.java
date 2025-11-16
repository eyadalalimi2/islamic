package com.eyadalalimi.islamic.pro;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import static com.eyadalalimi.islamic.pro.AyaList.isConnectingToInternet;

public class managerdb extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;

    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    private LinearLayout layoutads;
    // Media Player
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
    ;
    private Utilities utils;

    private int currentSongIndex = 0;
    String RecitesName = "";
    String RecitesAYA = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerdb);
        MobileAds.initialize(getApplicationContext(),getString(R.string.aplication_admob_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Bundle b = getIntent().getExtras();
        RecitesName = b.getString("RecitesName");
        RecitesAYA = b.getString("RecitesAYA");
        if(isConnectingToInternet(managerdb.this)){
            Toast.makeText(this, "انتظر قليلا لتشتغل السورة........", Toast.LENGTH_SHORT).show();


        }
        else {
            Toast.makeText(this, "لايوجد انصال بالانترنت تاكد ان الانترنت شغال لديك ........", Toast.LENGTH_SHORT).show();

        }
        // All player buttons
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songTitleLabel = (TextView) findViewById(R.id.songTitle);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);

        // Mediaplayer
        utils = new Utilities();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important

        // Getting all songs list


        /**
         * Play button click event
         * plays a song and changes button to pause image
         * pauses a song and changes button to play image
         * */
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mBoundService != null) {
                    mBoundService.btnPlayPressed();
                    songTitleLabel.setText(mBoundService.getSongTitle());
                    if (mBoundService.isPlaying()) {
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                    } else {
                        // Resume song
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }

            }
        });

        /**
         * Forward button click event
         * Forwards song specified seconds
         * */
        btnForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (mBoundService != null) {
                    mBoundService.btnForwrdPressed();
                }
            }
        });

        /**
         * Backward button click event
         * Backward song to specified seconds
         * */
        btnBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mBoundService != null) {
                    mBoundService.btnBackwardPressed();
                }

            }
        });

        /**
         * Next button click event
         * Plays next song by taking currentSongIndex + 1
         * */
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mBoundService != null) {
                    mBoundService.btnNextPressed();
                    songTitleLabel.setText(mBoundService.getSongTitle());

                }


            }
        });

        /**
         * Back button click event
         * Plays previous song by currentSongIndex - 1
         * */
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mBoundService != null) {
                    mBoundService.btnPreviousPressed();
                    songTitleLabel.setText(mBoundService.getSongTitle());
                }

            }
        });

        /**
         * Button Click event for Repeat button
         * Enables repeat flag to true
         * */
        btnRepeat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mBoundService != null) {
                    mBoundService.btnRepeatPressed();
                    if (mBoundService.isRepeatEnabled()) {
                        Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
                        btnRepeat.setImageResource(R.drawable.btn_repeat);
                    } else {
                        Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
                        btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
                        btnShuffle.setImageResource(R.drawable.btn_shuffle);
                    }
                }
            }
        });

        /**
         * Button Click event for Shuffle button
         * Enables shuffle flag to true
         * */
        btnShuffle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mBoundService != null) {
                    mBoundService.btnShufflePressed();

                    if (mBoundService.isShuffleEnabled()) {
                        Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
                        btnShuffle.setImageResource(R.drawable.btn_shuffle);
                    } else {
                        Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
                        btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
                        btnRepeat.setImageResource(R.drawable.btn_repeat);
                    }
                }

            }
        });

        /**
         * Button Click event for Play list click event
         * Launches list activity which displays list of songs
         */

        doBindService();

        // By default play first song
        currentSongIndex = Integer.parseInt(RecitesAYA);//-1 ;
//        playSong(currentSongIndex);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("songChangedEvent"));

    }



    // Our handler for received Intents. This will be called whenever an Intent
// with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            if (intent != null && intent.hasExtra("songChanged")) {
                songTitleLabel.setText(mBoundService.getSongTitle());
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
    }

    private LocalService mBoundService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  Because we have bound to a explicit
            // service that we know is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            mBoundService = ((LocalService.LocalBinder) service).getService();

            // Tell the user about this for our demo.
            playSong(currentSongIndex);
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            // Because it is running in our same process, we should never
            // see this happen.
            mBoundService = null;
        }
    };
    private boolean mShouldUnbind;

    void doBindService() {
        // Attempts to establish a connection with the service.  We use an
        // explicit class name because we want a specific service
        // implementation that we know will be running in our own process
        // (and thus won't be supporting component replacement by other
        // applications).
        Intent intent = new Intent(managerdb.this, LocalService.class);
        intent.putExtra("RecitesName", RecitesName);
        if (bindService(intent,
                mConnection, Context.BIND_AUTO_CREATE)) {
            mShouldUnbind = true;
        } else {
            Log.e("MY_APP_TAG", "Error: The requested service doesn't " +
                    "exist, or this client isn't allowed access to it.");
        }
    }

    void doUnbindService() {
        if (mShouldUnbind) {
            // Release information about the service's state.
            unbindService(mConnection);
            mShouldUnbind = false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_managerdb, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.gbackmenu) {
            this.finish();
        }


        return super.onOptionsItemSelected(item);
    }


    // @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            if (keyCode == KeyEvent.KEYCODE_BACK) {

                this.finish();
            }
        } catch (Exception e) {

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Receiving song index from playlist view
     * and play the song
     */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            playSong(currentSongIndex);
        }

    }

    /**
     * Function to play a song
     *
     * @param songIndex - index of song
     */
    public void playSong(int songIndex) {
        mBoundService.playSong(songIndex);
        songTitleLabel.setText(mBoundService.getSongTitle());
        songProgressBar.setProgress(0);
        songProgressBar.setMax(100);
        updateProgressBar();
    }

    /**
     * Update timer on seekbar
     */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            try {

                if (mBoundService != null) {
                    long totalDuration = mBoundService.getDuration();

                    long currentDuration = mBoundService.getCurrentPosition();

                    // Displaying Total Duration time
                    songTotalDurationLabel.setText("" + utils.milliSecondsToTimer(totalDuration));
                    // Displaying time completed playing
                    songCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));

                    // Updating progress bar
                    int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
                    //Log.d("Progress", ""+progress);
                    songProgressBar.setProgress(progress);

                    // Running this thread after 100 milliseconds
                    mHandler.postDelayed(this, 100);
                    if (currentDuration >= (totalDuration / 8)) {
                        layoutads.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception ex) {
            }
        }
    };

    /**
     *
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);

        if (mBoundService != null) {
            int totalDuration = (int)mBoundService.getDuration();
            int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

            // forward or backward to certain seconds
            mBoundService.seekTo(currentPosition);

            // update timer progress again
            updateProgressBar();
        }
    }


    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        doUnbindService();
    }

}