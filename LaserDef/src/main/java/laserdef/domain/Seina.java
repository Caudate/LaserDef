
package laserdef.domain;


public class Seina extends Kohde {
    
    private int kunto;
    
    public Seina(int kunto, int x, int y, int korkeus, int koko) {
        super(x, y, korkeus, koko);
        this.kunto = kunto;
    }

    public void tuhoaSeinaa(int maara) {
        this.kunto -= maara;
    }
    
    public void setKunto(int kunto) {
        this.kunto = kunto;
    }

    public int getKunto() {
        return kunto;
    }
    
}
