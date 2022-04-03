
package com.backend.fidelizacion.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vencimientos")
public class Vencimiento {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(name = "dias_validez")
    private Integer diasValidez;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Vencimiento() {
        this.createdAt = LocalDateTime.now();
    }

    public Vencimiento(Date fechaInicio, Date fechaFin, Integer diasValidez, LocalDateTime createdAt) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasValidez = diasValidez;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDiasValidez() {
        return diasValidez;
    }

    public void setDiasValidez(Integer diasValidez) {
        this.diasValidez = diasValidez;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    }

    public boolean isValid() {
        return fechaFin.after(fechaInicio);
    }
}