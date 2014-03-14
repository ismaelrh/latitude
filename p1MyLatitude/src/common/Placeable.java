package common;

/*Esta interfaz especifica el comportamiento de un objeto "posicionable".
 * Un objeto posicionable debería poder devolver una posición y ser capaz
 * de calcular la distancia absoluta respecto a otro objeto Placeable.
 */
public interface Placeable {

	/*
	 * Devuelve, en un objeto Position, la posición del objeto que implementa la
	 * interfaz.
	 */
	public Position getPosition();

	/*
	 * Devuelve la distancia absoluta del objeto que implementa la interfaz
	 * respecto a otro Placeable.
	 */
	public double distance(Placeable user);

}
