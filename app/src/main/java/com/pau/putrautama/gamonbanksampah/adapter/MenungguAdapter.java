package com.pau.putrautama.gamonbanksampah.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.model.UserBankSampah;
import com.pau.putrautama.gamonbanksampah.model.UserListMenunggu;
import com.pau.putrautama.gamonbanksampah.model.UserListTerdaftar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MenungguAdapter extends RecyclerView.Adapter<MenungguAdapter.ViewHolder> {


    private Context context;
    private ArrayList<UserListMenunggu> userListMenunggu;
    UserListTerdaftar userListTerdaftars;

    private DatabaseReference mFirebaseDatabaseUser,mFirebaseDatabaseBankSampah;
    private FirebaseDatabase mFirebaseInstanceUser, mFirebaseBankSampahInstance;

    private FirebaseAuth mAuth;
    private String userId,nasabahhId,saveCurrentDate, saveCurrentTime,tglBergabung, namaBank;

    public MenungguAdapter(Context context, ArrayList<UserListMenunggu> userListMenunggu) {
        this.context = context;
        this.userListMenunggu = userListMenunggu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menunggu, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namaUser.setText(userListMenunggu.get(position).getNamaUser());
        holder.tglMendaftar.setText(userListMenunggu.get(position).getTglBergabung());
        holder.btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpData();
                removeData();
                Toast.makeText(context, "Nasabah diterima", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData();
                Toast.makeText(context, "Nasabah ditolak", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListMenunggu.size();
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
    private void getNamaBank(){
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");
        mFirebaseDatabaseBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBankSampah userBankSampah = dataSnapshot.getValue(UserBankSampah.class);
                namaBank = userBankSampah.getNamaBankSampah();
                Log.d("namabank", "onDataChange: "+namaBank);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setUpData() {
        getDate();
//        getNamaBank();
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        mFirebaseInstanceUser = FirebaseDatabase.getInstance();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");
        mFirebaseDatabaseUser = mFirebaseInstanceUser.getReference("users");
        mFirebaseDatabaseBankSampah.child(userId).child("menunggu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tiapDataSnapshot:dataSnapshot.getChildren()) {
                    UserListMenunggu userListMenunggu1 = tiapDataSnapshot.getValue(UserListMenunggu.class);
                    userListMenunggu.add(userListMenunggu1);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mFirebaseDatabaseBankSampah.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                   namaBank =dataSnapshot1.child("namaBankSampah").getValue().toString();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        userListTerdaftars = new UserListTerdaftar(userId,userListMenunggu.get(0).getIdUser(),
               namaBank,userListMenunggu.get(0).getNamaUser()
                ,tglBergabung,0,0,0);

        mFirebaseDatabaseBankSampah.child(userId).child("terdaftar")
                .child(userListMenunggu.get(0).getIdUser()).setValue(userListTerdaftars);

        mFirebaseDatabaseUser.child(userListMenunggu.get(0).getIdUser()).child("bankSampah")
                .child(userId).setValue(userListTerdaftars);

        Log.d("user", "setUpData: " +namaBank);
    }

    private void getkey(){
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");
        mFirebaseDatabaseBankSampah.child(userId).child("menunggu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tiapDataSnapshot:dataSnapshot.getChildren()) {
                     nasabahhId = tiapDataSnapshot.child("idUser").getValue().toString();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void removeData() {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getUid();
        mFirebaseBankSampahInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseBankSampah = mFirebaseBankSampahInstance.getReference("userbanksampah");

        Query applesQuery = mFirebaseDatabaseBankSampah.child(userId).child("menunggu").equalTo(nasabahhId);
        getkey();
        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tiapDataSnapshot:dataSnapshot.getChildren()) {
                   tiapDataSnapshot.getRef().removeValue();
                }
                notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getDate(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM yyyy ");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm ");
        saveCurrentTime = currentTime.format(calendar.getTime());

        tglBergabung = saveCurrentDate + "|" + saveCurrentTime;
    }
}
