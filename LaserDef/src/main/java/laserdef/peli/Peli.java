
package laserdef.peli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import laserdef.domain.LaaserOsa;
import laserdef.domain.Laaseri;
import laserdef.domain.Pommi;
import laserdef.domain.Seina;
import laserdef.gui.Paivitettava;
import laserdef.laserdef.Suunta;

public class Peli implements Paivitettava {
    
    private int korkeus;
    private int leveys;
    private List<Laaseri> laaserit;
    private List<Pommi> pommit;
    private List<Seina> seinat;
    private Random rng;
    private int nopeus;
    private int pisteet;
    private int laaserienPaksuus = 5;
    private int pommienSivunKoko = 50;

    public Peli(int korkeus, int leveys, int nopeus) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.laaserit = new ArrayList<>();
        this.pommit = new ArrayList<>();
        this.seinat = new ArrayList<>();
        this.rng = new Random();
        this.nopeus = nopeus;
        this.pisteet = 0;
    }
    
    public List<Laaseri> getLaaserit() {
        return laaserit;
    }
    
    @Override
    public void paivita() {
        poistaPoistettavatLaaserit();
        poistaPoistettavatPommit();
        arvoUusiLaaseri();
        kasvataLaasereita();
        tikitaPommeja();
    }

    public void arvoUusiLaaseri() {
        if (rng.nextInt(120) < 1) {
            int arvottu = rng.nextInt(4);
            if (arvottu == 0) {
                this.laaserit.add(new Laaseri(Suunta.YLOS, Color.red, (rng.nextInt(3)+1)*this.nopeus, rng.nextInt(this.leveys), this.korkeus, this.laaserienPaksuus));
            } else if (arvottu == 1) {
                this.laaserit.add(new Laaseri(Suunta.ALAS, Color.red, (rng.nextInt(3)+1)*this.nopeus, rng.nextInt(this.leveys), 0, this.laaserienPaksuus));
            } else if (arvottu == 2) {
                this.laaserit.add(new Laaseri(Suunta.OIKEA, Color.red, (rng.nextInt(3)+1)*this.nopeus, 0, rng.nextInt(this.korkeus), this.laaserienPaksuus));
            } else if (arvottu == 3) {
                this.laaserit.add(new Laaseri(Suunta.VASEN, Color.red, (rng.nextInt(3)+1)*this.nopeus, this.leveys, rng.nextInt(this.korkeus), this.laaserienPaksuus));
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
                   if (!onkoKoordinaatissaPommi(laaseri.getX(), laaseri.getY())) {
                       this.pommit.add(new Pommi(1, 1, 160 - this.nopeus*20, laaseri.getX(), laaseri.getY(), this.pommienSivunKoko, this.pommienSivunKoko));
                   }
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
                System.out.println("laaseri poistettu");
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
    
    public void tikitaPommeja() {
        for (Pommi pommi : this.pommit) {
            pommi.tikita();
            if (pommi.rajahtaakoPommi()) {
                rajaytaPommi(pommi);
            }
        }
    }
    
    public boolean onkoKoordinaatissaPommi(int x, int y) {
        for (Pommi pommi : this.pommit) {
            if (pommi.getX() == x && pommi.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    
    public int rajaytaPommi(Pommi pommi) {
        pommi.setPoistetaanko(true);
        poistaPomminLaaseri(pommi);
        return pommi.otaSaadutPisteet();
    }
    
    public void poistaPomminLaaseri(Pommi pommi) {
        for (Laaseri laaseri : this.laaserit) {
            if (laaseri.getX() == pommi.getX() && laaseri.getY() == pommi.getY()) {
                laaseri.setPoistetaanko(true);
            }
        }
    }
    
    public void poistaPoistettavatPommit() {
        Iterator<Pommi> iterator = this.pommit.iterator();
        while (iterator.hasNext()) {
            Pommi pommi = iterator.next();
            if (pommi.isPoistetaanko()) {
                iterator.remove();
                System.out.println("Pommi poistettu");
            }
        }
    }
    
    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
    }

    public List<Pommi> getPommit() {
        return pommit;
    }

    public int getLaaserienPaksuus() {
        return laaserienPaksuus;
    }

    public int getPommienSivunKoko() {
        return pommienSivunKoko;
    }
    
    // testejä varten
    
    public void lisaaLaaseri(Laaseri laaseri) {
        this.laaserit.add(laaseri);
    }
}
