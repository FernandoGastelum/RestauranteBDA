/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import Entidades.Reserva;
import java.time.LocalDate;

/**
 *
 * @author gaspa
 */
public class CancelacionDTO {
    private Long idCancelacion;
    private LocalDate fecha;
    private Double multa;
    private Reserva reserva;  

    // Constructor
    public CancelacionDTO(Long idCancelacion, LocalDate fecha, Double multa, Reserva reserva) {
        this.idCancelacion = idCancelacion;
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }

    public CancelacionDTO() {
    }

    public CancelacionDTO(LocalDate fecha, Double multa, Reserva reserva) {
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    
    

    // Getters y Setters
    public Long getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(Long idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

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
