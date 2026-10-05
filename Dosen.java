public class Dosen extends Pengguna {

    private String nidn;
    private String mataKuliahDiampu;

    public Dosen(String username, String password, String nama,
                 String nidn, String mataKuliahDiampu) {
        super(username, password, nama);
        this.nidn = nidn;
        this.mataKuliahDiampu = mataKuliahDiampu;
    }

    public void inputNilai() {
        // belum diisi
    }

    public void viewNilai() {
        // belum diisi
    }
}