
package laserdef.domain;

import laserdef.domain.Pommi;
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
        this.pommi = new Pommi(1, 1, 1, 0, 0, 5, 5);
    }
    
    @Test
    public void luotuLaaseriOnOlemassa() {
        assertTrue(this.pommi!=null);   
    }
    
    @Test
    public void otaSaadutPisteetPalauttaaOikein() {
        assertEquals(2, this.pommi.otaSaadutPisteet());
    }
    
}
