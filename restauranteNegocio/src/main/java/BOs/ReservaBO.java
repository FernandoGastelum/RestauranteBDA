/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.ReservaDAO;
import Dto.ClienteDTO;
import Dto.ReservaDTO;
import Entidades.Cliente;
import Entidades.Mesa;
import Entidades.Reserva;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gaspa
 */
public class ReservaBO {
    private EntityManager em;
    public ReservaBO() {
    }
    public void registrarReserva(Reserva reserva) {
        em.persist(reserva);
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
