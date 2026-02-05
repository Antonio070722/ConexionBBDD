package Controller.dao;

import Controller.DB.ConexionBBDD;
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

    public static Bus insertarBus(Bus businsert){
        String sql = "INSERT INTO BUSES (Registro, Tipo, Licencia) VALUES (?,?,?)";
        try(Connection con  = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, businsert.getRegistro());
            ps.setString(2, businsert.getTipo());
            ps.setString(3, businsert.getLicencia());

            int filasInsertadas = ps.executeUpdate();
            Bus businsertado = new Bus(businsert.getRegistro(), businsert.getTipo(), businsert.getLicencia());

            if(filasInsertadas == 0){
                return null;
            }else {
                return businsertado;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar el bus: " + e.getMessage());
            return null;
        }
    }

    public static boolean borrarBus(String registro){
        String sql = "DELETE FROM BUSES WHERE Registro = ?";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, registro);

            int filasBorradas = ps.executeUpdate();

            if(filasBorradas == 0){
                return false;
            }else  {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al borrar el bus: " + e.getMessage());
            return false;
        }
    }

}
