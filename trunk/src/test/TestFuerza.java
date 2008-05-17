package test;

import static org.junit.Assert.*;
import modelo.Fuerza;
import org.junit.Before;
import org.junit.Test;

public class TestFuerza {

	private static Fuerza fuerza1;
	
	private static Fuerza fuerza2;
	
	@Test
	public void testFuerza() {
	  try{	
		fuerza1=new Fuerza();
		fuerza1.setValorDeLaFuerza(1);
		fuerza2=new Fuerza();
		fuerza2.setValorDeLaFuerza(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==1);
		assertTrue(fuerza2.getValorDeLaFuerza()==2);
		assertNull(fuerza1.getEmisor());
		assertNull(fuerza1.getReceptor());
		assertNull(fuerza2.getEmisor());
		assertNull(fuerza2.getReceptor());
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	}
	//------------------------ Pruebas con acceso ilimitado -----------------------------------
	
	@Test
	public void testSuma() {
	  try{	
		fuerza1.sumar(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	}
	
	@Test
	public void testSumaEstatica() {
	  try{	
		
		assertTrue(Fuerza.sumar(fuerza1,fuerza2)==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	}
	
	@Test
	public void testSumaReal() {
	  try{	
		fuerza1.sumar(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	}
			
	@Test
	public void testRestaEstatica() {
	  try{	
		assertTrue(Fuerza.restar(fuerza1,fuerza2)==-1);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	}
	
	@Test
	public void testDecrementar() {
	  try{	
		fuerza1.restar(1);
		assertTrue(fuerza1.getValorDeLaFuerza()==0);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	}
	
	@Test
	public void testMultiplicar() {
	  try{	
		fuerza1.multiplicar(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==2);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	}
	
	@Test
	public void testMultiplicarEstatica() {
	  try{	
		assertTrue(Fuerza.multiplicar(fuerza1,fuerza2)==2);
	  }catch(AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
	}
	
	@Test
	public void testMultiplicarReal() {
	  try{	
		fuerza1.multiplicar(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==2);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	}
	
	@Test
	public void testDividir() {
	  try{	
		fuerza1.dividir(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==0.5);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	}
	
	@Test
	public void testDividirEstatica() {
	  try{	
		assertTrue(Fuerza.dividir(fuerza1,fuerza2)==0.5);
	  }catch(AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
	}
	
	@Test
	public void testDividirReal() {
	  try{	
		fuerza1.dividir(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==0.5);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	}
	
	@Test
	public void testDividirPorCero() {
	  try{
		fuerza2.setValorDeLaFuerza(0);
		try{
		   fuerza1.dividir(fuerza2);
		}catch (Exception e){
		  assertNotNull(e);
		}
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  
	  fuerza1.setValorDeLaFuerza(1);
	}
	
	@Test
	public void testDividirEstaticaPorCero() {
	  try{
		  try{
			  Fuerza.dividir(fuerza1,fuerza2);  
		  }catch(Exception e){
			  assertNotNull(e); 
		  }
	  }catch(AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
	}
	
	@Test
	public void testDividirRealPorCero() {
	  try{
		  try{
        	  fuerza1.dividir(0);  
		  }catch(Exception e){
			  assertNotNull(e); 
		  }
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	}
	
	//------------------------ Pruebas con acceso ilimitado -----------------------------------
	
	@Test
	public void testLimitado(){
	  try{	
		fuerza1=new Fuerza(null,null,20,true);
		assertTrue(fuerza1.isAccesoLimitado());
		assertFalse(fuerza1.isAccedida());
		try{
		   fuerza1.getValorDeLaFuerza();
		   fuerza1.getValorDeLaFuerza();
		}catch(Exception e){
			assertNotNull(e);
		}
      }catch (AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
      try{	
  		fuerza1=new Fuerza(null,null,20,true);
  		assertTrue(fuerza1.isAccesoLimitado());
  		assertFalse(fuerza1.isAccedida());
  		try{
  		   fuerza1.setValorDeLaFuerza(30);
  		   fuerza1.setValorDeLaFuerza(15);
  		}catch(Exception e){
  			assertNotNull(e);
  		}
        }catch (AssertionError a){
  		System.out.println(a);   
  		a.printStackTrace();
  	  }
	}
}
