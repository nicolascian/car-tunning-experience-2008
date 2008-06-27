/**
 * 
 */
package vista.ventanas;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 * @author Usuario
 *
 */
public class VentanaSplash extends JWindow {
	
	JProgressBar progress=null;
	
	/**
	 * 
	 */
	public VentanaSplash() {
		progress = new JProgressBar();
		this.add("South",progress);
		this.setSize(400,250);
		this.setLocationRelativeTo(null); //centrada
		this.setAlwaysOnTop(true);
		this.setVisible(true);  //mostramos la ventana
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
		progress.setValue(0);
		progress.setStringPainted(true);// le pongo numeritos al progressBar
	}
	
	public void setProgresoProgressBar(int progreso){
		progress.setValue(progreso);
	}

}
