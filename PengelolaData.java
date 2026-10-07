import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PengelolaData {

    // DATA DUMMY KRS (nested class)
    public static class Krs {
        private final String nim;
        private final String tahunAjaran;   // contoh: "2025/2026"
        private final String semester;      // "ganjil" / "genap"
        private final String kodeMatkul;
        private final String namaMatkul;
        private final int sks;
        private final String nidnDosen;

        private double nilaiTugas;
        private double nilaiUts;
        private double nilaiUas;
        private double nilaiAkhir;
        private double nilaiMutu;   // skala 0-4
        private double bobot;        // SKS x nilai mutu
        private String grade = "-";
        private boolean sudahDinilai = false;

        private Krs(String nim, String tahunAjaran, String semester, String kodeMatkul,
                    String namaMatkul, int sks, String nidnDosen) {
            this.nim = nim;
            this.tahunAjaran = tahunAjaran;
            this.semester = semester;
            this.kodeMatkul = kodeMatkul;
            this.namaMatkul = namaMatkul;
            this.sks = sks;
            this.nidnDosen = nidnDosen;
        }

        /** 50% tugas, 25% UTS, 25% UAS, grade, nilai mutu, bobot. */
        private void isiNilai(double tugas, double uts, double uas) {
            this.nilaiTugas = tugas;
            this.nilaiUts = uts;
            this.nilaiUas = uas;
            this.nilaiAkhir = 0.5 * tugas + 0.25 * uts + 0.25 * uas;
            if (nilaiAkhir >= 85)      { grade = "A";  nilaiMutu = 4.0; }
            else if (nilaiAkhir >= 80) { grade = "A-"; nilaiMutu = 3.5; }
            else if (nilaiAkhir >= 70) { grade = "B";  nilaiMutu = 3.0; }
            else if (nilaiAkhir >= 65) { grade = "B-"; nilaiMutu = 2.5; }
            else if (nilaiAkhir >= 55) { grade = "C";  nilaiMutu = 2.0; }
            else if (nilaiAkhir >= 40) { grade = "D";  nilaiMutu = 1.0; }
            else                       { grade = "E";  nilaiMutu = 0.0; }
            this.bobot = sks * nilaiMutu;
            this.sudahDinilai = true;
        }

        public String getNim() { return nim; }
        public String getTahunAjaran() { return tahunAjaran; }
        public String getSemester() { return semester; }
        public String getPeriode() { return tahunAjaran + " " + semester; }
        public String getKodeMatkul() { return kodeMatkul; }
        public String getNamaMatkul() { return namaMatkul; }
        public int getSks() { return sks; }
        public String getNidnDosen() { return nidnDosen; }
        public double getNilaiTugas() { return nilaiTugas; }
        public double getNilaiUts() { return nilaiUts; }
        public double getNilaiUas() { return nilaiUas; }
        public double getNilaiAkhir() { return nilaiAkhir; }
        public double getNilaiMutu() { return nilaiMutu; }
        public double getBobot() { return bobot; }
        public String getGrade() { return grade; }
        public boolean isSudahDinilai() { return sudahDinilai; }
    }

    // DATA DUMMY
    private final List<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    private final List<Dosen> daftarDosen = new ArrayList<>();
    private final List<Krs> daftarKrs = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public PengelolaData() {
        isiDataDummy();
    }

    // Inisialisasi data dummy
    private void isiDataDummy() {
        // Mahasiswa: username, password, nama, nim, prodi
        daftarMahasiswa.add(new Mahasiswa("20255520001", "Naomi001", "Naomi", "20255520001", "Informatika"));
        daftarMahasiswa.add(new Mahasiswa("20255520002", "Citra001", "Citra Lestari", "20255520002", "Informatika"));
        daftarMahasiswa.add(new Mahasiswa("20255520003", "Dewi001", "Dewi Anggraini", "20255520003", "Sistem Informasi"));

        // Dosen: username, password, nama, nidn, daftar kode matkul yang diampu
        List<String> matkulAry = new ArrayList<>();
        matkulAry.add("IF101"); matkulAry.add("IF103");
        daftarDosen.add(new Dosen("IFD001", "Ary001", "Ary Budi", "IFD001", matkulAry));

        List<String> matkulSimon = new ArrayList<>();
        matkulSimon.add("IF102"); matkulSimon.add("IF104");
        daftarDosen.add(new Dosen("IFD002", "Sari002", "Simon Barus", "IFD002", matkulSimon));

        // KRS (nilai awal kosong/0)
        String[] nim = {"20255520001", "20255520002", "20255520003"};
        for (String n : nim) {
            // 2025/2026 ganjil
            daftarKrs.add(new Krs(n, "2025/2026", "ganjil", "IF101", "Algoritma dan Pemrograman", 3, "0411001"));
            daftarKrs.add(new Krs(n, "2025/2026", "ganjil", "IF102", "Pemrograman Berorientasi Objek", 3, "0411002"));
            // 2025/2026 genap
            daftarKrs.add(new Krs(n, "2025/2026", "genap", "IF103", "Basis Data", 3, "0411001"));
            daftarKrs.add(new Krs(n, "2025/2026", "genap", "IF104", "Struktur Data", 4, "0411002"));
        }
        // 2026/2027 ganjil
        daftarKrs.add(new Krs("20255520001", "2026/2027", "ganjil", "IF103", "Basis Data", 3, "0411001"));
        daftarKrs.add(new Krs("20255520002", "2026/2027", "ganjil", "IF102", "Pemrograman Berorientasi Objek", 3, "0411002"));

        // Contoh nilai awal buat nyoba
        // updateNilai(daftarKrs.get(0), 85, 80, 90);
        // updateNilai(daftarKrs.get(1), 75, 70, 72);
    }

    // ===================== Login =====================
    // Validasi login
    public Pengguna login(String username, String password) {
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getUsername().equals(username) && m.getPassword().equals(password)) return m;
        }
        for (Dosen d : daftarDosen) {
            if (d.getUsername().equals(username) && d.getPassword().equals(password)) return d;
        }
        return null;
    }

    // ===================== Pengambilan data =====================
    public Scanner getScanner() { return scanner; }

    // Nama mata kuliah berdasarkan kode
    public String getNamaMatkul(String kodeMatkul) {
        for (Krs k : daftarKrs) {
            if (k.getKodeMatkul().equals(kodeMatkul)) return k.getNamaMatkul();
        }
        return kodeMatkul;
    }

    public String getNamaMahasiswa(String nim) {
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equals(nim)) return m.getNama();
        }
        return "-";
    }

    // Semua KRS (mahasiswa) untuk satu mata kuliah
    public List<Krs> getKrsByMatkul(String kodeMatkul) {
        List<Krs> hasil = new ArrayList<>();
        for (Krs k : daftarKrs) {
            if (k.getKodeMatkul().equals(kodeMatkul)) hasil.add(k);
        }
        return hasil;
    }

    // Daftar periode unik
    public List<String> getDaftarPeriode(String nim) {
        List<String> hasil = new ArrayList<>();
        for (Krs k : daftarKrs) {
            if (k.getNim().equals(nim) && !hasil.contains(k.getPeriode())) hasil.add(k.getPeriode());
        }
        return hasil;
    }

    // KRS seorang mahasiswa pada periode tertentu, contoh "2025/2026 ganjil".
    public List<Krs> getKrsByPeriode(String nim, String periode) {
        List<Krs> hasil = new ArrayList<>();
        for (Krs k : daftarKrs) {
            if (k.getNim().equals(nim) && k.getPeriode().equals(periode)) hasil.add(k);
        }
        return hasil;
    }

    // ===================== Update nilai =====================
    // Simpan nilai tugas, UTS, UAS; nilai akhir/grade/bobot dihitung otomatis
    public void updateNilai(Krs krs, double tugas, double uts, double uas) {
        krs.isiNilai(tugas, uts, uas);
    }
}