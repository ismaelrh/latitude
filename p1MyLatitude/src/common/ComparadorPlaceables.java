/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Clase que sirve para ordenadr correctamente dos objetos placeables dentro
 * del montículo.
 */
package common;

import java.util.Comparator;

public class ComparadorPlaceables implements Comparator<Placeable> {

	private Placeable ref;
	
	public ComparadorPlaceables (Placeable ref){
		this.ref = ref;
	}
	
	
	@Override
	public int compare(Placeable x, Placeable y) {
			
		if (x.distance(ref)<y.distance(ref))return 1;
		else if (x.distance(ref)==y.distance(ref)) return 0;
		else return -1;
	}

}
