package com.eyadalalimi.islamic.pro.nbmessage.apapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import com.eyadalalimi.islamic.pro.R;
import com.eyadalalimi.islamic.pro.nbmessage.activi.activitymsg;

import java.util.ArrayList;

public class adap_msg_section extends RecyclerView.Adapter<adap_msg_section.ViewHolder> {
    private final ArrayList<String> part_message;
    private final ArrayList<String> SizeMessage;
    private final Context context;
    private InterstitialAd mInterstitialAd;

    public adap_msg_section(Context context1, ArrayList<String> SubjectValues1, ArrayList<String> size) {
        this.part_message = SubjectValues1;
        this.context = context1;
        this.SizeMessage = size;
        loadInterstitial(context1);
    }

    private void loadInterstitial(Context ctx) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                ctx,
                ctx.getString(R.string.intersianal_ad_unit_id),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView part;
        public TextView size_message;

        public ViewHolder(View v) {
            super(v);
            part = v.findViewById(R.id.textView);
            size_message = v.findViewById(R.id.size_message);

            Typeface type = Typeface.createFromAsset(context.getAssets(), "the_sans.otf");
            size_message.setTypeface(type);
            part.setTypeface(type);

            part.setOnClickListener(view -> {
                int item = getAdapterPosition() + 1;
                Intent i = new Intent(context, activitymsg.class);
                i.putExtra("part", item);
                context.startActivity(i);
                if (mInterstitialAd != null) {
                    mInterstitialAd.show((Activity) context);
                    mInterstitialAd = null;
                    loadInterstitial(context); // إعادة تحميل إعلان جديد
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.part.setText("رسائل " + part_message.get(position));
        holder.size_message.setText(SizeMessage.get(position));
    }

    @Override
    public int getItemCount() {
        return part_message.size();
    }
}
