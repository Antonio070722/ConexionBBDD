package Controller.dao;

import Controller.dao.DB.ConexionBBDD;
import model.Bus;

import java.sql.Connection;
import java.sql.*;

public class BusesDAO {

    public static Bus consultarBus(String registro){
        String sql = "SELECT Registro, Licencia, Tipo FROM BUSES WHERE Registro = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, registro);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String Registro = rs.getString("Registro");
                    String Licencia = rs.getString("Licencia");
                    String Tipo = rs.getString("Tipo");
                    return new Bus(Registro, Licencia, Tipo);
                }else {
                    return null; // No se encontró el bus
                }
            }catch (SQLException ex) {
                throw new RuntimeException("Error al extraer los datos de la Base de Datos"+ ex);
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al consultar bus"+ e);
        }
    }
}
