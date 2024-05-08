package projectbankingsystem;

import java.util.Scanner;

public class Input {
    
    static Scanner scanner = new Scanner(System.in);
    
    protected static void inputanKosong() {
        scanner.nextLine();
    }
    
    private static String basicInput(String teksUtama, String jenisValidasi, boolean diTengah, boolean opsiKembali) {
        while (true) {
            try {
                if (opsiKembali) {
                    System.out.println("Input \"kembali\" Untuk Kembali Ke Halaman Menu Utama!");
                }
                Output.keteranganInput(teksUtama, diTengah);
                String input = scanner.nextLine();
                
                Logic.validasiInput(input, jenisValidasi);
                
                System.out.println();
                return input;
            } catch (Exception e) {
                Output.judul(e.getMessage(), false);
            }
        }  
    }
    
    protected static int konfirmasi(String teksUtama) {
        System.out.println("\n" + teksUtama);
        System.out.println("1. Iya");
        System.out.println("2. Tidak");
        System.out.println();
        
        String input = basicInput("Input Pilihan Anda", "duaPilihan", false, false);
        
        return Integer.parseInt(input);
    }
    
    protected static int pilihMenu() {
        String input = basicInput("Input Pilihan Anda", "menu", false, false);
        
        return Integer.parseInt(input);
    }
    
    protected static BankAccount daftarAkun() {
        String nama = basicInput("Input Nama Anda", "nama", true, true);
        if (Logic.hurufKecilSemua(nama).equals("kembali")) {
            return null;
        }
        
        String alamat = basicInput("Input Alamat Anda", "alamat", true, true);
        if (Logic.hurufKecilSemua(alamat).equals("kembali")) {
            return null;
        }
        
        String nomorTelepon = basicInput("Input Nomor Telepon Anda", "nomorTelepon", true, true);
        if (Logic.hurufKecilSemua(nomorTelepon).equals("kembali")) {
            return null;
        }
        
        String saldo = basicInput("Input Saldo Awal Anda", "saldo", true, true);
        if (Logic.hurufKecilSemua(saldo).equals("kembali")) {
            return null;
        }
        
        return new BankAccount(nama, alamat, nomorTelepon, Integer.parseInt(saldo));
    }
    
    protected static String[] kirimUang() {
        String nomorAkunPengirim = basicInput("Input Nomor Akun Pengirim", "nomorAkun", true, true);
        if (Logic.hurufKecilSemua(nomorAkunPengirim).equals("kembali")) {
            return null;
        }
        
        String nomorAkunPenerima = basicInput("Input Nomor Akun Penerima", "nomorAkun", true, true);
        if (Logic.hurufKecilSemua(nomorAkunPenerima).equals("kembali")) {
            return null;
        }
        
        String saldo = basicInput("Input Nominal Pengiriman", "saldo", true, true);
        if (Logic.hurufKecilSemua(saldo).equals("kembali")) {
            return null;
        }

        return new String[]{nomorAkunPengirim, nomorAkunPenerima, saldo};
    }
    
    protected static String[] simpanUang() {
        String nomorAkun = basicInput("Input Nomor Akun", "nomorAkun", true, true);
        if (Logic.hurufKecilSemua(nomorAkun).equals("kembali")) {
            return null;
        }
        
        String saldo = basicInput("Input Nominal Penyimpanan", "saldo", true, true);
        if (Logic.hurufKecilSemua(saldo).equals("kembali")) {
            return null;
        }

        return new String[]{nomorAkun, saldo};
    }
    
    protected static BankAccount cekInfo() {
        String input = basicInput("Input Nomor Akun", "nomorAkun", false, true);
        
        if (Logic.hurufKecilSemua(input).equals("kembali")) {
            return null;
        }
        
        return Logic.cariAkun(input);
    }
    
}
