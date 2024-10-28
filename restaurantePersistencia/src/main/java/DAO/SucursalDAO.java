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
 * La clase SucursalDAO gestiona las operaciones de acceso a datos relacionadas con las sucursales,
 * incluyendo la creación, actualización de horarios y búsqueda de sucursales en la base de datos.
 */
public class SucursalDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    /**
     * Registra una nueva sucursal en la base de datos utilizando los datos proporcionados en el objeto SucursalDTO.
     *
     * @param sucursalDTO Objeto de tipo SucursalDTO que contiene los datos de la sucursal a registrar.
     */
    public void registrarSucursal(SucursalDTO sucursalDTO){
        EntityManager em = emf.createEntityManager();
        Sucursal sucursal = new Sucursal(Long.valueOf(2),sucursalDTO.getNombre(), sucursalDTO.getHoraApertura(), sucursalDTO.getHoraCierre());
        em.getTransaction().begin();
        em.persist(sucursal);
        em.getTransaction().commit();
        System.out.println("Sucursal creada");
    }
    /**
     * Cambia el horario de apertura y cierre de una sucursal existente en la base de datos.
     *
     * @param sucursalDTO Objeto de tipo SucursalDTO que contiene el nombre de la sucursal
     *                    y los nuevos horarios de apertura y cierre.
     */
    public void cambiarHoraSucursal(SucursalDTO sucursalDTO){
        Sucursal sucursal = buscarSucursal(sucursalDTO.getNombre());
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(sucursal);
        em.getTransaction().commit();
        System.out.println("Sucursal editada");
    }
    /**
     * Busca y devuelve una sucursal específica en la base de datos basada en su nombre.
     *
     * @param nombre Nombre de la sucursal que se desea buscar.
     * @return Objeto Sucursal correspondiente al nombre especificado.
     */
    public Sucursal buscarSucursal(String nombre){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Sucursal> query = em.createQuery("SELECT s FROM Sucursal s WHERE s.nombre = :nombre", Sucursal.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
}
