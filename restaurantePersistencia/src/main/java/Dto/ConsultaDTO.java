/**
 * Clase ConsultaDTO
 *
 * Esta clase representa un objeto de transferencia de datos (Data Transfer Object - DTO)
 * para una consulta de reservas. Se utiliza para transferir la información relevante de
 * una reserva desde la capa de acceso a datos o la capa de negocio hacia la capa de
 * presentación, asegurando que sólo los datos necesarios se expongan.
 */
package Dto;
 
import java.time.LocalDateTime;

public class ConsultaDTO {

    private Long id;                 // ID de la consulta
    private String nombreCliente;     // Nombre del cliente que realizó la reserva
    private String telefonoCliente;   // Teléfono del cliente
    private LocalDateTime fechaReserva; // Fecha y hora de la reserva
    private Double costoReserva;      // Costo total de la reserva
    private String mesa;              // Información sobre la mesa reservada (tipo y ubicación)

    /**
     * Constructor de ConsultaDTO.
     *
     * @param id Identificador único de la consulta o reserva.
     * @param nombreCliente Nombre completo del cliente.
     * @param telefonoCliente Número de teléfono del cliente.
     * @param fechaReserva Fecha y hora en la que se realizó la reserva.
     * @param costoReserva Costo total de la reserva.
     * @param mesa Información descriptiva sobre la mesa reservada.
     */
    public ConsultaDTO(Long id, String nombreCliente, String telefonoCliente, LocalDateTime fechaReserva, Double costoReserva, String mesa) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.fechaReserva = fechaReserva;
        this.costoReserva = costoReserva;
        this.mesa = mesa;
    }

    // Métodos Getters y Setters
    /**
     * Obtiene el ID de la consulta o reserva.
     *
     * @return ID de la consulta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la consulta o reserva.
     *
     * @param id Nuevo ID de la consulta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombreCliente Nuevo nombre del cliente.
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return Número de teléfono del cliente.
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param telefonoCliente Nuevo número de teléfono del cliente.
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * Obtiene la fecha y hora de la reserva.
     *
     * @return Fecha y hora de la reserva.
     */
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha y hora de la reserva.
     *
     * @param fechaReserva Nueva fecha y hora de la reserva.
     */
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Obtiene el costo total de la reserva.
     *
     * @return Costo total de la reserva.
     */
    public Double getCostoReserva() {
        return costoReserva;
    }

    /**
     * Establece el costo total de la reserva.
     *
     * @param costoReserva Nuevo costo total de la reserva.
     */
    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }

    /**
     * Obtiene la descripción de la mesa reservada (tipo y ubicación).
     *
     * @return Información de la mesa reservada.
     */
    public String getMesa() {
        return mesa;
    }

    /**
     * Establece la descripción de la mesa reservada.
     *
     * @param mesa Nueva información de la mesa reservada.
     */
    public void setMesa(String mesa) {
        this.mesa = mesa;
    }
}
