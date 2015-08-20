
package gUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import domain.Laaseri;
import domain.Pommi;
import peli.Peli;

/**
 * Alusta, joka piirtää kulloisnekin pelitilanteen. 
 * Perii JPanelin ja toteuttaa Runnable rajapinnan.
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private BufferedImage taustaKuva;
    private BufferedImage pommiKuva;
    
    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        try {                
          this.taustaKuva = ImageIO.read(getClass().getResourceAsStream("/paint.jpg"));
          this.pommiKuva = this.teeKuva("/nuke_icon.jpg", this.peli.getPommienSivunKoko(), this.peli.getPommienSivunKoko());
       } catch (IOException ex) {
       }
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
        for (Iterator<Laaseri> iterator = this.peli.getLaaserit().iterator(); iterator.hasNext();){
            Laaseri laaser = iterator.next();
            g2d.setColor(laaser.getVari());
            if (!laaser.isPoistetaanko()) {
                for (int i = 0; i < laaser.getOsat().size(); i++) {
                    g2d.fill3DRect(laaser.getOsat().get(i).getX(), laaser.getOsat().get(i).getY(), laaser.getOsat().get(i).getLeveys(), laaser.getOsat().get(i).getKorkeus(), true);
                }
            }
        }
    }
    
    /**
     * piirtää pelin pommit, jotka eivät ole poistumassa.
     * @param g2d 
     */
    public void piirraPommit(Graphics g2d) {
        for (Iterator<Pommi> iterator = this.peli.getPommit().iterator(); iterator.hasNext();){
            Pommi pommi = iterator.next();
            if (!pommi.isPoistetaanko()) {
                g2d.drawImage(this.pommiKuva, pommi.getX()-pommi.getLeveys()/2, pommi.getY()-pommi.getKorkeus()/2, this);
            }
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
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, leveys, korkeus, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return kuva;
    }
    
}
