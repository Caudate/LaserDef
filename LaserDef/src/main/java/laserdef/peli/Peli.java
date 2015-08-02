
package laserdef.peli;

import java.util.ArrayList;
import java.util.List;
import laserdef.domain.Laaseri;
import laserdef.domain.Seina;

public class Peli {
    
    private int nopeus;
    private int korkeus;
    private int leveys;
    private int laserMaara;
    private List laaserit;
    private List seinat;

    public Peli(int laserMaara, int nopeus, int korkeus, int leveys) {
        this.nopeus = nopeus;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.laserMaara = laserMaara;
        this.laaserit = new ArrayList<Laaseri>();
        this.seinat = new ArrayList<Seina>();
    }
    
    
}
