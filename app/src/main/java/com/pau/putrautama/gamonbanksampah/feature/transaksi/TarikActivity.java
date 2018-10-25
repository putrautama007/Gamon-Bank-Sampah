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

public class TarikActivity extends AppCompatActivity {

    private ImageView back;
    private Button btnTarikSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik);

        back = findViewById(R.id.back_tarik);
        btnTarikSaldo  = findViewById(R.id.btn_tarik_saldo);

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
                Intent intent = new Intent(TarikActivity.this,MainActivity.class);
                Toast.makeText(TarikActivity.this, "Berhasil melakukan penarikan", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
