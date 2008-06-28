/**
 * 
 */
package vista.ventanas;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Usuario
 *
 */
public class PanelMenuPrincipal extends JPanel {

	
	private VistaVentana vistaVentana=null;
	
	
	public PanelMenuPrincipal(VistaVentana vistaVetana){
		JButton botonManejar=new JButton();  //boton para manejar solo
		botonManejar.setText("Manejar");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonManejar();
			}
		});
		JButton botonCarrera=new JButton();  //boton para hacer una carrera
		botonManejar.setText("Carrera");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCarrera();
			}
		});
		JButton botonAuto=new JButton();  //boton para elegir un auto
		botonManejar.setText("Auto");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonAuto();
			}
		});
		JButton botonPista=new JButton();  //boton para elegir una pista
		botonManejar.setText("Pista");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonPista();
			}
		});
		JButton botonGuardar=new JButton();  //boton encargado de la persistencia
		botonManejar.setText("Guardar");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonGuardar();
			}
		});
		JButton botonOpciones=new JButton();  //boton para ver las opciones
		botonManejar.setText("Opciones");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonOpciones();
			}
		});
		JButton botonCreditos=new JButton();  //boton para ver los creditos
		botonManejar.setText("Creditos");
		botonManejar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonCreditos();
			}
		});
		
	}
	public void pressBotonManejar(){
		vistaVentana.Manejar();
	}
	public void pressBotonCarrera(){
		vistaVentana.Carrera();
	}
	public void pressBotonAuto(){
		vistaVentana.Auto();
	}
	public void pressBotonPista(){
		vistaVentana.Pista();
	}
	public void pressBotonGuardar(){
		vistaVentana.Guardar();
	}
	public void pressBotonOpciones(){
		vistaVentana.Opciones();
	}
	public void pressBotonCreditos(){
		vistaVentana.Creditos();
	}
	
}
