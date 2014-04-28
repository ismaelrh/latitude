/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Excepci�n que indica que se ha superado el n�mero m�ximo de usuarios a introducir en
 * alg�n lugar.
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
