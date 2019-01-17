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

public class TabungAdapter extends RecyclerView.Adapter<TabungAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Tabung> tabungs;

    public TabungAdapter(Context context, ArrayList<Tabung> tabungs) {
        this.context = context;
        this.tabungs = tabungs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tabung, parent, false);
        return  new TabungAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mNamaUser.setText(tabungs.get(position).getNamaUser());
        holder.mTglTransaksi.setText(tabungs.get(position).getTglTransaksi());
        String id = tabungs.get(position).getIdTransaksi();
        holder.mIdTransaksi.setText("ID : "+id);
        String total = Integer.toString(tabungs.get(position).getHargaTotal());
        holder.totalHarga.setText("Rp. " + total);
        holder.mCVHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return tabungs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNamaUser, mTglTransaksi,mIdTransaksi,totalHarga;
        private CardView mCVHistory;

        public ViewHolder(View itemView) {
            super(itemView);

            mNamaUser = itemView.findViewById(R.id.tv_nama_user_tabung);
            mTglTransaksi = itemView.findViewById(R.id.tv_tgl_tabung);
            mIdTransaksi = itemView.findViewById(R.id.tv_id_transaksi_tabung);
            mCVHistory = itemView.findViewById(R.id.cv_tabung);
            totalHarga = itemView.findViewById(R.id.total_harga_tabung);
        }
    }
}
