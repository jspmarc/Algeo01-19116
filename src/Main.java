/**
 * TODO:
 * [ ] input interpolasi dalam format (x0, y0), ..., (xn, yn)
 */

import java.util.*;

/**
 * Kelas utama untuk Tubes 01 IF2123 2020/2021
 *
 * @author Jeane Mikha Erwansyah - 13519116
 * @author Josep Marcello - 13519164
 * @author David Owen Adiwiguna - 13519169
 *
 */

class Main {
    /**
     * DAFTAR FUNGSI DAN PROSEDUR
     * - intro
     * - outro
     * - clearScr
     * - wOOOw
     * - w000w
     * - menu
     * - subMenu1
     * - subMenu2
     * - subMenu3
     * - bacaMetodeInput
     * - simpanKeFile (HashMap)
     * - simpanKeFile (String)
     * - simpanKeFile (Matriks)
     * - runSPL
     * - runDet
     * - runBalikan
     * - runInterpolasi
     * - runRegresi
     * - run
     */

    // Tugas

    /**
     * Metode untuk mencetak pembukaan
     */
    private static void intro() {
        clearScr();
        System.out.println(
            "Selamat Datang di Program Tugas Besar Pertama\n"
            + "Mata Kuliah IF2123 Aljabar Linier dan Geometri\n\n");
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

    /**
     * Nge-clear layar (may not work on all terminal emulator)
     */
    private static void clearScr() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * nge-print ASCII art untuk logo
     */
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
     * nge-print ASCII art untuk pesan penutupan
     */
    private static void w000w() {
        System.out.println
            (
                " ______     __  __     ______   \n"
                + "/\\  == \\   /\\ \\_\\ \\   /\\  ___\\  \n"
                + "\\ \\  __<   \\ \\____ \\  \\ \\  __\\  \n"
                + " \\ \\_____\\  \\/\\_____\\  \\ \\_____\\\n"
                + "  \\/_____/   \\/_____/   \\/_____/"
            );
    }

    /**
     * Metode untuk mencetak menu
     */
    private static void menu() {
        System.out.println("\nMENU:");
        System.out.println("1. Sistem Persamaan Linier\n"
                            + "2. Determinan\n"
                            + "3. Matriks Balikan\n"
                            + "4. Interpolasi Polinom\n"
                            + "5. Regresi Linier Berganda\n"
                            + "6. Keluar\n");
        System.out.printf("Masukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 1
     */
    private static void subMenu1() {
        System.out.println("\nMETODE UNTUK MENGHITUNG SPL:");
        System.out.println("1. Metode Eliminasi Gauss\n"
                            + "2. Metode Eliminasi Gauss-Jordan\n"
                            + "3. Matriks Balikan\n"
                            + "4. Kaidah Cramer\n"
                            + "5. Kembali\n");
        System.out.printf("Masukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 2
     */
    private static void subMenu2() {
        System.out.println("\nMETODE UNTUK MENGHITUNG DETERMINAN:");
        System.out.println("1. Metode Reduksi Baris\n"
                            + "2. Metode Ekspansi Kofaktor\n"
                            + "3. Kembali\n");
        System.out.printf("Masukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 3
     */
    private static void subMenu3() {
        System.out.println("\nMETODE UNTUK MENGHITUNG BALIKAN MATRIKS:");
        System.out.println("1. Metode adjoin matriks\n"
                            + "2. Metode OBE\n"
                            + "3. Kembali\n");
        System.out.printf("Masukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk membaca metode input
     * @return integer pilihan valid
     */
    private static int bacaMetodeInput() {
        System.out.println("MENU METODE INPUT:");
        System.out.println("1. Input dari keyboard\n"
                            + "2. Baca dari file eksternal\n");
        System.out.printf("Masukkan menu pilihan Anda: ");

        Scanner s = new Scanner(System.in);
        int pilihan = s.nextInt();

        while (pilihan != 1 && pilihan != 2) {
            System.out.printf("Pilihan tidak valid! Masukkan kembali pilihan Anda: ");
            pilihan = s.nextInt();
        }
        return pilihan;
    }

    /**
     * menyimpan hasil perhitungan ke file
     * @param sol hasil perhitungan yang ingin disimpan
     */
    private static void simpanKeFile(HashMap<String, String> sol) {
        Scanner s = new Scanner(System.in);
        System.out.printf("\nApakah Anda ingin menyimpan solusi ke file? (y/n): ");
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
        System.out.printf("\nApakah Anda ingin menyimpan solusi ke file? (y/n): ");
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
        System.out.printf("\nApakah Anda ingin menyimpan solusi ke file? (y/n): ");
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
                        System.out.printf("\nMasukkan m: ");
                        nBrsA = s.nextInt();
                        System.out.printf("Masukkan n: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        mA.bacaMatriks();
                        System.out.printf("\nMasukkan b[i] secara berurut\n");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gauss(mA);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gauss(m);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
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
                        System.out.printf("\nMasukkan m: ");
                        nBrsA = s.nextInt();
                        System.out.printf("Masukkan n: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        mA.bacaMatriks();
                        System.out.printf("\nMasukkan b[i] secara berurut\n");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gaussJordan(mA);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gaussJordan(m);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
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
                        System.out.printf("\nMasukkan m: ");
                        nBrsA = s.nextInt();
                        System.out.printf("Masukkan n: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        mA.bacaMatriks();
                        System.out.printf("\nMasukkan b[i] secara berurut\n");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.splBalikan(mA);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.splBalikan(m);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
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
                        System.out.printf("\nMasukkan m: ");
                        nBrsA = s.nextInt();
                        System.out.printf("Masukkan n: ");
                        nKolA = s.nextInt();

                        Matriks mA = new Matriks(nBrsA, nKolA);
                        Matriks mB = new Matriks(nBrsA, 1);

                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        mA.bacaMatriks();
                        System.out.printf("\nMasukkan b[i] secara berurut\n");
                        mB.bacaMatriks();
                        mA.makeAugmented(mB);

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.cramer(mA);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.cramer(m);
                        System.out.println("\nSolusi SPL:");
                        Matriks.tulisSolusi(sol, "x");
                        simpanKeFile(sol);
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
                        int nBrsA;
                        System.out.printf("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        m.bacaMatriks();

                        double sol = Matriks.determinanRedBrs(m);
                        //String strSol = String.format("%.2f", sol);
                        String strSol = String.valueOf(sol);
                        System.out.println("Reduksi baris: " + strSol);
                        simpanKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        //m.makeNotAugmented(new Matriks(m.getJmlBrs(), 1));

                        double sol = Matriks.determinanRedBrs(m);
                        //String strSol = String.format("%.2f", sol);
                        String strSol = String.valueOf(sol);
                        System.out.println("Reduksi baris: " + strSol);
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
                        int nBrsA;
                        System.out.printf("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
                        m.bacaMatriks();

                        double sol = Matriks.determinanEksKof(m);
                        //String strSol = String.format("%.2f", sol);
                        String strSol = String.valueOf(sol);
                        System.out.println("Ekspansi kofaktor: " + strSol);
                        simpanKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        //m.makeNotAugmented(new Matriks(m.getJmlBrs(), 1));

                        double sol = Matriks.determinanEksKof(m);
                        //String strSol = String.format("%.2f", sol);
                        String strSol = String.valueOf(sol);
                        System.out.println("Ekspansi kofaktor: " + strSol);
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
        int pilihan0 = s.nextInt();
        System.out.println();

        switch (pilihan0) {
            // balikan dengan adjoin
            case 1: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA;
                        System.out.printf("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
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
            }
            // Dengan OBE
            case 2: {
                int pilihan1 = bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA;
                        System.out.printf("\nMasukkan n: ");
                        nBrsA = s.nextInt();

                        Matriks m = new Matriks(nBrsA, nBrsA);
                        System.out.printf("\nMasukkan koefisien a[i][j] secara berurut\n");
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
            }
        }
    }

    /**
     * Metode untuk menjalankan subprogram interpolasi polinom
     */
    private static void runInterpolasi() {
        Scanner s = new Scanner(System.in);
        System.out.println();
        int pilihan1 = bacaMetodeInput();
        switch (pilihan1) {
            // Pilihan 1 - Input dari keyboard
            case 1: {
                System.out.printf("\nMasukkan n: ");
                int nBrs = s.nextInt();
                System.out.println();

                Matriks m = new Matriks(nBrs, 2);
                System.out.println("\nMasukkan data titik: ");
                m.bacaMatriks();

                //System.out.printf("\nMasukkan titik x: ");
                //double x = s.nextDouble();
                //System.out.println();

                //Matriks.interpolasi(m, x);
                Matriks.interpolasi(m);
                break;
            }
            // Pilihan 2 - Baca dari file eksternal
            case 2: {
                Matriks m = new Matriks(1, 2);
                m = Matriks.bacaDariFile();

                //System.out.printf("\nMasukkan titik x: ");
                //double x = s.nextDouble();
                //System.out.println();

                //Matriks.interpolasi(m, x);
                Matriks.interpolasi(m);
                break;
            }
        }
    }

    /**
     * Metode untuk menjalankan subprogram regresi linier berganda
     */
    private static void runRegresi() {
        Scanner s = new Scanner(System.in);
        System.out.println();
        int pilihan1 = bacaMetodeInput();
        switch (pilihan1) {
            // Pilihan 1 - Input dari keyboard
            case 1: {
                int nBrsA;
                System.out.printf("\nMasukkan n: ");
                nBrsA = s.nextInt();

                Matriks m = new Matriks(nBrsA, nBrsA);
                System.out.printf("\nMasukkan x[n][i] secara berurut\n");
                m.bacaMatriks();

                // kurang yi xk?

                m = Matriks.regresi(m);
                System.out.println();
                //simpanKeFile(m);
                break;
            }
            // Pilihan 2 - Baca dari file eksternal
            case 2: {
                Matriks m = new Matriks(1, 1);
                m = Matriks.bacaDariFile();

                m = Matriks.regresi(m);
                System.out.println();
                //simpanKeFile(m);
                break;
            }
        }
    }

    /**
     * Metode untuk menjalankan program
     */
    private static void run(boolean wrongInput) {
        boolean badInput = false;

        clearScr();
        wOOOw();
        menu();
        if (wrongInput) {
            System.out.println("Pilihan tidak valid!");
        }
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        switch (pilihan0) {
            case 1: // Pilihan 1 - Sistem Persamaan Linear
                subMenu1();
                runSPL();
                break;
            case 2: // Pilihan 2 - Determinan
                subMenu2();
                runDet();
                break;
            case 3: // Pilihan 3 - Matriks Balikan
                subMenu3();
                runBalikan();
                break;
            case 4: // Pilihan 4 - Interpolasi Polinom
                runInterpolasi();
                break;
            case 5: // Pilihan 5 - Regresi Linier Berganda
                runRegresi();
                break;
            case 6: // Pilihan 6 - Keluar
                //s.close();
                clearScr();
                w000w();
                outro();
                s.close();
                System.exit(0);
                //break;
            default: // Pilihan tidak valid
                System.out.println("Pilihan tidak valid!");
                badInput = true;
        }
        run(badInput);
    }

    public static void main(String[] args) {
        intro();
        run(false);
    }
}
