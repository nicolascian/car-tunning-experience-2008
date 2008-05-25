
package vista.imagen;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class ImagenAuto extends Component {
          
    BufferedImage Auto;
    BufferedImage Piso;
    BufferedImage Khm;
    BufferedImage Rpm;
    JFrame Ventana;
    
    public ImagenAuto(JFrame ventanaJuego) {
    	
    	this.Ventana= ventanaJuego;

    	try {
           Auto = ImageIO.read(new File("alfaromeo.png"));
       } catch (IOException e) {
       }
       
       try {
           Piso = ImageIO.read(new File("piso.png"));
       } catch (IOException e) {
       }
       
       try {
           Rpm = ImageIO.read(new File("rpm.png"));
       } catch (IOException e) {
       }
       
       try {
           Khm = ImageIO.read(new File("kmh.png"));
       } catch (IOException e) {
       }
       //Piso = new BufferedImage(2*Auto.getWidth(), Auto.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
    }
    
    public void paint(Graphics g) {
    	g.drawImage(Piso, (Ventana.getWidth()-Piso.getWidth(null))/2, (Ventana.getHeight()-Piso.getHeight(null))-30, null );
    	g.drawImage(Auto, (Ventana.getWidth()-Auto.getWidth(null))/2, (Ventana.getHeight()-Auto.getHeight(null))-100, null);
    	g.drawImage(Khm, 0, 0, null);
    	g.drawImage(Rpm, 0, 128, null);
    }

    public void update(Graphics g){
    	paint(g);
    }
    
    public Dimension getPreferredSize() {
        if (Piso == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(Piso.getWidth(null), Piso.getHeight(null));
       }
    }


}

