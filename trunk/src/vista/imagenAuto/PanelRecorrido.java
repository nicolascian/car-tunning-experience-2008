package vista.imagenAuto;

import java.awt.Dimension;
import modelo.Auto;
import modelo.Pista;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import vista.imagenTramo.Posicion;
import javax.swing.*;
public class PanelRecorrido extends JPanel {

    private long tiempoDeActualizacion=50;
	
	private Thread hiloDeActualizacion=null;
	
	private JProgressBar progress=null;
	
	private Auto auto=null;
		
	private String nombre=null;
	
	private JLabel jugador=null;
	
	private double m=0.0;
		
	public PanelRecorrido(String nombre, Auto auto,Pista pista,Dimension dimension,Posicion posicion,
			              Color colorBarra){
		this.nombre=nombre;
		this.auto=auto;
		this.setLayout(null);
		this.setSize(dimension);
		this.setBounds(posicion.getX(),posicion.getY(),dimension.width,dimension.height);
		this.setBackground(Color.BLACK);
		this.progress=new JProgressBar();
		this.progress.setBackground(Color.GRAY);
		this.progress.setForeground(colorBarra);
		progress.setStringPainted(false);
		progress.setBounds(0,(int)(dimension.height*0.5),
				           (int)(dimension.width*0.6),(int)(dimension.height*0.4));
		progress.setValue(0);
		progress.setVisible(true);
		this.setVisible(true);
		this.m=100/pista.getLongitud();
		this.hiloDeActualizacion=new Thread(){
		    public void run(){
			     super.run();
			     while(true){
			       synchronized(getAuto()){
			         progress.setValue((int)(m*getAuto().getPosicion()));
			       }
			       try{   
					  this.sleep(tiempoDeActualizacion);
				   }catch(Exception e){};
			     }
			}
		};
		try{    
		    this.jugador= new JLabel();
			jugador.setText(nombre);
		    this.jugador.setBackground(Color.black);
		    jugador.setBounds(progress.getWidth(),0,(int)(dimension.width*0.4),(int)(dimension.height*0.8));
		    jugador.setForeground(colorBarra);
		    this.add(jugador);
		    jugador.setVisible(true);
		}catch(NullPointerException e){};
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

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		//((Graphics2D)g).drawString("nombre",0,0);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean flag) {
		if(!flag)
		try{
			this.hiloDeActualizacion.stop();
			this.hiloDeActualizacion=null;
		}catch(NullPointerException e){}
		super.setVisible(flag);
	}

	/**
	 * @return the hiloDeActualizacion
	 */
	public Thread getHiloDeActualizacion() {
		return hiloDeActualizacion;
	}

	/**
	 * @param hiloDeActualizacion the hiloDeActualizacion to set
	 */
	public void setHiloDeActualizacion(Thread hiloDeActualizacion) {
		this.hiloDeActualizacion = hiloDeActualizacion;
	}	
	
	
}
