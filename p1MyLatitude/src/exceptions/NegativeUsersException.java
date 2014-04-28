package exceptions;

public class NegativeUsersException extends InputOutOfRangeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NegativeUsersException(float min) {
		super(min, Float.MAX_VALUE);
		// TODO Auto-generated constructor stub
	}

}
