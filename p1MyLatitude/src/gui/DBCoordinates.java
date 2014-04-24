package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import common.InterfazCercanos;
import common.InterfazCercanosFactory;
import common.Position;
import common.User;
import common.VectorElementos;
public class DBCoordinates extends JPanel{
	JTextArea texto ;
	
	
	DBCoordinates(){
		
		//Texto
		texto = new JTextArea();
		texto.setEditable(false);
		texto.setText("Hola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHola\nHolav");

		//Panel de scroll
		JScrollPane pane = new JScrollPane(texto);
		pane.setPreferredSize(new Dimension(50,150));
		TitledBorder title = BorderFactory.createTitledBorder("Coordenadas en Base de Datos");
		pane.setBorder(title);
		
		//Layout que contrendrá panel de scroll el botón
		BorderLayout layout= new BorderLayout();
		this.setLayout(layout);
		
		//Añadimos el panel de scroll
		this.add(pane,BorderLayout.CENTER);
		
		//Creamos un nuevo layout que almacenará el botón en su este
		BorderLayout layoutBoton= new BorderLayout();
		JPanel temp = new JPanel(layoutBoton);
		this.add(temp,BorderLayout.SOUTH);
		
		//Creamos el botón
		JButton btnUpdate = new JButton("Actualizar");
		temp.add(btnUpdate,BorderLayout.EAST);
		
		
		btnUpdate.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                try{
            	VectorElementos<User> usuarios = MainWindow.database.getUsers(MainWindow.username);
                InterfazCercanos<User> cercanos = 
						InterfazCercanosFactory.interfazCercanosFactoryMethod(0,
								new User(MainWindow.username, MainWindow.panelposition.getPosition()), 10);
                User currentUser = new User (MainWindow.username, MainWindow.panelposition.getPosition());
					usuarios.getClosest(cercanos);
					String temp ="";
					for (common.User u : cercanos) {
						temp+= u.name.trim()
								+ "; posicion: " + u.position.longitude
								+ " E; " + u.position.latitude + " N" + "; distancia: "
								+u.distance(currentUser) + "\n";
						
						MainWindow.imagen.mark(new Position(u.position.latitude, u.position.longitude), Color.blue);
						MainWindow.imagen.repaint();
					}
					texto.setText(temp);
            }
                catch(Exception ex){
                	
                }
            }
        });  

		
		
		
	}
	
}
