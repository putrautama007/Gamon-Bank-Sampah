package com.pau.putrautama.gamonbanksampah.feature.transaksi;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.pau.putrautama.gamonbanksampah.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    private RelativeLayout rlSetor, rlTarikSaldo;



    public TransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rlSetor = view.findViewById(R.id.cv_stor_sampah);
        rlTarikSaldo = view.findViewById(R.id.cv_tabung);

        rlSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScanSetorActivity.class);
                startActivity(intent);
            }
        });

        rlTarikSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScanTarikActivity.class);
                startActivity(intent);
            }
        });


    }
}

