/**
 * 
 */
package vista.ventanas;

import java.awt.Dimension;
import java.util.*;
import java.awt.BorderLayout;
import modelo.*;
import javax.swing.JFrame;
import vista.imagenTramo.Posicion;
import control.Usuario;
import javax.swing.JWindow;
import vista.ventanas.PanelDeInformacion;
import vista.imagenAuto.PanelRecorrido;
import java.awt.Color;
/**
 * @author Usuario
 *usuario.getAuto().getVelocidad()
 */ 
public class VentanaCarrera extends JFrame implements Observer{
	
    private PanelCarril panel=null;
	
    private modelo.Usuario usuario = null;
    
	public void update(Observable arg0, Object arg1) {
		panel.actualizarVelocidad(usuario.getAuto().getVelocidad());
	}
	
	public VentanaCarrera(modelo.Usuario usuario, modelo.Virtual virtual, Pista pista){
		this.setResizable(false);
		this.usuario  = usuario;
		this.setSize(1000, 620);
		this.setLocationRelativeTo(null); //centrada
		this.setLayout(null);
		Dimension dimensionPanel=new Dimension((int)(this.getSize().width*0.8),this.getSize().height);
		this.panel=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(dimensionPanel,new Posicion(0,20), 
				                                                    usuario);
		this.add(panel);
		this.add(new PanelDeInformacion(new Dimension(200,600),new Posicion(800,0),usuario ));
		
		this.add(new PanelRecorrido(usuario.getNombre(),usuario.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),20),
				new Posicion(),Color.RED));
		this.setAlwaysOnTop(true);
		this.setVisible(false);
		
	}

}
