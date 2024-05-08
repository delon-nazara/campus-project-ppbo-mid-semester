package projectbankingsystem;

public class Output {
    
    private static int lebarUI = 80;
    private static char karakterPertigaan = '+';
    private static char karakterVertikal = '|';
    private static char karakterHorizontal = '-';
    private static String pembatasTengah = ":  ";
    private static String pembatasSamping = Logic.karakterPerulangan(' ', 3);
    
    protected static String garisHorizontal() {
        return karakterPertigaan + Logic.karakterPerulangan(karakterHorizontal, lebarUI) + karakterPertigaan;
    }
    
    protected static void judul(String teksUtama, boolean adaPemisah) {
        int sisaSpasi = lebarUI - teksUtama.length();
        
        System.out.println(garisHorizontal());
        System.out.print(karakterVertikal);
        System.out.print(Logic.karakterPerulangan(' ', sisaSpasi/2));
        System.out.print(teksUtama);
        System.out.print(Logic.karakterPerulangan(' ', sisaSpasi/2 + (sisaSpasi % 2 == 0 ? 0 : 1)));
        System.out.println(karakterVertikal);
        System.out.println(garisHorizontal());
        
        if (adaPemisah) {
            System.out.println();
        }
    }
    
    protected static void menu(String[] teksUtama) {
        int panjangTeks = Logic.panjangTeks(teksUtama);
        
        for (String teks : teksUtama) {
            System.out.print(karakterVertikal);
            System.out.print(pembatasSamping);
            System.out.print(Logic.formatTeks(teks, panjangTeks));
            System.out.print(Logic.karakterPerulangan(' ', lebarUI - (pembatasSamping.length() + panjangTeks)));
            System.out.println(karakterVertikal);
        }
        System.out.println(garisHorizontal());
    }
    
    protected static void informasi(String[] teksKiri, String[] teksKanan) {
        int panjangTeksKanan = Logic.nilaiTerbesar(Logic.panjangTeks(teksKanan), lebarUI/2 - pembatasSamping.length() - pembatasTengah.length());
        int panjangTeksKiri = lebarUI - pembatasSamping.length() * 2 - panjangTeksKanan - pembatasTengah.length();
        
        for (int i = 0; i < teksKiri.length; i++) {
            System.out.print(karakterVertikal);
            System.out.print(pembatasSamping);
            System.out.print(Logic.formatTeks(teksKiri[i], panjangTeksKiri));
            System.out.print(pembatasTengah);
            System.out.print(Logic.formatTeks(teksKanan[i], panjangTeksKanan));
            System.out.print(pembatasSamping);
            System.out.println(karakterVertikal);
        }
        System.out.println(garisHorizontal());
    }
    
    protected static void keteranganInput(String teksUtama, boolean diTengah) {
        int panjangTeks = 0;
        if (diTengah) {
            panjangTeks = lebarUI/2 + 1;
        }
        System.out.print(Logic.formatTeks(teksUtama, panjangTeks) + pembatasTengah);
    }
    
    protected static void kembali() {
        System.out.print("\nTekan Tombol Enter Untuk Kembali!");
        Input.inputanKosong();
        System.out.println();
    }
    
    protected static void bersihkanLayar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
