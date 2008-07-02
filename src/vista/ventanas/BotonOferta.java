/**
 * 
 */
package vista.ventanas;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import modelo.componente.Componente;
import vista.imagenAuto.imagenesDeComponentes.*;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
/**
 * @author Usuario
 *
 */
public class BotonOferta extends JButton {

    DatoClase dato=null;
	
	Componente componente=null;

	VentanaTaller ventana=null;
	
	public BotonOferta(Componente componente,DatoClase dato,VentanaTaller ventana){
		this.ventana=ventana;
		this.dato=dato;
		this.componente=componente;
		this.setText(dato.getNombre());
		this.setIcon(dato.getIcono());
		this.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonComponente();
			}});
		this.setVerticalTextPosition(AbstractButton.CENTER);
	    this.setHorizontalTextPosition(AbstractButton.LEFT);
   }

	public void pressBotonComponente(){
		ventana.pressBotonOferta(componente, dato);
	}
	
}
