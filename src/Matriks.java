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
     *   - buatKofaktor
     *   - tambahBaris
     *   - kaliBaris
     *   - bagiBaris
     *   - tukarBaris
     *   - salinMatriks
     *   - makeSgtgAtas
     *   - makeAugmented
     *   - makeEselon
     *   - makeEselonTereduksi
     *   - indikator
     *   - solusiDouble
     *   - matriksToSPL
     * *** TUGAS ***
     *   - gauss
     *   - gaussJordan
     *   - tulisSolusi
     *   - determinanEksKof
     *   - determinanRedBrs
     *   - interpolasi
     *   - balikan
     *   - cramer
     */

    /* === ATTRIBUTES === */

    private int jmlBrsMat, // Banyak baris
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
        System.out.print("Masukkan path ke file (contoh: /path/to/file), boleh relative path: ");
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

                mat.tulisMatriks();

                // Probably close these on the main program instead
                // Probably wanna use a finally block
                fr.close();
                br.close();

                return mat;
            } catch (IOException e) {
                System.out.println("Kesalahan fatal proses maasukan/keluaran");
                System.out.println("Tidak dapat membaca file yang diberikan");
                System.out.println(e);
                System.exit(1);
            }
        } catch(FileNotFoundException e) {
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

        System.out.print("Masukkan path ke file (contoh: /path/to/file), boleh relative path: ");
        path = scan.next();

        try {
            fw = new FileWriter(path);
            String strMat = "", currBaris;

            for (int i = 0; i < mat.jmlBrsMat; ++i) {
                currBaris = "";
                for (int j = 0; j < mat.jmlKolMat; ++j) {
                    String currEl = String.format("%.2f", mat.getElmt(i, j));
                    currBaris += currEl + es;
                }
                strMat += currBaris + ls;
            }

            fw.write(strMat);

            // Probably close these on the main program instead
            // Probably wanna use a finally block
            fw.close();
        } catch (IOException e) {
                System.out.println("Kesalahan fatal proses maasukan/keluaran");
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

        System.out.print("Masukkan path ke file (contoh: /path/to/file), boleh relative path: ");
        path = scan.next();

        try {
            fw = new FileWriter(path);
            fw.write(str);

            // Probably close these on the main program instead
            // Probably wanna use a finally block
            fw.close();
        } catch (IOException e) {
                System.out.println("Kesalahan fatal proses maasukan/keluaran");
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
        for (int i = 0; i < this.jmlKolMat; ++i) {
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
     * Meng-augment matriks pemanggil dengan matriks aug
     * misalkan ada dua matriks: M1 dan M2, di mana M2 adalah matriks yang
     * ingin di-augment ke M1, maka:
     * M1.jadikanAugmented(M2), maka: [M1|M2]
     * @param aug matriks yang ingin di-augment-kan ke matriks pemanggil
     */
    public void makeAugmented(Matriks aug) {
        // TODO: Selesaiin fungsi ini  & Jadiin private lagi
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
     * Metode untuk membuat matriks augmented menjadi matriks eselon baris
     */
    public void makeEselon() {
        // TODO: FIX ME & Jadiin private lagi
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
    public void makeEselonTereduksi() {
        // TODO: Ganti jadi private

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
     * Metode untuk mencari (assign) solusi dari matriks eselon baris tereduksi
     * Prekondisi: indikator = 1 dan matriks sudah berupa matriks eselon tereduksi
     * @return HashMap<String, Double>
     */

    private HashMap<String, Double> solusiDouble() {
        // TODO: Test
        HashMap<String, Double> sol = new HashMap<String, Double>();

        for (int i = 0; i < this.jmlBrsMat; i++) {
            double val = this.getElmt(i, this.jmlKolMat-1);
            sol.put("x" + (i+1), val);
        }
        return sol;
    }

    /**
     * Metode untuk membuat SPL dari matriks eselon tereduksi
     * menghasilkan solusi SPL yang solusinya parametrik.
     * Prekondisi: matriks sudah berupa matriks eselon tereduksi dengan indikator = 2
     * @return Arraylist berisi pair variabel x1 - xn dan solusinya
     */

    private HashMap<String, String> matriksToSPL() {
        // TODO: More test
        //       Fix comment
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
                                                        this.getElmt(i, k) + solParametrik.get("x" + (k+1)));
                            } else if (this.getElmt(i, k) < 0) { // nilai koefisien negatif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) +
                                                        (-1)*this.getElmt(i, k) + solParametrik.get("x" + (k+1)));
                            }
                        } else { // elemen (i, k) merupakan konstanta
                            if (this.getElmt(i, k) > 0 || this.getElmt(i, k) < 0) { // nilai konstanta positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + this.getElmt(i, k));
                            }
                        }
                    } else {
                        // elemen (i, k) merupakan koefisien
                        if (k != this.jmlKolMat - 1) {
                            if (this.getElmt(i, k) > 0) { // nilai koefisien positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " -" +
                                                        this.getElmt(i, k) + solParametrik.get("x" + (k+1)));
                            } else if (this.getElmt(i, k) < 0) { // nilai koefisien negatif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " + " +
                                                        (-1)*this.getElmt(i, k) + solParametrik.get("x" + (k+1)));
                            }
                        } else { // elemen (i, k) merupakan konstanta
                            if (this.getElmt(i, k) > 0) { // nilai konstanta positif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " + " + this.getElmt(i, k));
                            } else if (this.getElmt(i, k) < 0) { // nilai konstanta negatif
                                solParametrik.replace("x" + (j+1), solParametrik.get("x" + (j+1)) + " " + this.getElmt(i, k));
                            }
                        }
                    }
                }
            } else { // j adalah koefisien xn
                solParametrik.replace("x" + (j+1), "" + this.getElmt(i, this.jmlKolMat-1));
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
        indikator = mat.indikator();
        if (indikator == 0) {
            return sol;
        } else if (indikator == 1) {
            for (int i = 0; i < mat.jmlBrsMat; i++) {
                double val = mat.getElmt(i, mat.jmlKolMat-1);
                String valString = Double.toString(val);
                sol.put("x" + (i+1), valString);
            }
            return sol;
        } else { // indikator == 2
            sol = mat.matriksToSPL();
            return sol;
        }
    }

    /**
     * Metode untuk mencetak jawaban ke layar
     * @param solHashMap
     */
    public static void tulisSolusi(HashMap<String, String> solHashMap) {
        if (solHashMap.isEmpty()) {
            System.out.println("Solusi tidak ada");
        }
        else {
            for (int i = solHashMap.size()-1; i >= 0; i--) {
                System.out.print("x" + (i+1) + " = " + solHashMap.get("x"+(i+1)));
                if (i != 0) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

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
        int indikator;
        Matriks matInter = new Matriks(titik.jmlBrsMat, titik.jmlBrsMat+1);

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

        // TODO: Remove this debygging messages
        // 2. Membuat matriks bru menjadi eselon tereduksi
        System.out.println("Before: ");
        matInter.tulisMatriks();
        matInter.makeEselon();
        //matInter.makeEselonTereduksi();
        System.out.println("After: ");
        matInter.tulisMatriks();


        indikator = matInter.indikator();
        if (indikator == 1) {
        } else { // indikator 0 atau 2; tidak bisa dibuat interpolasinya
            System.out.println("Tidak dapat dicari interpolasi polinom dari titik-titik yang diberikan");
            System.out.println("Gagal menginterpolasi polinom dari matriks yang diberikan");
            solv.add(Double.NaN);
            return solv;
        }

        for (int i = 0; i < titik.jmlBrsMat; ++i) {
        }

        return solv;
    }

    /**
     * Membuat inverse/balikan dari matriks yang memanggil
     */
    public void balikan(){
        Scanner sc = new Scanner(System.in);
        int i, j, nBar, nKol;
        double det,temp;
        nBar = this.jmlBrsMat;
        nKol = this.jmlKolMat;

        // Membuat matriks baru untuk temp invers
        Matriks mi = new Matriks(nBar, nKol);
        Matriks.salinMatriks(this, mi);

        // Mengecek apakah matriks merupakan matriks persegi
        if (mi.adalahPersegi()){
            // Men-swap elmt matriks(i,j) dengan elmt (j,i) dengan i sebagai baris dan j sebagai kolom
            det = determinanRedBrs(mi);
            for(i = 0; i<(nBar-1) ; i++){
                for(j = 0; j<(i+1) ; j++){
                    temp = mi.getElmt(i,j);
                    mi.setElmt(i, j, mi.getElmt(j,i));
                    mi.setElmt(j, i, temp);
                }
            }
            for(i = 0; i<nKol; i++){
                mi.bagiBaris(i,det);
            }
        } else {
            System.out.println("Tidak bisa dibuat invers karena buka matriks persegi");
        }
    }

    /**
     * Menyelesaikan SPL dengan metode Cramer untuk matriks yang memanggil
     * @return nilai SPL dari matriks yang memanggil
     */
    public void cramer(){
        int i, j, nBar, nKol;
        double det1,det2,temp;
        nBar = this.jmlBrsMat;
        nKol = (this.jmlKolMat-1);

        // Membuat matrix baru
        Matriks m = new Matriks(nBar, (nKol+1));
        Matriks.salinMatriks(this, m);
        Matriks m1 = new Matriks(nBar, nKol);
        Matriks m2 = new Matriks(nBar, 1);
        Matriks m3 = new Matriks(nBar, nKol);

        // Mengecek apakah matriks merupakan matriks persegi
        if(m1.adalahPersegi()){
            det1 = determinanRedBrs(m1);

            // Memasukan nilai dari elmt m1 dan m2
            for (i = 0; i<nKol; i++){
                for(j = 0; j<nKol ; j++){
                    m1.setElmt(i, j, m.getElmt(i, j));
                }
                m2.setElmt(i, 0, m.getElmt(i, nKol));
            }

            System.out.print("(");

            // Menghitung solusi dari SPL 1 demi 1
            for (i = 0; i<nKol; i++){
                if(i != 0){
                    System.out.print(",");
                }
                Matriks.salinMatriks(m3, m1);
                for(j = 0; j<nBar ; j++){
                    m3.setElmt(j, i, m2.getElmt(i, 0));
                }
                det2 = determinanRedBrs(m3);
                temp = det2/det1;
                System.out.print("x"+(i+1)+" = "+(temp));
            }
            System.out.println("");
        }
        else{
            System.out.println("Tidak ada solusi");
        }
    }
}
