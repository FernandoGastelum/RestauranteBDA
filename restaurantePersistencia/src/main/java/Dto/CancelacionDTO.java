/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import Entidades.Reserva;
import java.time.LocalDate;

/**
 * La clase CancelacionDTO es un Data Transfer Object (DTO) que encapsula la información
 * de una cancelación, incluyendo su ID, fecha, multa y reserva asociada.
 */
public class CancelacionDTO {
    private Long idCancelacion;
    private LocalDate fecha;
    private Double multa;
    private Reserva reserva;  

    /**
     * Constructor completo para crear una instancia de CancelacionDTO.
     *
     * @param idCancelacion ID de la cancelación.
     * @param fecha         Fecha de la cancelación.
     * @param multa         Monto de la multa asociada a la cancelación.
     * @param reserva       Reserva asociada a la cancelación.
     */
    public CancelacionDTO(Long idCancelacion, LocalDate fecha, Double multa, Reserva reserva) {
        this.idCancelacion = idCancelacion;
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    /**
     * Constructor vacío para crear una instancia de CancelacionDTO sin inicializar sus campos.
     */
    public CancelacionDTO() {
    }
    /**
     * Constructor que inicializa los campos fecha, multa y reserva sin ID de cancelación.
     *
     * @param fecha   Fecha de la cancelación.
     * @param multa   Monto de la multa asociada a la cancelación.
     * @param reserva Reserva asociada a la cancelación.
     */
    public CancelacionDTO(LocalDate fecha, Double multa, Reserva reserva) {
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    // Getters y Setters
    
    /**
     * Obtiene el ID de la cancelación.
     *
     * @return ID de la cancelación.
     */
    public Long getIdCancelacion() {
        return idCancelacion;
    }
    /**
     * Establece el ID de la cancelación.
     *
     * @param idCancelacion ID de la cancelación a establecer.
     */
    public void setIdCancelacion(Long idCancelacion) {
        this.idCancelacion = idCancelacion;
    }
    /**
     * Obtiene la fecha de la cancelación.
     *
     * @return Fecha de la cancelación.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha de la cancelación.
     *
     * @param fecha Fecha de la cancelación a establecer.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
     * Obtiene el monto de la multa asociada a la cancelación.
     *
     * @return Monto de la multa.
     */
    public Double getMulta() {
        return multa;
    }
    /**
     * Establece el monto de la multa asociada a la cancelación.
     *
     * @param multa Monto de la multa a establecer.
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }
    /**
     * Obtiene la reserva asociada a la cancelación.
     *
     * @return Reserva asociada a la cancelación.
     */
    public Reserva getReserva() {
        return reserva;
    }
    /**
     * Establece la reserva asociada a la cancelación.
     *
     * @param reserva Reserva a establecer.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    /**
     * Retorna una representación en forma de cadena de la instancia de CancelacionDTO.
     *
     * @return Cadena con los datos de la cancelación.
     */
    @Override
    public String toString() {
        return "CancelacionDTO{" +
                "idCancelacion=" + idCancelacion +
                ", fecha=" + fecha +
                ", multa=" + multa +
                ", reserva=" + reserva +
                '}';
    }
}
