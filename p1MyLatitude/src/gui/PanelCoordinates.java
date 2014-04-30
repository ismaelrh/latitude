/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Clase que controla la ventana de texto lateral donde se muestran todos los usuarios
 * especificados y sus coordenadas
 */
package gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelCoordinates extends JPanel{


	private static final long serialVersionUID = 1L;
	
	JTextArea texto ;
	PanelCoordinates(){
		
		//Texto
		texto = new JTextArea();
		texto.setEditable(false);
		//updateList(); //Actualizamos por primera vez

		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		//Panel de scroll
		JScrollPane pane = new JScrollPane(texto);
		pane.setPreferredSize(new Dimension(50,150));
		TitledBorder title = BorderFactory.createTitledBorder("Coordenadas en Base de Datos");
		pane.setBorder(title);
		
		this.add(pane,BorderLayout.CENTER);
	}
	
	//Establece el texto del scrollpane
	public void  setText(String text){
		texto.setText(text);
	}
	
	//Actualiza la lista según cercanos
	public void updateList(){
		String temp = "";
		for(common.User u: MainWindow.cercanos){
			temp+= u.name.trim()
					+ "; posicion: " + u.position.longitude
					+ " E; " + u.position.latitude + " N\n";
			
		}
		setText(temp);
	}
	
}
