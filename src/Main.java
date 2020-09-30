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
    private void intro() {
        System.out.println(
            "Selamat Datang di Program Tugas Besar Pertama\n
            Mata Kuliah IF2123 Aljabar Linier dan Geometri\n
            \n");
    }

    /**
     * Metode untuk mencetak penutup
     */
    private void outro() {
        System.out.println(
            "\nProgram dibuat oleh:\n
            *) Jeane Mikha Erwansyah - 13519116\n
            *) Josep Marcello        - 13519164\n
            *) David Owen Adiwiguna  - 13519169\n
            \nEnd of Program.");
    }

    /**
     * Metode untuk mencetak menu
     */
    private void menu() {
        System.out.println("\nMENU:\n");
        System.out.println("1. Sistem Persamaan Linier\n
                            2. Determinan\n
                            3. Matriks Balikan\n
                            4. Interpolasi Polinom\n
                            5. Regresi Linier Berganda\n
                            6. Keluar\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 1
     */
    private void subMenu1() {
        System.out.println("\nSUB MENU 1:\n");
        System.out.println("1. Metode Eliminasi Gauss\n
                            2. Metode Eliminasi Gauss-Jordan\n
                            3. Matriks Balikan\n
                            4. Kaidah Cramer\n
                            5. Kembali\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk mencetak sub menu 2
     */
    private void subMenu2() {
        System.out.println("\nSUB MENU 2:\n");
        System.out.println("1. Metode Reduksi Baris\n
                            2. Metode Ekspansi Kofaktor\n
                            3. Kembali\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");
    }

    /**
     * Metode untuk membaca metode input
     * @return integer pilihan valid
     */
    private int bacaMetodeInput() {
        System.out.println("\nMENU METODE INPUT:\n");
        System.out.println("1. Input dari keyboard\n
                            2. Baca dari file eksternal\n");
        System.out.println("\nMasukkan menu pilihan Anda: ");

        Scanner s = new Scanner(System.in);
        System.out.println();
        int pilihan = s.nextInt();

        while (pilihan != 1 || pilihan != 2) {
            System.out.println("Pilihan tidak valid! Masukan kembali pilihan Anda: ");
            pilihan = s.nextInt();
        }
        s.close();
        return pilihan;
    }

    /**
     * Metode untuk menjalankan subprogram SPL
     */
    private void runSPL() {
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        System.out.println();

        switch (pilihan0) {
            // Pilihan 1 - Metode Eliminasi Gauss
            case 1: {
                int pilihan1 = main.bacaMetodeInput();
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
                        Matriks.tulisKeFile(Matriks.stringSolusi(sol));
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gauss(m);
                        Matriks.tulisSolusi(sol);
                        Matriks.tulisKeFile(Matriks.stringSolusi(sol));
                        break;
                    }
                }
                break;
            }
            // Pilihan 2 - Metode Eliminiasi Gauss-Jordan
            case 2: {
                int pilihan1 = main.bacaMetodeInput();
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
                        Matriks.tulisKeFile(Matriks.stringSolusi(sol));
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();
                        HashMap<String, String> sol = new HashMap<>();
                        sol = Matriks.gaussJordan(m);
                        Matriks.tulisSolusi(sol);
                        Matriks.tulisKeFile(Matriks.stringSolusi(sol));
                        break;
                    }
                }
                break;
            }
            // Pilihan 3 - Matriks Balikan
            case 3: {
                int pilihan1 = main.bacaMetodeInput();
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
                int pilihan1 = main.bacaMetodeInput();
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
        s.close();
    }

    /**
     * Metode untuk menjalankan subprogram determinan
     */
    private void runDet() {
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        System.out.println();
        switch (pilihan0) {
            // Pilihan 1 - Metode Reduksi Baris
            case 1: {
                int pilihan1 = main.bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        n = s.nextInt();
                        
                        Matriks m = new Matriks(n, n);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();
                        
                        double sol = Matriks.determinanRedBrs(m);
                        System.out.printf("Reduksi baris: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        Matriks.tulisKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        double sol = Matriks.determinanRedBrs(m);
                        System.out.printf("Reduksi baris: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        Matriks.tulisKeFile(strSol);
                        break;
                    }
                }
                break;
            }
            // Pilihan 2 - Metode Ekspansi Kofaktor
            case 2: {
                int pilihan1 = main.bacaMetodeInput();
                switch (pilihan1) {
                    // Pilihan 1 - Input dari keyboard
                    case 1: {
                        int nBrsA, nKolA;
                        System.out.println("\nMasukkan n: ");
                        n = s.nextInt();
                        
                        Matriks m = new Matriks(n, n);
                        System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                        m.bacaMatriks();
                        
                        double sol = Matriks.determinanEksKof(m));
                        System.out.printf("Ekspansi kofaktor: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        Matriks.tulisKeFile(strSol);
                        break;
                    }
                    // Pilihan 2 - Baca dari file eksternal
                    case 2: {
                        Matriks m = new Matriks(1, 1);
                        m = Matriks.bacaDariFile();

                        double sol = Matriks.determinanEksKof(m));
                        System.out.printf("Ekspansi kofaktor: %.2f\n", sol);
                        String strSol = String.format("%.2f", sol);
                        Matriks.tulisKeFile(strSol);
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
        s.close();
    }

    /**
     * Metode untuk menjalankan subprogram matriks balikan
     */
    private void runBalikan() {
        int pilihan1 = main.bacaMetodeInput();
        switch (pilihan1) {
            // Pilihan 1 - Input dari keyboard
            case 1: {
                int nBrsA, nKolA;
                System.out.println("\nMasukkan n: ");
                n = s.nextInt();
                
                Matriks m = new Matriks(n, n);                         
                System.out.println("\nMasukan koefisien a[i][j] secara berurut");
                m.bacaMatriks();
                
                m.balikan();
                Matriks.tulisKeFile(m);
                break;
            }
            // Pilihan 2 - Baca dari file eksternal
            case 2: {
                Matriks m = new Matriks(1, 1);
                m = Matriks.bacaDariFile();

                m.balikan();

                Matriks.tulisKeFile(m);
                break;
            }
        }
        break;
    }

    /**
     * Metode untuk menjalankan subprogram interpolasi polinom
     */
    private void runInterpolasi() {
        int pilihan1 = main.bacaMetodeInput();
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
        break;
    }

    /**
     * Metode untuk menjalankan subprogram regresi linier berganda
     */
    private void runRegresi() {
        int pilihan1 = main.bacaMetodeInput();
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
        break;
    }

    /**
     * Metode untuk menjalankan program
     */
    private void run() {
        main.menu();
        Scanner s = new Scanner(System.in);
        int pilihan0 = s.nextInt();
        switch (pilihan0) {
            // Pilihan 1 - Sistem Persamaan Linear
            case 1: {
                main.subMenu1();
                main.runSPL();
                break;
            }
            // Pilihan 2 - Determinan
            case 2: {
                main.subMenu2();
                main.runDet();
                break;
            }
            // Pilihan 3 - Matriks Balikan 
            case 3: {
                main.runBalikan();
                break;
            }
            // Pilihan 4 - Interpolasi Polinom
            case 4: {
                main.runInterpolasi();
                break;
            }
            // Pilihan 5 - Regresi Linier Berganda
            case 5: {
                main.runRegresi();
                break;
            }
            // Pilihan 6 - Keluar
            case 6: {
                s.close();
                main.outro();
                System.exit(0);
                //break;
            }
            // Pilihan tidak valid
            default: {
                System.out.println("Pilihan tidak valid!");
            }
        }
        main.run();
    }

    public static void main(String[] args) {
        main.intro();
        main.run();
    }
}