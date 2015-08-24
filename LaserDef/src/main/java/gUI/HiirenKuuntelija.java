
package gUI;

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
        System.out.println("X: " + e.getX() + " Y: " + e.getY());
        this.peli.painettu(e.getX(), e.getY());
    }
}
