package common;

import java.util.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {

		System.out.print("Propietario  base de datos: ");
		Scanner sc=new Scanner(System.in);
		String owner=sc.nextLine();
		System.out.print("Contrasena: ");
		String password=sc.nextLine();
		
		db.DatabaseConnection datab = new db.DatabaseConnection("jdbc:oracle:thin:@hendrix-oracle.cps.unizar.es:1521:vicious",
		   	    										owner, password);
		System.out.print("Usuario: ");
		String user=sc.nextLine();
		System.out.println();
		//Si no existe el usuario, lo crea
		try { if (!datab.loginUser(user))
			   datab.addUser(user, new common.Position(0.0,0.0));
	    	} catch (SQLException e1) {
	    	 e1.printStackTrace();
	    	}

		String dummy, dummy2;
		Vector<common.User> vdummy;
		common.Position dummypos = new common.Position(0.0,0.0);
		
      	boolean quit = false;
        int menuItem;
        do {
    	    // Text menu
    	    System.out.println("1. Listar usuarios");
    	    System.out.println("2. Anadir usuario");
    	    System.out.println("3. Mostrar posicion");
    	    System.out.println("4. Actualizar posicion");
    	    System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            menuItem = sc.nextInt(); dummy=sc.nextLine();
            switch (menuItem) {
              case 1:
                    System.out.println("Listado de usuarios");
            		try {
            			//vdummy guardará lista de los usuarios
            		  vdummy = datab.getUsers(user);
            		  //Imprime
            		  for (int i=0; i< vdummy.size(); i++){
            			  System.out.println("Usuario: "+vdummy.get(i).name.trim()+"; posicion: "
            					  +vdummy.get(i).position.longitude+" E; "+vdummy.get(i).position.latitude+" N");
            		  }
            		} catch (SQLException e1) {
            		  e1.printStackTrace();
    		        }
                    break;
              case 2:
                    System.out.print("Usuario a anadir: ");
                    dummy=sc.nextLine();
            	    System.out.print("Nueva longitud: ");
            	    dummy2=sc.nextLine();
            	    try {
            	      dummypos.longitude = Double.valueOf(dummy2).doubleValue();
            	    } catch (NumberFormatException nfe) {
            	      System.out.println("Formato incorrecto");
            	      continue;
            	    }
            	    System.out.print("Nueva latitud: ");
            	    dummy2=sc.nextLine();
            	    try {
            	      dummypos.latitude = Double.valueOf(dummy2).doubleValue();
            	    } catch (NumberFormatException nfe) {
            	      System.out.println("Formato incorrecto");
            	      continue;
            	    }

           		    try {
           		      datab.addUser(dummy,dummypos);
           		    } catch (SQLException e1) {
           		     e1.printStackTrace();
   		            }
                    break;
              case 3:
         		    try {
           		      dummypos = datab.getPosition(user);
          		      System.out.println("Longitud: "+dummypos.longitude+" E; Latitud: "+dummypos.latitude+" N");
         		    } catch (SQLException e1) {
         		     e1.printStackTrace();
 		            }
                  break;
              case 4:
            	      System.out.print("Nueva longitud: ");
            	      dummy=sc.nextLine();
            	      try {
            	    	  dummypos.longitude = Double.valueOf(dummy).doubleValue();
            	      } catch (NumberFormatException nfe) {
            	        System.out.println("Formato incorrecto");
            	        continue;
            	      }
            	      System.out.print("Nueva latitud: ");
            	      dummy=sc.nextLine();
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
