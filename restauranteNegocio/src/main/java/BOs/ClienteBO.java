/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAO.ClienteDAO;
import Dto.ClienteDTO;
import Entidades.Cliente;
import java.util.ArrayList;
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
    public List<ClienteDTO> generarClientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        
        
        clientes.add(new ClienteDTO("Juan Perez", "+52 55-1001-1234"));
        clientes.add(new ClienteDTO("Maria Gomez", "+52 55-1002-2345"));
        clientes.add(new ClienteDTO("Carlos Sanchez", "+52 55-1003-3456"));
        clientes.add(new ClienteDTO("Ana Lopez", "+52 55-1004-4567"));
        clientes.add(new ClienteDTO("Luis Fernandez", "+52 55-1005-5678"));
        clientes.add(new ClienteDTO("Sofia Morales", "+52 55-1006-6789"));
        clientes.add(new ClienteDTO("Miguel Castro", "+52 55-1007-7890"));
        clientes.add(new ClienteDTO("Daniela Rojas", "+52 55-1008-8901"));
        clientes.add(new ClienteDTO("Javier Mendez", "+52 55-1009-9012"));
        clientes.add(new ClienteDTO("Patricia Herrera", "+52 55-1010-0123"));
        clientes.add(new ClienteDTO("Raul Vargas", "+52 55-1011-1234"));
        clientes.add(new ClienteDTO("Lorena Diaz", "+52 55-1012-2345"));
        clientes.add(new ClienteDTO("Fernando Alvarez", "+52 55-1013-3456"));
        clientes.add(new ClienteDTO("Lucia Marin", "+52 55-1014-4567"));
        clientes.add(new ClienteDTO("Andres Pineda", "+52 55-1015-5678"));
        clientes.add(new ClienteDTO("Marta Ruiz", "+52 55-1016-6789"));
        clientes.add(new ClienteDTO("Ricardo Peña", "+52 55-1017-7890"));
        clientes.add(new ClienteDTO("Carmen Castillo", "+52 55-1018-8901"));
        clientes.add(new ClienteDTO("Francisco Ortiz", "+52 55-1019-9012"));
        clientes.add(new ClienteDTO("Veronica Chavez", "+52 55-1020-0123"));

        return clientes;
    }
    public void registrarClientes(List<ClienteDTO> clientes) throws Exception {
        for (ClienteDTO clienteDTO : clientes) {
            clienteDAO.registrarCliente(clienteDTO);
        }
    }
    
}
