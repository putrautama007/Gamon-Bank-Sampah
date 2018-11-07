package com.pau.putrautama.gamonbanksampah.feature.history;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.adapter.TransaksiLangsungdapter;
import com.pau.putrautama.gamonbanksampah.model.TransaksiLangsung;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NabungFragment extends Fragment {

    private RecyclerView mRVHistory;
    private ArrayList<TransaksiLangsung> transaksiLangsungs = new ArrayList<>();
    private TransaksiLangsungdapter adapter;


    public NabungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nabung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        setUpData();
        mRVHistory = view.findViewById(R.id.rv_tabung);
        adapter = new TransaksiLangsungdapter(getContext(), transaksiLangsungs);
        mRVHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    private void setUpData() {
//        transaksiLangsungs.add(new Tabung("John Doe","Bank Sampah Sumber Jaya",38271,"21 Oktober 2018 | 14:32",
//                4,2,6000,5000,11000));
//        transaksiLangsungs.add(new Tabung("John Doe","Bank Sampah Sumber Jaya",38271,"19 Oktober 2018 2018 | 10:32",
//                2,2,3000,2000,8000));
//    }
}
