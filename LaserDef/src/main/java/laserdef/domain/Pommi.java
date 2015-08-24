
package laserdef.domain;


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
    
    public int otaSaadutPisteet() {
        return this.pisteet + this.aikaRajahdykseen;
    }
    
    public void tikita() {
        this.aikaRajahdykseen--;
    }
    
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
