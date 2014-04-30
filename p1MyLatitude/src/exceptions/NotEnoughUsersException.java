/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Excepcion que indica que se ha superado el numero maximo de usuarios a introducir en
 * algun lugar.
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
