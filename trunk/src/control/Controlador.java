package control;
import modelo.Auto;

import java.awt.*; 
import java.awt.event.*;
   
public class Controlador extends Frame implements KeyListener{  

	private static final long serialVersionUID = 1L;
	
	Auto auto;
	
	TextField t1;
	Label l1;
	
  public Controlador(String s /*Auto auto*/)   {  
    super(s);
    
    //this.auto = auto;
    
    Panel panel =new Panel();
    
    l1 = new Label ("Key Listener! usar: E A S D F I K O L P Ñ                         " ) ;
    panel.add(l1);  
    
    add(panel);
    
    addKeyListener ( this ) ; 
    setSize ( 500,100 );
    setVisible(true);
    
    addWindowListener(new WindowAdapter(){
    	public void windowClosing(WindowEvent e){
    		System.exit(0);
    	}
    });
    
  }
  
    public void keyTyped ( KeyEvent e ){  
    //l1.setText("Key Typed " + e);
     }
    
    public void keyPressed ( KeyEvent e){   
    	
    	switch (e.getKeyCode()){
    	case 70 : {
    		l1.setText("F: Acelerador -> auto.acelerar(true) ");
    		//auto.acelerar(true);
    	} break;
    	case 68 : {
    		l1.setText("D: Frenando -> auto.frenar(true) ");
    		//auto.frenar(true);
    	} break;
    	case 83 : {
    		l1.setText("S: Embragando -> auto.embragar(true) ");
    		//auto.embragar(true);
    	} break;
    	case 65 : {
    		l1.setText("A: Nitro -> auto.activarNitro(true) ");
    		//auto.activarNitro(true);
    	} break;
    	case 69 : {
    		l1.setText("E: Encender -> auto.setEncendido(true) ");
    		//auto.setEncendido(true);
    	} break;
    	
    	
    	case 73 : {l1.setText("I: Primera");} break;
    	case 75 : {l1.setText("K: Segunda");} break;
    	case 79 : {l1.setText("O: Tercera");} break;
    	case 76 : {l1.setText("L: Cuarta");} break;
    	case 80 : {l1.setText("P: Quinta");} break;
    	case 0  : {l1.setText("Ñ: Sexta");} break;
    	}
    	
    }
    
    public void keyReleased ( KeyEvent e ){  
    	
    	switch (e.getKeyCode()){
    	case 70 : {
    		l1.setText("F: Acelerador -> auto.acelerar(false) ");
    		//auto.acelerar(false);
    	} break;
    	case 68 : {
    		l1.setText("D: Frenando -> auto.frenar(false) ");
    		//auto.frenar(false);
    	} break;
    	case 83 : {
    		l1.setText("S: Embragando -> auto.embragar(false) ");
    		//auto.embragar(false);
    	} break;
    	case 65 : {
    		l1.setText("A: Nitro -> auto.activarNitro(false) ");
    		//auto.activarNitro(false);
    	} 
    	}//fin switch
    	
    }  
 
  }  