package exceptions;

public class InputOutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float outOfRange;
	
	public InputOutOfRangeException(float outOfRange){
		this.outOfRange= outOfRange;
	}
	
	public float getOutOfRange() {
		return outOfRange;
	}
}
