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
 * La clase ClienteDAO gestiona las operaciones de acceso a datos relacionadas con los clientes,
 * permitiendo realizar operaciones CRUD (crear y consultar) en la base de datos. Utiliza JPA
 * (Java Persistence API) para la interacción con la base de datos.
 */
public class ClienteDAO {
    @PersistenceContext
    private EntityManager em;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    

    /**
     * Registra un nuevo cliente en la base de datos.
     *
     * @param clienteDTO Objeto de tipo ClienteDTO que contiene la información del cliente
     *                   que se desea registrar. Debe incluir el nombre y el teléfono del cliente.
     * @throws Exception Si ocurre algún error durante la transacción de registro.
     */
    public void registrarCliente(ClienteDTO clienteDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        Cliente cliente = new Cliente(clienteDTO.getNombre(),clienteDTO.getTelefono());
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println("Cliente creado");
    }
    
    /**
     * Busca y devuelve un cliente a partir de su número de teléfono.
     *
     * @param telefono El número de teléfono del cliente que se desea buscar.
     * @return Un objeto Cliente correspondiente al número de teléfono proporcionado.
     *         Si no se encuentra un cliente con ese teléfono, el método devolverá null.
     */
    public Cliente buscarClientePorTelefono(String telefono) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class);
        query.setParameter("telefono", telefono);
        return query.getSingleResult();
    }
    
    /**
     * Obtiene y devuelve una lista de todos los clientes registrados en la base de datos.
     *
     * @return Una lista de objetos Cliente que representan todos los clientes registrados.
     *         Si no hay clientes, la lista estará vacía.
     */
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
