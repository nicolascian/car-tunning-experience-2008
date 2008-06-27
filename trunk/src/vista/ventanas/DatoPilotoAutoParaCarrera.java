/**
 * 
 */
package vista.ventanas;
import modelo.Auto;
import control.Jugador;

/**
 * @author Usuario
 *
 */
public class DatoPilotoAutoParaCarrera {

	private Jugador jugador=null;
	
	private Auto auto=null;
	
	private long tiempoLlegada=0;
	
	private String rutaAuto=null;
	
	public DatoPilotoAutoParaCarrera(Jugador jugador,Auto auto){
		this.setAuto(auto);
		this.setJugador(jugador);
	}

	/**
	 * @return the jugador
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * @param jugador the jugador to set
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return the auto
	 */
	public Auto getAuto() {
		return auto;
	}

	/**
	 * @param auto the auto to set
	 */
	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	/**
	 * @return the tiempoLlegada
	 */
	public long getTiempoLlegada() {
		return tiempoLlegada;
	}

	/**
	 * @param tiempoLlegada the tiempoLlegada to set
	 */
	public void setTiempoLlegada(long tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	/**
	 * @return the rutaAuto
	 */
	public String getRutaAuto() {
		return rutaAuto;
	}

	/**
	 * @param rutaAuto the rutaAuto to set
	 */
	public void setRutaAuto(String rutaAuto) {
		this.rutaAuto = rutaAuto;
	}

}
