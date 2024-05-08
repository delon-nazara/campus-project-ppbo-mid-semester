package projectbankingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankingSystem {
    
    protected static ArrayList<BankAccount> akunPengguna = new ArrayList<>();
    
    protected static void showMenu() {
        Output.bersihkanLayar();
        Output.judul("Selamat Datang di Project Banking System", false);
        
        String[] teksUtama = {
            "Pilih Salah Satu Menu Berikut!",
            "1. Registrasi Akun Baru",
            "2. Mengirim Uang Antar Akun",
            "3. Menyimpan Uang ke Dalam Akun",
            "4. Mengecek Informasi Rekening Pribadi",
            "5. Keluar dari Program"
        };
        Output.menu(teksUtama);
        
        int inputPengguna = Input.pilihMenu();
        Logic.pilihMenu(inputPengguna);
    }
    
    protected static void registrasiAkun() {
        Output.bersihkanLayar();
        Output.judul("Registrasi Akun Baru", true);
        
        System.out.println("Silahkan Isi Beberapa Informasi Berikut!\n");
        BankAccount akunBaru = Input.daftarAkun();
        
        if (akunBaru != null) {
            Output.bersihkanLayar();
            Output.judul("Konfirmasi Registrasi Akun Baru", false);

            String[] teksKiri = {
                "Nama",
                "Alamat",
                "Nomor Telepon",
                "Jumlah Saldo",
            };
            String[] teksKanan = {
                Logic.kapitalAwalKata(akunBaru.getNama()),
                Logic.kapitalAwalKata(akunBaru.getAlamat()),
                akunBaru.getNomorTelepon(),
                Logic.konversiSaldo(akunBaru.getSaldo())
            };
            Output.informasi(teksKiri, teksKanan);

            int input = Input.konfirmasi("Apakah Anda Yakin Ingin Membuat Akun Baru?");
            if (input == 1) {
                akunPengguna.add(akunBaru);

                System.out.println("Akun Anda Telah Berhasil Dibuat!");
                System.out.println("Berikut Data Registrasi Akun Anda:\n");
                akunBaru.showDescription();

                Output.kembali();
            } else {
                System.out.println("Proses Registrasi Akun Baru Gagal!");
                System.out.println("User Memilih \"Tidak\" dan Proses Dihentikan!");
                Output.kembali();
            }
        }
    }
    
    protected static void kirimUang() {
        Output.bersihkanLayar();
        Output.judul("Pengiriman Uang Antar Akun", true);
        
        String[] data = Input.kirimUang();
        
        if (data != null) {
            BankAccount akunPengirim = Logic.cariAkun(data[0]);
            BankAccount akunPenerima = Logic.cariAkun(data[1]);
            int nominalPengiriman = Integer.parseInt(data[2]);

            Output.bersihkanLayar();
            Output.judul("Konfirmasi Pengiriman Uang", false);

            String[] teksKiri = {
                "Nomor Akun Pengirim",
                "Nama Akun Pengirim",
                "Nomor Akun Penerima",
                "Nomor Akun Penerima",
                "Nominal Pengiriman"
            };
            String[] teksKanan = {
                akunPengirim.getNomorAkun(),
                akunPengirim.getNama(),
                akunPenerima.getNomorAkun(),
                akunPenerima.getNama(),
                Logic.konversiSaldo(nominalPengiriman)
            };
            Output.informasi(teksKiri, teksKanan);

            int input = Input.konfirmasi("Apakah Anda Ingin Melanjutkan Proses Pengiriman?");
            if (input == 1) {
                if (akunPengirim.getSaldo() >= nominalPengiriman) {
                    akunPengirim.setSaldo(-nominalPengiriman);
                    akunPenerima.setSaldo(nominalPengiriman);

                    System.out.println("Proses Pengiriman Uang Berhasil!");
                    System.out.println("Berikut Data Pengiriman Uang Anda:\n");

                    Output.judul("Informasi Pengiriman Uang", false);

                    String[] teksKiri2 = {
                        "Nomor Akun Pengirim",
                        "Nama Akun Pengirim",
                        "Nomor Akun Penerima",
                        "Nomor Akun Penerima",
                        "Nominal Pengiriman",
                        "Waktu Pengiriman"
                    };
                    String[] teksKanan2 = {
                        akunPengirim.getNomorAkun(),
                        akunPengirim.getNama(),
                        akunPenerima.getNomorAkun(),
                        akunPenerima.getNama(),
                        Logic.konversiSaldo(nominalPengiriman),
                        Logic.konversiLocalDateTime(LocalDateTime.now())
                    };
                    Output.informasi(teksKiri2, teksKanan2);

                    Output.kembali();
                } else {
                    System.out.println("Proses Pengiriman Uang Gagal!");
                    System.out.println("Saldo Pada Akun Pengirim Tidak Cukup Untuk Melakukan Proses Pengiriman!");
                    Output.kembali();
                }
            } else {
                System.out.println("Proses Pengiriman Uang Gagal!");
                System.out.println("User Memilih \"Tidak\" dan Proses Dihentikan!");
                Output.kembali();
            }
        }
    }
    
    protected static void simpanUang() {
        Output.bersihkanLayar();
        Output.judul("Penyimpanan Uang ke Dalam Akun", true);
        
        String[] data = Input.simpanUang();
        
        if (data != null) {
            BankAccount akun = Logic.cariAkun(data[0]);
            int nominalPenyimpanan = Integer.parseInt(data[1]);

            Output.bersihkanLayar();
            Output.judul("Konfirmasi Penyimpanan Uang", false);

            String[] teksKiri = {
                "Nomor Akun",
                "Nama Akun",
                "Nominal Penyimpanan"
            };
            String[] teksKanan = {
                akun.getNomorAkun(),
                akun.getNama(),
                Logic.konversiSaldo(nominalPenyimpanan)
            };
            Output.informasi(teksKiri, teksKanan);

            int input = Input.konfirmasi("Apakah Anda Ingin Melanjutkan Proses Penyimpanan?");
            if (input == 1) {
                akun.setSaldo(nominalPenyimpanan);

                System.out.println("Proses Penyimpanan Uang Berhasil!");
                System.out.println("Berikut Data Penyimpanan Uang Anda:\n");

                Output.judul("Informasi Penyimpanan Uang", false);

                String[] teksKiri2 = {
                    "Nomor Akun",
                    "Nama Akun",
                    "Nominal Penyimpanan",
                    "Waktu Penyimpanan"
                };
                String[] teksKanan2 = {
                    akun.getNomorAkun(),
                    akun.getNama(),
                    Logic.konversiSaldo(nominalPenyimpanan),
                    Logic.konversiLocalDateTime(LocalDateTime.now())
                };
                Output.informasi(teksKiri2, teksKanan2);

                Output.kembali();
            } else {
                System.out.println("Proses Penyimpanan Uang Gagal!");
                System.out.println("User Memilih \"Tidak\" dan Proses Dihentikan!");
                Output.kembali();
            }
        }
    }
    
    protected static void cekInfo() {
        Output.bersihkanLayar();
        Output.judul("Cek Informasi Akun", true);
        
        BankAccount akunDitemukan = Input.cekInfo();
        
        if (akunDitemukan != null) {
            System.out.println("Akun Tersebut Ditemukan!");
            System.out.println("Berikut Informasi Akun Tersebut:\n");
            
            akunDitemukan.showDescription();
            Output.kembali();
        }
    }
    
    protected static void keluarProgram() {
        Output.judul("Terimakasih Telah Menggunakan Layanan Kami!", true);
        System.out.print("Tekan Tombol Enter Untuk Keluar!");
        Input.inputanKosong();
        System.exit(0);
    }    
    
}
