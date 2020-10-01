/**
 * Test
 */
import java.util.Scanner;

public class Test {
    // === PENGUJIAN / TESTING === //

    /**
     * Metode untuk pengujian metode lain matriks ("driver")
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int nBar, nKol;

        /*
        // Buat baca matriks 3x3 dari file
        nBar = 3; nKol = 3;
        Matriks mOriginal = new Matriks(nBar, nKol);
        Matriks m1 = Matriks.bacaDariFile();
        */

        // Baca dari masukan user (keyboard)
        System.out.print("Masukkan banyak baris: ");
        nBar = s.nextInt();
        System.out.print("Masukkan banyak kolom: ");
        nKol = s.nextInt();
        Matriks m1 = new Matriks(nBar, nKol);
        Matriks mOriginal = new Matriks(nBar, nKol);
        m1.bacaMatriks();

        Matriks.salinMatriks(m1, mOriginal);
        m1.tulisMatriks();

        System.out.printf("Ekspansi kofaktor: %.2f\n", Matriks.determinanEksKof(m1));
        System.out.printf("Reduksi baris: %.2f\n", Matriks.determinanRedBrs(m1));
        Matriks.salinMatriks(mOriginal, m1);

        // tulis ke file
        //Matriks.tulisKeFile(m1);
        //Matriks.tulisKeFile("Hello, world!");
        //System.out.println();

        //m1.kaliBaris(0, 200);
        //m1.tulisMatriks();
        //System.out.println();

        //m1.tukarBaris(0, 2);
        //m1.tulisMatriks();
        //System.out.println();

        //m1.jumlahBaris(1, 2);
        //m1.tulisMatriks();
        //System.out.println();

        //m1.jumlahBaris(1, 2, -1);
        //m1.tulisMatriks();
        //System.out.println();

        //m1.jadikanAugmented(mOriginal);

        //m1.jadikanSgtgAtas();
        //m1.jadikanAugmented(m1);

        //mOriginal.tulisMatriks();
        //m1.tulisMatriks();

        //m1.makeEselon();
        Matriks.tulisSolusi(Matriks.gaussJordan(m1));
        //m1.tulisSolusi(m1.gauss(m1));

        //m1.makeAugmented(mOriginal);
        //System.out.println("Before: ");
        //m1.tulisMatriks();
        //m1.eselonTereduksi();
        //System.out.println("After: ");
        //m1.tulisMatriks();
        //m1.makeAugmented(mOriginal);
        //System.out.println(m1.jumElmt() + " " + m1.getJmlBrs() + " " + m1.getJmlKol());
        //m1.tulisMatriks();

        //m1 = Matriks.bacaDariFile();
        //Matriks.interpolasi(m1, 9.2);

        //System.out.println("88239r58239r5qw4ftg8i90gv8hg789yw4789ty234 89tyu294yt9");

        Matriks.salinMatriks(mOriginal, m1);
        Matriks.tulisSolusi(Matriks.cramer(m1));
        //m1.balikan();
        m1.tulisMatriks();
        m1.tulisMatriks();
        System.out.println("Determinan: " + Matriks.determinanRedBrs(m1));
        System.out.println("inverse:");
        Matriks.balikanAdjoint(m1);
        m1.tulisMatriks();

        Matriks.salinMatriks(mOriginal, m1);
        System.out.println("inverse:");
        Matriks.balikanOBE(m1).tulisMatriks();
        m1.tulisMatriks();

        Matriks.salinMatriks(mOriginal, m1);
        Matriks.tulisSolusi(Matriks.cramer(m1));

        System.out.println("Regresi Linier Berganda :");
        Matriks.Regresi(m1).tulisMatriks();;

        s.close();
        System.out.println("This is the end");
    }
}
