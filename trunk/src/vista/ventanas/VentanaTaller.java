package vista.ventanas;

import javax.swing.JButton;
import javax.swing.JFrame;
import modelo.componente.Componente;
import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import vista.visorDeImagenes.PanelVisorDeImagenes;
import vista.imagenAuto.imagenesDeComponentes.*;
public class VentanaTaller extends JFrame {

	private modelo.Usuario usuario=null;
	
	private Imagen imagenDeFondo=null;
	
	private JPanel panelVisor=null;
	
	private JPanel panelBotones=null;
	
	private JPanel panelInfo=null;
	
	private JFrame ventanaMenu=null;
	
	private AdministradorDeImagenesYEtiquetasDeComponentes administrador=new AdministradorDeImagenesYEtiquetasDeComponentes();
	
	public VentanaTaller(JFrame ventanaMenu) {
		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setLayout(null);
		this.ventanaMenu=ventanaMenu;
		this.setSize(800,600);
		this.setBackground(new Color(0,0,0,0));
		this.imagenDeFondo=new Imagen("src//vista//ventanas//cuadros.JPG",this.getSize(),new Posicion());
		this.setTitle("Taller");
		this.setLocationRelativeTo(null); //centrada
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cerrarVentana();
		    }
		});
    	this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setVisible(false);
		this.crearPaneles();
	}
	
	public void setUsuario(modelo.Usuario usuario){
		this.usuario=usuario;
		this.agregarBotones();
	}
	
	private void crearPaneles(){
		panelVisor= new PanelVisorDeImagenes(new Dimension((int)(this.getSize().width*0.5),
				                            (int)(this.getSize().height*0.45)),new Posicion(),
				                            "src//vista//imagenAuto//imagenes//DodgeViper");
		this.add(panelVisor);
		panelInfo= new JPanel();
		panelInfo.setBounds(panelVisor.getWidth(),0,panelVisor.getWidth(),panelVisor.getHeight());
		panelInfo.setVisible(true);
		this.add(panelInfo);
		
		this.panelBotones=new JPanel(){
			public void paint(Graphics g) {
				g.drawImage(imagenDeFondo.getImage(),imagenDeFondo.getPosicion().getX(),
		  				imagenDeFondo.getPosicion().getY(),imagenDeFondo.getDimension().width,
		  				imagenDeFondo.getDimension().height,this);
				this.paintComponents(g);
			}		
		};
		panelBotones.setBounds(0,panelVisor.getHeight(),this.getWidth(),(int)(this.getHeight()*0.55));
		panelBotones.setVisible(true);
		panelBotones.setLayout(new GridLayout());
	}
	
	private void agregarBotones(){
		this.usuario.getAuto()
		this.add(panelBotones);	
		this.agregarBoton(usuario.getAuto().getCaja());
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}

	private void agregarBoton(Componente componente){
		DatoClase dato=administrador.getDatoClase(componente.getClass());
		JButton boton=new JButton();
		boton.setText(dato.getNombre());
		boton.addActionListener(new java.awt.event.ActionListener() {
			private Componente componente=null;
			
			private DatoClase dato=null;
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				pressBotonComponente(componente,dato);
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

			/**
			 * @return the dato
			 */
			public DatoClase getDato() {
				return dato;
			}

			/**
			 * @param dato the dato to set
			 */
			public void setDato(DatoClase dato) {
				this.dato = dato;
			}
			
		});
		boton.setVisible(true);
		this.panelBotones.add(boton);
	}
	
	private void pressBotonComponente(Componente componente,DatoClase dato){
		
	}

	/* (non-Javadoc)
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		try{
			this.panelVisor.setVisible(b);
		}catch(NullPointerException e){}
		super.setVisible(b);
	}
	
	
	
}
