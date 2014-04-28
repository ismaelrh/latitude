package exceptions;

public class InputOutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private float maxRange;
	private float minRange;
	
	public InputOutOfRangeException(float min, float max){
		minRange = min;
		maxRange = max;
	}
	
	public float getMaxRange() {
		return maxRange;
	}

	public float getMinRange() {
		return minRange;
	}
	
}
