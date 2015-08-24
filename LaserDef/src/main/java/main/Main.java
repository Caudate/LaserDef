
package main;

import gUI.Kayttoliittyma;
import laserdef.peli.Peli;


public class Main {

 
    public static void main(String[] args) {
        Peli laserDef = new Peli(1020, 800, 5);
        Kayttoliittyma kayttis = new Kayttoliittyma(laserDef);
        kayttis.run();
    }
}
