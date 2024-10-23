/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ReservaDAO;
import Dto.ClienteDTO;
import Dto.ReservaDTO;
import Persistencia.Cliente;
import Persistencia.Mesa;
import Persistencia.Reserva;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gaspa
 */
public class ReservaBO {
    private ReservaDAO reservaDAO = new ReservaDAO();
    
    ClienteBO clienteBO = new ClienteBO();
    public void registrarReserva(ReservaDTO reservaDTO, ClienteDTO clienteDTO) throws Exception {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
            //cliente.setId(clienteBO.buscarClientePorTelefono(cliente.getTelefono()).getId());
            Cliente cliente = new Cliente( Long.valueOf(1),"Jahabiel", "6442165487");
            Mesa mesa = new Mesa( Long.valueOf(1),"Pequena", 2, "Terraza", "Ter-2-001");
            Reserva reserva1 = new Reserva( reservaDTO.getCosto(), true, reservaDTO.getFechaHora(), reservaDTO.getNumPersonas(), reservaDTO.getLugar(),cliente, mesa);
            em.getTransaction().begin();
            em.persist(reserva1);
            em.getTransaction().commit();
            System.out.println("Reserva ");
        
        
        /*
        // Verificar disponibilidad de la mesa
        boolean disponible = reservaDAO.verificarDisponibilidadMesa(reservaDTO.getIdMesa(), reservaDTO.getFechaHora());
        if (!disponible) {
            throw new Exception("La mesa no está disponible en la fecha y hora seleccionadas.");
        }

        // Crear nueva reserva
        Reserva reserva = new Reserva();
        reserva.setFechaHora(reservaDTO.getFechaHora());
        reserva.setNumeroPersonas(reservaDTO.getNumPersonas());
        reserva.setCosto(calcularCosto(reservaDTO.getNumPersonas()));
        reserva.setEstado(true);
        

        // Asignar cliente y mesa
        Cliente cliente = em.find(Cliente.class, reservaDTO.getIdCliente());
        Mesa mesa = em.find(Mesa.class, reservaDTO.getIdMesa());
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);

        // Registrar la reserva
        reservaDAO.registrarReserva(reserva);*/
    }

    public Double calcularCosto(int numPersonas) {
        if (numPersonas <= 2) {
            return 300.0;
        } else if (numPersonas <= 4) {
            return 500.0;
        } else if (numPersonas <= 8) {
            return 700.0;
        }
        return 0.0;
    }
}
