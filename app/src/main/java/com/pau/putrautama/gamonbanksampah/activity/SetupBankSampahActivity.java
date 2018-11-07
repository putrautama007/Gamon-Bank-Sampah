package com.pau.putrautama.gamonbanksampah.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.BankSampah;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;

public class SetupBankSampahActivity extends AppCompatActivity {


    TextView mTvAlamat, mNamaBank;
    ImageView mLlAlamat;
    Button mBtnDaftar;
    CheckBox mKertasCheck,mBotolCheck;
    ImageView mBack;
    ProgressBar progressBar;
    String address, bankLocation, namaBank,userId,
            alamatBank,email,password, namaUserBank,noHp;
    double latitudeMap, longitudeMap;

    UserBankSampah userBankSampah;
    BankSampah bankSampah;
    boolean isMenerimaKertas, isMenerimaBotol;
    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabaseUserBankSampah,mFirebaseDatabaseBankSampah;
    private FirebaseDatabase mFirebaseUserBankInstance, mFirebaseBankSampahInstance ;


    private static final int PLACE_PICKER_REQUEST = 1000;
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_bank_sampah);

        mTvAlamat = findViewById(R.id.tv_lokasi);
        mNamaBank = findViewById(R.id.et_nama_bank);
        mBtnDaftar = findViewById(R.id.btn_daftarkan_bank_sampah);
        mBack = findViewById(R.id.back_register_bank);
        mKertasCheck = findViewById(R.id.cb_kertas_setup);
        mBotolCheck = findViewById(R.id.cb_botol_setup);
        progressBar = findViewById(R.id.setup_bank_progressbar);

        mAuth = FirebaseAuth.getInstance();


        mKertasCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isMenerimaKertas = true;
                }else {
                    isMenerimaKertas = false;
                }
            }
        });

        mBotolCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isMenerimaBotol = true;
                }else {
                    isMenerimaBotol = false;
                }
            }
        });


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLlAlamat = findViewById(R.id.iv_pin_lokasi);
        mClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();

        mTvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placePicker();
            }
        });

        mBtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarkanBank();
            }
        });

    }

    private void daftarkanBank(){
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        namaUserBank = getIntent().getStringExtra("nama_bank");
        noHp = getIntent().getStringExtra("nohp");
        namaBank = mNamaBank.getText().toString();
        alamatBank = mTvAlamat.getText().toString();


            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                saveDataToFirebase();
                                Intent intent = new Intent(SetupBankSampahActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Log.d("Firebase", "signUpWithEmail:success");
                                Toast.makeText(SetupBankSampahActivity.this, R.string.berhasil_daftar, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w("Firebase", "signUpWithEmail:failure", task.getException());
                                Toast.makeText(SetupBankSampahActivity.this, R.string.gagal_daftar, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }



    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }
    @Override
    protected void onStop() {
        mClient.disconnect();
        super.onStop();
    }

    private void placePicker(){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(SetupBankSampahActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(data,this);
                StringBuilder stBuilder = new StringBuilder();
                address = String.format("%s", place.getAddress());
                latitudeMap = place.getLatLng().latitude;
                longitudeMap = place.getLatLng().longitude;
                stBuilder.append(address);
                mTvAlamat.setText(stBuilder.toString());
            }
        }
    }


    private void saveDataToFirebase() {

        mFirebaseUserBankInstance = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("banksampah");
        mFirebaseDatabaseUserBankSampah = mFirebaseUserBankInstance.getReference("userbanksampah");

        userId = mAuth.getUid();
        userBankSampah = new UserBankSampah(namaUserBank,namaBank,email,password,noHp,alamatBank,
                latitudeMap, longitudeMap,isMenerimaKertas,
                isMenerimaBotol,0,0);

        bankSampah = new BankSampah(namaBank,email,noHp,alamatBank, latitudeMap, longitudeMap,
                isMenerimaKertas,isMenerimaBotol,0,0);

        mFirebaseDatabaseUserBankSampah.child(userId).setValue(userBankSampah);
        mFirebaseDatabaseBankSampah.child(userId).setValue(bankSampah);

    }
}

