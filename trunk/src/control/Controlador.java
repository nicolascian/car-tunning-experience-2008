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
	
	/** Constructor */
  public Controlador(/*Auto auto*/)   {  
    
    //this.auto = auto;
   
  }
  
    public void keyTyped ( KeyEvent e ){  
    //l1.setText("Key Typed " + e);
     }
    
    /** Se ejecuta cuando se presiona una tecla */
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
    	}//fin switch
    	
    	
    	if (auto.isSecuencial()){
    	
    		switch (e.getKeyCode()){
    		case 85 : {System.out.println("U: Neutro");
						//auto.getCaja().setCambio(0);
    		} break;
    		case 74 : {System.out.println("J: Reversa");
						//auto.getCaja().setCambio(-1);
    		} break;
    		case 73 : {System.out.println("I: Primera");
    					//auto.getCaja().setCambio(1);
    		} break;
    		case 75 : {System.out.println("K: Segunda");
    					//auto.getCaja().setCambio(2);
    		} break;
    		case 79 : {System.out.println("O: Tercera");
						//auto.getCaja().setCambio(3);
    		} break;
    		case 76 : {System.out.println("L: Cuarta");
						//auto.getCaja().setCambio(4);
    		} break;
    		case 80 : {System.out.println("P: Quinta");
						//auto.getCaja().setCambio(5);
    		} break;
    		case 0  : {System.out.println("Ñ: Sexta");
    					//auto.getCaja().setCambio(6);
    		} break;
    		}//fin switch
    	}
    	
    	if (auto.isManual()){
    		
    		switch (e.getKeyCode()){
    		case 73 : {System.out.println("I: Siguiente");
						//auto.getCaja().siguiente();
    		} break;
    		case 75 : {System.out.println("K: Anterior");
						//auto.getCaja().anterior();
    		} break;
    		}//fin switch
    	}
    	
    }
    
    /** Se ejecuta cuando se suelta un tecla */
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
    	} break;
    	}//fin switch
    	
    }  
 
  }  