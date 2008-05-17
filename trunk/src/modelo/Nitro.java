/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * Durante el proceso de la combustión en un encendido, a unos 300 grados Celsios 
 * el Óxido Nitroso simplemente se descompone en Nitrógeno y Oxígeno.
 *
 * Es el Oxígeno extra el que crea poder adicional al permitir que más combustible 
 * sea quemado. El poder siempre viene del combustible, tener esto en cuenta!
|*
|* Si Usted agrega solamente Óxido Nitroso y no agrega combustible no conseguirá 
|* potencia extra. Puede aumentar el rango de velocidad de su vehículo quemando 
 * combustible, eso conduce a detonaciones destructivas del sistema de combustión 
 * del automóvil. 
 *
 * @version 1.0
 * @see <a href="http://tuning.deautomoviles.com.ar/articulos/oxido_nitroso.html">Oxido Nitroso</a> 
 */
public class Nitro extends Componente {

	private boolean activado= false;
	
	public Nitro(){
		setEstado(100);
	}
	
	public Nitro(double estado){
		setEstado(estado);
	}
	
	public void activar(boolean valor){
		activado = valor;
	}
	
	public boolean isActivado(){
		return activado;
	}
	
	@Override
	public void desgastar() {
		setEstado(getEstado() - 0.5);
		
	}

	@Override
	public double obtenerPotencia() {
		if(isActivado()){
			return 250;
		}else{
			return 0;
		}
	}

	
	
}
