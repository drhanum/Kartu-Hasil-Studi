import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dosen extends Pengguna {
    private String nidn;
    private List<String> listMatkulDiampu;   // berisi kode mata kuliah

    public Dosen(String username, String password, String nama, String nidn, List<String> listMatkulDiampu) {
        super(username, password, nama);
        this.nidn = nidn;
        this.listMatkulDiampu = new ArrayList<>(listMatkulDiampu);
    }

    public String getNidn() { return nidn; }
    public void setNidn(String nidn) { this.nidn = nidn; }

    public List<String> getListMatkulDiampu() { return new ArrayList<>(listMatkulDiampu); }
    public void setListMatkulDiampu(List<String> list) { this.listMatkulDiampu = new ArrayList<>(list); }

    // Menu dosen
    public void tampilkanMenuDosen(PengelolaData pd) {
        Scanner sc = pd.getScanner();
        while (true) {
            System.out.println("\n=== MENU DOSEN (" + getNama() + ") ===");
            System.out.println("1. View Nilai");
            System.out.println("2. Input Nilai");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1" -> tampilkanNilai(pd); // output aja, lalu kembali ke menu
                case "2" -> inputNilai(pd);
                case "3" -> {
                    System.out.println("Logout berhasil."); return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // View nilai per mata kuliah: nama, NIM, tugas, UTS, UAS, total nilai, grade.
    // Override
    @Override
    public void tampilkanNilai(PengelolaData pd) {
        for (String kode : listMatkulDiampu) {
            tampilkanNilai(pd, kode);   // memakai overload per mata kuliah
        }
    }
 
    // Overload
    public void tampilkanNilai(PengelolaData pd, String kodeMatkul) {
        if (!listMatkulDiampu.contains(kodeMatkul)) {
            System.out.println("Mata kuliah " + kodeMatkul + " tidak diampu oleh dosen ini.");
            return;
        }
        System.out.println("\n[" + kodeMatkul + "] " + pd.getNamaMatkul(kodeMatkul));
        System.out.printf("%-20s %-9s %7s %7s %7s %7s %-5s%n",
                "Nama", "NIM", "Tugas", "UTS", "UAS", "Total", "Grade");
        for (PengelolaData.Krs k : pd.getKrsByMatkul(kodeMatkul)) {
            String nama = pd.getNamaMahasiswa(k.getNim());
            if (k.isSudahDinilai()) {
                System.out.printf("%-20s %-9s %7.1f %7.1f %7.1f %7.2f %-5s%n", nama, k.getNim(),
                        k.getNilaiTugas(), k.getNilaiUts(), k.getNilaiUas(),
                        k.getNilaiAkhir(), k.getGrade());
            } else {
                System.out.printf("%-20s %-9s %7s %7s %7s %7s %-5s%n", nama, k.getNim(),
                        "-", "-", "-", "-", "-");
            }
        }
    }

    // Alur input nilai: pilih matkul -> pilih mahasiswa -> input nilai. 'x' = kembali satu tingkat. 
    private void inputNilai(PengelolaData pd) {
        Scanner sc = pd.getScanner();
        while (true) {
            // Pilih mata kuliah
            System.out.println("\nPilih mata kuliah:");
            for (int i = 0; i < listMatkulDiampu.size(); i++) {
                System.out.println((i + 1) + ". " + pd.getNamaMatkul(listMatkulDiampu.get(i)));
            }
            System.out.println("x. Keluar");
            System.out.print("Pilih: ");
            String inMatkul = sc.nextLine().trim();
            if (inMatkul.equalsIgnoreCase("x")) return;   // kembali ke menu dosen

            int idxMatkul = parseIndex(inMatkul, listMatkulDiampu.size());
            if (idxMatkul < 0) { System.out.println("Pilihan tidak valid."); continue; }
            String kodeMatkul = listMatkulDiampu.get(idxMatkul);

            // Pilih mahasiswa
            while (true) {
                List<PengelolaData.Krs> peserta = pd.getKrsByMatkul(kodeMatkul);
                System.out.println("\nPilih mahasiswa (" + pd.getNamaMatkul(kodeMatkul) + "):");
                for (int i = 0; i < peserta.size(); i++) {
                    PengelolaData.Krs k = peserta.get(i);
                    System.out.println((i + 1) + ". " + pd.getNamaMahasiswa(k.getNim())
                            + " [" + k.getNim() + "] - " + k.getPeriode());
                }
                System.out.println("x. Keluar");
                System.out.print("Pilih: ");
                String inMhs = sc.nextLine().trim();
                if (inMhs.equalsIgnoreCase("x")) break;   // kembali ke pilih matkul

                int idxMhs = parseIndex(inMhs, peserta.size());
                if (idxMhs < 0) { System.out.println("Pilihan tidak valid."); continue; }
                PengelolaData.Krs krs = peserta.get(idxMhs);

                // --- Input nilai ---
                System.out.println("\nNama MS    : " + pd.getNamaMahasiswa(krs.getNim()));
                System.out.println("NIM MS     : " + krs.getNim());
                System.out.println("Mata kuliah: " + krs.getNamaMatkul());
                double tugas = bacaNilai(sc, "1. Tugas");
                double uts = bacaNilai(sc, "2. UTS  ");
                double uas = bacaNilai(sc, "3. UAS  ");
                pd.updateNilai(krs, tugas, uts, uas);
                System.out.println("Nilai tersimpan.");
                // setelah selesai, otomatis kembali ke pilihan mahasiswa
            }
        }
    }

    // Ubah input "1".."n" menjadi index 0..n-1; -1 jika tidak valid.
    private int parseIndex(String input, int ukuran) {
        try {
            int n = Integer.parseInt(input);
            return (n >= 1 && n <= ukuran) ? n - 1 : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Baca nilai 0-100; ulangi sampai valid.
    private double bacaNilai(Scanner sc, String label) {
        while (true) {
            System.out.print(label + " (0-100): ");
            try {
                double v = Double.parseDouble(sc.nextLine().trim());
                if (v >= 0 && v <= 100) return v;
            } catch (NumberFormatException e) { /* jatuh ke pesan di bawah */ }
            System.out.println("Nilai harus angka 0-100.");
        }
    }
}