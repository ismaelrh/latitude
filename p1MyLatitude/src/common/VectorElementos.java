/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 *
 * @param <T>
 * 
 * Clase para gestionar elementos que implementen la interfaz Placeable. 
 * Similar a la clase Vector <T> pero con métodos añadidos para la realización
 * de la práctica.
 * 
 */

package common;

import java.util.Vector;

public class VectorElementos<T extends Placeable> extends Vector<T> {

	public VectorElementos() {
		super();
	}

	/*
	 * AÃ±ade todos los elementos a un objeto InterfazCercanos, que se encargarÃ¡
	 * de contener al final sÃ³lo los mÃ¡s cercanos a un usuario de referencia
	 */
	public void getClosest(InterfazCercanos<T> v) {

		for (T elem : this) {
			v.add(elem);
		}
	}

}
