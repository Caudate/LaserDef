
package peli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import domain.LaaserOsa;
import domain.Laaseri;
import domain.Pommi;
import domain.Seina;
import gUI.Paivitettava;
import main.Suunta;

/**
 * Hallinoi osia ja sisältää pelin toiminnan.
 * Toteuttaa rajapinnan Paivita
 */
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
    
    /**
     * palauttaa laaserit
     * @return laaserit
     */
    public List<Laaseri> getLaaserit() {
        return laaserit;
    }
    
    /**
     * Paivittaa pelin tilanteen.
     * poistaa poistettavat pommit ja laaserit.
     * Arpoo mahdollisia uusia laasereita.
     * Kasvattaa kaikkia laasereita ja tikittää pommeja.
     */
    @Override
    public void paivita() {
        poistaPoistettavatLaaserit();
        poistaPoistettavatPommit();
        arvoUusiLaaseri();
        kasvataLaasereita();
        tikitaPommeja();
    }

    /**
     * Arpoo ensin tehdäänkö tällä tikillä laaseri. 
     * Jos tehdään niin arpoo uuden laaserin suunnan ja nopeuden ja luo tämän.
     */
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

    /**
     * Kutsuu kaikkien laaserien kasvata metodia niin monta kertaa kuin laaserin
     * nopeus määrittää.
     * Testaa samalla törmääkö kasvanut laaseri ja törmätessä luo uuden pommin ja
     * pysäyttää laaserin kasvun.
     * Jos laaseri kasvaa yli pelirajojen, asettaa metodi tämän poistettavaksi.
     */
    public void kasvataLaasereita() {
        for (Laaseri laaseri : this.laaserit) {
            for (int i = 0; i < laaseri.getNopeus(); i++) {
                laaseri.kasva();
                if (this.tormaakoLaaseri(laaseri)) {
                   laaseri.setKasvaako(false);
                   i = laaseri.getNopeus();
                   if (!onkoKoordinaatissaPommi(laaseri.getX(), laaseri.getY())) {
                       this.pommit.add(new Pommi(1, 1, 300 - this.nopeus*20, laaseri.getX(), laaseri.getY(), this.pommienSivunKoko, this.pommienSivunKoko));
                   }
                }
            }
            if (laaseri.getX() > this.leveys || laaseri.getX() < 0 || laaseri.getY() > this.korkeus || laaseri.getY() < 0) {
                laaseri.setPoistetaanko(true);
            }
        }
    }
    
    /**
     * Poistaa kaikki laaserit listasta, jotka on asetettu poistettaviksi.
     */
    public void poistaPoistettavatLaaserit() {
        Iterator<Laaseri> iterator = this.laaserit.iterator();
        while (iterator.hasNext()) {
            Laaseri laaser = iterator.next();
            if (laaser.isPoistetaanko()) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Testaa parametrina annetun laaserin osumista kaikkien muiden laaserien 
     * LaaserOsiin paitsi omiinsa. Jos osuma tapahtuu palautetaan true.
     * @param laaseri
     * @return true jos osuu. false jos ei osu.
     */
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
    
    /**
     * Kutsuu tikita metodia kaikille pelin pommeille ja kutsuu rajaytaPommi
     * metodia, jos rajahtaakoPommi muuttuu tikityksen seurauksena trueksi.
     */
    public void tikitaPommeja() {
        for (Pommi pommi : this.pommit) {
            pommi.tikita();
            if (pommi.rajahtaakoPommi()) {
                rajaytaPommi(pommi);
            }
        }
    }
    
    
    /**
     * Testaa onko parametreina annetussa koordinaatissa pommi.
     * @param x Tutkittavan pisteen X koordinaatti.
     * @param y Tutkittavan pisteen Y koordinaatti.
     * @return true, jos koordinaatissa on pommi ja false jos ei ole.
     */
    public boolean onkoKoordinaatissaPommi(int x, int y) {
        for (Pommi pommi : this.pommit) {
            if (pommi.getX() == x && pommi.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Asettaa parametrina annetun pommin poistettavaksi ja kutsuu 
     * poistaPomminLaaseri metodia parametrillaan.
     * @param pommi räjäytettävä pommi
     */
    public void rajaytaPommi(Pommi pommi) {
        pommi.setPoistetaanko(true);
        poistaPomminLaaseri(pommi);
    }
    
    /**
     * Asettaa parametrina annetun pommin koordinaatissa olevan laaserin 
     * poistettavaksi.
     * @param pommi pommi jonka koordinaatista laaseri poistetaan.
     */
    public void poistaPomminLaaseri(Pommi pommi) {
        for (Laaseri laaseri : this.laaserit) {
            if (laaseri.getX() == pommi.getX() && laaseri.getY() == pommi.getY()) {
                laaseri.setPoistetaanko(true);
            }
        }
    }
    
    /**
     * Poistaa kaikki pommit listasta, jotka on asetettu poistettaviksi.
     */
    public void poistaPoistettavatPommit() {
        Iterator<Pommi> iterator = this.pommit.iterator();
        while (iterator.hasNext()) {
            Pommi pommi = iterator.next();
            if (pommi.isPoistetaanko()) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Annettuun antaa pisteet ja räjäyttää pommin annetusta koordinaatista.
     * @param x
     * @param y 
     */
    public void painettu(int x, int y) {
        for (Pommi pommi : pommit) {
            if (x >= pommi.getX() && x < pommi.getX() + pommi.getLeveys() &&
                y >= pommi.getY() && y < pommi.getX() + pommi.getLeveys()) {
                this.pisteet += pommi.otaSaadutPisteet();
                System.out.println("osuma pommiin havaittu");
                rajaytaPommi(pommi);
            }
        }
    }
    
    // getterit ja setterit
    
    
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
