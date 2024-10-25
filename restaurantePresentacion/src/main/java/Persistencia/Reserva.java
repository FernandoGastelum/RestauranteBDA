/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import com.github.lgooddatepicker.components.DateTimePicker;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author gaspa
 */
@Entity
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double costo;
    private boolean estado;
    private LocalDateTime fechaHora;
    private int numeroPersonas;
    private String lugar;
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa;

    public Reserva(Long id, double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, Cliente cliente, Mesa mesa) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Reserva(double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, Cliente cliente) {
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
    }

    public Reserva(Long id, double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas) {
        this.id = id;
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
    }

    public Reserva(double costo, boolean estado, LocalDateTime fechaHora, int numeroPersonas, String lugar, Cliente cliente, Mesa mesa) {
        this.costo = costo;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.numeroPersonas = numeroPersonas;
        this.cliente = cliente;
        this.mesa = mesa;
        this.lugar = lugar;
    }
    
    

    

    public Reserva() {
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "Persistencia.Reserva[ id=" + id + " ]";
    }
    
}
