package dao;


import models.Trabajo;
import psql.ConnectPostgres;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class TrabajosDao {

    public void guardarTrabajo(Trabajo trabajo, int id_materia){

        String sql = "INSERT INTO consulta.trabajos(descripcion,fecha_entrega, materia_id) VALUES(?,?,?)";

        try(Connection con = ConnectPostgres.conexionDb();
            PreparedStatement pstm = con.prepareStatement(sql)){
            pstm.setString(1,trabajo.getDescripcion());
            pstm.setDate(2,Date.valueOf(trabajo.getFechaEntrega()));
            pstm.setInt(3,id_materia);
            pstm.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
    }

    public ArrayList<Trabajo> mostrarTrabajos(int idMateria){

        String sql = "SELECT id_trabajo, descripcion, fecha_entrega FROM consulta.trabajos WHERE materia_id = ? ORDER BY fecha_entrega";
        ArrayList<Trabajo> trabajos = new ArrayList<>();

        try(Connection con = ConnectPostgres.conexionDb();
            PreparedStatement pstm = con.prepareStatement(sql))
        {
            pstm.setInt(1,idMateria);
            try(ResultSet rs = pstm.executeQuery()){


                while(rs.next()){

                    int id = rs.getInt("id_trabajo");
                    String descripcion = rs.getString("descripcion");
                    LocalDate fecha = rs.getDate("fecha_entrega").toLocalDate();
                    Trabajo t = new Trabajo(id,descripcion,fecha);
                    trabajos.add(t);
                }
            }

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

        return trabajos;
    }
    public void  eliminar(int idTrabajo){

        String sql = "DELETE FROM consulta.trabajos WHERE id_trabajo = ?";

        try(Connection con = ConnectPostgres.conexionDb();
            PreparedStatement pstm = con.prepareStatement(sql);

        ){
            pstm.setInt(1, idTrabajo);
            pstm.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error: "+ e.getMessage());

        }
    }
}


