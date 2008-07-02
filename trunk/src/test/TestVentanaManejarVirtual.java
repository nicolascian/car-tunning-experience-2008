package test;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import modelo.Pista;
import vista.imagenAuto.PanelRecorrido;
import vista.imagenTramo.Posicion;
import vista.ventanas.PanelCarril;
import vista.ventanas.PanelDeInformacion;

import junit.framework.TestCase;

public class TestVentanaManejarVirtual extends JFrame implements Observer {
	
    
    private modelo.Virtual virtual;
	private PanelCarril panelVirtual;
    
    public void update(Observable arg0, Object arg1) {
    	panelVirtual.actualizarVelocidad(virtual.getAuto().getVelocidad());
	}
	
	
	
	
	//constructor
	public TestVentanaManejarVirtual(modelo.Virtual virtual, Pista pista){
		this.setResizable(false);
		this.virtual  = virtual;
		this.setSize(1000, 620);
		this.setTitle("Manejar - Car Tunnning Experience 2008");
		this.setLocationRelativeTo(null); //centrada
		this.setLayout(null);
		//virtual
		Dimension dimensionPanel=new Dimension((int)(this.getSize().width*0.8),(int)(this.getSize().height*.846));
		this.panelVirtual=PanelCarril.createPanelCarrilVistaAutoDesdeAtras(dimensionPanel,
				               new Posicion(0,(int)(getSize().width*0.06452)),virtual);
		this.add(panelVirtual);
		//panel de recorrido
		this.add(new PanelRecorrido(virtual.getNombre(),virtual.getAuto(),pista,
				new Dimension((int)(this.getSize().width*0.8),(int)(getSize().width*0.03226)),
				new Posicion(0,(int)(getSize().width*0.03226)),Color.RED));
		this.setAlwaysOnTop(true);
		this.setVisible(false);
	}	
	
	

}
