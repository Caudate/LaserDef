
package laserdef.domain;


public class Pommi extends Kohde {
    
    int aikaRajahdykseen;
    int voimakkuus;
    int pisteet;
    
    public Pommi(int pisteet, int voimakkuus, int aikaRajahdykseen, int x, int y, int korkeus, int leveys) {
        super(x, y, korkeus, leveys);
        this.aikaRajahdykseen = aikaRajahdykseen;
        this.voimakkuus = voimakkuus;
        this.pisteet = pisteet;
    }
    
    public int otaSaadutPisteet() {
        return this.pisteet + this.aikaRajahdykseen;
    }
    
    
}
