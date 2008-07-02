/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import javax.swing.JFrame;

public class VentanaCreditos extends JFrame {

	private JFrame ventanaMenu=null;
	
	public VentanaCreditos(JFrame ventanaMenu) {
		this.ventanaMenu=ventanaMenu;
		
		this.setSize(800, 600);
		this.setTitle("Creditos - Car Tunnning Experience 2008");
		this.setLocationRelativeTo(null); //centrada
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});

    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);	
	
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}
	
	
	
}
