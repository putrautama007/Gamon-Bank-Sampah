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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;

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
        mFirebaseUserBankInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseUserBankSampah = mFirebaseUserBankInstance.getReference("userbanksampah");

        retriveHargaItem();
        checkButtonKertas();
        checkButtonBotol();

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
        int hargaKertas = Integer.parseInt(mHargaKertas.getText().toString());
        int hargaBotol = Integer.parseInt(mHargaBotol.getText().toString());

        mFirebaseUserBankInstance = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();


        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("banksampah");
        mFirebaseDatabaseUserBankSampah = mFirebaseUserBankInstance.getReference("userbanksampah");

        userId = mAuth.getUid();
        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahKertas").setValue(hargaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahKertas").setValue(isMenerimaKertas);
        mFirebaseDatabaseUserBankSampah.child(userId).child("hargaSampahPlastik").setValue(hargaBotol);
        mFirebaseDatabaseUserBankSampah.child(userId).child("menerimaSampahPlastik").setValue(isMenerimaBotol);

        mFirebaseDatabaseBankSampah.child(userId).child("hargaSampahKertas").setValue(hargaKertas);
        mFirebaseDatabaseBankSampah.child(userId).child("menerimaSampahKertas").setValue(isMenerimaKertas);
        mFirebaseDatabaseBankSampah.child(userId).child("hargaSampahPlastik").setValue(hargaBotol);
        mFirebaseDatabaseBankSampah.child(userId).child("menerimaSampahPlastik").setValue(isMenerimaBotol);

    }

    private void retriveHargaItem(){
        userId = mAuth.getUid();
        mFirebaseDatabaseUserBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah userBankSampah = dataSnapshot.getValue(UserBankSampah.class);
                mHargaKertas.setText(String.valueOf(userBankSampah.getHargaSampahKertas()));
                mHargaBotol.setText(String.valueOf(userBankSampah.getHargaSampahPlastik()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void checkButtonKertas(){
        userId = mAuth.getUid();
        mFirebaseDatabaseUserBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah user = dataSnapshot.getValue(UserBankSampah.class);
                if (user.isMenerimaSampahKertas()){
                    mCbKertas.setChecked(true);
                }else {
                    mCbKertas.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void checkButtonBotol(){
        userId = mAuth.getUid();
        mFirebaseDatabaseUserBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah user = dataSnapshot.getValue(UserBankSampah.class);
                if (user.isMenerimaSampahPlastik()){
                    mCbBotol.setChecked(true);
                }else {
                    mCbBotol.setChecked(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

