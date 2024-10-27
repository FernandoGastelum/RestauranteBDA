/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BOs.ReservaBO;
import Dto.ClienteDTO;
import Dto.ReservaDTO;
import Dto.SucursalDTO;
import Entidades.Cliente;
import Entidades.Mesa;
import Entidades.Reserva;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gaspa
 */
public class ReservaDAO {
    @PersistenceContext
    private EntityManager em;
    ReservaBO reservaBO = new ReservaBO();
    SucursalDAO sucursalDAO = new SucursalDAO();
    
    public void registrarReserva(ReservaDTO reservaDTO) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        //cliente.setId(clienteBO.buscarClientePorTelefono(cliente.getTelefono()).getId());
        
        // Verificar disponibilidad de la mesa
        boolean disponible = verificarDisponibilidadMesa(reservaDTO.getIdMesa(), reservaDTO.getFechaHora());
        if (!disponible) {
            throw new Exception("La mesa no está disponible en la fecha y hora seleccionadas.");
        }

        // Crear nueva reserva
        Reserva reserva = new Reserva();
        reserva.setFechaHora(reservaDTO.getFechaHora());
        
        reserva.setNumeroPersonas(reservaDTO.getNumPersonas());
        reserva.setCosto(reservaBO.calcularCosto(reservaDTO.getNumPersonas()));
        reserva.setEstado(true);
        reserva.setLugar(reservaDTO.getLugar());

        // Asignar cliente y mesa
        Cliente cliente = em.find(Cliente.class, reservaDTO.getIdCliente());
        Mesa mesa = em.find(Mesa.class, reservaDTO.getIdMesa());
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);

        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();
        System.out.println("Reserva ");
        
    }
    
    public List<Reserva> obtenerReservasPorCliente(Long idCliente) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Reserva r WHERE r.cliente.idCliente = :idCliente", Reserva.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }
    
    public boolean verificarDisponibilidadMesa(Long idMesa, LocalDateTime fechaHora) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limiteMaximo = ahora.plusMonths(1);
        
        if (fechaHora.isBefore(ahora.plusHours(24)) || fechaHora.isAfter(limiteMaximo)) {
            
            return false; 
        }
        
        
        LocalTime HORA_APERTURA = sucursalDAO.buscarSucursal("sucursalParis2").getHoraApertura();
        LocalTime HORA_CIERRE = sucursalDAO.buscarSucursal("sucursalParis2").getHoraCierre();
        sucursalDAO.buscarSucursal("sucursalParis2").getHoraCierre();
        LocalTime horaReserva = fechaHora.toLocalTime();
        
        
        if (horaReserva.isBefore(HORA_APERTURA) || horaReserva.isAfter(HORA_CIERRE)) {
            return false; // No está dentro del horario de apertura
        }    
        
        List<Reserva> reservas = em.createQuery("SELECT r FROM Reserva r WHERE r.mesa.id = :idMesa AND r.fechaHora = :fechaHora", Reserva.class)
                .setParameter("idMesa", idMesa)
                .setParameter("fechaHora", fechaHora)
                .getResultList();

        return reservas.isEmpty();
    }
}
