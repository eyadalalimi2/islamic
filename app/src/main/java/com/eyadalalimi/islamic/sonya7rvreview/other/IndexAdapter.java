package com.eyadalalimi.islamic.sonya7rvreview.other;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eyadalalimi.islamic.sonya7rvreview.R;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.DefaultIndex;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.OnItemClickListener;

import java.util.List;


/**
 * Created by anwar_se on 6/25/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class IndexAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    private static final int MENU_ITEM_VIEW_TYPE = 1;
    private static final String TAG = "DefaultIndexAdapter_Log";
    private final Context context;
    private final List<DefaultIndex> mRecyclerViewItems;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;
    private final int leftImageDrawableResId; // Renamed from indexColor to be more descriptive

    public IndexAdapter(Context context, List<DefaultIndex> recyclerViewItems, RecyclerView mRecyclerView, int leftImageDrawableResId) {
        this.context = context;
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerViewItems = recyclerViewItems;
        this.leftImageDrawableResId = leftImageDrawableResId; // Store the drawable resource ID
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
            final DefaultIndex country = mRecyclerViewItems.get(position);
            countryHolder.itemView.setOnClickListener(this);
            countryHolder.getCategoryName().setText(country.getTitle());

            // Set the image for iv_row_right2 using the passed drawable resource ID
            countryHolder.getIvRowRight2().setImageResource(leftImageDrawableResId);
            // Set the image for iv_row_right using the default drawable resource ID from XML
            countryHolder.getIvRowRight().setImageResource(R.drawable.row_right);
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

    // DialogHolder inner class - Modified to include both ImageViews
    public static class DialogHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName;
        private final ImageView ivRowRight2; // Field for iv_row_right2
        private final ImageView ivRowRight;  // Field for iv_row_right

        public DialogHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.tv_category);
            ivRowRight2 = itemView.findViewById(R.id.iv_row_right2);
            ivRowRight = itemView.findViewById(R.id.iv_row_right);
        }

        public TextView getCategoryName() {
            return categoryName;
        }

        public ImageView getIvRowRight2() {
            return ivRowRight2;
        }

        public ImageView getIvRowRight() {
            return ivRowRight;
        }
    }
}