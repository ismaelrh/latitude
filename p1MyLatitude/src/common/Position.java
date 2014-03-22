/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Clase que guarda la posici�n geogr�fica de una entidad.
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
