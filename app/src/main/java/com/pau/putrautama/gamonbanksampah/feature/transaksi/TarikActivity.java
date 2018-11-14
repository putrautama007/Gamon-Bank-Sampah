package com.pau.putrautama.gamonbanksampah.feature.transaksi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.activity.MainActivity;
import com.pau.putrautama.gamonbanksampah.model.Tabung;
import com.pau.putrautama.gamonbanksampah.model.User;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;
import com.pau.putrautama.gamonbanksampah.model.UserData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TarikActivity extends AppCompatActivity {

    private ImageView back;
    private EditText inputSaldo;
    private Button btnTarikSaldo;
    private TextView namaUserTextView,tanggalGabung, saldoTextView;
    String pelanggangBank;

    private DatabaseReference mFirebaseDatabaseUser,mFirebaseDatabaseBankSampah;
    private FirebaseDatabase mFirebaseInstanceUser, mFirebaseBankSampahInstance;
    private FirebaseAuth mAuth;
    private String userId, namaUser,namaBankSampah,saveCurrentDate, saveCurrentTime,tglTransaksi
            ,idTransaksi, namaUserTerdaftar,tglBergabungUserTerdaftar;
    int hargaSampahKertas, hargaSampahPlastik, saldo,beratBotolUser,beratKertasUser,poinUser;
    String idPelangganTerdaftar, saldoString;
    Tabung tabung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik);

        back = findViewById(R.id.back_tarik);
        btnTarikSaldo  = findViewById(R.id.btn_tarik_saldo);
        namaUserTextView = findViewById(R.id.nama_user_transaksi);
        tanggalGabung = findViewById(R.id.tgl_bergabung_transaksi);
        saldoTextView = findViewById(R.id.total_saldo_transaksi);
        inputSaldo = findViewById(R.id.input_transaksi);

        mAuth = FirebaseAuth.getInstance();
        pelanggangBank = getIntent().getStringExtra("user_id");


        retrieveBankSampah();
        retrieveUserData();
        retrieveTerdaftarUserBank();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TarikActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnTarikSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaksiNabungToFirebase();
            }
        });
    }


    private void retrieveTerdaftarUserBank(){
        userId = mAuth.getUid();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");

        mFirebaseDatabaseBankSampah.child(userId).child("terdaftar").child(pelanggangBank).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserData userBank = dataSnapshot.getValue(UserData.class);
                try {
                    saldo = userBank.getSaldo();
                    beratKertasUser = userBank.getJumlahKertas();
                    beratBotolUser = userBank.getJumlahBlastik();
                    idPelangganTerdaftar = userBank.getIdUser();
                    namaUserTerdaftar = userBank.getNamaUser();
                    tglBergabungUserTerdaftar = userBank.getTglBergabung();
                    saldoString = String.valueOf(saldo);
                    namaUserTextView.setText(namaUserTerdaftar);
                    tanggalGabung.setText(tglBergabungUserTerdaftar);
                    saldoTextView.setText("Rp."+saldoString+",-");
                }catch (Exception e){
                    e.getStackTrace();
                }

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

    private void retrieveUserData(){
        mFirebaseInstanceUser = FirebaseDatabase.getInstance();
        mFirebaseDatabaseUser = mFirebaseInstanceUser.getReference("users");


        mFirebaseDatabaseUser.child(pelanggangBank).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                namaUser = user.getNamaLengkap();
                poinUser = user.getPoin();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void transaksiNabungToFirebase(){
        retrieveUserData();
        retrieveBankSampah();
        getIdTransaksi();
        retrieveTerdaftarUserBank();
        getDate();


        int transaksiPenarikan = Integer.parseInt(inputSaldo.getText().toString());

        int beratKertas = 0;
        int beratBotol = 0;
        int hargaKertas = 0;
        int hargaPlastik = 0;
        int totalHarga = Integer.parseInt(inputSaldo.getText().toString());
        int poin = 0;
        int totalSaldo = saldo - totalHarga;
        int totalBeratPlastik = beratBotolUser;
        int totalBeratKertas = beratKertasUser;
        int totalPoin = poinUser;

        mFirebaseInstanceUser = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");
        mFirebaseDatabaseUser = mFirebaseInstanceUser.getReference("users");

        userId = mAuth.getUid();


        tabung = new Tabung(idTransaksi,namaUser,namaBankSampah,tglTransaksi,"KREDIT"
                ,beratKertas,hargaKertas,beratBotol,hargaPlastik,totalHarga,poin,totalSaldo);

        Log.d("idPelanggan", "transaksiNabungToFirebase: "+idPelangganTerdaftar);


        if(saldo >= 10000) {
            if (transaksiPenarikan >= 10000) {
                if (saldo > transaksiPenarikan) {
                    if (pelanggangBank.equalsIgnoreCase(idPelangganTerdaftar)) {

                        mFirebaseDatabaseBankSampah.child(userId).child("tarikSaldo")
                                .child(idTransaksi).setValue(tabung);

                        mFirebaseDatabaseUser.child(pelanggangBank).child("bankSampah").child(userId).child("tabung")
                                .child(idTransaksi).setValue(tabung);

                        mFirebaseDatabaseBankSampah.child(userId).child("terdaftar").child(pelanggangBank).child("saldo").setValue(totalSaldo);

                        mFirebaseDatabaseUser.child(pelanggangBank).child("bankSampah").child(userId).child("saldo").setValue(totalSaldo);

                        Intent intent = new Intent(TarikActivity.this, MainActivity.class);
                        Toast.makeText(TarikActivity.this, "Berhasil melakukan  penarikan saldo", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(TarikActivity.this, "Saldo yang akan tidak cukup"
                            , Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(TarikActivity.this, "Minimal penarikan adalah Rp. 10000"
                        , Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(TarikActivity.this, "Maaf Saldo tidak mencukupi untuk melakukan penarikan"
                    , Toast.LENGTH_SHORT).show();
        }

    }
}
