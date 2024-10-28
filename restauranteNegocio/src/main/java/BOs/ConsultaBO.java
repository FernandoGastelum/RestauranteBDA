package BOs;

import DAO.ReservaDAO;
import Entidades.Reserva;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ConsultaBO {
    private ReservaDAO reservaDAO;

    public ConsultaBO() {
        this.reservaDAO = new ReservaDAO();
    }

    public void buscarHistorial(DefaultTableModel model, String nombre, String telefono, LocalDate fechaInicio, LocalDate fechaFin, String area, String tipoMesa) {
        // Obtiene las reservas que coinciden con los criterios de búsqueda
        List<Reserva> listaReservas = reservaDAO.obtenerReservasReportes(fechaInicio, fechaFin, tipoMesa, area);

        // Limpia la tabla antes de cargar nuevos resultados
        limpiarTabla(model);

        // Carga las reservas en el modelo de la tabla
        for (Reserva reserva : listaReservas) {
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

    public void limpiarTabla(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
}

