package com.codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) 
    {
        List<Escuela> escuelas = new ArrayList<>();
        //Array donde se creara cada escuela y segun lo guardado en la base de datos,
        //para guardarlas en la lista escuelas.
        escuelas.add(new Escuela("Ingenieria"));

        Connection conexion = conectarBaseDatos();
		if (conexion == null) {
			System.out.println("Conexión fallida a la base de datos.");
			return;
		}

		try {
			Statement statement = conexion.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Carreras");
		
			while (rs.next()) {
				String Escuela = rs.getString("Escuela");
				String Carrera = rs.getString("Carrera");
				System.out.println("Escuela: " + Escuela + ", Carrera: " + Carrera);
			}

			rs.close();
			statement.close();
			conexion.close();	
		} catch (SQLException e) {
			System.out.println("Error al leer los datos de la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
        //Crear el metodo para agregar cada carrera a nuestra escuela.
    }

    private static Connection conectarBaseDatos() {
        try {
            Connection conn = DriverManager.getConnection(
            "jdbc:sqlite:db.db"
            );

            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
