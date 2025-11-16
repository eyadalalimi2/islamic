package com.eyadalalimi.islamic.pro.nbholyquran;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbsahihboukhari.OnItemClickListener;
import com.eyadalalimi.islamic.pro.other.Category;
import com.eyadalalimi.islamic.pro.other.DialogHolder;

import java.util.List;


/**
 * Created by anwar_se on 6/16/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class DialogItemAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    private static final int MENU_ITEM_VIEW_TYPE = 1;
    private static final String TAG = "CategoryAdapter_Log";
    private final Context context;
    private final List<Category> mRecyclerViewItems;
    private RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;

    public DialogItemAdapter(Context context, List<Category> recyclerViewItems, RecyclerView mRecyclerView) {
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
                R.layout.item_sub_category, viewGroup, false);
        return new DialogHolder(postLayoutView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int superPosition) {
        final int position = holder.getAdapterPosition();
        if (holder instanceof DialogHolder) {
            DialogHolder countryHolder = (DialogHolder) holder;
            final Category country = mRecyclerViewItems.get(position);
            countryHolder.itemView.setOnClickListener(this);
            countryHolder.getCategoryName().setText(country.getTitle());
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

    public void notifyItems(List<Category> mRecyclerViewItems) {
        this.mRecyclerViewItems.clear();
        this.mRecyclerViewItems.addAll(mRecyclerViewItems);
        notifyDataSetChanged();
    }
}
