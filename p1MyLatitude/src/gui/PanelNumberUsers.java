/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Clase que representa en la parte derecha el numero de usuarios que se quieren ver en 
 * el mapa, es un cuadro con dos flechas.
 */
package gui;

import java.awt.BorderLayout;
import exceptions.*;
import javax.swing.*;

public class PanelNumberUsers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	int lastUsersNumber;
	JSpinner spinner;
	
	PanelNumberUsers(){
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
	 spinner = new JSpinner();
	 
	 lastUsersNumber = 3; 
	 
	 spinner.setValue(lastUsersNumber);
	 

		this.add(spinner,BorderLayout.CENTER);
		
	}
	
	public int getUsers() throws InputOutOfRangeException{
		
		int currentNumber =((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
		
		if (currentNumber <=0){
			spinner.setValue(lastUsersNumber);
			throw new NegativeUsersException(currentNumber);
		}
		else if (currentNumber > MainWindow.usuarios.size()){
			spinner.setValue(lastUsersNumber);
			throw new NotEnoughUsersException(currentNumber);
		}
		else {
			lastUsersNumber = currentNumber;
			return currentNumber;
		}
	}
	
}
