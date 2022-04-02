
package com.backend.fidelizacion.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.Column;


@Entity
@Table(name = "reglas")
public class Regla {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "limite_inferior")
    private Integer limiteInferior;
    @Column(name = "limite_superior")
    private Integer limiteSuperior;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "created_at")
    private Date createdAt;

    public Regla() {
    }

    public Regla(Integer limiteInferior, Integer limiteSuperior, Integer monto, Date createdAt) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.monto = monto;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isValid() {
        return this.limiteInferior == null || this.limiteSuperior == null || this.limiteInferior <= this.limiteSuperior;
    }
}