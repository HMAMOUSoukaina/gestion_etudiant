package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class connexion {
	public static Connection getConnection() {
	        Connection cnn;
	        try {
	            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inscription", "root", "programmeR&#");
	            return cnn;
	        } catch (Exception ex) {
	            System.out.println("Error: " + ex.getMessage());
	            return null;
	        }
	        //la base de donnée nommée inscription et la table s'appelle etudiant
	    }

}
