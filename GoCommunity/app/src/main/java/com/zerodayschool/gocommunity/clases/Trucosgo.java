package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class Trucosgo implements Serializable {

    private String id;
    private  String foto;
    private  String nombre;
    private String descripcion;

    public  Trucosgo(String id, String foto,String nombre, String descripcion){
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
