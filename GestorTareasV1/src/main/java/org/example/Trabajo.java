package org.example;

import java.time.LocalDate;

public class Trabajo{

    private String descripcion;
    private LocalDate fechaEntrega;
    private int id;

    public Trabajo(String descripcion, LocalDate fechaEntrega){

        if( descripcion == null ||descripcion.trim().isEmpty() ){

            throw new IllegalArgumentException("Descripcion Esta Vacia");
        }

        if(fechaEntrega == null ){

            throw  new IllegalArgumentException("Fecha De Entrega Vacia");
        }

        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;

    }

    public Trabajo(int id, String descripcion, LocalDate fechaEntrega){
        if( descripcion == null ||descripcion.trim().isEmpty() ){

            throw new IllegalArgumentException("Descripcion Esta Vacia");
        }

        if(fechaEntrega == null ){

            throw  new IllegalArgumentException("Fecha De Entrega Vacia");
        }

        this.id = id;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {return id;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }



}

