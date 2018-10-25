package com.pau.putrautama.gamonbanksampah.feature.akun.detailAkun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.pau.putrautama.gamonbanksampah.R;

public class EditAkunActivity extends AppCompatActivity {

    EditText mEdtNamaLengkap, mEdtEmail, mEdtNoTlp;
    Button mBtnSave;
    ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        mEdtNamaLengkap = findViewById(R.id.edt_nama);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtNoTlp = findViewById(R.id.edt_notlp);
        mBtnSave = findViewById(R.id.btn_save_profile);
        mBack = findViewById(R.id.back_edt_profile);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}