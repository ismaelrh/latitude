/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Clase que guarda la posicion geografica de una entidad.
 */

package common;

public class Position {
	public double latitude;
	public double longitude;
	
	public Position(double lat, double lon)
	{
		latitude=lat; longitude = lon;
	}
}
