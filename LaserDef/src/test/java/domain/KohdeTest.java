
package domain;

import domain.Kohde;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class KohdeTest {
    
    Kohde kohde;
    
    @Before
    public void setVoid() {
        this.kohde = new Kohde(1, 1, 1, 1) {};
    }
    
    @Test
    public void kohdeOnOlemassa() {
        assertTrue(this.kohde!=null);
    }
    
//    Getterit
    
    @Test
    public void getXPalauttaaOikein() {
        assertEquals(1, this.kohde.getX());
    }
    
    @Test
    public void getYPalauttaaOikein() {
        assertEquals(1, this.kohde.getY());
    }
    
    @Test
    public void getKorkeusPalauttaaOikein() {
        assertEquals(1, this.kohde.getKorkeus());
    }
    
    @Test
    public void getLeveysPalauttaaOikein() {
        assertEquals(1, this.kohde.getLeveys());
    }
    
//    Setterit
    
    @Test
    public void setXAsettaaOikein() {
        this.kohde.setX(5);
        assertEquals(5, this.kohde.getX());
    }
    
    @Test
    public void setYAsettaaOikein() {
        this.kohde.setY(1);
        assertEquals(1, this.kohde.getY());
    }
    
    @Test
    public void setKorkeusAsettaaOikein() {
        this.kohde.setKorkeus(3);
        assertEquals(3, this.kohde.getKorkeus());
    }
    
    @Test
    public void setLeveysAsettaaOikein() {
        this.kohde.setLeveys(3);
        assertEquals(3, this.kohde.getLeveys());
    }
}
