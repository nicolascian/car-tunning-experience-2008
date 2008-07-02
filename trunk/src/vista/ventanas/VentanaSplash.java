/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Dimension;
import vista.imagenTramo.Imagen;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import vista.imagenTramo.Posicion;
import java.awt.Graphics2D;
import javax.swing.JPanel;
/**
 * @author Usuario
 *
 */
public class VentanaSplash extends JWindow {
	
	JProgressBar progress=null;
	
	private Imagen imagen=null;
	
	/**
	 * 
	 */
	public VentanaSplash() {
		this.setLayout(null);
		progress = new JProgressBar();
		this.add(progress);
		this.setSize(400,260);
		this.setLocationRelativeTo(null); //centrada
		this.setAlwaysOnTop(true);
		this.setVisible(true);  //mostramos la ventana
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
		progress.setValue(0);
		progress.setStringPainted(true);// le pongo numeritos al progressBar
		progress.setBounds(0,240,400,20);
		imagen=new Imagen("src//vista//ventanas//luigi.jpg",new Dimension(400,240),
				          new Posicion());
		JPanel panel=new JPanel(){
			/* (non-Javadoc)
			 * @see java.awt.Container#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				Graphics2D grafico=(Graphics2D)g;
				grafico.drawImage(imagen.getImage(),imagen.getPosicion().getX(),
				          imagen.getPosicion().getY(),imagen.getDimension().width,
				          imagen.getDimension().height,null);
			}
		};
		panel.setBounds(0,0, 400,240);
		panel.setVisible(true);
		this.add("North",panel);
		this.repaint();
	}
	
	public void setProgresoProgressBar(int progreso){
		progress.setValue(progreso);
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
