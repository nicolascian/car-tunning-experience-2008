/**
 * 
 */
package vista.ventanas;
import modelo.componente.Componente;
import javax.swing.*;
import vista.imagenTramo.Imagen;
import vista.imagenAuto.imagenesDeComponentes.*;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * @author Usuario
 *
 */
public class PanelComponente extends JPanel {

	private Imagen imagenComponente=null;
	
	private DatoClase dato=null;
	
	private VentanaTaller ventana=null;
	
	public PanelComponente(Componente componente,DatoClase dato,Dimension dimension,
			               VentanaTaller ventana){
		this.setSize(dimension);
		this.dato=dato;
		this.setVisible(true);
		this.ventana=ventana;
	}

	private void actualizarComponente(){
		imagenComponente=new Imagen(dato.getRutaImagen(),new Dimension(100,100),
                new Posicion());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imagenComponente.getImage(),imagenComponente.getPosicion().getX(),
				    imagenComponente.getPosicion().getY(),
				    (int)imagenComponente.getDimension().getWidth(),
				    (int)imagenComponente.getDimension().getHeight(),null);
	}

	public void repaint() {
		paint(this.getGraphics());
	}
		
	public void cerar(){
		
	}
}
