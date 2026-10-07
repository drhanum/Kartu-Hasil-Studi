public class Pengguna {
    private String username;
    private String password;
    private String nama;

    public Pengguna(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    // override
    public void tampilkanNilai(PengelolaData pd) {
        System.out.println("Tidak ada nilai yang dapat ditampilkan.");
    }
}