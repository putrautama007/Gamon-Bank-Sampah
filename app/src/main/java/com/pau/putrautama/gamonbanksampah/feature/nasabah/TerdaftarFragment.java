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

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.adapter.TerdaftarAdapter;
import com.pau.putrautama.gamonbanksampah.model.UserListTerdaftar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TerdaftarFragment extends Fragment {

    private RecyclerView mRVTerdaftar;
    private ArrayList<UserListTerdaftar> userListTerdaftars = new ArrayList<>();
    private TerdaftarAdapter adapter;


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

        setUpData();
        mRVTerdaftar = view.findViewById(R.id.rv_terdaftar);
        adapter = new TerdaftarAdapter(getContext(), userListTerdaftars);
        mRVTerdaftar.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVTerdaftar.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setUpData() {
        userListTerdaftars.add(new UserListTerdaftar("John Doe","02 Agustus 2018",
                6,4,19000));
        userListTerdaftars.add(new UserListTerdaftar("Amaris Ane","20 September 2018",
                4,2,11000));

    }
}
