/**
 * 
 */
package vista.imagenTramo;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * @author Usuario
 *
 */
public class ImagenTramo {
	
	private final static Dimension DIMENSION_MAXIMA=new Dimension(950,712);
	
	private final static Dimension DIMENSION_OPTIMA=new Dimension(800,600);
	
	private final static Dimension DIMENSION_MINIMA=new Dimension(600,450);
	
	private final static int MAXIMO_CONTADOR_ACTUALIZACION_CIELO=70; 
	
	private ImagenSecuencial cielo=null;
	
	private ImagenSecuencial ruta=null;
	
	private Posicion posicion=null;
	
	private Dimension dimension=null;
	
	private int contadorActualizacionCielo=MAXIMO_CONTADOR_ACTUALIZACION_CIELO;
	
	private BufferedImage imagenBuffer=null;
	
	private Graphics2D grafico=null;
	
	public static ImagenTramo createTramoAsfaltoCespedDiaAlgoNublado(Dimension dimension,Posicion posicion){
		return createTramo("src//vista//imagenTramo//cieloSemiNublado","src//vista//imagenTramo//carretera2",
				           "src//vista//imagenTramo//pasto",dimension,posicion);
	}
	
	public static ImagenTramo createTramoTierraNegraTierraClaraDiaAlgoNublado(Dimension dimension,Posicion posicion){
		return createTramo("src//vista//imagenTramo//cieloSemiNublado","src//vista//imagenTramo//TierraNegra",
				           "src//vista//imagenTramo//Tierra-Clara-1",dimension,posicion);
	}
	
	public static ImagenTramo createTramoPuente(Dimension dimension,Posicion posicion){
		return createTramo("src//vista//imagenTramo//cieloSemiNublado","src//vista//imagenTramo//carretera2",
				           "src//vista//imagenTramo//rio",dimension,posicion);
	}
	
	public static Dimension createDimensionMaxima(){
		return new Dimension(DIMENSION_MAXIMA);
	}
	
	public static Dimension createDimensionMinima(){
		return new Dimension(DIMENSION_MINIMA);
	}
	
	public static Dimension createDimensionOptima(){
		return new Dimension(DIMENSION_OPTIMA);
	}
	
	public Imagen getImagen(){
		Imagen imagenAuxiliar;
		if(contadorActualizacionCielo>=MAXIMO_CONTADOR_ACTUALIZACION_CIELO){
		   imagenAuxiliar=cielo.getImagen();
		   grafico.drawImage(imagenAuxiliar.getImage(),
				     imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
			         imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,
			         null);
		   this.contadorActualizacionCielo=0;
		}
		else
			this.contadorActualizacionCielo++;
		
		imagenAuxiliar=ruta.getImagen();
		grafico.drawImage(imagenAuxiliar.getImage(),
		        	      imagenAuxiliar.getPosicion().getX(),imagenAuxiliar.getPosicion().getY(),
		                  imagenAuxiliar.getDimension().width,imagenAuxiliar.getDimension().height,
		                  null);
		return new Imagen(imagenBuffer,dimension,posicion);
	}
	
	/**
	 * 
	 */
	private ImagenTramo() {
		
	}
		
	/**
	 * 
	 */
	private ImagenTramo(ImagenSecuencial cielo,ImagenSecuencial ruta,Dimension dimension,
			           Posicion posicon){ 
		this.setCielo(cielo);
		this.setRuta(ruta);
		this.setDimension(new Dimension(dimension));
		this.setPosicion(new Posicion(posicion));
		try{
		   this.imagenBuffer=new BufferedImage(dimension.width,dimension.height,
				                            BufferedImage.TYPE_INT_RGB);
		}catch(Exception e){
			System.gc();
			this.imagenBuffer=new BufferedImage(dimension.width,dimension.height,
                    BufferedImage.TYPE_INT_RGB);
		}
		this.grafico=(Graphics2D)imagenBuffer.createGraphics();
	}

	public static Shape createShapeRuta(Dimension dimension){
		Polygon poligono=new Polygon();
		poligono.addPoint((int)(dimension.width/2.0-30),0);
		poligono.addPoint((int)(dimension.width/2+30), 0);
		poligono.addPoint((int)(dimension.width),dimension.height);
		poligono.addPoint(0,dimension.height);
		return (poligono);
	}
	
	public static ImagenTramo createTramo(String rutaCielo,String rutaCamino,String rutaCampo,
			                            Dimension dimension,Posicion posicion){
		Dimension dimensionTotal=dimension;
		if((dimension.width>DIMENSION_MAXIMA.width)||(dimension.height>DIMENSION_MAXIMA.height))
		  dimensionTotal=ImagenTramo.createDimensionMaxima();
		else
		    if((dimension.width<DIMENSION_MINIMA.width)||(dimension.height<DIMENSION_MINIMA.height))
			dimensionTotal=ImagenTramo.createDimensionMinima();	
		Dimension dimensionCampo=new Dimension(dimensionTotal.width,(int)(dimensionTotal.height*0.6));
		Shape clipFondo=ImagenTramo.createShapeRuta(dimensionCampo);
		Dimension dimensionCielo=new Dimension(dimensionTotal.width,
				                               dimensionTotal.height-dimensionCampo.height);
		ImagenSecuencial imagenCielo=new ImagenSecuencial(rutaCielo,true,false,dimensionCielo,
				                                          new Posicion(0,0));
		ImagenSecuencial imagenCampo=new ImagenSecuencial(rutaCampo,false,false,dimensionCampo,
				                                          new Posicion(0,dimensionCielo.height));
		ImagenSecuencial imagenCamino=new ImagenSecuencial(rutaCamino,false,true,dimensionCampo,
														  new Posicion(0,dimensionCielo.height));
		ImagenSecuencial imagenRuta=new ImagenSecuencial(imagenCamino,imagenCampo,clipFondo);
		return new ImagenTramo(imagenCielo,imagenRuta,dimensionTotal,posicion);
	}
	
	/**
	 * @return the cielo
	 */
	private ImagenSecuencial getCielo() {
		return cielo;
	}

	/**
	 * @param cielo the cielo to set
	 */
	private void setCielo(ImagenSecuencial cielo) {
		this.cielo = cielo;
	}

	/**
	 * @return the ruta
	 */
	private ImagenSecuencial getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	private void setRuta(ImagenSecuencial ruta) {
		this.ruta = ruta;
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
	private void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
}
