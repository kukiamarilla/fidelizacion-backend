package com.backend.fidelizacion.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.Concepto;

@Stateless
public class ConceptoDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Concepto> listar() {
        return em.createQuery("select c from Concepto c", Concepto.class).getResultList();
    }

    public Concepto obtener(Long id) {
        return em.find(Concepto.class, id);
    }
    
    public void crear(Concepto concepto) {
        this.em.persist(concepto);
    }

    public void actualizar(Concepto concepto) {
        this.em.merge(concepto);
    }

    public void eliminar(Long id) {
        Concepto concepto = this.em.find(Concepto.class, id);
        this.em.remove(concepto);
    }
}
