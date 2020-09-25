/*
 * TODO
 * Implementaasi:
 *  - [ ] Gauss
 *  - [ ] Gauss-Jordan
 *  - [ ] Kramer
 *  - [x] Determinan
 *      - [x] Ekspansi kofaktor
 *      - [x] Reduksi baris
 */

import java.util.ArrayList; // Array dinamis untuk matriks
import java.util.Scanner;
import java.lang.Math;

/**
 * Class Matriks
 * Tipe data mariks adalah double dengan elemen awal di (0,0)
 * Indeks selalu diawali 0
 *
 * @author Jeane Mikha Erwansyah - 13519116
 * @author Josep Marcello - 13519164
 * @author David Owen Adiwiguna - 13519169
 *
 */

class Matriks {

    /*
     * DAFTAR FUNGSI DAN PROSEDUR:
     * *** CONSTRUCTOR ***
     *   - Matriks
     * *** GETTERS AND SETTERS ***
     *   - getElmt
     *   - setElmt
     *   - getBaris
     *   - setBaris
     * *** INPUTS/OUTPUTS ***
     *   - bacaMatriks
     *   - tulisMatriks
     * *** HELPER FUNCTIONS ***
     *   - adalahPersegi
     *   - jumElmt
     *   - buatKofaktor
     *   - tambahBaris
     *   - kaliBaris
     *   - bagiBaris
     *   - tukarBaris
     *   - salinMatriks
     *   - makeSgtgAtas
     *   - makeAugmented
     *   - eselonTereduksi
     *   - indikator
     * *** TUGAS ***
     *   - determinanEksKof
     *   - determinanRedBrs
     *   - interpolasi
     */

    /* === ATTRIBUTES === */

    public int jmlBrsMat, // Banyak baris
                jmlKolMat; // Banyak kolom

    // ArrayList dari ArrayList
    ArrayList<ArrayList<Double>> mat = new ArrayList<>();

    /* === CONSTRUCTOR === */

    /**
     * Metode untuk membuat matriks baru
     * Isi dari matriks ini adalah 0
     * dan memiliki ukuran baris * kolom
     * @param baris banyak baris di matriks
     * @param kolom banyak kolom di matriks
     * */
    public Matriks(int baris, int kolom) {
        // Mengisi matriks dengan +0.0
        for (int i = 0; i < baris; i++) {
            ArrayList<Double> kol = new ArrayList<>(kolom);
            for (int j = 0; j < kolom; j++) {
                kol.add(0.0);
            }
            this.mat.add(kol);
        }

        // Mengubah atribut matriks
        this.jmlBrsMat = baris;
        this.jmlKolMat = kolom;
    }

    /* === GETTERS AND SETTERS === */

    /**
     * Mengembalikan elemen pada mat[i][j]
     * Alias getter buat element ke-i,j
     * @param i baris
     * @param j kolom
     * @return elemen ke-i,j
     */
    public double getElmt(int i, int j) {
        return this.mat.get(i).get(j);
    }

    /**
     * Metode untuk mengubah nilai elemen matriks ke-i,j
     * Alias setter buat element ke-i,j
     * @param i indeks baris
     * @param j indeks kolom
     * @param val nilai baru yang ingin disubstitusi ke matriks
     */
    public void setElmt(int i, int j, double val) {
        this.mat.get(i).set(j, val);
    }

    /**
     * Mengambil baris ke-i matriks
     * @param i
     * @return baris ke-i matriks
     */
    public ArrayList<Double> getBaris(int i) {
        return this.mat.get(i);
    }

    /**
     * Mengubah baris ke-i dengan baris barisBaru
     * @param i indeks baris yang ingin diubah
     * @param barisBaru barisBaru yang menggantikan baris ke-i
     */
    public void setBaris(int i, ArrayList<Double> barisBaru) {
        this.mat.set(i, barisBaru);
    }

    /* === INPUTS AND OUTPUTS === */

    /**
     * Metode untuk membuat matriks dengan
     * membaca masukan elemen dari keyboard
     * */
    public void bacaMatriks() {
        // Membuat scanner baru (untuk membacaa masukkan dari user)
        Scanner scan = new Scanner(System.in);

        // Mengganti nilai di matriks sesuai dengan masukkan user
        for (int i = 0; i <this.jmlBrsMat; i++) {
            ArrayList<Double> kol = new ArrayList<>(this.jmlKolMat);
            for (int j = 0; j < this.jmlKolMat; j++) {
                Double el = scan.nextDouble();
                kol.add(el);
            }
            this.setBaris(i, kol);
        }

        // Menutup scanner
        scan.close();
    }

    /**
     * Metode untuk menuliskan isi elemen di matriks pemanggil
     * */
    public void tulisMatriks() {
        for (int i = 0; i < this.jmlBrsMat; i++) {
            for (int j = 0; j < this.jmlKolMat; j++) {
                System.out.printf("%.2f\t", this.getElmt(i, j));
            }
            System.out.println();
        }
    }

    /* === HELPER FUNCTIONS === */

    /**
     * Fungsi untuk mendapatkan ke-persegi-an matriks pemanggil
     * @return ke-persegi-an matriks (true jika  persegi)
     */
    public boolean adalahPersegi() {
        return this.jmlBrsMat == this.jmlKolMat;
    }

    /**
     * Fungsi untuk mendapatkan banyak elemen matriks
     * @return banyak elemen matriks
     */
    public int jumElmt() {
        return this.jmlBrsMat*this.jmlKolMat;
    }

    /**
     * Fungsi membuat kofaktor dari matriks mat
     * @param mat matriks yang ingin dibuat kofaktornya
     * @param idxAcuanBrs indeks baris yang menjadi acuan
     * @param idxAcuanKol indeks kolom yang menjadi acuan
     * @return kofaktor matriks dengan acuan sesuai parameter
     */
    private static Matriks buatKofaktor(Matriks mat, int idxAcuanBrs,
                                        int idxAcuanKol){
        Matriks matRes;
        int m, n;
        matRes = new Matriks(mat.jmlBrsMat-1, mat.jmlKolMat-1);

        // i untuk baris mat; j untuk baris mat
        // m untuk baris matRes; n untuk baris matRes
        m = 0; n = 0;
        for(int i = 0; i < mat.jmlBrsMat; ++i) {
            if (i == idxAcuanBrs) {
                // Nge-skip elmen yang berada di baris yang sama dengan acuanBrs
                continue;
            }
            for (int j = 0; j < mat.jmlKolMat; ++j) {
                // Nge-skip elemen yang berada di kolom yang sama dengan acuanKol
                if (j == idxAcuanKol) {
                    continue;
                }

                matRes.setElmt(m, n++, mat.getElmt(i, j));
                if (n == matRes.jmlKolMat) {
                    n = 0;
                    m++;
                }
            }
        }
        return matRes;
    }

    /**
     * Metode untuk melakukan operasi:
     * mat[idxBrsAsal] = mat[idxBrsAsal] + k*mat[idxBrsPenjumlah]
     * degan mat adalah matriks pemanggil
     * dengan k != 1
     * @param idxBrsAsal indeks baris yang ingin dijumlahkan
     * @param idxBrsPenjumlah indeks baris yang menjadi penjumlah
     * @param k konstanta pengali barisPenjumlah
     */
    private void tambahBaris(int idxBrsAsal, int idxBrsPenjumlah,
                                double k){
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.jmlKolMat; ++i) {
            tempElmt = this.getElmt(idxBrsAsal, i);
            tempElmt += k * this.getElmt(idxBrsPenjumlah, i);
            this.setElmt(idxBrsAsal, i, tempElmt);
        }
    }

    /**
     * Metode untuk melakukan operasi:
     * mat[idxBrsAsal] = mat[idxBrsAsal] + mat[idxBrsPenjumlah]
     * dengan mat adalah matriks pemanggil
     * dengan k == 1
     * @param idxBrsAsal indeks baris yang ingin dijumlahkan
     * @param idxBrsPenjumlah indeks baris yang menjadi penjumlah
     */
    private void tambahBaris(int idxBrsAsal, int idxBrsPenjumlah) {
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.jmlKolMat; ++i) {
            tempElmt = this.getElmt(idxBrsAsal, i);
            tempElmt += this.getElmt(idxBrsPenjumlah, i);
            this.setElmt(idxBrsAsal, i, tempElmt);
        }
    }

    /**
     * Mengkalikan elemen di baris ke-"idxBaris" dengan konsanta k
     * @param idxBaris indeks baris yang ingin dikalikan
     * @param k konstanta yang ingin mengkali baris
     * @param m matriks yang salah satu barisnya ingin dikalikan dengan k
     */
    private void kaliBaris(int idxBaris, double k) {
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.jmlBrsMat; ++i) {
            tempElmt = k*this.getElmt(idxBaris, i);
            this.setElmt(idxBaris, i, tempElmt);
        }
    }

    /**
     * Membagi elemen di baris ke-"idxBaris" dengan konsanta k
     * @param idxBaris indeks baris yang ingin dibagi
     * @param k konstanta yang ingin membagi baris
     * @param m matriks yang salah satu barisnya ingin dikalikan dengan k
     */
    private void bagiBaris(int idxBaris, double k) {
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.jmlBrsMat; ++i) {
            tempElmt = this.getElmt(idxBaris, i)/k;
            this.setElmt(idxBaris, i, tempElmt);
        }
    }

    /**
     * Menukarkan baris ke-barisPertama dengan bariss ke-barisKedua
     * di matriks pemanggil
     * @param barisPertama indeks baris pertama yang ingin ditukar
     * @param barisKedua indeks baris kedua yang ingin ditukar
     */
    private void tukarBaris(int idxBarisPertama, int idxBarisKedua) {
        ArrayList<Double> tempBaris = this.getBaris(idxBarisPertama);
        this.setBaris(idxBarisPertama, this.getBaris(idxBarisKedua));
        this.setBaris(idxBarisKedua, tempBaris);
    }

    /**
     * Menyalin mAsal ke mTujuan
     * @param mAsal
     * @param mTujuan
     */
    public static void salinMatriks(Matriks mAsal, Matriks mTujuan) {
        for (int i = 0; i < mAsal.jmlBrsMat; ++i) {
            for (int j = 0; j < mAsal.jmlKolMat; ++j) {
                mTujuan.setElmt(i, j, mAsal.getElmt(i, j));
            }
        }

        mTujuan.jmlBrsMat = mAsal.jmlBrsMat;
        mTujuan.jmlKolMat = mAsal.jmlKolMat;
    }

    /**
     * Mengubah matriks pemanggil menjadi matriks segitiga atas
     * */
    private int makeSgtgAtas() {
        // TODO: Further testing, edge cases
        int swapCount = 0;

        // i nandain baris yang sedang diproses
        for (int i = 0; i < this.jmlBrsMat; i++) {
            double pivot = this.getElmt(i, i),
                   tempPivot = pivot, // Pivot yang digunakan ketika mencari pivot tidak 0
                   firstElmt, // Elemen pertama tiap baris
                   konstanta; // Konstanta pengali matriks

            // Nyari pivot sampai pivot tidak 0
            int z = i+1; // iterator pencarian pivot tidak 0
            for (; z < this.jmlBrsMat && tempPivot == 0; ++z) {
                tempPivot = this.getElmt(z, i);
            }

            // Jika setelah dicari, pivot masih 0, lewati baris yang sedang
            // diproses
            if (tempPivot == 0) {
                continue;
            // Jika ditemukan pivot tidak 0, tukar dengan baris yang sedang
            // diproses
            } else if (pivot == 0 && tempPivot != 0) {
                pivot = tempPivot;
                swapCount++;
                this.tukarBaris(i, --z);
            }

            // Setelah selesai pencarian pivot tidak 0,
            // kalikan semua elemen di matriks dengan pembuat 1 atau inverse
            // pivot
            //this.kaliBaris(i, 1/pivot); // Ternyata ini butuhnya kalo mau bikin Gauss

            // Setelah dikali pivot, 0-kan semua elemen yang sekolom dan di
            // bawah pivot
            for (int j = i+1; j < this.jmlBrsMat; ++j) {
                firstElmt = this.getElmt(j, i);
                konstanta = -1 * firstElmt/pivot;

                //System.out.println( ((pivot >= 0 && firstElmt >= 0) ||
                                     //(pivot <= 0 && firstElmt <= 0) ? -1 : 1) +
                                     //" " + firstElmt + " " + pivot );
                this.tambahBaris(j, i, konstanta);
                //System.out.println("Konstanta: " + konstanta);
            }

            //this.tulisMatriks();
            //System.out.println("====");
        }

        return swapCount;
    }

    /**
     * Meng-augment matriks pemanggil dengan matriks aug
     * misalkan ada dua matriks: M1 dan M2, di mana M2 adalah matriks yang
     * ingin di-augment ke M1, maka:
     * M1.jadikanAugmented(M2), maka: [M1|M2]
     * @param aug matriks yang ingin di-augment-kan ke matriks pemanggil
     */
    public void makeAugmented(Matriks aug) {
        // TODO: Selesaiin fungsi ini
        int idxAugment;
        if (aug.jmlBrsMat != this.jmlBrsMat) {
            System.out.println("Jumlah baris kedua matriks berbeda");
            System.out.println("Matriks gagal diubah jadi matriks augmented");
            return;
        }

        // Meng-augment baris per baris
        for (int i = 0; i < this.jmlBrsMat; ++i) {
            for (int j = 0;  j < aug.jmlKolMat; ++j) {
                this.getBaris(i).add(aug.getElmt(i, j));
            }
        }
        this.jmlKolMat +=  aug.jmlKolMat;
    }

    /**
     * Metode untuk membuat matriks augmented menjadi matriks eselon baris tereduksi
     */
    private void eselonTereduksi() {
        // TODO: Test
        int lead = 0;
        for (int i = 0; i < this.jmlBrsMat; i++) {
            if (this.jmlKolMat <= lead) {
                return;
            }

            int k = i;
            while (getElmt(k, lead) == 0) {
                k++;
                if (this.jmlBrsMat-1 == k) {
                    k = i; lead++;
                    if (this.jmlKolMat == lead) {
                        return;
                    }
                }
            }
            tukarBaris(k, i);
            if (getElmt(i, lead) != 0) {
                bagiBaris(i, (getElmt(i, lead)));
            }
            for (int j = 0; j < this.jmlBrsMat; j++) {
                if (j != i) {
                    tambahBaris(j, i, ((-1) * (getElmt(k, lead))));
                }
            }
            lead++;
        }
    }

    /**
     * Metode untuk mencari apakah baris dari matriks eselon baris (tereduksi)
     * memiliki solusi unik, solusi tak hingga, atau solusi tidak ada.
     * @return ada 3 kemungkinan output:
     * - 0: Solusi tidak ada
     * - 1: Solusi unik
     * - 2: Solusi tak hingga
     */
    private int indikator() {
        // TODO: Test
        boolean konstantaNol = true;
        boolean koefisienNol = true;
        int j = 0;

        if (getElmt(this.jmlBrsMat-1, this.jmlKolMat-1) == 0) {
            konstantaNol = false;
        }

        while ((j < this.jmlKolMat-1) && koefisienNol) {
            if (getElmt(this.jmlBrsMat-1, j) != 0) {
                koefisienNol = false;
            }
            j++;
        }

        int indikator = (koefisienNol && konstantaNol) ? 2 : (koefisienNol && !konstantaNol) ? 0 : 1;
        return indikator;
    }
    /* === BAGIAN TUGAS === */

    /**
     * Metode menghitung determinan matriks dengan ekspansi kofaktor
     * @param mat matriks yang ingin dihitung kofaktornya
     * @return nilai determinan dari matriks, NaN jika bukan matriks persegi
     */
    public static double determinanEksKof(Matriks mat) {
        double res = 0.0;
        Matriks MTemp = new Matriks(mat.jmlBrsMat-1, mat.jmlKolMat-1);

        if (!mat.adalahPersegi()) {
            System.out.println("Determinan tidak bisa dihitung karena bukan matriks bujur sangkar.");
            System.out.println("Gagal menghitung determinan matriks");
            return Double.NaN;
        }

        // Basis
        if (mat.jmlBrsMat == 2 && mat.jmlKolMat == 2) {
            return ((mat.getElmt(0, 0)*mat.getElmt(1, 1)) -
                    (mat.getElmt(0, 1)*mat.getElmt(1, 0)));
        } else if (mat.jumElmt() == 1) {
            return mat.getElmt(0, 0);
        }

        // Rekurens
        // Baris yang digunakan adalah baris pertama
        // i buat nandain kolom untuk acuan
        for (int i = 0; i < mat.jmlKolMat; ++i) {
            MTemp = buatKofaktor(mat, 0, i);

            res += ((i % 2 == 0 ? 1 : ~0) *
                    mat.getElmt(0, i) *
                    determinanEksKof(MTemp));
        }

        return res;
    }

    /**
     * Metode untuk melakukan perhitungan determinan dengan metode reduksi
     * baris pada matriks mat
     * @param mat matriks yang ingin dihitung determinannya
     * @return determinan matriks mat
     */
    public static double determinanRedBrs(Matriks mat) {
        // TODO: untuk angka sangat besar/sangat kecil, implementasi ini kurang
        // akurat
        double res = 1.0;
        int sc;

        if (!mat.adalahPersegi()) {
            System.out.println("Determinan tidak bisa dihitung karena bukan matriks bujur sangkar.");
            System.out.println("Gagal menghitung determinan matriks");
            return Double.NaN;
        }

        sc = mat.makeSgtgAtas();
        for (int i = 0; i < mat.jmlBrsMat; ++i) {
            res *= mat.getElmt(i, i);
        }

        res *= Math.pow(-1, sc);

        return res;
    }

    /**
     * Mencari interpolasi polinom dari sekumpulan titik yang diberikan
     * dari matriks titik, Matriks titik terdiri dari:
     *           x     y
     * --------------------
     * baris1 | 0.0   1.0 |
     * baris2 | 2.0   3.2 |
     * ---    | ---   --- |
     * baris-n| x-n   y-n |
     * --------------------
     * @param titik matriks berisi kumpulan titik
     * @return jika matriks yang diberikan salah, berupa ArrayList berisi NaN,
     * selain itu akan berisi solusi a_0, a_1, ..., a_n
     */
    public static ArrayList<Double> interpolasi(Matriks titik, double x) {
        ArrayList<Double> solv = new ArrayList<>();
        Matriks interpolate = new Matriks(titik.jmlBrsMat, titik.jmlBrsMat);

        if (titik.jmlKolMat != 2) {
            solv.add(Double.NaN);
            System.out.println("Matriks yang dimasukkan bukan kumpulan titik");
            System.out.println("Gagal menginterpolasi polino dari matriks yang diberikan");
            solv.add(Double.NaN);
            return solv;
        }

        for (int i = 0; i < titik.jmlBrsMat; ++i) {
        }

        return solv;
    }

}
