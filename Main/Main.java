import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    Connection conectar = null;

    public Connection establecerConexion() {

        try {

            String usuario = "root";
            String clave = "";
            String bd = "uasd";
            String ip = "localhost";
            String puerto = "3306";

            String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

            conectar = DriverManager.getConnection(cadena, usuario, clave);

            System.out.println("Conexión exitosa");

        } catch (Exception e) {

            System.out.println("Error de conexión: " + e.toString());
        }

        return conectar;
    }

    
}
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class FormularioCarreras extends JFrame {

    JComboBox<String> comboEscuelas;
    JButton botonConsultar;
    JTextArea areaResultados;

    Conexion conexion = new Conexion();

    public FormularioCarreras() {

        setTitle("Consulta de Carreras UASD");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Seleccione una escuela:");
        label.setBounds(30, 30, 200, 30);
        add(label);

        comboEscuelas = new JComboBox<>();
        comboEscuelas.setBounds(30, 70, 200, 30);
        add(comboEscuelas);

        botonConsultar = new JButton("Consultar Carreras");
        botonConsultar.setBounds(250, 70, 180, 30);
        add(botonConsultar);

        areaResultados = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBounds(30, 130, 400, 180);
        add(scroll);

        cargarEscuelas();

        botonConsultar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                consultarCarreras();
            }
        });
    }

    public void cargarEscuelas() {

        try {

            Connection con = conexion.establecerConexion();

            String sql = "SELECT nombre FROM escuelas";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                comboEscuelas.addItem(rs.getString("nombre"));
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Error al cargar escuelas");
        }
    }

    public void consultarCarreras() {

        areaResultados.setText("");

        try {

            Connection con = conexion.establecerConexion();

            String escuela = comboEscuelas.getSelectedItem().toString();

            String sql = """
                    SELECT carreras.nombre
                    FROM carreras
                    INNER JOIN escuelas
                    ON carreras.id_escuela = escuelas.id
                    WHERE escuelas.nombre = ?
                    """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, escuela);

            ResultSet rs = ps.executeQuery();

            areaResultados.append("Carreras de " + escuela + ":\n\n");

            while (rs.next()) {

                areaResultados.append("- "
                        + rs.getString("nombre") + "\n");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Error en la consulta");
        }
    }

    public static void main(String[] args) {

        FormularioCarreras formulario =
                new FormularioCarreras();

        formulario.setVisible(true);
    }
}
