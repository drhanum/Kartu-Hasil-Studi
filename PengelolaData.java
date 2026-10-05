import java.util.ArrayList;

public class PengelolaData {

    // Daftar mahasiswa yang ditentukan oleh programmer
    private ArrayList<Mahasiswa> daftarMahasiswa;

    // Daftar dosen yang ditentukan oleh programmer
    private ArrayList<Dosen> daftarDosen;

    // Data KRS
    private String[][] dataKRS = {

        // NIM      Tahun Ajaran   Semester   Kode    Nama Mata Kuliah          SKS
        {"231001", "2026/2027",   "Ganjil",  "IF101", "Pemrograman Java",       "3"},
        {"231001", "2026/2027",   "Ganjil",  "IF102", "Basis Data",             "3"},
        {"231001", "2026/2027",   "Ganjil",  "IF103", "Pemrograman Berorientasi Objek", "3"},

        {"231001", "2026/2027",   "Genap",   "IF201", "Struktur Data",          "3"},
        {"231001", "2026/2027",   "Genap",   "IF202", "Pemrograman Web",         "3"},


        {"231002", "2026/2027",   "Ganjil",  "IF101", "Pemrograman Java",       "3"},
        {"231002", "2026/2027",   "Ganjil",  "IF102", "Basis Data",             "3"},
        {"231002", "2026/2027",   "Ganjil",  "IF104", "Sistem Operasi",         "3"},

        {"231002", "2026/2027",   "Genap",   "IF201", "Struktur Data",          "3"},
        {"231002", "2026/2027",   "Genap",   "IF203", "Jaringan Komputer",      "3"},


        {"231003", "2026/2027",   "Ganjil",  "IF101", "Pemrograman Java",       "3"},
        {"231003", "2026/2027",   "Ganjil",  "IF103", "Pemrograman Berorientasi Objek", "3"},
        {"231003", "2026/2027",   "Ganjil",  "IF105", "Matematika Diskrit",     "3"}
    };


    // Constructor
    public PengelolaData() {

        daftarMahasiswa = new ArrayList<>();
        daftarDosen = new ArrayList<>();

        // =========================
        // DATA MAHASISWA
        // =========================

        daftarMahasiswa.add(
            new Mahasiswa("231001", "Andi", "Teknik Informatika")
        );

        daftarMahasiswa.add(
            new Mahasiswa("231002", "Budi", "Teknik Informatika")
        );

        daftarMahasiswa.add(
            new Mahasiswa("231003", "Citra", "Teknik Informatika")
        );


        // =========================
        // DATA DOSEN
        // =========================

        daftarDosen.add(
            new Dosen("D001", "Dr. Ahmad", "Informatika")
        );

        daftarDosen.add(
            new Dosen("D002", "Budi Santoso, M.Kom.", "Informatika")
        );

        daftarDosen.add(
            new Dosen("D003", "Siti Rahma, M.Kom.", "Informatika")
        );
    }


    // =========================
    // METHOD
    // =========================

    public Mahasiswa cariMahasiswa(String nim) {
        return null;
    }

    public Dosen cariDosen(String idDosen) {
        return null;
    }

    public String[][] cariKRS(
        String nim,
        String tahunAjaran,
        String semester
    ) {
        return null;
    }
}