/*
 * ALGUNAS COSAS QUE SE ME OCURRIERON A LA NOCHE DE COMO PODRIA SER UNA CARRERA
 */

package modelo;

import javax.swing.*;
import vista.ventanas.*;
import modelo.exceptions.*;


public class Carrera implements Runnable {

	private Pista pista;
	
	private JFrame ventanaAnterior;
	
	private VentanaCarrera vista;
	
	/*
	 * La apuesta corresponde a la cantidad de dinero que cada jugador debera
	 * pagar en caso de perder la carrera
	 */ 
	private AlgoPesos apuesta;
	
	private modelo.Usuario usuario;
	
	private modelo.Virtual virtual;
	
	
	public Carrera(Usuario usuario,Virtual virtual, Pista pista, AlgoPesos apuesta, 
			JFrame ventanaAnterior, VentanaCarrera vistaCarrera){
		this.ventanaAnterior = ventanaAnterior;
		this.usuario = usuario;
		this.virtual = virtual;
		this.pista = pista;
		this.apuesta = apuesta;
    	this.vista =  vistaCarrera;

    	this.vista.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
	}
	
	/**
	 * Metodo que se encarga de inicializar los atributos para la carrera
	 */
	private void incializar(){

		this.usuario.getAuto().setPosicion(0);
		this.virtual.getAuto().setPosicion(0);
		this.vista.addKeyListener(new control.Usuario(usuario.getAuto()));
		this.vista.addKeyListener(this.virtual.getControl());
		this.usuario.getAuto().agregarObservador(this.vista);
		this.virtual.getAuto().agregarObservador(this.vista);
		this.usuario.getAuto().ActualizarObservadores();
		this.virtual.getAuto().ActualizarObservadores();
		this.vista.setVisible(true);
	}
	
	/**
	 * Metodo que se encarga de aumentar / decrementar el dinero del usuario segun
	 * el resultado de la carrera, setea las posiciones de los autos nuevamente en 0,
	 * cierra la vista de la carrera y muestra la vista anterior a la carrera
	 */
	private void finalizar(){
		
		//Si la vista de la carrera no fue cerrada, entonces se busca al ganador
		if (this.vista.isEnabled()){
		
		String ganador = null;
		
		if (this.usuario.getAuto().getPosicion() < this.virtual.getAuto().getPosicion()){
			this.usuario.setDinero(this.usuario.getDinero().restar(this.apuesta));
			ganador = this.virtual.getNombre();
		}else {
			this.usuario.setDinero(this.usuario.getDinero().sumar(this.apuesta));
			ganador = this.usuario.getNombre();
		}

		JOptionPane.showMessageDialog(this.vista, " Fin de la carrera.\n Ganador: " +
				ganador + "\n Ud tiene $" + this.usuario.getDinero().getEntero() +
				"," + this.usuario.getDinero().getDecimal());
		}
		this.virtual.getAuto().setPosicion(0);
		this.usuario.getAuto().setPosicion(0);
		
		//cerrar la ventana
		cerrarVentana();
		
	}
	
	public void cerrarVentana(){
		this.vista.setEnabled(false);
		this.vista.dispose();
		this.ventanaAnterior.setVisible(true);
		vista=null;
		System.gc();

	}
	
	public void run() {
		this.incializar();
		boolean enCarrera = true;
		this.virtual.jugar(true); //ponemos a manejar al virtual
		while(enCarrera){
			try{
				synchronized (this.usuario.getAuto())
				{
				synchronized (this.virtual.getAuto())
				{
					this.usuario.getAuto().ActualizarObservadores();
					this.virtual.getAuto().ActualizarObservadores();
					this.usuario.getAuto().wait(25);
					this.virtual.getAuto().wait(25);
					this.pista.actualizarPosiciones();
					this.usuario.getAuto().Desgastar();				
					this.virtual.getAuto().Desgastar();
					this.virtual.getAuto().notifyAll();
				}this.usuario.getAuto().notifyAll();
				}
			} catch (ExceptionFinPista e){
				enCarrera = false;
			}
			catch (InterruptedException e) {
		        
	            /* el thread fue interrumpido durante la espera */
	            throw new IllegalStateException("algo interrumpido", e);
	        }
			try{
			Thread.sleep(50);
			}catch (InterruptedException e){}
			//Si la venta de la carrera se cerro, se da por finalizada la carrera
			if (!this.vista.isEnabled()){
				enCarrera = false;
			}
		}
		this.virtual.jugar(false); //paramos de manejar el virtual
		this.finalizar();
	}

}