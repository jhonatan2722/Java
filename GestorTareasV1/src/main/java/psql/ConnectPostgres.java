package psql;
import java.sql.*;

public class ConnectPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/db_gestor_tareasv1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "jhonatan2025sql";

    public static  Connection conexionDb(){

        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException e){
            System.out.println("Error en conectar a postgres: " + e.getMessage() );
        }
        return conexion;
    }
}
