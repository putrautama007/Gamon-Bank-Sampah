package com.pau.putrautama.gamonbanksampah.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.feature.history.detailHistory.HistoryDetailActivity;
import com.pau.putrautama.gamonbanksampah.model.Tabung;
import com.pau.putrautama.gamonbanksampah.model.TransaksiLangsung;

import java.util.ArrayList;

public class TransaksiLangsungdapter extends RecyclerView.Adapter<TransaksiLangsungdapter.ViewHolder> {

    private Context context;
    private ArrayList<TransaksiLangsung> transaksiLangsungs;

    public TransaksiLangsungdapter(Context context, ArrayList<TransaksiLangsung> transaksiLangsungs) {
        this.context = context;
        this.transaksiLangsungs = transaksiLangsungs;
    }

    @NonNull
    @Override
    public TransaksiLangsungdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiLangsungdapter.ViewHolder holder, final int position) {

        holder.mNamaUser.setText(transaksiLangsungs.get(position).getNamaUser());
        holder.mTglTransaksi.setText(transaksiLangsungs.get(position).getTglTransaksi());
        String id = transaksiLangsungs.get(position).getIdTransaksi();
        holder.mIdTransaksi.setText("ID : "+id);
        String total = Integer.toString(transaksiLangsungs.get(position).getHargaTotal());
        holder.totalHarga.setText("Rp. " + total);
        holder.mCVHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);
                intent.putExtra(HistoryDetailActivity.nama_user, transaksiLangsungs.get(position).getNamaUser());
                intent.putExtra(HistoryDetailActivity.nama_bank, transaksiLangsungs.get(position).getNamaBankSampah());
                intent.putExtra(HistoryDetailActivity.tgl_transaksi, transaksiLangsungs.get(position).getTglTransaksi());
                intent.putExtra(HistoryDetailActivity.id_transaksi, transaksiLangsungs.get(position).getIdTransaksi());
                intent.putExtra(HistoryDetailActivity.total_harga, String.valueOf(transaksiLangsungs.get(position).getHargaTotal()));
                intent.putExtra(HistoryDetailActivity.berat_kertas, String.valueOf(transaksiLangsungs.get(position).getBeratKertas()));
                intent.putExtra(HistoryDetailActivity.berat_botol, String.valueOf(transaksiLangsungs.get(position).getBeratBotol()));
                intent.putExtra(HistoryDetailActivity.harga_kertas, String.valueOf(transaksiLangsungs.get(position).getHargaKertas()));
                intent.putExtra(HistoryDetailActivity.harga_botol, String.valueOf(transaksiLangsungs.get(position).getHargaBotol()));
                intent.putExtra(HistoryDetailActivity.ITEM_HISTORY, transaksiLangsungs.get(0));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiLangsungs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNamaUser, mTglTransaksi,mIdTransaksi,totalHarga;
        private CardView mCVHistory;


        public ViewHolder(View itemView) {
            super(itemView);

            mNamaUser = itemView.findViewById(R.id.tv_nama_user_history);
            mTglTransaksi = itemView.findViewById(R.id.tv_tgl_history);
            mIdTransaksi = itemView.findViewById(R.id.tv_id_transaksi_history);
            mCVHistory = itemView.findViewById(R.id.cv_history);
            totalHarga = itemView.findViewById(R.id.total_harga);
        }
    }
}