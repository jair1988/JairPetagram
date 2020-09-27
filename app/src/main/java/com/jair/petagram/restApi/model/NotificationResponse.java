package com.jair.petagram.restApi.model;

public class NotificationResponse {

    private String mensaje;

    public NotificationResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public NotificationResponse() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
