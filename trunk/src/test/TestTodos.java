package test;
import junit.framework.*;


public class TestTodos extends TestCase {
	public static Test suite(){
		TestSuite pruebaTodos =new TestSuite();
		pruebaTodos.addTestSuite(TestAuto.class);
		pruebaTodos.addTestSuite(TestAutoDeEscritorio.class);
		//pruebaTodos.addTestSuite(TestAutomatica.class);
		pruebaTodos.addTestSuite(TestAutoPotencias.class);
		pruebaTodos.addTestSuite(TestAutoSecondEdition.class);
		pruebaTodos.addTestSuite(TestCarburador.class);
		pruebaTodos.addTestSuite(TestClima.class);
		pruebaTodos.addTestSuite(TestCombustible.class);
		pruebaTodos.addTestSuite(TestEje.class);
		pruebaTodos.addTestSuite(TestEscape.class);
		pruebaTodos.addTestSuite(TestExperto.class);
		pruebaTodos.addTestSuite(TestFuerza.class);
		pruebaTodos.addTestSuite(TestIntermedio.class);
		pruebaTodos.addTestSuite(TestInyeccion.class);
		pruebaTodos.addTestSuite(TestLlanta.class);
		pruebaTodos.addTestSuite(TestManual.class);
		pruebaTodos.addTestSuite(TestMotor.class);
		pruebaTodos.addTestSuite(TestNeumatico.class);
		pruebaTodos.addTestSuite(TestPista.class);
		pruebaTodos.addTestSuite(TestPrincipiante.class);
		pruebaTodos.addTestSuite(TestRepositorioDeFuerzas.class);
		pruebaTodos.addTestSuite(TestSecuencial.class);
		pruebaTodos.addTestSuite(TestSuperficie.class);
		pruebaTodos.addTestSuite(TestSuspencion.class);
		pruebaTodos.addTestSuite(TestTramo.class);
		pruebaTodos.addTestSuite(TestTurbo.class);
		return pruebaTodos;
	}
}
