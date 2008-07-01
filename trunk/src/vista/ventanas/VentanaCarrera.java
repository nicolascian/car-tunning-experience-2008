/**
 * 
 */
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
	
    private PanelCarril panelVirtual=null;
    
    private modelo.Usuario usuario = null;
    
    private modelo.Virtual virtual=null;
    
	public void update(Observable arg0, Object arg1) {
	  synchronized(this.usuario.getAuto()){	
		panelUsuario.actualizarVelocidad(usuario.getAuto().getVelocidad());
	  }
	  synchronized(this.virtual.getAuto()){
	    try{
		  panelVirtual.actualizarVelocidad(virtual.getAuto().getVelocidad());
	    }catch(NullPointerException e){
	    	e.printStackTrace();
	    };
	  }
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		this.setResizable(false);
		this.usuario  = usuario;
		this.virtual= virtual;
		this.setSize(1000, 620);
		this.setLocationRelativeTo(null); //centrada
		this.setLayout(null);
		//usuario
		Dimension dimensionPanel=new Dimension((int)(this.getSize().width*0.8),(int)(this.getSize().height*.846));
		this.panelUsuario=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(dimensionPanel,
				               new Posicion(0,(int)(getSize().width*0.06452)),usuario);
		this.add(panelUsuario);
		this.add(new PanelDeInformacion(new Dimension((int)(getSize().width*0.2),(int)(getSize().height)),
				                        new Posicion((int)(getSize().width*0.8),0),
				                        usuario ));
		//panel de recorrido
		this.add(new PanelRecorrido(usuario.getNombre(),usuario.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),(int)(getSize().width*0.03226)),
				new Posicion(0,(int)(getSize().width*0.03226)),Color.RED));
		this.add(new PanelRecorrido(virtual.getNombre(),virtual.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),(int)(getSize().width*0.03226)),
				new Posicion(),Color.BLUE));
		this.setAlwaysOnTop(true);
		this.setVisible(false);
	}

}
