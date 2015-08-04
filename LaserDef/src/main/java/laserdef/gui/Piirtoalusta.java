
package laserdef.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import laserdef.domain.LaaserOsa;
import laserdef.domain.Laaseri;
import laserdef.peli.Peli;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Peli peli;
    
    public Piirtoalusta(Peli peli) {
        super.setBackground(Color.WHITE);
        this.peli = peli;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        for (Laaseri laaseri : this.peli.getLaaserit()) {
            g.setColor(laaseri.getVari());
            if (laaseri.isPoistetaanko() == false) {
                for (LaaserOsa osa : laaseri.getOsat()) {
                    g.fillRoundRect(osa.getX(), osa.getY(), osa.getLeveys(), osa.getKorkeus(), 2, 2);
                }
            } else {
                for (LaaserOsa osa : laaseri.getOsat()) {
                    g.clearRect(osa.getX(), osa.getY(), osa.getLeveys(), osa.getKorkeus());
                }
            }
        }
    }
    
    @Override
    public void paivita() {
        this.repaint();
    }
    
}
