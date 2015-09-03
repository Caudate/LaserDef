
package laserdef.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import main.Suunta;

/**
 * Luokka, joka sisältää Laaserin laaserin hallintaan liittyviä metodeja.
 */
public class Laaseri {
    private int nopeus;
    private Suunta suunta; 
    private boolean kasvaako;
    private boolean poistetaanko;
    private List<LaaserOsa> osat;
    private int x;
    private int y;
    private int paksuus;
    private int palanLeveys = 1;
    private Color vari;
    
    public Laaseri(Suunta suunta, Color vari, int nopeus, int x, int y, int paksuus) {
        this.nopeus = nopeus;
        this.suunta = suunta;
        this.vari = vari;
        this.x = x;
        this.y = y;
        this.paksuus = paksuus;
        this.kasvaako = true;
        this.poistetaanko = false;
        this.osat = new ArrayList<>();
        this.kasva();
    }
    
    
    /**
     * Testaa osuuko Laaserin kärki tiettyyn kohteeseen.
     * @param kohde Kohde johon osumista testataan.
     * @return 
     */
    public boolean osuuko(Kohde kohde) {
        if (this.x == kohde.getX() && this.y == kohde.getY()) {
            return true;
        }
        return false;
    }

    
    /**
     * Muuttaa kasvaako atribuuttia
     * 
     * @param kasvaako miksi kasvaako muutetaan
     */
    public void setKasvaako(boolean kasvaako) {
        this.kasvaako = kasvaako;
    }
    
    /**
     * Kasvattaa laaseria lisäämällä siihen uuden LaaserOsan ja muuttamalla 
     * laaserin kärjen sijaintia. Eri suuntaa kulkevia laasereita kasvatetaan
     * omiin suuntiinsa.
     * 
     * @see laserdef.domain.LaaserOsa
     */
    public void kasva() {
        if (this.kasvaako) {
            if (this.suunta == Suunta.ALAS) {
                this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
                this.y++;
            } else if (this.suunta == Suunta.YLOS) {
                this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
                this.y--;
            } else if (this.suunta == Suunta.VASEN) {
                this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
                this.x--;
            } else if (this.suunta == Suunta.OIKEA) {
                this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
                this.x++;
            } 
        }
    }
    
    /**
     * Palauttaa laaserin osat 
     * @return laaserin osat
     */
    public List<LaaserOsa> getOsat() {
        return this.osat;
    }
    
    /**
     * Palauttaa laaserin värin.
     * @return laaserin vari
     */
    public Color getVari() {
        return vari;
    }

    /**
     * Palauttaa Laaserin kärjen X koordinaatin.
     * @return laaserin kärjen X koordinaatti
     */
    public int getX() {
        return x;
    }

    /**
     * Palauttaa Laaserin kärjen Y koordinaatin.
     * @return laaserin kärjen Y koordinaatti
     */
    public int getY() {
        return y;
    }

    /**
     * Palauttaa Kasvaako artribuutin
     * @return kasvaako
     */
    public boolean isKasvaako() {
        return kasvaako;
    }

    /**
     * Palauttaa Poistetaanko atribuutin
     * @return poistetaanko
     */
    public boolean isPoistetaanko() {
        return poistetaanko;
    }

    /**
     * Asettaa poistetaanko atribuutin
     * @param poistetaanko 
     */
    public void setPoistetaanko(boolean poistetaanko) {
        this.poistetaanko = poistetaanko;
    }

    /**
     * Palauttaa laaserin kasvusuunnan
     * @return suunta
     */
    public Suunta getSuunta() {
        return suunta;
    }

    /**
     * Palauttaa laaserin nopeuden
     * @return nopeus
     */
    public int getNopeus() {
        return nopeus;
    }

    /**
     * Asettaa laaserin nopeuden
     * @param nopeus haluttu nopeus
     */
    public void setNopeus(int nopeus) {
        this.nopeus = nopeus;
    }
}
