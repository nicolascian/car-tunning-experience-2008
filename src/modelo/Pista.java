/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;
import java.util.*;

import modelo.exceptions.ExceptionFinPista;

/**
 * La pista es el lugar donde se desarrolla la carrera.
 * Tiene como caracteristicas una sucesion de tramos,
 * cada uno con una longitud y caracteristicas especificas.
 */
public class Pista  extends Observable{
	
	/*--------Atributos--------*/
	/**
	 * La pista tiene una referencia a los autos de los corredores
	 * que se encuentran compitiendo en ella
	 */	
	private Auto[] auto;
	
	private int cantAutos;
	
	private String rutaAparienciaCielo="src//vista//imagenTramo//cieloSemiNublado";
	
	private String rutaAparienciaCamino="src//vista//imagenTramo//carretera2";
	
	private String rutaAparienciaEntorno="src//vista//imagenTramo//pasto";
	
	/**
	 * La pista esta compuesta por una serie de tramos, cada uno con 
	 * distintas caracteristicas (superficie, clima) cuya longitud suma
	 * la longitud total.
	 * Los tramos deben estar en orden dentro de la lista.
	 */
	private double longitud;
	private ArrayList<Tramo> tramos;
	
	/**
	 * tramosActuales es un vector de tramos que representa los tramos
	 * en los que se encuentran cada uno de los autos en un determinado
	 * instante.
	 * el tramoActual[i] corresponde al Jugador[i]
	 */
	private Tramo[] tramoActual;
	
	/**
	 * Los iteradores se utilizaran para ir recorriendo la lista de tramos
	 * y actualizar los tramos actuales de cada auto.
	 */
	private Iterator<Tramo>[] iterador;
	
	/**
	 * se ejecuta cuando se cambia de tramo
	 */
	public void ActualizarObservadores()
	{
		setChanged();
		notifyObservers();		
	}
	
	/*---------Metodos---------*/
	/**
	 * Constructor que recibe como parametros dos jugadores y la longitud
	 * Genera un solo tramo con condiciones por defecto. 
	 * pre:-
	 * post: Queda creada una instacia de Pista.
	 */
	public Pista(Auto A1,Auto A2, double largo){
		longitud = largo;
		tramos = new ArrayList<Tramo>();
		tramos.add(new Tramo(longitud));
		auto = new Auto[2];
		auto[0]=A1;
		auto[1]=A2;
		iterador = new Iterator[2];
		iterador[0]= tramos.iterator();
		iterador[1]= tramos.iterator();
		tramoActual = new Tramo[2];
		tramoActual[0]= tramos.get(0);
		tramoActual[1]= tramos.get(0);
		cantAutos = 2;
		for (int i = 0; i<cantAutos; i++){
			auto[i].afectar(tramoActual[i].getClima());
			auto[i].afectar(tramoActual[i].getSuperficie());
		}
	}
	
	/**
	 * Constructor con parametrosSystem.out.print("Finaliz� la carrera");
	 * pre: La lista "tramos" debe estar ordenada, y el principio de cada
	 * tramo debe coincidir con el final del anterior.
	 * post: Queda creada una instancia de Pista.
	 */
	public Pista(Auto[] autos,ArrayList<Tramo> Tramos){
		tramos = Tramos;
		auto = autos;
		cantAutos = autos.length;
		iterador = new Iterator[cantAutos];
		tramoActual = new Tramo[cantAutos];
		for (int i=0;i<cantAutos;i++){
			iterador[i]= Tramos.iterator();
			tramoActual[i] = Tramos.get(0);
			auto[i].afectar(tramoActual[i].getClima());
			auto[i].afectar(tramoActual[i].getSuperficie());
		}
		longitud = 0;
		Iterator<Tramo> it = Tramos.iterator();
		while(it.hasNext()){
			longitud = it.next().getPosFinal();
		}
	}
	
	/**
	 * Metodo que se encarga de actualizar el tramo en el que debe estar cada uno de
	 * los autos.
	 * pre: los autos deben estar ubicados en tramos validos.
	 * post: quedan modificados los tramos actuales de todos los autos
	 * 	Si algun auto esta en una posicion mayor a la longitud de la pista,
	 *  se lanza la excepcion "ExceptionFinPista". 
	 *  Si se realiza un cambio de tramo, se afecta al auto correspondiente
	 *  por clima y por superficie.
	 * @throws ExceptionFinPista
	 */
	public void actualizarPosiciones() throws ExceptionFinPista{
		for (int i=0;i<this.getCantAutos();i++){
			Tramo aux;
			if (this.autoCambioTramo(i)){
				try{
					aux = this.buscarTramoActual(i);
				}catch (ExceptionFinPista e){
					throw e;
				}
			this.setTramoActual(i,aux);
			this.getAuto(i).afectar(aux.getClima());
			this.getAuto(i).afectar(aux.getSuperficie());
			}
		}
		//notifico a los observadores
		ActualizarObservadores();
	}
	
	/**
	 * Metodo que dada la posicion del auto de un determinado jugador se
	 * encarga de buscar el Tramo en el que deberia estar ubicado.
	 * pre: la posicion del auto debe ser menor o igual al largo de la pista.
	 * post: el iterador correspondiente al numero de jugador queda modificado,
	 *   avanzando hasta el nuevo tramo actual.
	 *   Se devuelve el tramo en el que se debe posicionar el auto. En caso de 
	 *   que el auto este en una posicion mayor a la longitud de la pista,
	 *   se lanza la excepcion "ExceptionFinPista". 
	 * @param nroJugador
	 * @throws ExceptionFinPista
	 */
	public Tramo buscarTramoActual(int nroAuto) throws ExceptionFinPista{
		while (iterador[nroAuto].hasNext()){
			Tramo aux = iterador[nroAuto].next();
			if (this.getAuto(nroAuto).getPosicion() 
					< aux.getPosFinal())return aux;
		}
		throw new ExceptionFinPista(nroAuto);
	}
	/**
	 * Metodo que se encarga de verificar si un auto cambio de tramo, es decir
	 * si se debe desplazar hacia otro tramo distinto de su actual
	 * pre: El auto correspondiente al numero de jugador pasado como parametro
	 * debe estar en una tramo valido.
	 * @param nroJugador
	 */
	public boolean autoCambioTramo(int nroAuto){
		return !(this.getAuto(nroAuto).getPosicion() 
				< this.getTramoActual(nroAuto).getPosFinal());
	}
	
	public String toString(){
		String cadena = "Pista de "+this.getLongitud()+ " metros, compuesta por "
						+ this.getTramos().size() + " tramos.";
		return cadena;
	}
	
	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double Longitud) {
		longitud = Longitud;
	}

	public ArrayList<Tramo> getTramos() {
		return tramos;
	}

	public void setTramos(ArrayList<Tramo> Tramos) {
		tramos = Tramos;
	}
	
	
	public void setTramoActual(int nroTramo, Tramo nuevo){
		this.tramoActual[nroTramo]= nuevo;
	}
	
	public Tramo getTramoActual(int nroTramo){
		return this.tramoActual[nroTramo];
	}

	public Auto getAuto(int nroAuto) {
		return auto[nroAuto];
	}

	public void setAuto(Auto auto, int nroAuto) {
		this.auto[nroAuto] = auto;
	}

	public int getCantAutos() {
		return cantAutos;
	}

	public void setCantAutos(int cantAutos) {
		this.cantAutos = cantAutos;
	}

	/**
	 * @return the rutaAparienciaCielo
	 */
	public String getRutaAparienciaCielo() {
		return rutaAparienciaCielo;
	}

	/**
	 * @param rutaAparienciaCielo the rutaAparienciaCielo to set
	 */
	public void setRutaAparienciaCielo(String rutaAparienciaCielo) {
		this.rutaAparienciaCielo = rutaAparienciaCielo;
	}

	/**
	 * @return the rutaAparienciaCamino
	 */
	public String getRutaAparienciaCamino() {
		return rutaAparienciaCamino;
	}

	/**
	 * @param rutaAparienciaCamino the rutaAparienciaCamino to set
	 */
	public void setRutaAparienciaCamino(String rutaAparienciaCamino) {
		this.rutaAparienciaCamino = rutaAparienciaCamino;
	}

	/**
	 * @return the rutaAparienciaEntorno
	 */
	public String getRutaAparienciaEntorno() {
		return rutaAparienciaEntorno;
	}

	/**
	 * @param rutaAparienciaEntorno the rutaAparienciaEntorno to set
	 */
	public void setRutaAparienciaEntorno(String rutaAparienciaEntorno) {
		this.rutaAparienciaEntorno = rutaAparienciaEntorno;
	}
	

}