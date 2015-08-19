
package laserdef.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import laserdef.peli.Peli;


public class HiirenKuuntelija extends MouseAdapter {
    
    Peli peli;

    public HiirenKuuntelija(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("PAINALLUS HAVAITTU");
        this.peli.hiirellaPainettu(e.getX(), e.getY());
    }
}
