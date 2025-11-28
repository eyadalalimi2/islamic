package com.eyad.alalimi.sonya7rvreview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.eyad.alalimi.sonya7rvreview.Adapter.Item;

import net.steamcrafted.materialiconlib.MaterialMenuInflater;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class asmaallah extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView listView;
    private Item[] values;

    @SuppressLint("NewApi")
    private static String normalize(CharSequence str) {
        String string = Normalizer.normalize(str, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string.toLowerCase(Locale.ENGLISH);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asmaa);

        listView = findViewById(android.R.id.list);
        values = new Item[99];

        String[] ar = getResources().getStringArray(R.array.names_ar);
        String[] name = getResources().getStringArray(R.array.names_name);
        String[] desc = getResources().getStringArray(R.array.names_desc);

        for (int i = 0; i < 99; i++) {
            Item item = new Item();
            item.arabic = ar[i];
            if (name.length > i)
                item.name = name[i];
            if (desc.length > i)
                item.desc = desc[i];
            values[i] = item;
        }
        listView.setAdapter(new Adapter(this, values));
        listView.setFastScrollEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MaterialMenuInflater.with(this)
                .setDefaultColor(0xFFFFFFFF)
                .inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Item> values = new ArrayList<>();
        for (Item val : this.values) {
            if (normalize(val.toString()).contains(normalize(newText))) {
                values.add(val);
            }
        }

        listView.setAdapter(new Adapter(this, values.toArray(new Item[values.size()])));
        return false;
    }

}
