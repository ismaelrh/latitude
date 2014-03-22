/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Clase que representa a los usuario del sistema. Todos ellos implementan la interfaz
 * Placeable para que puedan ser localizados.
 */

package common;

public class User implements Placeable {
	public String name; 		//Guarda el nombre del usuario
	public Position position;	//Contiene su posición geográfica

	public User(String n, Position p) {
		name = n;
		position = p;
	}

	/**
	 * Dado un Placeable, devuelve la distancia respecto a otro placeable.
	 */
	public double distance(Placeable user) {

		double distX = position.latitude - user.getPosition().latitude; 

		double distY = position.longitude - user.getPosition().longitude; 
		
		return Math.sqrt(distX * distX + distY * distY);

	}

	public Position getPosition() {
		return this.position;
	}
}
