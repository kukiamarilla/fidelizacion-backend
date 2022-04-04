package com.backend.fidelizacion.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usos")
public class Uso {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Column(name = "fecha")
    private Date fecha;
    @OneToMany(fetch = javax.persistence.FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
    @JoinTable(
        inverseJoinColumns = @JoinColumn(name = "id"),
        name = "uso_detalles",
        joinColumns = @JoinColumn(name = "uso_id")
    )
    private List<UsoDetalle> usoDetalles;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "concepto_id")
    private Concepto concepto;

    public Uso() {
        this.fecha = new Date(System.currentTimeMillis());
    }

    public Uso(Integer puntajeUtilizado, Date fecha, List<UsoDetalle> usoDetalles, Cliente cliente, Concepto concepto) {
        this.puntajeUtilizado = puntajeUtilizado;
        this.fecha = new Date(System.currentTimeMillis());
        this.usoDetalles = usoDetalles;
        this.cliente = cliente;
        this.concepto = concepto;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
    }

    public List<UsoDetalle> getUsoDetalles() {
        return usoDetalles;
    }

    public void setUsoDetalles(List<UsoDetalle> usoDetalles) {
        this.usoDetalles = usoDetalles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
}
