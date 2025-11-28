package com.eyad.alalimi.sonya7rvreview.nbholyquran;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbsahihboukhari.OnItemClickListener;
import com.eyad.alalimi.sonya7rvreview.other.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anwar_se on 6/20/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class MuslimDialog extends Dialog implements OnItemClickListener {

    private List<Category> categories = new ArrayList<>();
    private DialogItemAdapter countriesAdapter;
    private OnClickListener listener;

    public MuslimDialog(@NonNull Context context) {
        super(context);
    }

    public MuslimDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MuslimDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dialog);

        getWindow().setBackgroundDrawable(new ColorDrawable(
                getContext().getResources().getColor(android.R.color.transparent)));

        RecyclerView countriesRecycler = findViewById(R.id.rec_dialog);
        countriesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        countriesAdapter = new DialogItemAdapter(getContext(), categories, countriesRecycler);
        countriesRecycler.setAdapter(countriesAdapter);
        countriesAdapter.setOnItemClickListener(this);

    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        if (countriesAdapter != null)
            countriesAdapter.notifyItems(categories);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (listener != null)
            listener.onClick(this, categories.get(position).getCategoryId());
    }
}
