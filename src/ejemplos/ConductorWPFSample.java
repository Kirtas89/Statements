package ejemplos;

import java.sql.Date;

public class ConductorWPFSample {
	public static void main(String[] args) {
		ManejadoraWPFSample maneja = new ManejadoraWPFSample();
		int idPersonaPrueba;
		
		//Datos de la persona a insertar
		String nombre, apellidos, direccion, telefono;
		Date fecha_nacimiento;
		
		nombre = "Jesus";
		apellidos = "García";
		fecha_nacimiento = new Date(System.currentTimeMillis());
		direccion = "Calle Trolazo 4";
		telefono = "777555333";
		
		//Llamo a la función para insertar y muestro el resultado por pantalla
		idPersonaPrueba = maneja.insertarPersonaSP(nombre, apellidos, fecha_nacimiento, direccion, telefono);
		
		System.out.println("La id de la persona insertada es: " + idPersonaPrueba);
	}
	
}
