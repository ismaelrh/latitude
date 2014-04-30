/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Clase que crea el cuadro desplegable en la parte derecha de la ventana para poder
 * elegir entre dos estructuras de datos diferentes a la hora de utilizar la informacion.
 */
package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class PanelStructure extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JComboBox<String> desplegable;
	PanelStructure(){
		
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		
		String[] contenido = {"Vector","Montículo"};
		desplegable = new JComboBox<String>(contenido);
		
		
		this.add(desplegable,BorderLayout.CENTER);
		
		}
	
	public int getStructure(){
		
		         return desplegable.getSelectedIndex(); 
	}
	}
	
		
	
