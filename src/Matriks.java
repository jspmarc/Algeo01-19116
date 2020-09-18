/*
 * Matriks
 *
 * Jeane Mikha Erwansyh - 13519116
 * Josep Marcello - 13519164
 * David Owen Adiwiguna - 13519169
 *
 */

/* TODO
 * Implementaasi:
 *  Gauss
 *  Gauss-Jordan
 *  Kramer
 *  Determinan
 *      Ekspansi kofaktor
 *      Reduksi baris
 */

import java.util.ArrayList; // Array dinamis untuk matriks
import java.util.Scanner;

class Matriks {

    // banyak baris dan kolom matriks
    int barisMat, kolomMat;
    // ArrayList dari ArrayList
    ArrayList<ArrayList<Double>> mat = new ArrayList<>();

    /**
     * Metode untuk membuat matriks baru
     * Isi dari matriks ini adalah 0
     * dan memiliki ukuran baris * kolom
     * @param baris banyak baris di matriks
     * @param kolom banyak kolom di matriks
     * */
    Matriks(int baris, int kolom) {
        // Mengisi mariks dengan +0.0
        for (int i = 0; i < baris; i++) {
            ArrayList<Double> kol = new ArrayList<>(kolom);
            for (int j = 0; j < kolom; j++) {
                kol.add(0.0);
            }
            this.mat.add(kol);
        }

        // Mengubah nilai barisMat dan kolomMat
        this.barisMat = baris;
        this.kolomMat = kolom;
    }

    /**
     * Metode untuk membuat matrik dengan
     * membaca masukan elemen dari keyboard
     * */
    void bacaMatriks() {
        // Membuat scanner baru (untuk membacaa masukkan dari user)
        Scanner scan = new Scanner(System.in);

        // Mengganti nilai di matriks sesuai dengan masukkan user
        for (int i = 0; i <this.barisMat; i++) {
            ArrayList<Double> kol = new ArrayList<>(this.kolomMat);
            for (int j = 0; j < this.kolomMat; j++) {
                Double _el = scan.nextDouble();
                kol.add(_el);
            }
            this.mat.set(i, kol);
        }

        // Menutup scanner
        scan.close();
    }

    /**
     * Metode untuk menuliskan isi elemen di matriks
     * */
    void tulisMatriks() {
        for (int i = 0; i <this.barisMat; i++) {
            for (int j = 0; j < this.kolomMat; j++) {
                System.out.print(this.mat.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }

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
        m1.tulisMatriks();
        m1.bacaMatriks();
        m1.tulisMatriks();

        s.close();
    }
}
