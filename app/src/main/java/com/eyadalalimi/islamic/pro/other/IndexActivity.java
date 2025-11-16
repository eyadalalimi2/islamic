package com.eyadalalimi.islamic.pro.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.BukhariActivity;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.DBManager;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.DefaultIndex;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.OnItemClickListener;
import com.eyadalalimi.islamic.pro.nbnawawi.NawawiyaActivity;
import com.eyadalalimi.islamic.pro.nbholyquran.TextActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class IndexActivity extends AppCompatActivity implements OnItemClickListener {

    private Context context = this;
    private List<DefaultIndex> categories;
    private int indexId, indexColor;
    private String indexTitle;

    public static void newIntent(Activity activity, int indexId, String indexTitle, int indexColor) {
        newIntent(activity, indexId, indexTitle, indexColor, 1);
    }

    public static void newIntent(Activity activity, int indexId, String indexTitle, int indexColor, int spanCount) {
        Intent fatawyIntent = new Intent(activity, IndexActivity.class);
        fatawyIntent.putExtra(ActivityUtils.EXTRA_INDEX_ID, indexId);
        fatawyIntent.putExtra(ActivityUtils.EXTRA_INDEX_TEXT, indexTitle);
        fatawyIntent.putExtra(ActivityUtils.EXTRA_INDEX_COLOR, indexColor);
        fatawyIntent.putExtra(ActivityUtils.EXTRA_INDEX_COUNT, spanCount);
        activity.startActivity(fatawyIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);

        initIndexList();
        ActivityUtils.setupToolbar(this, indexTitle);

        RecyclerView countriesRecycler = findViewById(R.id.rec_index);
        int spanCount = getIntent().getIntExtra(ActivityUtils.EXTRA_INDEX_COUNT, 1);

        countriesRecycler.setLayoutManager(new GridLayoutManager(context, spanCount, RecyclerView.VERTICAL, false));
        IndexAdapter countriesAdapter = new IndexAdapter(context, categories, countriesRecycler, indexColor);
        countriesRecycler.setAdapter(countriesAdapter);
        countriesAdapter.setOnItemClickListener(this);
    }

    private void initIndexList() {
        indexId = getIntent().getIntExtra(ActivityUtils.EXTRA_INDEX_ID, 0);
        indexTitle = getIntent().getStringExtra(ActivityUtils.EXTRA_INDEX_TEXT);
        indexColor = getIntent().getIntExtra(ActivityUtils.EXTRA_INDEX_COLOR, R.color.colorPrimary);
        switch (indexId) {

            case R.id.cat_albukhari:
                categories = DBManager.getInstance(context).getAlbukhariIndexList();
                break;
            case R.id.cat_fiqh:
                categories = DBManager.getInstance(context).getFiqhIndexList();
                break;
            case R.id.cat_fiqh_1:
                categories = DBManager.getInstance(context).getFiqhIndexList(indexTitle);
                break;

            case R.id.cat_library:
                categories = DBManager.getInstance(context).getLibraryIndexList();
                break;
            case R.id.cat_library_1:
                categories = DBManager.getInstance(context).getLibraryIndexList(indexTitle);
                break;
            case R.id.cat_nawawiya_40:
                categories = Arrays.getNawaiya40List(context, "nawawiya_40/chapters.txt");
                break;
            default:
                categories = new ArrayList<>();
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (indexId) {

            case R.id.cat_albukhari:
                BukhariActivity.newIntent(this, categories.get(position).getTitle());
                break;
            case R.id.cat_fiqh:
                IndexActivity.newIntent(this, R.id.cat_fiqh_1, categories.get(position).getTitle(), R.drawable.cat_nawawiya_color);
                break;
            case R.id.cat_fiqh_1:
                String fiqhText = DBManager.getInstance(this).getFiqh(categories.get(position).getTitle()).getStory();
                TextActivity.newIntent(this, fiqhText, getString(R.string.cat_fiqh));
                break;
            case R.id.cat_library:
                IndexActivity.newIntent(this, R.id.cat_library_1, categories.get(position).getTitle(), R.drawable.cat_fatawy_color);
                break;
            case R.id.cat_library_1:
                String libraryText = DBManager.getInstance(this).getLibrary(categories.get(position).getTitle()).getStory();
                TextActivity.newIntent(this, libraryText, getString(R.string.cat_library));
                break;
            case R.id.cat_nawawiya_40:
                NawawiyaActivity.newIntent(this, categories.get(position).getTitle(), position);
                break;
            default:
                categories = new ArrayList<>();
        }
    }
}
