/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gaspa
 */
@Entity
@Table(name = "cancelaciones")
public class Cancelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_cancelacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fecha;
    private Double multa;
    @OneToOne
    @JoinColumn(name = "id_reserva", referencedColumnName = "id")
    private Reserva reserva;

    public Cancelacion() {
    }

    public Cancelacion(Long id, LocalDate fecha, Double multa, Reserva reserva) {
        this.id = id;
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    
    

    public Cancelacion(LocalDate fecha, Double multa, Reserva reserva) {
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
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
        if (!(object instanceof Cancelacion)) {
            return false;
        }
        Cancelacion other = (Cancelacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cancelacion[ id=" + id + " ]";
    }
    
}