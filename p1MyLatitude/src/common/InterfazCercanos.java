/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 */
package common;

/*Esta interfaz abstrae la funcionalidad de una colección de Placeables cercanos.
 * Dispone de métodos para añadir un Placeable. Debe ser Iterable, ya que
 * necesitamos iterar por la colección.
 */

public interface InterfazCercanos<T extends Placeable> extends Iterable<T> {

	/* Sea data un Placeable, lo añade a la colección */
	public boolean add(T data);

}
