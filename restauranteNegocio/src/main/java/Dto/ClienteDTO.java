/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

import Entidades.Cliente;




/**
 *
 * @author gaspa
 */
public class ClienteDTO {
    private long id;
    private String nombre;
    private String telefono;
    private Cliente cliente;

    public ClienteDTO() {
    }

    public ClienteDTO( String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public ClienteDTO(long id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
