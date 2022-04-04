package com.backend.fidelizacion.model;

public class Operacion {
    
    private Long clienteId;
    private Integer monto;

    public Operacion() {
    }

    public Operacion(Long clienteId, Integer monto) {
        this.clienteId = clienteId;
        this.monto = monto;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
    
}
