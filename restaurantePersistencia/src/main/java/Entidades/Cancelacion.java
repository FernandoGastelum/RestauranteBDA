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
 * La clase Cancelacion representa una entidad que almacena información sobre
 * una cancelación en el sistema de reservas. Implementa Serializable para permitir
 * la serialización de objetos de esta clase.
 */
@Entity
@Table(name = "cancelaciones")
public class Cancelacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cancelacion_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate fecha;
    private Double multa;
    @OneToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "reserva_id")
    private Reserva reserva;
    /**
     * Constructor vacío para crear una instancia de Cancelacion.
     */
    public Cancelacion() {
    }
    /**
     * Constructor que inicializa todos los campos de la clase Cancelacion.
     *
     * @param id      ID de la cancelación.
     * @param fecha   Fecha de la cancelación.
     * @param multa   Monto de la multa asociada a la cancelación.
     * @param reserva Reserva asociada a la cancelación.
     */
    public Cancelacion(Long id, LocalDate fecha, Double multa, Reserva reserva) {
        this.id = id;
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    /**
     * Constructor que inicializa los campos fecha, multa y reserva sin ID de cancelación.
     *
     * @param fecha   Fecha de la cancelación.
     * @param multa   Monto de la multa asociada a la cancelación.
     * @param reserva Reserva asociada a la cancelación.
     */
    public Cancelacion(LocalDate fecha, Double multa, Reserva reserva) {
        this.fecha = fecha;
        this.multa = multa;
        this.reserva = reserva;
    }
    /**
     * Obtiene la fecha de la cancelación.
     *
     * @return Fecha de la cancelación.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha de la cancelación.
     *
     * @param fecha Fecha de la cancelación a establecer.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
     * Obtiene el monto de la multa asociada a la cancelación.
     *
     * @return Monto de la multa.
     */
    public Double getMulta() {
        return multa;
    }
    /**
     * Establece el monto de la multa asociada a la cancelación.
     *
     * @param multa Monto de la multa a establecer.
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }
    /**
     * Obtiene la reserva asociada a la cancelación.
     *
     * @return Reserva asociada a la cancelación.
     */
    public Reserva getReserva() {
        return reserva;
    }
    /**
     * Establece la reserva asociada a la cancelación.
     *
     * @param reserva Reserva a establecer.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    /**
     * Obtiene el ID de la cancelación.
     *
     * @return ID de la cancelación.
     */    
    public Long getId() {
        return id;
    }
    /**
     * Establece el ID de la cancelación.
     *
     * @param id ID de la cancelación a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Calcula el código hash de la cancelación basándose en su ID.
     *
     * @return Código hash de la cancelación.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * Compara esta cancelación con otro objeto para verificar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
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
    /**
     * Retorna una representación en forma de cadena de la instancia de Cancelacion.
     *
     * @return Cadena con los datos de la cancelación.
     */
    @Override
    public String toString() {
        return "Cancelacion{" + "id=" + id + ", fecha=" + fecha + ", multa=" + multa + ", reserva=" + reserva + '}';
    }
    
    
}
