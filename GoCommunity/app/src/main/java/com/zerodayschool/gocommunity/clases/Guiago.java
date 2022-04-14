package com.zerodayschool.gocommunity.clases;

import java.io.Serializable;

public class Guiago  implements Serializable {

    private String id;
    private  String foto;
    private  String nombre;
    private String uso;

    public  Guiago(String id, String foto,String nombre, String uso){
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.uso = uso;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUso() { return uso; }

    public void setUso(String uso) { this.uso = uso; }
}
