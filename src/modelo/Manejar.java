package modelo;

import javax.swing.JFrame;

import modelo.exceptions.ExceptionFinPista;
import vista.ventanas.VentanaManejar;

public class Manejar implements Runnable {

	private JFrame ventanaAnterior;
	
	private Pista pista;
	
	private VentanaManejar vista;
		
	private modelo.Usuario usuario;
		
	public Manejar(modelo.Usuario usuario, Pista pista, JFrame ventanaAnterior){
		this.ventanaAnterior = ventanaAnterior;
			this.usuario = usuario;
			this.pista = pista;
			this.vista =  new VentanaManejar(this.usuario, this.pista);
			
			this.vista.addWindowListener(new java.awt.event.WindowAdapter() {
			    public void windowClosing(java.awt.event.WindowEvent e) {
			    	finalizar();
			    }
			});
	}
		
	/**
	 * Metodo que se encarga de inicializar los atributos para la carrera
	*/
	private void incializar(){
	/* setear posiciones de autos en 0, 
	 * inicializar controladores
	 * incializar vistas
	 * setear los observadores
	 * etc
	*/
	   this.usuario.getAuto().setPosicion(0);
	   this.vista.addKeyListener(new control.Usuario(usuario.getAuto()));
	   this.usuario.getAuto().agregarObservador(this.vista);
	   this.usuario.getAuto().ActualizarObservadores();
	   this.vista.setVisible(true);
	}
		
	private void finalizar(){
      //cambiar al menu
	   this.vista.dispose();
	   this.vista=null;
	   this.ventanaAnterior.setVisible(true);
	}
	
	public void run() {
	   this.incializar();
	   boolean enCarrera = true;
	   while(enCarrera){
    	 try{
    		synchronized (this.usuario.getAuto())
			{
    			this.usuario.getAuto().wait(25);
				this.pista.actualizarPosiciones();
				this.usuario.getAuto().Desgastar();
				this.usuario.getAuto().notifyAll();
			}
		 }catch (ExceptionFinPista e){
			enCarrera = false;
			System.out.println("FIN PRUEBA");
		 }
		 catch (InterruptedException e) {
		     /* el thread fue interrumpido durante la espera */
		     throw new IllegalStateException("algo interrumpido", e);
		 }
		 try{
			Thread.sleep(50);
		 }catch (InterruptedException e){}
		}
		this.finalizar();
	}
		
	public void correr(){
			/* dentro de un ciclo:
			 * 	discretiza tiempos para el control y para el cambio de posiciones?
			 * 	llamar al desgastar de cada auto
			 * 	finalizar el ciclo cuando a un auto se le acaba la pista-> 
			 *  ->atrapa la excepcion, de donde obtiene el numero correspondiente 
			 *  al auto que finalizo, y dicho numero corresponde tambien al nro de jugador,
			 *  por lo tanto este sera el ganador
			 *
			 * tiene q lanzar un thread para la vista?
			 */
	}

}
