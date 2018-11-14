package com.pau.putrautama.gamonbanksampah.model;

public class User {
    private String namaLengkap;
    private String email;
    private String password;
    private String noTlp;
    private int poin;
    private int saldo;


    public User(String namaLengkap, String email, String password,
                String noTlp, int poin, int saldo) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.noTlp = noTlp;
        this.poin = poin;
        this.saldo = saldo;
    }

    public User() {
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTlp() {
        return noTlp;
    }

    public void setNoTlp(String noTlp) {
        this.noTlp = noTlp;
    }
}
