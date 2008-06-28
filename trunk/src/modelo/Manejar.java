package modelo;

import vista.ventanas.VentanaManejar;

public class Manejar implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	public Manejar(modelo.Usuario usuario, Pista pista){
		
		VentanaManejar ventana = new VentanaManejar(usuario, pista);
		
		ventana.addKeyListener(new control.Usuario(usuario.getAuto()));
		
		usuario.getAuto().addObserver(ventana);
		
		usuario.getAuto().ActualizarObservadores();
		
	}

}
