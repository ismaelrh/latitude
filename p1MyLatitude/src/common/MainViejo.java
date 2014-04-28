/**
 * @author Pablo Lanaspa e Ismael Rodr�guez
 * 
 * Clase Main que ejecuta el programa de forma textual, paso previo para la aplicaci�n
 * gr�fica
 */
package common;

import java.sql.SQLException;
import java.util.Scanner;

public class MainViejo {
	/**
	 * @param args
	 */

	public static void main(String[] args) {

		System.out.print("Propietario  base de datos: ");
		Scanner sc = new Scanner(System.in);
		String owner =  "";
		System.out.print("Contrasena: ");
		String password = "";

		db.DatabaseConnection datab = new db.DatabaseConnection(
				"jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious",
				owner, password);
		System.out.print("Usuario: ");
		String user = sc.nextLine();
		System.out.println();

		// userpos guarda la posición del usuario actual
		common.Position userpos = new common.Position(0.0, 0.0);

		// Si no existe el usuario, lo crea
		try {
			if (!datab.loginUser(user)) {
				datab.addUser(user, userpos);
			} else {
				// Si existe, obtiene su posición de la base de datos
				userpos = datab.getPosition(user);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		User currentUser = new User (user, userpos); //Usuario de referencia para operaciones
		
		String dummy, dummy2;
		VectorElementos<common.User> vdummy;
		common.Position dummypos = new common.Position(0.0, 0.0);

		// Pedimos a la base de datos la posición del usuario actual

		boolean quit = false;
		int menuItem;
		do {
			// Text menu
			System.out.println("1. Listar usuarios");
			System.out.println("2. Anadir usuario");
			System.out.println("3. Mostrar posicion");
			System.out.println("4. Actualizar posicion");
			System.out.println("5. Mostrar más cercanos");
			System.out.println("0. Salir");
			System.out.print("Elige una opcion: ");
			menuItem = sc.nextInt();
			dummy = sc.nextLine();
			switch (menuItem) {
			case 1:
				System.out.println("Listado de usuarios");
				try {
					// vdummy guardará lista de los usuarios
					vdummy = datab.getUsers(user);

					// Imprime
					for (common.User u : vdummy) {
						System.out.println("Usuario: " + u.name.trim()
								+ "; posicion: " + u.position.longitude
								+ " E; " + u.position.latitude + " N" +"; distancia: "
								+u.distance(currentUser));

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				break;
			case 2:
				System.out.print("Usuario a anadir: ");
				dummy = sc.nextLine();
				System.out.print("Nueva longitud: ");
				dummy2 = sc.nextLine();
				try {
					dummypos.longitude = Double.valueOf(dummy2).doubleValue();
				} catch (NumberFormatException nfe) {
					System.out.println("Formato incorrecto");
					continue;
				}
				System.out.print("Nueva latitud: ");
				dummy2 = sc.nextLine();
				try {
					dummypos.latitude = Double.valueOf(dummy2).doubleValue();
				} catch (NumberFormatException nfe) {
					System.out.println("Formato incorrecto");
					continue;
				}

				try {
					datab.addUser(dummy, dummypos);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 3:
				try {
					dummypos = datab.getPosition(user);
					System.out.println("Longitud: " + dummypos.longitude
							+ " E; Latitud: " + dummypos.latitude + " N");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 4:
				System.out.print("Nueva longitud: ");
				dummy = sc.nextLine();
				try {
					dummypos.longitude = Double.valueOf(dummy).doubleValue();
				} catch (NumberFormatException nfe) {
					System.out.println("Formato incorrecto");
					continue;
				}
				System.out.print("Nueva latitud: ");
				dummy = sc.nextLine();
				try {
					dummypos.latitude = Double.valueOf(dummy).doubleValue();
				} catch (NumberFormatException nfe) {
					System.out.println("Formato incorrecto");
					continue;
				}
				try {
					datab.updatePosition(user, dummypos);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 5:
				int n = 3;
				try {
					VectorElementos<User> usuarios = datab.getUsers(user);
					
					System.out.println("Tipo de Estructura de Datos a utilizar:");
					System.out.println("\t - Vector    --> Pulse 0");
					System.out.println("\t - Monticulo --> Pulse 1");
					
					InterfazCercanos<User> cercanos = 
						InterfazCercanosFactory.interfazCercanosFactoryMethod(sc.nextInt(),
								new User(user, userpos), n);
					
					usuarios.getClosest(cercanos);
					for (common.User u : cercanos) {
						System.out.println("Usuario: " + u.name.trim()
								+ "; posicion: " + u.position.longitude
								+ " E; " + u.position.latitude + " N" + "; distancia: "
								+u.distance(currentUser));

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Hasta luego");
				quit = true;
				break;
			default:
				System.out.println("Opcion invalida");
			}
		} while (!quit);

	}

}
