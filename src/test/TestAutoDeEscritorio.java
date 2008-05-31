package test;
import modelo.*;
import junit.framework.*;

public class TestAutoDeEscritorio extends TestCase {
	
	
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
		
		
		System.out.print("esta listo: ");
		System.out.println(auto.estaListoParaCarrera());
		
		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());
		
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
		
			auto.setEncendido(true);
			System.out.println("ENCENDER AUTO");

		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());	
			
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
			
		long tiempo=System.currentTimeMillis()+1000;
		 while(System.currentTimeMillis()<tiempo);
		
		System.out.print("RPM: ");
		System.out.println(auto.getMotor().getRPM());
		  
			auto.acelerar(true);
			System.out.println("ACELERAR");
		
			assertEquals(true, auto.isEncendido());
			assertEquals(true, auto.getMotor().isAcelerando());
		
		System.out.print("auto encendido: ");
		System.out.println(auto.isEncendido());
		
		System.out.print("motor acelerando: ");
		System.out.println(auto.getMotor().isAcelerando());
		
		long tiempo1=System.currentTimeMillis()+1000;
		 while(System.currentTimeMillis()<tiempo1);
		
		System.out.print("RPM: ");
		System.out.println(auto.getMotor().getRPM());
		

		//System.out.print("RPM Maximas Cambio: ");
		//System.out.println(auto.getCaja().getRevolucionesMaximasCambio());
		
		
		int contador =0;
		while( contador <= 40){

			
			long tiempo2=System.currentTimeMillis()+1000;
			 while(System.currentTimeMillis()<tiempo2); 

			 auto.actualizarVelocidadYPosicion();
			 
			System.out.print("Cambio: ");
			System.out.print(auto.getCaja().getCambio());
			
			System.out.print("  RPM: ");
			System.out.print((long)auto.getMotor().getRPM());
	
			System.out.print("  Pot: ");
			System.out.print((long)auto.getPotenciaTotal()); 
						
			System.out.print("  V: ");
			System.out.print((long)auto.getVelocidad());

			System.out.print("  P: ");
			System.out.print((long)auto.getPosicion());
			
			System.out.print("  Estado: ");
			System.out.print((long)auto.getEstado());
			
			System.out.print("  DI: ");
			System.out.print(auto.getEjeDelantero().getLlantaIzquierda().getNeumatico().calcularAdherencia());
			
			System.out.print("  DD: ");
			System.out.print(auto.getEjeDelantero().getLlantaDerecha().getNeumatico().calcularAdherencia());
			
			System.out.print("  TI: ");
			System.out.print(auto.getEjeTrasero().getLlantaIzquierda().getNeumatico().calcularAdherencia());
			
			System.out.print("  TD: ");
			System.out.println(auto.getEjeTrasero().getLlantaDerecha().getNeumatico().calcularAdherencia());

			//System.out.print(" RpmMaxCambio: ");
			//System.out.println(auto.getMotor().getRevolucionesMaximasCambio());
			
			contador++;
		}//fin while
	
		
		
	}
	

}
