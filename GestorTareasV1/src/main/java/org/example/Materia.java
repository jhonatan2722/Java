package org.example;


public class Materia {

    private int id;
    private String nombre;

    public Materia(String nombre){

        if(nombre == null || nombre.trim().isEmpty()){

            throw new IllegalArgumentException("El Campo _Nombre Materia_ Esta Vacio.");
        }

     this.nombre = nombre;

    }
    public Materia(int id, String nombre){

        if(nombre == null || nombre.trim().isEmpty()){

            throw new IllegalArgumentException("El Campo _Nombre Materia_ Esta Vacio.");
        }

        this.nombre = nombre;
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }
    public int getId() { return id;}







}
