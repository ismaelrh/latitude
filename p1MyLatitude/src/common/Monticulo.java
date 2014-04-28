/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Clase que implementa un montículo o heap para la ejecución más eficiente de la
 * aplicación.
 */
package common;

import java.util.PriorityQueue;

public class Monticulo <T extends Placeable> extends PriorityQueue <T> 
			implements InterfazCercanos<T> {

	private int capacidad; 	// Capacidad maxima del vector
	private int num; 		// Numero de elementos almacenados actualmente

	public Monticulo(T ref, int capacidad) {
		super(capacidad,new ComparadorPlaceables(ref));
		this.capacidad = capacidad;
		num=0;
	}
	
	@Override
	public boolean add(T e){
	
		if (num<capacidad) {
			num++;
			return super.add(e);
		}
		else{
			if (this.comparator().compare(e, this.element()) > 0){
				//Lo añade
				this.remove();
				return super.add(e);
			}
			else return false;
		}
	}
	
	

}
