# Sistem Kartu Hasil Studi (KHS) – CLI Java

| No. | Topik | Nama |
|---|---|---|
| 1 | Class Pengguna | Theo |
| 2 | Class Dosen | Christian |
| 3 | Class Mahasiswa | Naomi |
| 4 | Class Main & Pengelola Data | Hanum |

Program berbasis CLI untuk mengelola nilai dan KHS dengan konsep OOP: **inheritance**, **encapsulation**, dan **polymorphism (method overriding)**. Data disimpan sebagai database dummy di memori, sehingga data hilang saat program ditutup.

## Struktur Project

```
khs/
├── Main.java            # Entry point & router
├── Pengguna.java        # Superclass
├── Dosen.java           # Subclass Pengguna
├── Mahasiswa.java       # Subclass Pengguna
├── PengelolaData.java   # Data center / database dummy (PD)
└── README.md
```

## Hierarki Kelas

```
Pengguna  (username, password, nama, tampilkanNilai())
 ├── Dosen      (+ nidn, listMatkulDiampu)   → override tampilkanNilai()
 └── Mahasiswa  (+ nim, prodi)               → override tampilkanNilai()

PengelolaData  (koleksi Mahasiswa, Dosen, Krs)
 └── Krs  (nested static class)

Main  (hanya router)
```

## Tanggung Jawab Tiap Kelas

| Kelas | Tanggung jawab |
|---|---|
| **Pengguna** | Atribut dasar (`username`, `password`, `nama`) dengan getter/setter. Menyediakan `tampilkanNilai(pd)` default yang di-override subclass. |
| **PengelolaData** | Menyimpan data dummy, validasi login, pengambilan data, dan update nilai. Berisi nested class `Krs`. Menyediakan satu `Scanner` bersama (`getScanner()`). |
| **Dosen** | Seluruh logika menu dosen: View Nilai, Input Nilai, Logout. |
| **Mahasiswa** | Seluruh logika request dan cetak KHS, serta Logout. |
| **Main** | Menu utama (Login / Exit), validasi login, dan routing ke menu Dosen atau Mahasiswa. |

## Aturan Desain (Rules)

1. **Enkapsulasi**: semua atribut `private`, diakses lewat getter/setter.
2. **Main hanya router**: tidak ada logika menu dosen atau mahasiswa di `Main`.
3. **Logika menu ada di kelasnya sendiri**:
   - `Dosen.tampilkanMenuDosen(PengelolaData pd)`
   - `Mahasiswa.tampilkanMenuMahasiswa(PengelolaData pd)`
4. **Data hanya lewat PengelolaData (PD)**: Dosen dan Mahasiswa tidak menyimpan data nilai sendiri.
5. **Nilai hanya bisa diubah lewat `PengelolaData.updateNilai()`**. Method penghitung di dalam `Krs` bersifat `private`, jadi kelas lain tidak bisa mengubah nilai secara langsung.
6. **Override**: `tampilkanNilai(PengelolaData pd)` di-override oleh `Dosen` (View Nilai) dan `Mahasiswa` (Cetak KHS), karena keduanya sama-sama menampilkan nilai dengan bentuk berbeda.
7. **Input `x`** pada menu pilihan berarti keluar / kembali satu tingkat.

## Alur Program

```
Main Menu ── 1. Login ──► validasi di PD
   │                        ├─ salah  → pesan salah, kembali ke prompt Login
   │                        └─ benar  → Dosen / Mahasiswa
   └── 2. Exit Program

Menu Dosen                          Menu Mahasiswa
 1. View Nilai  → tabel nilai,       1. Req KHS → pilih periode (x = keluar)
                  kembali ke menu                 → cetak KHS (nama, NIM, matkul,
 2. Input Nilai → pilih matkul (x)                  SKS, nilai mutu, bobot, grade,
                  → pilih mahasiswa (x)             jumlah SKS, IPS)
                  → input tugas/UTS/UAS            → kembali ke pilih periode
                  → kembali ke pilih mahasiswa   2. Logout
 3. Logout
Logout → kembali ke Main Menu
```

## Aturan Penilaian

**Total nilai** = 50% Tugas + 25% UTS + 25% UAS

| Total Nilai | Grade | Nilai Mutu |
|---|---|---|
| 85–100 | A | 4.0 |
| 80–84 | AB | 3.5 |
| 70–79 | B | 3.0 |
| 65–69 | BC | 2.5 |
| 55–64 | C | 2.0 |
| 40–54 | D | 1.0 |
| 0–39 | E | 0.0 |

- **Bobot** = SKS × Nilai Mutu
- **IPS** = Σ Bobot / Σ SKS (hanya dari mata kuliah yang sudah dinilai)
- **Jumlah SKS** = total SKS semua mata kuliah pada periode tersebut
- Input nilai harus berupa angka 0–100.

## Akun Dummy

| Peran | Username | Password | Keterangan |
|---|---|---|---|
| Mahasiswa | `andi` | `123` | NIM 2024001, Informatika |
| Mahasiswa | `citra` | `123` | NIM 2024002, Informatika |
| Mahasiswa | `dewi` | `123` | NIM 2024003, Sistem Informasi |
| Dosen | `budi` | `abc` | NIDN 0411001, mengampu IF101 & IF103 |
| Dosen | `sari` | `abc` | NIDN 0411002, mengampu IF102 & IF104 |

## Data Dummy KRS

| Periode | Mata kuliah |
|---|---|
| 2025/2026 ganjil | IF101 Algoritma dan Pemrograman (3), IF102 Pemrograman Berorientasi Objek (3) |
| 2025/2026 genap | IF103 Basis Data (3), IF104 Struktur Data (4) |
| 2026/2027 ganjil | Andi: IF103 · Citra: IF102 |

Semua nilai awal kosong; nilai muncul setelah dosen menginputnya.

## Cara Menjalankan

Syarat: JDK 8 atau lebih baru.

```bash
cd khs
javac *.java
java Main
```

Contoh alur uji:
1. Login `budi` / `abc` → Input Nilai → pilih matkul → pilih mahasiswa → isi nilai → `x` → Logout.
2. Login `andi` / `123` → Req KHS → pilih periode 2025/2026 ganjil → lihat KHS.

## Pengembangan Lanjutan (Opsional)

- Menyimpan data ke file/database agar tidak hilang saat program ditutup.
- Membuat `Krs` menjadi kelas terpisah (saat ini nested agar tetap 5 kelas sesuai spesifikasi).
- Menyimpan password dalam bentuk hash.