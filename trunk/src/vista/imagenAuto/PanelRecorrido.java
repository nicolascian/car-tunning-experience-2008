package vista.imagenAuto;

import java.awt.Dimension;
import modelo.Auto;
import modelo.Pista;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Color;

import vista.imagenTramo.Posicion;
public class PanelRecorrido extends JPanel {

    private long tiempoDeActualizacion=50;
	
	private Thread hiloDeActualizacion=null;
	
	private JProgressBar progress=null;
	
	private Auto auto=null;
		
	private double m=0.0;
	
	public PanelRecorrido(String nombre, Auto auto,Pista pista,Dimension dimension,Posicion posicion,
			              Color colorBarra){
		this.auto=auto;
		this.setLayout(null);
		this.setSize(dimension);
		this.setBounds(posicion.getX(),posicion.getY(),dimension.width,dimension.height);
		this.setBackground(Color.black);
		this.progress=new JProgressBar();
		this.progress.setBackground(Color.GRAY);
		this.progress.setForeground(colorBarra);
		progress.setStringPainted(false);
		progress.setBounds((int)(dimension.width*0.1),(int)(dimension.height*0.1),
				           (int)(dimension.width*0.8),(int)(dimension.height*0.8));
		progress.setValue(0);
		progress.setVisible(true);
		this.setVisible(true);
		this.m=pista.getLongitud()/100;
		this.hiloDeActualizacion=new Thread(){
		    public void run(){
			     super.run();
			     while(true){
				   progress.setValue((int)(m*getAuto().getPosicion()));
				   try{   
					  this.sleep(tiempoDeActualizacion);
				   }catch(Exception e){};
			     }
			}
		};
		this.add(progress);
		this.hiloDeActualizacion.start();	
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
	
}
