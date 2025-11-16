package com.eyadalalimi.islamic.pro.nbholyquran;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eyadalalimi.islamic.pro.R;

import java.util.List;


/**
 * Created by anwar_se on 6/15/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class QuranPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<QuranPage> pageRes;
    private View.OnClickListener onClickListener;

    public QuranPagerAdapter(Context context, List<QuranPage> pageRes) {
        mContext = context;
        this.pageRes = pageRes;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setId(R.id.quran_page);
        imageView.setImageResource(pageRes.get(position).getPageRes());
        imageView.setOnClickListener(onClickListener);
        collection.addView(imageView);
        return imageView;
    }


    public void setOnClickListener(View.OnClickListener l) {
        onClickListener = l;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return pageRes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
