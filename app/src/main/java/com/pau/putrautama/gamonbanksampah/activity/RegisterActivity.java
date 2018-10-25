package com.pau.putrautama.gamonbanksampah.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pau.putrautama.gamonbanksampah.R;

public class RegisterActivity extends AppCompatActivity {

    EditText mNamaLengkap, mEmail, mPassword, mNohp;
    LinearLayout mHaveAccount;
    Button mBtnDaftar;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNamaLengkap = findViewById(R.id.et_name_register);
        mEmail = findViewById(R.id.et_email_register);
        mPassword = findViewById(R.id.et_password_register);
        mNohp = findViewById(R.id.et_nohp_register);
        mHaveAccount = findViewById(R.id.allready_have_account);
        mBtnDaftar = findViewById(R.id.btn_daftar);
        progressBar = findViewById(R.id.register_progressbar);

        mBtnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();

            }
        });


        allreadyHaveAccount();
    }

    public void registerAccount() {
        String namaLengkap = mNamaLengkap.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String noHp = mNohp.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.mohon_masukan_email, Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), R.string.email_unvalid, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.masukan_password, Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.minimal_karakter, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(noHp)) {
            Toast.makeText(getApplicationContext(), R.string.masukan_nohp, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(namaLengkap)) {
            Toast.makeText(getApplicationContext(), R.string.masukan_nama_lengkap, Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(RegisterActivity.this, SetupBankSampahActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), R.string.berhasil_daftar, Toast.LENGTH_SHORT).show();

    }

    public void allreadyHaveAccount() {
        mHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
