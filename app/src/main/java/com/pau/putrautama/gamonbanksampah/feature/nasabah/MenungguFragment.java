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
import com.pau.putrautama.gamonbanksampah.adapter.MenungguAdapter;
import com.pau.putrautama.gamonbanksampah.model.UserList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenungguFragment extends Fragment {

    private RecyclerView mRVMenunggu;
    private ArrayList<UserList> userLists = new ArrayList<>();
    private MenungguAdapter adapter;


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

        setUpData();
        mRVMenunggu = view.findViewById(R.id.rv_menunggu);
        adapter = new MenungguAdapter(getContext(),userLists);
        mRVMenunggu.setLayoutManager(new LinearLayoutManager(getContext()));
        mRVMenunggu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setUpData() {
        userLists.add(new UserList("Anne John","19 Oktober 2018",
                0,0,0));
        userLists.add(new UserList("Anne Dean","18 Oktober 2018",
                0,0,0));
    }
}
