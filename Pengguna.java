public class Pengguna {

    protected String username;
    protected String password;
    protected String nama;

    public Pengguna(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public boolean login(String username, String password) {
        // Logika login nanti
        return false;
    }

    public void logout() {
        // Logika logout nanti
    }
}