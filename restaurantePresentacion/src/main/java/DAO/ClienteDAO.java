/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gaspa
 */
public class ClienteDAO {
    @PersistenceContext
    private EntityManager em;

    // Método para crear un nuevo cliente
    public void crearCliente(Cliente cliente) {
        em.persist(cliente);
    }
    
    public Cliente buscarClientePorTelefono(String telefono) {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class);
        query.setParameter("telefono", telefono);
        return query.getSingleResult();
    }
}
