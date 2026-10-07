import com.bank.Customer;
import com.bank.Rekening;
import com.bank.RekeningBank;
import com.bank.Transaksi;
import com.bank.VirtualAccount;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customerAktif = new Customer();

        // 1. AUTENTIKASI CUSTOMER
        while (customerAktif.getUsername() == null || customerAktif.getUsername().isEmpty()) {
            System.out.println("\n=========================================");
            System.out.println("=              SISTEM PERBANKAN         =");
            System.out.println("=========================================");
            System.out.println("1. Login Akun");
            System.out.println("2. Registrasi Akun Baru");
            System.out.print("Pilih opsi (1/2): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Harap masukkan angka 1 atau 2!");
                scanner.nextLine();
                continue;
            }

            int pilihanAwal = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (pilihanAwal == 1) {
                // User pilih login
                customerAktif.signIn();
            } else if (pilihanAwal == 2) {
                // User pilih registrasi
                customerAktif.signUp();
                
                // RESET username agar loop while TIDAK LEPAS & dipaksa kembali ke Menu Utama (Login)
                customerAktif.setUsername(null); 
                
                System.out.println("\nSilakan pilih opsi '1. Login Akun' untuk masuk.");
            } else {
                System.out.println("Pilihan tidak valid! Masukkan angka 1 atau 2.");
            }
        }

        // 2. PILIH METODE TRANSAKSI
        Rekening pengirim = null;
        Rekening penerima = null;
        String jenisTransaksi = "";
        String merchantNameVA = "";
        
        int pilihanMetode = 0;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.println("\n=== PILIH JENIS METODE TRANSAKSI ===");
            System.out.println("1. Rekening Bank");
            System.out.println("2. Virtual Account");
            System.out.print("Pilih (1/2): ");

            if (scanner.hasNextInt()) {
                pilihanMetode = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (pilihanMetode == 1 || pilihanMetode == 2) {
                    inputValid = true;
                } else {
                    System.out.println("Pilihan tidak valid! Harap masukkan angka 1 atau 2.");
                }
            } else {
                System.out.println("Input harus berupa angka!");
                scanner.next();
            }
        }

        // 3. INPUT DATA TRANSAKSI
        if (pilihanMetode == 1) {
            // --- REKENING BANK ---
            System.out.println("\n--- INPUT DATA REKENING PENGIRIM ---");
            System.out.print("Masukkan No Rekening Asal : ");
            String noRekAsal = scanner.nextLine();
            
            String namaPemilik = customerAktif.getNama();
            if (namaPemilik == null || namaPemilik.isEmpty()) {
                namaPemilik = customerAktif.getUsername();
            }
            System.out.print("Masukkan Nama Pemilik     : " + namaPemilik + "\n");

            System.out.print("Masukkan Saldo Awal       : ");
            double saldoAwal = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer
            System.out.print("Masukkan Mata Uang        : ");
            String mataUang = scanner.nextLine();
            System.out.print("Masukkan Nama Bank Asal   : ");
            String namaBankAsal = scanner.nextLine();
            System.out.print("Masukkan Cabang Bank      : ");
            String cabangBank = scanner.nextLine();

            pengirim = new RekeningBank(noRekAsal, namaPemilik, saldoAwal, mataUang, namaBankAsal, cabangBank);

            System.out.println("\n--- INPUT DATA REKENING PENERIMA (BANK) ---");
            System.out.print("No Rekening Tujuan : ");
            String noRekTujuan = scanner.nextLine();
            System.out.print("Nama Penerima      : ");
            String namaPenerima = scanner.nextLine();
            System.out.print("Mata Uang          : ");
            String mataUangTujuan = scanner.nextLine();
            System.out.print("Nama Bank Tujuan   : ");
            String namaBankTujuan = scanner.nextLine();
            System.out.print("Cabang Bank        : ");
            String cabangBankTujuan = scanner.nextLine();

            penerima = new RekeningBank(noRekTujuan, namaPenerima, 0.0, mataUangTujuan, namaBankTujuan, cabangBankTujuan);
            jenisTransaksi = "Transfer ke Rekening " + namaBankTujuan;

        } else {
            // --- VIRTUAL ACCOUNT ---
            System.out.println("\n--- INPUT DATA REKENING PENGIRIM ---");
            System.out.print("Masukkan No Rekening Asal : ");
            String noRekAsal = scanner.nextLine();

            String namaPemilik = customerAktif.getNama();
            if (namaPemilik == null || namaPemilik.isEmpty()) {
                namaPemilik = customerAktif.getUsername();
            }
            System.out.println("Nama Pemilik              : " + namaPemilik);

            System.out.print("Masukkan Saldo Awal       : ");
            double saldoAwal = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer
            System.out.print("Masukkan Mata Uang        : ");
            String mataUang = scanner.nextLine();
            System.out.print("Masukkan Kode Company Asal: ");
            String kodeCompanyAsal = scanner.nextLine();
            System.out.print("Masukkan Nama Merchant/VA : ");
            String merchantAsal = scanner.nextLine();

            pengirim = new VirtualAccount(noRekAsal, namaPemilik, saldoAwal, mataUang, kodeCompanyAsal, merchantAsal);

            System.out.println("\n--- INPUT DATA REKENING PENERIMA (VIRTUAL ACCOUNT) ---");
            System.out.print("No. Virtual Account : ");
            String noVATujuan = scanner.nextLine();
            System.out.print("Nama Penerima       : ");
            String namaPenerima = scanner.nextLine();
            System.out.print("Mata Uang           : ");
            String mataUangTujuan = scanner.nextLine();
            System.out.print("Kode Company        : ");
            String kodeCompany = scanner.nextLine();
            System.out.print("Nama Merchant / VA  : ");
            merchantNameVA = scanner.nextLine();

            penerima = new VirtualAccount(noVATujuan, namaPenerima, 0.0, mataUangTujuan, kodeCompany, merchantNameVA);
            jenisTransaksi = "Transfer ke Nexa Virtual Account";
        }

        // --- DETAIL NOMINAL & BERITA ---
        System.out.println("\n--- INPUT DETAIL TRANSAKSI ---");
        System.out.print("Nominal Transfer   : ");
        double nominalTransfer = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        System.out.print("Berita Catatan     : ");
        String beritaCatatan = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        String tglWaktuSekarang = formatter.format(new Date());
        String noRefOtomatis = UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0, 16);

        double biayaAdmin = penerima.cekBiayaAdmin(nominalTransfer);

        // 4. BUAT OBJEK TRANSAKSI
        Transaksi transaksi = new Transaksi(
            noRefOtomatis,
            tglWaktuSekarang,
            "Transfer Berhasil",
            jenisTransaksi,
            nominalTransfer,
            beritaCatatan,
            pengirim,
            penerima
        );

        transaksi.prosesTransfer();

        // 5. CETAK STRUK TRANSAKSI
        System.out.println("\n=========================================");
        System.out.println("            " + transaksi.getStatus().toUpperCase() + "            ");
        System.out.println("            " + transaksi.getTanggalWaktu() + "            ");
        System.out.println("            " + transaksi.getPengirim().getMataUang() + " " + String.format("%,.2f", transaksi.getNominal()) + "            ");
        System.out.println("=========================================");

        if (pilihanMetode == 1) {
            System.out.println("Nama Penerima   : " + transaksi.getPenerima().getNamaPemilik());
            System.out.println("Rekening Tujuan : " + transaksi.getPenerima().getNoRekening());
            System.out.println("Jenis Transaksi : " + transaksi.getJenisTransaksi());
            System.out.println("Mata Uang Tujuan: " + transaksi.getPenerima().getMataUang() + " - Indonesian Rupiah");
            System.out.println("Dari Rekening   : " + transaksi.getPengirim().getNoRekening());
            System.out.println("Mata Uang Asal  : " + transaksi.getPengirim().getMataUang() + " - Indonesian Rupiah");
            System.out.println("Nominal Tujuan  : " + transaksi.getPenerima().getMataUang() + " " + String.format("%,.2f", transaksi.getNominal()));
            System.out.println("Berita          : " + transaksi.getBerita());
        } else {
            System.out.println("No. Nexa Virtual Account : " + transaksi.getPenerima().getNoRekening());
            System.out.println("Nama                    : " + transaksi.getPenerima().getNamaPemilik());
            System.out.println("Nama Produk             : " + merchantNameVA);
            System.out.println("Dari Rekening           : " + transaksi.getPengirim().getNoRekening());
            System.out.println("Nominal Bayar           : " + transaksi.getPengirim().getMataUang() + " " + String.format("%,.2f", transaksi.getNominal()));
            System.out.println("Biaya Admin             : " + transaksi.getPengirim().getMataUang() + " " + String.format("%,.2f", biayaAdmin));
            System.out.println("Total Bayar             : " + transaksi.getPengirim().getMataUang() + " " + String.format("%,.2f", (transaksi.getNominal() + biayaAdmin)));
            System.out.println("Jenis Transaksi         : " + transaksi.getJenisTransaksi());
        }

        System.out.println("No. Referensi               : " + transaksi.getNoReferensi());
        System.out.println("=========================================");

        // 6. SIMPAN & BACA FILE TRANSAKSI
        transaksi.saveToFile("transaksi.txt");
        transaksi.readFromFile("transaksi.txt");
    }
}