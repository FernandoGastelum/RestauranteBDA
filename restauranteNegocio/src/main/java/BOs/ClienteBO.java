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
 * La clase ClienteBO representa la capa de negocio para las operaciones relacionadas con clientes en el sistema.
 * Proporciona métodos para crear, buscar, y cargar clientes, además de otras funciones auxiliares.
 */
public class ClienteBO {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EntityManager em;
    /**
     * Constructor vacío para la clase ClienteBO.
     */
    public ClienteBO() {
    }
    /**
     * Persiste un nuevo cliente en la base de datos.
     *
     * @param cliente El cliente a ser persistido.
     */
    public void crearCliente(Cliente cliente) {
        em.persist(cliente);
    }
    /**
     * Busca un cliente en la base de datos mediante su número de teléfono.
     *
     * @param telefono El número de teléfono del cliente.
     * @return El cliente correspondiente al número de teléfono, o null si no se encuentra.
     */
    public Cliente buscarClientePorTelefono(String telefono) {
        return clienteDAO.buscarClientePorTelefono(telefono);
    }
    /**
     * Obtiene todos los clientes de la base de datos y los convierte en una lista de ClienteDTO.
     *
     * @return Una lista de objetos ClienteDTO que representan a los clientes.
     */
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
    /**
     * Limpia todas las filas de un modelo de tabla.
     *
     * @param model El modelo de tabla (DefaultTableModel) del cual se eliminarán las filas.
     */
    public void limpiarTabla(DefaultTableModel model){
        for (int i = 0; i < model.getRowCount() ; i++) {
            model.removeRow(i);
            i=i-1;
        }
    }
    /**
     * Carga los datos de todos los clientes en un modelo de tabla para su visualización.
     *
     * @param model El modelo de tabla (DefaultTableModel) donde se cargarán los clientes.
     */
    public void cargarTablaClientes(DefaultTableModel model){
        java.util.List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        
        for (Cliente clientes : listaClientes) {
        model.addRow(new Object[]{
            clientes.getNombre(),
            clientes.getTelefono()
            });
        }
    }
    /**
     * Genera una lista de clientes de muestra en formato ClienteDTO.
     *
     * @return Una lista de objetos ClienteDTO con datos ficticios.
     */
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
    /**
     * Registra una lista de clientes en la base de datos.
     *
     * @param clientes La lista de clientes a registrar.
     * @throws Exception Si ocurre un error durante el proceso de registro.
     */
    public void registrarClientes(List<ClienteDTO> clientes) throws Exception {
        for (ClienteDTO clienteDTO : clientes) {
            clienteDAO.registrarCliente(clienteDTO);
        }
    }
    
}
