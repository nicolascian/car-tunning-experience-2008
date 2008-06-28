
package vista.imagenAuto;

import modelo.*;
import java.awt.*;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import modelo.Constantes;

import java.util.Observable;
import java.util.Observer;
public class ImagenAuto extends Component{
          
	private final static int OSCILACION=1;
	
	private final static int MAXIMO_CONTADOR=5;
	
	private Auto auto;
		
    private Imagen imagenAuto=null;
    
    private Posicion posicion=null;
    
    private Dimension dimension=null;
    
    private int contador=0;
    
    private long tiempoProximaActualizacion=0;
    
    public ImagenAuto( Auto auto, String ruta,Dimension dimension,
    		          Posicion posicion) {
       this.auto= auto;
       this.dimension=new Dimension(dimension);
       this.posicion=new Posicion(posicion);
       this.imagenAuto=new Imagen(ruta,dimension,posicion);
    }
    
    protected int obtenerOscilacion(){
    	if(contador<=MAXIMO_CONTADOR){
    	  contador++;
    	  return 0;
    	}
    	else{
    	  contador=0;
    	  return OSCILACION;
    	}
    }
    
    public void paint(Graphics g) {
       	try{	
    		((Graphics2D)g).drawImage(imagenAuto.getImage(),this.getPosicion().getX(),
    				                  this.getPosicion().getY()+this.obtenerOscilacion(),
    		                          this.getDimension().width,this.getDimension().height,this);
    	}catch(Exception e){};
    }

    public void update(Graphics g){
    	if(System.currentTimeMillis()>=this.tiempoProximaActualizacion){
    	  paint(g);
    	  this.tiempoProximaActualizacion+=System.currentTimeMillis()+Constantes.TIEMPO_DE_ACTUALIZACION;
    	}
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
	 * @return the imagenAuto
	 */
	public Imagen getImagenAuto() {
		return imagenAuto;
	}

	/**
	 * @param imagenAuto the imagenAuto to set
	 */
	public void setImagenAuto(Imagen imagenAuto) {
		this.imagenAuto = imagenAuto;
	}

	/**
	 * @return the posicion
	 */
	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the tiempoProximaActualizacion
	 */
	protected long getTiempoProximaActualizacion() {
		return tiempoProximaActualizacion;
	}

	/**
	 * @param tiempoProximaActualizacion the tiempoProximaActualizacion to set
	 */
	protected void setTiempoProximaActualizacion(long tiempoProximaActualizacion) {
		this.tiempoProximaActualizacion = tiempoProximaActualizacion;
	}
    
}

