package com.pau.putrautama.gamonbanksampah.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.UserListTerdaftar;

import java.util.ArrayList;

public class TerdaftarAdapter extends RecyclerView.Adapter<TerdaftarAdapter.ViewHolder> {

    private Context context;
    private ArrayList<UserListTerdaftar> userListTerdaftars;

    public TerdaftarAdapter(Context context, ArrayList<UserListTerdaftar> userListTerdaftars) {
        this.context = context;
        this.userListTerdaftars = userListTerdaftars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_terdaftar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namaUser.setText(userListTerdaftars.get(0).getNamaUser());
        holder.tglBergabung.setText(userListTerdaftars.get(0).getTglBergabung());
        holder.cvTerdaftar.setOnClickListener(new View.OnClickListener() {
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

        private TextView namaUser, tglBergabung;
        private CardView cvTerdaftar;
        public ViewHolder(View itemView) {
            super(itemView);

            namaUser = itemView.findViewById(R.id.nama_user_terdaftar);
            tglBergabung = itemView.findViewById(R.id.tgl_terdaftar);
            cvTerdaftar = itemView.findViewById(R.id.cv_terdaftar);
        }
    }
}
