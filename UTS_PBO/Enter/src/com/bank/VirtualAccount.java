package com.bank;

import java.util.Scanner;

public class VirtualAccount extends Rekening {
    private String kodeCompany;
    private String merchantName;

    public VirtualAccount(String noRekening, String namaPemilik, double saldo, String mataUang, String kodeCompany, String merchantName) {
        super(noRekening, namaPemilik, saldo, mataUang);
        this.kodeCompany = kodeCompany;
        this.merchantName = merchantName;
    }

    public VirtualAccount() {
        super();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Kode Company Asal : ");
        this.kodeCompany = scanner.nextLine();
        System.out.print("Masukkan Nama Merchant/VA  : ");
        this.merchantName = scanner.nextLine();
    }

    public void setKodeCompany(String kodeCompany) {
        this.kodeCompany = kodeCompany;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getKodeCompany() {
        return kodeCompany;
    }

    public String getMerchantName() {
        return merchantName;
    }

    @Override
    public double cekBiayaAdmin(double nominal) {
        if (merchantName != null && (merchantName.equalsIgnoreCase("GOPAY") || merchantName.equalsIgnoreCase("SHOPEEPAY"))) {
            return 1000.0;
        }
        return 0.0;
    }
}