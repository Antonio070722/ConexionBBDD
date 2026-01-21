package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://localhost:3306/aucorsa";

    ConexionBBDD() throws SQLException {

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
