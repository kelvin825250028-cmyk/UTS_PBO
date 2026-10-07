package com.bank;

import java.util.Scanner;

public class RekeningBank extends Rekening {
    private String namaBank;
    private String cabang;

    public RekeningBank(String noRekening, String namaPemilik, double saldo, String mataUang, String namaBank, String cabang) {
        super(noRekening, namaPemilik, saldo, mataUang);
        this.namaBank = namaBank;
        this.cabang = cabang;
    }

    public RekeningBank() {
        super();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Nama Bank      :");
        this.namaBank = scanner.nextLine();
        System.out.print("Masukkan Cabang Bank    :");
        this.cabang = scanner.nextLine();
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public String getCabang() {
        return cabang;
    }

    @Override
    public double cekBiayaAdmin(double nominal) {
        return 2500.0;
    }
}