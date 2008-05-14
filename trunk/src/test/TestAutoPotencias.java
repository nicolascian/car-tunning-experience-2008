package test;

import junit.framework.*;
import modelo.*;

public class TestAutoPotencias extends TestCase {
	
	
	public static void main (String[] args)
	{
		
		Auto auto = new Auto();
		System.out.println("CREAR AUTO POR DEFAULT");
		
			auto.setEncendido(true);
			System.out.println("ENCENDER AUTO");
  
			auto.acelerar(true);
			System.out.println("ACELERAR");
		
				
		int contador =0;
		while( contador <= 40){

			
			long tiempo2=System.currentTimeMillis()+1000;
			 while(System.currentTimeMillis()<tiempo2); 
		
			 
				System.out.println("motor: "+auto.getMotor().obtenerPotencia());   /* de aca salen: Alimentacion, Combustible*/
				System.out.println("-alimen: "+auto.getAlimentacion().obtenerPotencia());
				System.out.println("-combus: "+auto.getCombustible().obtenerPotencia()); 
				System.out.println("caja: "+auto.getCaja().obtenerPotencia() );
				System.out.println("susp: "+auto.getSuspension().obtenerPotencia() );
				System.out.println("escp: "+auto.getEscape().obtenerPotencia() );
				System.out.println("ejeD: "+auto.getEjeDelantero().obtenerPotencia() );//de aca salen: llantas y neumaticos delanteros
				System.out.println("ejeT: "+auto.getEjeTrasero().obtenerPotencia() );//de aca salen: llantas y neumaticos traseros
				System.out.println("turb: "+auto.getTurbo().obtenerPotencia() ); 
		
	
			System.out.print("  PotTOTAL: ");
			System.out.println(auto.getPotenciaTotal()); 
			System.out.println("");
		
			contador++;
		}//fin while
	
		
		
	}
	

}
