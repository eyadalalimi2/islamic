package com.eyad.alalimi.sonya7rvreview.nbholyquran;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbprayer.PreferenceUtil;
import com.eyad.alalimi.sonya7rvreview.nbsahihboukhari.DBManager;
import com.eyad.alalimi.sonya7rvreview.other.Arrays;

import java.util.List;

/**
 * Created by anwar_se on 6/14/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranActivity extends AppCompatActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener, DialogInterface.OnClickListener {

    public static final int PICK_PAGE = 614;
    public static final int PICK_AYA = 615;
    private boolean isFull;
    private Toolbar toolbar;
    private View bottomBar;
    private TextView tvJuz;
    private TextView tvPage;
    private List<QuranPage> quranPageList;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        // WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quran);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomBar = findViewById(R.id.bottom_bar);
        tvJuz = findViewById(R.id.tv_juz);
        tvPage = findViewById(R.id.tv_page);

        viewPager = findViewById(R.id.vp_quran);
        quranPageList = DBManager.getInstance(this).getQuranPageList();
        QuranPagerAdapter quranPagerAdapter = new QuranPagerAdapter(this, quranPageList);
        quranPagerAdapter.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(quranPagerAdapter);
        setCurrentItem(PreferenceUtil.getLastPagePref(this));
    }

    public boolean isFullScreen() {
        return (getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }

    public void setFullScreen(boolean full) {
        if (full == isFullScreen()) {
            return;
        }
        Window window = getWindow();
        if (full) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            bottomBar.setVisibility(View.GONE);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
            bottomBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.quran_page) {
            isFull = !isFull;
            setFullScreen(isFull);
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        QuranPage quranPage = quranPageList.get(i);
        toolbar.setTitle(quranPage.getSura());
        tvJuz.setText(getResources().getString(R.string.quran_juz, quranPage.getJuz()));
        tvPage.setText(getResources().getString(R.string.quran_page, quranPage.getPageAya()));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cat_quran, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list) {
            showQuranDialog();
            return true;
        } else if (id == R.id.action_search) {
            startActivityForResult(new Intent(this, QuranSearchActivity.class), PICK_AYA);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceUtil.setLastPagePref(this, viewPager.getCurrentItem());
    }

    private void showQuranDialog() {
        MuslimDialog muslimDialog = new MuslimDialog(this);
        muslimDialog.setCategories(Arrays.getQuranOptions());
        muslimDialog.setListener(this);
        muslimDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == R.id.cat_quran_item_index) {
            startActivityForResult(new Intent(this, QuranIndexActivity.class), PICK_PAGE);
        } else if (which == R.id.cat_quran_item_read_virtue) {
            TextActivity.newIntent(this, getString(R.string.read_quran_virtue),
                    getString(R.string.cat_quran_item_read_virtue));
        } else if (which == R.id.cat_quran_item_doaa) {// NawawiyaActivity.newIntent(this,
            // getString(R.string.cat_quran_item_doaa), "doaa.html");
            TextActivity.newIntent(this, Arrays.getDoaaQuran(), getString(R.string.cat_quran_item_doaa));
        } else if (which == R.id.cat_quran_item_tafsir) {
            startActivity(new Intent(this, TafsirActivity.class));
        } else if (which == R.id.cat_quran_item_m3ani) {
            startActivity(new Intent(this, M3aniActivity.class));
        } else if (which == R.id.cat_quran_item_save_mark) {
            PreferenceUtil.setMarkPref(this, viewPager.getCurrentItem());
        } else if (which == R.id.cat_quran_item_go_mark) {
            int markPref = PreferenceUtil.getMarkPref(this);
            Log.d("TAG", "onClick: markPref: " + markPref);
            setCurrentItem(markPref);
        }
        dialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == PICK_PAGE || requestCode == PICK_AYA) && resultCode == RESULT_OK) {
            if (data != null) {
                int pageNum = data.getIntExtra(Intent.EXTRA_TEXT, -1);
                int indexNum = pageNum - 1;
                if (indexNum >= 0 && indexNum < quranPageList.size())
                    setCurrentItem(pageNum - 1);
            }
        }
    }

    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item, false);
    }

}
