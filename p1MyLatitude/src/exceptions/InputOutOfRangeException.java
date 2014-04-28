/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Excepci�n padre de las otras dos excepciones del package, indica que un cierto valor
 * est� fuera de rango.
 */
package exceptions;

public class InputOutOfRangeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private float outOfRange;
	
	public InputOutOfRangeException(float outOfRange){
		this.outOfRange= outOfRange;
	}
	
	public float getOutOfRange() {
		return outOfRange;
	}
}
