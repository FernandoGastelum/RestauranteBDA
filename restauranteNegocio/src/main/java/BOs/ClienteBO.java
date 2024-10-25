/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.ClienteDAO;
import Dto.ClienteDTO;
import Entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gaspa
 */
public class ClienteBO {
    private ClienteDAO clienteDAO = new ClienteDAO();
    

    public void registrarCliente(ClienteDTO clienteDTO) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        Cliente cliente = new Cliente(clienteDTO.getNombre(),clienteDTO.getTelefono());
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        System.out.println("Cliente creado");
    }
    public Cliente buscarClientePorTelefono(String telefono) {
        return clienteDAO.buscarClientePorTelefono(telefono);
    }
    
}
