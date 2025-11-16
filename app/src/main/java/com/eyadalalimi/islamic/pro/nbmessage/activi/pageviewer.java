package com.eyadalalimi.islamic.pro.nbmessage.activi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eyadalalimi.islamic.pro.R;

import java.util.ArrayList;

/**
 * Created by andrid on 2/4/2017.
 */
public class pageviewer extends PagerAdapter {
    ArrayList<String> Message;
    Context act;
    View layout;
    TextView pagenumber;
    int postion;
    String Colorl_Stronf;

    public pageviewer(Context mainActivity, ArrayList<String> noofsize, int pos, String Colro) {
        // TODO Auto-generated constructor stub
        Message = noofsize;
        act = mainActivity;
        postion = pos;
        Colorl_Stronf = Colro;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Message.size();
    }

    boolean First = true;

    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.page_txt, null);
        pagenumber = (TextView) layout.findViewById(R.id.textView3);
        Typeface type = Typeface.createFromAsset(act.getAssets(), "the_sans.otf");
        TextView size = (TextView) layout.findViewById(R.id.size_text);
        pagenumber.setTypeface(type);
        pagenumber.setTextColor(Color.parseColor(Colorl_Stronf));


        pagenumber.setText(Message.get(position));
        size.setText("(" + position + "/" + (Message.size() - 1) + ")");

        ((ViewPager) container).addView(layout, 0);
        return layout;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    // }

}