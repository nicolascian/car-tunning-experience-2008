package vista.ventanas;
import java.awt.Color;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import modelo.componente.Componente;
import vista.imagenAuto.imagenesDeComponentes.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class BotonComponente extends JButton {

	DatoClase dato=null;
	
	Componente componente=null;

	VentanaTaller ventana=null;
	
	public BotonComponente(Componente componente,DatoClase dato,VentanaTaller ventana){
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
	    if(componente.getEstado()<=0)
	    	this.setBackground(Color.red);
   }

	public void pressBotonComponente(){
		ventana.pressBotonComponente(componente, dato);
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
