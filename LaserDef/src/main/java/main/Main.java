
package main;

import kayttoliittyma.Kayttoliittyma;
import laserdef.peli.Peli;


public class Main {

 
    public static void main(String[] args) {
        Peli laserDef = new Peli(600, 600, 6);
        Kayttoliittyma kayttis = new Kayttoliittyma(laserDef);
        kayttis.run();
    }
}
