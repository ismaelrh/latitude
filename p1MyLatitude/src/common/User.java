package common;

public class User implements Placeable {
	public String name;
	public Position position;

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
