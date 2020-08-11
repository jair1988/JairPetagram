package com.jair.petagram.entidades;

public class Mascota {

    private String id;
    private String nombre;
    private String foto;
    private int cantidadVotos;

    public Mascota(String id, String nombre, String foto, int cantidadVotos) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.cantidadVotos = cantidadVotos;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
