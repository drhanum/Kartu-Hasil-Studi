Tentu. Di bawah ini adalah **README lengkap versi panduan anggota kelompok** yang mengikuti struktur sistem KHS yang sudah kita sepakati: mulai dari konsep sistem, struktur class, relasi, login, alur mahasiswa/dosen, perhitungan nilai, tampilan, sampai materi OOP yang harus terlihat di program.

# README — Sistem Kartu Hasil Studi (KHS) Java

## 1. Deskripsi Proyek

**Sistem Kartu Hasil Studi (KHS)** adalah aplikasi berbasis Java untuk mengelola data akademik mahasiswa, dosen, mata kuliah, nilai, dan KHS.

Sistem memiliki dua jenis pengguna utama:

- **Mahasiswa**
  - Login
  - Request/melihat KHS berdasarkan periode
  - Melihat hasil nilai dalam bentuk **Grade** dan **Nilai Mutu**

- **Dosen**
  - Login
  - Melihat daftar nilai mahasiswa yang diampu
  - Menginput nilai mahasiswa
  - Melihat detail nilai mahasiswa

Sistem dibuat menggunakan konsep **Object-Oriented Programming (OOP)** pada Java.

---

# 2. Tujuan Proyek

Proyek ini dibuat untuk menerapkan konsep:

1. Class dan Object
2. Attribute dan tipe data
3. Method/behavior
4. Constructor
5. Default constructor
6. Parameterized constructor
7. Method overloading
8. Access modifier
9. Data hiding
10. Getter dan Setter
11. Validasi data
12. Inheritance
13. Superclass dan Subclass
14. Generalisasi
15. Reuse
16. Polymorphism
17. Method overriding
18. Dynamic binding
19. Upcasting

Struktur program harus dibuat sedemikian rupa sehingga konsep-konsep tersebut **benar-benar terlihat dalam source code**, bukan hanya dijelaskan secara teori.

---

# 3. Struktur Class yang FIX

Struktur utama sistem:

```text
                         Pengguna
                       <<superclass>>
                            ▲
                 ┌──────────┴──────────┐
                 │                     │
               Dosen               Mahasiswa
             <<subclass>>         <<subclass>>
                 │                     │
                 │                     │
          inputNilai()            requestKHS()
          viewNilai()             viewNilai()
                 │                     │
                 └──────────┬──────────┘
                            │
                     PengelolaData
```

Class utama yang digunakan:

```text
1. Pengguna
2. Dosen
3. Mahasiswa
4. PengelolaData
5. Main
```

---

# 4. Class Pengguna

`Pengguna` merupakan **superclass** yang menjadi dasar untuk `Dosen` dan `Mahasiswa`.

## Tanggung jawab

Class ini menyimpan data umum yang dimiliki semua pengguna sistem.

Contoh attribute:

```java
private String username;
private String password;
private String nama;
```

Struktur konsepnya:

```java
public class Pengguna {
    
    private String username;
    private String password;
    private String nama;

    public Pengguna() {
    }

    public Pengguna(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    // getter dan setter
}
```

### Kenapa `private`?

Karena kita menerapkan **data hiding**.

Attribute tidak boleh diakses langsung dari luar class.

Contoh:

```java
pengguna.password
```

tidak diperbolehkan jika `password` bersifat `private`.

Akses dilakukan melalui:

```java
getPassword()
setPassword()
```

---

# 5. Class Dosen

`Dosen` merupakan **subclass** dari `Pengguna`.

```java
public class Dosen extends Pengguna {
    
    // attribute khusus dosen
}
```

Karena menggunakan inheritance:

```text
Pengguna
   ↓
 Dosen
```

Dosen otomatis dapat menggunakan data dan method umum dari `Pengguna`.

## Tanggung jawab Dosen

Dosen dapat:

1. Login
2. Melihat nilai mahasiswa
3. Menginput nilai mahasiswa

Method utama:

```java
inputNilai()
viewNilai()
```

---

# 6. Class Mahasiswa

`Mahasiswa` juga merupakan subclass dari `Pengguna`.

```java
public class Mahasiswa extends Pengguna {
    
    // attribute khusus mahasiswa
}
```

Struktur inheritance:

```text
          Pengguna
          /      \
       Dosen   Mahasiswa
```

## Tanggung jawab Mahasiswa

Mahasiswa dapat:

1. Login
2. Request KHS
3. Melihat hasil nilai

Method utama:

```java
requestKHS()
viewNilai()
```

---

# 7. Polymorphism dan Override

Salah satu bagian penting dari sistem adalah `viewNilai()`.

Method tersebut dimiliki oleh dua subclass:

```text
Dosen
  └── viewNilai()

Mahasiswa
  └── viewNilai()
```

Namun fungsi yang ditampilkan berbeda.

### Dosen

Dosen dapat melihat **detail nilai**:

```text
Nama
NIM
Nilai Tugas
Nilai UTS
Nilai UAS
Nilai Total
Grade
```

### Mahasiswa

Mahasiswa hanya melihat hasil akhir:

```text
Mata Kuliah
Grade
Nilai Mutu
```

Jadi meskipun nama method sama:

```java
viewNilai()
```

perilakunya berbeda sesuai objek yang digunakan.

Hal ini merupakan:

> **Method Overriding + Polymorphism**

---

# 8. Contoh Konsep Override

Secara konsep:

```java
class Dosen extends Pengguna {

    @Override
    public void viewNilai() {
        // tampilkan detail nilai mahasiswa
    }
}
```

Sedangkan:

```java
class Mahasiswa extends Pengguna {

    @Override
    public void viewNilai() {
        // tampilkan grade dan nilai mutu
    }
}
```

Jadi:

```text
viewNilai()
     │
     ├── Dosen      → detail nilai
     │
     └── Mahasiswa  → grade + nilai mutu
```

---

# 9. PengelolaData

`PengelolaData` bertugas mengelola data yang digunakan sistem.

Class ini bukan turunan dari `Pengguna`.

Secara sederhana:

```text
Pengguna
 ├── Dosen
 └── Mahasiswa

PengelolaData
```

`PengelolaData` digunakan untuk menyimpan dan mencari data.

Contohnya:

```java
ArrayList<Dosen> daftarDosen;
ArrayList<Mahasiswa> daftarMahasiswa;
```

Data yang dikelola dapat meliputi:

- Data pengguna
- Data mahasiswa
- Data dosen
- Data mata kuliah
- Data nilai
- Data periode akademik
- Relasi dosen dengan mata kuliah
- Relasi mahasiswa dengan mata kuliah

---

# 10. Relasi Dosen dengan Mata Kuliah

Dosen tidak menginput nilai untuk semua mata kuliah.

Dosen hanya dapat menginput nilai untuk mata kuliah yang **diampunya**.

Contoh data dummy:

```text
Dosen:
D001 - Budi Santoso

Mata Kuliah:
Pemrograman Berorientasi Objek
```

Jika Budi merupakan pengampu PBO:

```text
Budi Santoso
      │
      └── Pemrograman Berorientasi Objek
```

Saat dosen login dan memilih:

```text
=== INPUT NILAI ===

Pilih Mata Kuliah:
1. Pemrograman Berorientasi Objek
2. Basis Data
```

maka hanya mata kuliah yang memang berkaitan dengan dosen tersebut yang ditampilkan.

---

# 11. Relasi Mahasiswa dengan Mata Kuliah

Mahasiswa juga memiliki daftar mata kuliah yang diambil.

Contoh:

```text
Mahasiswa:
M001 - Andi

Mengambil:
- Pemrograman Berorientasi Objek
- Basis Data
- Sistem Operasi
```

Nilai mahasiswa disimpan berdasarkan kombinasi data seperti:

```text
Mahasiswa
+
Mata Kuliah
+
Periode
+
Nilai
```

Dengan demikian, nilai dapat dibedakan berdasarkan semester/periode.

---

# 12. Periode KHS

Sistem **tidak menggunakan pilihan semester angka seperti 1, 2, 3, dst.**

Periode KHS menggunakan kombinasi:

```text
Tahun Ajaran + Semester
```

Contoh:

```text
2026/2027 - Ganjil
2026/2027 - Genap
2025/2026 - Genap
```

Mahasiswa cukup memilih **satu kali** periode.

Contoh:

```text
=== REQUEST KHS ===

Pilih Periode:

1. 2026/2027 - Ganjil
2. 2026/2027 - Genap
3. 2025/2026 - Genap

Pilihan:
```

Tidak perlu:

```text
Pilih tahun ajaran:
...

Pilih semester:
...
```

Karena keduanya sudah menjadi satu kesatuan periode.

---

# 13. Login

Saat program dijalankan:

```text
=================================
       SISTEM KHS
=================================

Username : 
Password :

=================================
```

Sistem mencocokkan username dan password dengan data pengguna.

Jika pengguna merupakan dosen:

```text
Login berhasil.

Selamat datang, Budi Santoso

=== MENU DOSEN ===
1. Input Nilai
2. View Nilai
3. Logout
```

Jika pengguna merupakan mahasiswa:

```text
Login berhasil.

Selamat datang, Andi

=== MENU MAHASISWA ===
1. Request KHS
2. View Nilai
3. Logout
```

---

# 14. Menu Dosen

Setelah login sebagai dosen:

```text
=================================
          MENU DOSEN
=================================

1. Input Nilai
2. View Nilai
3. Logout

Pilih menu:
```

---

# 15. Alur Input Nilai Dosen

Ketika memilih:

```text
1. Input Nilai
```

dosen terlebih dahulu memilih mata kuliah yang diampunya.

Contoh:

```text
=== INPUT NILAI ===

Pilih Mata Kuliah:

1. Pemrograman Berorientasi Objek
2. Basis Data

Pilihan:
```

Setelah memilih mata kuliah, sistem menampilkan mahasiswa yang mengambil mata kuliah tersebut.

Contoh:

```text
=== DAFTAR MAHASISWA ===

1. M001 - Andi
2. M002 - Budi
3. M003 - Citra

Pilih mahasiswa:
```

Kemudian dosen memasukkan:

```text
Nilai Tugas :
Nilai UTS   :
Nilai UAS   :
```

Sistem kemudian menghitung:

```text
Nilai Total
Grade
Nilai Mutu
```

---

# 16. Perhitungan Nilai

Perhitungan nilai menggunakan komponen:

```text
Nilai Tugas
Nilai UTS
Nilai UAS
```

Formula yang digunakan:

```text
Nilai Total =
(30% × Nilai Tugas)
+
(30% × Nilai UTS)
+
(40% × Nilai UAS)
```

atau dalam Java:

```java
double nilaiTotal =
        (0.30 * nilaiTugas)
        + (0.30 * nilaiUTS)
        + (0.40 * nilaiUAS);
```

Contoh:

```text
Tugas = 80
UTS   = 75
UAS   = 90
```

Maka:

```text
Nilai Total =
(0.30 × 80)
+ (0.30 × 75)
+ (0.40 × 90)

= 24 + 22.5 + 36

= 82.5
```

---

# 17. Konversi Nilai ke Grade

Nilai total kemudian dikonversikan menjadi grade.

Gunakan aturan:

| Nilai Total | Grade | Nilai Mutu |
|---:|:---:|---:|
| 85–100 | A | 4.0 |
| 80–84 | AB | 3.5 |
| 70–79 | B | 3.0 |
| 65–69 | BC | 2.5 |
| 55–64 | C | 2.0 |
| 40–54 | D | 1.0 |
| 0–39 | E | 0.0 |

> Jika dosen/pengampu tugas memberikan tabel konversi resmi yang berbeda, bagian ini adalah satu-satunya bagian yang perlu disesuaikan.

Contoh:

```text
Nilai Total = 82.5

82.5 → AB
AB → 3.5
```

Jadi:

```text
Grade     : AB
Nilai Mutu: 3.5
```

---

# 18. Validasi Nilai

Nilai yang dimasukkan harus berada dalam rentang:

```text
0 sampai 100
```

Contoh input valid:

```text
Nilai Tugas : 80
Nilai UTS   : 75
Nilai UAS   : 90
```

Contoh tidak valid:

```text
Nilai Tugas : 120
```

Sistem harus menolak input tersebut.

Contoh:

```text
Nilai harus berada di antara 0-100.
Silakan masukkan kembali.
```

Validasi ini merupakan bagian dari konsep:

> **Data validation**

---

# 19. Menu View Nilai Dosen

Jika dosen memilih:

```text
2. View Nilai
```

dosen dapat melihat **detail nilai mahasiswa**.

Contoh:

```text
===============================================================
                    DATA NILAI MAHASISWA
===============================================================
Nama     NIM       Tugas    UTS    UAS    Total    Grade
---------------------------------------------------------------
Andi     M001      80       75     90     82.5     AB
Budi     M002      90       85     88     87.7     A
Citra    M003      75       70     80     76.5     B
===============================================================
```

Dosen dapat melihat:

- Nama
- NIM
- Nilai Tugas
- Nilai UTS
- Nilai UAS
- Nilai Total
- Grade

Ini merupakan tampilan **detail** yang memang menjadi hak akses dosen.

---

# 20. Request KHS Mahasiswa

Mahasiswa memilih:

```text
1. Request KHS
```

Kemudian memilih periode.

Contoh:

```text
=== REQUEST KHS ===

Pilih Periode:

1. 2026/2027 - Ganjil
2. 2026/2027 - Genap
3. 2025/2026 - Genap

Pilihan:
```

Setelah memilih periode:

```text
2026/2027 - Ganjil
```

sistem menampilkan KHS mahasiswa pada periode tersebut.

---

# 21. Tampilan KHS Mahasiswa

Mahasiswa **tidak melihat detail nilai Tugas, UTS, dan UAS**.

Mahasiswa hanya melihat hasil akhirnya.

Contoh:

```text
=================================================
                 KHS MAHASISWA
=================================================

Nama           : Andi
NIM            : M001
Periode        : 2026/2027 - Ganjil

-------------------------------------------------
Mata Kuliah                         Grade   Mutu
-------------------------------------------------
Pemrograman Berorientasi Objek       AB     3.5
Basis Data                            A     4.0
Sistem Operasi                        B     3.0
-------------------------------------------------
```

Jadi perbedaan hak aksesnya jelas:

### Dosen

```text
Tugas
UTS
UAS
Total
Grade
```

### Mahasiswa

```text
Grade
Nilai Mutu
```

---

# 22. Perbedaan Hak Akses

| Fitur | Dosen | Mahasiswa |
|---|:---:|:---:|
| Login | ✓ | ✓ |
| Input nilai | ✓ | ✗ |
| Melihat nilai detail | ✓ | ✗ |
| Melihat Tugas | ✓ | ✗ |
| Melihat UTS | ✓ | ✗ |
| Melihat UAS | ✓ | ✗ |
| Melihat Nilai Total | ✓ | ✗ |
| Melihat Grade | ✓ | ✓ |
| Melihat Nilai Mutu | ✓ | ✓ |
| Request KHS | ✗ | ✓ |
| Memilih periode KHS | ✗ | ✓ |

---

# 23. Contoh Data Dummy

Agar program mudah dites, gunakan beberapa data dummy.

## Dosen

```text
D001
Nama     : Budi Santoso
Username : dosen1
Password : 12345
```

```text
D002
Nama     : Siti Aminah
Username : dosen2
Password : 12345
```

## Mahasiswa

```text
M001
Nama     : Andi
Username : mhs1
Password : 12345
```

```text
M002
Nama     : Budi
Username : mhs2
Password : 12345
```

```text
M003
Nama     : Citra
Username : mhs3
Password : 12345
```

---

# 24. Contoh Relasi Data Dummy

Misalnya:

```text
D001 - Budi Santoso
      │
      ├── Pemrograman Berorientasi Objek
      └── Basis Data
```

Sedangkan:

```text
D002 - Siti Aminah
      │
      └── Sistem Operasi
```

Mahasiswa:

```text
M001 - Andi
      │
      ├── Pemrograman Berorientasi Objek
      ├── Basis Data
      └── Sistem Operasi
```

```text
M002 - Budi
      │
      ├── Pemrograman Berorientasi Objek
      └── Basis Data
```

```text
M003 - Citra
      │
      ├── Pemrograman Berorientasi Objek
      └── Sistem Operasi
```

Dengan data seperti ini, alur program lebih mudah diuji.

---

# 25. Penggunaan Constructor

Setiap class dapat menggunakan constructor sesuai kebutuhan.

## Default Constructor

Contoh:

```java
public Pengguna() {
}
```

## Parameterized Constructor

Contoh:

```java
public Pengguna(String username, String password, String nama) {
    this.username = username;
    this.password = password;
    this.nama = nama;
}
```

Constructor digunakan untuk memberikan nilai awal pada object.

Contoh:

```java
Mahasiswa mhs = new Mahasiswa(
    "M001",
    "Andi",
    "mhs1",
    "12345"
);
```

---

# 26. Method Overloading

Method overloading digunakan sebagai salah satu penerapan OOP.

Contohnya:

```java
public void setNilai(double nilai) {
    ...
}
```

dan:

```java
public void setNilai(double tugas, double uts, double uas) {
    ...
}
```

Nama method sama:

```text
setNilai()
```

tetapi parameter berbeda.

Hal ini disebut:

> **Method Overloading**

---

# 27. Access Modifier

Gunakan access modifier sesuai tanggung jawab masing-masing data.

Attribute sebaiknya:

```java
private
```

Contoh:

```java
private String nama;
private String nim;
private double nilaiTugas;
private double nilaiUTS;
private double nilaiUAS;
```

Method yang memang digunakan dari luar class:

```java
public
```

Contoh:

```java
public String getNama()
public void setNama(String nama)
public void viewNilai()
```

---

# 28. Getter dan Setter

Contoh:

```java
public String getNama() {
    return nama;
}

public void setNama(String nama) {
    this.nama = nama;
}
```

Getter digunakan untuk mengambil data.

Setter digunakan untuk mengubah data.

Setter juga dapat digunakan untuk validasi.

Contoh:

```java
public void setNilaiUTS(double nilaiUTS) {
    if (nilaiUTS >= 0 && nilaiUTS <= 100) {
        this.nilaiUTS = nilaiUTS;
    }
}
```

---

# 29. Inheritance

Inheritance diterapkan melalui:

```java
public class Dosen extends Pengguna
```

dan:

```java
public class Mahasiswa extends Pengguna
```

Sehingga:

```text
              Pengguna
                 │
        ┌────────┴────────┐
        │                 │
      Dosen           Mahasiswa
```

Keuntungan:

- Mengurangi duplikasi code
- Menggunakan kembali attribute dan method
- Menunjukkan hubungan superclass-subclass
- Menerapkan generalisasi

---

# 30. Upcasting

Upcasting dapat diterapkan dengan:

```java
Pengguna pengguna = new Mahasiswa(...);
```

atau:

```java
Pengguna pengguna = new Dosen(...);
```

Karena:

```text
Mahasiswa IS-A Pengguna
Dosen IS-A Pengguna
```

Object subclass dapat direferensikan menggunakan tipe superclass.

---

# 31. Dynamic Binding

Misalnya:

```java
Pengguna pengguna;

pengguna = new Dosen(...);
pengguna.viewNilai();
```

Jika `viewNilai()` dioverride oleh `Dosen`, maka Java akan menjalankan:

```text
Dosen.viewNilai()
```

Jika:

```java
pengguna = new Mahasiswa(...);
pengguna.viewNilai();
```

maka yang dijalankan:

```text
Mahasiswa.viewNilai()
```

Jadi method yang dipanggil ditentukan berdasarkan object sebenarnya pada saat runtime.

Inilah:

> **Dynamic Binding**

---

# 32. Alur Program Keseluruhan

Secara keseluruhan:

```text
START
  │
  ▼
Login
  │
  ├───────────────┐
  │               │
  ▼               ▼
 Dosen         Mahasiswa
  │               │
  ▼               ▼
Menu Dosen     Menu Mahasiswa
  │               │
  ├─ Input Nilai  ├─ Request KHS
  │               │
  └─ View Nilai   └─ View Nilai
  │               │
  └───────┬───────┘
          │
          ▼
       Logout
          │
          ▼
         END
```

---

# 33. Alur Input Nilai

```text
Dosen Login
     │
     ▼
Menu Dosen
     │
     ▼
Input Nilai
     │
     ▼
Pilih Mata Kuliah
     │
     ▼
Pilih Mahasiswa
     │
     ▼
Input Nilai Tugas
     │
     ▼
Input Nilai UTS
     │
     ▼
Input Nilai UAS
     │
     ▼
Hitung Nilai Total
     │
     ▼
Tentukan Grade
     │
     ▼
Tentukan Nilai Mutu
     │
     ▼
Simpan Data Nilai
```

---

# 34. Alur Request KHS

```text
Mahasiswa Login
       │
       ▼
Menu Mahasiswa
       │
       ▼
Request KHS
       │
       ▼
Pilih Periode
       │
       ▼
Cari nilai mahasiswa
pada periode tersebut
       │
       ▼
Tampilkan KHS
       │
       ▼
Grade + Nilai Mutu
```

---

# 35. Struktur File yang Disarankan

Struktur project:

```text
SistemKHS/
│
├── src/
│   ├── Main.java
│   ├── Pengguna.java
│   ├── Dosen.java
│   ├── Mahasiswa.java
│   └── PengelolaData.java
│
└── README.md
```

Untuk versi awal, cukup menggunakan class-class tersebut.

Jika nantinya diperlukan class tambahan, penambahan harus tetap mengikuti rancangan sistem dan tidak mengubah struktur utama yang sudah disepakati tanpa alasan.

---

# 36. Tanggung Jawab Setiap Class

| Class | Tanggung Jawab |
|---|---|
| `Pengguna` | Menyimpan data umum pengguna |
| `Dosen` | Fitur dosen, input nilai, view nilai detail |
| `Mahasiswa` | Fitur mahasiswa, request KHS, view nilai |
| `PengelolaData` | Mengelola seluruh data sistem |
| `Main` | Menjalankan program dan mengatur alur menu |

---

# 37. Prinsip Pembagian Code

Jangan menaruh seluruh program di `Main`.

**Salah:**

```java
public class Main {
    // semua data
    // semua perhitungan
    // semua login
    // semua menu
    // semua validasi
}
```

Lebih baik:

```text
Pengguna
    ↓
menyimpan data pengguna

Dosen
    ↓
fitur dosen

Mahasiswa
    ↓
fitur mahasiswa

PengelolaData
    ↓
mengelola data

Main
    ↓
menjalankan program
```

Dengan demikian struktur OOP terlihat jelas.

---

# 38. Checklist Materi OOP

Sebelum project dikumpulkan, pastikan semua materi berikut sudah benar-benar ada.

### Class & Object

- [ ] Ada `Pengguna`
- [ ] Ada `Dosen`
- [ ] Ada `Mahasiswa`
- [ ] Ada `PengelolaData`
- [ ] Ada object dari masing-masing class

### Attribute

- [ ] Attribute memiliki tipe data
- [ ] Attribute menggunakan access modifier yang sesuai

### Constructor

- [ ] Default constructor
- [ ] Parameterized constructor

### Method

- [ ] Method sesuai tanggung jawab class
- [ ] Method memiliki access modifier yang sesuai

### Access Modifier

- [ ] Attribute menggunakan `private`
- [ ] Method yang digunakan dari luar menggunakan `public`

### Data Hiding

- [ ] Attribute tidak diakses langsung
- [ ] Menggunakan getter/setter

### Validasi

- [ ] Nilai hanya 0–100
- [ ] Login harus sesuai data
- [ ] Pilihan menu harus valid
- [ ] Pilihan mahasiswa/mata kuliah harus valid

### Inheritance

- [ ] `Dosen extends Pengguna`
- [ ] `Mahasiswa extends Pengguna`

### Overriding

- [ ] `Dosen` override `viewNilai()`
- [ ] `Mahasiswa` override `viewNilai()`

### Polymorphism

- [ ] Object subclass dapat direferensikan sebagai `Pengguna`

### Dynamic Binding

- [ ] Pemanggilan `viewNilai()` melalui reference superclass menghasilkan behavior subclass yang sesuai

### Upcasting

- [ ] Ada contoh seperti:

```java
Pengguna pengguna = new Mahasiswa(...);
```

### Method Overloading

- [ ] Ada method dengan nama sama tetapi parameter berbeda

---

# 39. Hal yang Tidak Boleh Diubah

Struktur berikut dianggap **FIX** untuk project:

```text
                    Pengguna
                       ▲
              ┌────────┴────────┐
              │                 │
            Dosen           Mahasiswa
```

Method utama:

```text
Dosen:
- inputNilai()
- viewNilai()

Mahasiswa:
- requestKHS()
- viewNilai()
```

Perbedaan `viewNilai()`:

```text
Dosen
→ detail nilai:
  Tugas
  UTS
  UAS
  Total
  Grade

Mahasiswa
→ hasil:
  Grade
  Nilai Mutu
```

Periode KHS:

```text
Tahun Ajaran + Semester
```

Contoh:

```text
2026/2027 - Ganjil
```

bukan:

```text
Tahun Ajaran → 2026/2027
Semester      → Ganjil
```

secara terpisah dalam menu.

---

# 40. Kesimpulan Sistem

Sistem KHS ini menggunakan konsep inheritance dengan struktur:

```text
                 Pengguna
                /        \
             Dosen     Mahasiswa
```

`Pengguna` menjadi superclass yang menyimpan informasi umum.

`Dosen` dan `Mahasiswa` menjadi subclass dengan hak akses yang berbeda.

Dosen:

```text
Login
  ↓
Input Nilai
  ↓
View Nilai Detail
```

Mahasiswa:

```text
Login
  ↓
Request KHS
  ↓
Pilih Periode
  ↓
View Grade + Nilai Mutu
```

Perhitungan nilai:

```text
Tugas = 30%
UTS   = 30%
UAS   = 40%
```

kemudian:

```text
Nilai Total
     ↓
   Grade
     ↓
Nilai Mutu
```

Dengan struktur ini, project sudah mencakup **class, object, attribute, behavior, constructor, access modifier, data hiding, getter/setter, validasi, inheritance, generalisasi, reuse, overriding, polymorphism, dynamic binding, dan upcasting** sesuai materi OOP yang menjadi target project.