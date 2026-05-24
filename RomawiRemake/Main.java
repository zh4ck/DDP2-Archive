public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO OPERASI MATRIKS ANGKA ROMAWI ===\n");

        RomawiMatriks romawiMatriks = new RomawiMatriks();

        Romawi[][] matriks1 = {
            { new Romawi("IX"), new Romawi("IV") },
            { new Romawi("V"),  new Romawi("II") }
        };

        Romawi[][] matriks2 = {
            { new Romawi("III"), new Romawi("VI") },
            { new Romawi("I"),   new Romawi("X") }
        };

        System.out.println("=== Perkalian Matriks ===");
        Number[][] hasilKali = romawiMatriks.multiplyMatrix(matriks1, matriks2);
        
        GenericMatrix.printResult(matriks1, matriks2, hasilKali, '*');
        System.out.println();


        System.out.println("=== Penambahan Matriks ===");
        Number[][] hasilTambah = romawiMatriks.addMatrix(matriks1, matriks2);
        GenericMatrix.printResult(matriks1, matriks2, hasilTambah, '+');
        System.out.println();


        System.out.println("=== Informasi Matriks 1 ===");
        int totalElemen = romawiMatriks.hitungElemen(matriks1);
        Romawi jumlahElemen = romawiMatriks.jumlahElemen(matriks1);
        
        System.out.println("Banyaknya elemen dalam Matriks 1 : " + totalElemen);
        System.out.println("Jumlah total nilai di Matriks 1   : " + jumlahElemen + " (" + jumlahElemen.intValue() + ")");
        System.out.println();


        System.out.println("=== Simulasi Validasi Simbol Salah ===");
        try {
            System.out.println("Mencoba membuat angka Romawi 'LC'");
            new Romawi("LC");
        } catch (ExceptionInvalidRomeNumber e) {
            System.out.println("Terjadi Exception: " + e.getMessage());
        }

        try {
            System.out.println("\nMencoba membuat angka Romawi 'Z'");
            new Romawi("Z");
        } catch (ExceptionInvalidRomeNumber e) {
            System.out.println("Terjadi Exception: " + e.getMessage());
        }

        try {
            System.out.println("\nMencoba membuat angka Romawi dari bilangan negatif (-15)");
            new Romawi(-15);
        } catch (ExceptionInvalidRomeNumber e) {
            System.out.println("Terjadi Exception: " + e.getMessage());
        }
    }
}