package com.backend.fidelizacion.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.backend.fidelizacion.model.Cliente;

@Stateless
public class ClienteDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Cliente> listar() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    public Cliente obtener(Long id) {
        return em.find(Cliente.class, id);
    }
    
    public void crear(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void eliminar(Long id) {
        Cliente cliente = this.em.find(Cliente.class, id);
        this.em.remove(cliente);
    }
}
