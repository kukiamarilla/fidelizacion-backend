package com.backend.fidelizacion.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "bolsas")
public class Bolsa {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cliente_id")
    private Long clienteId;
    @Column(name = "fecha_asignacion")
    private Date fechaAsignacion;
    @Column(name = "fecha_caducidad")
    private Date fechaCaducidad;
    @Column(name = "puntaje_asignado")
    private int puntosAsignados;
    @Column(name = "puntaje_utilizado")
    private int puntosUtilizados;
    @Column(name = "saldo")
    private int saldo;
    @Column(name = "monto")
    private int monto;
    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    public Bolsa() {
        this.createdAt = LocalDateTime.now();
    }

    public Bolsa(Long clienteId, Date fechaAsignacion, Date fechaCaducidad, int puntosAsignados, int puntosUtilizados, int saldo, int monto) {
        this.clienteId = clienteId;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaCaducidad = fechaCaducidad;
        this.puntosAsignados = puntosAsignados;
        this.puntosUtilizados = puntosUtilizados;
        this.saldo = saldo;
        this.monto = monto;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getPuntosAsignados() {
        return puntosAsignados;
    }

    public void setPuntosAsignados(int puntosAsignados) {
        this.puntosAsignados = puntosAsignados;
    }

    public int getPuntosUtilizados() {
        return puntosUtilizados;
    }

    public void setPuntosUtilizados(int puntosUtilizados) {
        this.puntosUtilizados = puntosUtilizados;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    }
}
