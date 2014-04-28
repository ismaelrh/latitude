package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelNumberUsers extends JPanel {


	JSpinner spinner;
	
	PanelNumberUsers(){
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
	 spinner = new JSpinner();
	 spinner.setValue(new Integer(3));
	 
		TitledBorder titleSpinner = BorderFactory
				.createTitledBorder("Usuarios a mostrar");
		spinner.setBorder(titleSpinner);
		this.add(spinner,BorderLayout.CENTER);
		
	}
	
	public int getUsers(){
		return ((SpinnerNumberModel) spinner.getModel()).getNumber().intValue();
	}
	
}
