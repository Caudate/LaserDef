
package laserdef.domain;

/**
 * Pommeja hallinnoiva luokka, joka toteuttaa kohteen.
 */
public class Pommi extends Kohde {
    
    int aikaRajahdykseen;
    int voimakkuus;
    int pisteet;
    boolean poistetaanko;
    
    public Pommi(int pisteet, int voimakkuus, int aikaRajahdykseen, int x, int y, int korkeus, int leveys) {
        super(x, y, korkeus, leveys);
        this.aikaRajahdykseen = aikaRajahdykseen;
        this.voimakkuus = voimakkuus;
        this.pisteet = pisteet;
        this.poistetaanko = false;
    }
    
    /**
     * Palauttaa ansaitut pisteet, jotka on pommille asetettu ominaispistemäärä
     * + jäljellä olevien tikitysten määrän räjähdykseen.
     * @return 
     */
    public int otaSaadutPisteet() {
        return this.pisteet + this.aikaRajahdykseen;
    }
    
    /**
     * Vähentää aikaa räjähdykseen.
     */
    public void tikita() {
        this.aikaRajahdykseen--;
    }
    
    /**
     * Vastaa kysymykseen, onko pommin aika räjähtää. Räjähtää kun aikaa räjähdykseen
     * on jäljellä alle 1.
     * @return 
     */
    public boolean rajahtaakoPommi() {
        if (this.aikaRajahdykseen < 1) {
            return true;
        }
        return false;
    }
    
    public boolean isPoistetaanko() {
        return poistetaanko;
    }

    public void setPoistetaanko(boolean poistetaanko) {
        this.poistetaanko = poistetaanko;
    }

    public int getAikaRajahdykseen() {
        return aikaRajahdykseen;
    }

    public int getVoimakkuus() {
        return voimakkuus;
    }
    
}
