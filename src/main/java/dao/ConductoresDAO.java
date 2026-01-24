package dao;

import DB.ConexionBBDD;
import model.Conductor;

import java.sql.*;

public class ConductoresDAO {
    public Conductor consultarConductor(int numDriver){

        // Cambio realizado: Se implementa correctamente la consulta parametrizada
        // usando PreparedStatement y try-with-resources. Antes el método intentaba
        // usar dos veces la conexión y ejecutaba un executeQuery con SQL literal
        // sin parámetros; además no devolvía ningún valor. Ahora devuelve un
        // objeto Conductor si lo encuentra o null si no existe.

        String sql = "SELECT nombre, apellidos, numeroConductor FROM CONDUCTORES WHERE numeroConductor = ?";

        try (Connection con = ConexionBBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numDriver);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");
                    int numero = rs.getInt("numeroConductor");
                    // Devolvemos un Conductor construido a partir de los datos de la BD
                    return new Conductor(nombre, apellidos, numero);
                } else {
                    // No se encontró el conductor
                    return null;
                }
            }

        } catch (SQLException e) {
            // Cambio realizado: ahora capturamos la excepción y la envolvemos en RuntimeException
            // para que la capa de presentación pueda mostrar un mensaje amigable.
            throw new RuntimeException("Error al consultar conductor", e);
        }
    }
}
