/**
 * 
 */
package vista.imagenAuto.imagenRelojes;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.Observable;
import java.util.Observer;
import modelo.Auto;
import modelo.componente.Caja;
import modelo.componente.Motor;
/**
 * @author Usuario
 *
 */
public abstract class ImagenReloj implements Observer{

	private double anguloMinimo=0;
	
	private double anguloMaximo=0;
	
	private double valorMinimo=0;
	
	private double valorMaximo=0;
	
	private double angulo=0;
	
	private double m=0;
	
	private double b=0;
	
	private double largoAguja=0;
	
	private Imagen imagenReloj=null;
	
    private Posicion posicion=null;
	
    private Posicion posicionCentro=null;
    
	private Dimension dimension=null;
	
	private Graphics2D grafico=null;
	
	private BufferedImage buffImage=null;
	
	private Auto auto=null;
		
	public ImagenReloj(Auto auto,String rutaImagen,Posicion posicion, Dimension dimension,
					   Color colorDeAguja,double anguloMinimo,double anguloMaximo,
					   double valorMinimo,double valorMaximo){
		this.setAnguloMaximo(anguloMaximo);
		this.setAnguloMinimo(anguloMinimo);
		this.setDimension(dimension);
		this.setPosicion(posicion);
		this.setValorMaximo(valorMaximo);
		this.setValorMinimo(valorMinimo);
		this.setImagen(new Imagen(rutaImagen,dimension,new Posicion()));
		this.posicionCentro=new Posicion((int)(dimension.width/2.0),(int)(dimension.height/2.0));
		this.m=(float)(anguloMinimo-anguloMaximo)/(float)(valorMinimo-valorMaximo);
		this.b=(float)anguloMinimo-(float)m*anguloMaximo;
		this.buffImage=new BufferedImage(dimension.width,dimension.height,
				                            BufferedImage.TYPE_INT_ARGB);
		this.grafico=(Graphics2D)buffImage.createGraphics();
		this.grafico.setColor(colorDeAguja);
		this.grafico.setBackground(new Color(0,0,0,0));
		this.auto=auto;
		this.largoAguja=this.dimension.getHeight()*0.85;
	}
		
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.actualizarAngulo();		
	}

	public void actualizarAngulo(){}

	/**
	 * @return the image
	 */
	public Image getImage() {
		grafico.drawImage(imagenReloj.getImage(),imagenReloj.getPosicion().getX(),
				          imagenReloj.getPosicion().getY(),imagenReloj.getDimension().width,
				          imagenReloj.getDimension().height,null);
		grafico.setStroke(new BasicStroke(4.0f));
		grafico.drawLine(this.posicionCentro.getX(),posicionCentro.getY(),
				         (int)(posicionCentro.getX()+largoAguja*Math.cos(this.angulo)),
				         (int)(posicionCentro.getY()+largoAguja*Math.sin(this.angulo)));
		return buffImage;
	}

	
	/**
	 * @return the anguloMinimo
	 */
	public double getAnguloMinimo() {
		return anguloMinimo;
	}

	/**
	 * @param anguloMinimo the anguloMinimo to set
	 */
	public void setAnguloMinimo(double anguloMinimo) {
		this.anguloMinimo = anguloMinimo;
	}

	/**
	 * @return the anguloMaximo
	 */
	public double getAnguloMaximo() {
		return anguloMaximo;
	}

	/**
	 * @param anguloMaximo the anguloMaximo to set
	 */
	public void setAnguloMaximo(double anguloMaximo) {
		this.anguloMaximo = anguloMaximo;
	}

	/**
	 * @return the valorMinimo
	 */
	public double getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * @param valorMinimo the valorMinimo to set
	 */
	public void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * @return the valorMaximo
	 */
	public double getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * @param valorMaximo the valorMaximo to set
	 */
	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
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
		this.posicion =new Posicion( posicion);
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
		this.dimension =new Dimension( dimension);
	}

	/**
	 * @return the imagen
	 */
	protected Imagen getImagen() {
		return imagenReloj;
	}

	/**
	 * @param imagen the imagenReloj to set
	 */
	protected void setImagen(Imagen imagen) {
		this.imagenReloj = imagen;
	}

	/**
	 * @return the angulo
	 */
	public double getAngulo() {
		return angulo;
	}

	/**
	 * @param angulo the angulo to set
	 */
	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

}
