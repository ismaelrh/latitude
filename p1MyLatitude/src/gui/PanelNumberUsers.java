package gui;

import java.awt.BorderLayout;
import exceptions.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelNumberUsers extends JPanel {

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
