package com.backend.fidelizacion.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.Regla;

@Stateless
public class ReglaDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Regla> listar() {
        return em.createQuery("select r from Regla r", Regla.class).getResultList();
    }

    public Regla obtener(Long id) {
        return em.find(Regla.class, id);
    }
    
    public void crear(Regla regla) {
        this.em.persist(regla);
    }

    public void actualizar(Regla regla) {
        this.em.merge(regla);
    }

    public void eliminar(Long id) {
        Regla regla = this.em.find(Regla.class, id);
        this.em.remove(regla);
    }
}
