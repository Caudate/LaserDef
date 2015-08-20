
package gUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import peli.Peli;


public class HiirenKuuntelija extends MouseAdapter {
    
    Peli peli;

    public HiirenKuuntelija(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("PAINALLUS HAVAITTU");
        this.peli.painettu(e.getX(), e.getY());
    }
}
