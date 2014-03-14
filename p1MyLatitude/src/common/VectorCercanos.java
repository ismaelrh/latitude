package common;

import java.util.Vector;

public class VectorCercanos<T extends Placeable> extends Vector<T> implements InterfazCercanos<T> {

	private T ref;
	private int capacidad; // Capacidad maxima del vector
	private int num; // Numero de elementos almacenados actualmente

	public VectorCercanos(T ref, int capacidad) {
		super();
		this.ref = ref;
		this.capacidad = capacidad;
	}

	@Override
	/*
	 * Si el vector no esta lleno, añade
	 */
	public boolean add(T data) {

		// Si hay hueco, lo meto directamente
		if (num < capacidad) {
			super.add(data);
			num++;

		}
		// Si no, sustituyo por el mas lejano si el que introduzco es mas
		// cercano
		else {
			int farestIndex = theFarest();
			if (ref.distance(data) < ref.distance(get(farestIndex))) {
				set(farestIndex, data);

			} else
				return false;
		}
		return true;

	}

	// Devuelve el indice del mayor 
	private int theFarest() {
		double maxDistance = 0;
		T theFarest = null;

		for (T d : this) {
			Double distance = d.distance(ref);
			if (distance > maxDistance) {
				maxDistance = distance;
				theFarest = d;
			}

		}
		return indexOf(theFarest);
	}

}
