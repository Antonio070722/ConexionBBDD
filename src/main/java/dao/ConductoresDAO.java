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

        String sql = "SELECT numeroConductor, nombre, apellidos FROM CONDUCTORES WHERE numeroConductor = ?";

        try (Connection con = ConexionBBDD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numDriver);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int numero = rs.getInt("numeroConductor");
                    String nombre = rs.getString("nombre");
                    String apellidos = rs.getString("apellidos");

                    // Devolvemos un Conductor construido a partir de los datos de la BD
                    return new Conductor(numero ,nombre, apellidos);
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

    public static Conductor insertarConductor(Conductor conductor){
        //En la variable sql se guarda la consulta para insertar un nuevo conductor en la base de datos
        String sql = "INSERT INTO CONDUCTORES (numeroConductor, nombre, apellidos) VALUES (?, ?, ?)";

        try(Connection con = ConexionBBDD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, conductor.getNumeroConductor());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellidos());

            int filasInsertadas = ps.executeUpdate();

            Conductor nuevoConductor = new Conductor(conductor.getNumeroConductor() ,conductor.getNombre(), conductor.getApellidos());

            if (filasInsertadas == 0){
                return null;
            }
            else{
                return nuevoConductor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
