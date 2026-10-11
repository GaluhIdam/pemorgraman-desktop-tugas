import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final NumberFormat RUPIAH = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
    private static final long BIAYA_PELAYANAN = 20_000L;

    private static final Menu[] DAFTAR_MENU = {
        new Menu("Nasi Padang", 30_000L, "makanan"),
        new Menu("Nasi Goreng", 25_000L, "makanan"),
        new Menu("Mie Goreng", 20_000L, "makanan"),
        new Menu("Ayam Bakar", 35_000L, "makanan"),
        new Menu("Es Teh", 10_000L, "minuman"),
        new Menu("Es Jeruk", 15_000L, "minuman"),
        new Menu("Kopi", 12_000L, "minuman"),
        new Menu("Air Mineral", 5_000L, "minuman")
    };
    private static final Menu[] PESANAN = new Menu[4];
    private static final int[] JUMLAH = new int[4];

    public static void main(String[] args) {
        RUPIAH.setMaximumFractionDigits(0);
        tampilkanMenu();
        if (terimaPesanan()) {
            hitungDanCetakStruk();
        } else {
            System.out.println("\nPesanan dibatalkan. Jalankan kembali program untuk memesan ulang.");
        }
        INPUT.close();
    }

    private static String rupiah(long nilai) {
        return RUPIAH.format(nilai);
    }

    private static void tampilkanMenu() {
        System.out.println("============================================================");
        System.out.println("                    RESTORAN MAHASISWA");
        System.out.println("============================================================");
        tampilkanKategori("makanan");
        tampilkanKategori("minuman");
        System.out.println("\nKETENTUAN PEMBAYARAN:");
        System.out.println("- Pajak 10% dari subtotal; pelayanan " + rupiah(BIAYA_PELAYANAN) + ".");
        System.out.println("- Subtotal > Rp100.000: diskon 10% dari subtotal.");
        System.out.println("- Subtotal > Rp50.000: beli 1 gratis 1 khusus Es Teh.");
        System.out.println("- Jumlah Es Teh adalah jumlah gelas yang diterima.");
        System.out.println("  Setiap 2 gelas: 1 gratis; kedua promo dapat digabung.");
        System.out.println("- Maksimal 4 baris pesanan; jumlah tiap baris 1-1000.");
    }

    private static void tampilkanKategori(String kategori) {
        switch (kategori) {
            case "makanan":
                System.out.println("\nMAKANAN");
                tampilkanItem(DAFTAR_MENU[0]);
                tampilkanItem(DAFTAR_MENU[1]);
                tampilkanItem(DAFTAR_MENU[2]);
                tampilkanItem(DAFTAR_MENU[3]);
                break;
            case "minuman":
                System.out.println("\nMINUMAN");
                tampilkanItem(DAFTAR_MENU[4]);
                tampilkanItem(DAFTAR_MENU[5]);
                tampilkanItem(DAFTAR_MENU[6]);
                tampilkanItem(DAFTAR_MENU[7]);
                break;
            default:
                System.out.println("Kategori tidak dikenal.");
        }
    }

    private static void tampilkanItem(Menu menu) {
        System.out.printf("  %-20s %15s%n", menu.getNama(), rupiah(menu.getHarga()));
    }

    private static boolean terimaPesanan() {
        System.out.print("\nBerapa baris menu yang dipesan (1-4)? ");
        if (!INPUT.hasNextLine()) {
            System.out.println("Input tidak tersedia.");
            return false;
        }
        String teks = INPUT.nextLine().trim();
        if (!teks.matches("[1-4]")) {
            System.out.println("Jumlah baris harus 1, 2, 3, atau 4.");
            return false;
        }
        int banyakMenu = Integer.parseInt(teks);
        System.out.println("Ketik nama menu = jumlah, contoh: Nasi Padang = 2");
        if (!bacaPesanan(0)) { return false; }
        if (banyakMenu >= 2 && !bacaPesanan(1)) { return false; }
        if (banyakMenu >= 3 && !bacaPesanan(2)) { return false; }
        if (banyakMenu == 4 && !bacaPesanan(3)) { return false; }
        return true;
    }

    private static boolean bacaPesanan(int indeks) {
        System.out.print("Pesanan " + (indeks + 1) + ": ");
        if (!INPUT.hasNextLine()) {
            System.out.println("Input pesanan belum lengkap.");
            return false;
        }
        String[] bagian = INPUT.nextLine().split("=", -1);
        if (bagian.length != 2) {
            System.out.println("Format salah. Gunakan Nama Menu = jumlah.");
            return false;
        }
        String nama = bagian[0].trim().replaceAll("\\s+", " ");
        String teksJumlah = bagian[1].trim();
        Menu menu = cariMenu(nama);
        if (menu == null) {
            System.out.println("Menu tidak ditemukan: " + nama);
            return false;
        }
        if (!teksJumlah.matches("[0-9]{1,4}")) {
            System.out.println("Jumlah harus bilangan bulat 1-1000.");
            return false;
        }
        int jumlah = Integer.parseInt(teksJumlah);
        if (jumlah < 1 || jumlah > 1000) {
            System.out.println("Jumlah harus berada dalam rentang 1-1000.");
            return false;
        }
        PESANAN[indeks] = menu;
        JUMLAH[indeks] = jumlah;
        return true;
    }

    private static Menu cariMenu(String nama) {
        if (DAFTAR_MENU[0].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[0];
        } else if (DAFTAR_MENU[1].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[1];
        } else if (DAFTAR_MENU[2].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[2];
        } else if (DAFTAR_MENU[3].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[3];
        } else if (DAFTAR_MENU[4].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[4];
        } else if (DAFTAR_MENU[5].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[5];
        } else if (DAFTAR_MENU[6].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[6];
        } else if (DAFTAR_MENU[7].getNama().equalsIgnoreCase(nama)) {
            return DAFTAR_MENU[7];
        } else {
            return null;
        }
    }

    private static long totalBaris(int indeks) {
        if (PESANAN[indeks] == null) { return 0L; }
        return PESANAN[indeks].getHarga() * JUMLAH[indeks];
    }

    private static int jumlahEsTeh(int indeks) {
        if (PESANAN[indeks] != null) {
            if (PESANAN[indeks].getKategori().equals("minuman")
                    && PESANAN[indeks].getNama().equals("Es Teh")) {
                return JUMLAH[indeks];
            }
        }
        return 0;
    }

    private static void hitungDanCetakStruk() {
        long subtotal = totalBaris(0) + totalBaris(1) + totalBaris(2) + totalBaris(3);
        long pajak = subtotal * 10 / 100;
        long diskon = 0L;
        int gelasGratis = 0;
        int totalEsTeh = jumlahEsTeh(0) + jumlahEsTeh(1) + jumlahEsTeh(2) + jumlahEsTeh(3);

        if (subtotal > 100_000L) {
            diskon = subtotal * 10 / 100;
        }
        if (subtotal > 50_000L) {
            if (totalEsTeh >= 2) {
                gelasGratis = totalEsTeh / 2;
            }
        }
        long potonganPromo = gelasGratis * DAFTAR_MENU[4].getHarga();
        long setelahPotongan = subtotal - diskon - potonganPromo;
        long totalBayar = setelahPotongan + pajak + BIAYA_PELAYANAN;
        cetakStruk(subtotal, pajak, diskon, gelasGratis, potonganPromo,
                setelahPotongan, totalBayar);
    }

    private static void cetakStruk(long subtotal, long pajak, long diskon,
            int gelasGratis, long potonganPromo, long setelahPotongan, long totalBayar) {
        System.out.println("\n======================================================================");
        System.out.println("                           STRUK PESANAN");
        System.out.println("======================================================================");
        System.out.printf("%-20s %6s %18s %20s%n", "Item", "Jumlah", "Harga satuan", "Total item");
        cetakBaris(0);
        cetakBaris(1);
        cetakBaris(2);
        cetakBaris(3);
        System.out.println("----------------------------------------------------------------------");
        cetakBiaya("Subtotal (sebelum potongan)", subtotal);
        if (diskon > 0) {
            cetakBiaya("Diskon 10% dari subtotal", -diskon);
        } else {
            System.out.println("Diskon 10%: tidak berlaku (subtotal <= Rp100.000).");
        }
        if (potonganPromo > 0) {
            System.out.println("Promo Es Teh: " + gelasGratis + " gelas gratis dari jumlah yang dipesan.");
            cetakBiaya("Potongan beli 1 gratis 1", -potonganPromo);
        } else if (subtotal <= 50_000L) {
            System.out.println("Promo Es Teh: tidak berlaku (subtotal <= Rp50.000).");
        } else {
            System.out.println("Promo Es Teh: tidak berlaku (pesanan Es Teh kurang dari 2 gelas).");
        }
        cetakBiaya("Harga pesanan setelah potongan", setelahPotongan);
        cetakBiaya("Pajak 10% dari subtotal sebelum potongan", pajak);
        cetakBiaya("Biaya pelayanan", BIAYA_PELAYANAN);
        System.out.println("----------------------------------------------------------------------");
        cetakBiaya("TOTAL BAYAR", totalBayar);
        System.out.println("======================================================================");
        System.out.println("Terima kasih atas kunjungan Anda!");
    }

    private static void cetakBaris(int indeks) {
        if (PESANAN[indeks] != null) {
            System.out.printf("%-20s %6d %18s %20s%n", PESANAN[indeks].getNama(),
                    JUMLAH[indeks], rupiah(PESANAN[indeks].getHarga()), rupiah(totalBaris(indeks)));
        }
    }

    private static void cetakBiaya(String label, long nilai) {
        System.out.printf("%-47s %20s%n", label, rupiah(nilai));
    }
}
