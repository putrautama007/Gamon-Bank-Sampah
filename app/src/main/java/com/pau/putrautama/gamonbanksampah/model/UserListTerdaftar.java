package com.pau.putrautama.gamonbanksampah.model;

public class UserListTerdaftar {
    private String namaUser;
    private String tglBergabung;
    private int totalKertas;
    private int totalBotol;
    private int totalSaldo;

    public UserListTerdaftar(String namaUser, String tglBergabung, int totalKertas, int totalBotol, int totalSaldo) {
        this.namaUser = namaUser;
        this.tglBergabung = tglBergabung;
        this.totalKertas = totalKertas;
        this.totalBotol = totalBotol;
        this.totalSaldo = totalSaldo;
    }

    public UserListTerdaftar() {
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getTglBergabung() {
        return tglBergabung;
    }

    public void setTglBergabung(String tglBergabung) {
        this.tglBergabung = tglBergabung;
    }

    public int getTotalKertas() {
        return totalKertas;
    }

    public void setTotalKertas(int totalKertas) {
        this.totalKertas = totalKertas;
    }

    public int getTotalBotol() {
        return totalBotol;
    }

    public void setTotalBotol(int totalBotol) {
        this.totalBotol = totalBotol;
    }

    public int getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(int totalSaldo) {
        this.totalSaldo = totalSaldo;
    }
}
