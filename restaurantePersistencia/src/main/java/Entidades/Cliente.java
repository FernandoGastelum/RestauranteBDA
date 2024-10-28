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
 * La clase Cliente representa a un cliente en el sistema de reservas.
 * Implementa Serializable para permitir la serialización de objetos de esta clase.
 */
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(nullable = false)
    private String telefono;  
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Reserva> reservas;
    /**
     * Constructor vacío para crear una instancia de Cliente.
     */
    public Cliente() {
    }
    /**
     * Constructor que inicializa todos los campos de la clase Cliente.
     *
     * @param id       ID del cliente.
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     * @param reservas Lista de reservas asociadas al cliente.
     */
    public Cliente(Long id, String nombre, String telefono, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.reservas = reservas;
    }
    /**
     * Constructor que inicializa el nombre, teléfono y reservas sin ID de cliente.
     *
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     * @param reservas Lista de reservas asociadas al cliente.
     */
    public Cliente(String nombre, String telefono, List<Reserva> reservas) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.reservas = reservas;
    }
    /**
     * Constructor que inicializa el ID, nombre y teléfono del cliente.
     *
     * @param id       ID del cliente.
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     */
    public Cliente(Long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    /**
     * Constructor que inicializa el nombre y teléfono del cliente sin ID.
     *
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     */
    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente.
     */
    public Long getId() {
        return id;
    }
    /**
     * Establece el ID del cliente.
     *
     * @param id ID del cliente a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre del cliente.
     *
     * @param nombre Nombre del cliente a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene el teléfono del cliente.
     *
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono Teléfono del cliente a establecer.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Obtiene la lista de reservas asociadas al cliente.
     *
     * @return Lista de reservas.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }
    /**
     * Establece la lista de reservas asociadas al cliente.
     *
     * @param reservas Lista de reservas a establecer.
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    /**
     * Calcula el código hash del cliente basándose en su ID.
     *
     * @return Código hash del cliente.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compara este cliente con otro objeto para verificar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Retorna una representación en forma de cadena de la instancia de Cliente.
     *
     * @return Cadena con los datos del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", reservas=" + reservas + '}';
    }
    
    
    
}
