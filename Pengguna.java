public class Pengguna {

    protected String username;
    private  String password;
    protected String nama;

    // Constructor
    public Pengguna(String username, String password, String nama) {
        setUsername(username);
        setPassword(password);
        setNama(nama);
    }



    // getter
    public String getPassword() {
        return password;
    }



    // Setter + Validasi
    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException(
                "Password minimal 6 karakter"
            );
        }
        this.password = password;
    }

    public void setNama(String nama) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException(
                "Nama tidak boleh kosong"
            );
        }
        this.nama = nama;
    }



    // Login
    public boolean login(String username, String password) {
        return this.username.equals(username)
            && this.password.equals(password);
    }



    // Logout
    public void logout() {
        System.out.println(nama + " berhasil logout.");
    }
}