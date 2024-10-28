package DAO;

import Entidades.Cliente;
import Entidades.Reserva;
import Entidades.Sucursal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;


public class ConsultaDAO {
    private EntityManagerFactory emf;

    public ConsultaDAO() {
        this.emf = Persistence.createEntityManagerFactory("pu_restaurante");
    }

    /**
     * Obtiene reservas de un cliente según su nombre y/o teléfono.
     *
     * @param nombre   
     * @param telefono 
     * @return
     */
    public List<Reserva> obtenerReservasPorCliente(String nombre, String telefono) {
        EntityManager em = emf.createEntityManager();
        
        try {
            String queryStr = "SELECT r FROM Reserva r WHERE 1=1";
            if (nombre != null && !nombre.isEmpty()) {
                queryStr += " AND r.cliente.nombreCompleto = :nombre";
            }
            if (telefono != null && !telefono.isEmpty()) {
                queryStr += " AND r.cliente.telefono = :telefono";
            }

            TypedQuery<Reserva> query = em.createQuery(queryStr, Reserva.class);
            if (nombre != null && !nombre.isEmpty()) {
                query.setParameter("nombre", nombre);
            }
            if (telefono != null && !telefono.isEmpty()) {
                query.setParameter("telefono", telefono);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene reservas en un rango de fechas, filtradas por tipo y ubicación de mesa.
     *
     * @param fechaInicio   
     * @param fechaFin     
     * @param tipoMesa     
     * @param ubicacion    
     * @return 
     */
    public List<Reserva> obtenerReservasPorFechaYMesa(LocalDate fechaInicio, LocalDate fechaFin, String tipoMesa, String ubicacion) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryStr = "SELECT r FROM Reserva r WHERE FUNCTION('DATE', r.fechaHora) BETWEEN :fechaInicio AND :fechaFin";
            if (tipoMesa != null && !tipoMesa.isEmpty()) {
                queryStr += " AND r.mesa.tipo = :tipoMesa";
            }
            if (ubicacion != null && !ubicacion.isEmpty()) {
                queryStr += " AND r.mesa.ubicacion = :ubicacion";
            }

            TypedQuery<Reserva> query = em.createQuery(queryStr, Reserva.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            if (tipoMesa != null && !tipoMesa.isEmpty()) {
                query.setParameter("tipoMesa", tipoMesa);
            }
            if (ubicacion != null && !ubicacion.isEmpty()) {
                query.setParameter("ubicacion", ubicacion);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene reservas de una sucursal específica en un rango de fechas.
     *
     * @param nombreSucursal  
     * @param fechaInicio      
     * @param fechaFin         
     * @return Lista de reservas en la sucursal y rango de fechas.
     */
    public List<Reserva> obtenerReservasPorSucursalYFecha(String nombreSucursal, LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryStr = "SELECT r FROM Reserva r WHERE FUNCTION('DATE', r.fechaHora) BETWEEN :fechaInicio AND :fechaFin " +
                              "AND r.lugar = :nombreSucursal";

            TypedQuery<Reserva> query = em.createQuery(queryStr, Reserva.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            query.setParameter("nombreSucursal", nombreSucursal);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory.
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

