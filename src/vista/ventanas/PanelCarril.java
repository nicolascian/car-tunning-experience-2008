/**
 * 
 */
package vista.ventanas;

import javax.swing.JPanel;
import vista.imagenTramo.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

import vista.imagenAuto.*;
/**
 * @author Usuario
 *
 */
public class PanelCarril extends JPanel {

	private Posicion posicion=null;
	
	private DatoPilotoAutoParaCarrera datoPilotoAuto=null;
	
	private ImagenTramo imagenTramo=null;
	
	private ImagenAuto imagenAuto=null;
	
	private BufferedImage buffImage=null;
	
	private PanelCarril(Dimension dimension, Posicion posicion,
			           DatoPilotoAutoParaCarrera datoPilotoAuto){
		this.setDimension(new Dimension(dimension));
		this.setPosicion(new Posicion(posicion));
		this.setDatoPilotoAuto(datoPilotoAuto);
		this.setSize(dimension);
		this.buffImage=new BufferedImage(this.getWidth(),this.getHeight(), 
				                         BufferedImage.TYPE_INT_RGB);
	}
	
	public static PanelCarril createPanelCarrilVistaAutoDesdeAtras(Dimension dimension, 
			                  Posicion posicion,DatoPilotoAutoParaCarrera datoPilotoAuto){
		PanelCarril retorno=new PanelCarril(dimension,posicion,datoPilotoAuto);
		retorno.setImagenTramo(ImagenTramo.createTramoAsfaltoCespedDiaAlgoNublado(ImagenTramo.createDimensionOptima(),
                               new Posicion()));
		retorno.setImagenAuto(new ImagenAutoDesdeAtras(datoPilotoAuto.getAuto(),
		    datoPilotoAuto.getRutaAuto()+"//atras.png",new Dimension((int)(dimension.getWidth()*0.3375),
		    (int)(dimension.getWidth()*0.2833)),new Posicion((int)(dimension.getWidth()*0.3125),
		    (int)(dimension.getWidth()*0.5))));
		return retorno;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
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
		return this.getSize();
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(Dimension dimension) {
		this.setSize(dimension);
	}

	/**
	 * @return the datoPilotoAuto
	 */
	public DatoPilotoAutoParaCarrera getDatoPilotoAuto() {
		return datoPilotoAuto;
	}

	/**
	 * @param datoPilotoAuto the datoPilotoAuto to set
	 */
	public void setDatoPilotoAuto(DatoPilotoAutoParaCarrera datoPilotoAuto) {
		this.datoPilotoAuto = datoPilotoAuto;
	}

	/**
	 * @return the imagenTramo
	 */
	public ImagenTramo getImagenTramo() {
		return imagenTramo;
	}

	/**
	 * @param imagenTramo the imagenTramo to set
	 */
	public void setImagenTramo(ImagenTramo imagenTramo) {
		this.imagenTramo = imagenTramo;
	}

	/**
	 * @return the imagenAuto
	 */
	public ImagenAuto getImagenAuto() {
		return imagenAuto;
	}

	/**
	 * @param imagenAuto the imagenAuto to set
	 */
	public void setImagenAuto(ImagenAuto imagenAuto) {
		this.imagenAuto = imagenAuto;
	}
	
}
