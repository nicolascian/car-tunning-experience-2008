/**
 * 
 */
package vista.ventanas;

import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import modelo.componente.Componente;
import vista.imagenAuto.imagenesDeComponentes.*;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import javax.swing.JPanel;
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
	    this.setVisible(true);
   }

	public void pressBotonComponente(){
		ventana.pressBotonOferta(componente, dato);
	}
	
	public static void agregarBotonesComponentesCompatiblesAPanel(Componente componente,JPanel panel,
			             AdministradorDeImagenesYEtiquetasDeComponentes administrador,
			             VentanaTaller ventana){
	  
	  LinkedList<DatoClase> listaDatoClases=administrador.listarDatosRamaDeHerencia(componente.getClass());
	  Iterator<Componente> itComp=ventana.getComponentesEnOfeta().iterator();
	  while(itComp.hasNext()){
		  Componente comp=itComp.next();
		  Iterator<DatoClase> it=listaDatoClases.iterator();
		  while(it.hasNext()){
			  DatoClase dato=it.next();
			  if((dato.getClase()).isInstance(comp)){
				  panel.add(new BotonOferta(comp,administrador.getDatoClase(comp.getClass()),
						    ventana));
			  } 
		  }
	  }
	}
}
