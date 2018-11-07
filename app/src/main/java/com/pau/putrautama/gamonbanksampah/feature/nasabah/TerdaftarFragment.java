package com.pau.putrautama.gamonbanksampah.feature.nasabah;


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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.adapter.TerdaftarAdapter;
import com.pau.putrautama.gamonbanksampah.model.UserListMenunggu;
import com.pau.putrautama.gamonbanksampah.model.UserListTerdaftar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerdaftarFragment extends Fragment {

    private RecyclerView mRVTerdaftar;
    private ArrayList<UserListTerdaftar> userListTerdaftars = new ArrayList<>();
    private TerdaftarAdapter adapter;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    private String userId;


    public TerdaftarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terdaftar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("userbanksampah");



        mRVTerdaftar = view.findViewById(R.id.rv_terdaftar);
        adapter = new TerdaftarAdapter(getContext(), userListTerdaftars);
        mRVTerdaftar.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpData();
        adapter.notifyDataSetChanged();
    }

    private void setUpData() {
        userId = mAuth.getUid();

        mFirebaseDatabase.child(userId).child("terdaftar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserListTerdaftar userListTerdaftar = new UserListTerdaftar();
                userListTerdaftar = dataSnapshot.getValue(UserListTerdaftar.class);

                userListTerdaftars.add(userListTerdaftar);
                mRVTerdaftar.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
