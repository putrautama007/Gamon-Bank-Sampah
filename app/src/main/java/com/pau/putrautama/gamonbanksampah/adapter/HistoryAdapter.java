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
import com.pau.putrautama.gamonbanksampah.model.HistoryList;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HistoryList> historyList;

    public HistoryAdapter(Context context, ArrayList<HistoryList> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, final int position) {
//        if (historyList.get(position).getBeratKertas() > 0 && historyList.get(position).getBeratBotol() < 0 ){
//            Glide.with(context).load(R.drawable.flat_paper).into(holder.mIcon);
//        }else if (historyList.get(position).getBeratKertas() < 0 && historyList.get(position).getBeratBotol() > 0){
//            Glide.with(context).load(R.drawable.flat_bottle).into(holder.mIcon);
//        }else if (historyList.get(position).getBeratKertas() > 0 &&historyList.get(position).getBeratBotol() > 0){
//            Glide.with(context).load(R.drawable.flat_both).into(holder.mIcon);
//        }

        holder.mNamaUser.setText(historyList.get(position).getNamaUser());
        holder.mTglTransaksi.setText(historyList.get(position).getTgl());
        String id = Integer.toString(historyList.get(position).getIdTransaksi());
        holder.mIdTransaksi.setText("ID : "+id);
        String total = Integer.toString(historyList.get(position).getTotalHarga());
        holder.totalHarga.setText("Rp. " + total);
        holder.mCVHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoryDetailActivity.class);
//                intent.putExtra("nama_user", historyList.get(position).getNamaUser());
//                intent.putExtra("id_transaksi", historyList.get(position).getIdTransaksi());
//                intent.putExtra("nama_bank", historyList.get(position).getNamaBankSampah());
//                intent.putExtra("tgl_transaksi", historyList.get(position).getTgl());
//                intent.putExtra("berat_botol", historyList.get(position).getBeratBotol());
//                intent.putExtra("berat_kertas", historyList.get(position).getBeratKertas());
//                intent.putExtra("harga_botol", historyList.get(position).getHargaBotol());
//                intent.putExtra("harga_kertas", historyList.get(position).getHargaKertas());
//                intent.putExtra("total_harga", historyList.get(position).getTotalHarga());
                intent.putExtra(HistoryDetailActivity.ITEM_HISTORY, historyList.get(0));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
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