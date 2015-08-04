
package laserdef.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import laserdef.peli.Peli;


public class Kayttoliittyma implements Runnable {

    private Peli peli;
    private Piirtoalusta piirturi;
    
    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }
    
    @Override
    public void run() {
        JFrame frame = luoFrame("LaserDef", peli.getLeveys(), peli.getKorkeus());
        frame.setVisible(true);
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
        int FPS = 50;
        int ohitettavaAika = 1000 / FPS;
        long seuraavaPaivitys = System.currentTimeMillis();
        long odotusaika = 0;
        boolean onkoPaalla = true;

        while(onkoPaalla) {
            this.peli.paivita();
            this.piirturi.paivita();

            seuraavaPaivitys += ohitettavaAika;
            odotusaika = seuraavaPaivitys - System.currentTimeMillis();
            if(odotusaika >= 0) {
                try {
                    Thread.sleep(odotusaika);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Peli ei päivity tarpeeksi nopeasti");
            }
        }    
    }
}
