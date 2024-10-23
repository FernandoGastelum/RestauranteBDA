/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ReservaDAO;
import Dto.ReservaDTO;
import Persistencia.Cliente;
import Persistencia.Mesa;
import Persistencia.Reserva;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gaspa
 */
public class ReservaBO {
    private ReservaDAO reservaDAO = new ReservaDAO();
    

    public void registrarReserva(ReservaDTO reservaDTO) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
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
        reservaDAO.registrarReserva(reserva);
    }

    private Double calcularCosto(int numPersonas) {
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
