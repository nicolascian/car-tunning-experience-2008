/**
 * 
 */
package vista.imagenRueda;

import java.awt.Dimension;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.ImagenSecuencial;
import vista.imagenTramo.Posicion;

	

/**
 * @author Usuario
 *
 */
public class ImagenRueda extends ImagenSecuencial {
	
	private int altoIndividual=0;
	
	/* (non-Javadoc)
	 * @see vista.imagenTramo.ImagenSecuencial#getImagen()
	 */
	@Override
	public Imagen getImagen() {
		setCursor(getCursor()+getVectorImagenesSalida().length/3);
		if(getCursor()>getVectorImagenesSalida().length-1)
		   setCursor(0);
		return super.getImagen();
	}

	public static Dimension createDimensionRuedaMedianaParaAuto(Dimension dimension){
		return new Dimension((int)(dimension.getWidth()*0.22),
				             (int)(dimension.getHeight()*0.45));
	}
	
	public static ImagenRueda createImagenRuedaNeumaticoDibujo2(Dimension dimension,Posicion posicion){
		return new ImagenRueda("src//vista//imagenRueda//neumatico2",false,false,dimension,posicion);
	}
	
	private ImagenRueda(String ruta, boolean invertida, boolean piramidal,
			Dimension dimension, Posicion posicion) {
		super(ruta,false,false, dimension, posicion);
		// TODO Auto-generated constructor stub
	}

	private ImagenRueda(String ruta, boolean invertida, boolean piramidal) {
		super(ruta,false,false);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see vista.imagenTramo.ImagenSecuencial#comprobarProporcion()
	 */
	@Override
	protected void comprobarProporcion(){
		int altoIndividual=(int)(Math.round((float)this.getDimension().getHeight()/this.getVectorImagenes().length));
		int altoTotal=altoIndividual*this.getVectorImagenes().length;
		while(altoTotal<getDimension().height){
			altoTotal=altoIndividual*this.getVectorImagenes().length;
			altoIndividual++;
		}
	}

	/* (non-Javadoc)
	 * @see vista.imagenTramo.ImagenSecuencial#generarImagenes()
	 */
	@Override
	protected void generarImagenes() {
		int altoIndividual=(int)(Math.round((float)this.getDimension().getHeight()/this.getVectorImagenes().length));
		Dimension[] vectorDimensiones=new Dimension[getVectorImagenes().length];
		Posicion[] vectorPosiciones=new Posicion[getVectorImagenes().length];
		int ancho=getDimension().width;
		vectorDimensiones[0]=new Dimension(ancho,altoIndividual);
		vectorPosiciones[0]=new Posicion(0,0);
		for(int cursor=1;cursor<vectorDimensiones.length;cursor++){
		   vectorDimensiones[cursor]=new Dimension(ancho,altoIndividual);
		   vectorPosiciones[cursor]=new Posicion(0,vectorPosiciones[cursor-1].getY()+altoIndividual);
		}
		for(int cursor=0;cursor<getVectorImagenesSalida().length;cursor++){
			 Imagen imagen=generarImagenSalida(vectorDimensiones,vectorPosiciones);			
			 this.getVectorImagenesSalida()[cursor]=imagen;
			 this.rotar();
	  }
	}
}
