package taberth;
import java.util.ArrayList;

public class Berth {
    private int idBerth;
    private int idAsli;
    private double maxDWT;
    private ArrayList<Ship> daftarship = new ArrayList<Ship>();

    public Berth(int idBerth) {
        this.idBerth = idBerth;
    }

    public int getIdBerth() {
        return idBerth;
    }

    public void setIdBerth(int idBerth) {
        this.idBerth = idBerth;
    }

    public ArrayList<Ship> getDaftarship() {
        return daftarship;
    }

    public void setDaftarship(ArrayList<Ship> daftarship) {
        this.daftarship = daftarship;
    }
    
    public int getIdAsli() {
        return idAsli;
    }

    public void setIdAsli(int idAsli) {
        this.idAsli = idAsli;
    }

    public double getMaxDWT() {
        return maxDWT;
    }

    public void setMaxDWT(double maxDWT) {
        this.maxDWT = maxDWT;
    }

    
    
}
