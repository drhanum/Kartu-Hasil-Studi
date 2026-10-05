/*
DESKRIPSI KELAS DOSEN
EXTENDS Pengguna
ATRIBUTES:
- nidn: Nomer Induk Dosen Nasional
- mataKuliahDiampu: Array string yang berisi matkul-matkul yang diampu oleh dosen
- nilaiMahasiswa: Array integer 3D yang menyimpan nilai mahasiswa per mata kuliah
    FORMAT: [indexMataKuliah][indexMahasiswa][0:NIM, 1:TUGAS, 2:UTS, 3:UAS]
CONSTRUCTORS
- Dosen(String username, String password, String nama, String nidn, String[] mataKuliahDiampu, int[][][] nilaiMahasiswa)
- Dosen(String username, String password, String nama, String nidn, String[] mataKuliahDiampu)
- Dosen(String username, String password, String nama, String nidn)
HELPER METHODS
- cariIndexMataKuliah(String mataKuliah)
- tambahMataKuliah(String mataKuliah)
SETTERS
- setMataKuliahDiampu(String[] mataKuliahDiampu)
- setNilaiMahasiswa(int[][][] nilaiMahasiswa)
GETTERS
- getNidn()
- getMataKuliahDiampu()
- getNilaiMahasiswa(String mataKuliah)
FEATURE METHODS
- OVERRIDE viewNilai()
- OVERLOAD viewNilai(String mataKuliah)
- OVERLOAD inputNilai(String mataKuliah, int[][] nilaiMahasiswa)
- OVERLOAD inputNilai(String mataKuliah, int nim, int tugas, int uts, int uas)
 */

import java.util.Arrays;

public class Dosen extends Pengguna {

    // ATTRIBUTES
    private String nidn;
    private String[] mataKuliahDiampu; // SATU DOSEN DAPAT MENGAMPU LEBIH DARI SATU MATKUL
    private int[][][] nilaiMahasiswa; // GUNAKAN INDEKS DARI mataKuliahDiampu UNTUK MENGAKSES NILAI-NILAI MAHASISWA
    // FORMAT NIM, TUGAS, UTS, UAS

    // CONSTRUCTOR LENGKAP DENGAN LIST MATA KULIAH DAN ARRAY DATA NILAI MAHASISWA SESUAI FORMAT
    public Dosen(String username, String password, String nama, String nidn, String[] mataKuliahDiampu, int[][][] nilaiMahasiswa) {
        super(username, password, nama);
        this.nidn = nidn;
        this.mataKuliahDiampu = mataKuliahDiampu;
        this.nilaiMahasiswa = nilaiMahasiswa;
    }
    // CONSTRUCTOR DENGAN LIST MATA KULIAH
    public Dosen(String username, String password, String nama, String nidn, String[] mataKuliahDiampu) {
        super(username, password, nama);
        this.nidn = nidn;
        this.mataKuliahDiampu = mataKuliahDiampu;
        this.nilaiMahasiswa = new int[mataKuliahDiampu.length][][]; // INISIALISASI ARRAY NILAI MAHASISWA SESUAI PANJANG LIST MATA KULIAH
    }
    // CONSTRUCTOR TANPA LIST MATA KULIAH
    public Dosen(String username, String password, String nama, String nidn) {
        super(username, password, nama);
        this.nidn = nidn;
    }

    // HELPER METHOD
    // CARI INDEX MATA KULIAH DARI STRING MATA KULIAH
    private int cariIndexMataKuliah(String mataKuliah) {
        for (int i = 0; i < mataKuliahDiampu.length; i++) {
            if (mataKuliahDiampu[i].equalsIgnoreCase(mataKuliah)) {
                return i;
            }
        }
        return -1; // JIKA TIDAK DITEMUKAN
    }
    // TAMBAH MATA KULIAH BARU, KEMUDIAN PANJANGKAN ARRAY NILAI MAHASISWA
    private void tambahMataKuliah(String mataKuliah) {
        // TAMBAHKAN MATA KULIAH BARU KE LIST MATA KULIAH
        mataKuliahDiampu = Arrays.copyOf(mataKuliahDiampu, mataKuliahDiampu.length + 1);
        mataKuliahDiampu[mataKuliahDiampu.length - 1] = mataKuliah;
        // PANJANGKAN ARRAY NILAI MAHASISWA SESUAI PANJANG LIST MATA KULIAH
        nilaiMahasiswa = Arrays.copyOf(nilaiMahasiswa, mataKuliahDiampu.length);
    }

    // SETTERS
    // SETTER LIST MATA KULIAH
    public void setMataKuliahDiampu(String[] mataKuliahDiampu) {
        this.mataKuliahDiampu = mataKuliahDiampu;
        this.nilaiMahasiswa = new int[mataKuliahDiampu.length][][]; // INISIALISASI ARRAY NILAI MAHASISWA SESUAI PANJANG LIST MATA KULIAH
    }
    // SETTER DATA NILAI MAHASISWA SESUAI FORMAT
    public void setNilaiMahasiswa(int[][][] nilaiMahasiswa) {
        // CEK APAKAH PANJANG ARRAY NILAI MAHASISWA SESUAI DENGAN PANJANG LIST MATA KULIAH
        if (mataKuliahDiampu != null && nilaiMahasiswa.length != mataKuliahDiampu.length) {
            throw new IllegalArgumentException("Panjang array nilai mahasiswa harus sama dengan panjang list mata kuliah");
        }
        this.nilaiMahasiswa = nilaiMahasiswa;
    }

    // GETTERS
    public String getNidn() {
        return nidn;
    }
    public String[] getMataKuliahDiampu() {
        return mataKuliahDiampu;
    }
    public int[][] getNilaiMahasiswa(String mataKuliah) {
        // CARI INDEX MATA KULIAH
        int indexMatKul = cariIndexMataKuliah(mataKuliah);
        // JIKA INDEX -1 MAKA MATA KULIAH TIDAK DITEMUKAN
        if (indexMatKul == -1) {
            System.out.println(mataKuliah + " tidak ditemukan.");
            return null;
        }
        // RETURN ARRAY NILAI MAHASISWA UNTUK MATA KULIAH INI
        // FORMAT NIM, TUGAS, UTS, UAS
        return nilaiMahasiswa[indexMatKul];
    }
    
    // viewNilai() DULUAN KARENA inputNilai() SANGAT RIBET
    // OVERRIDING METHOD viewNilai() DARI SUPER CLASS PENGGUNA
    // METHOD viewNilai() MODE PRINT SEMUAHH
    public void viewNilai() {
        // CEK APAKAH DATA NILAI ADA
        if (nilaiMahasiswa == null) {
            System.out.println("Belum ada data nilai mahasiswa.");
            return;
        }
        // TAMPILKAN SEMUA DATA NILAI MENGGUNAKAN OVERLOADING METHOD viewNilai(String mataKuliah)
        for (int i = 0; i < mataKuliahDiampu.length; i++) {
            viewNilai(mataKuliahDiampu[i]);
        }
    }
    // METHODviewNilai() MODE MATA KULIAH TERTENTU
    public void viewNilai(String mataKuliah) {
        // CARI INDEX MATA KULIAH
        int indexMatKul = cariIndexMataKuliah(mataKuliah);
        // JIKA INDEX -1 MAKA MATA KULIAH TIDAK DITEMUKAN
        if (indexMatKul == -1) {
            System.out.println(mataKuliah + " tidak ditemukan.");
            return;
        }
        // CEK APAKAH ADA DATA NILAI UNTUK MATA KULIAH INI
        if (nilaiMahasiswa[indexMatKul] == null) {
            System.out.println(mataKuliah + " belum memiliki data nilai.");
            return;
        }
        // TAMPILKAN NILAI MAHASISWA UNTUK MATA KULIAH INI
        System.out.println(mataKuliah + ":");
        System.out.println("NIM, TUGAS, UTS, UAS");
        for (int[] baris : nilaiMahasiswa[indexMatKul]) {
            System.out.println(baris[0] + ", " + baris[1] + ", " + baris[2] + ", " + baris[3]);
        }
    }

    // METHOD inputNilai() MODE INSTAN SEKALIGUS, LANGSUNG ISI DENGAN ARRAY YANG SESUAI FORMAT
    public void inputNilai(String mataKuliah, int[][] nilaiMahasiswa) {
        // CARI INDEX MATA KULIAH
        int indexMatKul = cariIndexMataKuliah(mataKuliah);
        // JIKA INDEX -1 MAKA MATA KULIAH TIDAK DITEMUKAN, TAMBAHKAN MATA KULIAH INI KE LIST MATA KULIAH
        if (indexMatKul == -1) {
            // TAMBAHKAN MATA KULIAH BARU KE LIST MATA KULIAH
            tambahMataKuliah(mataKuliah); // CODE REUSE
            // SET indexMatKul KE INDEX TERBARU
            indexMatKul = mataKuliahDiampu.length - 1;
        }
        // SIMPAN DATA NILAI MAHASISWA UNTUK MATA KULIAH INI
        this.nilaiMahasiswa[indexMatKul] = nilaiMahasiswa;
    }
    // METHOD inputNilai() VERSI SATU MAHASISWA AJA
    public void inputNilai(String mataKuliah, int nim, int tugas, int uts, int uas) {
        // CARI INDEX MATA KULIAH
        int indexMatKul = cariIndexMataKuliah(mataKuliah);
        // JIKA INDEX -1 MAKA MATA KULIAH TIDAK DITEMUKAN, TAMBAHKAN MATA KULIAH INI KE LIST MATA KULIAH
        if (indexMatKul == -1) {
            tambahMataKuliah(mataKuliah); // CODE REUSE
            // SET indexMatKul KE INDEX TERBARU
            indexMatKul = mataKuliahDiampu.length - 1;
        }
        // CARI INDEX BARIS DATA NILAI BERDASARKAN NIM
        int indexBaris = -1;
        if (nilaiMahasiswa[indexMatKul] != null) {
            for (int i = 0; i < nilaiMahasiswa[indexMatKul].length; i++) {
                if (nilaiMahasiswa[indexMatKul][i][0] == nim) {
                    indexBaris = i;
                    break;
                }
            }
        }
        // JIKA INDEX BARIS -1 MAKA MAHASISWA INI BELUM ADA DI DATA NILAI MATKUL INI, TAMBAHKAN BARIS BARU
        if (indexBaris == -1) {
            if (this.nilaiMahasiswa[indexMatKul] == null) {
                this.nilaiMahasiswa[indexMatKul] = new int[1][4];
            } else {
                int[][] temp = Arrays.copyOf(this.nilaiMahasiswa[indexMatKul], this.nilaiMahasiswa[indexMatKul].length + 1);
                this.nilaiMahasiswa[indexMatKul] = temp;
            }
            indexBaris = this.nilaiMahasiswa[indexMatKul].length - 1;
        }
        // SIMPAN NILAI MAHASISWA UNTUK MATA KULIAH INI
        this.nilaiMahasiswa[indexMatKul][indexBaris] = new int[]{nim, tugas, uts, uas};
    }
}