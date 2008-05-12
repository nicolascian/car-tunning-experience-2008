package modelo;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

/**
 * Documentacion
 * 
 * @version	1.0
 */
import java.util.*;

/**
 * Documentacion
 * 
 * @version 1.0
 */
public class Auto implements AfectablePorClima, AfectablePorSuperficie{
	/* comentario acerca de la implementacion de la clase */
	
	private double CNTE_ACELERACION_POTENCIA = 0.000003;
	
	private double Velocidad; // integral de Aaceleracion
	private double Aceleracion; // = Potencia de las RPM cuadrado y algo mas
	private double Posicion; // dstancia recorrida
	
	private Motor motor;
	private Caja caja;
	private Combustible combustible;
	private Alimentacion alimentacion;
	private Carroceria carroceria;
	private Suspension suspension;	
	private Escape escape;
	private Turbo turbo;
	
	private Eje ejeDelantero;
	private Eje ejeTracero;
			
	/**
	 * constructor por defecto
	 *
	 */
	public Auto(){
		
		//creacion de componentes
		
		setMotor(new Motor(4,1600,8000));
		setCaja(new Manual(5));
		setCombustible(new Combustible(50.0,0.4));
		setCarroceria(new Carroceria(2.4));
		setAlimentacion(new Carburador());
		setSuspension(new Suspension());
		setEscape(new Escape());
		setTurbo(new Turbo());
		setEjeDelantero(new Eje());
		setEjeTracero(new Eje());
		setEncendido(false);
	}
	
	/**
	 * constructor
	 *
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
		setEjeTracero(new Eje());
		setEncendido(false);

	}

	
	/**
	 * @return the suspension
	 */
	public Suspension getSuspension() {
		return suspension;
	}


	/**
	 * @param suspension the suspension to set
	 */
	public void setSuspension(Suspension suspension) {
		this.suspension = suspension;
	}

	/*
	 * ESTO HAY Q CAMBIARLOOOOOOOOOOOOO!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!
	 */
	public double getVelocidad(){
		
		Velocidad = this.getAceleracion() * 20;
		
		return Velocidad;
	}
	
	public double getAceleracion(){
		
		Aceleracion = CNTE_ACELERACION_POTENCIA * (getPotenciaTotal()*getPotenciaTotal());
		
		return Aceleracion;
		
	}

	/**
	 * para cada instante puede decirnos cual
	 * es la potencia final del automovil en HP ---> 1 HP = 746W

	 * 
	 * @return
	 */
	public double getPotenciaTotal(){
		//recorremos los componentes pertinentes, y hacemos:
		// componente.obtenerPotencia();
		return ( motor.obtenerPotencia() +   /* de aca salen: Alimentacion, Combustible*/
		         carroceria.obtenerPotencia() +
		         caja.obtenerPotencia() +
		         suspension.obtenerPotencia() +
		         escape.obtenerPotencia() +
		         //ejeDelantero.obtenerPotencia() +//de aca salen: llantas y neumaticos delanteris
		         //ejeTrasero.obtenerPotencia() + //de aca salen: llantas y neumaticos traseros
		         turbo.obtenerPotencia()
		            );
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
		lista.add(this.ejeTracero.getLlantaDerecha());
		lista.add(this.ejeTracero.getLlantaIzquierda());
		lista.add(this.ejeTracero.getNeumaticoDerecho());
		lista.add(this.ejeTracero.getNeumaticoIzquierdo());
		lista.add(this.ejeTracero);

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
			
	/* setters y getters */
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		combustible.instalar(this);
		this.combustible = combustible;
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

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		alimentacion.instalar(this);
		this.alimentacion = alimentacion;
	}

	public double getPosicion() {
		return Posicion;
	}
	
	private void setPosicion(double posicion) {
		Posicion = posicion;
		
	}

	/**
	 * @return the carroceria
	 */
	public Carroceria getCarroceria() {
		return carroceria;
	}

	/**
	 * @param carroceria the carroceria to set
	 */
	public void setCarroceria(Carroceria carroceria) {
		carroceria.instalar(this);
		this.carroceria = carroceria;
	}

	/**
	 * @return the listoParaCarrera
	 */
	public void comprobarComponentes() 
	  throws ExceptionComponenteFaltante, ExceptionComponenteDesgastado{
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Componente aux = it.next();
			if( aux == null) throw new ExceptionComponenteFaltante(aux.getClass().getName());
			if(aux.getEstado()==0)throw new ExceptionComponenteDesgastado(aux.getClass().getName());
		}
	}
	
	
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
	 * @Pre: La instancia de la clase Auto ha sido creada.
	 * @Post: Se verifica si la instancia se encuentra lista para correr y en
	 * ese caso setea como lista para carrera.
	 */
	public void actualizarListoParaCarrera(){
		
	}

	/**
	 * @return the escape
	 */
	public Escape getEscape() {
		return escape;
	}

	/**
	 * @param escape the escape to set
	 */
	public void setEscape(Escape escape) {
		escape.instalar(this);
		this.escape = escape;
	}

	/**
	 * @return the turbo
	 */
	public Turbo getTurbo() {
		return turbo;
	}

	/**
	 * @param turbo the turbo to set
	 */
	public void setTurbo(Turbo turbo) {
		turbo.instalar(this);
		this.turbo = turbo;
	}

	public double getEstado() {
		
		double Estado = 0;
		
		LinkedList<Componente> lista = this.obtenerComponentes();
		Iterator<Componente> it = lista.iterator();
		while (it.hasNext()){
			Estado = Estado + it.next().getEstado();
		}
		
		return Estado;
	}

	public Eje getEjeDelantero() {
		return ejeDelantero;
	}

	public void setEjeDelantero(Eje ejeDelantero) {
		ejeDelantero.instalar(this);
		this.ejeDelantero = ejeDelantero;
	}

	public Eje getEjeTracero() {
		return ejeTracero;
	}

	public void setEjeTracero(Eje ejeTracero) {
		ejeTracero.instalar(this);
		this.ejeTracero = ejeTracero;
	}

	/**
	 * @return the encendido
	 */
	public boolean isEncendido() {
		if(getMotor()!=null)
			return(getMotor().isEncendido());
		else
			return(false);
	}

	/**
	 * @param encedido the encendido to set
	 */
	public void setEncendido(boolean encendido) {
		if(getMotor()!=null)
			getMotor().setEncendido(encendido);
	}

	public void acelerar(boolean valor){
		getMotor().acelerar(valor);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return super.toString();
	}
	
	
	
	
}