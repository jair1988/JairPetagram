package com.jair.petagram.entidades;

public class LikeMascota {

    private String idMascota;
    private int voto;

    public LikeMascota(String idMascota, int voto) {
        this.idMascota = idMascota;
        this.voto = voto;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public int getVoto() {
        return voto;
    }
}
