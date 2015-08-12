
package laserdef.peli;

import java.awt.Color;
import laserdef.domain.Laaseri;
import laserdef.laserdef.Suunta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PeliTest {
    
    Peli peli;
    
    @Before
    public void setVoid() {
        this.peli = new Peli(300, 300, 1);
        this.peli.lisaaLaaseri(new Laaseri(Suunta.YLOS, Color.gray, 2, 2, this.peli.getKorkeus(), 1));
        this.peli.lisaaLaaseri(new Laaseri(Suunta.ALAS, Color.gray, 1, 3, 0, 1));
        this.peli.lisaaLaaseri(new Laaseri(Suunta.OIKEA, Color.gray, 1, 0, 4, 1));
        this.peli.lisaaLaaseri(new Laaseri(Suunta.VASEN, Color.gray, 2, 4, 5, 1));
    }
    
    @Test
    public void peliOnOlemassa() {
        assertTrue(this.peli!=null);
    }
    
    @Test
    public void kasvataLaasereitaKasvattaaEnsimmaistaLaaseriaOikeaanYkoordinaattiin() {
        this.peli.kasvataLaasereita();
        assertEquals(297, this.peli.getLaaserit().get(0).getY());
    }
    
    @Test
    public void kasvataLaasereitaKasvattaaVaakasuuntaistaLaaseriaOikeaanXkoordinaattiin() {
        this.peli.kasvataLaasereita();
        assertEquals(2, this.peli.getLaaserit().get(0).getX());
    }
    
    @Test
    public void kasvataLaasereitaKasvattaaHorisontaalistaLaaseriaOikeaanXkoordinaattiin() {
        this.peli.kasvataLaasereita();
        assertEquals(2, this.peli.getLaaserit().get(2).getX());
    }
    
    @Test
    public void kasvataLaasereitaKasvattaaHorisontaalistaLaaseriaOikeaanYkoordinaattiin() {
        this.peli.kasvataLaasereita();
        assertEquals(4, this.peli.getLaaserit().get(2).getY());
    }
    
    @Test
    public void kasvataLaasereitaMuuttaaTormayksessaKasvaakoFalseksi() {
        this.peli.lisaaLaaseri(new Laaseri(Suunta.OIKEA, Color.gray, 3, 0, this.peli.getKorkeus() - 5, 1));
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(false, this.peli.getLaaserit().get(0).isKasvaako());
    }
    
    @Test
    public void kasvataLaasereitaEiMuutaKaasvaakoTrueksiJosEiOsu() {
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(true, this.peli.getLaaserit().get(0).isKasvaako());
    }
    
    @Test
    public void kasvataLaasereitaMuuttaaPoistettaviksiYliMenneetLaaserit() {
        for (int i=0; i < 3; i++) {
            this.peli.kasvataLaasereita();
        }
        assertTrue(this.peli.getLaaserit().get(3).isPoistetaanko());
    }
    
    @Test
    public void kasvataLaasereitaEiMuutaPoistettaviksiKeskeneraisiaLaasereita() {
        for (int i=0; i < 3; i++) {
            this.peli.kasvataLaasereita();
        }
        assertFalse(this.peli.getLaaserit().get(2).isPoistetaanko());
    }
    
    
    @Test
    public void pysaytaTormanneetLaaseritPysayttaaTormanneet() {
        
    }
    
    
    @Test
    public void lisaaLaaseriToimii() {
        Laaseri laaseri = new Laaseri(Suunta.YLOS, Color.gray, 2, 1, this.peli.getKorkeus(), 1);
        this.peli.lisaaLaaseri(laaseri);
        assertEquals(laaseri, this.peli.getLaaserit().get(this.peli.getLaaserit().size()-1));
    }
    
    
    
    // Getterit ja Setterit
    
    @Test
    public void getLaaseritPalauttaaOikein() {
    }
    
}
