package com.eyad.alalimi.sonya7rvreview.nbmessage.fragm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eyad.alalimi.sonya7rvreview.R;
import com.eyad.alalimi.sonya7rvreview.nbmessage.apapter.adap_msg_fav;
import com.eyad.alalimi.sonya7rvreview.nbmessage.database.Tablemsg;
import com.eyad.alalimi.sonya7rvreview.nbmessage.database.database;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager recylerViewLayoutManager;
    database db;
    ArrayList<String> fov = new ArrayList<String>();
    public fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Toast.makeText(getActivity(), "asdasdsad", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        fov.clear();
        List<Tablemsg> contacts = db.getFovarate();
        for (Tablemsg cn : contacts) {
            fov.add(cn.getTag());
        }

        adap_msg_fav adpater = new adap_msg_fav(getActivity(), fov);
        recyclerView.setAdapter(adpater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        try {
            db = new database(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Tablemsg> contacts = db.getFovarate();
        for (Tablemsg cn : contacts) {
            fov.add(cn.getTag());
        }
        AdView adView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        adap_msg_fav adpater = new adap_msg_fav(getActivity(), fov);
        recyclerView.setAdapter(adpater);
        return v;
    }

}
