
package laserdef.peli;

import laserdef.peli.Peli;
import java.awt.Color;
import laserdef.domain.Laaseri;
import laserdef.domain.Pommi;
import main.Suunta;
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
        this.peli.getLaaserit().get(0).setNopeus(2);
        this.peli.lisaaLaaseri(new Laaseri(Suunta.ALAS, Color.gray, 1, 3, 0, 1));
        this.peli.getLaaserit().get(1).setNopeus(1);
        this.peli.lisaaLaaseri(new Laaseri(Suunta.OIKEA, Color.gray, 1, 0, 4, 1));
        this.peli.getLaaserit().get(2).setNopeus(1);
        this.peli.lisaaLaaseri(new Laaseri(Suunta.VASEN, Color.gray, 2, 4, 5, 1));
        this.peli.getLaaserit().get(3).setNopeus(2);
        this.peli.getPommit().add(new Pommi(1, 1, 5, 1, 1, 4, 4));
    }
    
    @Test
    public void peliOnOlemassa() {
        assertTrue(this.peli!=null);
    }
    
    //paivita testit
    
    @Test
    public void paivitaPoistaaPoistettavatLaaserit() {
        peli.getLaaserit().get(0).setPoistetaanko(true);
        peli.paivita();
        assertEquals(4, peli.getLaaserit().size());
    }
    
    @Test
    public void paivitaPoistaaPoistettavatPommit() {
        this.peli.getPommit().get(0).setPoistetaanko(true);
        peli.paivita();
        assertEquals(0, this.peli.getPommit().size());
    }
    
    @Test
    public void paivitaEiArvoLaasereitaKunEiOleAika() {
        this.peli.paivita();
        this.peli.paivita();
        assertEquals(5, this.peli.getLaaserit().size());
    }
    
    @Test
    public void paivitaArpooLaasereitaKunOnAika() {
        this.peli.paivita();
        assertEquals(5, this.peli.getLaaserit().size());
    }
    
    @Test
    public void paivitaKasvattaaPaivitysTilannetta() {
        this.peli.paivita();
        this.peli.paivita();
        assertEquals(2, this.peli.getPaivitysTilanne());
    }
    
    @Test
    public void paivitaKasvattaaLaasereita() {
        this.peli.paivita();
        assertEquals(297, this.peli.getLaaserit().get(0).getY());
    }
    
    @Test
    public void paivitaTikittaaPommeja() {
        this.peli.paivita();
        assertEquals(4, this.peli.getPommit().get(0).getAikaRajahdykseen());
    }
    
    //KasvataLaasereita Testit
    
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
    public void kasvataLaasereitaLuoTormayksessaUudenPommin() {
        int pommienMaaraAlussa = this.peli.getPommit().size();
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(pommienMaaraAlussa + 1, this.peli.getPommit().size());
    }
    
    @Test
    public void kasvataLaasereitaLuoTormayksessaUudenPomminOikeaanXkoordinaattiin() {
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(3, this.peli.getPommit().get(1).getX());
    }
    
    @Test
    public void kasvataLaasereitaLuoTormayksessaUudenPomminOikeaanYkoordinaattiin() {
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(5, this.peli.getPommit().get(1).getY());
    }
    
    @Test
    public void kasvataLaasereitaLuoPommilleOikeanAjanRajahdykseen() {
        for (int i=0; i < 10; i++) {
            this.peli.kasvataLaasereita();
        }
        assertEquals(160 - this.peli.getNopeus()*20, this.peli.getPommit().get(1).getAikaRajahdykseen());
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
    
    //  tikitä pommeja testit
    
    @Test
    public void tikitaPommejaTikittaa() {
        this.peli.tikitaPommeja();
        assertEquals(4, this.peli.getPommit().get(0).getAikaRajahdykseen());
    }
    
    @Test
    public void tikitaPommejaRajayttaaRajahtavat() {
        for(int i = 0; i < 5; i++) {
            this.peli.tikitaPommeja();
        }
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void tikitaPommejaEiRajaytaTurhia() {
        for(int i = 0; i < 4; i++) {
            this.peli.tikitaPommeja();
        }
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void tikitaPommejaVahentaaElamaaJosPommiRajahtaa() {
        int alkuElama = this.peli.getElama();
        for(int i = 0; i < 5; i++) {
            this.peli.tikitaPommeja();
        }
        assertEquals(alkuElama-1, this.peli.getElama());
    }
    
    //OnkoKoordinaatissaPommiTestit
    
    @Test
    public void onkoKoordinaatissaPommiPalauttaaTrueKunOn() {
        assertTrue(peli.onkoKoordinaatissaPommi(1, 1));
    }
    
    @Test
    public void onkoKoordinaatissaPommiPalauttaaFalseKunEiOle() {
        assertFalse(peli.onkoKoordinaatissaPommi(2, 2));
    }
    
    //RajaytaPommi testit
    
    @Test
    public void rajaytaPommiAsettaaPoistetaankoTrue() {
        this.peli.havitaPommi(this.peli.getPommit().get(0));
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void rajaytaPommiPoistaaPomminLaaserin() {
        this.peli.getLaaserit().add(new Laaseri(Suunta.YLOS, Color.gray, 1, 1, 2, 1));
        this.peli.havitaPommi(this.peli.getPommit().get(0));
        assertTrue(this.peli.getLaaserit().get(4).isPoistetaanko());
    }
    
    // poistaPomminLaaseri testit
    
    @Test
    public void poistaPomminLaaseriAsettaaTrue() {
        this.peli.getLaaserit().add(new Laaseri(Suunta.YLOS, Color.gray, 1, 1, 2, 1));
        this.peli.poistaPomminLaaseri(this.peli.getPommit().get(0));
        assertTrue(this.peli.getLaaserit().get(4).isPoistetaanko());
    }
    
    @Test
    public void poistaPomminLaaseriEiAsetaKunEriX() {
        this.peli.getLaaserit().add(new Laaseri(Suunta.YLOS, Color.gray, 1, 2, 2, 1));
        this.peli.poistaPomminLaaseri(this.peli.getPommit().get(0));
        assertFalse(this.peli.getLaaserit().get(4).isPoistetaanko());
    }
    
    @Test
    public void poistaPomminLaaseriEiAsetaKunEriY() {
        this.peli.getLaaserit().add(new Laaseri(Suunta.YLOS, Color.gray, 1, 1, 1, 1));
        this.peli.poistaPomminLaaseri(this.peli.getPommit().get(0));
        assertFalse(this.peli.getLaaserit().get(4).isPoistetaanko());
    }
    
    // PoistaPoistettavatPommit testit
    
    @Test
    public void poistaPoistettavatPommitPoistaaOikeat() {
        this.peli.getPommit().add(new Pommi(1, 1, 1, 2, 2, 1, 1));
        this.peli.getPommit().get(0).setPoistetaanko(true);
        this.peli.poistaPoistettavatPommit();
        assertEquals(1, peli.getPommit().size());
    }
    
    //PoistaPoistettavatLaaseritTestit
    
    @Test
    public void poistaPoistettavatLaaseritPoistaaOikeanMaaran() {
        peli.getLaaserit().get(0).setPoistetaanko(true);
        peli.getLaaserit().get(2).setPoistetaanko(true);
        peli.poistaPoistettavatLaaserit();
        assertEquals(2, peli.getLaaserit().size());
    }
    
    @Test
    public void poistaPoistettavatLaaseritEiPoistaKunEiOlePoistettavaa() {
        peli.poistaPoistettavatLaaserit();
        assertEquals(4, peli.getLaaserit().size());
    }
    
    //lisaaLaaseri Testi
    
    @Test
    public void lisaaLaaseriToimii() {
        Laaseri laaseri = new Laaseri(Suunta.YLOS, Color.gray, 2, 1, this.peli.getKorkeus(), 1);
        this.peli.lisaaLaaseri(laaseri);
        assertEquals(laaseri, this.peli.getLaaserit().get(this.peli.getLaaserit().size()-1));
    }
    
    //ArvoUusiLaaseri testit
    
    @Test
    public void tekeeUudenLaaserin() {
        this.peli.arvoUusiLaaseri();
        assertEquals(5, peli.getLaaserit().size());
    }
    
    @Test
    public void muuttaaSeuraavanLaaserinKulman() {
        boolean alkuperainen = this.peli.isSeuraavaHorisontaalinen();
        this.peli.arvoUusiLaaseri();
        assertEquals(!alkuperainen, this.peli.isSeuraavaHorisontaalinen());
    }
    
    // Painettu testit
    
    @Test
    public void painettuEiHavitaPommiaJosEiOsu() {
        this.peli.painettu(5, 1);
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuEiOtaPisteitaJosEiOsu() {
        this.peli.painettu(5, 1);
        assertEquals(0, this.peli.getPisteet());
    }
    
    @Test
    public void painettuHavittaaPomminJosOsuu() {
        this.peli.painettu(1, 1);
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuOttaaPisteetOsuu() {
        this.peli.painettu(1, 1);
        assertEquals(6,  peli.getPisteet());
    }
    
    @Test
    public void painettuVasenYlaKulmaOsuuPommiin() {
        this.peli.painettu(-1, 3);
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuVasenAlaKulmaOsuuPommiin() {
        this.peli.painettu(-1, -1);
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuOikeaAlaKulmaOsuuPommiin() {
        this.peli.painettu(3, -1);
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuOikeaYlaKulmaOsuuPommiin() {
        this.peli.painettu(3, 3);
        assertTrue(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuEiOsuXLiianIso() {
        this.peli.painettu(4, 1);
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuEiOsuXLiianPieni() {
        this.peli.painettu(-2, 1);
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuEiOsuYLiianPieni() {
        this.peli.painettu(1, -2);
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    
    @Test
    public void painettuEiOsuYLiianIso() {
        this.peli.painettu(1, 4);
        assertFalse(this.peli.getPommit().get(0).isPoistetaanko());
    }
    // Getterit ja Setterit
    
    @Test
    public void getLeveysPalauttaaOikein() {
        assertEquals(300, peli.getLeveys());
    }
    
    @Test
    public void getKorkeusPalauttaaOikein() {
        assertEquals(300, peli.getKorkeus());
    }
    
    @Test
    public void getLaaserienPaksuusPalauttaaOikein() {
        assertEquals(7, this.peli.getLaaserienPaksuus());
    }
    
    @Test
    public void getPommienSivunKokoOikein() {
        assertEquals(50, this.peli.getPommienSivunKoko());
    }
            
}
