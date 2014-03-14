package common;

import java.util.Vector;

public class VectorElementos<T extends Placeable> extends Vector<T> {

	public VectorElementos() {
		super();
	}

	/*
	 * Añade todos los elementos a un objeto InterfazCercanos, que se encargará
	 * de contener al final sólo los más cercanos a un usuario de referencia
	 */
	public void getClosest(InterfazCercanos<T> v) {

		for (T elem : this) {
			v.add(elem);
		}
	}

}
