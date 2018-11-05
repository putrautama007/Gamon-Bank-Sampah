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

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Tabung> tabung;

    public HistoryAdapter(Context context, ArrayList<Tabung> tabung) {
        this.context = context;
        this.tabung = tabung;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, final int position) {
//        if (tabung.get(position).getBeratKertas() > 0 && tabung.get(position).getBeratBotol() < 0 ){
//            Glide.with(context).load(R.drawable.flat_paper).into(holder.mIcon);
//        }else if (tabung.get(position).getBeratKertas() < 0 && tabung.get(position).getBeratBotol() > 0){
//            Glide.with(context).load(R.drawable.flat_bottle).into(holder.mIcon);
//        }else if (tabung.get(position).getBeratKertas() > 0 &&tabung.get(position).getBeratBotol() > 0){
//            Glide.with(context).load(R.drawable.flat_both).into(holder.mIcon);
//        }

        holder.mNamaUser.setText(tabung.get(position).getNamaUser());
        holder.mTglTransaksi.setText(tabung.get(position).getTglTransaksi());
        String id = tabung.get(position).getIdTransaksi();
        holder.mIdTransaksi.setText("ID : "+id);
        String total = Integer.toString(tabung.get(position).getHargaTotal());
        holder.totalHarga.setText("Rp. " + total);
        holder.mCVHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);
//                intent.putExtra("nama_user", tabung.get(position).getNamaUser());
//                intent.putExtra("id_transaksi", tabung.get(position).getIdTransaksi());
//                intent.putExtra("nama_bank", tabung.get(position).getNamaBankSampah());
//                intent.putExtra("tgl_transaksi", tabung.get(position).getTgl());
//                intent.putExtra("berat_botol", tabung.get(position).getBeratBotol());
//                intent.putExtra("berat_kertas", tabung.get(position).getBeratKertas());
//                intent.putExtra("harga_botol", tabung.get(position).getHargaBotol());
//                intent.putExtra("harga_kertas", tabung.get(position).getHargaKertas());
//                intent.putExtra("total_harga", tabung.get(position).getTotalHarga());
                intent.putExtra(HistoryDetailActivity.ITEM_HISTORY, tabung.get(0));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tabung.size();
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