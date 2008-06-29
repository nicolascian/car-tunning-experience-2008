package vista.ventanas;

import vista.imagenTramo.Posicion;

public class VentanaCreditos {

	
	
	public ventanaCreditos() {
	
		this.setSize(800, 600);
		this.setTitle("Carrera");
		this.setLocationRelativeTo(null); //centrada
		this.add(PanelCarril.createPanelCarrilVistaAutoDesdeAtras(this.getSize(),new Posicion(), usuario));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(false);	
	
	}
	
	
}
