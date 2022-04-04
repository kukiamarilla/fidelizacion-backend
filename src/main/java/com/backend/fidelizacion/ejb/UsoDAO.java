package com.backend.fidelizacion.ejb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.backend.fidelizacion.model.Uso;

@Stateless
public class UsoDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Uso> listar(String concepto, String fecha, Long clienteId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Uso> cq = cb.createQuery(Uso.class);
        Root<Uso> usos = cq.from(Uso.class);
        List<Predicate> predicates = new ArrayList<>();
        if(concepto != null && !concepto.isEmpty()) {
            predicates.add(cb.like(usos.get("concepto").get("descripcion").as(String.class), "%" + concepto + "%"));
        }
        if(fecha != null && !fecha.isEmpty()) {
            predicates.add(cb.equal(usos.get("fecha"), Date.valueOf(fecha)));
        }
        if(clienteId != null) {
            predicates.add(cb.equal(usos.get("cliente").get("id"), clienteId));
        }
        cq.select(usos).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(cq).getResultList();
    }

    public Uso obtener(Long id) {
        return em.find(Uso.class, id);
    }
    
    public void crear(Uso uso) {
        this.em.persist(uso);
    }

    public void actualizar(Uso uso) {
        this.em.merge(uso);
    }

    public void eliminar(Long id) {
        Uso uso = this.em.find(Uso.class, id);
        this.em.remove(uso);
    }
}
