/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.*;


public class DatosPartida {

	
	private modelo.Usuario usuario = null;
	
	private modelo.Virtual virtual = null;
	private modelo.Auto autoVirtual = null;
	private control.Virtual controlVirtual;
	private control.Habilidad habilidad;
	
	private Pista pista = null;

	
	private ControladorJuego control;
	
	private GestorPersistencia gestor = null;
	
	
	public DatosPartida(){
		//creo un gestor de guardado
		gestor = new GestorPersistencia();
		
		//preparo todo lo del jugador virtual
		autoVirtual = new Auto();
		habilidad = new control.Intermedio(autoVirtual);
		controlVirtual = new control.Virtual(habilidad, autoVirtual);
		virtual = new Virtual(controlVirtual, autoVirtual);
	
		//mando las ventanas
		control = new ControladorJuego(this);
		new vista.VistaVentana(control);
		
		
	}

	public void Manejar(){
		this.pista = new Pista(usuario.getAuto(), virtual.getAuto(), 2000 );
		Manejar manejar = new Manejar(usuario, pista);
		Thread threadManejar = new Thread(manejar);
		threadManejar.start();
	}
	
	public void Carrera(){
		//SACAR ESTO DE ACA
		this.pista = new Pista(usuario.getAuto(), virtual.getAuto(),3000 );
		AlgoPesos apuesta = new AlgoPesos(100,00);
		Carrera carrera = new Carrera(usuario, virtual, pista, apuesta);
	    Thread threadCarrera = new Thread(carrera);
		threadCarrera.start();
		
	}
	
	public void Guardar(){
		
		try{
			gestor.Guardar(usuario, "guardado.xml");
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "No se pudo guardar el Juego actual.",
	        	    "Error al guardar",
	        	    JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void Cargar(){
		
		//cargamos desde un achivo y asignamos
		try{
			usuario = gestor.Cargar("guardado.xml");  
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "No se pudo cargar desde archivo." +'\n'+
	        	    "Se ha generado un Jugador estandar.",
	        	    "Error al cargar",
	        	    JOptionPane.ERROR_MESSAGE);
			//no se ha podido cargar, generamos un estadar
			crearJuegoNuevo("Jugador");
		}
		
	}
	
	
	public void crearJuegoNuevo(String nombre){
		setUsuario(new modelo.Usuario(nombre, new AlgoPesos(1000,00), new Auto()));
	}
	
	
	public Pista getPista() {
		return pista;
	}


	public void setPista(Pista pista) {
		this.pista = pista;
	}


	public modelo.Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(modelo.Usuario usuario) {
		this.usuario = usuario;
	}
		
}
