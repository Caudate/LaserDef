
package domain;

/**
 * Abstrackti luokka, joka antaa pelin kohteelle sijainnin ja koon.
 */

public abstract class Kohde {
    private int x;
    private int y;
    private int korkeus;
    private int leveys;

    public Kohde(int x, int y, int korkeus, int leveys) {
        this.x = x;
        this.y = y;
        this.korkeus = korkeus;
        this.leveys = leveys;
    }
    
    /**
     * Asettaa kohteen X koordinaatin.
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Palauttaa kohteen X koordinaatin.
     * @return X
     */
    public int getX() {
        return x;
    }
 
    /**
     * Asettaa kohteen Y koordinaatin. 
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
 
    /**
     * Palauttaa kohteen X koordinaatin.
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * Asettaa kohteen korkeuden.
     * @param korkeus 
     */
    public void setKorkeus(int korkeus) {
            this.korkeus = korkeus;
    }
    
    /**
     * Palauttaa kohteen korkeuden.
     * @return korkeus
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Asettaa kohteen leveyden.
     * @param leveys 
     */
    public void setLeveys(int leveys) {
            this.leveys = leveys;
    }

    /**
     * Palauttaa kohteen leveyden.
     * @return leveys
     */
    public int getLeveys() {
        return leveys;
    }
}
