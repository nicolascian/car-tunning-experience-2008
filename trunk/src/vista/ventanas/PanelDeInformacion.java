/**
 * 
 */
package vista.ventanas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import vista.imagenTramo.Posicion;
import vista.imagenAuto.imagenRelojes.ImagenReloj;
import vista.imagenAuto.imagenRelojes.ImagenTacometro;
import vista.imagenAuto.imagenRelojes.ImagenVelocimetro;
import vista.imagenTramo.Imagen;
/**
 * @author Usuario
 *
 */
public class PanelDeInformacion extends JPanel {
    
	private ImagenTacometro imagenTacometro=null;
	
	private ImagenVelocimetro imagenVelocimetro=null;
	
	private Imagen imagenDeFondo=null;
	
	private BufferedImage buffImage=null;

	private Graphics2D grafico=null;
	
	private long tiempoDeActualizacion=50;
	
	private Thread hiloDeActualizacion=null;
	
	public PanelDeInformacion(Dimension dimension,Posicion posicion,modelo.Usuario usuario){
		this.setBounds(posicion.getX(),posicion.getY(),dimension.width,dimension.height);
		this.setDimension(new Dimension(dimension));
		this.setSize(dimension);
		this.buffImage=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		this.grafico=buffImage.createGraphics();
		Dimension dimensionReloj=new Dimension((int)(dimension.width*0.8),(int)(dimension.width*0.8));
		this.imagenTacometro=ImagenTacometro.createTacometroBlanco(usuario.getAuto(), 
			 new Posicion((int)((dimension.width-dimensionReloj.width)/2),
			              (int)((dimension.width-dimensionReloj.width)/2)),dimensionReloj);
		this.imagenVelocimetro=ImagenVelocimetro.createVelocimetroBlanco(usuario.getAuto(), 
			 new Posicion((int)((dimension.width-dimensionReloj.width)/2),
			 (int)((dimensionReloj.width*1.05)+(dimension.width-dimensionReloj.width)/2)),dimensionReloj);
		this.imagenDeFondo=new Imagen("src//vista//ventanas//cuadros.JPG",
				                      this.getDimension(),new Posicion());		
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
		
	/* (non-Javadoc)
	 * @see java.awt.Component#repaint()
	 */
	@Override
	public void repaint() {
     	this.paint(this.getGraphics());
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
	  	try{	
	  		grafico.drawImage(imagenDeFondo.getImage(),imagenDeFondo.getPosicion().getX(),
	  				imagenDeFondo.getPosicion().getY(),imagenDeFondo.getDimension().width,
	  				imagenDeFondo.getDimension().height,this);
			ImagenReloj imagenAuxiliar=imagenTacometro;
			grafico.drawImage(imagenAuxiliar.getImage(),imagenAuxiliar.getPosicion().getX(),
					imagenAuxiliar.getPosicion().getY(),imagenAuxiliar.getDimension().width,
					imagenAuxiliar.getDimension().height,this);
			imagenAuxiliar=imagenVelocimetro;
			grafico.drawImage(imagenAuxiliar.getImage(),imagenAuxiliar.getPosicion().getX(),
					imagenAuxiliar.getPosicion().getY(),imagenAuxiliar.getDimension().width,
					imagenAuxiliar.getDimension().height,this);
			((Graphics2D)g).drawImage(buffImage,0 ,0,this.getWidth(),this.getHeight(),this);
		}catch(Exception e){};
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
	 * @return the imagenTacometro
	 */
	protected ImagenTacometro getImagenTacometro() {
		return imagenTacometro;
	}

	/**
	 * @param imagenTacometro the imagenTacometro to set
	 */
	protected void setImagenTacometro(ImagenTacometro imagenTacometro) {
		this.imagenTacometro = imagenTacometro;
	}

	/**
	 * @return the imagenVelocimetro
	 */
	protected ImagenVelocimetro getImagenVelocimetro() {
		return imagenVelocimetro;
	}

	/**
	 * @param imagenVelocimetro the imagenVelocimetro to set
	 */
	protected void setImagenVelocimetro(ImagenVelocimetro imagenVelocimetro) {
		this.imagenVelocimetro = imagenVelocimetro;
	}

	/**
	 * @return the buffImage
	 */
	protected BufferedImage getBuffImage() {
		return buffImage;
	}

	/**
	 * @param buffImage the buffImage to set
	 */
	protected void setBuffImage(BufferedImage buffImage) {
		this.buffImage = buffImage;
	}

	/**
	 * @return the grafico
	 */
	protected Graphics2D getGrafico() {
		return grafico;
	}

	/**
	 * @param grafico the grafico to set
	 */
	protected void setGrafico(Graphics2D grafico) {
		this.grafico = grafico;
	}

	/**
	 * @return the tiempoDeActualizacion
	 */
	protected long getTiempoDeActualizacion() {
		return tiempoDeActualizacion;
	}

	/**
	 * @param tiempoDeActualizacion the tiempoDeActualizacion to set
	 */
	protected void setTiempoDeActualizacion(long tiempoDeActualizacion) {
		this.tiempoDeActualizacion = tiempoDeActualizacion;
	}

	/**
	 * @return the hiloDeActualizacion
	 */
	protected Thread getHiloDeActualizacion() {
		return hiloDeActualizacion;
	}

	/**
	 * @param hiloDeActualizacion the hiloDeActualizacion to set
	 */
	protected void setHiloDeActualizacion(Thread hiloDeActualizacion) {
		this.hiloDeActualizacion = hiloDeActualizacion;
	}
	
	
}
