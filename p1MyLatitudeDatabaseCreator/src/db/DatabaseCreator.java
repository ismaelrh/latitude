package db;
import java.sql.*;
import java.util.Scanner;

public class DatabaseCreator {

	
	public void createDatabase(String driver, String username, String password) throws ClassNotFoundException
	{
	    Connection con;
		try {
			Class.forName ("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
			        driver, username,
			        password);

	        Statement stmt = con.createStatement();
	        stmt.executeUpdate(
	            "CREATE TABLE users "+
	            "( Nombre char(100) UNIQUE NOT NULL,"+
	            " Latitud float NOT NULL,"+
	            " Longitud float NOT NULL)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Alfredo',41.0,1.0)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Ana',42.0,-4.0)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Paula',38.0,-1.0)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Sergio',40.0,4.0)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Sandra',37.0,1.0)");
	        stmt.executeUpdate("INSERT INTO users VALUES ('Antonio',40.0,-1.0)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException 
	{
		System.out.print("Propietario base de datos: ");
		Scanner sc=new Scanner(System.in);
		String owner=sc.nextLine();
		System.out.print("Contrasena: ");
		String password=sc.nextLine();
		DatabaseCreator creator = new DatabaseCreator();
		creator.createDatabase("jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious",
				owner, password);
	  }
}
