package com.pau.putrautama.gamonbanksampah.model;

public class User {
    private String namaLengkap;
    private String email;
    private String password;
    private String noTlp;
    private int poin;


    public User(String namaLengkap, String email, String password, String noTlp, int poin) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.noTlp = noTlp;
        this.poin = poin;
    }

    public User() {
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
