/**
 * 
 */
package vista.ventanas;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
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
import java.awt.event.*;
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
		this.addMouseListener(new MouseListener(){

			/* (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent arg0) {
				getVentana().actualizarTextoInferior(getComponente().toString());
				
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent arg0) {
				getVentana().actualizarTextoInferior("");
			}
			
		});
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

	/**
	 * @return the dato
	 */
	public DatoClase getDato() {
		return dato;
	}

	/**
	 * @param dato the dato to set
	 */
	public void setDato(DatoClase dato) {
		this.dato = dato;
	}

	/**
	 * @return the componente
	 */
	public Componente getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	/**
	 * @return the ventana
	 */
	public VentanaTaller getVentana() {
		return ventana;
	}

	/**
	 * @param ventana the ventana to set
	 */
	public void setVentana(VentanaTaller ventana) {
		this.ventana = ventana;
	}
	
}
