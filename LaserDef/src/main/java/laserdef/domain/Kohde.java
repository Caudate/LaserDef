
package laserdef.domain;

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
    
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setKorkeus(int korkeus) {
            this.korkeus = korkeus;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setLeveys(int leveys) {
            this.leveys = leveys;
    }

    public int getLeveys() {
        return leveys;
    }
}
