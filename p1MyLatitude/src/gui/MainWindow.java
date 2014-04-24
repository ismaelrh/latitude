package gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import common.Position;

import java.sql.SQLException;
import java.text.*;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final String mapname = "data/Iberian_Peninsula.jpg";
	// Los limites geograficos del mapa son: N: 44.4º N; S: 34.7º N; W: 9.9º; E: 4.8ºE
	public static final double minlongitude = -4.19;
	public static final double maxlongitude = 9.18; 
	public static final double minlatitude = 34.7; 
	public static final double maxlatitude = 44.4;
          
    static PanelPosition panelposition;
    static Map	 imagen;
    PanelCursor	     panelcursor;
   static db.DatabaseConnection database;
    DBCoordinates dbCoordinates = new DBCoordinates(this);
    static String username;
    
    public MainWindow(db.DatabaseConnection db)
    {
    	database = db;
        imagen = new Map(mapname,minlatitude,minlongitude,maxlatitude,maxlongitude);
        panelposition = new PanelPosition();
		panelcursor = new PanelCursor();
    }
    
    public void start() throws SQLException
    {
    	String s = (String)JOptionPane.showInputDialog(
                this,
                "Nombre de usuario:",
                "Login",
                JOptionPane.PLAIN_MESSAGE);
    	
	    this.setTitle("Mapa de Localizaciones");

		// Si se obtuvo una cadena...
		if ((s != null) && (s.length() > 0)) {
			this.setTitle("MyLatitude - "+s);
			if (!database.loginUser(s))
				database.addUser(s, new common.Position(41.39,0.53));
			username=s;
		    distribute();
		}
		else
			this.dispose();
     }

	private void distribute() throws SQLException 
	{
		panelposition.setPosition(database.getPosition(username));

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Creamos un panel para la derecha, en el centro esta el mapa
	    BorderLayout layout = new BorderLayout();
	    
	    //JPanel para el marco de la derecha
	    JPanel right = new JPanel(); 
	    //JPanel para los datos, luego habrá para lista, etc.
	    JPanel data = new JPanel();
	    right.add(data,BorderLayout.CENTER);
	    
	    //En el panel de la derecha, donde estan los datos, vamos a ponerlos uno debajo de
	    //otro (BoxLayout)
	    //Layout vertical
	    BoxLayout dataLayout = new BoxLayout(data,BoxLayout.Y_AXIS);
	    data.setLayout(dataLayout);
	    
	    JPanel viewportPanel = new JPanel();
	    viewportPanel.add(imagen,BorderLayout.CENTER);
	    
	    //Un layout BorderLayout tiene NORTH,SOUTH, EAST, WEST Y CENTER
	    this.getContentPane().setLayout(layout);
	    //Añade al centro la imagen con scroll, en el CENTRO
	    this.getContentPane().add(new JScrollPane(viewportPanel),BorderLayout.CENTER);
	    //Añade en el este el panel de la derecha, que incluye el data
	    this.getContentPane().add(right,BorderLayout.EAST);
	    
	    /*Añadimos panelPosition al frame data*/
	    data.add(panelposition);
	    TitledBorder titleCoordenadas = BorderFactory.createTitledBorder("Coordenadas locales");
	    panelposition.setBorder(titleCoordenadas);
	    
	    /*Añadimos panelcursor al frame data*/
	    data.add(panelcursor);
	    TitledBorder titleCursor = BorderFactory.createTitledBorder("Coordenadas del cursor");
	    panelcursor.setBorder(titleCursor);

	   
		
		data.add(dbCoordinates);
		
		// Listeners
		// Mapa
	    imagen.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e)
	    	{	
	    		updateCoordinatesFromMap(e.getX(),e.getY());
	    	}
	    });
	   
	    imagen.addMouseMotionListener(new MouseMotionAdapter() {
	    	public void mouseMoved(MouseEvent e)
	    	{	
	    		updateStatusPanel(e.getX(),e.getY());
	    	}
	    });
	    
	    panelposition.addChangeListener(new ChangeListener() {
	    	public void stateChanged(ChangeEvent e) 
	    	{  
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
    	Position position = imagen.positionFromPixelCoordinates(x,y);
		DecimalFormat df = new DecimalFormat("#.###");
		panelcursor.cursorLatitud.setText(df.format(position.latitude));
		panelcursor.cursorLongitud.setText(df.format(position.longitude));
     }
    
    public void updateDatabaseFromMap(int x, int y)
    {
    	try {
			database.updatePosition(username,imagen.positionFromPixelCoordinates(x,y));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
     
    public void updateDatabaseFromCoordinates()
    {
    	try {
    		database.updatePosition(username,panelposition.getPosition());
    	} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }

    public void updateCoordinatesFromMap(int x, int y)
    {
    	panelposition.setPosition(imagen.positionFromPixelCoordinates(x,y));
    }
     
    public void updateImage()
     {
			imagen.reset();
 			imagen.mark(panelposition.getPosition(), Color.red);
 			imagen.repaint();
     }
}
