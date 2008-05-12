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
	}

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
			}
		}
	}
	public Tramo buscarTramoActual(int nroJugador) throws ExceptionFinPista{
		while (iterador[nroJugador].hasNext()){
			Tramo aux = iterador[nroJugador].next();
			if (this.getJugador(nroJugador).getAuto().getPosicion() 
					< aux.getPosFinal())return aux;
		}
		throw new ExceptionFinPista();
	}
	
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