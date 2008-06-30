/**
 * 
 */
package vista.imagenAuto.imagenRelojes;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.*;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import modelo.Auto;
/**
 * @author Usuario
 *
 */
public class ImagenReloj{

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
	
    private Dimension dimension=null;
    
    private Posicion posicionCentro=null;
    	
	private Graphics2D grafico=null;
	
	private Color colorAguja=null;
	
	private BufferedImage buffImage=null;
	
	private Auto auto=null;
			
	protected ImagenReloj(Auto auto,String rutaImagen,Posicion posicion, Dimension dimension,
					   Color colorDeAguja,double anguloMinimo,double anguloMaximo,
					   double valorMinimo,double valorMaximo){
		this.colorAguja=colorDeAguja;
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
		this.buffImage=new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
		this.grafico=(Graphics2D)buffImage.createGraphics();
		this.grafico.setColor(colorDeAguja);
		this.grafico.setBackground(new Color(0,0,0,0));
		this.auto=auto;
		this.largoAguja=this.getDimension().getWidth()*0.85/2;	
	}
		
	protected void actualizarAngulo(){}

	/**
	 * @return the image
	 */
	public Image getImage() {
		this.actualizarAngulo();
		grafico.drawImage(imagenReloj.getImage(),imagenReloj.getPosicion().getX(),
				          imagenReloj.getPosicion().getY(),imagenReloj.getDimension().width,
				          imagenReloj.getDimension().height,null);
		grafico.setStroke(new BasicStroke(4.0f));
		grafico.drawLine(this.posicionCentro.getX(),posicionCentro.getY(),
				         (int)(posicionCentro.getX()+largoAguja*Math.cos(this.angulo)),
				         (int)(posicionCentro.getY()-largoAguja*Math.sin(this.angulo)));
		return buffImage;
	}

	/**
	 * @return the anguloMinimo
	 */
	protected double getAnguloMinimo() {
		return anguloMinimo;
	}

	/**
	 * @param anguloMinimo the anguloMinimo to set
	 */
	protected void setAnguloMinimo(double anguloMinimo) {
		this.anguloMinimo = anguloMinimo;
	}

	/**
	 * @return the anguloMaximo
	 */
	protected double getAnguloMaximo() {
		return anguloMaximo;
	}

	/**
	 * @param anguloMaximo the anguloMaximo to set
	 */
	protected void setAnguloMaximo(double anguloMaximo) {
		this.anguloMaximo = anguloMaximo;
	}

	/**
	 * @return the valorMinimo
	 */
	protected double getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * @param valorMinimo the valorMinimo to set
	 */
	protected void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * @return the valorMaximo
	 */
	protected double getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * @param valorMaximo the valorMaximo to set
	 */
	protected void setValorMaximo(double valorMaximo) {
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
		return this.dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	protected void setDimension(Dimension dimension) {
		this.dimension=new Dimension(dimension);
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
	protected double getAngulo() {
		return angulo;
	}

	/**
	 * @param angulo the angulo to set
	 */
	protected void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	/**
	 * @return the auto
	 */
	protected Auto getAuto() {
		return auto;
	}

	/**
	 * @param auto the auto to set
	 */
	protected void setAuto(Auto auto) {
		this.auto = auto;
	}

	/**
	 * @return the m
	 */
	protected double getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	protected void setM(double m) {
		this.m = m;
	}

	/**
	 * @return the b
	 */
	protected double getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	protected void setB(double b) {
		this.b = b;
	}

	/**
	 * @return the colorAguja
	 */
	public Color getColorAguja() {
		return colorAguja;
	}

	/**
	 * @return the largoAguja
	 */
	protected double getLargoAguja() {
		return largoAguja;
	}

	/**
	 * @param largoAguja the largoAguja to set
	 */
	protected void setLargoAguja(double largoAguja) {
		this.largoAguja = largoAguja;
	}

	/**
	 * @return the imagenReloj
	 */
	protected Imagen getImagenReloj() {
		return imagenReloj;
	}

	/**
	 * @param imagenReloj the imagenReloj to set
	 */
	protected void setImagenReloj(Imagen imagenReloj) {
		this.imagenReloj = imagenReloj;
	}

	/**
	 * @return the posicionCentro
	 */
	protected Posicion getPosicionCentro() {
		return posicionCentro;
	}

	/**
	 * @param posicionCentro the posicionCentro to set
	 */
	protected void setPosicionCentro(Posicion posicionCentro) {
		this.posicionCentro = posicionCentro;
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
	 * @param colorAguja the colorAguja to set
	 */
	protected void setColorAguja(Color colorAguja) {
		this.colorAguja = colorAguja;
	}
	
}
