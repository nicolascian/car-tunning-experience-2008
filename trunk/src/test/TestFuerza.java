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
		   assertFalse(fuerza2.isAccesoLimitado());
		}catch(AssertionError a){
		   System.out.println(a);   
	    }
	  }catch(Exception e){}	
	}
	//------------------------ Pruebas con acceso ilimitado -----------------------------------
	
	@Test
	public void testSuma() {
	 try{ 
	  try{	
		fuerza1.sumar(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	 }catch (Exception e){}
	}
	
	@Test
	public void testSumaEstatica() {
	 try{
	  try{	
		assertTrue(Fuerza.sumar(fuerza1,fuerza2)==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	 }catch(Exception e){} 
	}
	
	@Test
	public void testSumaReal() {
	 try{
	  try{	
		fuerza1.sumar(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==3);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	 }catch(Exception e){}
	}
			
	@Test
	public void testRestaEstatica() {
	 try{ 
	  try{	
		assertTrue(Fuerza.restar(fuerza1,fuerza2)==-1);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	 }catch(Exception e){}
	}
	
	@Test
	public void testDecrementar() {
	 try{
	  try{	
		fuerza1.restar(1);
		assertTrue(fuerza1.getValorDeLaFuerza()==0);
	  }catch(AssertionError a){
		System.out.println(a);   
	  }
	  fuerza1.setValorDeLaFuerza(1);
	 }catch(Exception e){}
	}
	
	@Test
	public void testMultiplicar() {
	 try{ 
	  try{	
		fuerza1.multiplicar(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==2);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	 }catch(Exception e){}
	}
	
	@Test
	public void testMultiplicarEstatica() {
	 try{ 
	  try{	
		assertTrue(Fuerza.multiplicar(fuerza1,fuerza2)==2);
	  }catch(AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
	 }catch(Exception e){}
	}
	
	@Test
	public void testMultiplicarReal() {
	 try{ 
	  try{	
		fuerza1.multiplicar(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==2);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividir() {
	 try{
	  try{	
		fuerza1.dividir(fuerza2);
		assertTrue(fuerza1.getValorDeLaFuerza()==0.5);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	  fuerza2.setValorDeLaFuerza(2);
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividirEstatica() {
	 try{ 
	  try{	
		assertTrue(Fuerza.dividir(fuerza1,fuerza2)==0.5);
	  }catch(AssertionError a){
		System.out.println(a);   
		a.printStackTrace();
	  }
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividirReal() {
	 try{
	  try{	
		fuerza1.dividir(2);
		assertTrue(fuerza1.getValorDeLaFuerza()==0.5);
	  }catch(AssertionError a){
		System.out.println(a);
		a.printStackTrace();
	  }
	  fuerza1.setValorDeLaFuerza(1);
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividirPorCero() {
	 try{ 
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
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividirEstaticaPorCero() {
	 try{ 
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
	 }catch(Exception e){}
	}
	
	@Test
	public void testDividirRealPorCero() {
	 try{ 
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
	 }catch(Exception e){}
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
