
package domain;

import domain.Pommi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PommiTest {
    
    Pommi pommi;
    
    @Before
    public void setVoid() {
        this.pommi = new Pommi(1, 1, 10, 0, 0, 5, 5);
    }
    
    @Test
    public void luotuLaaseriOnOlemassa() {
        assertTrue(this.pommi!=null);   
    }
    
    @Test
    public void otaSaadutPisteetPalauttaaOikein() {
        assertEquals(11, this.pommi.otaSaadutPisteet());
    }
    
    @Test
    public void tikitaVahentaaAikaa() {
        this.pommi.tikita();
        assertEquals(9, this.pommi.getAikaRajahdykseen());
    }
    
    @Test
    public void rajahtaakoPommiPalauttaaFalseKunAikaaOnJaljella() {
        for(int i = 0; i < 9; i++) {
            pommi.tikita();
        }
        assertFalse(this.pommi.rajahtaakoPommi());
    }
    
    @Test
    public void rajahtaakoPommiPalauttaaTrueAikaRajahdykseen0() {
        for(int i = 0; i < 10; i++) {
            pommi.tikita();
        }
        assertTrue(this.pommi.rajahtaakoPommi());
    }
    
    
    //Getterit ja Setterit
    
    @Test
    public void isPoistetaankoPalauttaaOikein() {
        assertFalse(pommi.isPoistetaanko());
    }
    
    @Test
    public void getAikaRajahdukseenPalauttaaOikein() {
        assertEquals(10, this.pommi.getAikaRajahdykseen());
    }
    
    @Test
    public void setPoistetaankoAsettaaOikein() {
        pommi.setPoistetaanko(true);
        assertTrue(pommi.isPoistetaanko());
    }
}
