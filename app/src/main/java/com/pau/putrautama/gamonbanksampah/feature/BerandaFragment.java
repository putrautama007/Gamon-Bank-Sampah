package com.pau.putrautama.gamonbanksampah.feature;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pau.putrautama.gamonbanksampah.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    EditText mHargaKertas, mHargaBotol;
    CheckBox mCbKertas, mCbBotol;
    Button mBtnSave;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHargaKertas = view.findViewById(R.id.et_harga_kertas);
        mHargaBotol = view.findViewById(R.id.et_harga_botol);
        mCbKertas = view.findViewById(R.id.cb_kertas);
        mCbBotol = view.findViewById(R.id.cb_botol);
        mBtnSave = view.findViewById(R.id.btn_save_sampah);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAllertDialog();
            }
        });
    }
    private void customAllertDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.save_jenis_sampah_dailog);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        TextView close = dialog.findViewById(R.id.tv_close);






        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

