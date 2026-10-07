import java.util.List;
import java.util.Scanner;

public class Mahasiswa extends Pengguna {
    private String nim;
    private String prodi;

    public Mahasiswa(String username, String password, String nama, String nim, String prodi) {
        super(username, password, nama);
        this.nim = nim;
        this.prodi = prodi;
    }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getProdi() { return prodi; }
    public void setProdi(String prodi) { this.prodi = prodi; }

    // Loop menu mahasiswa. Berhenti saat logout.
    public void tampilkanMenuMahasiswa(PengelolaData pd) {
        Scanner sc = pd.getScanner();
        while (true) {
            System.out.println("\n=== MENU MAHASISWA (" + getNama() + ") ===");
            System.out.println("1. Req KHS");
            System.out.println("2. Logout");
            System.out.print("Pilih: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1" -> tampilkanNilai(pd);
                case "2" -> {
                    System.out.println("Logout berhasil."); return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Override
    @Override
    public void tampilkanNilai(PengelolaData pd) {
        Scanner sc = pd.getScanner();
        List<String> periode = pd.getDaftarPeriode(nim);
 
        while (true) {
            System.out.println("\nPilih periode:");
            for (int i = 0; i < periode.size(); i++) {
                System.out.println((i + 1) + ". " + periode.get(i));
            }
            System.out.println("x. Keluar");
            System.out.print("Pilih: ");
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("x")) return;   // kembali ke menu mahasiswa
 
            int idx;
            try {
                idx = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                idx = -1;
            }
            if (idx < 0 || idx >= periode.size()) {
                System.out.println("Pilihan tidak valid.");
                continue;
            }
            tampilkanNilai(pd, periode.get(idx));   // memakai overload per periode
        }
    }
 
    // Overload
    public void tampilkanNilai(PengelolaData pd, String periode) {
        List<PengelolaData.Krs> daftar = pd.getKrsByPeriode(nim, periode);
 
        System.out.println("\n========== KARTU HASIL STUDI ==========");
        System.out.println("Nama    : " + getNama());
        System.out.println("NIM     : " + nim);
        System.out.println("Periode : " + periode);
        System.out.println("---------------------------------------");
        System.out.printf("%-32s %-6s %3s %7s %6s %5s%n", "Mata Kuliah", "Kode", "SKS", "N.Mutu", "Bobot", "Grade");
 
        int jumlahSks = 0;
        int sksTernilai = 0;       // SKS dari matkul yang sudah dinilai (untuk IPS)
        double totalMutu = 0;      // sum(bobot), bobot = SKS x nilai mutu
 
        for (PengelolaData.Krs k : daftar) {
            jumlahSks += k.getSks();
            if (k.isSudahDinilai()) {
                sksTernilai += k.getSks();
                totalMutu += k.getBobot();
                System.out.printf("%-32s %-6s %3d %7.2f %6.2f %5s%n", k.getNamaMatkul(), k.getKodeMatkul(),
                        k.getSks(), k.getNilaiMutu(), k.getBobot(), k.getGrade());
            } else {
                System.out.printf("%-32s %-6s %3d %7s %6s %5s%n", k.getNamaMatkul(), k.getKodeMatkul(),
                        k.getSks(), "-", "-", "-");
            }
        }
 
        double ips = (sksTernilai == 0) ? 0 : totalMutu / sksTernilai;
        System.out.println("---------------------------------------");
        System.out.println("Jumlah SKS : " + jumlahSks);
        System.out.printf("IPS        : %.2f%n", ips);
        if (sksTernilai < jumlahSks) {
            System.out.println("(IPS hanya dihitung dari matkul yang sudah dinilai)");
        }
    }
}
 