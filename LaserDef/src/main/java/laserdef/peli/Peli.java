
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
    private int pisteet = 0;
    private int elama = 100;
    private int laaserienPaksuus = 7;
    private int pommienSivunKoko = 50;
    private boolean seuraavaHorisontaalinen = true;
    private int laaserienVali = 40; //Monenko päivityksen välein tulee uusi laaseri.
    private int paivitysTilanne = laaserienVali;

    public Peli(int leveys, int korkeus, int nopeus) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.laaserit = new ArrayList<>();
        this.pommit = new ArrayList<>();
        this.seinat = new ArrayList<>();
        this.rng = new Random();
        this.nopeus = nopeus;
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
     * Tekee uuden laaserin, jos on kulunut tarpeeksi päivityksiä edellisestä.
     * Kasvattaa kaikkia laasereita ja tikittää pommeja.
     */
    @Override
    public void paivita() {
        poistaPoistettavatLaaserit();
        poistaPoistettavatPommit();
        if (paivitysTilanne == laaserienVali) {
            arvoUusiLaaseri();
            paivitysTilanne = 0;
        }
        paivitysTilanne++;
        kasvataLaasereita();
        tikitaPommeja();
    }

    /**
     * Arpoo ensin tehdäänkö tällä tikillä laaseri. 
     * Jos tehdään niin arpoo uuden laaserin suunnan ja nopeuden ja luo tämän.
     * Tekee vuorotellen horisontaalisia ja vertikaalisia laasereita.
     */
    public void arvoUusiLaaseri() {
        int nopeusVaihtelunTasaaja = 1;
        if (this.nopeus > 4) {
            nopeusVaihtelunTasaaja = this.nopeus - 3;
        }
        int arvottu = rng.nextInt(2);
        if (arvottu == 0 && seuraavaHorisontaalinen) {
            this.laaserit.add(new Laaseri(Suunta.YLOS, Color.red, (rng.nextInt(this.nopeus)+nopeusVaihtelunTasaaja), rng.nextInt(this.leveys - 100) + 50, this.korkeus, this.laaserienPaksuus));
            seuraavaHorisontaalinen = false;
        } else if (arvottu == 1 && seuraavaHorisontaalinen) {
            this.laaserit.add(new Laaseri(Suunta.ALAS, Color.red, (rng.nextInt(this.nopeus)+nopeusVaihtelunTasaaja), rng.nextInt(this.leveys -100) + 50, 0, this.laaserienPaksuus));
            seuraavaHorisontaalinen = false;
        } else if (arvottu == 0 && !seuraavaHorisontaalinen) {
            this.laaserit.add(new Laaseri(Suunta.OIKEA, Color.red, (rng.nextInt(this.nopeus)+nopeusVaihtelunTasaaja), 0, rng.nextInt(this.korkeus - 100) + 50, this.laaserienPaksuus));
            seuraavaHorisontaalinen = true;
        } else if (arvottu == 1 && !seuraavaHorisontaalinen) {
            this.laaserit.add(new Laaseri(Suunta.VASEN, Color.red, (rng.nextInt(this.nopeus)+nopeusVaihtelunTasaaja), this.leveys, rng.nextInt(this.korkeus - 100) + 50, this.laaserienPaksuus));
            seuraavaHorisontaalinen = true;
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
                       this.pommit.add(new Pommi(1, 5, 200 - this.nopeus*20, laaseri.getX(), laaseri.getY(), this.pommienSivunKoko, this.pommienSivunKoko));
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
     * Kutsuu tikita metodia kaikille pelin pommeille ja kutsuu havitaPommi
     * metodia ja poistaa elämää, jos rajahtaakoPommi muuttuu tikityksen 
     * seurauksena trueksi.
     */
    public void tikitaPommeja() {
        for (Pommi pommi : this.pommit) {
            pommi.tikita();
            if (pommi.rajahtaakoPommi()) {
                this.elama -= pommi.getVoimakkuus();
                havitaPommi(pommi);
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
    public void havitaPommi(Pommi pommi) {
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
            if (x >= pommi.getX() - pommi.getLeveys()/2
                && x < pommi.getX() + pommi.getLeveys()/2 + 1
                && y >= pommi.getY() - pommi.getKorkeus()/2
                && y < pommi.getY() + pommi.getKorkeus()/2 + 1) {
                this.pisteet += pommi.otaSaadutPisteet();
                havitaPommi(pommi);
                System.out.println(this.pisteet);
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

    public int getElama() {
        return elama;
    }

    public int getPisteet() {
        return pisteet;
    }
    
    
    // testejä varten
    
    public void lisaaLaaseri(Laaseri laaseri) {
        this.laaserit.add(laaseri);
    }

    public void setNopeus(int nopeus) {
        this.nopeus = nopeus;
    }

    public int getNopeus() {
        return nopeus;
    }

    public boolean isSeuraavaHorisontaalinen() {
        return seuraavaHorisontaalinen;
    }

    public int getPaivitysTilanne() {
        return paivitysTilanne;
    }
    
    
}
