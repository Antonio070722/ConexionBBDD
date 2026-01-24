package dao;

import DB.ConexionBBDD;
import model.Bus;

import java.sql.Connection;
import java.sql.*;

public class BusesDAO {
    public Bus consultarBus(int registro){
        String sql = "SELECT Registro, Licencia, Tipo FROM BUSES WHERE Registro = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, registro);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String Registro = rs.getString("Registro");
                    String Licencia = rs.getString("Licencia");
                    String Tipo = rs.getString("Tipo");
                    return new Bus(Integer.parseInt(Registro), Licencia, Tipo);
                }else {
                    return null; // No se encontró el bus
                }
            }catch (SQLException ex) {
                throw new RuntimeException("Error al extraer los datos de la Base de Datos", ex);
            }

        }catch (SQLException e){
            throw new RuntimeException("Error al consultar bus", e);
        }
    }
}
