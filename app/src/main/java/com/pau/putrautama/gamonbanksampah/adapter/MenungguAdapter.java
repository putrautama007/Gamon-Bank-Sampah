package com.pau.putrautama.gamonbanksampah.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.UserListTerdaftar;

import java.util.ArrayList;

public class MenungguAdapter extends RecyclerView.Adapter<MenungguAdapter.ViewHolder> {


    private Context context;
    private ArrayList<UserListTerdaftar> userListTerdaftars;

    public MenungguAdapter(Context context, ArrayList<UserListTerdaftar> userListTerdaftars) {
        this.context = context;
        this.userListTerdaftars = userListTerdaftars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menunggu, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namaUser.setText(userListTerdaftars.get(position).getNamaUser());
        holder.tglMendaftar.setText(userListTerdaftars.get(position).getTglBergabung());
        holder.btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userListTerdaftars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView namaUser,tglMendaftar;
        private Button btnTerima, btnTolak;

        public ViewHolder(View itemView) {
            super(itemView);

            namaUser = itemView.findViewById(R.id.nama_user_menunggu);
            tglMendaftar = itemView.findViewById(R.id.tgl_menunggu);
            btnTerima = itemView.findViewById(R.id.btn_terima);
            btnTolak = itemView.findViewById(R.id.btn_tolak);
        }
    }
}
