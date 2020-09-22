/*
 * Matriks
 *
 * Jeane Mikha Erwansyah - 13519116
 * Josep Marcello - 13519164
 * David Owen Adiwiguna - 13519169
 *
 */

/* TODO
 * Implementaasi:
 *  - [ ] Gauss
 *  - [ ] Gauss-Jordan
 *  - [ ] Kramer
 *  - [ ] Determinan
 *      - [x] Ekspansi kofaktor
 *      - [ ] Reduksi baris
 * Minor stuff:
 *  - [ ] Helper functions kayak jumlah, kali, tukar buat matriks salinan
 *        (tidak mengubah nilai matriks awal)
 */

import java.util.ArrayList; // Array dinamis untuk matriks
import java.util.Scanner;

/*
 * Tipe data mariks dengan elemen awal di 0,0
 */

class Matriks {

    /* === ATTRIBUTES === */

    // banyak baris dan kolom matriks
    private int barisMat, kolomMat,
    // Banyak elemen matriks
                jumElmt;
    // Matriks persegi atau bukan
    public boolean bujurSangkar;
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
    Matriks(int baris, int kolom) {
        // Mengisi matriks dengan +0.0
        for (int i = 0; i < baris; i++) {
            ArrayList<Double> kol = new ArrayList<>(kolom);
            for (int j = 0; j < kolom; j++) {
                kol.add(0.0);
            }
            this.mat.add(kol);
        }

        // Mengubah atribut matriks
        this.barisMat = baris;
        this.kolomMat = kolom;
        this.jumElmt = baris*kolom;

        this.bujurSangkar = baris == kolom;
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
     * Metode untuk membuat matrik dengan
     * membaca masukan elemen dari keyboard
     * */
    public void bacaMatriks() {
        // Membuat scanner baru (untuk membacaa masukkan dari user)
        Scanner scan = new Scanner(System.in);

        // Mengganti nilai di matriks sesuai dengan masukkan user
        for (int i = 0; i <this.barisMat; i++) {
            ArrayList<Double> kol = new ArrayList<>(this.kolomMat);
            for (int j = 0; j < this.kolomMat; j++) {
                Double el = scan.nextDouble();
                kol.add(el);
            }
            this.setBaris(i, kol);
        }

        // Menutup scanner
        scan.close();
    }

    /**
     * Metode untuk menuliskan isi elemen di matriks
     * */
    public void tulisMatriks() {
        for (int i = 0; i < this.barisMat; i++) {
            for (int j = 0; j < this.kolomMat; j++) {
                System.out.print(this.getElmt(i, j) + "\t");
            }
            System.out.println();
        }
    }

    /* === HELPER FUNCTIONS === */

    /**
     * Fungsi membuat kofaktor dari matriks
     * @param mat matriks yang ingin dibuat kofaktornya
     * @param idxAcuanBrs indeks baris yang menjadi acuan
     * @param idxAcuanKol indeks kolom yang menjadi acuan
     * @return kofaktor matriks dengan acuan sesuai parameter
     */
    private static Matriks buatKofaktor(Matriks mat, int idxAcuanBrs,
                                        int idxAcuanKol){
        Matriks matRes;
        int m, n;
        matRes = new Matriks(mat.barisMat-1, mat.kolomMat-1);

        // i untuk baris mat; j untuk baris mat
        // m untuk baris matRes; n untuk baris matRes
        m = 0; n = 0;
        for(int i = 0; i < mat.barisMat; ++i) {
            if (i == idxAcuanBrs) {
                // Nge-skip elmen yang berada di baris yang sama dengan acuanBrs
                continue;
            }
            for (int j = 0; j <  mat.kolomMat; ++j) {
                // Nge-skip elemen yang berada di kolom yang sama dengan acuanKol
                if (j == idxAcuanKol) {
                    continue;
                }

                matRes.setElmt(m, n++, mat.getElmt(i, j));
                if (n == matRes.kolomMat) {
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
     * dengan k != 1
     * @param idxBrsAsal indeks baris yang ingin dijumlahkan
     * @param idxBrsPenjumlah indeks baris yang menjadi penjumlah
     * @param k konstanta pengali barisPenjumlah
     */
    private void jumlahBaris(int idxBrsAsal, int idxBrsPenjumlah,
                             double k ){
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.kolomMat; ++i) {
            tempElmt = this.getElmt(idxBrsAsal, i);
            tempElmt += k * this.getElmt(idxBrsPenjumlah, i);
            this.setElmt(idxBrsAsal, i, tempElmt);
        }
    }

    /**
     * Metode untuk melakukan operasi:
     * mat[idxBrsAsal] = mat[idxBrsAsal] + mat[idxBrsPenjumlah]
     * dengan k == 1
     * @param idxBrsAsal indeks baris yang ingin dijumlahkan
     * @param idxBrsPenjumlah indeks baris yang menjadi penjumlah
     */
    private void jumlahBaris(int idxBrsAsal, int idxBrsPenjumlah) {
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.kolomMat; ++i) {
            tempElmt = this.getElmt(idxBrsAsal, i);
            tempElmt += this.getElmt(idxBrsPenjumlah, i);
            this.setElmt(idxBrsAsal, i, tempElmt);
        }
    }

    /**
     * Mengkalikan baris ke-"idxBaris" dengan konsanta k
     * @param idxBaris indeks baris yang ingin dikalikan
     * @param k konstanta yang ingin dikalikan ke baris
     * @param m matriks yang salah satu barisnya ingin dikalikan dengan k
     */
    private void kaliBaris(int idxBaris, double k) {
        double tempElmt;

        // i untuk kolom
        for (int i = 0; i < this.barisMat; ++i) {
            tempElmt = k*this.getElmt(idxBaris, i);
            this.setElmt(idxBaris, i, tempElmt);
        }
    }

    /**
     * Menukarkan baris ke-barisPertama dengan bariss ke-barisKedua
     * di matriks
     * @param barisPertama indeks baris pertama yang ingin ditukar
     * @param barisKedua indeks baris kedua yang ingin ditukar
     */
    private void tukarBaris(int barisPertama, int barisKedua) {
        ArrayList<Double> tempBaris = this.getBaris(barisPertama);
        this.setBaris(barisPertama, this.getBaris(barisKedua));
        this.setBaris(barisKedua, tempBaris);
    }

    /**
     * Menyalin mAsal ke mTujuan
     * @param mAsal
     * @param mTujuan
     */
    public static void salinMatriks(Matriks mAsal, Matriks mTujuan) {
        for (int i = 0; i < mAsal.barisMat; ++i) {
            for (int j = 0; j < mAsal.kolomMat; ++j) {
                mTujuan.setElmt(i, j, mAsal.getElmt(i, j));
            }
        }
    }

    /* === BAGIAN TUGAS === */

    /**
     * Metode menghitung determinan matriks dengan ekspansi kofaktor
     * @param m1 matriks yang ingin dihitung kofaktornya
     * @return nilai determinan dari matriks, 0.0 jika bukan matriks bujur
     * sangkar
     */
    public static double determinanEksKof(Matriks mat) {
        double res = 0.0;
        int i;
        Matriks MTemp = new Matriks(mat.barisMat-1, mat.kolomMat-1);

        if (!mat.bujurSangkar) {
            System.out.println("Determinan tidak bisa dihitung karena bukan matriks bujur sangkar.");
            return 0.0;
        }

        // Basis
        if (mat.barisMat == 2 && mat.kolomMat == 2) {
            return ((mat.getElmt(0, 0)*mat.getElmt(1, 1)) -
                    (mat.getElmt(0, 1)*mat.getElmt(1, 0)));
        } else if (mat.jumElmt == 1) {
            return mat.getElmt(0, 0);
        }

        // Rekurens
        // Baris yang digunakan adalah baris pertama
        // i buat nandain kolom untuk acuan
        for (i = 0; i < mat.kolomMat; ++i) {
            MTemp = buatKofaktor(mat, 0, i);

            res += ((i % 2 == 0 ? 1 : ~0) *
                    mat.getElmt(0, i) *
                    determinanEksKof(MTemp));
        }

        return res;
    }

    // === PENGUJIAN / TESTING === //

    /**
     * Metode untuk pengujian metode lain matriks ("driver")
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int nBar, nKol;

        System.out.print("Masukkan banyak baris: ");
        nBar = s.nextInt();
        System.out.print("Masukkan banyak kolom: ");
        nKol = s.nextInt();

        Matriks m1 = new Matriks(nBar, nKol);
        Matriks mOriginal = new Matriks(nBar, nKol);


        //m1.tulisMatriks();
        m1.bacaMatriks();
        salinMatriks(m1, mOriginal);
        m1.tulisMatriks();

        System.out.format("%.2f", determinanEksKof(m1));
        System.out.println();

        m1.kaliBaris(0, 200);
        m1.tulisMatriks();
        System.out.println();

        m1.tukarBaris(0, 2);
        m1.tulisMatriks();
        System.out.println();

        m1.jumlahBaris(1, 2);
        m1.tulisMatriks();
        System.out.println();

        m1.jumlahBaris(1, 2, -1);
        m1.tulisMatriks();
        System.out.println();

        mOriginal.tulisMatriks();

        s.close();
    }
}
