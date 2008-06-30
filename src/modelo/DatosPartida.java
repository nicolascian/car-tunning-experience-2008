/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import control.*;


public class DatosPartida {

	
	private modelo.Usuario usuario = null;
	
	private modelo.Virtual virtual = null;
	private modelo.Auto autoVirtual = null;
	private control.Virtual controlVirtual;
	private control.Habilidad habilidad;
	
	private Pista pista = null;

	
	private ControladorJuego control;
	private vista.VistaVentana vista ;
	
	private GestorPersistencia gestor = null;
	
	
	public DatosPartida(){
		//creo un gestor de guardado
		gestor = new GestorPersistencia();
		
		//preparo todo lo del jugador virtual
		habilidad = new control.Intermedio();
		autoVirtual = new Auto();
		controlVirtual = new control.Virtual(habilidad, autoVirtual);
		virtual = new Virtual(controlVirtual, autoVirtual);
	
		//mando las ventanas
		control = new ControladorJuego(this);
		vista = new vista.VistaVentana(control);
		
		
	}

	public void Manejar(){
		this.pista = new Pista(usuario.getAuto(), virtual.getAuto(), 100 );
		Manejar manejar = new Manejar(usuario, pista);
		manejar.run();
		
	}
	
	public void Carrera(){
		//SACAR ESTO DE ACA
		this.pista = new Pista(usuario.getAuto(), virtual.getAuto(),3000 );
		AlgoPesos apuesta = new AlgoPesos(100,00);
		Carrera carrera = new Carrera(usuario, virtual, pista, apuesta);
		//carrera.run();
		
		Thread threadCarrera = new Thread(carrera);
		threadCarrera.start();
		
	}
	
	public void Guardar(){
		
		try{
			gestor.Guardar(usuario);
		}
		catch(Exception e1){
			System.out.println("EXCEPCION en DatosPartida, guardar. ERR: " + e1.getMessage());
		}
		
	}

	public void Cargar(){
		
		//PENDIENTE
		//cargamos desde un achivo y asignamos
		this.setUsuario(null);
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
