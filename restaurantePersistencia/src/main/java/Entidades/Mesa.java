/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * La clase Mesa representa una mesa en el sistema de reservas.
 * Implementa Serializable para permitir la serialización de objetos de esta clase.
 */
@Entity
@Table(name="Mesas")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "mesa_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private Integer capacidad;
    private String ubicacion;
    private String codigo;
    
    /**
     * Constructor vacío para crear una instancia de Mesa.
     */
    public Mesa() {
    }
    /**
     * Constructor que inicializa todos los campos de la clase Mesa.
     *
     * @param id        ID de la mesa.
     * @param tipo      Tipo de mesa (ejemplo: "Individual", "Grupal").
     * @param capacidad Capacidad de la mesa (número de personas que puede acomodar).
     * @param ubicacion Ubicación de la mesa en el restaurante.
     * @param codigo    Código identificador de la mesa.
     */
    public Mesa(Long id, String tipo, Integer capacidad, String ubicacion, String codigo) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.codigo = codigo;
    }
    /**
     * Constructor que inicializa la mesa sin ID.
     *
     * @param tipo      Tipo de mesa (ejemplo: "Individual", "Grupal").
     * @param capacidad Capacidad de la mesa (número de personas que puede acomodar).
     * @param ubicacion Ubicación de la mesa en el restaurante.
     * @param codigo    Código identificador de la mesa.
     */
    public Mesa(String tipo, Integer capacidad, String ubicacion, String codigo) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.codigo = codigo;
    }
    /**
     * Obtiene el ID de la mesa.
     *
     * @return ID de la mesa.
     */
    public Long getId() {
        return id;
    }
    /**
     * Establece el ID de la mesa.
     *
     * @param id ID de la mesa a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Obtiene el tipo de mesa.
     *
     * @return Tipo de mesa.
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Establece el tipo de mesa.
     *
     * @param tipo Tipo de mesa a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Obtiene la capacidad de la mesa.
     *
     * @return Capacidad de la mesa.
     */
    public Integer getCapacidad() {
        return capacidad;
    }
    /**
     * Establece la capacidad de la mesa.
     *
     * @param capacidad Capacidad de la mesa a establecer.
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    /**
     * Obtiene la ubicación de la mesa.
     *
     * @return Ubicación de la mesa.
     */
    public String getUbicacion() {
        return ubicacion;
    }
    /**
     * Establece la ubicación de la mesa.
     *
     * @param ubicacion Ubicación de la mesa a establecer.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    /**
     * Obtiene el código identificador de la mesa.
     *
     * @return Código de la mesa.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Establece el código identificador de la mesa.
     *
     * @param codigo Código de la mesa a establecer.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Calcula el código hash de la mesa basándose en su ID.
     *
     * @return Código hash de la mesa.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compara esta mesa con otra objeto para verificar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Retorna una representación en forma de cadena de la instancia de Mesa.
     *
     * @return Cadena con los datos de la mesa.
     */
    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", tipo=" + tipo + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + ", codigo=" + codigo + '}';
    }
    
    
    
    
}
