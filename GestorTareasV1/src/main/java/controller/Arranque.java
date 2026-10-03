package controller;

import models.*;
import dao.MateriasDao;
import dao.TrabajosDao;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Arranque{

    private Scanner entry = new Scanner(System.in);
    private  ArrayList<Materia> materias = new ArrayList<>();
    private ArrayList<Trabajo> trabajos = new ArrayList<>();
    private TrabajosDao td = new TrabajosDao();
    private MateriasDao md = new MateriasDao();

    public void iniciar(){

          this.materias = md.listarMaterias();
          boolean salir = false;
          System.out.println("--Bienvenido al Gestor De Tareas--");

          while(!salir){

              System.out.println("\n1.Agregar Materias\n2.Agregar Trabajos\n3.Consultar" +
                                 "\n4.Eliminar Trabajo\n5.Eliminar Materias\n6.Actualizar Materias\n7.Salir ");
              String opcion = entry.nextLine();

              switch (opcion){

                  case "1" :
                      agregarMateria();
                      break;

                  case "2":
                      agregarTrabajo();
                      break;

                  case "3":
                      consultarTrabajos();
                      break;

                  case "4":
                      eliminarTrabajo();
                      break;

                  case "5":
                      eliminarMateria();
                      break;

                  case "6":
                      actualizarMateria();
                      break;

                  case "7":
                      System.out.println("\nSee You Soon !!\n");
                      entry.close();
                      salir = true;
                      break;

                  default:
                      System.out.println("\nOpcion Invalida\n");
                     break;
              }

          }
      }

    private void agregarMateria(){

        System.out.println("Ingresa Nombre de Materia A registrar:");
        String nombreMateria = entry.nextLine();

        try{

            Materia m = new Materia(nombreMateria);
            md.Guardar(m);
            this.materias = md.listarMaterias();

        }catch(IllegalArgumentException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }

    private void agregarTrabajo(){

           if(validacionM(materias)){return;}
           verOpcionesM();

            int elegirMateria = pedirNumero();
            if(elegirMateria == -1){
                return;
            }

            try{

                System.out.println("Fecha AAAA-MM-DD: ");
                String fechaEntrega = entry.nextLine();
                System.out.println("Descripcion: ");
                String descripcion = entry.nextLine();
                LocalDate fecha = LocalDate.parse(fechaEntrega);
                Materia materiaSeleccionada = materias.get(elegirMateria);
                Trabajo t = new Trabajo(descripcion, fecha);
                td.guardarTrabajo(t,materiaSeleccionada.getId());

            }catch(DateTimeParseException e){

                System.out.println("Error: Escribe la fecha en formato AAAA-MM-DD");

            }catch(IllegalArgumentException e){

                System.out.println("Error: " + e.getMessage());

            } catch (IndexOutOfBoundsException e){

                System.out.println("La Materia elegida no Existe");
            }

    }

    private void consultarTrabajos(){

           if(validacionM(materias)){return;}

            System.out.println("Consulta Trabajos de las Materias");
            verOpcionesM();

            int consultaEleccion = pedirNumero();
            if(consultaEleccion == -1){ return;}

            try{

              this.materias = md.listarMaterias();
              Materia materiaSeleccionada =  materias.get(consultaEleccion);
              this.trabajos = td.mostrarTrabajos(materiaSeleccionada.getId());

              if(validacionM(trabajos)){return;}

              for(Trabajo trabajo: trabajos){

                  System.out.println(trabajo.getDescripcion()+ "\nFecha Entrega: " + trabajo.getFechaEntrega());
              }

            }catch(IndexOutOfBoundsException e){

                System.out.println("Materia no Existente");
            }

        }


        private void eliminarTrabajo(){

            if(validacionM(materias)){return;}
            verOpcionesM();

            int consultaEleccion = pedirNumero();
            if(consultaEleccion == -1){return;}

            try{

                Materia materiaSeleccionada = materias.get(consultaEleccion);
                this.trabajos = td.mostrarTrabajos(materiaSeleccionada.getId());

               if(validacionM(trabajos)){return;}

                for(int i = 0; i<trabajos.size(); i++){
                    System.out.println(i+". "+ trabajos.get(i).getDescripcion());
                }

                int eleccionTrabajo = pedirNumero();
                if(eleccionTrabajo == -1){ return;}

                td.eliminar(trabajos.get(eleccionTrabajo).getId());
                System.out.println("Trabajo Eliminado Con Exito");

            }catch(IllegalArgumentException e){
                 System.out.println("Error: "+ e.getMessage());

            }catch(IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        private void eliminarMateria(){

            if(validacionM(materias)){return;}
            verOpcionesM();

            int consultaEleccion = pedirNumero();
            if(consultaEleccion == -1){ return; }

            try{

                md.eliminarM(materias.get(consultaEleccion).getId());
                System.out.println("Materia Eliminada Con Exito");
                this.materias = md.listarMaterias();

            }catch(IllegalArgumentException e){
                System.out.println("Error: " +e.getMessage());

            }catch(IndexOutOfBoundsException e){
                System.out.println("Error: " + e.getMessage());

            }
        }

        private void actualizarMateria(){

         if(validacionM(materias)){return;}
         verOpcionesM();
         int materiaSeleccionada = pedirNumero();
         if(materiaSeleccionada == -1){return;}

         System.out.println("Ingresa Nombre: ");
         String descripcion = entry.nextLine();

         md.actualizaM(descripcion, materias.get(materiaSeleccionada).getId());
         this.materias = md.listarMaterias();

        }

        private int pedirNumero(){

           try{
               int numero = entry.nextInt();
               entry.nextLine();
               return numero;

           }catch(InputMismatchException e){

               System.out.println("ERROR: INGRESA UN NUMERO");
               entry.nextLine();
               return -1;

           }
        }

        private boolean validacionM( ArrayList lista){

            if(lista.isEmpty()){
                System.out.println("Lista Vacia");
                return true ;
            }
            return false;
        }

        private  void verOpcionesM(){

            System.out.println("--Materias--");
            for(int i = 0; i< materias.size() ; i++){
                System.out.println(i +". "+ materias.get(i).getNombre());
            }
            System.out.println("Elige Materia: ");
        }

    }









