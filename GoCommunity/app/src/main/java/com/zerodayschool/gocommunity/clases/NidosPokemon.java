package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class NidosPokemon implements Serializable {
    String id;
    String coordenadas;
    String foto;
    String nombre;
    String pais;
    String variocolor;

    public NidosPokemon(String id, String coordenadas, String foto, String nombre, String pais, String variocolor){
        this.id = id;
        this.coordenadas = coordenadas;
        this.foto = foto;
        this.nombre = nombre;
        this.pais = pais;
        this.variocolor = variocolor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoordenadas() { return coordenadas; }

    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

    public String getVariocolor() { return variocolor; }

    public void setVariocolor(String variocolor) { this.variocolor = variocolor; }
}
