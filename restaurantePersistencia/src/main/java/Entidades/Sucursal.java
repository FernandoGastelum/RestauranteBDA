/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La clase Sucursal representa una sucursal de un restaurante.
 * Implementa Serializable para permitir la serialización de objetos de esta clase.
 */
@Entity
@Table(name="Sucursales")
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "sucursal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "hora_apertura")
    private LocalTime horaApertura;
    @Column(name = "hora_cierre")
    private LocalTime horaCierre;
    /**
     * Constructor vacío para crear una instancia de Sucursal.
     */
    public Sucursal() {
    }
    /**
     * Constructor que inicializa todos los campos de la clase Sucursal.
     *
     * @param id             ID de la sucursal.
     * @param nombre         Nombre de la sucursal.
     * @param horaApertura   Hora de apertura de la sucursal.
     * @param horaCierre     Hora de cierre de la sucursal.
     */
    public Sucursal(Long id, String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    /**
     * Constructor que inicializa todos los campos excepto el ID de la sucursal.
     *
     * @param nombre         Nombre de la sucursal.
     * @param horaApertura   Hora de apertura de la sucursal.
     * @param horaCierre     Hora de cierre de la sucursal.
     */
    public Sucursal(String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }
    
    /**
     * Obtiene el nombre de la sucursal.
     *
     * @return Nombre de la sucursal.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de la sucursal.
     *
     * @param nombre Nombre de la sucursal a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene la hora de apertura de la sucursal.
     *
     * @return Hora de apertura de la sucursal.
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }
    /**
     * Establece la hora de apertura de la sucursal.
     *
     * @param horaApertura Hora de apertura a establecer.
     */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }
    /**
     * Obtiene la hora de cierre de la sucursal.
     *
     * @return Hora de cierre de la sucursal.
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }
    /**
     * Establece la hora de cierre de la sucursal.
     *
     * @param horaCierre Hora de cierre a establecer.
     */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }
    /**
     * Obtiene el ID de la sucursal.
     *
     * @return ID de la sucursal.
     */
    public Long getId() {
        return id;
    }
    /**
     * Establece el ID de la sucursal.
     *
     * @param id ID de la sucursal a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Calcula el código hash de la sucursal basándose en su ID.
     *
     * @return Código hash de la sucursal.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compara esta sucursal con otra objeto para verificar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Retorna una representación en forma de cadena de la instancia de Sucursal.
     *
     * @return Cadena con los datos de la sucursal.
     */
    @Override
    public String toString() {
        return "Entidades.Sucursal[ id=" + id + " ]";
    }
    
}
