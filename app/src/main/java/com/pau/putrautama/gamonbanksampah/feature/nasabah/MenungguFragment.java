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
import com.pau.putrautama.gamonbanksampah.adapter.MenungguAdapter;
import com.pau.putrautama.gamonbanksampah.model.UserListMenunggu;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenungguFragment extends Fragment {

    private RecyclerView mRVMenunggu;
    private ArrayList<UserListMenunggu> userListMenunggu = new ArrayList<>();
    private MenungguAdapter adapter;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    private String userId;

    public MenungguFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menunggu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("userbanksampah");



        mRVMenunggu = view.findViewById(R.id.rv_menunggu);
        adapter = new MenungguAdapter(getContext(), userListMenunggu);
        mRVMenunggu.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRVMenunggu.setAdapter(adapter);
        setUpData();
        adapter.notifyDataSetChanged();
    }

    private void setUpData() {
        userId = mAuth.getUid();

        mFirebaseDatabase.child(userId).child("menunggu").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                UserListMenunggu userListMenunggu1 = new UserListMenunggu();
                userListMenunggu1 = dataSnapshot.getValue(UserListMenunggu.class);

                userListMenunggu.add(userListMenunggu1);
                mRVMenunggu.setAdapter(adapter);
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
