
package laserdef.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import laserdef.domain.Laaseri;
import laserdef.peli.Peli;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    private BufferedImage taustaKuva;
    
    public Piirtoalusta(Peli peli) {
        this.peli = peli;
        try {                
          this.taustaKuva = ImageIO.read(new File("res/paint.jpg"));
       } catch (IOException ex) {
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(this.taustaKuva, 0, 0, this.peli.getLeveys(), this.peli.getKorkeus(), null);
        for (Laaseri laaseri : this.peli.getLaaserit()) {
            g.setColor(laaseri.getVari());
            this.piirraLaaserit(g);
        }
    }
    
    @Override
    public void paivita() {
        this.repaint();
    }
    
    public void piirraLaaserit(Graphics g) {
        for (Iterator<Laaseri> iterator = this.peli.getLaaserit().iterator(); iterator.hasNext();){
            Laaseri laaser = iterator.next();
            if (!laaser.isPoistetaanko()) {
                for (int i = 0; i < laaser.getOsat().size(); i++) {
                    g.fill3DRect(laaser.getOsat().get(i).getX(), laaser.getOsat().get(i).getY(), laaser.getOsat().get(i).getLeveys(), laaser.getOsat().get(i).getKorkeus(), true);
                }
            }
        }
    }
}
