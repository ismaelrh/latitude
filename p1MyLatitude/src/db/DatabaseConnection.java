package db;

import java.sql.*;
import java.util.Vector;

public class DatabaseConnection {

	private String db_driver;
	private String db_username;
	private String db_password;

	public DatabaseConnection(String driver, String username, String password) {
		db_driver = driver;
		db_username = username;
		db_password = password;

		if (driver.contains("oracle")) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.err.println("Oracle driver not found");
				e.printStackTrace();
			}
		}
	}

	public void addUser(String user, common.Position position)
			throws SQLException {
		Connection connection = DriverManager.getConnection(db_driver,
				db_username, db_password);

		// Get a statement from the connection
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO users VALUES " + "('" + user + "',"
				+ position.latitude + "," + position.longitude + ")");

		// Close the statement and the connection
		stmt.close();
		connection.close();
	}

	public common.Position getPosition(String user) throws SQLException {
		Connection connection = DriverManager.getConnection(db_driver,
				db_username, db_password);
		common.Position position = new common.Position(0, 0);
		// Get a statement from the connection
		Statement stmt = connection.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE Nombre='"
				+ user + "'");

		rs.next();
		position.latitude = rs.getDouble("latitud");
		position.longitude = rs.getDouble("longitud");

		return position;
	}

	public boolean loginUser(String user) throws SQLException {
		Connection connection = DriverManager.getConnection(db_driver,
				db_username, db_password);

		// Get a statement from the connection
		Statement stmt = connection.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE Nombre='"
				+ user + "'");

		boolean sol = rs.next();

		// Close the result set, statement and the connection
		rs.close();
		stmt.close();
		connection.close();

		return sol;
	}

	public void updatePosition(String user, common.Position position)
			throws SQLException {
		Connection connection = DriverManager.getConnection(db_driver,
				db_username, db_password);

		// Get a statement from the connection
		Statement stmt = connection.createStatement();
		// Execute the update
		stmt.executeUpdate("UPDATE users SET Latitud = " + position.latitude
				+ ", Longitud = " + position.longitude + " WHERE Nombre = '"
				+ user + "'");
		// Close the statement and the connection
		stmt.close();
		connection.close();
	}

	public Vector<common.User> getUsers(String currentUser) throws SQLException {
		Connection connection = DriverManager.getConnection(db_driver,
				db_username, db_password);

		// Get a statement from the connection
		Statement stmt = connection.createStatement();
		// Execute the query
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");

		Vector<common.User> result = new Vector<common.User>();

		while (rs.next()) { // Automáticamente se salta el título

			String nombre = rs.getString("Nombre");
			if (!nombre.trim().equalsIgnoreCase(currentUser)) {
				double lat = rs.getDouble("Latitud");
				double longitud = rs.getDouble("Longitud");

				result.add(new common.User(nombre, new common.Position(lat,
						longitud)));
			}

		}
		//
		// POR HACER
		//

		// Close the statement and the connection
		stmt.close();
		connection.close();

		return result;
	}

}
