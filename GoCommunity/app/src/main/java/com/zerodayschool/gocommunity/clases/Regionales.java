package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class Regionales implements Serializable {
    private String id;
    private  String foto;
    private  String nombre;
    private  String coordenada;
    private  String pais;

    public Regionales(String id, String foto, String nombre, String coordenada, String pais) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.coordenada = coordenada;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
