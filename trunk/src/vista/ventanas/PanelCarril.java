/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;
import vista.imagenAuto.imagenRelojes.*;
import javax.swing.JPanel;
import vista.imagenTramo.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import vista.imagenAuto.*;

/**
 * @author Usuario
 *
 */
public class PanelCarril extends JPanel{

	private Posicion posicion=null;
	
	private ImagenTramo imagenTramo=null;
	
	private ImagenAuto imagenAuto=null;
	
	private BufferedImage buffImage=null;

	private Graphics2D grafico=null;
	
	private long tiempoDeActualizacion=550;
	
	private Thread hiloDeActualizacion=null;
	
	private PanelCarril(Dimension dimension, Posicion posicion,modelo.Jugador jugador){
		this.setDimension(new Dimension(dimension));
		this.setPosicion(new Posicion(posicion));
		this.setBounds(posicion.getX(),posicion.getY(),dimension.width,dimension.height);
		
		this.buffImage=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		this.grafico=buffImage.createGraphics();
		this.hiloDeActualizacion=new Thread(){
		    public void run(){
			     super.run();
			     while(true){
				   repaint();
				   try{   
					  this.sleep(tiempoDeActualizacion);
				   }catch(Exception e){};
			     }
			}
		};
		this.hiloDeActualizacion.start();	
		
	}
	
	public static PanelCarril createPanelCarrilVistaAutoDesdeAtras(Dimension dimension,Posicion posicion,
			                                                       modelo.Jugador usuario){
		PanelCarril retorno=new PanelCarril(dimension,posicion,usuario);
		retorno.setImagenTramo(ImagenTramo.createTramoAsfaltoCespedDiaAlgoNublado(ImagenTramo.createDimensionOptima(),
                               new Posicion()));
		Dimension dimensionAuto=new Dimension((int)(dimension.getWidth()*0.3375),(int)(dimension.getWidth()*0.24));
		retorno.setImagenAuto(new ImagenAutoDesdeAtras(usuario.getAuto(),
				              "src//vista//imagenAuto//imagenes//DodgeViper//atras.png",dimensionAuto,
			                  new Posicion((int)((retorno.getDimension().width-dimensionAuto.getWidth())/2.0),
					          (int)(dimension.getWidth()*0.35))));
		return retorno;
	}
	
	public void actualizarVelocidad(double velocidad){
		if(velocidad<=0)
		  this.tiempoDeActualizacion=0;
		else
	      this.tiempoDeActualizacion=(int)(velocidad-650)/(-10);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 */
	@Override
	public void repaint() {
     if(this.tiempoDeActualizacion>0)
		this.paint(this.getGraphics());
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
	  	try{	
			Imagen imagenAuxiliar=imagenTramo.getImagen();
			grafico.drawImage(imagenAuxiliar.getImage(),0,0,
					this.getDimension().width,this.getDimension().height,this);
			imagenAuto.paint(grafico);
			((Graphics2D)g).drawImage(buffImage,0 ,0,this.getWidth(),this.getHeight(),this);
		}catch(Exception e){};
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
