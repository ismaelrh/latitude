/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Clase factory que permite que se devuelva una lista o un mont�culo dependiendo
 * de lo que se le pasa por el parametro choice
 */
package common;

public class InterfazCercanosFactory {

	public static < T extends Placeable>  InterfazCercanos <T> interfazCercanosFactoryMethod
	(int choice, T ref, int capacidad){
		
		InterfazCercanos<T> product = null;
		
		switch (choice){
			case 0:
				product = new VectorCercanos<T> (ref, capacidad);
				break;
			case 1:
				product = new Monticulo<T> (ref, capacidad);
				break;
		}
		
		return product;
	}
	
}
