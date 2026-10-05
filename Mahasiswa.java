public class Mahasiswa extends Pengguna {

    // Attribute
    private String nim;
    private String programStudi;

    // Constructor
    public Mahasiswa(String nama, String email, String nim, String programStudi) {
        super(nama, email);
        this.nim = nim;
        this.programStudi = programStudi;
    }

    // Method
    public void requestKHS() {

    }

    public void viewNilai() {

    }
}