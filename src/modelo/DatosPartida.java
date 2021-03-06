/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.ventanas.*;
import control.*;
import java.util.*;


public class DatosPartida extends Observable{

	
	private modelo.Usuario usuario = null;
	private modelo.Virtual virtual = null;
	private modelo.Auto autoVirtual = null;
	private control.Virtual controlVirtual;
	private control.Habilidad habilidad;
	private Pista pista = null;
	private ControladorJuego control;
	private GestorPersistencia gestor = null;
	
	public DatosPartida(){
		
		//preparo todo lo del jugador virtual
		autoVirtual = new Auto();
		habilidad = new control.Intermedio(autoVirtual);
		controlVirtual = new control.Virtual(habilidad, autoVirtual);
		virtual = new Virtual(controlVirtual, autoVirtual);
		pista = null;
		//mando las ventanas
		control = new ControladorJuego(this);
		vista.VistaVentana vistaVentana = new vista.VistaVentana(control);
		this.addObserver(vistaVentana);
	}

	public void Manejar(JFrame ventanaAnterior){
		this.pista = new Pista(usuario.getAuto(), virtual.getAuto(), 2000 );
		Manejar manejar = new Manejar(usuario, pista, ventanaAnterior);
		Thread threadManejar = new Thread(manejar);
		threadManejar.start();
	}
	
	public void Carrera(JFrame ventanaAnterior, AlgoPesos apuesta){
        if(this.pista==null){
		   this.pista = new Pista(usuario.getAuto(), virtual.getAuto(),3000 );
        }
        else{
        	usuario.getAuto().deleteObservers();
        	virtual.getAuto().deleteObservers();
        	pista.setAuto(usuario.getAuto(),0);
        	pista.setAuto(virtual.getAuto(),1);
        }
		VentanaCarrera ventanaCarrera = new VentanaCarrera(this.usuario, this.virtual, this.pista);
		Carrera carrera = new Carrera(usuario, virtual, pista, apuesta, ventanaAnterior, ventanaCarrera);
	    Thread threadCarrera = new Thread(carrera);
		threadCarrera.start();
	}
	
	public void Guardar(){
		
		//vemos si tenemos gestor
		if (gestor==null){ //creo un gestor de guardado
			gestor = new GestorPersistencia(); }
		
		try{
			gestor.Guardar(usuario, "guardado.xml");
			
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "Se ha guardado el usuario: ' " + usuario.getNombre() +" '"+'\n'+
	        	    "de forma satisfactoria.", 
	        	    "Informacion de guardado", JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "No se pudo guardar el Juego actual.",
	        	    "Error al guardar",
	        	    JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void Cargar(){
		
		//vemos si tenemos gestor
		if (gestor==null){ //creo un gestor de guardado
			gestor = new GestorPersistencia(); }
		
		//cargamos desde un achivo y asignamos
		try{
			usuario = gestor.Cargar("guardado.xml");  
			
			JOptionPane.showMessageDialog(new JFrame(),
	        	    "Se ha cargado el usuario: ' " + usuario.getNombre()+ " '" +'\n'+
	        	    "de forma satisfactoria.", 
	        	    "Informacion de carga", JOptionPane.INFORMATION_MESSAGE);
			
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
	
	public void cambiarHabilidad(String habilidad){
		String habilidadActual = virtual.getControl().getHabilidad().toString();
		//si no es la misma, lo cambianos
		if (!habilidadActual.equalsIgnoreCase(habilidad)){
			if (habilidad.equalsIgnoreCase("Principiante")){
				virtual.getControl().setHabilidad(new Principiante(virtual.getAuto()));
			} 
			if (habilidad.equalsIgnoreCase("Intermedio")){
				virtual.getControl().setHabilidad(new Intermedio(virtual.getAuto()));
			}
			if (habilidad.equalsIgnoreCase("Experto")){
				virtual.getControl().setHabilidad(new Experto(virtual.getAuto()));
			}
		}
	}
	
	public void crearJuegoNuevo(String nombre){
		setUsuario(new modelo.Usuario(nombre, new AlgoPesos(9000,00), new Auto()));
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
