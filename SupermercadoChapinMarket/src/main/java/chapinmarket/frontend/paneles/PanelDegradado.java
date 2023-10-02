
package chapinmarket.frontend.paneles;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author CIROSS
 */
public class PanelDegradado extends JPanel {
    
    private Color color1;
    private Color color2;

    public PanelDegradado(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    public PanelDegradado(int r1, int g1, int b1, int r2, int g2, int b2) {
        this.color1 = new Color(r1,g1,b1);
        this.color2 = new Color(r2,g2,b2);
        this.color2 = new Color(0x255075);
    }
    
    public PanelDegradado(int hx1, int hx2) {
//        this.color2 = new Color(0x999999);
        this.color1 = new Color(hx1);
        this.color2 = new Color(hx2);
    }

    public PanelDegradado() {
        this.color1 = Color.DARK_GRAY;
        this.color2 = Color.LIGHT_GRAY;
    }
    
    
    
    @Override
    public void paintComponent(Graphics graph) {
        Graphics2D graphics2D = (Graphics2D) graph;
        int ancho = getWidth();
        int alto = getHeight();
        
        GradientPaint gradientPaint = new GradientPaint(0,0,this.color1,10,alto,this.color2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, ancho, alto);
    }
    
}
