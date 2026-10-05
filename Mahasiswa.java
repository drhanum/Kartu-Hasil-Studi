import java.util.Scanner;

public class Mahasiswa extends Pengguna {

    // Attribute
    private final Scanner scanner;
    private String nim;
    private String programStudi;

    // Constructor
    public Mahasiswa(String nim, String nama, String programStudi) {
        super(nim, "", nama);
        this.scanner = new Scanner(System.in);
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public Mahasiswa(String nama, String email, String nim, String programStudi) {
        this(nim, nama, programStudi);
    }

    // Method
    public void requestKHS() {
        System.out.println("\n=== REQUEST KHS ===");
        System.out.println("Pilih Periode:");
        System.out.println("1. 2026/2027 - Ganjil");
        System.out.println("2. 2026/2027 - Genap");
        System.out.println("3. 2025/2026 - Genap");
        System.out.print("Pilihan: ");

        int pilihan = scanner.nextInt();
        String periode = "";

        switch (pilihan) {
            case 1:
                periode = "2026/2027 - Ganjil";
                break;
            case 2:
                periode = "2026/2027 - Genap";
                break;
            case 3:
                periode = "2025/2026 - Genap";
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        System.out.println("Permintaan KHS untuk mahasiswa dengan NIM " + nim
                + " dan periode " + periode + " telah diajukan.");
    }

    // Overriding: mengganti method viewNilai() dari class Pengguna
    @Override
    public void viewNilai() {
        viewNilai("Mahasiswa dengan NIM " + nim + " pada periode yang diminta");
    }

    // Overloading: method viewNilai() dengan parameter periode
    public void viewNilai(String periode) {
        System.out.println("=== NILAI KHS ===");
        System.out.println("Nama    : " + nama);
        System.out.println("NIM     : " + nim);
        System.out.println("Program Studi : " + programStudi);
        System.out.println("Periode : " + periode);
        System.out.println("-------------------------------------------------");
        System.out.printf("%-35s %-7s %-5s\n", "Mata Kuliah", "Grade", "Mutu");
        System.out.println("-------------------------------------------------");

        System.out.printf("%-35s %-7s %-5.1f\n", "Pemrograman Berorientasi Objek", "AB", 3.5);
        System.out.printf("%-35s %-7s %-5.1f\n", "Basis Data", "A", 4.0);
        System.out.printf("%-35s %-7s %-5.1f\n", "Sistem Operasi", "B", 3.0);
        System.out.println("-------------------------------------------------");
    }
}