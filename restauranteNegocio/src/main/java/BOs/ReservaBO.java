/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.ReservaDAO;
import Entidades.Cancelacion;
import Entidades.Reserva;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 * La clase ReservaBO proporciona lógica de negocio para la gestión de reservas en el sistema.
 * Incluye métodos para calcular el costo de una reserva, cargar datos de reservas en tablas de visualización,
 * y gestionar la limpieza de dichas tablas.
 */
public class ReservaBO {
    private ReservaDAO reservaDAO = new ReservaDAO();
    /**
     * Constructor por defecto para la clase ReservaBO.
     */
    public ReservaBO() {
    }
    /**
     * Calcula el costo de la reserva en función del número de personas.
     * 
     * @param numPersonas Número de personas que incluye la reserva.
     * @return El costo correspondiente de la reserva según el número de personas.
     */
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
    /**
     * Limpia todas las filas de una tabla específica en el modelo proporcionado.
     * 
     * @param model El modelo de tabla (DefaultTableModel) que se limpiará.
     */
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    /**
     * Carga todas las reservas disponibles en el modelo de tabla proporcionado.
     * 
     * @param model El modelo de tabla (DefaultTableModel) en el que se cargarán las reservas.
     */
    public void cargarTablaReservas(DefaultTableModel model){
        
        java.util.List<Reserva> listaReservas = reservaDAO.obtenerTodasLasReservasDisponibles();
        
        for (Reserva reservas : listaReservas) {
        model.addRow(new Object[]{
            reservas.getCliente().getNombre(),
            reservas.getCliente().getTelefono(),
            reservas.getMesa().getCodigo(),
            reservas.getFechaHora(),
            reservas.getCosto()
            });
        }
    }
    /**
     * Carga las reservas en un rango de fechas especificado y filtradas por tipo y ubicación en el modelo de tabla.
     * También indica si una reserva ha sido cancelada y muestra cualquier multa aplicada.
     * 
     * @param model El modelo de tabla (DefaultTableModel) en el que se cargarán las reservas.
     * @param fecha1 La fecha inicial del rango de búsqueda.
     * @param fecha2 La fecha final del rango de búsqueda.
     * @param tipo El tipo de mesa para filtrar las reservas.
     * @param ubicacion La ubicación de la mesa para filtrar las reservas.
     */
    public void cargarTablaReservas(DefaultTableModel model, LocalDate fecha1, LocalDate fecha2, String tipo, String ubicacion) {
        java.util.List<Reserva> listaReservas = reservaDAO.obtenerReservasReportes(fecha1, fecha2, tipo, ubicacion);

        for (Reserva reserva : listaReservas) {
            // Verificar si existe una cancelación para la reserva
            Cancelacion cancelacion = reservaDAO.obtenerCancelacionPorReserva(reserva.getId());

            // Comprobar si hay una cancelación
            boolean hayCancelacion = cancelacion != null;
            Double multa = hayCancelacion ? cancelacion.getMulta() : 0.0; // Asignar la multa o 0.0 si no hay cancelación

            model.addRow(new Object[]{
                reserva.getCliente().getNombre(),
                reserva.getFechaHora(),
                reserva.getNumeroPersonas(),
                reserva.getMesa().getTipo(),
                reserva.getMesa().getUbicacion(),
                reserva.getCosto(),
                hayCancelacion ? "Sí" : "No", // Mostrar si hay cancelación
                multa // Mostrar la multa
            });
        }
    }
    /**
     * Carga todas las reservas generales en el modelo de tabla, incluyendo información de cancelaciones y multas.
     * 
     * @param model El modelo de tabla (DefaultTableModel) en el que se cargarán las reservas generales.
     */
    public void cargarTablaReservasGeneral(DefaultTableModel model) {
        java.util.List<Reserva> listaReservas = reservaDAO.obtenerTodasLasReservas();

        for (Reserva reserva : listaReservas) {
            // Verificar si existe una cancelación para la reserva
            Cancelacion cancelacion = reservaDAO.obtenerCancelacionPorReserva(reserva.getId());

            // Comprobar si hay una cancelación
            boolean hayCancelacion = cancelacion != null;
            Double multa = hayCancelacion ? cancelacion.getMulta() : 0.0; // Asignar la multa o 0.0 si no hay cancelación

            model.addRow(new Object[]{
                reserva.getCliente().getNombre(),
                reserva.getFechaHora(),
                reserva.getNumeroPersonas(),
                reserva.getMesa().getTipo(),
                reserva.getMesa().getUbicacion(),
                reserva.getCosto(),
                hayCancelacion ? "Sí" : "No", // Mostrar si hay cancelación
                multa // Mostrar la multa
            });
        }
    }
    
    
}
