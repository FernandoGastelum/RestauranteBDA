/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import com.github.lgooddatepicker.components.DateTimePicker;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author gaspa
 */
public class ReservaDTO {
    private Long idCliente;
    private Long idMesa;
    private LocalDateTime fechaHora;
    private Integer numPersonas;
    private Double costo;
    
    public ReservaDTO(Long idCliente, Long idMesa, LocalDateTime fechaHora, Integer numPersonas, Double costo) {
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaHora = fechaHora;
        this.numPersonas = numPersonas;
        this.costo = costo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
}


