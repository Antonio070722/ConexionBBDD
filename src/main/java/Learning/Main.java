package Learning;

import java.sql.*;

public class Main {
    static void main(String[] args){
        String URL = "jdbc:mysql://localhost:3306/aucorsa";
        String USER = "root";
        String PASSWORD = "1234";
        try {

            //Hacer la conexion a la base de datos
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            //Para preparar la orden o instruccion para la base de datos
            Statement st = con.createStatement();
            //Ejecutar la consulta
            ResultSet rs = st.executeQuery("SELECT * FROM CONDUCTORES");

            //Mostramos los datos en la columna 1, 2 y 3
            while (rs.next()) {
                System.out.println("Numero conductor: "+rs.getString(1)+ "\n Nombre: "+rs.getString(2)+"\n Apellidos: "+rs.getString(3)+"\n");
            }

            rs.close();
            st.close();
            con.close();

        }catch(SQLException e){

            e.printStackTrace();

        }
    }
}
