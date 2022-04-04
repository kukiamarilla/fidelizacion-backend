package com.backend.fidelizacion.notification;

import com.backend.fidelizacion.model.Uso;

public class PuntosUtilizadosNotification extends MailNotification {
    
    protected Uso uso;
    protected String content;

    public PuntosUtilizadosNotification(Uso uso) {
        this.uso = uso;
    }
    
    @Override
    protected String content() {
        content = "Has utilizado " + uso.getPuntajeUtilizado() + " en concepto de " + uso.getConcepto().getDescripcion() + ".\n";
        content += "El código de uso es: " + uso.getId() + ".\n";
        return content;
    }

    @Override
    protected String to() {
        return uso.getCliente().getEmail();
    }

    @Override
    protected String subject() {
        return "Puntos Utilizados";
    }
}
