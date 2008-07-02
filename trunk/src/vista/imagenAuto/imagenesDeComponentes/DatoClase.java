package vista.imagenAuto.imagenesDeComponentes;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import javax.swing.ImageIcon;
public class DatoClase{
	
	private String nombre;
	
	private String rutaImagen;
	
	private Class clase=null;
	
	private Imagen imagen=null;

	private ImageIcon icono=null;
	
	public DatoClase(Class clase,String nombre,String rutaImagen){
		this.setClase(clase);
		this.setRutaImagen(rutaImagen);
		this.setNombre(nombre);
		try{
		   this.imagen=new Imagen(this.rutaImagen,new Dimension(200,150),new Posicion());
		   icono= new ImageIcon(imagen.getImage().getScaledInstance(40,40, java.awt.Image.SCALE_DEFAULT));
		}catch(Exception e){};
	}
		
	/**
	 * @return the icono
	 */
	public ImageIcon getIcono() {
		return icono;
	}

	/**
	 * @return the imagen
	 */
	public Imagen getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
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