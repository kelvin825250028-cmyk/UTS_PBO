package com.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Customer extends Person implements Login {
    private String alamat;
    private String username;
    private String password;

    public Customer(String kode, String nama, String noHP, String alamat, String username, String password) {
        super(kode, nama, noHP);
        this.alamat = alamat;
        this.username = username;
        this.password = password;
    }

    public Customer() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    @Override
    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== FORM REGISTRASI CUSTOMER ===");
        System.out.print("Masukkan Kode Customer : ");
        setKode(scanner.nextLine());
        System.out.print("Masukkan Nama Lengkap   : ");
        setNama(scanner.nextLine());
        System.out.print("Masukkan No. HP         : ");
        setNoHP(scanner.nextLine());
        System.out.print("Masukkan Alamat         : ");
        this.alamat = scanner.nextLine();
        System.out.print("Buat Username           : ");
        this.username = scanner.nextLine();
        System.out.print("Buat Password           : ");
        this.password = scanner.nextLine();

        saveAkunToFile("akun.txt");
        System.out.println("Registrasi berhasil! Akun Anda telah tersimpan di akun.txt.");
    }

    @Override
    public void signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== FORM LOGIN CUSTOMER ===");
        System.out.print("Masukkan Username : ");
        String inputUser = scanner.nextLine();
        System.out.print("Masukkan Password : ");
        String inputPass = scanner.nextLine();

        if (validateLogin("akun.txt", inputUser, inputPass)) {
            System.out.println("Login berhasil! Selamat datang, " + getNama() + " (" + this.username + ").");
        } else {
            System.out.println("Akun tidak ditemukan!");
            this.username = null; // Reset penanda jika login gagal
        }
    }

    private void saveAkunToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(username + "," + password + "," + getKode() + "," + getNama() + "," + getNoHP() + "," + alamat);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan akun ke file: " + e.getMessage());
        }
    }

    private boolean validateLogin(String fileName, String user, String pass) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    if (data[0].trim().equals(user) && data[1].trim().equals(pass)) {
                        this.username = data[0].trim();
                        this.password = data[1].trim();
                        if (data.length >= 6) {
                            setKode(data[2].trim());
                            setNama(data[3].trim());
                            setNoHP(data[4].trim());
                            this.alamat = data[5].trim();
                        }
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file akun: " + e.getMessage());
        }
        return false;
    }
}