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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.adapter.TabungAdapter;
import com.pau.putrautama.gamonbanksampah.adapter.TransaksiLangsungdapter;
import com.pau.putrautama.gamonbanksampah.model.Tabung;
import com.pau.putrautama.gamonbanksampah.model.TransaksiLangsung;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NabungFragment extends Fragment {

    private RecyclerView mRVHistory;
    private ArrayList<Tabung> tabungs = new ArrayList<>();
    private TabungAdapter adapter;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    private String userId;

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

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("userbanksampah");

        loadData();
        mRVHistory = view.findViewById(R.id.rv_tabung);
        adapter = new TabungAdapter(getContext(), tabungs);
        mRVHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadData(){
        userId = mAuth.getUid();
        mFirebaseDatabase.child(userId).child("tabung").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tiapDataSnapshot:dataSnapshot.getChildren()) {
                    Tabung tabung = tiapDataSnapshot.getValue(Tabung.class);
                    tabungs.add(tabung);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
