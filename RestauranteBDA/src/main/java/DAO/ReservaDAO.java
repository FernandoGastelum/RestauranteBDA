/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.Reserva;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gaspa
 */
public class ReservaDAO {
    @PersistenceContext
    private EntityManager em;

    public void registrarReserva(Reserva reserva) {
        em.persist(reserva);
    }

    public List<Reserva> obtenerReservasPorCliente(Long idCliente) {
        return em.createQuery("SELECT r FROM Reserva r WHERE r.cliente.idCliente = :idCliente", Reserva.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }

    public boolean verificarDisponibilidadMesa(Long idMesa, LocalDateTime fechaHora) {
        // Implementar lógica para verificar si la mesa está disponible en la fecha y hora indicadas
        List<Reserva> reservas = em.createQuery("SELECT r FROM Reserva r WHERE r.mesa.idMesa = :idMesa AND r.fechaHora = :fechaHora", Reserva.class)
                .setParameter("idMesa", idMesa)
                .setParameter("fechaHora", fechaHora)
                .getResultList();

        return reservas.isEmpty();
    }
}
