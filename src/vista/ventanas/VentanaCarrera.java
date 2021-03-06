/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import java.awt.Dimension;
import java.util.*;
import modelo.*;
import javax.swing.JFrame;
import vista.imagenTramo.Posicion;
import vista.ventanas.PanelDeInformacion;
import vista.imagenAuto.PanelRecorrido;
import java.awt.Color;
/**
 * @author Usuario
 *usuario.getAuto().getVelocidad()
 */ 
public class VentanaCarrera extends JFrame implements Observer{
	
    private PanelCarril panelUsuario=null;
	
    private modelo.Usuario usuario = null;
    
    private modelo.Virtual virtual=null;
    
    private PanelRecorrido panelRecorridoVirtual=null;
    
    private PanelRecorrido panelRecorridoUsuario=null;
    
    private PanelDeInformacion panelInfoUsuario=null;
    
    
	public void update(Observable arg0, Object arg1) {
	  synchronized(this.usuario.getAuto()){	
		try{
		  panelUsuario.actualizarVelocidad(usuario.getAuto().getVelocidad());
		}catch(NullPointerException e){};
	  }
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		this.setResizable(false);
		this.usuario  = usuario;
		this.virtual= virtual;
		this.setSize(1000, 620);
		this.setTitle("Carrera - Car Tunnning Experience 2008");
		this.setLocationRelativeTo(null); //centrada
		this.setLayout(null);
		//usuario
		Dimension dimensionPanel=new Dimension((int)(this.getSize().width*0.8),(int)(this.getSize().height*.846));
		this.panelUsuario=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(dimensionPanel,
				               new Posicion(0,(int)(getSize().width*0.06452)),usuario,pista);
		this.add(panelUsuario);
		this.panelInfoUsuario=new PanelDeInformacion(new Dimension((int)(getSize().width*0.2),(int)(getSize().height)),
				                        new Posicion((int)(getSize().width*0.8),0),
				                        usuario );
		add(panelInfoUsuario);
		//panel de recorrido
		this.panelRecorridoUsuario=new PanelRecorrido(usuario.getNombre(),usuario.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),(int)(getSize().width*0.03226)),
				new Posicion(0,(int)(getSize().width*0.03226)),Color.RED);
		
		this.add(this.panelRecorridoUsuario);
		this.panelRecorridoVirtual=new PanelRecorrido(virtual.getNombre(),virtual.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),(int)(getSize().width*0.03226)),
				new Posicion(),Color.BLUE);
		this.add(this.panelRecorridoVirtual);
		this.setAlwaysOnTop(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	try{
		    		panelUsuario.getHiloDeActualizacion().stop();
		    		panelUsuario.setHiloDeActualizacion(null);
		    	}catch(NullPointerException exception){};
		    	panelUsuario.setVisible(false);
		    	panelUsuario=null;
		    	try{
		    		panelRecorridoUsuario.getHiloDeActualizacion().stop();
		    		panelRecorridoUsuario.setHiloDeActualizacion(null);
		    	}catch(NullPointerException exception){};
		    	panelRecorridoUsuario.setVisible(false);
		    	panelRecorridoUsuario=null;
		    	try{
		    		panelRecorridoVirtual.getHiloDeActualizacion().stop();
		    		panelRecorridoVirtual.setHiloDeActualizacion(null);
		    	}catch(NullPointerException exception){};
		    	panelRecorridoVirtual.setVisible(false);
		    	panelRecorridoVirtual=null;
		    }
		});
		this.setVisible(false);
	}

	/**
	 * @return the panelUsuario
	 */
	public PanelCarril getPanelUsuario() {
		return panelUsuario;
	}

	/**
	 * @param panelUsuario the panelUsuario to set
	 */
	public void setPanelUsuario(PanelCarril panelUsuario) {
		this.panelUsuario = panelUsuario;
	}

	/**
	 * @return the panelInfoUsuario
	 */
	public PanelDeInformacion getPanelInfoUsuario() {
		return panelInfoUsuario;
	}

	/**
	 * @param panelInfoUsuario the panelInfoUsuario to set
	 */
	public void setPanelInfoUsuario(PanelDeInformacion panelInfoUsuario) {
		this.panelInfoUsuario = panelInfoUsuario;
	}
	
}
