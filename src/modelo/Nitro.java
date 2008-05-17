/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package modelo;

/**
 * 
 *
 */
public class Nitro extends Componente {

	private boolean activado= false;
	
	public Nitro(){
		setEstado(100);
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
