
package vista.imagenAuto;

import modelo.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;

public class ImagenAuto extends Component {
          
	Auto auto;
		
    Imagen imagenAuto=null;
    
    Posicion posicion=null;
    
    Dimension dimension=null;
    
    public ImagenAuto( Auto auto, String ruta,Dimension dimension,
    		          Posicion posicion) {
       this.auto= auto;
       this.dimension=new Dimension(dimension);
       this.posicion=new Posicion(posicion);
       this.imagenAuto=new Imagen(ruta,dimension,posicion);
    }
    
    public void paint(Graphics g) {
    	
    }

    public void update(Graphics g){
    	paint(g);
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
    
}

