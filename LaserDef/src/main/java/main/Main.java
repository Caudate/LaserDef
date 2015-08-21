
package main;

import gUI.Kayttoliittyma;
import peli.Peli;


public class Main {

 
    public static void main(String[] args) {
        Peli laserDef = new Peli(600, 600, 8);
        Kayttoliittyma kayttis = new Kayttoliittyma(laserDef);
        kayttis.run();
    }
}
