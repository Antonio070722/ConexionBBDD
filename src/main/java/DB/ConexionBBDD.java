package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBBDD {

    public static final String USER = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/aucorsa";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }







//    public Connection crearConexionBBDD() throws SQLException {
//        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
//        return con;
//    }
//
//    public void consultarBBDD(String consulta){
//        Connection con = null;
//        try {
//            con = crearConexionBBDD();
//            Statement s = con.createStatement();
//            s.execute(consulta);
//
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean insertarConductor(String consulta){
//        Connection con = null;
//        try {
//            con = crearConexionBBDD();
//            Statement s = con.createStatement();
//            s.execute(consulta);
//            return true;
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }

}
