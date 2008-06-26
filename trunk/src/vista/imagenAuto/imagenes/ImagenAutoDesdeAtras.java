/**
 * 
 */
package vista.imagenAuto.imagenes;

import java.awt.Dimension;

import modelo.Auto;
import vista.imagenAuto.ImagenAuto;
import vista.imagenTramo.Posicion;
import vista.imagenRueda.*;
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
	private ImagenAutoDesdeAtras(Auto auto, String ruta, Dimension dimension,
			Posicion posicion) {
		super(auto, ruta, dimension, posicion);
		Dimension dimensionRuedas=ImagenRueda.createDimensionRuedaMedianaParaAuto(dimension);
		Posicion posicionRuedas=new Posicion(posicion.getX(),posicion.getY());
		this.setRuedaIzq(ImagenRueda.createImagenRuedaNeumaticoDibujo2(dimensionRuedas,
				         posicion));
	}

	/**
	 * @return the anchoEje
	 */
	protected int getAnchoEje() {
		return anchoEje;
	}

	/**
	 * @param anchoEje the anchoEje to set
	 */
	protected void setAnchoEje(int anchoEje) {
		this.anchoEje = anchoEje;
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
