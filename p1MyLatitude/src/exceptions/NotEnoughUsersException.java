package exceptions;

public class NotEnoughUsersException extends InputOutOfRangeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughUsersException(float max) {
		super(Float.MIN_VALUE, max);
		// TODO Auto-generated constructor stub
	}

}
