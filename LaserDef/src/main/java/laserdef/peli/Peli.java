
package laserdef.peli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import laserdef.domain.LaaserOsa;
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
    private int nopeus;

    public Peli(int korkeus, int leveys, int nopeus) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.laaserit = new ArrayList<Laaseri>();
        this.seinat = new ArrayList<Seina>();
        this.rng = new Random();
        this.nopeus = nopeus;
    }
    
    public List<Laaseri> getLaaserit() {
        return laaserit;
    }
    

    @Override
    public void paivita() {
        poistaPoistettavatLaaserit();
        arvoUusiLaaseri();
        kasvataLaasereita();
    }

    public void arvoUusiLaaseri() {
        if (rng.nextInt(60) < 1) {
            int arvottu = rng.nextInt(4);
            if (arvottu == 0) {
                this.laaserit.add(new Laaseri(Suunta.YLOS, Color.red, (rng.nextInt(3)+1)*this.nopeus, rng.nextInt(this.leveys), this.korkeus, 5));
            } else if (arvottu == 1) {
                this.laaserit.add(new Laaseri(Suunta.ALAS, Color.red, (rng.nextInt(3)+1)*this.nopeus, rng.nextInt(this.leveys), 0, 5));
            } else if (arvottu == 2) {
                this.laaserit.add(new Laaseri(Suunta.OIKEA, Color.red, (rng.nextInt(3)+1)*this.nopeus, 0, rng.nextInt(this.korkeus), 5));
            } else if (arvottu == 3) {
                this.laaserit.add(new Laaseri(Suunta.VASEN, Color.red, (rng.nextInt(3)+1)*this.nopeus, this.leveys, rng.nextInt(this.korkeus), 5));
            }
        }
    }
    
    public void kasvataLaasereita() {
        for (Laaseri laaseri : this.laaserit) {
            for (int i = 0; i < laaseri.getNopeus(); i++) {
                laaseri.kasva();
                if (this.tormaakoLaaseri(laaseri)) {
                   laaseri.setKasvaako(false);
                   i = laaseri.getNopeus();
                }
            }
            if (laaseri.getX() > this.leveys || laaseri.getX() < 0 || laaseri.getY() > this.korkeus || laaseri.getY() < 0) {
                laaseri.setPoistetaanko(true);
            }
        } 
    }
    
    public void poistaPoistettavatLaaserit() {
        Iterator<Laaseri> iterator = this.laaserit.iterator();
        while (iterator.hasNext()) {
            Laaseri laaser = iterator.next();
            if (laaser.isPoistetaanko()) {
                iterator.remove();
            }
        }
    }
    
    public boolean tormaakoLaaseri(Laaseri laaseri) {
        for (Laaseri laaseri2 : this.laaserit) {
            if (laaseri != laaseri2) {
                for (LaaserOsa osa : laaseri2.getOsat()) {
                    if(laaseri.osuuko(osa)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }
    
    // testejä varten
    
    public void lisaaLaaseri(Laaseri laaseri) {
        this.laaserit.add(laaseri);
    }
}
