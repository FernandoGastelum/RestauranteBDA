/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * La clase SucursalDTO es un objeto de transferencia de datos (DTO) que representa una sucursal en el sistema.
 * Se utiliza para transferir información sobre una sucursal entre diferentes capas de la aplicación.
 */
public class SucursalDTO {
    private int id;
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    /**
     * Constructor que inicializa todos los campos de SucursalDTO.
     *
     * @param id          El identificador de la sucursal.
     * @param nombre      El nombre de la sucursal.
     * @param horaApertura La hora de apertura de la sucursal.
     * @param horaCierre  La hora de cierre de la sucursal.
     */
    public SucursalDTO(int id, String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    /**
     * Constructor que inicializa la sucursal con nombre, hora de apertura y hora de cierre, sin ID.
     *
     * @param nombre      El nombre de la sucursal.
     * @param horaApertura La hora de apertura de la sucursal.
     * @param horaCierre  La hora de cierre de la sucursal.
     */
    public SucursalDTO(String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    /**
     * Constructor vacío de SucursalDTO.
     */
    public SucursalDTO() {
    }
    
    // Métodos getter y setter
    
    /**
     * Obtiene el identificador de la sucursal.
     *
     * @return El identificador de la sucursal.
     */
    public int getId() {
        return id;
    }
    /**
     * Establece el identificador de la sucursal.
     *
     * @param id El identificador de la sucursal.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Obtiene el nombre de la sucursal.
     *
     * @return El nombre de la sucursal.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de la sucursal.
     *
     * @param nombre El nombre de la sucursal.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene la hora de apertura de la sucursal.
     *
     * @return La hora de apertura de la sucursal.
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }
    /**
     * Establece la hora de apertura de la sucursal.
     *
     * @param horaApertura La hora de apertura de la sucursal.
     */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }
    /**
     * Obtiene la hora de cierre de la sucursal.
     *
     * @return La hora de cierre de la sucursal.
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }
    /**
     * Establece la hora de cierre de la sucursal.
     *
     * @param horaCierre La hora de cierre de la sucursal.
     */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }
    /**
     * Genera una representación en cadena de la sucursal, mostrando su ID, nombre, hora de apertura y hora de cierre.
     *
     * @return Una cadena de texto que representa la sucursal.
     */
    @Override
    public String toString() {
        return "SucursalDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horaApertura='" + horaApertura + '\'' +
                ", horaCierre='" + horaCierre + '\'' +
                '}';
    }
}
