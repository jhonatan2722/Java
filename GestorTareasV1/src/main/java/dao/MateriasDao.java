package dao;

import models.Materia;
import psql.ConnectPostgres;
import java.sql.*;
import java.util.ArrayList;

  public class MateriasDao {

      public void Guardar(Materia materia){

          String sql = "INSERT INTO consulta.materias(nombre) VALUES (?)";
          try(Connection con = ConnectPostgres.conexionDb();
              PreparedStatement pstm = con.prepareStatement(sql)
           ){
              pstm.setString(1,materia.getNombre());
              pstm.executeUpdate();
              System.out.println("Se guardo exitosamente la materia ");

          }catch (SQLException e){
              System.out.println("Error: " + e.getMessage());
          }
      }

      public ArrayList<Materia> listarMaterias(){

          ArrayList<Materia> listaMaterias = new ArrayList<>();
          String sql = "SELECT id, nombre FROM consulta.materias";

          try(Connection con = ConnectPostgres.conexionDb();
              PreparedStatement pstm = con.prepareStatement(sql);
              ResultSet rs = pstm.executeQuery();
          ){
              while(rs.next()){

                  Materia m = new Materia(rs.getInt("id"), rs.getString("nombre") );
                  listaMaterias.add(m);
              }
          }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
          }
          return listaMaterias;
      }

      public void eliminarM(int idMateria){

          String sql = "DELETE FROM consulta.materias WHERE id =  ? ";

          try(Connection con = ConnectPostgres.conexionDb();
              PreparedStatement pstm = con.prepareStatement(sql)

          ){
              pstm.setInt(1,idMateria);
              pstm.executeUpdate();

          }catch(SQLException e){
              System.out.println("Error: "+  e.getMessage());
          }
      }

      public void actualizaM(String nombreMateria , int idMateria){

          String sql = "UPDATE  consulta.materias SET nombre = ? WHERE id = ?";

          try(Connection con = ConnectPostgres.conexionDb();
             PreparedStatement pstm = con.prepareStatement(sql)){

                 pstm.setString(1,nombreMateria);
                 pstm.setInt(2,idMateria);
                 pstm.executeUpdate();

          }catch(SQLException e){

              System.out.println("Error: " + e.getMessage());

          }

      }

   }
