package com.pau.putrautama.gamonbanksampah.model;

import java.io.Serializable;

public class HistoryList implements Serializable {
    private String namaUser;
    private String namaBankSampah;
    private int idTransaksi;
    private String tgl;
    private int beratKertas;
    private int beratBotol;
    private int hargaKertas;
    private int hargaBotol;
    private int totalHarga;

    public HistoryList(String namaUser, String namaBankSampah, int idTransaksi,
                       String tgl, int beratKertas, int beratBotol, int hargaKertas, int hargaBotol
            , int totalHarga) {
        this.namaUser = namaUser;
        this.namaBankSampah = namaBankSampah;
        this.idTransaksi = idTransaksi;
        this.tgl = tgl;
        this.beratKertas = beratKertas;
        this.beratBotol = beratBotol;
        this.hargaKertas = hargaKertas;
        this.hargaBotol = hargaBotol;
        this.totalHarga = totalHarga;
    }

    public HistoryList() {
    }

    public String getNamaBankSampah() {
        return namaBankSampah;
    }

    public void setNamaBankSampah(String namaBankSampah) {
        this.namaBankSampah = namaBankSampah;
    }


    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public int getBeratKertas() {
        return beratKertas;
    }

    public void setBeratKertas(int beratKertas) {
        this.beratKertas = beratKertas;
    }

    public int getBeratBotol() {
        return beratBotol;
    }

    public void setBeratBotol(int beratBotol) {
        this.beratBotol = beratBotol;
    }

    public int getHargaKertas() {
        return hargaKertas;
    }

    public void setHargaKertas(int hargaKertas) {
        this.hargaKertas = hargaKertas;
    }

    public int getHargaBotol() {
        return hargaBotol;
    }

    public void setHargaBotol(int hargaBotol) {
        this.hargaBotol = hargaBotol;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
