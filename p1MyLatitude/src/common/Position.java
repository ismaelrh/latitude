/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Clase que guarda la posición geográfica de una entidad.
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
