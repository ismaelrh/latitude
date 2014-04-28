/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Clase factory que permite que se devuelva una lista o un mont�culo dependiendo
 * de lo que se le pasa por el par�metro choice
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
