package projectbankingsystem;

import java.util.Random;
import java.time.LocalDateTime;

public class BankAccount {
    
    private String nama;
    private String alamat;
    private String nomorTelepon;
    private String nomorAkun;
    private int saldo;
    private LocalDateTime tanggalRegistrasi;

    protected BankAccount(String nama, String alamat, String nomorTelepon, int saldo) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.nomorAkun = generateNomorAkun();
        this.saldo = saldo;
        this.tanggalRegistrasi = LocalDateTime.now();
    }
                
    protected void topUp(int jumlahTopUp) {
        this.saldo += jumlahTopUp;
    }
    
    protected void transfer(int jumlahTransfer) {
        this.saldo -= jumlahTransfer;
    }
    
    private String generateNomorAkun() {
        Random random = new Random();
        return Logic.konversiAngka(random.nextInt(1, 10000000), 7);
    }
    
    protected void showDescription() {
        Output.judul("Informasi Rekening Pribadi", false);
        
        String[] teksKiri = {
            "Nama",
            "Alamat",
            "Nomor Telepon",
            "Nomor Akun",
            "Jumlah Saldo",
            "Waktu Registrasi"
        };
        String[] teksKanan = {
            Logic.kapitalAwalKata(this.nama),
            Logic.kapitalAwalKata(this.alamat),
            this.nomorTelepon,
            this.nomorAkun,
            Logic.konversiSaldo(this.saldo),
            Logic.konversiLocalDateTime(this.tanggalRegistrasi)
        };
        Output.informasi(teksKiri, teksKanan);
    }
    
    protected String getNama() {
        return this.nama;
    }
    
    protected String getAlamat() {
        return this.alamat;
    }
    
    protected String getNomorTelepon() {
        return this.nomorTelepon;
    }
    
    protected String getNomorAkun() {
        return this.nomorAkun;
    }
    
    protected int getSaldo() {
        return this.saldo;
    }
    
    protected void setSaldo(int nominalUang) {
        this.saldo += nominalUang;
    }
    
}
