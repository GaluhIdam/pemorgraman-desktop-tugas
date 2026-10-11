/** Merepresentasikan satu menu restoran. */
public class Menu {
    private final String nama;
    private final long harga;
    private final String kategori;

    public Menu(String nama, long harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() { return nama; }
    public long getHarga() { return harga; }
    public String getKategori() { return kategori; }
}
