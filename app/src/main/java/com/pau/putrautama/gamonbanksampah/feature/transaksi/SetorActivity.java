package com.pau.putrautama.gamonbanksampah.feature.transaksi;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.activity.MainActivity;


public class SetorActivity extends AppCompatActivity {

    private ImageView back;
    private Button btnTabung, btnTarikLangsung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setor);

        back = findViewById(R.id.back_setor);
        btnTabung = findViewById(R.id.btn_menabung);
        btnTarikLangsung = findViewById(R.id.btn_transaksi_langsung);


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
                Intent intent = new Intent(SetorActivity.this,MainActivity.class);
                Toast.makeText(SetorActivity.this, "Berhasil melakukan transaksi", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }



}
