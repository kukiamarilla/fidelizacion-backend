package com.backend.fidelizacion.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.Vencimiento;

@Stateless
public class VencimientoDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Vencimiento> listar() {
        return em.createQuery("select v from Vencimiento v", Vencimiento.class).getResultList();
    }

    public Vencimiento obtener(Long id) {
        return em.find(Vencimiento.class, id);
    }
    
    public void crear(Vencimiento regla) {
        this.em.persist(regla);
    }

    public void actualizar(Vencimiento regla) {
        this.em.merge(regla);
    }

    public void eliminar(Long id) {
        Vencimiento regla = this.em.find(Vencimiento.class, id);
        this.em.remove(regla);
    }
}
