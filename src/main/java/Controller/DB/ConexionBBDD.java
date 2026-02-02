package Controller.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexionBBDD {

    // Cambios realizados: añadido comentario instructivo, se mantiene el método
    // getConexion() que devuelve una Connection usando DriverManager.

    // Ajusta estas constantes según tu base de datos local (usuario/contraseña/URL):
    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/AUCORSA";

    // Método centralizado para obtener una conexión JDBC.
    // Nota para estudiantes: este método lanza SQLException si falla la conexión.
    // Usa: Connection con = ConexionBBDD.getConexion(); y preferiblemente try-with-resources.
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
