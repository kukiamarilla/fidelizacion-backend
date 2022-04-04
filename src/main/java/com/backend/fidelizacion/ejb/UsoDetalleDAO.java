package com.backend.fidelizacion.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.UsoDetalle;

@Stateless
public class UsoDetalleDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public UsoDetalle obtener(Long id) {
        return em.find(UsoDetalle.class, id);
    }
    
    public void crear(UsoDetalle usoDetalle) {
        this.em.persist(usoDetalle);
    }

    public void actualizar(UsoDetalle usoDetalle) {
        this.em.merge(usoDetalle);
    }

    public void eliminar(Long id) {
        UsoDetalle usoDetalle = this.em.find(UsoDetalle.class, id);
        this.em.remove(usoDetalle);
    }
}
