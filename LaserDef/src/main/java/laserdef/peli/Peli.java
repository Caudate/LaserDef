
package laserdef.peli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import laserdef.domain.Laaseri;
import laserdef.domain.Seina;
import laserdef.gui.Paivitettava;
import laserdef.laserdef.Suunta;

public class Peli implements Paivitettava {
    
    private int korkeus;
    private int leveys;
    private List<Laaseri> laaserit;
    private List<Seina> seinat;
    private Random rng;

    public Peli(int korkeus, int leveys) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.laaserit = new ArrayList<Laaseri>();
        this.seinat = new ArrayList<Seina>();
        this.rng = new Random();
    }
    
    public List<Laaseri> getLaaserit() {
        return laaserit;
    }

    @Override
    public void paivita() {
        this.poistaPoistettavatLaaserit();
        if (rng.nextInt(50) < 1) {
            int arvottu = rng.nextInt(4);
            if (arvottu == 0) {
                this.laaserit.add(new Laaseri(Suunta.YLOS, Color.red, 3, rng.nextInt(this.leveys), this.korkeus, 5));
            } else if (arvottu == 1) {
                this.laaserit.add(new Laaseri(Suunta.ALAS, Color.red, 3, rng.nextInt(this.leveys), 0, 5));
            } else if (arvottu == 2) {
                this.laaserit.add(new Laaseri(Suunta.OIKEA, Color.red, 3, 0, rng.nextInt(this.korkeus), 5));
            } else if (arvottu == 3) {
                this.laaserit.add(new Laaseri(Suunta.VASEN, Color.red, 3, this.leveys, rng.nextInt(this.korkeus), 5));
            }
        }
        for (Laaseri laaseri : this.laaserit) {
            laaseri.kasva();
            if (laaseri.getX() > this.leveys || laaseri.getX() < 0 || laaseri.getY() > this.korkeus || laaseri.getY() < 0) {
                laaseri.setPoistetaanko(true);
            }
        } 
    }

    public void poistaPoistettavatLaaserit() {
        for (int i = 0; i < this.laaserit.size(); i++) {
            if (this.laaserit.get(i).isPoistetaanko()) {
                this.laaserit.remove(i);
                i--;
            }
        }
    }
    
    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }
}
