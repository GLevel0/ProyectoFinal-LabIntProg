package com.codigo;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class App {
    public static ArrayList<Escuela> Escuelas = new ArrayList<Escuela>();
    public static void main(String[] args) 
    {
        Connection conexion = conectarBaseDatos();
        //Comprueba que la conexion fue exitosa antes de continuar, sino, no ejecuta el programa.
		if (conexion == null) {
			System.out.println("Conexión fallida a la base de datos.");
			return;
		}

		try {
            //Se crea el formulario y se le pasa la conexion para que pueda usarla en sus metodos.
            FormularioCarreras formulario = new FormularioCarreras();
            formulario.setVisible(true);
            
            //Cierra la conexion al cerrar la aplicacion
			conexion.close();	
		} catch (SQLException e) {
			System.out.println("Error al leer los datos de la base de datos: " + e.getMessage());
			e.printStackTrace();
		}

        //Crear el metodo para agregar cada carrera a nuestra escuela.
    }

    public static void cargarEscuelas(JComboBox<String> comboEscuelas) {

        try {
            Connection conexion = conectarBaseDatos();
            String sql = "SELECT * FROM Escuelas";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String Nombre = rs.getString("Nombre");
                
                Escuela escuela = new Escuela(id, Nombre);
                Escuelas.add(escuela);
                comboEscuelas.addItem(Nombre);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error al cargar escuelas");
            e.printStackTrace();
        }
    }

    public static void consultarCarreras(JTextArea areaResultados,JComboBox<String> comboEscuelas) {
        areaResultados.setText("");

        try {
            String escuela = comboEscuelas.getSelectedItem().toString();

            Connection conexion = conectarBaseDatos();
            String sql = "SELECT * FROM Carreras";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            areaResultados.append("Carreras de " + escuela + ":\n\n");

            while (rs.next()) {
                if(rs.getInt("Escuela") == Escuelas.get(comboEscuelas.getSelectedIndex()).getId())
                {
                    areaResultados.append("- "+ rs.getString("Nombre") + "\n");
                }
            }

            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error en la consulta");
        }
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
