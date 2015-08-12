
package laserdef.laserdef;

import laserdef.gui.Kayttoliittyma;
import laserdef.peli.Peli;


public class Main {

 
    public static void main(String[] args) {
        Peli laserDef = new Peli(600, 600, 1);
        Kayttoliittyma kayttis = new Kayttoliittyma(laserDef);
        kayttis.run();
    }
}
