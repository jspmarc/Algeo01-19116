# Tubes Pertama IF2123 Aljabar Linier dan Geometri
### Algeo01-19116

Kelompok 59

| Nama | NIM |
|------|-----|
| Jeane Mikha Erwansyah | 13519116 |
| Josep Marcello | 13519164 |
| David Owen Adiwiguna | 13519169 |

[SOP](./SOP.md)

## Pembagian tugas

| NIM | Tugas |
|-----|-------|
| 13519116 | Metode Gauss, Gauss-Jordan, Pembuat program utama |
| 13519164 | Determinan matriks metode ekspansi kofaktor dan reduksi baris, interpolasi polinom |
| 13519169 | Matriks balikan, Kaidah cramer, regresi linier berganda |

## Spesifikasi Tugas

[Tugas Besar 1 IF 2123 Aljabar Linier dan  Geometri](http://informatika.stei.itb.ac.id/~rinaldi.munir/AljabarGeometri/2020-2021/Tubes1-Algeo-2020.pdf)

1. Menghitung solusi SPL dengan metode eliminasi Gauss, Gauss-Jordan, matriks
balikan, dan kaidah Cramer
> Kaidah Cramer khusus untuk SPL dengan $n$ peubah dan $n$ variabel
2. Menyelesaikan persoalan interpolasi polinom dan regresi linier berganda
3. Menghitung matriks balikan
4. Menghitung determinan matriks dengan metode reduksi baris dan ekspansi
kofaktor

## Petunjuk Instalasi

Jalankan perintah `make jar`.
Tidak punya `make`?
Jalankan:
1. `git clone https://github.com/jspmarc/Algeo01-19116`
1. `javac -g -d bin src/Matriks.java src/Main.java`
1. `jar -cvfe bin/tubes.jar Main -C bin/Matriks.class -C bin/Main.class`

## Petunjuk Penggunaan

jalankan perintah `make`
Tidak punya `make`?
1. Pastikan Anda sudah melalui proses instalasi
1. `java -jar bin/tubes.jar`
