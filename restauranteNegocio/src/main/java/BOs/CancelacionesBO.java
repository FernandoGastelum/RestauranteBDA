/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.CancelacionDAO;
import Entidades.Cancelacion;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import javax.swing.table.DefaultTableModel;

/**
 * La clase CancelacionesBO representa la capa de negocio para las operaciones de cancelaciones en el sistema.
 * Proporciona métodos para calcular la multa por cancelación, limpiar el contenido de una tabla, y cargar datos de cancelaciones.
 */
public class CancelacionesBO {
    private CancelacionDAO cancelacionDAO = new CancelacionDAO();
    /**
     * Calcula la multa aplicable para una reserva cancelada según la proximidad de la fecha y hora de la reserva.
     *
     * @param fechaHoraReserva Fecha y hora de la reserva.
     * @param costoReserva     Costo de la reserva.
     * @return El valor de la multa calculada en base a la diferencia horaria respecto a la fecha actual.
     */
    public double calcularMulta(LocalDateTime fechaHoraReserva, double costoReserva) {
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        long horasDiferencia = ChronoUnit.HOURS.between(fechaHoraActual, fechaHoraReserva);
        
        double multa = 0.0;
        
        if (horasDiferencia < 24) {
            multa = costoReserva * 0.5; // 50% si falta menos de 24 horas
        } else if (horasDiferencia < 48) {
            multa = costoReserva * 0.25; // 25% si falta entre 24 y 48 horas
        } else {
            multa = 0.0; // Sin multa si falta más de 48 horas
        }
        return multa;
    }
    /**
     * Limpia el contenido de una tabla especificada eliminando todas sus filas.
     *
     * @param model El modelo de tabla (DefaultTableModel) al que se le eliminarán las filas.
     */
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    /**
     * Carga los datos de cancelaciones en un modelo de tabla para su visualización.
     * Recupera las cancelaciones desde el DAO y añade cada una como una fila en la tabla.
     *
     * @param model El modelo de tabla (DefaultTableModel) donde se cargarán las cancelaciones.
     */
    public void cargarTablaCancelaciones(DefaultTableModel model){
        
        java.util.List<Cancelacion> listaCancelaciones = cancelacionDAO.buscarCancelaciones();
        
        for (Cancelacion cancelaciones : listaCancelaciones) {
        model.addRow(new Object[]{
            cancelaciones.getReserva().getCliente().getNombre(),
            cancelaciones.getReserva().getCliente().getTelefono(),
            cancelaciones.getReserva().getMesa().getCodigo(),
            cancelaciones.getFecha(),
            cancelaciones.getMulta(),
            });
        }
    }
}
