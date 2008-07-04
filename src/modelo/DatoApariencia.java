/**
 * 
 */
package modelo;

/**
 * @author Usuario
 *
 */
public class DatoApariencia  {

	private String ruta=null;
	
	private String nombre=null;
	
	/**
	 * 
	 */
	public DatoApariencia(String ruta, String nombre){
	
	}

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(this.getNombre().equals(((DatoApariencia)obj).getNombre())){
		  if(this.getRuta().equals(((DatoApariencia)obj).getRuta()))
			return true;
		  else
			return false;
		}
		  return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNombre()+" "+this.getRuta();
	}
	
}
