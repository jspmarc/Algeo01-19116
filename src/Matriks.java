// Struktur data
import java.util.ArrayList; // Array dinamis untuk matriks
import java.util.HashMap;
import java.util.Scanner;

// Input/Output-related
import java.io.*;

// Mathematics
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
     *   - bacaDariFile
     *   - tulisKeFile (Matriks)
     *   - tulisKeFile (String)
     * *** HELPER FUNCTIONS ***
     *   - adalahPersegi
     *   - jumElmt
     *   - makeMinor
     *   - tambahBaris
     *   - kaliMatriks
     *   - bagiBaris
     *   - tukarBaris
     *   - salinMatriks
     *   - makeIdentitas
     *   - makeSgtgAtas
     *   - makeAugmented
     *   - makePersegi
     *   - makeEselon
     *   - makeEselonTereduksi
     *   - makeAdjoin
     *   - indikator
     *   - matriksToSPL
     * *** TUGAS ***
     *   - gauss
     *   - gaussJordan
     *   - splBalikan
     *   - tulisSolusi
     *   - stringSolusi
     *   - determinanEksKof
     *   - determinanRedBrs
     *   - interpolasi
     *   - balikan
     *   - cramer
     *   - regresi
     */

    /* === ATTRIBUTES === */

    private int jmlBrsMat, // Banyak baris
                jmlKolMat; // Banyak kolom

    // ArrayList dari ArrayList
    // Unttuk menampung elemen matriks
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
     * @param val nilai baru yang ingin disubstitusi/dimasukkan ke matriks
     */
    public void setElmt(int i, int j, double val) {
        this.mat.get(i).set(j, val);
        /*
        try {
            this.mat.get(i).set(j, val);
        } catch (IndexOutOfBoundsException e) {
            // Kalo ukuran ArrayList yang sebenarnya lebih kecil dari yang
            // tertulis
            // Cursed but, eeeehhh...
            try {
                this.mat.get(i).add(val);
            } catch (IndexOutOfBoundsException e2) {
                ArrayList<Double> tempAl = new ArrayList<>(1);
                tempAl.add(val);
                this.mat.add(tempAl);
            }
        }
        */
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
        /*
        try {
            this.mat.set(i, barisBaru);
        } catch (IndexOutOfBoundsException e) {
            // Kalo ukuran ArrayList yang sebenarnya lebih kecil dari yang
            // tertulis
            if (this.jmlBrsMat > i) {
                this.mat.add(barisBaru);
            } else {
                // Menambahkan elemen ke tempat yang tidak seharusnya
                throw e;
            }
        }
        */
    }

    /**
     * @return Banyak kolom matriks
     */
    public int getJmlKol() {
        return this.jmlKolMat;
    }

    /**
     * @return Banyak baris matriks
     */
    public int getJmlBrs() {
        return this.jmlBrsMat;
    }

    /* === INPUTS AND OUTPUTS === */

    /**
     * Metode untuk membuat matriks dengan
     * membaca masukan elemen dari keyboard
     * */
    public void bacaMatriks() {
        // Membuat scanner baru (untuk membacaa masukkan dari user)
        // Tutup scanner di program utama
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

    /**
     * Membaca matriks dari suatu file plaintext-file lalu mengembalikannya
     * Setiap elemen di baris dipisahkan spasi dan
     * Setiap baris dipisahakan \n (newline character)
     * @return Matriks yang dibaca dari plaintext-file
     */
    public static Matriks bacaDariFile() {
        // Tutup scanner di program utama
        Scanner scan = new Scanner(System.in);
        FileReader fr;
        BufferedReader br;
        String path;
        Matriks mat,
                tempMat = new Matriks(1, 1);
        int brs, kol;
        ArrayList<Double> secondSplit;
        ArrayList<ArrayList<Double>> firstSplit = new ArrayList<>();

        // Ngebaca dari file
        System.out.print("Masukkan path ke file input (contoh: /path/to/file), boleh relative path: ");
        path = scan.next();

        // Menghindari error file ga ada
        try {
            fr = new FileReader(path);
            // Menghindari error file ga bisa dibaca
            try {
                br = new BufferedReader(fr);

                // Masukin isi file ke suatu string baris per baris
                StringBuilder fromFile = new StringBuilder();
                String ls = "\n", // pemisah antarbaris
                       currLine = br.readLine(); // line sekarang
                while (currLine != null) {
                    fromFile.append(currLine + ls);
                    currLine = br.readLine(); // Next line
                }

                // Menghapus line separator terakhir
                fromFile.deleteCharAt(fromFile.length()-1);

                // konversi ke String dari StringBuilder
                String strFromFile = fromFile.toString();

                brs = 0;
                kol = 0;
                boolean firstPast = true;

                // Pecah string per baris
                for (String str : strFromFile.split("\n")) {
                    ++brs;
                    secondSplit = new ArrayList<>();

                    // Pecah elemen per spasi
                    for (String num : str.split(" ")) {
                        secondSplit.add(Double.parseDouble(num));
                        if (firstPast) {
                            ++kol;
                        }
                    }

                    firstSplit.add(secondSplit);
                    firstPast = false;
                }

                // Bikin matriks baru
                mat = new Matriks(brs, kol);
                mat.mat = firstSplit;

                // Probably close these on the main program instead
                // Probably wanna use a finally block
                fr.close();
                br.close();

                return mat;
            } catch (IOException e) {
                System.out.println("Kesalahan fatal proses masukan/keluaran");
                System.out.println("Tidak dapat membaca file yang diberikan");
                System.out.println(e);
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan\n" + e);
            System.exit(1);
        }
        // Mengisi matriks tempMat denagn NaN
        tempMat.setElmt(0, 0, Double.NaN);
        return tempMat;
    }

    /**
     * Menuliskan matriks ke file
     * @param mat matriks yang ingin ditulis ke file
     */
    public static void tulisKeFile(Matriks mat) {
        // Tutup scanner di program utama
        Scanner scan = new Scanner(System.in);
        FileWriter fw;
        String path,
               ls = "\n", // line separator
               es = " "; // element separator

        System.out.print("Masukkan path ke file output (contoh: /path/to/file), boleh relative path: ");
        path = scan.next();

        try {
            fw = new FileWriter(path);
            String strMat = "", currBaris;

            for (int i = 0; i < mat.jmlBrsMat; ++i) {
                currBaris = "";
                for (int j = 0; j < mat.jmlKolMat; ++j) {
                    String currEl = String.format("%.4f", mat.getElmt(i, j));
                    currBaris += currEl + es;
                }
                strMat += currBaris + ls;
            }

            fw.write(strMat);

            // Probably close these on the main program instead
            // Probably wanna use a finally block
            fw.close();
        } catch (IOException e) {
                System.out.println("Kesalahan fatal proses masukan/keluaran");
                System.out.println("Tidak dapat menulis ke file yang diberikan");
                System.out.println(e);
                System.exit(1);
        }
    }

    /**
     * Menulis string ke file (secara mentah-mentah, tanpa perubahan dari string)
     * @param str string yang ingin dituliskan ke file
     */
    public static void tulisKeFile(String str) {
        // Tutup scanner di program utama
        Scanner scan = new Scanner(System.in);
        FileWriter fw;
        String path;

        System.out.print("Masukkan path ke file output (contoh: /path/to/file), boleh relative path: ");
        path = scan.next();

        try {
            fw = new FileWriter(path);
            fw.write(str);

            // Probably close these on the main program instead
            // Probably wanna use a finally block
            fw.close();
        } catch (IOException e) {
                System.out.println("Kesalahan fatal proses masukan/keluaran");
                System.out.println("Tidak dapat menulis ke file yang diberikan");
                System.out.println(e);
                System.exit(1);
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
     * Fungsi membuat minor dari matriks mat
     * @param mat matriks yang ingin dibuat minornya
     * @param idxAcuanBrs indeks baris yang menjadi acuan
     * @param idxAcuanKol indeks kolom yang menjadi acuan
     * @return minor matriks dengan acuan sesuai parameter
     */
    private static Matriks makeMinor(Matriks mat, int idxAcuanBrs,
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
     * Mengkalikan matriks yang memanggil dengan suatu matriks lain
     * this * m2
     * Syarat: Ukuran kolom this sama dengan ukuran baris m2
     * @param m2 matriks yang dikalikan dengan matriks pemanggil (this*m2)
     */
    private void kaliMatriks (Matriks m2) {
        if (this.jmlKolMat != m2.jmlBrsMat) {
            System.out.println("Ukuran kedua matriks tidak cocok.");
            System.out.println("Perkalian matriks gagal dilakukan");
            return;
        }

        Matriks matRes = new Matriks(this.jmlBrsMat, m2.jmlKolMat);

        for (int i = 0;  i < this.jmlBrsMat; ++i) {
            for (int j = 0; j < m2.jmlKolMat; ++j) {
                double curEl = matRes.getElmt(i, j);
                for (int k = 0; k < m2.jmlBrsMat; ++k) {
                    curEl += this.getElmt(i, k) * m2.getElmt(k, j);
                }
                matRes.setElmt(i, j, curEl);
            }
        }

        salinMatriks(matRes, this);
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
        for (int i = 0; i < this.jmlKolMat; ++i) {
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
        // padding
        ArrayList<Double> tempAl = new ArrayList<>();
        for (int i = 0; i < mAsal.jmlBrsMat; ++i) {
            // i >= mTujuan.jmlBrsMat artinya mulai baris baru
            if (i >= mTujuan.jmlBrsMat) {
                mTujuan.mat.add(tempAl);
            }
            for (int j = i >= mTujuan.jmlBrsMat ? 0 : mTujuan.jmlKolMat;
                    j < mAsal.jmlKolMat; ++j) {
                mTujuan.getBaris(i).add(0.0);
            }
        }

        // salin matriks
        mTujuan.jmlBrsMat = mAsal.jmlBrsMat;
        mTujuan.jmlKolMat = mAsal.jmlKolMat;

        for (int i = 0; i < mAsal.jmlBrsMat; ++i) {
            for (int j = 0; j < mAsal.jmlKolMat; ++j) {
                mTujuan.setElmt(i, j, mAsal.getElmt(i, j));
            }
        }
    }

    /**
     * membuat matriks identitas berukuran n x n
     * @param n ukuran baris dan kolom matriks
     * @return matriks identitas berukuran n x n
     */
    private static Matriks makeIdentitas(int n) {
        Matriks mat = new Matriks(n, n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    mat.setElmt(i, j, 1);
                } else {
                    mat.setElmt(i, j, 0);
                }
            }
        }

        return mat;
    }

    /**
     * Mengubah matriks pemanggil menjadi matriks segitiga atas
     * */
    private int makeSgtgAtas() {
        int swapCount = 0;

        // i nandain baris yang sedang diproses
        for (int i = 0; i < this.jmlBrsMat; i++) {
            double pivot = this.getElmt(i, i),
                   tempPivot = pivot, // Pivot yang digunakan ketika mencari pivot tidak 0
                   firstElmt, // Elemen pertama tiap baris
                   konstanta; // Konstanta pengali matriks

            // Nyari pivot sampai pivot tidak 0
            int z = i; // iterator pencarian pivot tidak 0
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

                this.tambahBaris(j, i, konstanta);
            }
        }

        return swapCount;
    }

    /**
     * Membatalkan augment matriks yang memanggil
     * @param mB menyimpan bagian augment dari matriks
     * @return bagian augment matriks
     */
    public Matriks makeNotAugmented(Matriks mB) {
        // this sudah augmented dengan mB
        // [this] -> [this], [mB]

        // tempOri akan menampung matriks `this` (matriks sebelum diaugment)
        // untuk sementara waktu
        // kolOri adalah ukuran kolom matriks original
        int kolOri = this.jmlKolMat - mB.jmlKolMat;
        Matriks tempOri = new Matriks(this.jmlBrsMat,  kolOri);

        for (int i = 0; i < this.jmlBrsMat; ++i) {
            int k = 0;
            for (int j = 0; j < this.jmlKolMat; ++j) {
                if (j < tempOri.jmlKolMat) {
                    tempOri.setElmt(i, j, this.getElmt(i, j));
                } else { // Sudah masuk ke bagian augmented
                    mB.setElmt(i, k++, this.getElmt(i, j));
                }
            }
        }

        salinMatriks(tempOri, this);
        return mB;
    }

    /**
     * Meng-augment matriks pemanggil dengan matriks aug
     * misalkan ada dua matriks: M1 dan M2, di mana M2 adalah matriks yang
     * ingin di-augment ke M1, maka:
     * M1.jadikanAugmented(M2), maka: [M1|M2]
     * @param aug matriks yang ingin di-augment-kan ke matriks pemanggil
     */
    public void makeAugmented(Matriks aug) {
        if (aug.jmlBrsMat != this.jmlBrsMat) {
            System.out.println("Jumlah baris kedua matriks berbeda");
            System.out.println("Matriks gagal diubah jadi matriks augmented");
            return;
        }

        // Padding matriks
        for (int i = 0; i < this.jmlBrsMat; ++i) {
            for (int j = this.jmlKolMat; j < aug.jmlKolMat+this.jmlKolMat; ++j) {
                this.getBaris(i).add(0.0);
            }
        }

        // Meng-augment baris per baris
        for (int i = 0; i < this.jmlBrsMat; ++i) {
            for (int j = 0;  j < aug.jmlKolMat; ++j) {
                this.setElmt(i, this.jmlKolMat+j, aug.getElmt(i, j));
            }
        }
        this.jmlKolMat += aug.jmlKolMat;
    }

    /**
     * Mengubah matriks yang memanggil jadi matriks kofaktor
     */
    private void makeKofaktor() {
        Matriks kofak = new Matriks(this.jmlBrsMat, this.jmlKolMat);
        salinMatriks(this, kofak);

        // Bikin kofaktornya dulu
        for (int i = 0; i < kofak.jmlBrsMat; ++i) {
            for (int j = 0; j < kofak.jmlKolMat; ++j) {
                Matriks tempKofak = new Matriks(kofak.jmlBrsMat-1, kofak.jmlKolMat-1);
                tempKofak = makeMinor(this, i, j);
                double val = determinanEksKof(tempKofak);
                val *= Math.pow(-1, i+j);
                kofak.setElmt(i, j, val);
            }
        }

        // Pindahin kofaktor ke matriks awal
        salinMatriks(kofak, this);
    }

    /**
     * Men-transpose matriks yang memanggil
     */
    private void transpose() {
        Matriks tempMat = new Matriks(this.jmlBrsMat, this.jmlKolMat);
        salinMatriks(this, tempMat);

        for (int i = 0; i < this.jmlBrsMat; ++i) {
            for (int j = 0; j < this.jmlKolMat; ++j) {
                this.setElmt(i, j, tempMat.getElmt(j, i));
            }
        }
    }

    /**
     * Mengubah mattriks yang memanggil jadi adjointnya
     */
    private void makeAdjoint() {
        this.makeKofaktor();
        this.transpose();
    }

    /**
     * Metode untuk membuat matriks augmented menjadi n x n+1
     */
    private void makePersegi() {
        ArrayList<Double> newBrs = new ArrayList<>();

        for (int i = 0; i < this.jmlKolMat; ++i) {
            newBrs.add(0.0);
        }

        for (int i = this.jmlBrsMat; i < jmlKolMat; ++i) {
            this.mat.add(newBrs);
        }
        this.jmlBrsMat = this.jmlKolMat;
    }

    /**
     * Metode untuk membuat matriks augmented menjadi matriks eselon baris
     */
    private void makeEselon() {
        int leadIdx = 0,
            k;
        double leadElmt;
        for (int i = 0; i < this.jmlBrsMat; i++) {
            // k ini iterator buat baris
            // leadIdx buat nandain posisi leading one
            // leadElmt adalah elemen yang berada di posisi leading one

            k = i;
            leadElmt = this.getElmt(k, leadIdx);

            // Kalau leadElmt nol
            // Akan dicari sampai tidak nol
            while (leadElmt == 0) {
                k++; // Dilihat baris selanjutnya
                // Kalau k menjadi out of bound
                // Artinya sekolom dari baris ke-i sampai baris ke-(bnykBrs-1)
                // 0 semua
                if (k == this.jmlBrsMat) {
                    k = i; // k dikembalikan ke i
                    leadIdx++; // posisi leading one dimajuin satu

                    // Kalau posisi diagonal sudah out of bounds, artinya
                    // sebaris terakhir memiliki elemen 0 semua
                    // (kecuali bagian augmented)

                    // this.jmlKolMat-1 biar yang bagian augmented diperiksa
                    // ga usah diperiksa
                    if (leadIdx == this.jmlKolMat) {
                        return;
                    }
                }
                // Ambil elemen di baris selanjutnya
                leadElmt = this.getElmt(k, leadIdx);
            }
            // Menukar baris kalau ditemukan baris yang elemen leading-nya
            // tidak 0
            if (k != i) {
                tukarBaris(k, i);
            }

            // Membagi elemen baris agar memiliki one lead
            if (this.getElmt(i, leadIdx) != 0) {
                this.bagiBaris(i, (this.getElmt(i, leadIdx)));
            }

            // Meng-0-kan elemen yang sebaris dengan one-lead
            for (int j = i+1; j < this.jmlBrsMat; j++) {
                // Diambil elemen yang di bawah 1 lead
                double elmtPertama = this.getElmt(j, i),
                       konstanta = -1 * elmtPertama/this.getElmt(i, leadIdx);
                this.tambahBaris(j, i, konstanta);
            }

            leadIdx++;
        }
    }

    /**
     * Metode untuk mengubah matriks eselon baris menjadi
     * matriks eselon baris tereduksi
     */
    private void makeEselonTereduksi() {
        int leadIdx = 0,
            k;

        for (int i = 0; i < this.jmlBrsMat; i++) {
            // k ini iterator buat baris
            // leadIdx buat nandain posisi leading one
            // leadElmt adalah elemen yang berada di posisi leading one

            if (this.jmlKolMat <= leadIdx) {
                return;
            }

            k = i;

            // Kalau leadElmt nol
            // Akan dicari sampai tidak nol
            while (this.getElmt(k, leadIdx) == 0) {
                k++; // Dilihat baris selanjutnya
                // Kalau k menjadi out of bound
                // Artinya sekolom dari baris ke-i sampai baris ke-(bnykBrs-1)
                // 0 semua
                if (k == this.jmlBrsMat) {
                    k = i; // k dikembalikan ke i
                    leadIdx++; // posisi leading one dimajuin satu

                    // Kalau posisi diagonal sudah out of bounds, artinya
                    // sebaris terakhir memiliki elemen 0 semua
                    // (kecuali bagian augmented)

                    // this.jmlKolMat-1 biar yang bagian augmented diperiksa
                    // ga usah diperiksa
                    if (leadIdx == this.jmlKolMat) {
                        return;
                    }
                }
            }
            // Menukar baris kalau ditemukan baris yang elemen leading-nya
            // tidak 0
            if (k != i) {
                tukarBaris(k, i);
            }

            // Membagi elemen baris agar memiliki one lead
            if (this.getElmt(i, leadIdx) != 0) {
                this.bagiBaris(i, (this.getElmt(i, leadIdx)));
            }

            // Meng-0-kan elemen yang sebaris dengan one-lead
            for (int j = 0; j < this.jmlBrsMat; j++) {
                if (j != i) {
                    // Diambil elemen yang di bawah 1 lead
                    double elmtPertama = this.getElmt(j, leadIdx),
                        konstanta = -1 * elmtPertama/this.getElmt(i, leadIdx);
                    this.tambahBaris(j, i, konstanta);
                }
            }

            leadIdx++;
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
        boolean konstantaNol = true;
        boolean koefisienNol = true;
        int j = 0;

        if (getElmt(this.jmlBrsMat-1, this.jmlKolMat-1) != 0.0d) {
            konstantaNol = false;
        }

        while ((j < this.jmlKolMat-1) && koefisienNol) {
            if (getElmt(this.jmlBrsMat-1, j) != 0.0d) {
                koefisienNol = false;
            }
            j++;
        }

        int indikator = (koefisienNol && konstantaNol) ? 2 :
                        (koefisienNol && !konstantaNol) ? 0 : 1;
        return indikator;
    }

    /**
     * Metode untuk membuat SPL dari matriks eselon tereduksi
     * menghasilkan solusi SPL yang solusinya parametrik.
     * Prekondisi: matriks sudah berupa matriks eselon tereduksi dengan indikator = 2
     * @return Arraylist berisi pair variabel x1 - xn dan solusinya
     */
    private HashMap<String, String> matriksToSPL() {
        HashMap<String, String> solParametrik = new HashMap<>();
        char varBebas = 's'; // variabel bebas pertama
        int i, j;

        // Assigning xn(s) which is a free variable(s) with a
        // parametric solution/alphabet
        // Asumsi hanya terdapat maksimal 26 variabel bebas
        for (j = this.jmlKolMat-2; j >= 0; j--) {
            boolean semuaNol = true;
            for (i = this.jmlBrsMat-1; i >= 0; i--) {
                if (this.getElmt(i, j) != 0) {
                    semuaNol = false;
                    break;
                }
            }
            if (semuaNol || this.getElmt(i, j) != 1) {
                solParametrik.put("x" + (j+1), varBebas + "");
                if (varBebas == 'z') {
                    varBebas -= 25;
                } else {
                    varBebas++;
                }
            }
        }

        // Mencari jumlah baris yang tidak nol
        int jmlBarisTidakNol = 0;
        i = 0;
        j = 0;
        boolean nol = true;

        while (i < this.jmlBrsMat) {
            nol = true;
            while (nol && j < this.jmlKolMat) {
                if (this.getElmt(i, j) != 0) {
                    jmlBarisTidakNol++;
                    nol = false;
                }
                j++;
            }
            i++;
        }

        // Assigning the rest of xns with a value for their solution
        for (i = 0; i < jmlBarisTidakNol; i++) {
            j = 0;

            // Mencari elemen matriks = 1 (1 yang pertama merupakan leading one)
            while (this.getElmt(i, j) != 1) {
                j++;
            }

            // Inisiasi key HashMap solParametrik
            // Untuk elemen matriks yang sama dengan 1
            solParametrik.put("x" + (j+1), "");

            // j bukan koefisien xn
            if (j != this.jmlKolMat-2) {
                // Traversing baris yang sama untuk mencari solusi
                for (int k = j+1; k < this.jmlKolMat; k++) {
                    // Percabangan untuk mengatasi whitespace bagian awal solusi
                    if (solParametrik.get("x" + (j+1)) != null && solParametrik.get("x" + (j+1)).equals("")) {
                        // elemen (i, k) merupakan koefisien
                        if (k != this.jmlKolMat - 1) {
                            if (this.getElmt(i, k) > 0) { // nilai koefisien positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + "-" +
                                                        String.format("%.4f", this.getElmt(i, k)) +
                                                        solParametrik.get("x" + (k+1)));
                            } else if (this.getElmt(i, k) < 0) { // nilai koefisien negatif
                                solParametrik.replace("x" + (j+1),
                                                        solParametrik.get("x" + (j+1)) +
                                                        String.format("%.4f", (-1)*this.getElmt(i, k)) +
                                                        solParametrik.get("x" + (k+1)));
                            }
                        } else { // elemen (i, k) merupakan konstanta
                            if (this.getElmt(i, k) > 0 || this.getElmt(i, k) < 0) { // nilai konstanta positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) +
                                String.format("%.4f", this.getElmt(i, k)));
                            }
                        }
                    } else {
                        // elemen (i, k) merupakan koefisien
                        if (k != this.jmlKolMat - 1) {
                            if (this.getElmt(i, k) > 0) { // nilai koefisien positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " -" +
                                                        String.format("%.4f", this.getElmt(i, k)) +
                                                        solParametrik.get("x" + (k+1)));
                            } else if (this.getElmt(i, k) < 0) { // nilai koefisien negatif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " + " +
                                                        String.format("%.4f", (-1)*this.getElmt(i, k)) +
                                                        solParametrik.get("x" + (k+1)));
                            }
                        } else { // elemen (i, k) merupakan konstanta
                            if (this.getElmt(i, k) > 0) { // nilai konstanta positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " + " +
                                String.format("%.4f", this.getElmt(i, k)));
                            } else if (this.getElmt(i, k) < 0) { // nilai konstanta negatif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " " +
                                String.format("%.4f", this.getElmt(i, k)));
                            }
                        }
                    }
                }
            } else { // j adalah koefisien xn
                solParametrik.replace("x" + (j+1), "" + String.format("%.4f", this.getElmt(i, this.jmlKolMat-1)));
            }
        }
        return solParametrik;
    }

    /* === BAGIAN TUGAS === */

    /**
     * Metode Gauss
     * @param mat
     * @return 3 kemungkinan output (dalam tipe string):
     * - nilai x1-xn tertulis: matriks augmented memiliki solusi unik, indikator = 2
     * - "Solusi tidak ada": matriks augmented tidak memiliki solusi, indikator = 0
     * - nilai x1-xn parametrik: matriks augmented memiliki solusi banyak, indikator = 1
     */
    public static HashMap<String, String> gauss(Matriks mat) {
        mat.makeEselon();
        if (mat.jmlBrsMat < mat.jmlKolMat -1) {
            mat.makePersegi();
        }

        // Akan menjadi SPL baru
        // Solve SPL baru dengan gaussJordan
        return gaussJordan(mat);
    }

    /**
     * Metode Gauss Jordan
     * @param mat matriks yang ingin diselesaikan dengan metode Gauss-Jordan
     * @return 3 kemungkinan output (dalam tipe string):
     * - nilai x1-xn tertulis: matriks augmented memiliki solusi unik, indikator = 2
     * - "Solusi tidak ada": matriks augmented tidak memiliki solusi, indikator = 0
     * - nilai x1-xn parametrik: matriks augmented memiliki solusi banyak, indikator = 1
     */
    public static HashMap<String, String> gaussJordan(Matriks mat) {
        int indikator;
        HashMap<String, String> sol = new HashMap<>();

        mat.makeEselonTereduksi();
        if (mat.jmlBrsMat < mat.jmlKolMat -1) {
            mat.makePersegi();
        }
        indikator = mat.indikator();
        if (indikator == 0) {
            return sol;
        } else if (indikator == 1) {
            for (int i = 0; i < mat.jmlBrsMat; i++) {
                double val = mat.getElmt(i, mat.jmlKolMat-1);
                String valString = String.format("%.4f", val);
                sol.put("x" + (i+1), valString);
            }
            return sol;
        } else { // indikator == 2
            sol = mat.matriksToSPL();
            return sol;
        }
    }

    /**
     * Menghitung SPL dengan metode matriks balikan
     * @param matGab matriks augmented yang ingin dihitung SPL-nya
     * @return solusi SPL matriks augmented
     */
    public static HashMap<String, String> splBalikan(Matriks matGab) {
        // matA*matX = matB
        // <=> matX = inv(matA)*matB
        HashMap<String, String> sol = new HashMap<>();
        Matriks matA = makeIdentitas(matGab.jmlBrsMat),
                matB = new Matriks(matGab.jmlBrsMat, 1);

        matB = matGab.makeNotAugmented(matB);
        salinMatriks(matGab, matA);

        if (!matA.adalahPersegi()) {
            System.out.println("Bentuk matriks inkonsisten.");
            System.out.println("Gagal menghiung solusi SPL.");
            System.out.println("Silakan gunakan metode Gauss-Jordan.");
        } else {
            double det = determinanEksKof(matGab);
            if (det != 0) {
                matA = balikanOBE(matA);
                matA.kaliMatriks(matB);

                for (int i = 0; i < matA.jmlBrsMat; ++i) {
                    sol.put("x"+(i+1), String.format("%.4f", matA.getElmt(i, 0)));
                }
            }
        }

        return sol;
    }

    /**
     * Metode untuk mencetak jawaban ke layar
     * @param solHashMap kumpulan x1, x2, ..., xn yang akan diprint ke layar
     * @param symb simbol/variabeel yang digunakan untuk menuliskan solusi
     */
    public static void tulisSolusi(HashMap<String, String> solHashMap, String symb) {
        if (solHashMap.isEmpty()) {
            System.out.println("Solusi tidak ada");
        } else {
            for (int i = solHashMap.size()-1; i >= 0; i--) {
                System.out.print(symb + (i+1) + " = " + solHashMap.get("x"+(i+1)));
                if (i != 0) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Metode untuk mencetak jawaban ke layar
     * @param solHashMap kumpulan x1, x2, ..., xn yang akan diprint ke layar
     */
    public static String stringSolusi(HashMap<String, String> solHashMap) {
        String str = new String();
        if (solHashMap.isEmpty()) {
            str = "Solusi tidak ada";
        } else {
            for (int i = solHashMap.size()-1; i >= 0; i--) {
                str = "x" + (i+1) + " = " + solHashMap.get("x"+(i+1));
                if (i != 0) {
                    str = str.concat(", ");
                }
            }
        }
        return str;
    }

    /**
     * Metode menghitung determinan matriks dengan ekspansi kofaktor
     * @param mat matriks yang ingin dihitung determinannya
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
            MTemp = makeMinor(mat, 0, i);

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
        double res = 1.0;
        int sc;
        Matriks tempMat = new Matriks(mat.jmlBrsMat, mat.jmlKolMat);

        salinMatriks(mat, tempMat);

        if (!mat.adalahPersegi()) {
            System.out.println("Determinan tidak bisa dihitung karena bukan matriks bujur sangkar.");
            System.out.println("Gagal menghitung determinan matriks");
            return Double.NaN;
        }

        sc = tempMat.makeSgtgAtas();
        for (int i = 0; i < tempMat.jmlBrsMat; ++i) {
            res *= tempMat.getElmt(i, i);
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
    public static ArrayList<Double> interpolasi(Matriks titik) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Double> solv = new ArrayList<>();
        int indikator;
        Matriks matInter = new Matriks(titik.jmlBrsMat, titik.jmlBrsMat+1);
        String px, ps;
        double interpolatedX = 0;

        if (titik.jmlKolMat != 2) {
            // Jika yang didapatkan bukan matriks yang terdiri dari titik-titik
            System.out.println("Matriks yang dimasukkan bukan kumpulan titik");
            System.out.println("Gagal menginterpolasi polinom dari matriks yang diberikan");
            solv.add(Double.NaN);
            return solv;
        }

        /*
        * Proses:
        * 1.a. Buat matriks dengan banyak baris sebanyak matriks titik
        *   b. lalu banyak kolom sebanyak baris di matriks titik ditambah 1
        *   c. dengan kolom pertama tiap baris bernilai 1
        *   d. dan kolom terakhir adalah elemen y tiap titik
        * 2. Solve as if it's an SPL
        * 3. Akan didapat nilai a_0, a_1, ..., a_n
        *   a. Simpan nilai a_0 sampai a_n di dalam suatu ArrayList
        * 4. Print jadi p(x) = a_0 + a_1 * x + a_2 * x^2 + ... + a_n * x^n
        * 5. Plug in x to p(x) and then print the result
        */

        // 1. "Membuat" dan mengisi matriks baru
        for (int i = 0; i < titik.jmlBrsMat; ++i) {
            double currX = titik.getElmt(i, 0),
                   currY = titik.getElmt(i, 1);
            for (int j = 0; j < titik.jmlBrsMat; ++j) {
                matInter.setElmt(i, j, Math.pow(currX, j));
            }
            matInter.setElmt(i, matInter.jmlKolMat-1, currY);
        }

        matInter.makeEselon();
        matInter.makeEselonTereduksi();

        indikator = matInter.indikator();
        if (indikator == 1) {
            // Ambil kolom terakhir
            for (int i = 0; i < matInter.jmlBrsMat; ++i) {
                solv.add(matInter.getElmt(i, matInter.jmlKolMat-1));
            }

            px = "\nP(x) = ";
            //ps = "P(" + x + ") = ";
            for (int i = 0; i < matInter.jmlBrsMat; ++i) {
                px += String.format("%.4f", solv.get(i) >= 0 || i == 0
                                            ? solv.get(i)
                                            : -1*solv.get(i));
                //ps += String.format("%.4f", solv.get(i) >= 0 || i == 0
                                            //? solv.get(i)
                                            //: -1*solv.get(i));
                if (i != 0) {
                    px += "(x" + (i == 1 ? "" : "**" + i) + ")";
                    //ps += "(" + x +  (i == 1 ? "" : "**" + i) + ")";
                }
                if (i < matInter.jmlBrsMat-1) {
                    px += (solv.get(i+1) >= 0) ? " + " : " - ";
                    //ps += (solv.get(i+1) >= 0) ? " + " : " - ";
                }

                //interpolatedX += solv.get(i) * Math.pow(x, i);
            }

            //ps += String.format(" = %.4f", interpolatedX);

            System.out.println(px);
            //System.out.println(ps);

            ps = "";
            System.out.print("\nApakah Anda ingin membuat perkiraan beberapa titik? (y/n): ");
            if (scan.next().toLowerCase().equals("y")) {
                String currPs = "";
                System.out.print("Banyak x/titik yang ingin diperkirakan: ");
                int limit = scan.nextInt();

                for (int i = 0; i < limit; ++i) {
                    System.out.print("x" + (i+1) + ": ");
                    double x = scan.nextDouble();
                    currPs = "P(" + x + ") = ";

                    for (int j = 0; j < matInter.jmlBrsMat; ++j) {
                        interpolatedX += solv.get(j) * Math.pow(x, j);
                    }

                    currPs += String.format("%.4f", interpolatedX);
                    System.out.println(currPs);

                    ps += currPs + "\n";
                }
            }

            System.out.print("\nApakah Anda ingin menyimpan solusi ke file? (y/n): ");
            String yn = scan.next();

            if (yn.toLowerCase().equals("y")) {
                tulisKeFile(px + "\n" + ps);
            }
        } else { // indikator 0 atau 2; tidak bisa dibuat interpolasinya
            System.out.println("Tidak dapat dicari interpolasi polinom dari titik-titik yang diberikan");
            System.out.println("Gagal menginterpolasi polinom dari matriks yang diberikan");
            solv.add(Double.NaN);
            return solv;
        }

        return solv;
    }

    /**
     * Membuat inverse/balikan dari matriks yang memanggil
     * dengan metode adjoin
     */
    public static Matriks balikanAdjoint(Matriks mat){
        int i, nKol;
        double det;
        nKol = mat.jmlKolMat;

        /*
        // Membuat matriks baru untuk temp invers
        Matriks mi = new Matriks(nBar, nKol);
        salinMatriks(mat, mi);
        */

        det = determinanEksKof(mat);

        // Mengecek apakah matriks merupakan matriks persegi
        if (mat.adalahPersegi() && det != 0){
            /*
            // Men-swap elmt matriks(i,j) dengan elmt (j,i) dengan i sebagai baris dan j sebagai kolom
            det = determinanRedBrs(mi);
            for(i = 0; i<(nBar-1) ; i++){
                for(j = 0; j<(i+1) ; j++){
                    temp = mi.getElmt(i,j);
                    mi.setElmt(i, j, mi.getElmt(j,i));
                    mi.setElmt(j, i, temp);
                }
            }
            */

            mat.makeAdjoint();

            for(i = 0; i<nKol; i++){
                mat.bagiBaris(i, det);
            }
            //salinMatriks(mi, mat);
        } else {
            System.out.println("Tidak bisa dibuat invers karena bukan matriks persegi");
            System.out.println("atau determinannya 0");
        }
        return mat;
    }

    /**
     * Membuat balikan matriks mat dengan metode OBE
     * @param mat matriks yang ingin dibuat balikannya
     * @return balikan matriks mat
     */
    public static Matriks balikanOBE(Matriks mat) {
        double det = determinanRedBrs(mat);
        if (mat.adalahPersegi() && det != 0) {
            Matriks ident = makeIdentitas(mat.jmlBrsMat),
                    m1 = new Matriks(mat.jmlBrsMat, mat.jmlKolMat);

            salinMatriks(mat, m1);
            m1.makeAugmented(ident);
            m1.makeEselon();
            m1.makeEselonTereduksi();

            // Pecah matriksnya
            //for (int i = 0; i < mat.jmlBrsMat; ++i) {
                //for (int j = mat.jmlKolMat; j < m1.jmlKolMat; ++j) {
                    //mat.setElmt(i, j-mat.jmlKolMat, m1.getElmt(i, j));
                //}
            //}

            m1.makeNotAugmented(mat);

        } else {
            System.out.println("Matriks yang diberikan bukan matriks persegi");
            System.out.println("atau determinannya 0.");
            System.out.println("Balikan matriks tidak dapat dibuat.");
        }
        return mat;
    }

    /**
     * Menyelesaikan SPL dengan metode Cramer untuk matriks yang memanggil
     * Mariks yang memanggil adalah matriks augmented
     * @param mat Matriks yang ingin dihiung SPL-nya
     * @return nilai SPL dari matriks yang memanggil
     */
    public static HashMap<String, String> cramer(Matriks mat){
        int i, j, nBar, nKol;
        double det1,det2,temp;
        nBar = mat.jmlBrsMat;
        nKol = (mat.jmlKolMat-1);
        HashMap<String, String> solHash = new HashMap<>();

        // Membuat matrix baru
        Matriks m = new Matriks(nBar, (nKol+1));
        Matriks.salinMatriks(mat, m);
        Matriks m1 = new Matriks(nBar, nKol);
        Matriks m2 = new Matriks(nBar, 1);
        Matriks m3 = new Matriks(nBar, nKol);

        // Mengecek apakah matriks merupakan matriks persegi
        if(m1.adalahPersegi()){
            // Memasukan nilai dari elmt m1 dan m2
            for (i = 0; i < nKol; i++){
                for(j = 0; j < nKol ; j++){
                    m1.setElmt(i, j, m.getElmt(i, j));
                }
                m2.setElmt(i, 0, m.getElmt(i, nKol));
            }

            det1 = determinanRedBrs(m1);

            // In case deteminannya 0
            if (det1 == 0) {
                System.out.println("Determinan matriks adalah 0.");
                System.out.print("Artinya matriks masukan memliki banyak solusi");
                System.out.println(" atau tidak memiliki solusi.");
                System.out.print("Silakan menggunakan metode Gauss-Jordan");
                System.out.println(" untuk menarik kesimpulan.");

                return solHash;
            }

            // Menghitung solusi dari SPL 1 demi 1
            for (i = 0; i < nKol; i++){
                Matriks.salinMatriks(m1, m3);

                // Taro elemen m2 di m3
                for(j = 0; j<nBar ; j++){
                    m3.setElmt(j, i, m2.getElmt(j, 0));
                }

                det2 = determinanRedBrs(m3);
                temp = det2/det1;

                solHash.put("x"+(i+1), (String.format("%.2f", temp)));
            }
        } else{
            System.out.println("Bentuk matriks inkonsisten.");
            System.out.println("Gagal menghiung solusi SPL.");
            System.out.println("Silakan gunakan metode Gauss-Jordan.");
        }
        return solHash;
    }

    /**
     * Metode untuk mencari regresi
     * @param mat
     * @return Matriks
     */
    public static Matriks regresi(Matriks mat){
        Scanner s = new Scanner(System.in);
        int i, j, k, nBar, nKol;
        //double temp;
        double sum = 0;
        nBar = mat.jmlBrsMat;
        nKol = mat.jmlKolMat;
        HashMap<String, String> solHm = new HashMap<>();
        ArrayList<Double> solv = new ArrayList<>();
        String yx;

        // Membuat Matriks baru (m1)
        Matriks m1 = new Matriks(nKol, (nKol+1));

        // Mengisi m1 untuk (0,0)
        m1.setElmt(0, 0, nBar);

        // Mengisi m1 untuk baris pertama
        for (i = 0; i < nKol; i++){
            for (j = 0; j < nBar; j++){
                sum = sum + mat.getElmt(j, i);
            }
            m1.setElmt(0, (i+1), sum);
            sum = 0;
        }

        // Mengisi m1 untuk kolom pertama
        for (i = 1; i< nKol; i++){
            m1.setElmt(i, 0, m1.getElmt(0,i));
        }

        // Mengisi m1 untuk sisanya
        for (i = 1; i < nKol; i++){           // Baris Output
            for (j = 0; j < nKol; j++){       // Kolom Input & Output
                for (k = 0; k < nBar; k++){   // Baris Input
                    sum = sum + (mat.getElmt(k,(i-1)) * mat.getElmt(k,j));
                }
                m1.setElmt(i, (j+1), sum);
                sum = 0;
            }
        }

        // Lakukan Gauss-Jordan
        solHm = gaussJordan(m1);
        tulisSolusi(solHm, "b");

        for (i = 0; i < m1.jmlBrsMat; ++i) {
            solv.add(i, m1.getElmt(i, m1.jmlKolMat-1));
        }

        // Membuat persamaan y
        yx = "y = ";
        for (i = 0; i < m1.jmlBrsMat; ++i) {
            yx += String.format("%.4f", solv.get(i) >= 0 || i == 0
                                        ? solv.get(i)
                                        : -1*solv.get(i));
            if (i != 0) {
                yx += "x" + i;
            }
            if (i < m1.jmlBrsMat-1) {
                yx += (solv.get(i+1) >= 0) ? " + " : " - ";
            }
        }
        System.out.println(yx);

        // Memprediksi
        String xPred = "";
        sum = solv.get(0);
        System.out.print("Ingin memprediksi? (y/n): ");
        if (s.next().toLowerCase().equals("y")) {
            for (i = 1; i < m1.jmlBrsMat; ++i) {
                System.out.print("Masukkan x" + i + ": ");
                double input = s.nextDouble();
                sum += input*solv.get(i);
                xPred += "x" + i + ": " + input + "\n";
            }
        }

        String pred = "Hasil prediksi: y = " + sum;
        System.out.print(pred);

        yx += "\n\nPrediksi:\n" + xPred + pred;

        System.out.printf("\nApakah Anda ingin menyimpan solusi ke file? (y/n): ");
        if (s.next().toLowerCase().equals("y")) {
            tulisKeFile(yx);
        }

        return m1;
    }
}
