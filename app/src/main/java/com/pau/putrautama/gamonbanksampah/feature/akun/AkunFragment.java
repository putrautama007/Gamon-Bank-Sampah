package com.pau.putrautama.gamonbanksampah.feature.akun;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.feature.akun.detailAkun.EditAkunActivity;
import com.pau.putrautama.gamonbanksampah.feature.akun.detailAkun.PasswordActivity;
import com.pau.putrautama.gamonbanksampah.activity.LoginActivity;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    TextView mNamaLengkap,mEmail, mNoTlp, mEdit;
    CardView mCvSandi, mCvLokasi, mCvLogout;

    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNamaLengkap = view.findViewById(R.id.tv_nama_lengkap);
        mEmail = view.findViewById(R.id.tv_email_user);
        mNoTlp = view.findViewById(R.id.tv_nohp_user);
        mEdit = view.findViewById(R.id.tv_edit);
        mCvSandi = view.findViewById(R.id.cv_password);
        mCvLokasi = view.findViewById(R.id.cv_lokasi);
        mCvLogout = view.findViewById(R.id.cv_logout);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("userbanksampah");

        retriveData();

        mClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditAkunActivity.class);
                startActivity(intent);
            }
        });

        mCvSandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });

        mCvLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placePicker();
            }
        });

        mCvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customAllertDialog();
            }
        });
    }

    private void retriveData(){
        userId = mAuth.getUid();
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah user = dataSnapshot.getValue(UserBankSampah.class);
                mNamaLengkap.setText(user.getNamaBankSampah());
                mEmail.setText(user.getBankSampahEmail());
                mNoTlp.setText(user.getBankSampahNoTlp());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void customAllertDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logout_dialog);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        TextView yes = dialog.findViewById(R.id.tv_iya_keluar);
        TextView no  = dialog.findViewById(R.id.tv_tidak_keluar);

        yes.setEnabled(true);
        no.setEnabled(true);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mClient.connect();
    }
    @Override
    public void onStop() {
        mClient.disconnect();
        super.onStop();
    }

    private void placePicker(){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data,getContext());
                StringBuilder stBuilder = new StringBuilder();
                String address = String.format("%s", place.getAddress());
                stBuilder.append(address);
            }
        }
    }
}
