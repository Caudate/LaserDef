
package laserdef.domain;

import java.awt.Color;
import laserdef.laserdef.Suunta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaaseriTest {
    
    
    Laaseri laaseriYlos;
    Laaseri laaseriAlas;
    Laaseri laaseriOikea;
    Laaseri laaseriVasen;
    
    @Before
    public void setVoid() {
        this.laaseriYlos = new Laaseri(Suunta.YLOS, Color.gray, 1, 0, 0, 1);
        this.laaseriAlas = new Laaseri(Suunta.ALAS, Color.gray, 1, 0, 0, 1);
        this.laaseriOikea = new Laaseri(Suunta.OIKEA, Color.gray, 1, 0, 0, 1);
        this.laaseriVasen = new Laaseri(Suunta.VASEN, Color.gray, 1, 0, 0, 1);
    }
    
    @Test
    public void luotuLaaseriOnOlemassa() {
        assertTrue(this.laaseriYlos!=null);   
    }
    
    @Test
    public void osuukoPalauttaaTrueKunOsuu() {
        LaaserOsa osa = new LaaserOsa(laaseriAlas.getX(), laaseriAlas.getY(), 1, 1);
        assertTrue(this.laaseriAlas.osuuko(osa));
    }
    
    @Test
    public void osuukoPalauttaaFalseKunEiOsu() {
        LaaserOsa osa = new LaaserOsa(1, 1, 1, 1);
        assertFalse(this.laaseriYlos.osuuko(osa));
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
    
//  Setteri ja getteri testit
    
    @Test
    public void setKasvaakoAsettaaTrue() {
        this.laaseriAlas.setKasvaako(true);
        assertEquals(true, this.laaseriAlas.isKasvaako());
    }
    
    @Test
    public void setKasvaakoAsettaaFalse() {
        this.laaseriAlas.setKasvaako(false);
        assertEquals(false, this.laaseriAlas.isKasvaako());
    }
    
    @Test
    public void getNopeusPalauttaaOikein() {
        assertEquals(1, this.laaseriAlas.getNopeus());
    }
    
    @Test
    public void getVariPalauttaaVarin() {
        assertEquals(Color.gray, this.laaseriAlas.getVari());
    }
    
    @Test
    public void getSuunta() {
        assertEquals(Suunta.ALAS, this.laaseriAlas.getSuunta());
    }
    
    @Test
    public void isKasvaakoPalauttaaOikein() {
        assertEquals(true, this.laaseriAlas.isKasvaako());
    }
    
    @Test
    public void isPoistetaankoPalauttaaOikein() {
        assertEquals(false, this.laaseriAlas.isPoistetaanko());
    }
    
    @Test
    public void setPoistetaankoAsettaaTrue() {
        this.laaseriAlas.setPoistetaanko(true);
        assertEquals(true, this.laaseriAlas.isPoistetaanko());
    }
    
    @Test
    public void setPoistetaankoAsettaaFalse() {
        this.laaseriAlas.setPoistetaanko(false);
        assertEquals(false, this.laaseriAlas.isPoistetaanko());
    }
}
