
package laserdef.domain;

import java.awt.Color;
import java.util.ArrayList;
import laserdef.domain.LaaserOsa;
import laserdef.domain.Laaseri;
import laserdef.laserdef.Suunta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaaseriTest {
    
    
    Laaseri laaseriYlos;
    Laaseri laaseriAlas;
    Laaseri laaseriOikea;
    Laaseri laaseriVasen;
    LaaserOsa osa;
    
    @Before
    public void setVoid() {
        this.laaseriYlos = new Laaseri(Suunta.YLOS, Color.gray, 1, 0, 0, 1);
        this.laaseriAlas = new Laaseri(Suunta.ALAS, Color.gray, 1, 0, 0, 1);
        this.laaseriOikea = new Laaseri(Suunta.OIKEA, Color.gray, 1, 0, 0, 1);
        this.laaseriVasen = new Laaseri(Suunta.VASEN, Color.gray, 1, 0, 0, 1);
        this.osa = new LaaserOsa(0, 0, 1, 1);
    }
    
    @Test
    public void luotuLaaseriOnOlemassa() {
        assertTrue(this.laaseriYlos!=null);   
    }
    
    @Test
    public void osuukoPalauttaaTrueKunOsuu() {
        assertTrue(this.laaseriYlos.osuuko(this.osa));
    }
    
    @Test
    public void osuukoPalauttaaFalseKunEiOsu() {
        LaaserOsa osa2 = new LaaserOsa(1, 1, 1, 1);
        assertFalse(this.laaseriYlos.osuuko(osa2));
    }
    
    @Test
    public void kasvaKasvattaaLaaseria() {
        this.laaseriYlos.kasva();
        assertEquals(2, this.laaseriYlos.getOsat().size());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanXKoordinaattiinYlosLaaseria() {
        this.laaseriYlos.kasva();
        assertEquals(0, this.laaseriYlos.getX());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanXKoordinaattiinAlasLaaseria() {
        this.laaseriAlas.kasva();
        assertEquals(0, this.laaseriAlas.getX());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanXKoordinaattiinVasenLaaseria() {
        this.laaseriVasen.kasva();
        assertEquals(-2, this.laaseriVasen.getX());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanXKoordinaattiinOikeaLaaseria() {
        this.laaseriOikea.kasva();
        assertEquals(2, this.laaseriOikea.getX());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanYKoordinaattiinYlosLaaseria() {
        this.laaseriYlos.kasva();
        assertEquals(-2, this.laaseriYlos.getY());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanYKoordinaattiinAlasLaaseria() {
        this.laaseriAlas.kasva();
        assertEquals(2, this.laaseriAlas.getY());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanYKoordinaattiinVasenLaaseria() {
        this.laaseriVasen.kasva();
        assertEquals(0, this.laaseriVasen.getY());
    }
    
    @Test
    public void kasvaKasvattaaOikeaanYKoordinaattiinOikeaLaaseria() {
        this.laaseriOikea.kasva();
        assertEquals(0, this.laaseriOikea.getY());
    }
}
