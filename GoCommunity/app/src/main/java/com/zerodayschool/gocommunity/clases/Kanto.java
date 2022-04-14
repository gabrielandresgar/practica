package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class Kanto implements Serializable {

    String id;
    String datos;
    String foto;
    String nombre;
    String pcmax;
    String tipo;
    String variocolor;

    public Kanto(String id, String datos, String foto, String nombre, String pcmax,String tipo, String variocolor){
        this.id = id;
        this.datos = datos;
        this.foto = foto;
        this.nombre = nombre;
        this.pcmax = pcmax;
        this.tipo = tipo;
        this.variocolor = variocolor;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getDatos() { return datos; }

    public void setDatos(String datos) { this.datos = datos; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre;}

    public String getPcmax() { return pcmax; }

    public void setPcmax(String pcmax) { this.pcmax = pcmax; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getVariocolor() { return variocolor; }

    public void setVariocolor(String variocolor) { this.variocolor = variocolor; }
}
