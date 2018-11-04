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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pau.putrautama.gamonbanksampah.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    EditText mHargaKertas, mHargaBotol;
    CheckBox mCbKertas, mCbBotol;
    Button mBtnSave;

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseUserBankSampah,mFirebaseDatabaseBankSampah;
    private FirebaseDatabase mFirebaseUserBankInstance, mFirebaseBankSampahInstance ;
    String userId;
    boolean isMenerimaKertas, isMenerimaBotol;

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

        mAuth = FirebaseAuth.getInstance();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAllertDialog();
            }
        });

        mCbBotol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isMenerimaBotol = true;
                }else {
                    isMenerimaBotol = false;
                }
            }
        });
        mCbKertas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isMenerimaKertas = true;
                }else {
                    isMenerimaKertas = false;
                }
            }
        });
    }
    private void customAllertDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.save_jenis_sampah_dailog);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        updateData();

        TextView close = dialog.findViewById(R.id.tv_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateData(){
        String hargaKertas = mHargaKertas.getText().toString();
        String hargaBotol = mHargaBotol.getText().toString();

        mFirebaseUserBankInstance = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();


        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("banksampah");
        mFirebaseDatabaseUserBankSampah = mFirebaseUserBankInstance.getReference("userbanksampah");

        userId = mAuth.getUid();
        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahKertas").setValue(hargaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahKertas").setValue(isMenerimaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahPlastik").setValue(hargaBotol);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahPlastik").setValue(isMenerimaBotol);

        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahKertas").setValue(hargaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahKertas").setValue(isMenerimaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahPlastik").setValue(hargaBotol);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahPlastik").setValue(isMenerimaBotol);

    }
}

