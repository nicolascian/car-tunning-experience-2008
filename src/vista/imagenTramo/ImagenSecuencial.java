package vista.imagenTramo;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Shape;
/**
 * @author Usuario
 *
 */
public class ImagenSecuencial{

	private Posicion posicion=null;
	
	private Dimension dimension=null;
	
	private Imagen[] vectorImagenes=null;
	
	private Imagen[] vectorImagenesSalida=null;
	
	private int cursor=0;
	
	private double proporcionAlto=0.5;
	
	private double proporcionAltoInicial=0.02857;
	
	private double proporcionAncho=0.52;
	
	private double proporcionAnchoInicial=0.12;
	
	private boolean invertida=false;
	
	private boolean piramidal=false;
		
	/**
	 * @param ruta
	 */
	public ImagenSecuencial(String ruta,boolean invertida,boolean piramidal) {
		this.invertida=invertida;
		this.piramidal=piramidal;
		this.dimension=new Dimension(800,360);
		this.setVectorImagenes(Imagen.createVectorImagenes(ruta));
		this.comprobarProporcion();
		this.vectorImagenesSalida=new Imagen[vectorImagenes.length];
		this.setPosicion(new Posicion());
		this.generarImagenes();
		this.cursor=0;
	}

	/**
	 * @param ruta
	 * @param dimension
	 * @param posicion
	 */
	public ImagenSecuencial(String ruta,boolean invertida, boolean piramidal,
			                Dimension dimension, Posicion posicion) {
		this.invertida=invertida;
		this.piramidal=piramidal;
		this.dimension=new Dimension(dimension.width,dimension.height);
		this.setVectorImagenes(Imagen.createVectorImagenes(ruta));
		this.comprobarProporcion();
		this.vectorImagenesSalida=new Imagen[vectorImagenes.length];
		this.setPosicion(new Posicion(posicion.getX(),posicion.getY()));
		this.generarImagenes();
		this.cursor=0;
	}
		
	/**
	 * 
	 */
	public ImagenSecuencial(ImagenSecuencial imagenFrente,ImagenSecuencial imagenFondo,Shape clipFondo) {
		this.dimension=new Dimension(imagenFondo.getDimension());
		this.setPosicion(new Posicion(imagenFondo.getPosicion().getX(),
				                      imagenFondo.getPosicion().getY()));
		this.acoplarImagenes(imagenFrente,imagenFondo,clipFondo);
		this.cursor=0;
	}

	private void acoplarImagenes(ImagenSecuencial imagenFrente,ImagenSecuencial imagenFondo,
			                     Shape clipFondo){
		if(imagenFrente.getVectorImagenesSalida().length<
		   imagenFondo.getVectorImagenesSalida().length)
		   this.vectorImagenesSalida=new Imagen[imagenFrente.getVectorImagenesSalida().length];
		else
		   this.vectorImagenesSalida=new Imagen[imagenFondo.getVectorImagenesSalida().length];
		for(int cursor=0;cursor<vectorImagenesSalida.length;cursor++){
			BufferedImage buffImagen=new BufferedImage(getDimension().width,getDimension().height,
	                BufferedImage.TYPE_INT_RGB);
	        Graphics2D g=buffImagen.createGraphics();
			Imagen imagenAux=imagenFondo.getImagen();
        	g.drawImage(imagenAux.getImage(),0,0,imagenAux.getDimension().width,
        			                             imagenAux.getDimension().height,null);
        	imagenAux=imagenFrente.getImagen();
        	g.setClip(clipFondo);
        	g.drawImage(imagenAux.getImage(),0,0,imagenAux.getDimension().width,
        			                             imagenAux.getDimension().height,null);
        	vectorImagenesSalida[cursor]=new Imagen(buffImagen,this.getDimension(),
        			                                this.getPosicion());
		}
	}
	
	protected void comprobarProporcion(){
		int altoTotal=0;
		int altoImagenAnterior=0;
		boolean encontrado=false;
		while(!encontrado){
			altoImagenAnterior=(int)(this.getDimension().height*proporcionAltoInicial);
			altoTotal=altoImagenAnterior;
			for(int cursor=1;cursor<vectorImagenes.length;cursor++){
				altoImagenAnterior=(int)(altoImagenAnterior*this.proporcionAlto);
				altoTotal+=altoImagenAnterior;
			}
			if(altoTotal<=getDimension().height)
			   proporcionAlto+=0.01;
			else
			  encontrado=true;
		}
		if(!piramidal)
		  proporcionAncho=1;
		else{
		  encontrado=false;
		  int anchoImagen=0;
		  while(!encontrado){
				anchoImagen=(int)(this.getDimension().width*proporcionAnchoInicial);
				for(int cursor=1;cursor<vectorImagenes.length;cursor++){
					anchoImagen=(int)(anchoImagen*this.proporcionAncho);
				}
				if(anchoImagen<=getDimension().width)
				   proporcionAncho+=0.01;
				else
				  encontrado=true;
			}
		}
		  
	}
	
	protected void generarImagenes(){
		Dimension[] vectorDimensiones=new Dimension[vectorImagenes.length];
		Posicion[] vectorPosiciones=new Posicion[vectorImagenes.length];
		if(!piramidal)
			proporcionAnchoInicial=1;
		int alto=getDimension().height;
		int ancho=getDimension().width;
		if(!invertida){  
		  vectorDimensiones[0]=new Dimension((int)(proporcionAnchoInicial*ancho),(int)(alto*proporcionAltoInicial));
		  if(!piramidal)
		    vectorPosiciones[0]=new Posicion(0,0);
		  else
			vectorPosiciones[0]=new Posicion((int)(getDimension().width*(0.5-proporcionAnchoInicial/2)),0);
		  for(int cursor=1;cursor<vectorDimensiones.length;cursor++){
		   vectorDimensiones[cursor]=new Dimension((int)(vectorDimensiones[cursor-1].width*proporcionAncho),
				                    (int)(vectorDimensiones[cursor-1].height*proporcionAlto));
		   vectorPosiciones[cursor]=new Posicion((int)(getDimension().width/2.0-vectorDimensiones[cursor].width/2.0),
				                  vectorPosiciones[cursor-1].getY()+vectorDimensiones[cursor-1].height);
		  }
		  for(int cursor=vectorImagenesSalida.length-1;cursor>=0;cursor--){
				 Imagen imagen=generarImagenSalida(vectorDimensiones,vectorPosiciones);			
				 this.vectorImagenesSalida[cursor]=imagen;
				 this.rotar();
		  }
		}
		else{
		  vectorDimensiones[vectorDimensiones.length-1]=new Dimension((int)(proporcionAnchoInicial*ancho),
				                                                      (int)(alto*proporcionAltoInicial));
		  for(int cursor=vectorDimensiones.length-2;cursor>=0;cursor--)
			   vectorDimensiones[cursor]=new Dimension((int)(vectorDimensiones[cursor+1].width*proporcionAncho),
					                                  (int)(vectorDimensiones[cursor+1].height*proporcionAlto));
		  vectorPosiciones[0]=new Posicion(0,0);
		  for(int cursor=1;cursor<vectorDimensiones.length;cursor++)
			 vectorPosiciones[cursor]=new Posicion(0,vectorPosiciones[cursor-1].getY()+
			                                       vectorDimensiones[cursor-1].height);
		  for(int cursor=0;cursor<vectorImagenesSalida.length;cursor++){
				 Imagen imagen=generarImagenSalida(vectorDimensiones,vectorPosiciones);			
				 this.vectorImagenesSalida[cursor]=imagen;
				 this.rotar();
		  }
		}
	}
	
	public Imagen getImagen(){
		Imagen salida=vectorImagenesSalida[cursor];
		if(cursor<vectorImagenesSalida.length-1)
			cursor++;
		else
			cursor=0;
		return salida;
	}
	
	protected void rotar(){
		   Imagen imagen0=vectorImagenes[0];
		   for(int cursor=0;cursor<vectorImagenes.length-1;cursor++){
			  vectorImagenes[cursor]=vectorImagenes[cursor+1];
		   }
		   vectorImagenes[vectorImagenes.length-1]=imagen0;
	}
	
	/**
	 * @return the vectorImagenes
	 */
	protected Imagen[] getVectorImagenes() {
		return vectorImagenes;
	}

	/**
	 * 
	 */
	protected Imagen generarImagenSalida(Dimension[] vectorDimensiones,Posicion[] vectorPosiciones){
		BufferedImage buffImagen=new BufferedImage(getDimension().width,getDimension().height,
				                               BufferedImage.TYPE_INT_RGB);
		Graphics2D g=buffImagen.createGraphics();
		for(int cursor=0;cursor<vectorPosiciones.length;cursor++){
			g.drawImage(vectorImagenes[cursor].getImage(),
					    vectorPosiciones[cursor].getX(),vectorPosiciones[cursor].getY(),
					    vectorDimensiones[cursor].width,vectorDimensiones[cursor].height,null);
		}
		return new Imagen(buffImagen,this.getDimension(),this.getPosicion());
	}
	
	/**
	 * @param vectorImagenes the vectorImagenes to set
	 */
	protected void setVectorImagenes(Imagen[] vectorImagenes) {
		this.vectorImagenes = vectorImagenes;
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
	protected void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the vectorImagenesSalida
	 */
	protected Imagen[] getVectorImagenesSalida() {
		return vectorImagenesSalida;
	}

	/**
	 * @param vectorImagenesSalida the vectorImagenesSalida to set
	 */
	protected void setVectorImagenesSalida(Imagen[] vectorImagenesSalida) {
		this.vectorImagenesSalida = vectorImagenesSalida;
	}

	/**
	 * @return the proporcionAlto
	 */
	protected double getProporcionAlto() {
		return proporcionAlto;
	}

	/**
	 * @param proporcionAlto the proporcionAlto to set
	 */
	protected void setProporcionAlto(double proporcionAlto) {
		this.proporcionAlto = proporcionAlto;
	}

	/**
	 * @return the invertida
	 */
	protected boolean isInvertida() {
		return invertida;
	}

	/**
	 * @param invertida the invertida to set
	 */
	protected void setInvertida(boolean invertida) {
		this.invertida = invertida;
	}

	/**
	 * @return the cursor
	 */
	protected int getCursor() {
		return cursor;
	}

	/**
	 * @param cursor the cursor to set
	 */
	protected void setCursor(int cursor) {
		this.cursor = cursor;
	}

	/**
	 * @return the proporcionAltoInicial
	 */
	protected double getProporcionAltoInicial() {
		return proporcionAltoInicial;
	}

	/**
	 * @param proporcionAltoInicial the proporcionAltoInicial to set
	 */
	protected void setProporcionAltoInicial(double proporcionAltoInicial) {
		this.proporcionAltoInicial = proporcionAltoInicial;
	}

	/**
	 * @return the proporcionAncho
	 */
	protected double getProporcionAncho() {
		return proporcionAncho;
	}

	/**
	 * @param proporcionAncho the proporcionAncho to set
	 */
	protected void setProporcionAncho(double proporcionAncho) {
		this.proporcionAncho = proporcionAncho;
	}

	/**
	 * @return the proporcionAnchoInicial
	 */
	protected double getProporcionAnchoInicial() {
		return proporcionAnchoInicial;
	}

	/**
	 * @param proporcionAnchoInicial the proporcionAnchoInicial to set
	 */
	protected void setProporcionAnchoInicial(double proporcionAnchoInicial) {
		this.proporcionAnchoInicial = proporcionAnchoInicial;
	}

	/**
	 * @return the piramidal
	 */
	protected boolean isPiramidal() {
		return piramidal;
	}

	/**
	 * @param piramidal the piramidal to set
	 */
	protected void setPiramidal(boolean piramidal) {
		this.piramidal = piramidal;
	}
	
}
