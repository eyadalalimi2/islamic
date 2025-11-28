package com.eyad.alalimi.sonya7rvreview.nbmessage.fragm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbmessage.apapter.adap_msg_section;
import com.eyad.alalimi.sonya7rvreview.nbmessage.database.Tablemsg;
import com.eyad.alalimi.sonya7rvreview.nbmessage.database.database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class msghragment extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager recylerViewLayoutManager;
    adap_msg_section adpater;
    ArrayList<String> Message = new ArrayList<String>();
    ArrayList<String> size_messs = new ArrayList<String>();
    AdView adView;
    msghragment context;
    database db;

    public msghragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_msgpart, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        // recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        try {
            db = new database(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Tablemsg> contacts = db.GetMessageSection();
        for (Tablemsg cn : contacts) {
            Message.add(cn.getTag());
        }

        for (int i = 1; i <= 21; i++) {
            size_messs.add(db.getSizeMessage(i));
        }

        adpater = new adap_msg_section(getActivity(), Message, size_messs);
        recyclerView.setAdapter(adpater);

        adView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        return rootView;
    }

}
