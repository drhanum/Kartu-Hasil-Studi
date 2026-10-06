import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PengelolaData pd = new PengelolaData();   // inisialisasi data dummy
        Scanner sc = pd.getScanner();

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Exit Program");
            System.out.print("Pilih: ");
            String pilihan = sc.nextLine().trim();

            if (pilihan.equals("2")) {
                System.out.println("Program selesai.");
                break;
            } else if (!pilihan.equals("1")) {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            //Login
            Pengguna user = null;
            while (user == null) {
                System.out.println("\n--- LOGIN ---");
                System.out.print("Username : ");
                String username = sc.nextLine().trim();
                System.out.print("Password : ");
                String password = sc.nextLine().trim();

                user = pd.login(username, password);
                if (user == null) {
                    System.out.println("Username atau password salah!");
                }
            }

            // Routing berdasarkan tipe pengguna
            switch (user) {
                case Dosen objectDosen -> objectDosen.tampilkanMenuDosen(pd);
                case Mahasiswa objectMahasiswa -> objectMahasiswa.tampilkanMenuMahasiswa(pd);
                default -> {
                }
            }
            // setelah logout, loop kembali ke Main Menu
        }
    }
}