/**
 * 
 */
package modelo.componente;

/**
 * @Descripcion:
 * 				Modela un componente que esta contenido en otro componente.
 *
 */
public interface ComponenteContenidoEnComponente {
	
	public Componente getComponenteContenedor();
	
	public void setComponenteContenedor(Componente contenedor);

}
