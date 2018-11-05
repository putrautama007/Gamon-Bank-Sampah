package com.pau.putrautama.gamonbanksampah.model;

import java.io.Serializable;

public class UserBankSampah implements Serializable {
    private String namaUserBankSampah;
    private String namaBankSampah;
    private String bankSampahEmail;
    private String bankSampahPassword;
    private String bankSampahNoTlp;
    private String alamatBank;
    private double latitude;
    private double longitude;
    private boolean menerimaSampahKertas;
    private boolean menerimaSampahPlastik;
    private int hargaSampahKertas;
    private int hargaSampahPlastik;

    public UserBankSampah(String namaUserBankSampah, String namaBankSampah, String bankSampahEmail,
                          String bankSampahPassword, String bankSampahNoTlp, String alamatBank,
                          double latitude, double longitude, boolean menerimaSampahKertas,
                          boolean menerimaSampahPlastik, int hargaSampahKertas,
                          int hargaSampahPlastik) {
        this.namaUserBankSampah = namaUserBankSampah;
        this.namaBankSampah = namaBankSampah;
        this.bankSampahEmail = bankSampahEmail;
        this.bankSampahPassword = bankSampahPassword;
        this.bankSampahNoTlp = bankSampahNoTlp;
        this.alamatBank = alamatBank;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menerimaSampahKertas = menerimaSampahKertas;
        this.menerimaSampahPlastik = menerimaSampahPlastik;
        this.hargaSampahKertas = hargaSampahKertas;
        this.hargaSampahPlastik = hargaSampahPlastik;
    }

    public UserBankSampah() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNamaUserBankSampah() {
        return namaUserBankSampah;
    }

    public void setNamaUserBankSampah(String namaUserBankSampah) {
        this.namaUserBankSampah = namaUserBankSampah;
    }

    public String getBankSampahEmail() {
        return bankSampahEmail;
    }

    public void setBankSampahEmail(String bankSampahEmail) {
        this.bankSampahEmail = bankSampahEmail;
    }

    public String getBankSampahPassword() {
        return bankSampahPassword;
    }

    public void setBankSampahPassword(String bankSampahPassword) {
        this.bankSampahPassword = bankSampahPassword;
    }

    public String getBankSampahNoTlp() {
        return bankSampahNoTlp;
    }

    public void setBankSampahNoTlp(String bankSampahNoTlp) {
        this.bankSampahNoTlp = bankSampahNoTlp;
    }

    public String getNamaBankSampah() {
        return namaBankSampah;
    }

    public void setNamaBankSampah(String namaBankSampah) {
        this.namaBankSampah = namaBankSampah;
    }

    public String getAlamatBank() {
        return alamatBank;
    }

    public void setAlamatBank(String alamatBank) {
        this.alamatBank = alamatBank;
    }

    public boolean isMenerimaSampahKertas() {
        return menerimaSampahKertas;
    }

    public void setMenerimaSampahKertas(boolean menerimaSampahKertas) {
        this.menerimaSampahKertas = menerimaSampahKertas;
    }

    public boolean isMenerimaSampahPlastik() {
        return menerimaSampahPlastik;
    }

    public void setMenerimaSampahPlastik(boolean menerimaSampahPlastik) {
        this.menerimaSampahPlastik = menerimaSampahPlastik;
    }

    public int getHargaSampahKertas() {
        return hargaSampahKertas;
    }

    public void setHargaSampahKertas(int hargaSampahKertas) {
        this.hargaSampahKertas = hargaSampahKertas;
    }

    public int getHargaSampahPlastik() {
        return hargaSampahPlastik;
    }

    public void setHargaSampahPlastik(int hargaSampahPlastik) {
        this.hargaSampahPlastik = hargaSampahPlastik;
    }

}
