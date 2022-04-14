package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class Defendergym implements Serializable {

    String id;
    String nombre;
    String foto;
    String top;

    public Defendergym(String id, String nombre, String foto, String top){
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.top = top;

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public String getTop() { return top; }

    public void setTop(String top) { this.top = top; }
}
