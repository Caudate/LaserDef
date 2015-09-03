
package kayttoliittyma;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import laserdef.domain.Laaseri;
import laserdef.domain.Pommi;
import java.awt.Color;
import main.Suunta;
import laserdef.peli.Peli;

/**
 * Alusta, joka piirtää kulloisekin pelitilanteen. 
 * Perii JPanelin ja toteuttaa Runnable rajapinnan.
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private BufferedImage taustaKuva;
    private BufferedImage pommiKuva;
    private BufferedImage laaseriPysty;
    private BufferedImage laaseriVaaka;
    
    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        this.addMouseListener(new HiirenKuuntelija(peli));
        this.taustaKuva = this.teeKuva("/taustaKuva_LD.jpg", this.peli.getLeveys(), this.peli.getKorkeus());
        this.pommiKuva = this.teeKuva("/nuke_icon.jpg", this.peli.getPommienSivunKoko(), this.peli.getPommienSivunKoko());
        this.laaseriPysty = this.teeKuva("/laaseri_pysty.png", this.peli.getLaaserienPaksuus(), 1);
        this.laaseriVaaka = this.teeKuva("/laaseri_vaaka.png", 1, this.peli.getLaaserienPaksuus());
    }

    /**
     * Piirtää komponentit.
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.taustaKuva, 0, 0, this.peli.getLeveys(), this.peli.getKorkeus(), null);
        this.piirraElamaPalkki(g2d);
        this.piirraLaaserit(g2d);
        this.piirraPommit(g2d);
    }
    
    /**
     * Piirtää pelin tilanteen uudestaan.
     */
    @Override
    public void paivita() {
        this.repaint();
    }
    
    /**
     * Piirtää pelin laaserit, jotka eivät ole poistumassa.
     * @param g2d 
     */
    public void piirraLaaserit(Graphics2D g2d) {
        for (Iterator<Laaseri> iterator = this.peli.getLaaserit().iterator(); iterator.hasNext();) {
            Laaseri laaser = iterator.next();
            g2d.setColor(laaser.getVari());
            if (!laaser.isPoistetaanko()) {
                for (int i = 0; i < laaser.getOsat().size(); i++) {
                    if (laaser.getSuunta() == Suunta.ALAS || laaser.getSuunta() == Suunta.YLOS) {
                        g2d.drawImage(this.laaseriPysty, laaser.getOsat().get(i).getX(), laaser.getOsat().get(i).getY(), this);
                    } else {
                        g2d.drawImage(this.laaseriVaaka, laaser.getOsat().get(i).getX(), laaser.getOsat().get(i).getY(), this);
                    }
                }
            }
        }
    }
    
    /**
     * piirtää pelin pommit, jotka eivät ole poistumassa.
     * @param g2d 
     */
    public void piirraPommit(Graphics2D g2d) {
        for (Iterator<Pommi> iterator = this.peli.getPommit().iterator(); iterator.hasNext();) {
            Pommi pommi = iterator.next();
            if (!pommi.isPoistetaanko()) {
                g2d.drawImage(this.pommiKuva, pommi.getX() - pommi.getLeveys() / 2, pommi.getY() - pommi.getKorkeus() / 2, this);
            }
        }
    }
    
    /**
     * piirtaa elämä palkin.
     * @param g2d 
     */
    public void piirraElamaPalkki(Graphics2D g2d) {
        for (int i = 0; i < this.peli.getElama(); i++) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(10, i + 10, 13, 1);
        }
    }
    
    /**
     * Hakee annetusta sijainnista kuvan ja palauttaa tämän parametreina
     * annetun kokoisena.
     * @param kuvanSijainti kuvan sijainti
     * @param leveys haluttu kuvan leveys
     * @param korkeus haluttu kuvan korkeus.
     * @return kuva oikean kokoisena.
     */
    public BufferedImage teeKuva(String kuvanSijainti, int leveys, int korkeus) {
        BufferedImage kuva = null;
        try {
            ImageIcon ii = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(kuvanSijainti)));
            kuva = new BufferedImage(leveys, korkeus, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) kuva.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, leveys, korkeus, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return kuva;
    }
    
}
