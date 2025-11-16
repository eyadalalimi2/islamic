package com.eyadalalimi.islamic.pro.nbholyquran;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.OnItemClickListener;

import java.util.List;



/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranSearchAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    private static final int MENU_ITEM_VIEW_TYPE = 1;
    private static final String TAG = "QuranSearchAdapter_Log";
    private final List<QuranSearch> mRecyclerViewItems;
    private RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;

    public QuranSearchAdapter(List<QuranSearch> recyclerViewItems, RecyclerView mRecyclerView) {
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
                R.layout.item_quran_search, viewGroup, false);
        return new QuranSearchHolder(postLayoutView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int superPosition) {
        final int position = holder.getAdapterPosition();
        if (holder instanceof QuranSearchHolder) {
            QuranSearchHolder countryHolder = (QuranSearchHolder) holder;
            final QuranSearch quranSearch = mRecyclerViewItems.get(position);
            countryHolder.itemView.setOnClickListener(this);
            countryHolder.getAyaText().setText(quranSearch.getAya());
            countryHolder.getAyaNum().setText(String.valueOf(quranSearch.getAyaNum()));
            countryHolder.getSuraName().setText(quranSearch.getSura());
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

    public void notifyItems(List<QuranSearch> mRecyclerViewItems) {
        this.mRecyclerViewItems.clear();
        this.mRecyclerViewItems.addAll(mRecyclerViewItems);
        notifyDataSetChanged();
    }
}
