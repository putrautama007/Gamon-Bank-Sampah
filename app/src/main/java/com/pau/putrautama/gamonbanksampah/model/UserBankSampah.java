package com.pau.putrautama.gamonbanksampah.model;

import java.io.Serializable;

public class UserBankSampah implements Serializable {
    private String namaBankSampah;
    private String bankSampahEmail;
    private String bankSampahPassword;
    private String bankSampahNoTlp;
    private String alamatBank;
    private String locationBank;
    private boolean menerimaSampahKertas;
    private boolean menerimaSampahPlastik;
    private String hargaSampahKertas;
    private String hargaSampahPlastik;

    public UserBankSampah(String namaBankSampah, String bankSampahEmail, String bankSampahPassword,
                          String bankSampahNoTlp, String alamatBank, String locationBank,
                          boolean menerimaSampahKertas, boolean menerimaSampahPlastik,
                          String hargaSampahKertas, String hargaSampahPlastik) {
        this.namaBankSampah = namaBankSampah;
        this.bankSampahEmail = bankSampahEmail;
        this.bankSampahPassword = bankSampahPassword;
        this.bankSampahNoTlp = bankSampahNoTlp;
        this.alamatBank = alamatBank;
        this.locationBank = locationBank;
        this.menerimaSampahKertas = menerimaSampahKertas;
        this.menerimaSampahPlastik = menerimaSampahPlastik;
        this.hargaSampahKertas = hargaSampahKertas;
        this.hargaSampahPlastik = hargaSampahPlastik;
    }

    public UserBankSampah() {
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

    public String getLocationBank() {
        return locationBank;
    }

    public void setLocationBank(String locationBank) {
        this.locationBank = locationBank;
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

    public String getHargaSampahKertas() {
        return hargaSampahKertas;
    }

    public void setHargaSampahKertas(String hargaSampahKertas) {
        this.hargaSampahKertas = hargaSampahKertas;
    }

    public String getHargaSampahPlastik() {
        return hargaSampahPlastik;
    }

    public void setHargaSampahPlastik(String hargaSampahPlastik) {
        this.hargaSampahPlastik = hargaSampahPlastik;
    }

}
