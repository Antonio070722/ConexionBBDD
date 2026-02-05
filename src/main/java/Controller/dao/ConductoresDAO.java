package Controller.dao;

import Controller.DB.ConexionBBDD;
import model.Conductor;

import java.sql.*;

public class ConductoresDAO {

    /**
     * Método para borrar un conductor de la base de datos dado su número de conductor. Si la eliminación es exitosa, devuelve true;
     * si no se encuentra el conductor o ocurre un error, devuelve false.
     * @param idConductorBorrar Número de conductor que se desea eliminar de la base de datos.
     * @return
     */
    public static boolean borrarConductor(int idConductorBorrar) {
        String sql = "DELETE FROM CONDUCTORES WHERE numeroConductor = ?";

        try(Connection con = ConexionBBDD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idConductorBorrar);

            int filasElim = ps.executeUpdate();

            if (filasElim > 0) return true;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Método para consultar un conductor en la base de datos dado su número de conductor. Si se encuentra el conductor,
     * devuelve un objeto Conductor con sus datos; si no se encuentra o ocurre un error, devuelve null.
     * @param numDriver Número de conductor que se desea consultar en la base de datos.
     * @return
     */
    public static Conductor consultarConductor(int numDriver){

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

    /**
     * Método para insertar un nuevo conductor en la base de datos. Recibe un objeto Conductor con los datos a insertar.
     * Si la inserción es exitosa, devuelve un nuevo objeto Conductor con los datos insertados; si ocurre un error, devuelve null.
     * @param conductor Objeto Conductor que contiene los datos del nuevo conductor a insertar en la base de datos.
     * @return
     */
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
