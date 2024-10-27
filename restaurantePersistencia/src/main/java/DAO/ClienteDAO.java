/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dto.ClienteDTO;
import Entidades.Cliente;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gaspa
 */
public class ClienteDAO {
    @PersistenceContext
    private EntityManager em;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    

    // Método para crear un nuevo cliente
    public void registrarCliente(ClienteDTO clienteDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = new Cliente(clienteDTO.getNombre(),clienteDTO.getTelefono());
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println("Cliente creado");
    }
    
    
    public Cliente buscarClientePorTelefono(String telefono) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class);
        query.setParameter("telefono", telefono);
        return query.getSingleResult();
    }
    
    // Método para buscar todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String queryJpql = "SELECT c FROM Cliente c";
        TypedQuery query = em.createQuery(queryJpql, Cliente.class);
        List<Cliente> clientes = query.getResultList();
        em.getTransaction().commit();
        return clientes;
    }
    
    
}
