package test;

import junit.framework.*;
import modelo.*;

public class TestAutoSecondEdition extends TestCase {
	
	
	public static void main (String[] args)
	{
		
		Auto auto = new Auto();
		System.out.println("CREAR AUTO POR DEFAULT");
		
		
		System.out.print("caja tipo manual: ");
		System.out.println(auto.isManual());
		
		System.out.print("caja tipo secuencial: ");
		System.out.println(auto.isSecuencial());
		
		System.out.print("caja tipo automatica: ");
		System.out.println(auto.isAutomatica());
		
			Caja automatica = new Automatica(5);
			System.out.println("CREAR CAJA AUTOMATICA");
		
			auto.setCaja(automatica);
			System.out.println("COLOCAR CAJA AUTOMATICA");
		
		
		System.out.print("esta listo: ");
		System.out.println(auto.estaListoParaCarrera());
		
		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());
		
		System.out.print("motor encendido: ");
		System.out.println(auto.getMotor().isEncendido());
		
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
		
			auto.setEncendido(true);
			System.out.println("ENCENDER AUTO");

		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());
			
		System.out.print("motor encendido: ");
		System.out.println(auto.getMotor().isEncendido());	
			
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
			
			auto.acelerar(true);
			System.out.println("ACELERAR");
		
			assertEquals(true, auto.isEncendido());
			assertEquals(true, auto.getMotor().isAcelerando());
		
		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());
		
		System.out.print("motor encendido: ");
		System.out.println(auto.getMotor().isEncendido());
		
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
		
		
		System.out.print("caja tipo manual: ");
		System.out.println(auto.isManual());
		
		System.out.print("caja tipo secuencial: ");
		System.out.println(auto.isSecuencial());
		
		System.out.print("caja tipo automatica: ");
		System.out.println(auto.isAutomatica());
		
		
		System.out.print("RPM: ");
		System.out.println(auto.getMotor().getRPM());
		
		System.out.print("Cambio: ");
		System.out.println(auto.getCaja().getCambio());
		
		/* lo pongo a 7000 rpm */ 
		/*while( auto.getMotor().getRPM() <= 700){*/
		
		int contador =0;
		while( contador <= 1500){

			/*
			long tiempo=System.currentTimeMillis()+1000;
			 while(System.currentTimeMillis()<tiempo);  */
			

			System.out.print("Cambio: ");
			System.out.print(auto.getCaja().getCambio());
			
			System.out.print("  RPM: ");
			System.out.print((long)auto.getMotor().getRPM());
			
			System.out.print("  A: ");
			System.out.print((long)auto.getAceleracion());
			
			System.out.print("  V: ");
			System.out.print((long)auto.getVelocidad());

			System.out.print("  P: ");
			System.out.print((long)auto.getPosicion());
			
			System.out.print("  Estado: ");
			System.out.println((long)auto.getEstado());

			contador++;
		}//fin while
		
		
		
	}
	

}
