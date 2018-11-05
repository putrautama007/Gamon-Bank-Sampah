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
import com.pau.putrautama.gamonbanksampah.adapter.HistoryAdapter;
import com.pau.putrautama.gamonbanksampah.model.Tabung;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarikLangsungFragment extends Fragment {

    private RecyclerView mRVHistory;
    private ArrayList<Tabung> tabung = new ArrayList<>();
    private HistoryAdapter adapter;


    public TarikLangsungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarik_langsung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        setUpData();
        mRVHistory = view.findViewById(R.id.rv_tarik_langsung);
        adapter = new HistoryAdapter(getContext(), tabung);
        mRVHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    private void setUpData() {
//        tabung.add(new Tabung("John Doe","Bank Sampah Sumber Jaya",38271,"20 Oktober 2018 | 12:30",
//                2,1,3000,2500,55000));
//    }
}
