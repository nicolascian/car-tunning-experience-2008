package modelo;
import java.util.*;
/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
	/**
	 * La pista es el lugar donde se desarrolla la carrera.
	 * Tiene como caracteristicas una sucesion de tramos,
	 * cada uno con una longitud y caracteristicas especificas.
	 */
public class Pista{
	/*--------Atributos--------*/
	/**
	 * La pista tiene una referencia a los dos corredores
	 * que se encuentran compitiendo en ella
	 */	
	private Jugador[] Jugador;
	private int cantJugadores;
	/**
	 * La pista esta compuesta por una serie de tramos, cada uno con 
	 * distintascaracteristicas (superficie, clima) cuya longitud suma
	 * la longitud total.
	 */
	private double Longitud;
	private ArrayList<Tramo> Tramos;
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
	
	/*---------Metodos---------*/
	/**
	 * Constructor que recibe como parametros dos jugadores y la longitud
	 * Genera un solo tramo con condiciones por defecto. 
	 * pre:-
	 * post: Queda creada una instacia de Pista.
	 */
	Pista(Jugador j1,Jugador j2, double longitud){
		Tramos = new ArrayList<Tramo>();
		Tramos.add(new Tramo(longitud));
		Longitud = longitud;
		Jugador = new Jugador[2];
		Jugador[1]=j1;
		Jugador[2]=j2;
		iterador = new Iterator[2];
		iterador[1]= Tramos.iterator();
		iterador[2]= Tramos.iterator();
		tramoActual = new Tramo[2];
		tramoActual[1]= Tramos.get(1);
		tramoActual[2]= Tramos.get(1);
		cantJugadores = 2;
		for (int i = 0; i<cantJugadores; i++){
			Jugador[i].getAuto().afectar(tramoActual[i].getClima());
			Jugador[i].getAuto().afectar(tramoActual[i].getSuperficie());
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
		for (int i=0;i<this.getCantJugadores();i++){
			Tramo aux;
			if (this.jugadorCambioTramo(i)){
				try{
					aux = this.buscarTramoActual(i);
				}catch (ExceptionFinPista e){
					throw e;
				}
			this.setTramoActual(i,aux);
			this.getJugador(i).getAuto().afectar(aux.getClima());
			this.getJugador(i).getAuto().afectar(aux.getSuperficie());
			}
		}
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
	 * @return
	 * @throws ExceptionFinPista
	 */
	public Tramo buscarTramoActual(int nroJugador) throws ExceptionFinPista{
		while (iterador[nroJugador].hasNext()){
			Tramo aux = iterador[nroJugador].next();
			if (this.getJugador(nroJugador).getAuto().getPosicion() 
					< aux.getPosFinal())return aux;
		}
		throw new ExceptionFinPista();
	}
	/**
	 * Metodo que se encarga de verificar si un auto cambio de tramo, es decir
	 * si se debe desplazar hacia otro tramo distinto de su actual
	 * pre: El auto correspondiente al numero de jugador pasado como parametro
	 * debe estar en una tramo valido.
	 * @param nroJugador
	 * @return
	 */
	public boolean jugadorCambioTramo(int nroJugador){
		return (this.getJugador(nroJugador).getAuto().getPosicion() 
				< this.getTramoActual(nroJugador).getPosFinal());
	}
	
	public double getLongitud() {
		return Longitud;
	}

	public void setLongitud(double longitud) {
		Longitud = longitud;
	}

	public ArrayList<Tramo> getTramos() {
		return Tramos;
	}

	public void setTramos(ArrayList<Tramo> tramos) {
		Tramos = tramos;
	}
	
	
	public void setTramoActual(int nroTramo, Tramo nuevo){
		this.tramoActual[nroTramo]= nuevo;
	}
	
	public Tramo getTramoActual(int nroTramo){
		return this.tramoActual[nroTramo];
	}
	
	public Jugador getJugador(int nroJugador){
		return this.Jugador[nroJugador];
	}
	
	public void setJugador(int nroJugador, Jugador jugador){
		this.Jugador[nroJugador] = jugador;
	}
	

	public int getCantJugadores() {
		return cantJugadores;
	}
	
	

	public void setCantJugadores(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}
}