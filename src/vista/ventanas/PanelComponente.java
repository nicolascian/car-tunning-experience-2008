/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package vista.ventanas;

import modelo.componente.Componente;
import javax.swing.*;
import vista.imagenTramo.Imagen;
import vista.imagenAuto.imagenesDeComponentes.*;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import modelo.Taller;
import java.awt.GridLayout;
/**
 * @author Usuario
 *
 */
public class PanelComponente extends JPanel {

	private Imagen imagenComponente=null;
	
	private DatoClase dato=null;
	
	private VentanaTaller ventana=null;
	
	private Componente componente=null;
	
	private JTextArea infoComponente=null;
	
	private Taller taller=null;
	
	private JPanel panelBotones=null;
	
	private JPanel panelOfertas=null;
	
	public PanelComponente(Componente componente,DatoClase dato,Dimension dimension,
			               VentanaTaller ventana){
		this.setLayout(null);
		this.dato=dato;
		this.componente=componente;
		this.ventana=ventana;
		this.repaint();
		this.setBackground(Color.black);
		this.setSize(dimension);
		this.setBounds(0,ventana.getPanelVisor().getHeight(),ventana.getWidth(),
				      (int)(ventana.getHeight()*0.55));
		this.infoComponente=new JTextArea();
		this.infoComponente.setBackground(Color.black);
		this.infoComponente.setForeground(Color.white);
		this.infoComponente.setEditable(false);
		this.infoComponente.setVisible(true);
		this.infoComponente.setBounds(200,0,200,150);
		this.add(this.infoComponente);
		this.actualizarComponente();
		this.agregarBotones();
		this.setVisible(true);
	}
	
	private void agregarBotones(){
		this.panelBotones=new JPanel();
		panelBotones.setLayout(new GridLayout());
		panelBotones.setBounds(0,300,400,100);
		JButton botonVolver=new JButton("Volver");
		botonVolver.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
				try{
				  this.wait();
				}catch(Exception exception){};
				cerrar();
		    }
		});
		botonVolver.setVisible(true);
		panelBotones.add(botonVolver);
		JButton botonReparar=new JButton("Reparar");
		botonReparar.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonReparar();
		    }
		});
		botonReparar.setVisible(true);
		panelBotones.add(botonReparar);
		panelBotones.setVisible(true);
		this.add(panelBotones);
		this.panelOfertas=new JPanel();
		panelOfertas.setLayout(new GridLayout(6,6));
		panelOfertas.setBounds(400,0,400,400);
		panelOfertas.setBackground(Color.black);
		BotonOferta.agregarBotonesComponentesCompatiblesAPanel(componente,panelOfertas,
				                                ventana.getAdministrador(), ventana);
		this.add(panelOfertas);
		panelOfertas.setVisible(true);	
	}
	
	public void actualizarComponente(){
		imagenComponente=dato.getImagen();
		imagenComponente.setPosicion(new Posicion(0,0));
		this.actualizaTextolInfoComponente();
	}
	
	public void actualizarComponente(Componente componente){
		this.setComponente(componente);
		dato=this.ventana.getAdministrador().getDatoClase(componente.getClass());
		this.actualizarComponente();
		this.paint(this.getGraphics());
	}
	
	private void actualizaTextolInfoComponente(){
		String precio="";
		try{
			precio=String.valueOf(componente.getPrecio());
		}catch(NullPointerException e){}
		this.infoComponente.setText(dato.getNombre()+'\n'+
				                    "Precio: "+precio+" AlgoPesos "+'\n'+
				                    "Peso: "+componente.getPeso()+" Kg"+'\n'+
				                    "Estado: "+String.valueOf(componente.getEstado()));
	}
	
	/*
	private void pressBotonOferta(){
		
	}
	*/
	private void pressBotonReparar(){
		this.ventana.setVisible(false);
		ventana.getTaller().repararacion(componente);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try{
		g.setColor(Color.BLACK);
		g.fillRect(0,0, (int)imagenComponente.getDimension().getWidth(),
				    (int)imagenComponente.getDimension().getHeight());
		g.drawImage(imagenComponente.getImage(),imagenComponente.getPosicion().getX(),
				    imagenComponente.getPosicion().getY(),
				    (int)imagenComponente.getDimension().getWidth(),
				    (int)imagenComponente.getDimension().getHeight(),null);
		}catch(NullPointerException e){};
	}

	public void repaint() {
		paint(this.getGraphics());
	}
		
	public void cerrar(){
		this.setVisible(false);
		this.ventana.refrescarPanelBotones();
		this.ventana.refrescarInfo();
		this.ventana.getPanelBotones().setVisible(true);
	}

	/**
	 * @return the componente
	 */
	public Componente getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	
}
