/**
 * @author Pablo Lanaspa e Ismael Rodríguez
 * 
 * Excepción que indica que se ha introducido un número negativo de usuarios.
 */
package exceptions;

public class NegativeUsersException extends InputOutOfRangeException {

	private static final long serialVersionUID = 1L;
	
	public NegativeUsersException(float outOfRange) {
		super(outOfRange);
		// TODO Auto-generated constructor stub
	}

}
