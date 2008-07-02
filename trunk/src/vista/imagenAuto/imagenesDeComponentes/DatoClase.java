package vista.imagenAuto.imagenesDeComponentes;

public class DatoClase{
	
	private String nombre;
	
	private String rutaImagen;
	
	private Class clase;

	public DatoClase(Class clase,String nombre,String rutaImagen){
		this.setClase(clase);
		this.setRutaImagen(rutaImagen);
		this.setNombre(nombre);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(((DatoClase)obj).getClase()==this.getClase())
		  return true;
		else
		  return false;
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

	/**
	 * @return the rutaImagen
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * @param rutaImagen the rutaImagen to set
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	/**
	 * @return the clase
	 */
	public Class getClase() {
		return clase;
	}

	/**
	 * @param clase the clase to set
	 */
	public void setClase(Class clase) {
		this.clase = clase;
	}	
}