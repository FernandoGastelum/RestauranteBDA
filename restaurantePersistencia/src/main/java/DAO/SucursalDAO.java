/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dto.SucursalDTO;
import Entidades.Sucursal;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author gaspa
 */
public class SucursalDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    public void registrarSucursal(SucursalDTO sucursalDTO){
        EntityManager em = emf.createEntityManager();
        Sucursal sucursal = new Sucursal(Long.valueOf(2),sucursalDTO.getNombre(), sucursalDTO.getHoraApertura(), sucursalDTO.getHoraCierre());
        em.getTransaction().begin();
        em.persist(sucursal);
        em.getTransaction().commit();
        System.out.println("Sucursal creada");
    }
    public void cambiarHoraSucursal(SucursalDTO sucursalDTO){
        Sucursal sucursal = buscarSucursal(sucursalDTO.getNombre());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(sucursal);
        em.getTransaction().commit();
        System.out.println("Sucursal editada");
    }
    public Sucursal buscarSucursal(String nombre){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Sucursal> query = em.createQuery("SELECT s FROM Sucursal s WHERE s.nombre = :nombre", Sucursal.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
}
