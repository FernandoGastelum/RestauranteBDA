/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author gaspa
 */
public class SucursalDTO {
    private int id;
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    // Constructor
    public SucursalDTO(int id, String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public SucursalDTO(String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    
    

    public SucursalDTO() {
    }
    
    

    // Métodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

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
