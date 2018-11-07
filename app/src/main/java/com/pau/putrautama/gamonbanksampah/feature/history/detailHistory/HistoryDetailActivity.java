package com.pau.putrautama.gamonbanksampah.feature.history.detailHistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.Tabung;

public class HistoryDetailActivity extends AppCompatActivity {
    public static final String ITEM_HISTORY = "item_movie";
    private TextView mNamaDetail, mNamaBankDetail, mTglDetail,
            mBeratKertas, mBeratBotol, mHargaKertas, mHargaBotol, mTotalHarga,mIdTransaksi;

    private ImageView mIcon;
    private Button mBtnBack;
    private String namaUser,namaBank,idTransaksi,tglTransaksi,
            beratKertas,beratBotol,hargaKertas,hargaBotol,totalHarga;

    public static final String nama_user = "nama_user";
    public static final String nama_bank = "nama_bank";
    public static final String tgl_transaksi = "tgl_transaksi";
    public static final String id_transaksi = "id_transaksi";
    public static final String total_harga = "total_harga";
    public static final String berat_kertas = "berat_kertas";
    public static final String berat_botol = "berat_botol";
    public static final String harga_kertas = "harga_kertas";
    public static final String harga_botol = "harga_botol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        mNamaDetail = findViewById(R.id.tv_detail_nama);
        mNamaBankDetail = findViewById(R.id.tv_detail_nama_bank_sampah);
        mTglDetail = findViewById(R.id.tv_detail_tgl);
        mBeratKertas = findViewById(R.id.tv_detail_berat_kertas);
        mBeratBotol = findViewById(R.id.tv_detail_berat_botol);
        mHargaKertas = findViewById(R.id.tv_detail_harga_kertas);
        mHargaBotol = findViewById(R.id.tv_detail_harga_botol);
        mTotalHarga = findViewById(R.id.tv_detail_total_harga);
        mIdTransaksi = findViewById(R.id.tv_detail_id_transaksi);
        mIcon = findViewById(R.id.iv_detail_riwayat);
        mBtnBack = findViewById(R.id.btn_detail_back);

        namaUser = getIntent().getStringExtra(nama_user);
        namaBank = getIntent().getStringExtra(nama_bank);
        idTransaksi = getIntent().getStringExtra(id_transaksi);
        tglTransaksi = getIntent().getStringExtra(tgl_transaksi);
        beratKertas = getIntent().getStringExtra(berat_kertas);
        beratBotol = getIntent().getStringExtra(berat_botol);
        hargaKertas = getIntent().getStringExtra(harga_kertas);
        hargaBotol = getIntent().getStringExtra(harga_botol);
        totalHarga = getIntent().getStringExtra(total_harga);

        mNamaDetail.setText(namaUser);
        mNamaBankDetail.setText(namaBank);
        mTglDetail.setText(tglTransaksi);
        mIdTransaksi.setText("ID : " + idTransaksi);
        String beratKertasString = beratKertas;
        mBeratKertas.setText(beratKertasString+getResources().getString(R.string.kilogram));
        String beratBotolString = beratBotol;
        mBeratBotol.setText(beratBotolString+getResources().getString(R.string.kilogram));
        String hargaKertasString = hargaKertas;
        mHargaKertas.setText(getResources().getString(R.string.rupiah)+hargaKertasString);
        String hargaBotolString = hargaBotol;
        mHargaBotol.setText(getResources().getString(R.string.rupiah)+hargaBotolString);
        String totalHargaString = totalHarga;
        mTotalHarga.setText(getResources().getString(R.string.rupiah)+totalHargaString);

        if (Integer.valueOf(beratKertas) > 0 && Integer.valueOf(beratBotol) < 0 ){
            Glide.with(this).load(R.drawable.flat_paper).into(mIcon);
        }else if (Integer.valueOf(beratKertas) < 0 && Integer.valueOf(beratBotol) > 0){
            Glide.with(this).load(R.drawable.flat_bottle).into(mIcon);
        }else if (Integer.valueOf(beratKertas) > 0 && Integer.valueOf(beratBotol) > 0){
            Glide.with(this).load(R.drawable.flat_both).into(mIcon);
        }



        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

