package WorkbenchUAS;

public class Kerucut {
    private int id;
    private double alas;
    private double tinggi;

    public Kerucut(double alas, double tinggi, int id) {
        this.alas = alas;
        this.tinggi = tinggi;
        this.id = id;
    }

    public double getTinggi() {
        return tinggi;
    }

    public double getVolume() {
        return Math.round((1.0/3.0)*Math.PI*(Math.pow(this.alas,2))*this.tinggi);
    }

    public String toString() {
        return "Kerucut ke-" + id + "\nAlas: " + alas + "\nTinggi: " + tinggi + "\nVolume: " + this.getVolume() + "\n";
    }
}
