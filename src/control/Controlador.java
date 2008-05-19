/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/

package control;
import modelo.Auto;

import java.awt.event.*;
   
public class Controlador implements KeyListener{  

	
	Auto auto;
	
	
  public Controlador(/*Auto auto*/)   {  
    
    //this.auto = auto;
   
  }
  
    public void keyTyped ( KeyEvent e ){  
    //l1.setText("Key Typed " + e);
     }
    
    public void keyPressed ( KeyEvent e){   
    	
    	switch (e.getKeyCode()){
    	case 70 : {
    		System.out.println("F: Acelerador -> auto.acelerar(true) ");
    		//auto.acelerar(true);
    	} break;
    	case 68 : {
    		System.out.println("D: Frenando -> auto.frenar(true) ");
    		//auto.frenar(true);
    	} break;
    	case 83 : {
    		System.out.println("S: Embragando -> auto.embragar(true) ");
    		//auto.embragar(true);
    	} break;
    	case 65 : {
    		System.out.println("A: Nitro -> auto.activarNitro(true) ");
    		//auto.activarNitro(true);
    	} break;
    	case 69 : {
    		System.out.println("E: Encender -> auto.setEncendido(true) ");
    		//auto.setEncendido(true);
    	} break;
    	
    	
    	case 73 : {System.out.println("I: Primera");} break;
    	case 75 : {System.out.println("K: Segunda");} break;
    	case 79 : {System.out.println("O: Tercera");} break;
    	case 76 : {System.out.println("L: Cuarta");} break;
    	case 80 : {System.out.println("P: Quinta");} break;
    	case 0  : {System.out.println("Ñ: Sexta");} break;
    	}
    	
    }
    
    public void keyReleased ( KeyEvent e ){  
    	
    	switch (e.getKeyCode()){
    	case 70 : {
    		System.out.println("F: Acelerador -> auto.acelerar(false) ");
    		//auto.acelerar(false);
    	} break;
    	case 68 : {
    		System.out.println("D: Frenando -> auto.frenar(false) ");
    		//auto.frenar(false);
    	} break;
    	case 83 : {
    		System.out.println("S: Embragando -> auto.embragar(false) ");
    		//auto.embragar(false);
    	} break;
    	case 65 : {
    		System.out.println("A: Nitro -> auto.activarNitro(false) ");
    		//auto.activarNitro(false);
    	} 
    	}//fin switch
    	
    }  
 
  }  