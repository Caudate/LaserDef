
package laserdef.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import laserdef.laserdef.Suunta;

public class Laaseri {
    private int nopeus;
    private Suunta suunta; 
    private boolean kasvaako;
    private boolean poistetaanko;
    private List<LaaserOsa> osat;
    private int x;
    private int y;
    private int paksuus;
    private int palanLeveys = 1;
    private Color vari;
            
    public Laaseri(Suunta suunta, Color vari, int nopeus, int x, int y, int paksuus) {
        this.nopeus = nopeus;
        this.suunta = suunta;
        this.vari = vari;
        this.x = x;
        this.y = y;
        this.paksuus = paksuus;
        this.kasvaako = true;
        this.poistetaanko = false;
        this.osat = new ArrayList<LaaserOsa>();
        this.kasva();
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
        if (this.kasvaako) {
            for (int i=0; i < this.nopeus; i++) {
                if (this.suunta == Suunta.ALAS) {
                    this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
                    this.y++;
                } else if (this.suunta == Suunta.YLOS) {
                    this.osat.add(new LaaserOsa(this.x, this.y, this.palanLeveys, this.paksuus));
                    this.y--;
                } else if (this.suunta == Suunta.VASEN) {
                    this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
                    this.x--;
                } else if (this.suunta == Suunta.OIKEA) {
                    this.osat.add(new LaaserOsa(this.x, this.y, this.paksuus, this.palanLeveys));
                    this.x++;
                } 
            }
        }
    }
    
    public List<LaaserOsa> getOsat() {
        return this.osat;
    }

    public Color getVari() {
        return vari;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isKasvaako() {
        return kasvaako;
    }

    public boolean isPoistetaanko() {
        return poistetaanko;
    }

    public void setPoistetaanko(boolean poistetaanko) {
        this.poistetaanko = poistetaanko;
    }

    public Suunta getSuunta() {
        return suunta;
    }
}
