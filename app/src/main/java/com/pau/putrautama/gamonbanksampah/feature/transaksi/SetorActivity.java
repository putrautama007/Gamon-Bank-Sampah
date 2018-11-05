package com.pau.putrautama.gamonbanksampah.feature.transaksi;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.activity.MainActivity;
import com.pau.putrautama.gamonbanksampah.model.BankSampah;
import com.pau.putrautama.gamonbanksampah.model.TransaksiLangsung;
import com.pau.putrautama.gamonbanksampah.model.User;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SetorActivity extends AppCompatActivity {

    private ImageView back;
    private EditText jumlahKertas,jumlahPlastik;
    private Button btnTabung, btnTarikLangsung;
    String pelanggangBank;

    private DatabaseReference mFirebaseDatabaseUser,mFirebaseDatabaseBankSampah;
    private FirebaseDatabase mFirebaseInstanceUser, mFirebaseBankSampahInstance;
    private FirebaseAuth mAuth;
    private String userId, namaUser,namaBankSampah,saveCurrentDate, saveCurrentTime,tglTransaksi
            ,idTransaksi;
    int hargaSampahKertas, hargaSampahPlastik;
    TransaksiLangsung transaksiLangsung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor);

        pelanggangBank = getIntent().getStringExtra("user_id");


        back = findViewById(R.id.back_setor);
        btnTabung = findViewById(R.id.btn_menabung);
        jumlahKertas = findViewById(R.id.jumlah_kertas_transaksi);
        jumlahPlastik = findViewById(R.id.jumlah_botol_transaksi);

        btnTarikLangsung = findViewById(R.id.btn_transaksi_langsung);
        mAuth = FirebaseAuth.getInstance();

        retrieveBankSampah();
        retrieveUserData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetorActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnTabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetorActivity.this,MainActivity.class);
                Toast.makeText(SetorActivity.this, "Berhasil menabung", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnTarikLangsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaksiLangsungToFirebase();
                Intent intent = new Intent(SetorActivity.this,MainActivity.class);
                Toast.makeText(SetorActivity.this, "Berhasil melakukan transaksi", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
    private void retrieveUserData(){
        mFirebaseInstanceUser = FirebaseDatabase.getInstance();
        mFirebaseDatabaseUser = mFirebaseInstanceUser.getReference("users");


        mFirebaseDatabaseUser.child(pelanggangBank).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                namaUser = user.getNamaLengkap();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void retrieveBankSampah(){
        userId = mAuth.getUid();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");

        mFirebaseDatabaseBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah userBankSampah = dataSnapshot.getValue(UserBankSampah.class);
                namaBankSampah = userBankSampah.getNamaBankSampah();
                hargaSampahKertas = userBankSampah.getHargaSampahKertas();
                hargaSampahPlastik = userBankSampah.getHargaSampahPlastik();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getDate(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM yyyy ");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm ");
        saveCurrentTime = currentTime.format(calendar.getTime());

        tglTransaksi = saveCurrentDate + "|" + saveCurrentTime;
    }

    private void getIdTransaksi(){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddmmmyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HHmm");
        saveCurrentTime = currentTime.format(calendar.getTime());


        idTransaksi = saveCurrentDate+saveCurrentTime;
    }

    private void transaksiLangsungToFirebase() {
        retrieveUserData();
        retrieveBankSampah();
        getIdTransaksi();
        getDate();

        int beratKertas = Integer.parseInt(jumlahKertas.getText().toString());
        int beratBotol = Integer.parseInt(jumlahPlastik.getText().toString());
        int hargaKertas = beratKertas * hargaSampahKertas;
        int hargaPlastik = beratBotol * hargaSampahPlastik;
        int totalHarga = hargaKertas + hargaPlastik;
        int poin = totalHarga/100;

        mFirebaseInstanceUser = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");
        mFirebaseDatabaseUser = mFirebaseInstanceUser.getReference("users");

        userId = mAuth.getUid();

        transaksiLangsung = new TransaksiLangsung(idTransaksi,namaUser,namaBankSampah,tglTransaksi
        ,beratKertas,hargaKertas,beratBotol,hargaPlastik,totalHarga,poin);


        mFirebaseDatabaseBankSampah.child(userId).child("transaksi_langsung")
                .child(idTransaksi).setValue(transaksiLangsung);

        mFirebaseDatabaseUser.child(pelanggangBank).child("transaksi_langsung")
                .child(idTransaksi).setValue(transaksiLangsung);

    }

}
