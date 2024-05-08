package projectbankingsystem;

import java.time.LocalDateTime;

public class Logic {
    
    protected static int nilaiTerbesar(int angka1, int angka2) {
        return angka1 > angka2 ? angka1 : angka2;
    }
    
    protected static int nilaiTerkecil(int angka1, int angka2) {
        return angka1 < angka2 ? angka1 : angka2;
    }
    
    protected static String formatTeks(String teksUtama, int panjangTeks) {
        String finalTeks = teksUtama;
        for (int i = 0; i < panjangTeks - teksUtama.length(); i++) {
            finalTeks += " ";
        }
        return finalTeks;
    }
    
    protected static String karakterPerulangan(char karakter, int jumlah) {
        String finalTeks = "";
        for (int i = 0; i < jumlah; i++) {
            finalTeks += karakter;
        }
        return finalTeks;
    }
    
    protected static int panjangTeks(String[] teksUtama) {
        int panjangTeks = 0;
        for (String teks : teksUtama) {
            if (teks.length() > panjangTeks) {
                panjangTeks = teks.length();
            }
        }
        return panjangTeks;
    }
    
    protected static String konversiAngka(int angka, int digitKonversi) {
        String stringAngka = String.valueOf(angka);
      
        String hasilKonversiAngka = "";
        for (int i = 0; i < digitKonversi - stringAngka.length(); i++) {
            hasilKonversiAngka += "0";
        }
        
        return hasilKonversiAngka + stringAngka;
    }
        
    protected static String konversiSaldo(int saldo) {
        String stringSaldo = String.valueOf(saldo);
        int digitSaldo = stringSaldo.length();
        int variabelPembantu = (digitSaldo % 3 == 0? 3 : digitSaldo % 3);
        
        String hasilKonversiSaldo = "Rp";
        for (int i = 0; i < digitSaldo; i++) {
            if (variabelPembantu == 0) {
                hasilKonversiSaldo += ".";
                variabelPembantu = 3;
            } 
            variabelPembantu--;
            hasilKonversiSaldo += stringSaldo.charAt(i);
        }
        
        return hasilKonversiSaldo;
    }
        
    protected static String konversiHari(String hari) {
        return switch (hari) {
            case "MONDAY" -> "Senin";
            case "TUESDAY" -> "Selasa";
            case "WEDNESDAY" -> "Rabu";
            case "THURSDAY" -> "Kamis";
            case "FRIDAY" -> "Jumat";
            case "SATURDAY" -> "Sabtu";
            case "SUNDAY" -> "Minggu";
            default -> "";
        };
    }
       
    protected static String konversiBulan(String bulan) {
        return switch (bulan) {
            case "JANUARY" -> "Januari";
            case "FEBRUARY" -> "Februari";
            case "MARCH" -> "Maret";
            case "APRIL" -> "April";
            case "MAY" -> "Mei";
            case "JUNE" -> "Juni";
            case "JULY" -> "Juli";
            case "AUGUST" -> "Agustus";
            case "SEPTEMBER" -> "September";
            case "OCTOBER" -> "Oktober";
            case "NOVEMBER" -> "November";
            case "DECEMBER" -> "Desember";
            default -> "";
        };
    }
    
    protected static String konversiLocalDateTime(LocalDateTime waktu) {
        String detik = konversiAngka(waktu.getSecond(), 2);
        String menit = konversiAngka(waktu.getMinute(), 2);
        String jam = konversiAngka(waktu.getHour(), 2);
        String hari = konversiHari(waktu.getDayOfWeek().toString());
        String tanggal = konversiAngka(waktu.getDayOfMonth(), 2);
        String bulan = konversiBulan(waktu.getMonth().toString());
        String tahun = konversiAngka(waktu.getYear(), 4);
        
        return hari + ", " + tanggal + " " + bulan + " " + tahun + ", Pukul " + jam + ":" + menit + ":" + detik + " WIB";
    }
    
    protected static String kapitalAwalKata(String teksUtama) {
        String finalTeks = "";
        
        if (teksUtama.charAt(0) >= 'a' && teksUtama.charAt(0) <= 'z') {
            finalTeks += (char) (teksUtama.charAt(0) - 'a' + 'A');
        } else {
            finalTeks += teksUtama.charAt(0);
        }
        
        for (int i = 1; i < teksUtama.length(); i++) {
            if (teksUtama.charAt(i-1) == ' ' && teksUtama.charAt(i) >= 'a' && teksUtama.charAt(i) <= 'z') {
                finalTeks += (char) (teksUtama.charAt(i) - 'a' + 'A');
            } else if (teksUtama.charAt(i-1) != ' ' && teksUtama.charAt(i) >= 'A' && teksUtama.charAt(i) <= 'Z') {
                finalTeks += (char) (teksUtama.charAt(i) - 'A' + 'a');
            } else {
                finalTeks += teksUtama.charAt(i);
            }
        }
        
        return finalTeks;
    }
    
    protected static String hurufKecilSemua(String teksUtama) {
        String finalTeks = "";
        
        for (int i = 0; i < teksUtama.length(); i++) {
            if (teksUtama.charAt(i) >= 'A' && teksUtama.charAt(i) <= 'Z') {
                finalTeks += (char) (teksUtama.charAt(i) - 'A' + 'a');
            } else {
                finalTeks += teksUtama.charAt(i);
            }
        }
        
        return finalTeks;
    }
        
    protected static void pilihMenu(int pilihan) {
        switch (pilihan) {
            case 1 -> BankingSystem.registrasiAkun();
            case 2 -> BankingSystem.kirimUang();
            case 3 -> BankingSystem.simpanUang();
            case 4 -> BankingSystem.cekInfo();
            case 5 -> BankingSystem.keluarProgram();
            default -> BankingSystem.showMenu();
        }
    }
    
    protected static void validasiInput(String input, String jenisValidasi) throws Exception {
        switch (jenisValidasi) {
            
            case "menu" -> {
                if (!input.matches("\\d+") || input.length() > 1 || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                    throw new Exception("Input Hanya Dapat Berupa Bilangan Bulat 1-5!");
                }
            }
            
            case "nama" -> {
                if (hurufKecilSemua(input).equals("kembali")) {
                    break;
                } else if (!input.matches("[a-zA-Z ]+")) {
                    throw new Exception("Input Hanya Dapat Berupa Alphabet A-Z!");
                } else if (input.length() > 32) {
                    throw new Exception("Input Maksimal Sebanyak 32 Karakter!");
                } 
            }
            
            case "alamat" -> {
                if (hurufKecilSemua(input).equals("kembali")) {
                    break;
                } else if (!input.matches("^[a-zA-Z].*")) {
                    throw new Exception("Input Karakter Pertama Harus Berupa Alphabet A-Z!");
                } else if (input.length() > 32) {
                    throw new Exception("Input Maksimal Sebanyak 32 Karakter!");
                } 
            }
            
            case "nomorTelepon" -> {
                if (hurufKecilSemua(input).equals("kembali")) {
                    break;
                } else if (!input.matches("\\d+")) {
                    throw new Exception("Input Hanya Dapat Berupa Bilangan Bulat 0-9!");
                } else if (input.length() < 5) {
                    throw new Exception("Input Minimal Sebanyak 5 Digit!");
                } else if (input.length() > 15) {
                    throw new Exception("Input Maksimal Sebanyak 15 Digit!");
                }
            }
            
            case "saldo" -> {
                if (hurufKecilSemua(input).equals("kembali")) {
                    break;
                } else if (!input.matches("\\d+")) {
                    throw new Exception("Input Hanya Dapat Berupa Bilangan Bulat 0-9!");
                } else if (input.length() > 10 || Integer.parseInt(input) > 1000000000) {
                    throw new Exception("Input Saldo Maksimal Sebesar Rp1.000.000.000");
                } else if (Integer.parseInt(input) < 1) {
                    throw new Exception("Input Saldo Minimal Sebesar Rp1");
                }
            }
            
            case "duaPilihan" -> {
                if (!input.matches("\\d+") || input.length() > 1 || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 2) {
                    throw new Exception("Input Hanya Dapat Berupa Bilangan Bulat 1-2!");
                }
            }
            
            case "nomorAkun" -> {
                if (hurufKecilSemua(input).equals("kembali")) {
                    break;
                } else if (!input.matches("\\d+")) {
                    throw new Exception("Input Hanya Dapat Berupa Bilangan Bulat 0-9");
                } else if (input.length() != 7) {
                    throw new Exception("Input Nomor Akun Sebanyak 7 Digit");
                } else if (cariAkun(input) == null) {
                    throw new Exception("Akun Tersebut Tidak Ditemukan!");
                }
            }
            
        }
    }
    
    protected static BankAccount cariAkun(String nomorAkun) {
        for (BankAccount akun : BankingSystem.akunPengguna) {
            if (akun.getNomorAkun().equals(nomorAkun)) {
                return akun;
            }
        }
        return null;
    }
    
}
