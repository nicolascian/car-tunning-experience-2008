/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

import modelo.componente.*;
import modelo.componente.neumaticos.*;
import modelo.exceptions.*;
import modelo.fuerzas.*;
import java.util.*;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @Domumentacion: Una instancia de la clase Auto modela un auto, con todos sus componentes,
 * como ser caja, motor, neumaticos, combustible, escape,etc, los cuales son suceptibles 
 * de ser desgastados por el clima o por la superficie durante una carrera.
 * Los componentes proveen potencia a la instancia de Auto, la cual es utilizada para
 * acelerar y aumentar su velocidad.
 * Una instancia de esta clase se puede encontrar, encendido, apagado o acelerando.
 * 	
 * @version 1.0
 */
public class Auto extends Observable implements AfectablePorClima, AfectablePorSuperficie{
	
	private String nombre="generico"; //nombre para el auto
	private double Velocidad=0; //velocidad del auto
	private double Posicion=0; // dstancia recorrida
	private Motor motor=null;
	private Caja caja=null;
	private Embrague embrague=null;
	private Combustible combustible=null;
	private Alimentacion alimentacion=null;
	private Carroceria carroceria=null;
	private Suspension suspension=null;	
	private Escape escape=null;
	private Turbo turbo=null;
	private Nitro nitro=null;
	private Freno freno=null;
	private Eje ejeDelantero=null;
	private Eje ejeTrasero=null;
	private SistemaDeRefrigeracion sistemaDeRefrigeracion=null;
	//llantas
	private Llanta llantaDelanteraDerecha=null;
	private Llanta llantaDelanteraIzquierda=null;
	private Llanta llantaTraseraDerecha=null;
	private Llanta llantaTraseraIzquierda=null;
	//neumaticos
	private Neumatico neumaticoDelanteroDerecho=null;
	private Neumatico neumaticoDelanteroIzquierdo=null;
	private Neumatico neumaticoTraseroDerecho=null;
	private Neumatico neumaticoTraseroIzquierdo=null;
	
	protected final static double CONSTANTE_DE_OBTENCION_DE_VELOCIDAD=0.0123112;
	
	/**
	 *	@Pre:
	 *	@Post: Se ha creado una instancia de la clase Auto con valores por defecto 
	 *  correspondiente a un auto mediano de calle, con caja automatica de 5 velocidades,
	 *  motor de 1.6 litros con 4 cilindros, con revoluciones maximas de 8000 rpm.
	*/
	public Auto(){
		//creacion de componentes
		  setMotor(new Motor(4,1600,8000)); 
		  setCaja(new Automatica(5));
		  setEmbrague(new Embrague());
		  setCombustible(new Combustible(50.0,0.4));
		  setCarroceria(new Carroceria(2.4));
		  setAlimentacion(new Carburador());
		  setSuspension(new Suspension());
		  setEscape(new Escape());
		  setTurbo(new Turbo());
		  setNitro(new Nitro());
		  setFreno(new FrenoCinta());
		  setSistemaDeRefrigeracion(new SistemaDeRefrigeracion(this));
		/* eje delantero */

		  setLlantaDelanteraDerecha(new Llanta());
		  setLlantaDelanteraIzquierda(new Llanta());
		  setNeumaticoDelanteroDerecho(new NeumaticoMixto());
		  setNeumaticoDelanteroIzquierdo(new NeumaticoMixto());
		  setEjeDelantero(new Eje(this, this.llantaDelanteraDerecha, this.llantaDelanteraIzquierda));
		/* eje trasero */

		  setLlantaTraseraDerecha(new Llanta());
		  setLlantaTraseraIzquierda(new Llanta());
		  setNeumaticoTraseroDerecho(new NeumaticoMixto());
		  setNeumaticoTraseroIzquierdo(new NeumaticoMixto());
		  setEjeTrasero(new Eje(this, this.llantaTraseraDerecha, this.llantaTraseraIzquierda));
		//inicilizacion de otros atributos
		  setEncendido(false);
	      this.embragar(false);
	      motor.setAuto(this);
	      setNombre("Auto generico");
	}
	
	/**
	 * @Pre: Las intancias pasadas por parametro han sido creadas.
	 * @Post: Se ha creado una instancia de la clase Auto segun los componentes pasados
	 * por parametro.
	 * @param motor
	 * @param caja
	 * @param combustible
	 * @param carroceria
	 * @param alimentacion
	 * @param suspension
	 * @param escape
	 * @param turbo
	*/
	public Auto(Motor motor,Caja caja,Combustible combustible,
			Carroceria carroceria,Alimentacion alimentacion,
			Suspension suspension,Escape escape,Turbo turbo, 
			Nitro nitro, Embrague embrague, Freno freno){
		setMotor(motor);
		setCaja(caja);
		setEmbrague(embrague);
		setCombustible(combustible);
		setCarroceria(carroceria);
		setAlimentacion(alimentacion);
		setSuspension(suspension);
		setEscape(escape);
		setTurbo(turbo);
		setNitro(nitro);
		setFreno(freno);
		setSistemaDeRefrigeracion(new SistemaDeRefrigeracion(this));
		/* eje delantero */

		setLlantaDelanteraDerecha(new Llanta());
		setLlantaDelanteraIzquierda(new Llanta());
		setNeumaticoDelanteroDerecho(new NeumaticoMixto());
		setNeumaticoDelanteroIzquierdo(new NeumaticoMixto());
		setEjeDelantero(new Eje(this, this.llantaDelanteraDerecha, this.llantaDelanteraIzquierda));
		/* eje trasero */

		setLlantaTraseraDerecha(new Llanta());
		setLlantaTraseraIzquierda(new Llanta());
		setNeumaticoTraseroDerecho(new NeumaticoMixto());
		setNeumaticoTraseroIzquierdo(new NeumaticoMixto());
		setEjeTrasero(new Eje(this, this.llantaTraseraDerecha, this.llantaTraseraIzquierda));
		
		setEncendido(false);
		//inicializacion de aceleracion y velocidad
		this.embragar(false);
		motor.setAuto(this);
		setNombre("Auto modificado");
	}
	
	/**
	 * Persistencia
	 * @param xmlElement
	 */
	public Auto(Element xmlElement){
		//traigo el atributo nombre
		this.nombre =xmlElement.getAttribute("nombre");
		//creo los componentes
		crearComponentes(xmlElement, this);
	}
	
	private void crearComponentes(Element xmlElement, Auto auto){
		//Alimentacion
		NodeList nodo = xmlElement.getElementsByTagName("alimentacion");
		String tipo = ((Element)nodo.item(0)).getAttribute("tipo");
		if (tipo == "carburador"){
			this.setAlimentacion(new Carburador((Element)nodo.item(0)));
		}else if(tipo == "inyeccion"){
			this.setAlimentacion(new Inyeccion((Element)nodo.item(0)));
		};
		//Caja
		nodo = xmlElement.getElementsByTagName("caja");
		tipo = ((Element)nodo.item(0)).getAttribute("tipo");
/*		if (tipo == "automatica"){
			this.setCaja(new Automatica((Element)nodo.item(0)));
		}else if(tipo == "secuencial"){
			this.setCaja(new Secuencial((Element)nodo.item(0)));
		}else if(tipo == "manual"){
			this.setCaja(new Manual((Element)nodo.item(0)));
		};*/
		//Embrague
		nodo = xmlElement.getElementsByTagName("embrague");
		this.setEmbrague(new Embrague((Element)nodo.item(0)));
		//Carroceria
		nodo = xmlElement.getElementsByTagName("carroceria");
		this.setCarroceria(new Carroceria((Element)nodo.item(0)));
		//Combustible
		nodo = xmlElement.getElementsByTagName("combustible");
		this.setCombustible(new Combustible((Element)nodo.item(0)));
		//Escape
		nodo = xmlElement.getElementsByTagName("escape");
		this.setEscape(new Escape((Element)nodo.item(0)));
		//Motor
/*		nodo = xmlElement.getElementsByTagName("motor");
		this.setMotor(new Motor((Element)nodo.item(0)));*/
		//Suspension
		nodo = xmlElement.getElementsByTagName("suspension");
		this.setSuspension(new Suspension((Element)nodo.item(0)));
		//Turbo
		nodo = xmlElement.getElementsByTagName("turbo");
		this.setTurbo(new Turbo((Element)nodo.item(0)));
		//Nitro
		nodo = xmlElement.getElementsByTagName("nitro");
		this.setNitro(new Nitro((Element)nodo.item(0)));
		//Freno
		nodo = xmlElement.getElementsByTagName("freno");
		tipo = ((Element)nodo.item(0)).getAttribute("tipo");
		if (tipo == "abs"){
			this.setFreno(new FrenoABS((Element)nodo.item(0)));
		}else if(tipo == "disco"){
			this.setFreno(new FrenoDisco((Element)nodo.item(0)));
		}else if(tipo == "cinta"){
			this.setFreno(new FrenoCinta((Element)nodo.item(0)));
		};
		//SistemaDeRefrigeracion
		nodo = xmlElement.getElementsByTagName("sistemaderefrigeracion");
		this.setSistemaDeRefrigeracion(new SistemaDeRefrigeracion((Element)nodo.item(0), auto));
		//Neumatico
		nodo = xmlElement.getElementsByTagName("neumatico");
		tipo = ((Element)nodo.item(0)).getAttribute("tipo");
		if (tipo == "slick"){
			this.setNeumaticoDelanteroDerecho(new NeumaticoSlick((Element)nodo.item(0), auto));
		}else if(tipo == "mixto"){
			this.setNeumaticoDelanteroDerecho(new NeumaticoMixto((Element)nodo.item(0), auto));
		}else if(tipo == "invierno"){
			this.setNeumaticoDelanteroDerecho(new NeumaticoInvierno((Element)nodo.item(0), auto));
		}else if(tipo == "lluvia"){
			this.setNeumaticoDelanteroDerecho(new NeumaticoLluvia((Element)nodo.item(0), auto));
		}else if(tipo == "todoterreno"){
			this.setNeumaticoDelanteroDerecho(new NeumaticoTodoTerreno((Element)nodo.item(0), auto));
		};
		tipo = ((Element)nodo.item(1)).getAttribute("tipo");
		if (tipo == "slick"){
			this.setNeumaticoDelanteroIzquierdo(new NeumaticoSlick((Element)nodo.item(1), auto));
		}else if(tipo == "mixto"){
			this.setNeumaticoDelanteroIzquierdo(new NeumaticoMixto((Element)nodo.item(1), auto));
		}else if(tipo == "invierno"){
			this.setNeumaticoDelanteroIzquierdo(new NeumaticoInvierno((Element)nodo.item(1), auto));
		}else if(tipo == "lluvia"){
			this.setNeumaticoDelanteroIzquierdo(new NeumaticoLluvia((Element)nodo.item(1), auto));
		}else if(tipo == "todoterreno"){
			this.setNeumaticoDelanteroIzquierdo(new NeumaticoTodoTerreno((Element)nodo.item(1), auto));
		};
		tipo = ((Element)nodo.item(2)).getAttribute("tipo");
		if (tipo == "slick"){
			this.setNeumaticoTraseroDerecho(new NeumaticoSlick((Element)nodo.item(2), auto));
		}else if(tipo == "mixto"){
			this.setNeumaticoTraseroDerecho(new NeumaticoMixto((Element)nodo.item(2), auto));
		}else if(tipo == "invierno"){
			this.setNeumaticoTraseroDerecho(new NeumaticoInvierno((Element)nodo.item(2), auto));
		}else if(tipo == "lluvia"){
			this.setNeumaticoTraseroDerecho(new NeumaticoLluvia((Element)nodo.item(2), auto));
		}else if(tipo == "todoterreno"){
			this.setNeumaticoTraseroDerecho(new NeumaticoTodoTerreno((Element)nodo.item(2), auto));
		};
		tipo = ((Element)nodo.item(3)).getAttribute("tipo");
		if (tipo == "slick"){
			this.setNeumaticoTraseroIzquierdo(new NeumaticoSlick((Element)nodo.item(3), auto));
		}else if(tipo == "mixto"){
			this.setNeumaticoTraseroIzquierdo(new NeumaticoMixto((Element)nodo.item(3), auto));
		}else if(tipo == "invierno"){
			this.setNeumaticoTraseroIzquierdo(new NeumaticoInvierno((Element)nodo.item(3), auto));
		}else if(tipo == "lluvia"){
			this.setNeumaticoTraseroIzquierdo(new NeumaticoLluvia((Element)nodo.item(3), auto));
		}else if(tipo == "todoterreno"){
			this.setNeumaticoTraseroIzquierdo(new NeumaticoTodoTerreno((Element)nodo.item(3), auto));
		};
		//Llanta
		nodo = xmlElement.getElementsByTagName("llanta");
		this.setLlantaDelanteraDerecha(new Llanta((Element)nodo.item(0)));
		this.setLlantaDelanteraIzquierda(new Llanta((Element)nodo.item(1)));
		this.setLlantaTraseraDerecha(new Llanta((Element)nodo.item(2)));
		this.setLlantaTraseraIzquierda(new Llanta((Element)nodo.item(3)));
		//Eje
/*		nodo = xmlElement.getElementsByTagName("eje");
		this.setEjeDelantero(new Eje((Element)nodo.item(0)));
		this.setEjeTrasero(new Eje((Element)nodo.item(1)));*/
		
	}
	
	/**
	 * se ejecuta cuando hay cambios en el auto
	 * auto.actualizarVelocidadYPosicion ----------> Velocidad, Posicion
	 * auto.desgastar -----------------------------> Estado?????????????????cuando se llama?
	 * motor.setRPM -------------------------------> RPM
	 * caja.setCambio -----------------------------> Cambio
	 * caja.setRevolucionesMaximasMotorParaCambioActual ----------------------------> RPMmaximas
	 * acelerar ebragar enceder nitroactivar frenar
	 */
	public void ActualizarObservadores(){
		if(!this.isAcelerando())
			this.getMotor().acelerar(false);
		this.actualizarVelocidadYPosicion();
		setChanged();
		notifyObservers();		
	}
	
	public void agregarObservador(Observer obs){
		this.addObserver(obs);
		getCaja().addObserver(obs);
		getEmbrague().addObserver(obs);
		getMotor().addObserver(obs);
	}

	public void actualizarVelocidadYPosicion(){
		Velocidad = getEjeDelantero().getRpm()*CONSTANTE_DE_OBTENCION_DE_VELOCIDAD;
		Posicion += getVelocidad() * (0.016);
		//notifico a los observadores
		//ActualizarObservadores();
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la potencia de la instancia en HP de acuerdo a la potencia total
	 * de sus componentes.
	 * @Nota: Todos los componentes excepto la carroceria aportan potencia solo al estar encendida
	 * la instancia, la carroceria solo con que el auto tenga velocidad mayor que cero aporta potencia. 
	*/
	public double getPotenciaTotal(){
		double potencia=0; 
		//hay componente que solo aportan potencia al estar encendido el auto
		if(isEncendido()){
		   LinkedList<Componente> lista=this.obtenerComponentes();
		   Iterator<Componente> it=lista.iterator();
		   while(it.hasNext())  
		     try{
			    potencia=it.next().obtenerPotencia();
			 }catch(NullPointerException e){}	
		}
		return(potencia);
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se han desgastado todos los componentes de la instancia.
	 * @return
	*/
	public void Desgastar(){
		LinkedList<Componente> lista= this.obtenerComponentes();
		Iterator<Componente> it =lista.iterator();
		while (it.hasNext()){
			it.next().desgastar();
		}
		//notifico a los observadores
		ActualizarObservadores();
	}	
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna true en caso de que el auto se encuentre preparado para correr una carrera,
	 * es decir que tenga las caracteristicas minimas de componentes y estado para ello.
	*/
	public boolean estaListoParaCarrera() {
		boolean listo = true;
		try{
			comprobarComponentes();
		}catch (Exception e){
			return false;
		}
		return listo;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Compruba la precencia y estado de los componentes del auto.
	 * @throws: ExceptionComponenteFaltante (en caso de que un componente falte),
	 *          ExceptionComponenteDesgastado (en caso de que un componente se halle totalmente desgastado)
	*/
	public void comprobarComponentes() 
	  throws ExceptionComponenteFaltante, ExceptionComponenteDesgastado{
		LinkedList<Componente> lista=this.obtenerComponentes();
		Iterator<Componente> it =lista.iterator();
		while (it.hasNext()){
			Componente aux = it.next();
			if( aux == null) throw new ExceptionComponenteFaltante();
			if(aux.getEstado()==0)throw new ExceptionComponenteDesgastado(aux.getClass().getName());
		}
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Solo si la instancia se encuentra lista para correr una carrera se enciende la instancia.
	 * Si luego de ejecutar este metodo no queda encendido puede deberse a componentes faltantes o 
	 * desgastados completamente.
	*/
	public void setEncendido(boolean encendido) {
      try {	
		//encendido de motor
	    if(encendido){
	  	  if(!getMotor().isEncendido()){
	  		  this.embragar(true);
			  getCaja().setCambio(0);
			  this.embragar(false);
			  //encendido de motor
	  		  getEjeDelantero().setRpm(0);
	  		  getEjeTrasero().setRpm(0);
	  		  getMotor().encender();
	  	  }	
	    }
        else{
        	getMotor().apagar();
	 		getEjeDelantero().setRpm(0);
	  		getEjeTrasero().setRpm(0);
	  		caja.setRevolucionesMaximasMotorParaCambioActual(0);
	  		caja.setRevolucionesMinimasMotorParaCambioActual(0);
	  		Velocidad=0;
	 		liberarFuerzas();
	 		this.embragar(true);
			getCaja().setCambio(0);
			this.embragar(false);
        }
        //actualizacion de observadores
	    ActualizarObservadores();
	  }catch(NullPointerException e){}
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna true en caso de que el auto se encuentre encendido.
	*/
	public boolean isEncendido() {
		return(getMotor().isEncendido());
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: En caso de que la instancia se encuentre lista para carrera se enciende la instancia.
	*/
    public void acelerar(boolean valor){
	      getMotor().acelerar(valor);
	   	  actualizarVelocidadYPosicion();
	   	  ActualizarObservadores();
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se retorna true en caso de que la instancia se encuentre acelerando.
	*/
	public boolean isAcelerando(){
		return(getMotor().isAcelerando());
	}
	
	public void frenar(boolean valor){
		if(getFreno()!=null)
			getFreno().frenar(valor);
		ActualizarObservadores();
	}
	
	public boolean isFrenando(){
		if(getFreno()!=null){
			return(getFreno().isFrenando());
		}else
			return false;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: En caso de que la instancia se encuentre lista para carrera se embraga.
	*/
    public void embragar(boolean valor){
	   	if(getEmbrague()!=null)
	   		getEmbrague().embragar(valor);
	  
	   	ActualizarObservadores();
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se retorna true en caso de que la instancia se encuentre embragado.
	*/
	public boolean isEmbragado(){
		if(getEmbrague()!=null){
		   return(getEmbrague().isEmbragado());
		}else
		   return false;
	}
	

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: En caso de que la instancia se encuentre lista para carrera se activa el nitro.
	*/
    public void activarNitro(boolean valor){
	   	if(getNitro()!=null)
		   getNitro().activar(valor);
	  
	   	ActualizarObservadores();
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se retorna true en caso de que la instancia se encuentre activado el nitro.
	*/
	public boolean isNitroActivado(){
		if(getNitro()!=null){
		   return(getNitro().isActivado());
		}else
		   return false;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna el estado promedio de la instancia, teniendo en cuenta el estado de todos
	 * sus componentes, en caso de que un componente tenga estado 0 se retorna 0 como estado 
	 * promedio.
	*/
	public double getEstado() {
		double Estado = 0;
		int componentes=0;
		LinkedList<Componente> lista=obtenerComponentes();
		Iterator<Componente> it =lista.iterator();
		boolean componente0=false;
		while ((it.hasNext())&&(!componente0)){
			double estadoComponente= it.next().getEstado();
			if(estadoComponente!=0)
			  Estado+=estadoComponente;
			else{
			  Estado=0;
			  componente0=true;
			}
			componentes++;
		}
		try{
			return(Estado/componentes);
		}catch(Exception e){
			return 0;
		}
	}
	
    protected void liberarFuerzas(){
    	LinkedList<ReceptorDeFuerzas> lista=this.obtenerReceptoresDeFuerzas();
    	Iterator<ReceptorDeFuerzas> it = lista.iterator();
		boolean componente0=false;
		while ((it.hasNext())&&(!componente0)){
			it.next().liberarFuerzas();
		}
    }
    	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la velocidad de acuerdo a la potencia.
	*/
	public double getVelocidad(){
		return Velocidad;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha obtenido la posicion en la pista de acuerdo a la velocidad y aceleracion de la
	 * instancia. 
	 * @return
	*/
	public double getPosicion() {
		return Posicion;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se ha seteado la posicion del auto en la pista.
	*/
	public void setPosicion(double posicion) {
		Posicion = posicion;
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto, la instancia del parametro clima ha sido
	 * creada.
	 * @Post: Se afecta a la instancia y a todos sus componentes por el clima pasado por parametro.
	*/
	public void afectar(Clima clima){
		Iterator<AfectablePorClima> it = this.obtenerAfectablesPorClima().iterator();
		while (it.hasNext()){
			it.next().afectar(clima);
		}
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto, la instancia del parametro superficie ha sido
	 * creada.
	 * @Post: Se afecta a la instancia y a todos sus componentes por la supericie pasada por parametro.
	*/
	public void afectar(Superficie superficie){
		Iterator<AfectablePorSuperficie> it = this.obtenerAfectablesPorSup().iterator();
		while (it.hasNext()){
			it.next().afectar(superficie);
		}
	}
	
	public boolean isAutomatica(){
		return(getCaja().getClass().isInstance(new Automatica(5)));
	}
		
	public boolean isManual(){
		return(getCaja().getClass().isInstance(new Manual(5)));
	}
	
	public boolean isSecuencial(){
		return(getCaja().getClass().isInstance(new Secuencial(5)));
	}	

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes de la instancia.
	*/
	public LinkedList<Componente> obtenerComponentes(){
		LinkedList<Componente> lista =  new LinkedList<Componente>();
		lista.add(this.alimentacion);
		lista.add(this.caja);
		lista.add(this.embrague);
		lista.add(this.carroceria);
		lista.add(this.combustible);
		lista.add(this.escape);
		lista.add(this.motor);
		lista.add(this.suspension);
		lista.add(this.turbo);
		lista.add(this.nitro);
		lista.add(this.freno);
		lista.add(this.sistemaDeRefrigeracion);
		//neumaticos
		lista.add(this.neumaticoDelanteroDerecho); 
		lista.add(this.neumaticoDelanteroIzquierdo);
		lista.add(this.neumaticoTraseroDerecho); 
		lista.add(this.neumaticoTraseroIzquierdo);
		//llantas
		lista.add(this.llantaDelanteraDerecha);	
		lista.add(this.llantaDelanteraIzquierda);
		lista.add(this.llantaTraseraDerecha);	
		lista.add(this.llantaTraseraIzquierda);	
		//ejes
		lista.add(this.ejeDelantero);
		lista.add(this.ejeTrasero);
		return lista;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes afectables por
	 * superficie de la instancia.
	*/
	public LinkedList<ReceptorDeFuerzas> obtenerReceptoresDeFuerzas(){
		LinkedList<ReceptorDeFuerzas> lista = new LinkedList<ReceptorDeFuerzas>();
		try{
			lista.add(this.motor);
			lista.add(this.caja);
			lista.add(this.carroceria);
			//ejes
			lista.add(this.ejeDelantero);
			lista.add(this.ejeTrasero);
			//llantas
			lista.add(this.llantaDelanteraDerecha);	lista.add(this.llantaDelanteraIzquierda);
			lista.add(this.llantaTraseraDerecha);	lista.add(this.llantaTraseraIzquierda);
			//neumaticos
			lista.add(this.neumaticoDelanteroDerecho); lista.add(this.neumaticoDelanteroIzquierdo);
			lista.add(this.neumaticoTraseroDerecho); lista.add(this.neumaticoTraseroIzquierdo);
		}catch(Exception e){}
		return lista;
	}
		
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes afectables por
	 * superficie de la instancia.
	*/
	public LinkedList<AfectablePorSuperficie> obtenerAfectablesPorSup(){
		LinkedList<AfectablePorSuperficie> listaAS = new LinkedList<AfectablePorSuperficie>();
		LinkedList<Componente> lista=obtenerComponentes();
		Iterator<Componente> it =lista.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecSuperficie(listaAS);
		}
		return listaAS;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Retorna una instancia de lista LinkedList con todos los componentes afectables por
	 * clima de la instancia.
	*/
	public LinkedList<AfectablePorClima> obtenerAfectablesPorClima(){
		LinkedList<AfectablePorClima> listaAC = new LinkedList<AfectablePorClima>();
		LinkedList<Componente> lista=this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecClima(listaAC);
		}
		return listaAC;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se setea el eje delantero de la instancia.
	*/
	public void setEjeDelantero(Eje ejeDelantero) {
    	try{
    		ejeDelantero.instalar(this,getLlantaDelanteraDerecha(),
    				              getLlantaDelanteraIzquierda());
    		this.ejeDelantero = ejeDelantero;
    	}catch(NullPointerException e){}
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se obtiene el eje delantero de la instancia.
	*/
	public Eje getEjeDelantero() {
		return ejeDelantero;
	}
	
	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se setea el eje trasero de la instancia.
	*/
	public void setEjeTrasero(Eje ejeTrasero) {
	 	try{
			ejeTrasero.instalar(this,getLlantaTraseraDerecha(),
					            getLlantaTraseraIzquierda());
			this.ejeTrasero = ejeTrasero;
		}catch(NullPointerException e){}
	}

	/**
	 * @Pre: Se ha creado la instancia de la clase Auto.
	 * @Post: Se obtiene el eje delantero de la instancia.
	*/
	public Eje getEjeTrasero() {
		return ejeTrasero;
	}
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
	  try{	
		combustible.instalar(this);
		this.combustible = combustible;
	  }catch(NullPointerException e){}
	}

	public void setSuspension(Suspension suspension) {
	  try{	
		this.suspension = suspension;
		suspension.instalar(this);
	  }catch(NullPointerException e){}
	}

	public Suspension getSuspension() {
		return suspension;
	}
	
	public void setCaja(Caja caja){
	  try{	
		caja.instalar(this);
		this.caja = caja;
	  }catch (NullPointerException e){}
	}

	public Caja getCaja(){
		return this.caja;
	}

	public void setMotor(Motor motor){
	  try{	
		motor.instalar(this);
		this.motor = motor;
	  }catch (NullPointerException e){}
	}
	
	public Motor getMotor(){
		return this.motor;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
	  try{	
		alimentacion.instalar(this);
		this.alimentacion = alimentacion;
	  }catch (NullPointerException e){}
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setCarroceria(Carroceria carroceria) {
	  try{	
		carroceria.instalar(this);
		this.carroceria = carroceria;
	  }catch (NullPointerException e){}
	}
	
	public Carroceria getCarroceria() {
		return carroceria;
	}

	public void setEscape(Escape escape) {
	  try{
		escape.instalar(this);
		this.escape = escape;
	  }catch (NullPointerException e){}
	}

	public Escape getEscape() {
		return escape;
	}

	public void setTurbo(Turbo turbo) {
	  try{	
		turbo.instalar(this);
		this.turbo = turbo;
	  }catch (NullPointerException e){}
	}
	
	public Turbo getTurbo() {
		return turbo;
	}
	
	public Nitro getNitro() {
		return nitro;
	}
	
	public void setNitro(Nitro nitro) {
	  try{
		nitro.instalar(this);  
		this.nitro = nitro;
	  }catch (NullPointerException e){}
	}
	

	public Embrague getEmbrague() {
		return embrague;
	}

	public void setEmbrague(Embrague embrague) {
	  try{	
		embrague.instalar(this);
		this.embrague = embrague;
	  }catch (NullPointerException e){}
	}

	public Freno getFreno() {
		return freno;
	}

	public void setFreno(Freno freno) {
	  try{
		embrague.instalar(this);
		this.freno = freno;
	  }catch (NullPointerException e){}
	}
	
	public double getPeso(){
	  double peso=motor.getPeso();
	  LinkedList<Componente> lista=obtenerComponentes();
	  Iterator<Componente> it=lista.iterator();  
	  while(it.hasNext())  
	    try{
	    	peso+=it.next().getPeso();
	    }catch (NullPointerException e){}
	  return(peso);
	}

	/**
	 * @return the llantaDelanteraDerecha
	 */
	public Llanta getLlantaDelanteraDerecha() {
		return llantaDelanteraDerecha;
	}

	/**
	 * @param llantaDelanteraDerecha the llantaDelanteraDerecha to set
	 */
	public void setLlantaDelanteraDerecha(Llanta llantaDelanteraDerecha) {
		this.llantaDelanteraDerecha = llantaDelanteraDerecha;
		try{
			this.llantaDelanteraDerecha.instalar(this,getEjeDelantero(),getNeumaticoDelanteroDerecho(),
					                             true);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the llantaDelanteraIzquierda
	 */
	public Llanta getLlantaDelanteraIzquierda() {
		return llantaDelanteraIzquierda;
	}

	/**
	 * @param llantaDelanteraIzquierda the llantaDelanteraIzquierda to set
	 */
	public void setLlantaDelanteraIzquierda(Llanta llantaDelanteraIzquierda) {
		this.llantaDelanteraIzquierda = llantaDelanteraIzquierda;
		try{
			this.llantaDelanteraIzquierda.instalar(this,ejeDelantero,getNeumaticoDelanteroIzquierdo(),
					                               false);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the llantaTraseraDerecha
	 */
	public Llanta getLlantaTraseraDerecha() {
		return llantaTraseraDerecha;
	}

	/**
	 * @param llantaTraseraDerecha the llantaTraseraDerecha to set
	 */
	public void setLlantaTraseraDerecha(Llanta llantaTraseraDerecha) {
		this.llantaTraseraDerecha = llantaTraseraDerecha;
		try{
			this.llantaTraseraDerecha.instalar(this,ejeTrasero,getNeumaticoTraseroDerecho(),true);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the llantaTraseraIzquierda
	 */
	public Llanta getLlantaTraseraIzquierda() {
		return llantaTraseraIzquierda;
	}

	/**
	 * @param llantaTraseraIzquierda the llantaTraseraIzquierda to set
	 */
	public void setLlantaTraseraIzquierda(Llanta llantaTraseraIzquierda) {
		this.llantaTraseraIzquierda = llantaTraseraIzquierda;
		try{
			this.llantaTraseraIzquierda.instalar(this,ejeTrasero,getNeumaticoTraseroIzquierdo(),false);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the neumaticoDelanteroDerecho
	 */
	public Neumatico getNeumaticoDelanteroDerecho() {
		return neumaticoDelanteroDerecho;
	}

	/**
	 * @param neumaticoDelanteroDerecho the neumaticoDelanteroDerecho to set
	 */
	public void setNeumaticoDelanteroDerecho(Neumatico neumaticoDelanteroDerecho) {
		this.neumaticoDelanteroDerecho = neumaticoDelanteroDerecho;
		try{
			this.neumaticoDelanteroDerecho.instalar(this,llantaDelanteraDerecha);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the neumaticoDelanteroIzquierdo
	 */
	public Neumatico getNeumaticoDelanteroIzquierdo() {
		return neumaticoDelanteroIzquierdo;
	}

	/**
	 * @param neumaticoDelanteroIzquierdo the neumaticoDelanteroIzquierdo to set
	 */
	public void setNeumaticoDelanteroIzquierdo(Neumatico neumaticoDelanteroIzquierdo) {
		this.neumaticoDelanteroIzquierdo = neumaticoDelanteroIzquierdo;
		try{
			this.neumaticoDelanteroIzquierdo.instalar(this,llantaDelanteraIzquierda);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the neumaticoTraseroDerecho
	 */
	public Neumatico getNeumaticoTraseroDerecho() {
		return neumaticoTraseroDerecho;
	}

	/**
	 * @param neumaticoTraseroDerecho the neumaticoTraseroDerecho to set
	 */
	public void setNeumaticoTraseroDerecho(Neumatico neumaticoTraseroDerecho) {
		this.neumaticoTraseroDerecho = neumaticoTraseroDerecho;
		try{
			this.neumaticoTraseroDerecho.instalar(this,llantaTraseraDerecha);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the neumaticoTraseroIzquierdo
	 */
	public Neumatico getNeumaticoTraseroIzquierdo() {
		return neumaticoTraseroIzquierdo;
	}

	/**
	 * @param neumaticoTraseroIzquierdo the neumaticoTraseroIzquierdo to set
	 */
	public void setNeumaticoTraseroIzquierdo(Neumatico neumaticoTraseroIzquierdo) {
		this.neumaticoTraseroIzquierdo = neumaticoTraseroIzquierdo;
		try{
			this.neumaticoTraseroIzquierdo.instalar(this,llantaTraseraIzquierda);
		}catch(NullPointerException e){}
	}

	/**
	 * @return the sistemaDeRefrigeracion
	 */
	public SistemaDeRefrigeracion getSistemaDeRefrigeracion() {
		return sistemaDeRefrigeracion;
	}

	/**
	 * @param sistemaDeRefrigeracion the sistemaDeRefrigeracion to set
	 */
	public void setSistemaDeRefrigeracion(
			SistemaDeRefrigeracion sistemaDeRefrigeracion) {
		this.sistemaDeRefrigeracion = sistemaDeRefrigeracion;
		try{
			this.sistemaDeRefrigeracion.instalar(this);
		}catch(NullPointerException e){}
	}
	
	
	
	/* toString */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	*/
	@Override
	public String toString() {
		String cadena="Auto Velocidad: "+getVelocidad()+"Km/h ";
		if(estaListoParaCarrera())
			cadena=cadena+" Esta Listo Para Carrera ";
		else
			cadena=cadena+" No Esta Listo Para Carrera ";
		cadena=cadena+'\n'+getMotor().toString()+getCaja().toString();
		return(cadena);
	}
	
	/** 
	 * Persistencia
	 */
	public Element toXml(Document doc){
		Element xmlElement = doc.createElement("auto");
		xmlElement.setAttribute("nombre", this.getNombre());
		
		LinkedList<Componente> lista= obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		
		while (it.hasNext()){ xmlElement.appendChild(it.next().toXml(doc));  }
		
		return xmlElement;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}