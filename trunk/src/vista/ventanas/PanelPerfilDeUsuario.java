/**
 * 
 */
package vista.ventanas;

import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Usuario
 *
 */
public class PanelPerfilDeUsuario extends JPanel {
	
	private JButton botonNuevoUsuario = null;  //boton para crear usuario nuevo
	
	private JButton botonCargarUsuario = null;  //boton para cargar un usuario previamente guardado
	
	private VistaVentana vistaVentana=null; 
		
	/**
	 * 
	 */
	public PanelPerfilDeUsuario(VistaVentana vistaVentana) {
		botonNuevoUsuario = new JButton();
		botonNuevoUsuario.setText("Crear Usuario Nuevo");
		botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonNuevoUsuario();
			}
		});
		botonCargarUsuario = new JButton();
		botonCargarUsuario.setText("Cargar Usuario Nuevo");
		botonCargarUsuario.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCargarUsuario();
			}
		});
		this.setVisible(false);
	}
	
	public void pressBotonNuevoUsuario(){
		vistaVentana.JuegoNuevo();
	}
	
	public void pressBotonCargarUsuario(){
		vistaVentana.CargarJuego();
	}
	
}
