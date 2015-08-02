
package laserdef.domain;


public class Pommi extends Kohde {
    
    int aikaRajahdykseen;
    int voimakkuus;
    int pisteet;
    
    public Pommi(int pisteet, int voimakkuus, int aikaRajahdykseen, int x, int y, int korkeus, int koko) {
        super(x, y, korkeus, koko);
        this.aikaRajahdykseen = aikaRajahdykseen;
        this.voimakkuus = voimakkuus;
        this.pisteet = pisteet;
    }
    
    public int otaPisteet() {
        return this.pisteet + this.aikaRajahdykseen;
    }
    
    
}
