package com.backend.fidelizacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "uso_detalles")
public class UsoDetalle {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Column(name = "uso_id")
    private Long usoId;
    @ManyToOne
    @JoinColumn(name = "bolsa_id")
    private Bolsa bolsa;

    public UsoDetalle() {
    }

    public UsoDetalle(Integer puntajeUtilizado, Long usoId, Bolsa bolsa) {
        this.puntajeUtilizado = puntajeUtilizado;
        this.usoId = usoId;
        this.bolsa = bolsa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Long getUsoId() {
        return usoId;
    }

    public void setUsoId(Long usoId) {
        this.usoId = usoId;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }
}
