package common;

import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
       try {
    	     String usuario =  "A587429"; //(String)JOptionPane.showInputDialog(null,
    	    			//"Nombre de usuario base de datos:","DB Login",JOptionPane.PLAIN_MESSAGE);
    	     JPasswordField jpf =  new JPasswordField();
    	     JLabel titulo = new JLabel ("Contrasena base de datos:");
    	    // JOptionPane.showConfirmDialog (null, new Object[]{titulo, jpf}, "DB contrasena", JOptionPane.PLAIN_MESSAGE);
    	    // char p[] = jpf.getPassword();
    	     String pass = "fresaEL123";

    	     gui.MainWindow window = new gui.MainWindow(new db.DatabaseConnection("jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious",
       	    	usuario, pass));
	         window.start();
       }
      catch (SQLException e) { e.printStackTrace(); }
       
    }
        
}
