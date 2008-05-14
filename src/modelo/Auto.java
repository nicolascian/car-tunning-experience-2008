/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;
import java.util.*;

/**
 * Clase Auto
 * 
 * @version 1.0
 */
public class Auto implements AfectablePorClima, AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	
	private double CNTE_ACELERACION_POTENCIA = 0.0003;
	
	private double Velocidad; // integral de Aaceleracion
	private double Aceleracion; // = Potencia de las RPM cuadrado y algo mas
	private double Posicion; // dstancia recorrida
	private double tiempo = 20;//(0.000000000006);//tiempoPorCiclo;
	
	private Motor motor;
	private Caja caja;
	private Combustible combustible;
	private Alimentacion alimentacion;
	private Carroceria carroceria;
	private Suspension suspension;	
	private Escape escape;
	private Turbo turbo;
	
	private Eje ejeDelantero;
	private Eje ejeTrasero;
			
	/**
	 * Constructor de Auto por defecto
	 *
	 */
	public Auto(){
		//creacion de componentes
		setMotor(new Motor(4,1600,8000)); 
		setCaja(new Automatica(5));
		setCombustible(new Combustible(50.0,0.4));
		setCarroceria(new Carroceria(2.4));
		setAlimentacion(new Carburador());
		setSuspension(new Suspension());
		setEscape(new Escape());
		setTurbo(new Turbo());
		
		/* eje delantero */
		  setEjeDelantero(new Eje());
		  ejeDelantero.setLlantaDerecha(new Llanta());
		  ejeDelantero.setLlantaIzquierda(new Llanta());
		  ejeDelantero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeDelantero.setNeumaticoIzquierdo(new NeumaticoMixto());
		
		/* eje trasero */
		  setEjeTrasero(new Eje());
		  ejeTrasero.setLlantaDerecha(new Llanta());
		  ejeTrasero.setLlantaIzquierda(new Llanta());
		  ejeTrasero.setNeumaticoDerecho(new NeumaticoMixto());
		  ejeTrasero.setNeumaticoIzquierdo(new NeumaticoMixto());
		
		setEncendido(false);
		//inicializacion de aceleracion y velocidad
		Velocidad=0;
		Aceleracion=0;
		Posicion=0;
	}
	
	/**
	 * Costructor de Auto con parametros
	 * 
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
			Suspension suspension,Escape escape,Turbo turbo){
		setMotor(motor);
		setCaja(caja);
		setCombustible(combustible);
		setCarroceria(carroceria);
		setAlimentacion(alimentacion);
		setSuspension(suspension);
		setEscape(escape);
		setTurbo(turbo);
		setEjeDelantero(new Eje());
		setEjeTrasero(new Eje());
		setEncendido(false);
		//inicializacion de aceleracion y velocidad
		Velocidad=0;
		Aceleracion=0;
		Posicion=0;
    }

	/**
	 * getAceleracion = a = cteAcPot . ( PotTotal2 + RPM )
	 * @return
	 */
	public double getAceleracion(){
		
		Aceleracion = CNTE_ACELERACION_POTENCIA * ( (getPotenciaTotal()*getPotenciaTotal()) + getMotor().getRPM() );
		return Aceleracion;		
	}
	
	/**
	 * getVelocidad = Vo + a.t
	 * @return
	 */
	public double getVelocidad(){
		
		Velocidad = /*velocidadInicial + */ getAceleracion() *  tiempo;
		return Velocidad;
	}
	
	/**
	 * getPosicion = V.t + 1/2 a.t2
	 * @return
	 */
	public double getPosicion() {
		
		Posicion += getVelocidad() * (0.00000006) + (0.5)*(getAceleracion());
		return Posicion;
	}
	
	/* mas adelante hay que poner este metodo como PRIVATE */
	public void setPosicion(double posicion) {
		Posicion = posicion;
	}

	/**
	 * para cada instante puede decirnos cual
	 * es la potencia final del automovil en HP ---> 1 HP = 746W
	 * 
	 * @return
	 */
	public double getPotenciaTotal(){
		double potencia=0; 
		//seteo la velocidad de la carroceria
		this.getCarroceria().setVelocidad(Velocidad); 
		//hay componente que solo aportan potencia al estar encendido el auto
		if(this.isEncendido()){ 
		   potencia=motor.obtenerPotencia() +   /* de aca salen: Alimentacion, Combustible*/
		            caja.obtenerPotencia() +
		            suspension.obtenerPotencia() +
		            escape.obtenerPotencia() +
		         //   ejeDelantero.obtenerPotencia() +//de aca salen: llantas y neumaticos delanteros
		         //   ejeTrasero.obtenerPotencia() + //de aca salen: llantas y neumaticos traseros
		            turbo.obtenerPotencia();
		} 
		//en caso de que el auto se este moviendo la carroceria aporta potencia negativa
		potencia=potencia+getCarroceria().obtenerPotencia(); 
		return(potencia);
	}
	
	/**
	 * es invocado en cada ciclo durante la carrera, se encarga
	 * de deteriorar los componentes del auto
	 */
	public void Desgastar(){
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			it.next().desgastar();
		}
	}	
	
	/**
	 * estaListoParaCarrera
	 * @return
	 */
	public boolean estaListoParaCarrera() {
		boolean listo = true;
		try{
			comprobarComponentes();
		}catch (Exception e){
		/*	System.out.println("" +
	        "No esta listo para carrera "
		    + e.getClass().getName()  + " " 
		    + e.getMessage());   */
			return false;
		}
		return listo;
	}
	
	/**
	 * comprobarComponentes
	 * @return the listoParaCarrera
	 */
	public void comprobarComponentes() 
	  throws ExceptionComponenteFaltante, ExceptionComponenteDesgastado{
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Componente aux = it.next();
			if( aux == null) throw new ExceptionComponenteFaltante();
			if(aux.getEstado()==0)throw new ExceptionComponenteDesgastado(aux.getClass().getName());
		}
	}

	/**
	 * setEncendido
	 * @param encendido
	 */
	public void setEncendido(boolean encendido) {
	 	if(estaListoParaCarrera()){
	 		if (encendido==true){
	 			getMotor().encender();
	 		}else{
	 			getMotor().apagar();
	 		}
	 	}
	}
	
	/**
	 * isEncendido
	 * @return
	 */
	public boolean isEncendido() {
		if(estaListoParaCarrera())
			return(getMotor().isEncendido());
		else
			return(false);
	}

	/**
	 * acelerar
	 * @param valor
	 */
	public void acelerar(boolean valor){
		if(estaListoParaCarrera())
			getMotor().acelerar(valor);
	}
	
	public boolean isAcelerando(){
		
		return(getMotor().isAcelerando());
	}
	
	/**
	 * getEstado
	 * @return
	 */
	public double getEstado() {
		
		double Estado = 0;
		
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Estado = Estado + it.next().getEstado();
		}
		
		return Estado;
	}

	/**
	 * se afectan los componentes del auto por el clima
	 * - esta sobrecargada con Superficie
	 *
	 * @param clima
	*/
	public void afectar(Clima clima){
		LinkedList<AfectablePorClima> lista = this.obtenerAfectablesPorClima();
		Iterator<AfectablePorClima> it = lista.iterator();
		while (it.hasNext()){
			it.next().afectar(clima);
		}
	}
	
	/**
	 * se afectan los componentes del auto por la superficie
	 * - esta sobrecargada con Clima
	 * 
	 * @param superficie
	 */
	public void afectar(Superficie superficie){
		LinkedList<AfectablePorSuperficie> lista = this.obtenerAfectablesPorSup();
		Iterator<AfectablePorSuperficie> it = lista.iterator();
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

	public LinkedList<Componente> obtenerComponentes(){
		
		LinkedList<Componente> lista =  new LinkedList<Componente>();
		lista.add(this.alimentacion);
		lista.add(this.caja);
		lista.add(this.carroceria);
		lista.add(this.combustible);
		lista.add(this.escape);
		lista.add(this.motor);
		lista.add(this.suspension);
		lista.add(this.turbo);
		lista.add(this.ejeDelantero);
		lista.add(this.ejeDelantero.getLlantaDerecha());
		lista.add(this.ejeDelantero.getLlantaIzquierda());
		lista.add(this.ejeDelantero.getNeumaticoDerecho());
		lista.add(this.ejeDelantero.getNeumaticoIzquierdo());
		lista.add(this.ejeTrasero.getLlantaDerecha());
		lista.add(this.ejeTrasero.getLlantaIzquierda());
		lista.add(this.ejeTrasero.getNeumaticoDerecho());
		lista.add(this.ejeTrasero.getNeumaticoIzquierdo());
		lista.add(this.ejeTrasero);

		return lista;
	}

	public LinkedList<AfectablePorSuperficie> obtenerAfectablesPorSup(){
		LinkedList<AfectablePorSuperficie> listaAS = new LinkedList<AfectablePorSuperficie>();
		LinkedList<Componente> listaComp = this.obtenerComponentes();
		Iterator<Componente> it = listaComp.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecSuperficie(listaAS);
		}
		return listaAS;
	}
		
	public LinkedList<AfectablePorClima> obtenerAfectablesPorClima(){
		LinkedList<AfectablePorClima> listaAC = new LinkedList<AfectablePorClima>();
		LinkedList<Componente> listaComp = this.obtenerComponentes();
		Iterator<Componente> it = listaComp.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecClima(listaAC);
		}
		return listaAC;
	}
	
	/* setters y getters */

	public void setEjeDelantero(Eje ejeDelantero) {
	 
		ejeDelantero.instalar(this);
		try{	
		  ejeDelantero.setLlantaDerecha(this.getEjeDelantero().getLlantaDerecha());
		  ejeDelantero.setLlantaIzquierda(this.getEjeDelantero().getLlantaIzquierda());
		  ejeDelantero.setNeumaticoDerecho(this.getEjeDelantero().getNeumaticoDerecho());
		  ejeDelantero.setNeumaticoIzquierdo(this.getEjeDelantero().getNeumaticoIzquierdo());
		}catch(Exception e){}
		this.ejeDelantero = ejeDelantero;
	 
	}

	public Eje getEjeDelantero() {
		return ejeDelantero;
	}
	
	public void setEjeTrasero(Eje ejeTrasero) {
	 
		ejeTrasero.instalar(this);
		try{
		  ejeTrasero.setLlantaDerecha(this.getEjeTrasero().getLlantaDerecha());
		  ejeTrasero.setLlantaIzquierda(this.getEjeTrasero().getLlantaIzquierda());
		  ejeTrasero.setNeumaticoDerecho(this.getEjeTrasero().getNeumaticoDerecho());
		  ejeTrasero.setNeumaticoIzquierdo(this.getEjeTrasero().getNeumaticoIzquierdo());
		}catch(Exception e){}
		this.ejeTrasero = ejeTrasero;
	 
	}

	public Eje getEjeTrasero() {
		return ejeTrasero;
	}
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		combustible.instalar(this);
		this.combustible = combustible;
	}

	public void setSuspension(Suspension suspension) {
		this.suspension = suspension;
		suspension.instalar(this);
	}

	public Suspension getSuspension() {
		return suspension;
	}
	
	public void setCaja(Caja caja){
		caja.instalar(this);
		this.caja = caja;
	}

	public Caja getCaja(){
		return this.caja;
	}

	public void setMotor(Motor motor){
		motor.instalar(this);
		this.motor = motor;
	}
	
	public Motor getMotor(){
		return this.motor;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		alimentacion.instalar(this);
		this.alimentacion = alimentacion;
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setCarroceria(Carroceria carroceria) {
		carroceria.instalar(this);
		this.carroceria = carroceria;
	}
	
	public Carroceria getCarroceria() {
		return carroceria;
	}

	public void setEscape(Escape escape) {
		escape.instalar(this);
		this.escape = escape;
	}

	public Escape getEscape() {
		return escape;
	}

	public void setTurbo(Turbo turbo) {
		turbo.instalar(this);
		this.turbo = turbo;
	}
	
	public Turbo getTurbo() {
		return turbo;
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
		
	
	
}