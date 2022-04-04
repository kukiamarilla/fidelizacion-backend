package com.backend.fidelizacion.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.Bolsa;

@Stateless
public class BolsaDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Bolsa> listar() {
        return em.createQuery("select b from Bolsa b", Bolsa.class).getResultList();
    }

    public Bolsa obtener(Long id) {
        return em.find(Bolsa.class, id);
    }
    
    public void crear(Bolsa regla) {
        this.em.persist(regla);
    }

    public void actualizar(Bolsa regla) {
        this.em.merge(regla);
    }

    public void eliminar(Long id) {
        Bolsa regla = this.em.find(Bolsa.class, id);
        this.em.remove(regla);
    }

    public List<Bolsa> listarPorCliente(Long idCliente) {
        return em.createQuery("select b from Bolsa b where b.clienteId = :idCliente order by created_at asc", Bolsa.class).setParameter("idCliente", idCliente).getResultList();
    }

    public List<Bolsa> listarPorRango(Integer limiteInferior, Integer limiteSuperior) {
        return em.createQuery("select b from Bolsa b where b.monto >= :minimo and b.monto <= :maximo", Bolsa.class)
            .setParameter("minimo", limiteInferior)
            .setParameter("maximo", limiteSuperior)
            .getResultList();
    }
}
