package Dto;

import java.time.LocalDateTime;

public class ConsultaDTO {
    private Long id; // ID de la consulta
    private String nombreCliente; // Nombre del cliente
    private String telefonoCliente; // Teléfono del cliente
    private LocalDateTime fechaReserva; // Fecha y hora de la reserva
    private Double costoReserva; // Costo de la reserva
    private String mesa; // Información sobre la mesa

    // Constructor
    public ConsultaDTO(Long id, String nombreCliente, String telefonoCliente, LocalDateTime fechaReserva, Double costoReserva, String mesa) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.fechaReserva = fechaReserva;
        this.costoReserva = costoReserva;
        this.mesa = mesa;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }
}

