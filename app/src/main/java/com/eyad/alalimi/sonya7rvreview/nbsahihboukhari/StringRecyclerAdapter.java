package com.eyad.alalimi.sonya7rvreview.nbsahihboukhari;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eyad.alalimi.sonya7rvreview.R;

import java.util.List;

/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class StringRecyclerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    private static final int MENU_ITEM_VIEW_TYPE = 1;
    private static final String TAG = "StringAdapter_Log";
    private final Context context;
    private final List<DefaultIndex> mRecyclerViewItems;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;

    public StringRecyclerAdapter(Context context, List<DefaultIndex> recyclerViewItems, RecyclerView mRecyclerView) {
        this.context = context;
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerViewItems = recyclerViewItems;
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems == null ? 0 : mRecyclerViewItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return MENU_ITEM_VIEW_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View postLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_dialog, viewGroup, false);
        return new StringHolder(postLayoutView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int superPosition) {
        final int position = holder.getAdapterPosition();
        if (holder instanceof StringHolder) {
            StringHolder countryHolder = (StringHolder) holder;
            final DefaultIndex country = mRecyclerViewItems.get(position);
            countryHolder.itemView.setOnClickListener(this);
            countryHolder.getText().setText(country.getTitle());
        }

    }

    @Override
    public void onClick(View v) {
        int clickedPosition = mRecyclerView.getChildAdapterPosition(v);
        Log.d(TAG, "clickedPosition: " + clickedPosition);
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(v, clickedPosition);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void notifyItems(List<DefaultIndex> mRecyclerViewItems) {
        this.mRecyclerViewItems.clear();
        this.mRecyclerViewItems.addAll(mRecyclerViewItems);
        notifyDataSetChanged();
    }
}
