package com.bank;

import java.util.Scanner;

public class Rekening {
    private String noRekening;
    private String namaPemilik;
    private double saldo;
    private String mataUang;

    public Rekening(String noRekening, String namaPemilik, double saldo, String mataUang) {
        this.noRekening = noRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
        this.mataUang = mataUang;
    }

    public Rekening() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan No Rekening Asal : ");
        this.noRekening = scanner.nextLine();
        System.out.print("Masukkan Nama Pemilik     : ");
        this.namaPemilik = scanner.nextLine();
        System.out.print("Masukkan Saldo Awal       : ");
        this.saldo = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        System.out.print("Masukkan Mata Uang        : ");
        this.mataUang = scanner.nextLine();
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getMataUang() {
        return mataUang;
    }

    public double cekBiayaAdmin(double nominal) {
        return 0.0;
    }
}