/**
 * TODO:
 * runInterpolasi, runRegresi belum
 * Buat prosedur untuk baca input spl
 * Matriks.java rounding solusi parametrik
 * Pilihan untuk menyimpan, tidak langsung simpan
 */


/*
 * Program utama/gabungan untuk Tubes 01 IF2123 2020/2021
 *
 * Jeane Mikha Erwansyah - 13519116
 * Josep Marcello - 13519164
 * David Owen Adiwiguna - 13519169
 *
 */
import java.util.*;

class Main {
    /**
     * DAFTAR PROSEDUR
     * - intro
     * - outro
     * - menu
     * - subMenu1
     * - subMenu2
     * - bacaMetodeInput
     * - runSPL
     * - runDet
     * - runBalikan
     * - runInterpolasi
     * - runRegresi
     * - run
     */

    /**
     * Metode untuk mencetak pembukaan
     */
    private static void intro() {
        System.out.println(
            "Selamat Datang di Program Tugas Besar Pertama\n"
            + "Mata Kuliah IF2123 Aljabar Linier dan Geometri\n"
            + "\n");
    }

    /**
     * Metode untuk mencetak penutup
     */
    private static void outro() {
        System.out.println(
            "\nProgram dibuat oleh:\n"
            + "*) Jeane Mikha Erwansyah - 13519116\n"
            + "*) Josep Marcello        - 13519164\n"
            + "*) David Owen Adiwiguna  - 13519169\n"
            + "\nEnd of Program.");
    }

    private static void wOOOw() {
        System.out.println(" /$$      /$$             /$$               /$$          ");
        System.out.println("| $$$    /$$$            | $$              |__/          ");
        System.out.println("| $$$$  /$$$$  /$$$$$$  /$$$$$$    /$$$$$$  /$$ /$$   /$$");
        System.out.println("| $$ $$/$$ $$ |____  $$|_  $$_/   /$$__  $$| $$|  $$ /$$/");
        System.out.println("| $$  $$$| $$  /$$$$$$$  | $$    | $$  \\__/| $$ \\  $$$$/");
        System.out.println("| $$\\  $ | $$ /$$__  $$  | $$ /$$| $$      | $$  >$$  $$");
        System.out.println("| $$ \\/  | $$|  $$$$$$$  |  $$$$/| $$      | $$ /$$/\\  $$");
        System.out.println("|__/     |__/ \\_______/   \\___/  |__/      |__/|__/  \\__/");
    }

    /**
     * Metode untuk mencetak menu
     */
    private static void menu() {
        System.out.println("\nMENU:\n");
        System.out.println("1. Sistem Persamaan Linier\n"
                           + "2. Determinan\n"
                           + "3. Matriks Balikan\n"
                           + "4. Interpolasi Polinom\n"
                           + "5. Regresi Linier Berganda\n"
                           + "6. Keluar\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 1
     */
    private static void subMenu1() {
        //System.out.println("\nSUB MENU 1:\n");
        System.out.println("\nMETODE UNTUK MENGHITUNG SPL:\n");
        System.out.println("1. Metode Eliminasi Gauss\n"
                            + "2. Metode Eliminasi Gauss-Jordan\n"
                            + "3. Matriks Balikan\n"
                            + "4. Kaidah Cramer\n"
                            + "5. Kembali\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 2
     */
    private static void subMenu2() {
        //System.out.println("\nSUB MENU 2:\n");
        System.out.println("\nMETODE UNTUK MENGHITUNG DETERMINAN:\n");
        System.out.println("1. Metode Reduksi Baris\n"
                            + "2. Metode Ekspansi Kofaktor\n"
                            + "3. Kembali\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 3
     */
    private static void subMenu3() {
        //System.out.println("\nSUB MENU 2:\n");
        System.out.println("\nMETODE UNTUK MENGHITUNG BALIKAN MATRIKS:\n");
        System.out.println("1. Metode adjoin matriks\n"
                            + "2. Metode OBE\n "
                            + "3. Kembali\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk membaca metode input
     * @return integer pilihan valid
     */
    private static int bacaMetodeInput() {
        System.out.println("\nMENU METODE INPUT:\n");
        System.out.println("1. Input dari keyboard\n"
                            + "2. Baca dari file eksternal\n");
        System.out.print("\nMasukkan menu pilihan Anda: ");

        Scanner s = new Scanner(System.in);
        System.out.println();
        int pilihan = s.nextInt();

        while (pilihan != 1 && pilihan != 2) {
            System.out.println("Pilihan tidak valid! Masukan kembali pilihan Anda: ");
            pilihan = s.nextInt();
        }
        //s.close();
        return pilihan;
    }

    /**
     * menyimpan hasil perhitungan ke file
     * @param sol hasil perhitungan yang ingin disimpan
     */
    private static void simpanKeFile(HashMap<String, String> sol) {
        Scanner s = new Scanner(System.in);
        System.out.println("Apakah Anda ingin menyimpan solusi ke file? (y/n): ");
        if (s.next().toLowerCase().equals("y")) {
            Matriks.tulisKeFile(Matriks.stringSolusi(sol));
        }
    }

    /**
     * menyimpan hasil perhitungan ke file
     * @param sol hasil perhitungan yang ingin disimpan
     */
    private static void simpanKeFile(Matriks mat) {
        Scanner s = new Scanner(System.in);
        System.out.println("Apakah Anda ingin menyimpan solusi ke file? (y/n): ");
        if (s.next().toLowerCase().equals("y")) {
            Matriks.tulisKeFile(mat);
        }
    }

    /**
     * menyimpan hasil perhitungan ke file
     * @param sol hasil perhitungan yang ingin disimpan
     */
    private static void simpanKeFile(String sol) {
        Scanner s = new Scanner(System.in);
        System.out.println("Apakah Anda ingin menyimpan solusi ke file? (y/n): ");
        if (s.next().toLowerCase().equals("y")) {
            Matriks.tulisKeFile(sol);
        }
    }

    /**
     * Metode untuk menjalankan subprogram SPL
     */
    private static void runSPL() {
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        System.out.println();

        switch (pilihan0) {
            // Pilihan 1 - Metode Eliminasi Gauss
            case 1: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan jumlah baris: ");
                        nBrsA = s.nextInt();
                        System.out.println("\nMasukkan jumlah kolom: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        mA.bacaMatriks();
                        System.out.println("\nMasukan b[i] secara berurut");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gauss(mA);
                        Matriks.tulisSolusi(sol);
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gauss(m);
                        Matriks.tulisSolusi(sol);
                        simpanKeFile(sol);
                        break;
                    }
                }
                break;
            }
            // Pilihan 2 - Metode Eliminiasi Gauss-Jordan
            case 2: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan jumlah baris: ");
                        nBrsA = s.nextInt();
                        System.out.println("\nMasukkan jumlah kolom: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        mA.bacaMatriks();
                        System.out.println("\nMasukan b[i] secara berurut");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gaussJordan(mA);
                        Matriks.tulisSolusi(sol);
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gaussJordan(m);
                        Matriks.tulisSolusi(sol);
                        simpanKeFile(sol);
                        break;
                    }
                }
                break;
            }
            // Pilihan 3 - Matriks Balikan
            case 3: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan jumlah baris: ");
                        nBrsA = s.nextInt();
                        System.out.println("\nMasukkan jumlah kolom: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        mA.bacaMatriks();
                        System.out.println("\nMasukan b[i] secara berurut");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        // insert spl metode matriks balikan

                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        // insert spl metode matriks balikan
                        break;
                    }
                }
                break;
            }
            // Pilihan 4 - Kaidah Cramer
            case 4: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan jumlah baris: ");
                        nBrsA = s.nextInt();
                        System.out.println("\nMasukkan jumlah kolom: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        mA.bacaMatriks();
                        System.out.println("\nMasukan b[i] secara berurut");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        // insert spl metode matriks balikan
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        // insert spl metode matriks balikan
                        break;
                    }
                }
                break;
            }
            // Pilihan 5 - Kembali
            case 5: {
                break;
            }
            // Pilihan tidak valid
            default: {
                System.out.println("Pilihan tidak Valid! Masukkan kembali pilihan Anda: ");
                runSPL();
            }
        }
        //s.close();
    }

    /**
     * Metode untuk menjalankan subprogram determinan
     */
    private static void runDet() {
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        System.out.println();

        switch (pilihan0) {
            // Pilihan 1 - Metode Reduksi Baris
            case 1: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        // NOTE: I changd n to nBrsA here and 3 lines after here
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();

                        double sol = Matriks.determinanRedBrs(m);
                        System.out.printf("Reduksi baris: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        simpanKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        double sol = Matriks.determinanRedBrs(m);
                        System.out.printf("Reduksi baris: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        simpanKeFile(strSol);
                        break;
                    }
                }
                break;
            }
            // Pilihan 2 - Metode Ekspansi Kofaktor
            case 2: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        // NOTE: I changd n to nBrsA here and 3 lines after here
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();

                        double sol = Matriks.determinanEksKof(m);
                        System.out.printf("Ekspansi kofaktor: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        simpanKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        double sol = Matriks.determinanEksKof(m);
                        System.out.printf("Ekspansi kofaktor: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        simpanKeFile(strSol);
                        break;
                    }
                }
                break;
            }
            // Pilihan 3 - Kembali
            case 3: {
                break;
            }
            // Pilihan tidak valid
            default: {
                System.out.println("Pilihan tidak valid!");
                runDet();
            }
        }
        //s.close();
    }

    /**
     * Metode untuk menjalankan subprogram matriks balikan
     */
    private static void runBalikan() {
        Scanner s = new Scanner(System.in);
        subMenu3();
        int pilihan0 = s.nextInt();
        System.out.println();

        switch (pilihan0) {
            case 1: // balikan dengan adjoin
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();

                        Matriks.balikanAdjoint(m);
                        simpanKeFile(m);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        Matriks.balikanAdjoint(m);

                        simpanKeFile(m);
                        break;
                    }
                }
                break;
            case 2: // Dengan OBE
                pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // TODO: BENERIN
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();

                        Matriks.balikanAdjoint(m);
                        simpanKeFile(m);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        Matriks.balikanAdjoint(m);

                        simpanKeFile(m);
                        break;
                    }
                }
        }
    }

    /**
     * Metode untuk menjalankan subprogram interpolasi polinom
     */
    private static void runInterpolasi() {
        int pilihan1 = bacaMetodeInput();
        switch (pilihan1) {
            // Pilihan 1 - Input dari keyboard
            case 1: {

                break;
            }
            // Pilihan 2 - Baca dari file eksternal
            case 2: {

                break;
            }
        }
    }

    /**
     * Metode untuk menjalankan subprogram regresi linier berganda
     */
    private static void runRegresi() {
        int pilihan1 = bacaMetodeInput();
        switch (pilihan1) {
            // Pilihan 1 - Input dari keyboard
            case 1: {

                break;
            }
            // Pilihan 2 - Baca dari file eksternal
            case 2: {

                break;
            }
        }
    }

    /**
     * Metode untuk menjalankan program
     */
    private static void run() {
        menu();
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        switch (pilihan0) {
            // Pilihan 1 - Sistem Persamaan Linear
            case 1: {
                subMenu1();
                runSPL();
                break;
            }
            // Pilihan 2 - Determinan
            case 2: {
                subMenu2();
                runDet();
                break;
            }
            // Pilihan 3 - Matriks Balikan
            case 3: {
                subMenu3();
                runBalikan();
                break;
            }
            // Pilihan 4 - Interpolasi Polinom
            case 4: {
                runInterpolasi();
                break;
            }
            // Pilihan 5 - Regresi Linier Berganda
            case 5: {
                runRegresi();
                break;
            }
            // Pilihan 6 - Keluar
            case 6: {
                //s.close();
                outro();
                System.exit(0);
                //break;
            }
            // Pilihan tidak valid
            default: {
                System.out.println("Pilihan tidak valid!");
            }
        }
        run();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        intro();
        wOOOw();
        run();
        s.close();
    }
}
