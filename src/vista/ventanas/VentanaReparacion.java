/**
 * 
 */
package vista.ventanas;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.*;
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
	
	private JTextArea infoReparacion=null;
	
    public VentanaReparacion(Componente componente,VentanaTaller ventana) {
		this.setTitle("Reparacion De Componente");
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
	       try{ 
	    	progress.setValue(deslizadora.getValue() );
	        infoReparacion.setText((Componente.calcularCostoReparacion(getComponente(),
	        	 (int)Math.round(deslizadora.getValue()-getComponente().getEstado()))).toStringConUnidades());
	       }catch(NullPointerException e){};
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
        this.infoReparacion=new JTextArea();
		this.infoReparacion.setBackground(Color.black);
		this.infoReparacion.setForeground(this.botonReparar.getBackground());
		this.infoReparacion.setEditable(false);
		this.infoReparacion.setVisible(true);
		this.add(this.infoReparacion);
        
	  }	
      
      public void pressBotonReparar(){
    	 this.ventanaTaller.getTaller().reparar(componente,(int)Math.round(deslizadora.getValue()-
    			                                   componente.getEstado()));
    	 this.ventanaTaller.getPanelComponente().actualizarComponente();
    	 this.ventanaTaller.refrescarPanelInfo();
    	 this.ventanaTaller.setVisible(true);
    	 this.dispose();
      }

	/**
	 * @return the progress
	 */
	public JProgressBar getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(JProgressBar progress) {
		this.progress = progress;
	}

	/**
	 * @return the deslizadora
	 */
	public JSlider getDeslizadora() {
		return deslizadora;
	}

	/**
	 * @param deslizadora the deslizadora to set
	 */
	public void setDeslizadora(JSlider deslizadora) {
		this.deslizadora = deslizadora;
	}

	/**
	 * @return the ventanaTaller
	 */
	public VentanaTaller getVentanaTaller() {
		return ventanaTaller;
	}

	/**
	 * @param ventanaTaller the ventanaTaller to set
	 */
	public void setVentanaTaller(VentanaTaller ventanaTaller) {
		this.ventanaTaller = ventanaTaller;
	}

	/**
	 * @return the botonReparar
	 */
	public JButton getBotonReparar() {
		return botonReparar;
	}

	/**
	 * @param botonReparar the botonReparar to set
	 */
	public void setBotonReparar(JButton botonReparar) {
		this.botonReparar = botonReparar;
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
	 * @return the infoReparacion
	 */
	public JTextArea getInfoReparacion() {
		return infoReparacion;
	}

	/**
	 * @param infoReparacion the infoReparacion to set
	 */
	public void setInfoReparacion(JTextArea infoReparacion) {
		this.infoReparacion = infoReparacion;
	}
    
}
