package vista.imagenAuto;
/**
 * 
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import vista.imagenTramo.Imagen;
import modelo.Auto;
import vista.imagenAuto.ImagenAuto;
import vista.imagenTramo.Posicion;
import vista.imagenRueda.*;
import modelo.Constantes;
/**
 * @author Usuario
 *
 */
public class ImagenAutoDesdeAtras extends ImagenAuto {

	private ImagenRueda ruedaIzq=null;
	
	private ImagenRueda ruedaDer=null;
	
	/**
	 * @param auto
	 * @param ruta
	 * @param dimension
	 * @param posicion
	*/
	public ImagenAutoDesdeAtras(Auto auto, String ruta, Dimension dimension,
			                     Posicion posicion) {
		super(auto, ruta, dimension, posicion);
		Dimension dimensionRuedas=ImagenRueda.createDimensionRuedaMedianaParaAuto(dimension);
		Posicion posicionRueda=new Posicion((int)(posicion.getX()+dimension.width*0.05),
				                            (int)(posicion.getY()+dimension.getHeight()-
				                             dimensionRuedas.getHeight()*0.9));
		this.setRuedaIzq(ImagenRueda.createImagenRuedaNeumaticoDibujo2(dimensionRuedas,
				         posicionRueda));
		posicionRueda.setX((int)(posicion.getX()+dimension.getWidth()*0.95-dimensionRuedas.getWidth()));
		this.setRuedaDer(ImagenRueda.createImagenRuedaNeumaticoDibujo2(dimensionRuedas,
		         posicionRueda));	
	}
	
	/* (non-Javadoc)
	 * @see vista.imagenAuto.ImagenAuto#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
	 if(System.currentTimeMillis()>=this.getTiempoProximaActualizacion()){
		try{
			Imagen imagenAux=this.ruedaIzq.getImagen();
    		((Graphics2D)g).drawImage(imagenAux.getImage(),imagenAux.getPosicion().getX(),
    				                  imagenAux.getPosicion().getY(),imagenAux.getDimension().width,
    				                  imagenAux.getDimension().height,this);
    		imagenAux=this.ruedaDer.getImagen();
    		((Graphics2D)g).drawImage(imagenAux.getImage(),imagenAux.getPosicion().getX(),
    				                  imagenAux.getPosicion().getY(),imagenAux.getDimension().width,
    				                  imagenAux.getDimension().height,this);
    	}catch(Exception e){};
    	this.setTiempoProximaActualizacion(this.getTiempoProximaActualizacion()+Constantes.TIEMPO_DE_ACTUALIZACION);
	 } 
     else{
    	ruedaIzq.getImagen();
    	ruedaDer.getImagen();
     }
     super.paint(g);
	}

	/**
	 * @return the ruedaIzq
	 */
	protected ImagenRueda getRuedaIzq() {
		return ruedaIzq;
	}
	
	/**
	 * @param ruedaIzq the ruedaIzq to set
	 */
	protected void setRuedaIzq(ImagenRueda ruedaIzq) {
		this.ruedaIzq = ruedaIzq;
	}

	/**
	 * @return the ruedaDer
	 */
	protected ImagenRueda getRuedaDer() {
		return ruedaDer;
	}

	/**
	 * @param ruedaDer the ruedaDer to set
	 */
	protected void setRuedaDer(ImagenRueda ruedaDer) {
		this.ruedaDer = ruedaDer;
	}

}
