package com.pau.putrautama.gamonbanksampah.model;

public class UserListTerdaftar {
    private String idBankSampah;
    private String idUser;
    private String namaBank;
    private String namaUser;
    private String tglBergabung;
    private int jumlahKertas;
    private int jumlahBlastik;
    private int saldo;


//    public UserListTerdaftar(String idUser, String namaBank, String namaUser, String tglBergabung,
//                             int jumlahKertas, int jumlahBlastik, int saldo) {
//        this.idUser = idUser;
//        this.namaBank = namaBank;
//        this.namaUser = namaUser;
//        this.tglBergabung = tglBergabung;
//        this.jumlahKertas = jumlahKertas;
//        this.jumlahBlastik = jumlahBlastik;
//        this.saldo = saldo;
//    }

    public UserListTerdaftar(String idBankSampah, String idUser, String namaBank, String namaUser,
                             String tglBergabung, int jumlahKertas, int jumlahBlastik, int saldo) {
        this.idBankSampah = idBankSampah;
        this.idUser = idUser;
        this.namaBank = namaBank;
        this.namaUser = namaUser;
        this.tglBergabung = tglBergabung;
        this.jumlahKertas = jumlahKertas;
        this.jumlahBlastik = jumlahBlastik;
        this.saldo = saldo;
    }

    public UserListTerdaftar() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
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

    public int getJumlahKertas() {
        return jumlahKertas;
    }

    public void setJumlahKertas(int jumlahKertas) {
        this.jumlahKertas = jumlahKertas;
    }

    public int getJumlahBlastik() {
        return jumlahBlastik;
    }

    public void setJumlahBlastik(int jumlahBlastik) {
        this.jumlahBlastik = jumlahBlastik;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
