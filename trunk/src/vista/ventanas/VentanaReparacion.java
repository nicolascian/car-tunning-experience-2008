/**
 * 
 */
package vista.ventanas;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import modelo.componente.Componente;
/**
 * @author Usuario
 *
 */
public class VentanaReparacion extends JFrame {
	private JProgressBar progress = new JProgressBar();
	
	private JSlider deslizadora = new JSlider( JSlider.HORIZONTAL,0,100,60 );
    
	private VentanaTaller ventanaTaller=null;
	
	private JButton botonReparar=null;
	
	private Componente componente=null;
	
    public VentanaReparacion(Componente componente,VentanaTaller ventana) {
		this.setTitle("Ventana Reparacion");
    	JFrame.setDefaultLookAndFeelDecorated(false);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(400,150));
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
			    ventanaTaller.setVisible(true);
				dispose();
			}
		});
		this.ventanaTaller=ventana;
	    this.componente=componente;
		setLayout( new GridLayout(2,1) );
	    add( progress );
	    deslizadora.setValue((int)componente.getEstado());
	    deslizadora.setPaintTicks( true );
	    deslizadora.setMajorTickSpacing( 20 );
	    deslizadora.setMinorTickSpacing( 5 );
	    deslizadora.setBorder( new TitledBorder("Porcenje De Reparacion") );
	    deslizadora.addChangeListener( new ChangeListener() {
	      public void stateChanged( ChangeEvent evt ) {
	        progress.setValue(deslizadora.getValue() );
	      }
	    } );
	    add( deslizadora );
	    deslizadora.setMinimum((int)componente.getEstado());
	    deslizadora.setValue(deslizadora.getMinimum());
        progress.setStringPainted(true);
        this.botonReparar=new JButton("Reparar");
        botonReparar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonReparar();
			}});
        botonReparar.setVisible(true);
        this.add(botonReparar);
	  }	
      
      public void pressBotonReparar(){
    	 this.ventanaTaller.getTaller().reparar(componente,(int)(deslizadora.getValue()-
    			                                   componente.getEstado()));
    	 this.ventanaTaller.getPanelComponente().actualizarComponente();	 
    	 this.ventanaTaller.setVisible(true);
    	 this.dispose();
      }
    
}
