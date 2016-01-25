package ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejadoraWPFSample {
	
	public void insertPersona(String nombre, String apellidos) {
		final String USE = "Use [WPFSample]";
		Connection connexionBaseDatos = null;
		
		try {
			//Cargo la clase del driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//Asigno los valores de la conexión y establezco la conexión
			String sourceURL = "jdbc:sqlserver://localhost";
		    String usuario = "prueba";
		    String password = "123";
		    connexionBaseDatos = DriverManager.getConnection(sourceURL, usuario, password);
		    
		    //Creo la sentencia USE para establecer la base de datos
		    Statement sentenciaUSE = connexionBaseDatos.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		    
		    sentenciaUSE.execute(USE);
		    
		    //Creo la sentencia de inserción
		    String select = "INSERT INTO Personas (Nombre, Apellidos) VALUES (?,?)";	
			PreparedStatement sentenciaSQL = connexionBaseDatos.prepareStatement(select);
			
			//Asigno parámetros y ejecuto
			sentenciaSQL.setString(1,nombre);
			sentenciaSQL.setString(2,apellidos);
			sentenciaSQL.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connexionBaseDatos.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
