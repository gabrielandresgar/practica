package com.zerodayschool.gocommunity.clases;

public class Perfil {
    private String id;
    private String foto;
    private String nombre;
    private String apellido;
    private String celular;
    private String nickname;

    public Perfil(String id, String imagenPerfil, String nombre, String apellido, String nickname, String celular) {
        this.id = id;
        this.foto = imagenPerfil;
        this.nombre = nombre;
        this.celular = celular;
        this.apellido = apellido;
        this.nickname = nickname;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }
}
