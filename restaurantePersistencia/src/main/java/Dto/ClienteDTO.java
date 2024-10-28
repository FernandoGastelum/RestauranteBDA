/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import Entidades.Cliente;




/**
 * La clase ClienteDTO es un objeto de transferencia de datos (DTO) que representa a un cliente.
 * Se utiliza para transferir datos de cliente entre diferentes capas de la aplicación.
 */
public class ClienteDTO {
    private long id;
    private String nombre;
    private String telefono;
    private Cliente cliente;
    /**
     * Constructor vacío para crear una instancia de ClienteDTO.
     */
    public ClienteDTO() {
    }
    /**
     * Constructor que inicializa el nombre y el teléfono del cliente.
     *
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     */
    public ClienteDTO( String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }
    /**
     * Constructor que inicializa todos los campos de ClienteDTO.
     *
     * @param id       ID del cliente.
     * @param nombre   Nombre del cliente.
     * @param telefono Teléfono del cliente.
     */
    public ClienteDTO(long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    /**
     * Obtiene el cliente asociado a este DTO.
     *
     * @return Cliente asociado.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Obtiene el ID del cliente.
     *
     * @return ID del cliente.
     */
    public long getId() {
        return id;
    }
    /**
     * Establece el ID del cliente.
     *
     * @param id ID del cliente a establecer.
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Establece el cliente asociado a este DTO.
     *
     * @param cliente Cliente a establecer.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
}
