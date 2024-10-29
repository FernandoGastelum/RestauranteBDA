/**
 * Clase ConsultaBO
 *
 * Esta clase pertenece a la capa de negocio (Business Object) y se encarga de gestionar
 * la lógica de negocio relacionada con la consulta del historial de reservas de clientes
 * en el sistema de reservas de mesas. Utiliza la clase ReservaDAO para interactuar con
 * la base de datos y obtener los datos de reservas según los filtros aplicados.
 */
package BOs;

import DAO.ReservaDAO;
import Entidades.Reserva;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ConsultaBO {

    private ReservaDAO reservaDAO;

    /**
     * Constructor de ConsultaBO.
     *
     * Inicializa una instancia de ReservaDAO para gestionar las operaciones de
     * consulta en la base de datos.
     */
    public ConsultaBO() {
        this.reservaDAO = new ReservaDAO();
    }

    /**
     * Método buscarHistorial
     *
     * Realiza una búsqueda en el historial de reservas de clientes en función
     * de los criterios de búsqueda proporcionados. Los resultados se muestran
     * en el modelo de la tabla proporcionado.
     *
     * @param model El modelo de la tabla (DefaultTableModel) donde se cargará
     * la información de las reservas.
     * @param nombre El nombre del cliente para filtrar los resultados. Puede
     * ser nulo o vacío si no se desea filtrar por nombre.
     * @param telefono El teléfono del cliente para filtrar los resultados.
     * Puede ser nulo o vacío si no se desea filtrar por teléfono.
     * @param fechaInicio La fecha de inicio del rango de búsqueda. Las reservas
     * antes de esta fecha no serán incluidas.
     * @param fechaFin La fecha de fin del rango de búsqueda. Las reservas
     * después de esta fecha no serán incluidas.
     * @param area La ubicación del área donde se realizó la reserva. Puede ser
     * nulo si no se desea filtrar por área.
     * @param tipoMesa El tipo de mesa (por ejemplo, "Pequeña", "Mediana",
     * "Grande") para filtrar los resultados. Puede ser nulo si no se desea
     * filtrar por tipo de mesa.
     */
    public void buscarHistorial(DefaultTableModel model, String nombre, String telefono, LocalDate fechaInicio, LocalDate fechaFin, String area, String tipoMesa) {
        // Obtiene las reservas que coinciden con los criterios de búsqueda
        List<Reserva> listaReservas = reservaDAO.obtenerReservasReportes(fechaInicio, fechaFin, tipoMesa, area);

        // Limpia la tabla antes de cargar nuevos resultados
        limpiarTabla(model);

        // Carga las reservas en el modelo de la tabla
        for (Reserva reserva : listaReservas) {
            // Filtra las reservas por el nombre del cliente
            if (nombre != null && !nombre.isEmpty() && !reserva.getCliente().getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                continue; // Salta si no coincide el nombre
            }
            if (telefono != null && !telefono.isEmpty() && !reserva.getCliente().getTelefono().equals(telefono)) {
                continue; // Salta si no coincide el teléfono
            }

            // Agrega la reserva al modelo de la tabla
            model.addRow(new Object[]{
                reserva.getCliente().getNombre(),
                reserva.getCliente().getTelefono(),
                reserva.getFechaHora(),
                reserva.getMesa().getTipo(),
                reserva.getMesa().getUbicacion(),
                reserva.getCosto(),
                // Verificar si existe una cancelación para la reserva
                reservaDAO.obtenerCancelacionPorReserva(reserva.getId()) != null ? "Sí" : "No"
            });
        }
    }

    /**
     * Método limpiarTabla
     *
     * Elimina todas las filas de la tabla proporcionada para dejarla vacía.
     * Este método se usa antes de cargar nuevos resultados de búsqueda en el
     * modelo de la tabla.
     *
     * @param model El modelo de la tabla (DefaultTableModel) que se limpiará.
     */
    public void limpiarTabla(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
}
