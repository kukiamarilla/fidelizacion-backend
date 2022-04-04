package com.backend.fidelizacion.ejb;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.backend.fidelizacion.model.Bolsa;
import com.backend.fidelizacion.model.Cliente;


@Stateless
public class ClienteDAO {
    
    @PersistenceContext(unitName = "fidelizacion")
    private EntityManager em;

    public List<Cliente> listar(String nombre, String apellido, String cumple) {
        // return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> clientes = query.from(Cliente.class);
        List<Predicate> predicates = new ArrayList<>();
        if (nombre != null && !nombre.isEmpty()) {
            predicates.add(cb.like(clientes.get("nombre").as(String.class), "%" + nombre + "%"));
        }
        if (apellido != null && !apellido.isEmpty()) {
            predicates.add(cb.like(clientes.get("apellido").as(String.class), "%" + apellido + "%"));
        }
        if (cumple != null && !cumple.isEmpty()) {
            predicates.add(cb.equal(clientes.get("fechaNacimiento"), Date.valueOf(cumple)));
        }
        query.select(clientes).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(query).getResultList();

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

    public List<Cliente> conPuntosPorVencer(Integer dias) {
        HashSet<Cliente> clientes = new HashSet<Cliente>();
        List<Bolsa> bolsas = this.em.createQuery("select b from Bolsa b where b.fechaCaducidad >= :hoy and b.fechaCaducidad <= :fecha", Bolsa.class)
                .setParameter("hoy", Date.valueOf(LocalDate.now()))
                .setParameter("fecha", Date.valueOf(LocalDate.now().plusDays(dias))).getResultList();
        for (Bolsa bolsa : bolsas) {
            Cliente cliente = this.obtener(bolsa.getClienteId());
            clientes.add(cliente);
        }
        return new ArrayList<Cliente>(clientes);
    }
}
