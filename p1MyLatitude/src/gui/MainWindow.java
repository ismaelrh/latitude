/**
 * @author Pablo Lanaspa e Ismael Rodriguez
 * 
 * Clase que lanza la ventana principal de la aplicacion y a partir de donde se gestiona
 * el resto de botones y complementos dentro de ella.
 */
package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;


import common.InterfazCercanosFactory;
import common.Position;
import common.User;
import exceptions.InputOutOfRangeException;
import exceptions.NegativeUsersException;
import exceptions.NotEnoughUsersException;

import java.sql.SQLException;
import java.text.*;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String mapname = "data/Iberian_Peninsula.jpg";
	// Los limites geograficos del mapa son: N: 44.4º N; S: 34.7º N; W: 9.9º; E:
	// 4.8ºE
	public static final double minlongitude = -4.19;
	public static final double maxlongitude = 9.18;
	public static final double minlatitude = 34.7;
	public static final double maxlatitude = 44.4;
	static common.InterfazCercanos<common.User> cercanos;
	static common.VectorElementos<User> usuarios ;

	PanelPosition panelposition;
	Map imagen;
	PanelCursor panelcursor;
	PanelNumberUsers panelnumberusers;
	PanelCoordinates panelcoordinates;
	PanelStructure panelstructure;
	db.DatabaseConnection database;

	String username;

	public MainWindow(db.DatabaseConnection db) {
		database = db;
		imagen = new Map(mapname, minlatitude, minlongitude, maxlatitude,
				maxlongitude);
		panelposition = new PanelPosition();
		panelcursor = new PanelCursor();
		panelcoordinates = new PanelCoordinates();
		 panelnumberusers = new PanelNumberUsers();
		panelstructure = new PanelStructure();
	}

	public void start() throws SQLException {
		String s = (String) JOptionPane.showInputDialog(this,
				"Nombre de usuario:", "Login", JOptionPane.PLAIN_MESSAGE);

		this.setTitle("Mapa de Localizaciones");

		// Si se obtuvo una cadena...
		if ((s != null) && (s.length() > 0)) {
			this.setTitle("MyLatitude - " + s);
			if (!database.loginUser(s))
				database.addUser(s, new common.Position(41.39, 0.53));
			username = s;
			updateListas(); //Actualizamos la lista de usuarios
			distribute();
		} else
			this.dispose();
	}

	private void distribute() throws SQLException {
		panelposition.setPosition(database.getPosition(username));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creamos un panel para la derecha, en el centro esta el mapa
		BorderLayout layout = new BorderLayout();

		// JPanel para el marco de la derecha
		JPanel right = new JPanel();
		// JPanel para los datos, luego habrá para lista, etc.
		JPanel data = new JPanel();
		right.add(data, BorderLayout.CENTER);

		// En el panel de la derecha, donde estan los datos, vamos a ponerlos
		// uno debajo de
		// otro (BoxLayout)
		// Layout vertical
		BoxLayout dataLayout = new BoxLayout(data, BoxLayout.Y_AXIS);
		data.setLayout(dataLayout);

		JPanel viewportPanel = new JPanel();
		viewportPanel.add(imagen, BorderLayout.CENTER);

		// Un layout BorderLayout tiene NORTH,SOUTH, EAST, WEST Y CENTER
		this.getContentPane().setLayout(layout);
		// Añade al centro la imagen con scroll, en el CENTRO
		this.getContentPane().add(new JScrollPane(viewportPanel),
				BorderLayout.CENTER);
		// Añade en el este el panel de la derecha, que incluye el data
		this.getContentPane().add(right, BorderLayout.EAST);

		/* Añadimos panelPosition al frame data */
		data.add(panelposition);
		TitledBorder titleCoordenadas = BorderFactory
				.createTitledBorder("Coordenadas locales");
		panelposition.setBorder(titleCoordenadas);


		TitledBorder titleSpinner = BorderFactory
				.createTitledBorder("Usuarios a mostrar");
		panelnumberusers.setBorder(titleSpinner);
		data.add(panelnumberusers);
		
		//Añadimos el panel panelCoordinates
		data.add(panelcoordinates);
		
		//Botón
		//Creamos el botón
		JButton btnUpdate = new JButton("Actualizar");
		data.add(btnUpdate);
				
		TitledBorder titlestructure= BorderFactory.createTitledBorder("Estructura");
		panelstructure.setBorder(titlestructure);
		data.add(panelstructure);
		
		/* Añadimos panelcursor al frame data */
		data.add(panelcursor);	
		TitledBorder titleCursor = BorderFactory
				.createTitledBorder("Coordenadas del cursor");
		panelcursor.setBorder(titleCursor);

		
		
		
	
		//Listener del boton
		btnUpdate.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	updateListas();
            	panelcoordinates.updateList();
            	updatePoints();
            }
		});
            
		// Listeners
		// Mapa
		imagen.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				updateCoordinatesFromMap(e.getX(), e.getY());
				updateListas();
            	panelcoordinates.updateList();
            	updatePoints();
            
			}
		});

		imagen.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				updateStatusPanel(e.getX(), e.getY());
			}
		});

		panelposition.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateImage();
				updateDatabaseFromCoordinates();
			}
		});

		this.pack();
		this.setVisible(true);

		// Redibujar mapa y circulo
		updateImage();
	}
	

	
	public void updateStatusPanel(int x, int y) {
		Position position = imagen.positionFromPixelCoordinates(x, y);
		DecimalFormat df = new DecimalFormat("#.###");
		panelcursor.cursorLatitud.setText(df.format(position.latitude));
		panelcursor.cursorLongitud.setText(df.format(position.longitude));
	}

	public void updateDatabaseFromMap(int x, int y) {
		try {
			database.updatePosition(username,
					imagen.positionFromPixelCoordinates(x, y));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void updateDatabaseFromCoordinates() {
		try {
			database.updatePosition(username, panelposition.getPosition());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void updateCoordinatesFromMap(int x, int y) {
		panelposition.setPosition(imagen.positionFromPixelCoordinates(x, y));
	}

	public void updateImage() {
		imagen.reset();
		imagen.mark(panelposition.getPosition(), Color.red);
		imagen.repaint();
	}
	
	//Actualiza los puntos del mapa
	public void updatePoints(){
		
		for(common.User u: MainWindow.cercanos){
			imagen.mark(new Position(u.position.latitude, u.position.longitude), Color.blue);
			
		}
		imagen.repaint();
	}
	
	//Actualiza las listas de usuarios y cercanos
	public void updateListas(){
		try {
			usuarios = database.getUsers(username);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Actualizamos la lista de usuarios
		
        try {
			cercanos =  InterfazCercanosFactory.interfazCercanosFactoryMethod(panelstructure.getStructure(),
							new User(username, panelposition.getPosition()), panelnumberusers.getUsers());
			usuarios.getClosest(cercanos);
		} catch (NotEnoughUsersException e) {		
			JOptionPane.showMessageDialog(this, "No hay suficiente n�mero de usuarios en la base de datos. (Maximo "+usuarios.size()+ ")"
					,"Datos fuera de rango",JOptionPane.ERROR_MESSAGE);
		} catch (NegativeUsersException e) {		
			JOptionPane.showMessageDialog(this, "No se puede buscar un n�mero negativo de usuarios en el mapa.","Datos fuera de rango",
				    JOptionPane.ERROR_MESSAGE);
		}catch (InputOutOfRangeException e) {		
			JOptionPane.showMessageDialog(this, "Numero para buscar usuarios fuera de rango.","Datos fuera de rango",
				    JOptionPane.ERROR_MESSAGE);
		}        
   
	}
}
