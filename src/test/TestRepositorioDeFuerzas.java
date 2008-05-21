/* ****************************************************************************
 *                         Car-Tunnig-Experience-2008                         *
 *                                                                            *
 *                   Algoritmos y Programacion III - 75.07                    *
 *            Facultad de Ingenieria - Universidad de Buenos Aires            *
 ******************************************************************************/
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import modelo.fuerzas.Fuerza;
import modelo.fuerzas.ReceptorDeFuerzas;
import modelo.fuerzas.RepositorioDeFuerzas;
public class TestRepositorioDeFuerzas {

	//Atributos
	private static HijaReceptorDeFuerzas receptor1;
	private static HijaReceptorDeFuerzas receptor2;
	
	/*Modela una clase hija de ReceptorDeFuerzas diseña con el solo proposito de probar
	 * el correcto funcionamiento de RepocitorioDeFuerzas
	 */
	public class HijaReceptorDeFuerzas implements ReceptorDeFuerzas{
		
		private RepositorioDeFuerzas repositorio;
		
		public HijaReceptorDeFuerzas(){
			repositorio=new RepositorioDeFuerzas(this);
		}
		
		/* (non-Javadoc)
		 * @see modelo.ReceptorDeFuerzas#liberarFuerzas()
		 */
		@Override
		public void liberarFuerzas() {
			this.repositorio.vaciar();
		}

		/* (non-Javadoc)
		 * @see modelo.ReceptorDeFuerzas#recibirFuerza(modelo.Fuerza)
		 */
		@Override
		public void recibirFuerza(Fuerza fuerza) {
			this.repositorio.insertarFuerza(fuerza);			
		}

		/**
		 * @return the repositorio
		 */
		public RepositorioDeFuerzas getRepositorio() {
			return repositorio;
		}

		/**
		 * @param repositorio the repositorio to set
		 */
		public void setRepositorio(RepositorioDeFuerzas repositorio) {
			this.repositorio = repositorio;
		}
		
		
	}
	
	/**
	 * Test method for {@link modelo.fuerzas.RepositorioDeFuerzas#RepositorioDeFuerzas(modelo.fuerzas.ReceptorDeFuerzas)}.
	 */
	@Test
	public void testRepositorioDeFuerzas() {
		receptor1=new HijaReceptorDeFuerzas();
		receptor2=new HijaReceptorDeFuerzas();
		try{	
			assertNotNull(receptor1);
			assertNotNull(receptor2);
			assertNotNull(receptor1.getRepositorio());
			assertNotNull(receptor2.getRepositorio());
		  }catch(AssertionError a){
			System.out.println(a);   
		  }
	}

	/**
	 * Test method for {@link modelo.fuerzas.RepositorioDeFuerzas#insertarFuerza(modelo.fuerzas.Fuerza)}.
	 */
	@Test
	public void testInsertarFuerza() {
	  try{	
		for(int contador=1;contador<20;contador++){
			Fuerza fuerza=new Fuerza(receptor1,receptor2,contador);
			receptor2.recibirFuerza(fuerza);
		}
		assertNotNull(receptor2.getRepositorio());
	  }catch(AssertionError a){
		  System.out.println(a);
	  }
	}
	
	/**
	 * Test method for {@link modelo.fuerzas.RepositorioDeFuerzas#obtenerValorSumatoriaDeFuezas()}.
	 */
	@Test
	public void testObtenerValorSumatoriaDeFuezas() {
		try{	
			assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuerzas()==19);
			assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuerzas()==19);
		}catch(AssertionError a){
			  System.out.println(a);
			  a.printStackTrace();
		}
	}	

	/**
	 * Test de de el metodo vaciar.
	 */
	@Test
	public void testInsercionFuerzasDeAccesoLimitado() {
	  try{	
		receptor2.getRepositorio().vaciar();
		assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuerzas()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
	  }
	}
	
	/**
	 * Test de respuesta de la instancia ante la insercion de fuerzas de acceso limitado
	 */
	@Test
	public void testInsercionFuerzasDeAccesoLimitado2() {
	  try{	
		for(int contador=1;contador<20;contador++){
			Fuerza fuerza=new Fuerza(receptor1,receptor2,1,true);
			receptor2.recibirFuerza(fuerza);
		}
		assertNotNull(receptor2.getRepositorio());
		assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuerzas()==19);
		assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuerzas()==0);
	  }catch(AssertionError a){
		  System.out.println(a);
	  }
	}
	
	/**
	 * Se testea el comportamiento de la clase al introducir fuerzas de acceso limitado
	 */
	@Test 
	public void testComportamientoAlIntroducirFuerzas() {
		 try{	
				//assertNotNull(receptor2.getRepositorio().obtenerFuerzaDeAccesoIlimitadoDeEmisor(receptor1));
			    for(int contador=1;contador<=20;contador++){
					Fuerza fuerza=new Fuerza(receptor1,receptor2,1,true);
					receptor2.recibirFuerza(fuerza);
			    }
				//System.out.println(receptor2.getRepositorio().obtenerValorSumatoriaDeFuezas());
				//assertTrue(receptor2.getRepositorio().obtenerValorSumatoriaDeFuezas()>10);
			}catch(AssertionError a){
				System.out.println(a);
				a.printStackTrace();
	        }
	}
}
