package Controller.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexionBBDD {

    /**
     * En los atributos de la clase se define la URL de la base de datos, el usuario y la contraseña.
     */

    // Ajusta estas constantes según tu base de datos local (usuario/contraseña/URL):
    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/AUCORSA";

    /**
     *  GetConnection: Método para obtener una conexión a la base de datos, se usa para no repetir código en
     *  cada clase que necesite acceder a la base de datos.
     * @return
     * @throws SQLException
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
