package dao;

import DB.ConexionBBDD;
import model.Conductor;

import java.sql.*;
import java.util.ArrayList;

public class driverDAO {
    public Conductor consultarConductor(int numDriver){

        String sql = "select nombre, apellidos from Driver where numeroConductor = ?";
        Conductor conductorConsultado = new Conductor();
        try{
            Connection con = ConexionBBDD.getConexion();
            PreparedStatement s = con.prepareStatement(sql);

            s.setInt(1, numDriver);

            ResultSet rs = s.executeQuery("select * from CONDUCTOR");

            while(rs.next()){
                System.out.println((rs.getInt(1)));
                System.out.println(rs.getString("nombre"));
            }

            rs.close();
            s.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
