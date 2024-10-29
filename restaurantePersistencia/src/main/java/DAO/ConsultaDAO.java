/**
 * Clase ConsultaDAO
 *
 * Esta clase pertenece a la capa de acceso a datos (Data Access Object) y
 * proporciona métodos para consultar información de reservas en la base de
 * datos utilizando JPA. Realiza búsquedas de reservas basadas en diferentes
 * criterios, como cliente, fechas, tipo de mesa, ubicación y sucursal.
 */
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

    /**
     * Constructor de ConsultaDAO.
     *
     * Inicializa el EntityManagerFactory para conectarse a la unidad de
     * persistencia "pu_restaurante".
     */
    public ConsultaDAO() {
        this.emf = Persistence.createEntityManagerFactory("pu_restaurante");
    }

    /**
     * Obtiene una lista de reservas para un cliente específico según su nombre
     * y/o teléfono.
     *
     * @param nombre Nombre del cliente para filtrar las reservas. Puede ser
     * nulo o vacío si no se desea filtrar por nombre.
     * @param telefono Teléfono del cliente para filtrar las reservas. Puede ser
     * nulo o vacío si no se desea filtrar por teléfono.
     * @return Lista de reservas que coinciden con el nombre y/o teléfono del
     * cliente.
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
     * Obtiene una lista de reservas en un rango de fechas, filtradas por tipo
     * de mesa y ubicación.
     *
     * @param fechaInicio Fecha de inicio del rango de búsqueda de reservas.
     * @param fechaFin Fecha de fin del rango de búsqueda de reservas.
     * @param tipoMesa Tipo de mesa (por ejemplo, "Pequeña", "Mediana",
     * "Grande") para filtrar las reservas. Puede ser nulo si no se desea
     * filtrar por tipo.
     * @param ubicacion Ubicación de la mesa para filtrar las reservas. Puede
     * ser nulo si no se desea filtrar por ubicación.
     * @return Lista de reservas que coinciden con el rango de fechas, tipo de
     * mesa y ubicación especificados.
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
     * Obtiene una lista de reservas de una sucursal específica dentro de un
     * rango de fechas.
     *
     * @param nombreSucursal Nombre de la sucursal para filtrar las reservas.
     * @param fechaInicio Fecha de inicio del rango de búsqueda de reservas.
     * @param fechaFin Fecha de fin del rango de búsqueda de reservas.
     * @return Lista de reservas realizadas en la sucursal especificada en el
     * rango de fechas indicado.
     */
    public List<Reserva> obtenerReservasPorSucursalYFecha(String nombreSucursal, LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager em = emf.createEntityManager();

        try {
            String queryStr = "SELECT r FROM Reserva r WHERE FUNCTION('DATE', r.fechaHora) BETWEEN :fechaInicio AND :fechaFin "
                    + "AND r.lugar = :nombreSucursal";

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
     *
     * Este método debe llamarse cuando ya no se necesite realizar operaciones
     * en la base de datos, para liberar los recursos asociados al
     * EntityManagerFactory.
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
