/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.ClienteDAO;
import Dto.ClienteDTO;
import Entidades.Cliente;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gaspa
 */
public class ClienteBO {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EntityManager em;

    public ClienteBO() {
    }

    public void crearCliente(Cliente cliente) {
        em.persist(cliente);
    }
    public Cliente buscarClientePorTelefono(String telefono) {
        return clienteDAO.buscarClientePorTelefono(telefono);
    }
    // Método para obtener todos los clientes en forma de DTO
    public List<ClienteDTO> cargarClientes() {
        // Obtener la lista de entidades Cliente desde el DAO
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Convertir cada Cliente en ClienteDTO
        return clientes.stream().map(cliente -> new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getTelefono()))
                .collect(Collectors.toList());
    }
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    public void cargarTablaClientes(DefaultTableModel model){
        java.util.List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        
        for (Cliente clientes : listaClientes) {
        model.addRow(new Object[]{
            clientes.getNombre(),
            clientes.getTelefono()
            });
        }
    }
    
}
