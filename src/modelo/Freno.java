/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Un freno es un dispositivo utilizado para detener o disminuir el movimiento 
 * de algún cuerpo, generalmente, un eje, árbol o tambor. Los frenos son 
 * transformadores de energía, por lo cual pueden ser entendidos como una máquina 
 * per se, ya que transforman la energía cinética de un cuerpo en calor o trabajo 
 * y en este sentido pueden visualizarse como “extractores“ de energía. A pesar de
 * que los frenos son también máquinas, generalmente se les encuentra en la 
 * literatura del diseño como un elemento de máquina y en literaturas de teoría de 
 * control pueden encontrarse como actuadores.
 * 
 * heredan:
 * freno de cinta o de banda
 * freno de disco
 * freno ABS (Antilock Brake System)
 * 
 * @version 1.0
 * @see <a href="http://es.wikipedia.org/wiki/Freno">Freno - Wikipedia</a>
 * 
 * @see <a href="http://es.wikipedia.org/wiki/Freno#Frenos_de_fricci.C3.B3n">Freno de Cinta - Wikipedia</a>
 * @see <a href="http://es.wikipedia.org/wiki/Freno_de_disco">Freno de Disco - Wikipedia</a>
 * @see <a href="http://es.wikipedia.org/wiki/Antilock_Brake_System">Freno ABS - Wikipedia</a>
 */
public abstract class Freno extends Componente implements AfectablePorClima{

	private boolean frenando = false;
	
	protected double EfectoClimatico = 1;
	
	public Freno(){
		setEstado(100);
	}
	
	public boolean isFrenando(){
		return frenando;
	}
	
	public void frenar(boolean valor){
		frenando = valor;
		desgastar();
	}
	
	
	public abstract void desgastar();
	public abstract void afectar(Clima clima);
	
	
	@Override
	public double obtenerPotencia() {
		return 0;
	}

	
	
}
