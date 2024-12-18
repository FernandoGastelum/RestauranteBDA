/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BOs.ReservaBO;
import Dto.ClienteDTO;
import Dto.ReservaDTO;
import Dto.SucursalDTO;
import Entidades.Cancelacion;
import Entidades.Cliente;
import Entidades.Mesa;
import Entidades.Reserva;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 * La clase ReservaDAO gestiona las operaciones de acceso a datos relacionadas con las reservas,
 * incluyendo la creación, búsqueda, actualización y generación de reportes. Utiliza JPA 
 * (Java Persistence API) para la interacción con la base de datos.
 */
public class ReservaDAO {
    @PersistenceContext
    private EntityManager em;
    ReservaBO reservaBO;
    SucursalDAO sucursalDAO;
    /**
     * Constructor de la clase ReservaDAO.
     */
    public ReservaDAO() {
    }
    /**
     * Registra una nueva reserva en la base de datos, verificando primero la disponibilidad de la mesa.
     *
     * @param reservaDTO Objeto de tipo ReservaDTO que contiene los datos de la reserva a registrar.
     * @throws Exception Si la mesa no está disponible en la fecha y hora seleccionadas o si ocurre un error en la transacción.
     */
    public void registrarReserva(ReservaDTO reservaDTO) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        reservaBO = new ReservaBO();
        
        // Verificar disponibilidad de la mesa
        boolean disponible = verificarDisponibilidadMesa(reservaDTO.getIdMesa(), reservaDTO.getFechaHora());
        if (!disponible) {
            JOptionPane.showMessageDialog(null, "La mesa no está disponible en la fecha y hora seleccionadas.", "Error", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(null, "Se ha registrado una reserva", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    
    /**
    * Verifica la disponibilidad de una mesa para una reserva en una fecha y hora específicas.
    *
    * Este método comprueba si la mesa identificada por `idMesa` está disponible en el 
    * momento indicado por `fechaHora`. Se asegura de que la reserva cumpla con las 
    * siguientes condiciones:
    * 1. La fecha y hora de la reserva deben ser al menos 24 horas en el futuro 
    *    y no más de un mes de anticipación.
    * 2. La hora de la reserva debe estar dentro del horario de apertura y cierre 
    *    de la sucursal correspondiente.
    * 3. No debe existir otra reserva para la misma mesa en la misma fecha y hora.
    * 4. La mesa no debe haber sido apartada en las últimas 5 horas desde el momento 
    *    actual.
    *
    * @param idMesa   El identificador de la mesa que se desea reservar.
    * @param fechaHora La fecha y hora en la que se desea realizar la reserva.
    * @return true si la mesa está disponible para la reserva; false en caso contrario.
    */
   public boolean verificarDisponibilidadMesa(Long idMesa, LocalDateTime fechaHora) {
       // Crear una fábrica de EntityManager para gestionar la conexión a la base de datos
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
       EntityManager em = emf.createEntityManager();

       // Crear una instancia del DAO de Sucursal para acceder a la información de sucursales
       sucursalDAO = new SucursalDAO();

       // Obtener el tiempo actual
       LocalDateTime ahora = LocalDateTime.now();
       // Calcular el límite máximo de fecha, que es un mes a partir de ahora
       LocalDateTime limiteMaximo = ahora.plusMonths(1);

       // Verificar si la fecha de reserva está dentro de las 24 horas o más de un mes
       if (fechaHora.isBefore(ahora.plusHours(24)) || fechaHora.isAfter(limiteMaximo)) {
           return false; // La reserva no es válida
       }

       // Verificar si la mesa ha sido apartada en las últimas 5 horas
       LocalDateTime limiteAparcamiento = ahora.minusHours(5);
       List<Reserva> reservasRecientes = em.createQuery(
               "SELECT r FROM Reserva r WHERE r.mesa.id = :idMesa AND r.fechaHora > :limiteAparcamiento", 
               Reserva.class)
               .setParameter("idMesa", idMesa)
               .setParameter("limiteAparcamiento", limiteAparcamiento)
               .getResultList();

       if (!reservasRecientes.isEmpty()) {
           return false; // La mesa ha sido apartada en las últimas 5 horas
       }

       // Obtener las horas de apertura y cierre de la sucursal
       LocalTime HORA_APERTURA = sucursalDAO.buscarSucursal("sucursalParis2").getHoraApertura();
       LocalTime HORA_CIERRE = sucursalDAO.buscarSucursal("sucursalParis2").getHoraCierre();

       // Obtener la hora de la reserva
       LocalTime horaReserva = fechaHora.toLocalTime();

       // Verificar si la hora de la reserva está fuera del horario de apertura
       if (horaReserva.isBefore(HORA_APERTURA) || horaReserva.isAfter(HORA_CIERRE)) {
           return false; // No está dentro del horario de apertura
       }

       // Consultar la base de datos para verificar si ya existe una reserva en la misma mesa y hora
       List<Reserva> reservas = em.createQuery(
               "SELECT r FROM Reserva r WHERE r.mesa.id = :idMesa AND r.fechaHora = :fechaHora", 
               Reserva.class)
               .setParameter("idMesa", idMesa)
               .setParameter("fechaHora", fechaHora)
               .getResultList();

       // Retornar verdadero si no hay reservas conflictivas; falso de lo contrario
       return reservas.isEmpty();
    }
    /**
     * Obtiene una lista de reservas para un cliente específico según su ID.
     *
     * @param idCliente ID del cliente cuyas reservas se desean obtener.
     * @return Lista de objetos Reserva pertenecientes al cliente especificado.
     */
    public List<Reserva> obtenerReservasPorCliente(Long idCliente) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Reserva r WHERE r.id = :idCliente", Reserva.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }
    /**
     * Busca una reserva en la base de datos según el teléfono del cliente.
     *
     * @param telefono Número de teléfono del cliente asociado a la reserva.
     * @return Objeto Reserva correspondiente al cliente con el teléfono proporcionado.
     */
    public Reserva buscarReservaPorTelefono(String telefono) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Reserva r WHERE r.cliente.telefono = :telefono", Reserva.class)
                .setParameter("telefono", telefono)
                .getSingleResult();
    }
    /**
     * Genera un reporte de reservas filtrado por rango de fechas, tipo de mesa y ubicación.
     *
     * @param fecha1    Fecha de inicio del rango de fechas.
     * @param fecha2    Fecha de fin del rango de fechas.
     * @param tipo      Tipo de mesa.
     * @param ubicacion Ubicación de la mesa.
     * @return Lista de objetos Reserva que cumplen con los criterios de búsqueda.
     */
    public List<Reserva> obtenerReservasReportes(LocalDate fecha1, LocalDate fecha2, String tipo, String ubicacion){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r WHERE FUNCTION('DATE', r.fechaHora) BETWEEN :fecha1 AND :fecha2 "+
            "AND r.mesa.tipo = :tipo " +
            "AND r.mesa.ubicacion = :ubicacion", Reserva.class);
        query.setParameter("fecha1", fecha1);
        query.setParameter("fecha2", fecha2);
        query.setParameter("tipo", tipo);
        query.setParameter("ubicacion", ubicacion);
        List<Reserva> reservas = query.getResultList();
        return reservas;
    }
    /**
     * Obtiene la cancelación asociada a una reserva específica.
     *
     * @param idReserva ID de la reserva cuya cancelación se desea obtener.
     * @return Objeto Cancelacion correspondiente a la reserva, o null si no tiene una cancelación.
     */
    public Cancelacion obtenerCancelacionPorReserva(Long idReserva) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Cancelacion c WHERE c.reserva.id = :idReserva", Cancelacion.class)
                     .setParameter("idReserva", idReserva)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // Si no hay cancelación para esta reserva
        } finally {
            em.close();
            emf.close();
        }
    }
    /**
     * Obtiene una lista de todas las reservas registradas en la base de datos.
     *
     * @return Lista de todos los objetos Reserva en la base de datos.
     */
    public List<Reserva> obtenerTodasLasReservas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Reserva r",Reserva.class).getResultList();
    }
    /**
     * Obtiene una lista de todas las reservas disponibles en la base de datos.
     *
     * @return Lista de todos los objetos Reserva con estado disponible.
     */
    public List<Reserva> obtenerTodasLasReservasDisponibles(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r FROM Reserva r WHERE r.estado = 1",Reserva.class).getResultList();
    }
    /**
     * Cambia el estado de una reserva específica a no disponible.
     *
     * @param idReserva ID de la reserva cuyo estado se desea cambiar.
     * @throws Exception Si no se encuentra una reserva con el ID especificado o si ocurre un error en la transacción.
     */
    public void cambiarEstadoReserva(Long idReserva) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        
        try {
            
            em.getTransaction().begin();
            
            // Buscar la reserva por su ID
            Reserva reserva = em.find(Reserva.class, idReserva);
            if (reserva == null) {
                throw new Exception("No se encontró una reserva con el ID especificado.");
            }
            reserva.setEstado(false);
            
            // Actualizar la reserva en la base de datos
            em.merge(reserva);
            em.getTransaction().commit();
            
            System.out.println("Estado de la reserva actualizado correctamente.");
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}
