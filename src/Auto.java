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
	
	private double Velocidad; // integral de Aaceleracion
	private double Aceleracion; // = Potencia de las RPM
	private double Posicion; // dstancia recorrida
	
	private Motor motor;
	private Caja caja;
	private Combustible combustible;
	private Alimentacion alimentacion;
	private Carroceria carroceria;
	private Suspension suspension;	
	private Escape escape;
	private Turbo turbo;
	
	/*
	 * HAY Q IMPLEMENTAR LOS EJES O VER COMO RESOLVEMOS LO DE LAS LLANTAS 
	 * Y NEUMATICOS
	 */
	private Llanta LlantaDelanteraIzquierda;
	private Llanta LlantaDelanteraDerecha;
	private Llanta LlantaTraceraIzquierda;
	private Llanta LlantaTraceraDerecha;
	
	private Neumatico NeumaticoDelanteraIzquierda;
	private Neumatico NeumaticoDelanteraDerecha;
	private Neumatico NeumaticoTraceraIzquierda;
	private Neumatico NeumaticoTraceraDerecha;

	/**
	 * constructor
	 *
	 */
	Auto(){
		
		
	}

	
	public double getVelocidad(){
		Velocidad = this.getAceleracion() * 100;
		return Velocidad;
	}
	
	public double getAceleracion(){
		Aceleracion = this.getPotenciaTotal() /10;
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
		return ( motor.obtenerPotencia() +   /* de aca salen: Caja, Alimentacion, Combustible*/
		         carroceria.obtenerPotencia() +
		         suspension.obtenerPotencia() +
		         escape.obtenerPotencia()     
		         /*ETC...*/    );
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
	
	/*
	 * FALTAN AGREGAR LAS LLANTAS Y LOS NEUMATICOS
	 */
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

		return lista;
	}

	/*
	 * FALTAN AGREGAR LAS LLANTAS Y LOS NEUMATICOS
	 */
	public LinkedList<AfectablePorSuperficie> obtenerAfectablesPorSup(){
		LinkedList<AfectablePorSuperficie> listaAS = new LinkedList<AfectablePorSuperficie>();
		LinkedList<Componente> listaComp = this.obtenerComponentes();
		Iterator<Componente> it = listaComp.iterator();
		while (it.hasNext()){
			it.next().agregarAListaAfecSuperficie(listaAS);
		}
		return listaAS;
	}
	
	/*
	 * FALTAN AGREGAR LAS LLANTAS Y LOS NEUMATICOS
	 */
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
		this.combustible = combustible;
	}

	public void setCaja(Caja caja){
		this.caja = caja;
	}

	public Caja getCaja(){
		return this.caja;
	}

	public void setMotor(Motor motor){
		this.motor = motor;
	}
	
	public Motor getMotor(){
		return this.motor;
	}

	public Alimentacion getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(Alimentacion alimentacion) {
		this.alimentacion = alimentacion;
	}

	public double getPosicion() {
		return Posicion;
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
		this.carroceria = carroceria;
	}
	
	
	
}