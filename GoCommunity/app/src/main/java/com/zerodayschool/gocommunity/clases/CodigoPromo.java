package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class CodigoPromo implements Serializable {
    private String id;
    private  String foto;
    private  String nombre;
    private  String codigo;

    public CodigoPromo(String id, String foto, String nombre, String codigo) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
