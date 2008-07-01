package vista.ventanas;

import javax.swing.JFrame;

import vista.imagenTramo.Imagen;
import vista.imagenTramo.Posicion;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import vista.visorDeImagenes.PanelVisorDeImagenes;
public class VentanaTaller extends JFrame {

	private Imagen imagenDeFondo=null;
	
	private JPanel panelVisor=null;
	
	private JPanel panelBotones=null;
	
	private JPanel panelInfo=null;
	
	private JFrame ventanaMenu=null;
	
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
		this.add(panelBotones);		
	}
	
	private void cerrarVentana(){
		ventanaMenu.setVisible(true);
		this.dispose();
	}

}
