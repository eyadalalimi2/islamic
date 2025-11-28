package com.eyadalalimi.islamic.sonya7rvreview.nbholyquran;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.OnItemClickListener;

import java.util.List;



/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranIndexAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    private static final int MENU_ITEM_VIEW_TYPE = 1;
    private static final String TAG = "QuranIndexAdapter_Log";
    private final Context context;
    private final List<QuranIndex> mRecyclerViewItems;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;

    public QuranIndexAdapter(Context context, List<QuranIndex> recyclerViewItems, RecyclerView mRecyclerView) {
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
                R.layout.item_quran_index, viewGroup, false);
        return new QuranIndexHolder(postLayoutView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int superPosition) {
        final int position = holder.getAdapterPosition();
        if (holder instanceof QuranIndexHolder) {
            QuranIndexHolder countryHolder = (QuranIndexHolder) holder;
            final QuranIndex quranIndex = mRecyclerViewItems.get(position);
            countryHolder.itemView.setOnClickListener(this);
//            countryHolder.getContainer().setBackgroundColor(context.getResources().getColor(
//                    (position % 2 == 0) ? R.color.quran_index_bg_one : R.color.quran_index_bg_tow
//            ));
            countryHolder.getIndexNum().setText(String.valueOf(quranIndex.getId()));
            countryHolder.getIndexName().setText(quranIndex.getSura());
            countryHolder.getIndexAyat().setText(quranIndex.getNumAyat());
            countryHolder.getIndexType().setText(quranIndex.getSuraType());
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

    public void notifyItems(List<QuranIndex> mRecyclerViewItems) {
        this.mRecyclerViewItems.clear();
        this.mRecyclerViewItems.addAll(mRecyclerViewItems);
        notifyDataSetChanged();
    }
}
