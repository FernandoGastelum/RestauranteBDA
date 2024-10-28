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
 *
 * @author gaspa
 */
public class ReservaBO {
    private ReservaDAO reservaDAO = new ReservaDAO();
    public ReservaBO() {
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
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
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
    
    
}
