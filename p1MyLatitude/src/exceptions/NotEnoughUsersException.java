/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Excepción que indica que se ha superado el número máximo de usuarios a introducir en
 * algún lugar.
 */
package exceptions;

public class NotEnoughUsersException extends InputOutOfRangeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughUsersException(float outOfRange) {
		super(outOfRange);
		// TODO Auto-generated constructor stub
	}

}
