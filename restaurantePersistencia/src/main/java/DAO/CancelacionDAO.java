/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dto.CancelacionDTO;
import Dto.ReservaDTO;
import Entidades.Cancelacion;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author gaspa
 */
public class CancelacionDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    public void registrarCancelacion(CancelacionDTO cancelacionDTO){
        EntityManager em = emf.createEntityManager();
        Cancelacion cancelacion = new Cancelacion(cancelacionDTO.getFecha(), cancelacionDTO.getMulta(), cancelacionDTO.getReserva());
        em.getTransaction().begin();
        em.persist(cancelacion);
        em.getTransaction().commit();
        System.out.println("Cancelacion creada");
    }
    public List<Cancelacion> buscarCancelacionesPorReservas(Long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cancelacion> query = em.createQuery("SELECT c FROM Cancelacion c WHERE c.reserva.id = :id", Cancelacion.class);
        query.setParameter("id", id);
        List<Cancelacion> listaCancelacion = query.getResultList();
        return listaCancelacion;
    }
    
    public List<Cancelacion> buscarCancelaciones(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cancelacion> query = em.createQuery("SELECT c FROM Cancelacion c ", Cancelacion.class);
        List<Cancelacion> listaCancelacion = query.getResultList();
        return listaCancelacion;
    }
    
}
