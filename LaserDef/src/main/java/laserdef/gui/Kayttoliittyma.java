
package laserdef.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import laserdef.peli.Peli;


public class Kayttoliittyma implements Runnable {

    private Peli peli;
    private Piirtoalusta piirturi;
    private JFrame frame;
    
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }
    
    @Override
    public void run() {
        this.frame = luoFrame("LaserDef", peli.getLeveys(), peli.getKorkeus());
        this.frame.setVisible(true);
        this.looppaa();
    }
    
    public JFrame luoFrame(String otsikko, int leveys, int korkeus) {
        JFrame frame1 = new JFrame(otsikko);
        frame1.setPreferredSize(new Dimension(leveys, korkeus));
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame1.getContentPane());
        frame1.pack();
        return frame1;
    }
    
    public void luoKomponentit(Container container) {
        this.piirturi = new Piirtoalusta(this.peli);
        container.add(this.piirturi);
    }
    
    public void looppaa() {
        long initialTime = System.nanoTime();
        final double pelinPaivitysAika = 1000000000 / 50;
        final double framenPaivitysAika = 1000000000 / 50;
        double deltaPelinPaivitys = 0, deltaFramenPaivitys = 0;
        int framet = 0, tikit = 0;
        long timer = System.currentTimeMillis();

            while (true) {

                long currentTime = System.nanoTime();
                deltaPelinPaivitys += (currentTime - initialTime) / pelinPaivitysAika;
                deltaFramenPaivitys += (currentTime - initialTime) / framenPaivitysAika;
                initialTime = currentTime;

                    if (deltaPelinPaivitys >= 1) {
                        this.peli.paivita();
                        tikit++;
                        deltaPelinPaivitys--;
                    }

                    if (deltaFramenPaivitys >= 1) {
                        this.piirturi.paivita();
                        framet++;
                        deltaFramenPaivitys--;
                    }

                    if (System.currentTimeMillis() - timer > 1000) {
                        if (true) {
                            System.out.println(String.format("UPS: %s, FPS: %s", tikit, framet));
                        }
                        framet = 0;
                        tikit = 0;
                        timer += 1000;
                    }
            }   
    }
}
