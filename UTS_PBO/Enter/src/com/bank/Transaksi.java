package com.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Transaksi {
    private String noReferensi;
    private String tanggalWaktu;
    private String status;
    private String jenisTransaksi;
    private double nominal;
    private String berita;
    private Rekening pengirim;
    private Rekening penerima;

    public Transaksi(String noReferensi, String tanggalWaktu, String status, String jenisTransaksi, double nominal, String berita, Rekening pengirim, Rekening penerima) {
        this.noReferensi = noReferensi;
        this.tanggalWaktu = tanggalWaktu;
        this.status = status;
        this.jenisTransaksi = jenisTransaksi;
        this.nominal = nominal;
        this.berita = berita;
        this.pengirim = pengirim;
        this.penerima = penerima;
    }

    public Transaksi() {
        this.status = "pending";
    }

    public void setNoReferensi(String noReferensi) {
        this.noReferensi = noReferensi;
    }

    public void setTanggalWaktu(String tanggalWaktu) {
        this.tanggalWaktu = tanggalWaktu;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }

    public void setPengirim(Rekening pengirim) {
        this.pengirim = pengirim;
    }

    public void setPenerima(Rekening penerima) {
        this.penerima = penerima;
    }

    public String getNoReferensi() {
        return noReferensi;
    }

    public String getTanggalWaktu() {
        return tanggalWaktu;
    }

    public String getStatus() {
        return status;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public double getNominal() {
        return nominal;
    }

    public String getBerita() {
        return berita;
    }

    public Rekening getPengirim() {
        return pengirim;
    }

    public Rekening getPenerima() {
        return penerima;
    }

    // METHOD INI WAJIB ADA UNTUK DIPANGGIL DI APP.JAVA
    public boolean prosesTransfer() {
        if (pengirim != null && pengirim.getSaldo() >= nominal) {
            pengirim.setSaldo(pengirim.getSaldo() - nominal);
            if (penerima != null) {
                penerima.setSaldo(penerima.getSaldo() + nominal);
            }
            this.status = "Transfer Berhasil";
            return true;
        } else {
            this.status = "Transfer Gagal - Saldo Tidak Cukup";
            return false;
        }
    }

    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println("=========================================");
            writer.println("STATUS TRANSAKSI: " + status);
            writer.println("No. Referensi   : " + noReferensi);
            writer.println("Tanggal / Waktu : " + tanggalWaktu);
            writer.println("Jenis Transaksi : " + jenisTransaksi);
            writer.println("Nominal         : " + (pengirim != null ? pengirim.getMataUang() : "IDR") + " " + String.format("%,.2f", nominal));
            writer.println("Berita          : " + berita);
            writer.println("Pengirim        : " + (pengirim != null ? pengirim.getNamaPemilik() + " (" + pengirim.getNoRekening() + ")" : "-"));
            writer.println("Penerima        : " + (penerima != null ? penerima.getNamaPemilik() + " (" + penerima.getNoRekening() + ")" : "-"));
            writer.println("=========================================\n");
            System.out.println("Data transaksi berhasil disimpan ke file: " + fileName);
        } catch (IOException e) {
            System.out.println(" Gagal menyimpan data transaksi: " + e.getMessage());
        }
    }

    public void readFromFile(String fileName) {
        System.out.println("\n=== Riwayat Transaksi ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file transaksi: " + e.getMessage());
        }
    }
}