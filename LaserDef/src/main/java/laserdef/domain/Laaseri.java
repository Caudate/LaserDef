
package laserdef.domain;

import java.util.ArrayList;
import java.util.List;
import laserdef.laserdef.Suunta;

public class Laaseri {
    private int nopeus;
    private Suunta suunta; 
    private boolean kasvaako;
    private List<LaaserOsa> osat;
    private int x;
    private int y;
    private int paksuus;
    private int palanLeveys = 1;
            
    public Laaseri(Suunta suunta, int nopeus, int x, int y, int paksuus) {
        this.nopeus = nopeus;
        this.suunta = suunta;
        this.x = x;
        this.y = y;
        this.paksuus = paksuus;
        this.kasvaako = true;
        this.osat = new ArrayList<LaaserOsa>();
        if(suunta == Suunta.ALAS || suunta == Suunta.YLOS) {
            this.osat.add(new LaaserOsa(x, y, this.palanLeveys, paksuus));
        } else if (suunta == Suunta.VASEN || suunta == Suunta.OIKEA) {
            this.osat.add(new LaaserOsa(x, y, paksuus, this.palanLeveys));
        }
    }
    
    public boolean osuuko(Kohde kohde) {
        if (this.osat.get(this.osat.size()-1).getX() == kohde.getX() && this.osat.get(this.osat.size()-1).getY() == kohde.getY()) {
            return true;
        }
        return false;
    }

    public void setKasvaako(boolean kasvaako) {
        this.kasvaako = kasvaako;
    }
    
    public void kasva() {
        if (suunta == Suunta.ALAS) {
            this.y--;
            this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
        } else if (suunta == Suunta.YLOS) {
            this.y++;
            this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
        } else if (suunta == Suunta.VASEN) {
            this.x++;
            this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
        } else if (suunta == Suunta.OIKEA) {
            this.x--;
            this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
        } 
    }
}
