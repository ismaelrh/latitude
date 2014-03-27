/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 *
 * @param <T>
 * 
 * Clase para gestionar un número <num> de elementos que implementen la 
 * interfaz Placeable. Tiene similar comportamiento a la clase Vector <T> pero
 * con el método add(<T>) modificado para la realización de la práctica.
 * 
 */
package common;

import java.util.Vector;

public class VectorCercanos<T extends Placeable> extends Vector<T> implements InterfazCercanos<T> {

	private T ref;			// Elemento de referencia sobre el que se calcula la distancia
	private int capacidad; 	// Capacidad maxima del vector
	private int num; 		// Numero de elementos almacenados actualmente

	public VectorCercanos(T ref, int capacidad) {
		super();
		this.ref = ref;
		this.capacidad = capacidad;
	}

	@Override
	/*
	 * Si el vector no esta lleno, aÃ±ade
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
			int farestIndex = theFarthest();
			if (ref.distance(data) < ref.distance(get(farestIndex))) {
				set(farestIndex, data);

			} else
				return false;
		}
		return true;

	}

	// Devuelve el indice del mayor 
	private int theFarthest() {
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
