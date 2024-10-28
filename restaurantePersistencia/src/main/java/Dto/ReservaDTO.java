/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * La clase ReservaDTO es un objeto de transferencia de datos (DTO) que representa una reserva en el sistema.
 * Se utiliza para transferir información sobre una reserva entre diferentes capas de la aplicación.
 */
public class ReservaDTO {
    private Long idCliente;
    private Long idMesa;
    private LocalDateTime fechaHora;
    private Integer numPersonas;
    private String lugar;
    private Double costo;
    /**
     * Constructor que inicializa todos los campos de ReservaDTO.
     *
     * @param idCliente  El ID del cliente que realiza la reserva.
     * @param idMesa     El ID de la mesa reservada.
     * @param fechaHora  La fecha y hora de la reserva.
     * @param numPersonas El número de personas para la reserva.
     * @param costo      El costo total de la reserva.
     * @param lugar      El lugar de la reserva.
     */
    public ReservaDTO(Long idCliente, Long idMesa, LocalDateTime fechaHora, Integer numPersonas, Double costo, String lugar) {
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaHora = fechaHora;
        this.numPersonas = numPersonas;
        this.costo = costo;
        this.lugar = lugar;
    }
    /**
     * Constructor que inicializa los campos relevantes de ReservaDTO, excluyendo los IDs.
     *
     * @param fechaHora  La fecha y hora de la reserva.
     * @param numPersonas El número de personas para la reserva.
     * @param costo      El costo total de la reserva.
     * @param lugar      El lugar de la reserva.
     */
    public ReservaDTO(LocalDateTime fechaHora, Integer numPersonas, Double costo, String lugar) {
        this.fechaHora = fechaHora;
        this.numPersonas = numPersonas;
        this.costo = costo;
        this.lugar = lugar;
    }
    /**
     * Obtiene el lugar de la reserva.
     *
     * @return El lugar de la reserva.
     */
    public String getLugar() {
        return lugar;
    }
    /**
     * Establece el lugar de la reserva.
     *
     * @param lugar El lugar de la reserva a establecer.
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    /**
     * Obtiene el ID del cliente que realiza la reserva.
     *
     * @return El ID del cliente.
     */
    public Long getIdCliente() {
        return idCliente;
    }
    /**
     * Establece el ID del cliente que realiza la reserva.
     *
     * @param idCliente El ID del cliente a establecer.
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    /**
     * Obtiene el ID de la mesa reservada.
     *
     * @return El ID de la mesa.
     */
    public Long getIdMesa() {
        return idMesa;
    }
    /**
     * Establece el ID de la mesa reservada.
     *
     * @param idMesa El ID de la mesa a establecer.
     */
    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }
    /**
     * Obtiene la fecha y hora de la reserva.
     *
     * @return La fecha y hora de la reserva.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    /**
     * Establece la fecha y hora de la reserva.
     *
     * @param fechaHora La fecha y hora de la reserva a establecer.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    /**
     * Obtiene el número de personas para la reserva.
     *
     * @return El número de personas.
     */
    public Integer getNumPersonas() {
        return numPersonas;
    }
    /**
     * Establece el número de personas para la reserva.
     *
     * @param numPersonas El número de personas a establecer.
     */
    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }
    /**
     * Obtiene el costo total de la reserva.
     *
     * @return El costo total de la reserva.
     */
    public Double getCosto() {
        return costo;
    }
    /**
     * Establece el costo total de la reserva.
     *
     * @param costo El costo total de la reserva a establecer.
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }
    
}


