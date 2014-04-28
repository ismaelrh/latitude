package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelStructure extends JPanel {

	
	JComboBox desplegable;
	PanelStructure(){
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		
		String[] contenido = {"Vector","Montículo"};
		desplegable = new JComboBox(contenido);
		
		
		this.add(desplegable,BorderLayout.CENTER);
		
		}
	
	public int getStructure(){
		
		         return desplegable.getSelectedIndex(); 
	}
	}
	
		
	
