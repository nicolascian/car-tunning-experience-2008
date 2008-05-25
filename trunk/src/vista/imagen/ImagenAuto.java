
package vista.imagen;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class ImagenAuto extends Component {
          
    BufferedImage img;
    JFrame frame;
    
    public ImagenAuto(JFrame ventanaJuego) {
    	this.frame= ventanaJuego;
       try {
           img = ImageIO.read(new File("alfaromeo.png"));
       } catch (IOException e) {
       }
    }
    
    public void paint(Graphics g) {
        g.drawImage(img, (frame.getWidth()-img.getWidth(null))/2, (frame.getHeight()-img.getHeight(null))-100, null);
    }

    public void update(Graphics g){
    	paint(g);
    }
    
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }


}

