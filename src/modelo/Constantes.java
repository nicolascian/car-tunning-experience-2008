package modelo;

public abstract class Constantes {

/*
  			<<<<< Borrar este comentario antes de entrega  >>>>>
 
	Nota Importante!!! (Rafael): Hice cuentas teniendo en cuenta un procesador de 3 Ghz , 

	teniendo en cuenta el tiempoPorCiclo de 0.00001 que ya tenemos puesto en Constante, llegue 
	a la siguiente ecuacion: 
		
		TiempoDeDuracionDeComponente=0.0033/constanteDeDesgaste  
		
		
		-->TiempoDeDuracionDeComponente: en segundos
		-->constanteDeDesgaste: seria el desgaste en cada componente que ustedes le den a cada clase
		         5E-6<= constanteDeDesgaste <=9E-6
			
	  Con la siguiente Ecuacion de desgaste:

	public void Desgastar(){  
	  setEstado(getEstado()-tiempoPorCiclo*constanteDeDesgaste
	}  

	Con estos calculos un componente con una constante de 5E-6 deberia durar 5 carreras
	de 2 minutos (120 segundos )cada una.
*/	
	
	public static double tiempoPorCiclo = (0.00001);

}
