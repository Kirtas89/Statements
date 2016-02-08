package ejemplos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejadoraWPFSample {
	
	/* void insertPersona(String nombre, String apellidos)
	 * 
	 * Comentario: Método para insertar una persona en la base de datos dado su nombre y su apellido
	 * Precondición: Nada
	 * Entrada: Dos cadenas (nombre y apellido)
	 * Salida: Nada
	 * Postcondición: La persona queda insertada 
	 */
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
	
	/* int insertarPersonaSP(String nombre, String apellidos, String fecha_nacimiento, String Direccion, String Telefono)
	 * 
	 * Comentario: Método para insertar una persona en la base de datos usando CallableStatement
	 * Precondición: Nada
	 * Entrada: Cinco String que representan los datos de la persona
	 * Salida: Un entero
	 * Postcondición: Se devuelve un entero asociado al nombre. Representa el id de la persona insertada.
	 */
	public int insertarPersonaSP(String nombre, String apellidos, Date fecha_nacimiento, String Direccion, String Telefono) {
		int idPersonaInsertada = 0;
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
			String sentencia = "EXECUTE sp_insertarPersona ?,?,?,?,?,?";		
			CallableStatement callableStatement = connexionBaseDatos.prepareCall(sentencia);
			
			//Asigno valores a los parámetros
			callableStatement.setString(1, nombre);
			callableStatement.setString(2, apellidos);
			callableStatement.setDate(3, fecha_nacimiento);
			callableStatement.setString(4, Direccion);
			callableStatement.setString(5, Telefono);
			callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
			
			//Ejecuto la sentencia y recojo el valor devuelto
			callableStatement.executeUpdate();
			idPersonaInsertada = callableStatement.getInt(6);			
		} catch (SQLException e) {
			System.out.println("Fallo de conexión con el servidor" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra la clase del driver SQL");
		}
		
		return idPersonaInsertada;
	}
	
}
