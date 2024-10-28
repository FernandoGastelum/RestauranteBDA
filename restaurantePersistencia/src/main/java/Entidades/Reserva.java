/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
/**
 * La clase Reserva representa una reserva en el sistema de gestión de un restaurante.
 * Implementa Serializable para permitir la serialización de objetos de esta clase.
 */
@Entity
@Table(name="Reservas")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "reserva_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double costo;
    private boolean estado;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    @Column(name = "numero_personas")
    private int numeroPersonas;
    private String lugar;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;
    /**
     * Constructor que inicializa todos los campos de la clase Reserva.
     *
     * @param id             ID de la reserva.
     * @param costo          Costo de la reserva.
     * @param estado         Estado de la reserva (confirmada/cancelada).
     * @param fechaHora      Fecha y hora de la reserva.
     * @param numeroPersonas Número de personas para la reserva.
     * @param cliente        Cliente asociado a la reserva.
     * @param mesa           Mesa asociada a la reserva.
     */
    public Reserva(Long id, double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, Cliente cliente, Mesa mesa) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
        this.mesa = mesa;
    }
    /**
     * Constructor que inicializa todos los campos excepto el ID de la reserva.
     *
     * @param costo          Costo de la reserva.
     * @param estado         Estado de la reserva (confirmada/cancelada).
     * @param fechaHora      Fecha y hora de la reserva.
     * @param numeroPersonas Número de personas para la reserva.
     * @param cliente        Cliente asociado a la reserva.
     */
    public Reserva(double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, Cliente cliente) {
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
    }
    /**
     * Constructor que inicializa todos los campos menos el cliente y la mesa.
     *
     * @param id             ID de la reserva.
     * @param costo          Costo de la reserva.
     * @param estado         Estado de la reserva (confirmada/cancelada).
     * @param fechaHora      Fecha y hora de la reserva.
     * @param numeroPersonas Número de personas para la reserva.
     */
    public Reserva(Long id, double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
    }
    /**
     * Constructor que inicializa todos los campos, incluyendo el lugar.
     *
     * @param costo          Costo de la reserva.
     * @param estado         Estado de la reserva (confirmada/cancelada).
     * @param fechaHora      Fecha y hora de la reserva.
     * @param numeroPersonas Número de personas para la reserva.
     * @param lugar          Lugar de la reserva.
     * @param cliente        Cliente asociado a la reserva.
     * @param mesa           Mesa asociada a la reserva.
     */
    public Reserva(double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, String lugar, Cliente cliente, Mesa mesa) {
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
        this.mesa = mesa;
        this.lugar = lugar;
    }
    /**
     * Constructor vacío para crear una instancia de Reserva.
     */
    public Reserva() {
    }
    /**
     * Obtiene el lugar de la reserva.
     *
     * @return Lugar de la reserva.
     */
    public String getLugar() {
        return lugar;
    }
    /**
     * Establece el lugar de la reserva.
     *
     * @param lugar Lugar de la reserva a establecer.
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    /**
     * Obtiene el cliente asociado a la reserva.
     *
     * @return Cliente asociado a la reserva.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Establece el cliente asociado a la reserva.
     *
     * @param cliente Cliente a establecer.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtiene la mesa asociada a la reserva.
     *
     * @return Mesa asociada a la reserva.
     */
    public Mesa getMesa() {
        return mesa;
    }
    /**
     * Establece la mesa asociada a la reserva.
     *
     * @param mesa Mesa a establecer.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    /**
     * Obtiene el costo de la reserva.
     *
     * @return Costo de la reserva.
     */
    public double getCosto() {
        return costo;
    }
    /**
     * Establece el costo de la reserva.
     *
     * @param costo Costo de la reserva a establecer.
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }
    /**
     * Obtiene el estado de la reserva.
     *
     * @return Estado de la reserva.
     */
    public boolean isEstado() {
        return estado;
    }
    /**
     * Establece el estado de la reserva.
     *
     * @param estado Estado de la reserva a establecer.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    /**
     * Obtiene la fecha y hora de la reserva.
     *
     * @return Fecha y hora de la reserva.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    /**
     * Establece la fecha y hora de la reserva.
     *
     * @param fechaHora Fecha y hora de la reserva a establecer.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    /**
     * Obtiene el número de personas de la reserva.
     *
     * @return Número de personas en la reserva.
     */
    public int getNumeroPersonas() {
        return numeroPersonas;
    }
    /**
     * Establece el número de personas de la reserva.
     *
     * @param numeroPersonas Número de personas a establecer.
     */
    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }   
    /**
     * Obtiene el ID de la reserva.
     *
     * @return ID de la reserva.
     */
    public Long getId() {
        return id;
    }
    /**
     * Establece el ID de la reserva.
     *
     * @param id ID de la reserva a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Calcula el código hash de la reserva basándose en su ID.
     *
     * @return Código hash de la reserva.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compara esta reserva con otra objeto para verificar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * Retorna una representación en forma de cadena de la instancia de Reserva.
     *
     * @return Cadena con los datos de la reserva.
     */
    @Override
    public String toString() {
        return "Persistencia.Reserva[ id=" + id + " ]";
    }
    
}
