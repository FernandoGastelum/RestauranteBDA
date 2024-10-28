/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
* La clase MesaDTO es un objeto de transferencia de datos (DTO) que representa una mesa en el sistema.
* Se utiliza para transferir información sobre una mesa entre diferentes capas de la aplicación.
*/
public class MesaDTO {
    private int capacidad;
    private String codigo;
    private String tipo;
    private String ubicacion;
    /**
     * Constructor que inicializa todos los campos de MesaDTO.
     *
     * @param capacidad La capacidad de la mesa.
     * @param codigo    El código de identificación de la mesa.
     * @param tipo      El tipo de la mesa.
     * @param ubicacion La ubicación de la mesa.
     */
    public MesaDTO(int capacidad, String codigo, String tipo, String ubicacion) {
        this.capacidad = capacidad;
        this.codigo = codigo;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }
    /**
     * Obtiene la capacidad de la mesa.
     *
     * @return La capacidad de la mesa.
     */
    public int getCapacidad() {
        return capacidad;
    }
    /**
     * Establece la capacidad de la mesa.
     *
     * @param capacidad La capacidad de la mesa a establecer.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    /**
     * Obtiene el código de la mesa.
     *
     * @return El código de la mesa.
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Establece el código de la mesa.
     *
     * @param codigo El código de la mesa a establecer.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Obtiene el tipo de la mesa.
     *
     * @return El tipo de la mesa.
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Establece el tipo de la mesa.
     *
     * @param tipo El tipo de la mesa a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Obtiene la ubicación de la mesa.
     *
     * @return La ubicación de la mesa.
     */
    public String getUbicacion() {
        return ubicacion;
    }
    /**
     * Establece la ubicación de la mesa.
     *
     * @param ubicacion La ubicación de la mesa a establecer.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}
