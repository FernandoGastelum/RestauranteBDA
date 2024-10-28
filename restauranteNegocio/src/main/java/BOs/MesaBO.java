package BOs;

import Dto.MesaDTO;
import Entidades.Mesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DAO.MesaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 * La clase MesaBO representa la capa de negocio para las operaciones relacionadas con las mesas en el sistema.
 * Proporciona métodos para generar mesas, asignar capacidades, generar códigos y cargar datos en tablas.
 */
public class MesaBO {

    private MesaDAO mesaDAO;
    /**
     * Constructor por defecto que inicializa una instancia de MesaDAO.
     */
    public MesaBO() {
        this.mesaDAO = new MesaDAO();
    }
    /**
     * Genera una cantidad específica de mesas de un tipo y ubicación determinados y las registra en la base de datos.
     *
     * @param cantidad La cantidad de mesas a generar.
     * @param tipo El tipo de mesa (pequeña, mediana, grande).
     * @param ubicacion La ubicación de las mesas.
     * @throws Exception Si ocurre un error durante el registro de las mesas.
     */
    public void generarMesas(int cantidad, String tipo, String ubicacion) throws Exception {
        int capacidad = asignarCapacidadMesa(tipo);
        for (int i = 1; i <= cantidad; i++) {

            String codigo = generarCodigoMesa(ubicacion, tipo);

            Mesa mesa = new Mesa(tipo, capacidad, ubicacion, codigo);
            mesaDAO.registrarMesa(mesa);
        }
    }
    /**
     * Genera un código único para la mesa basado en la ubicación, tipo y un número secuencial.
     *
     * @param ubicacion La ubicación de la mesa.
     * @param tipo El tipo de la mesa.
     * @return Un código único para identificar la mesa.
     */
    public String generarCodigoMesa(String ubicacion, String tipo) {
        // Paso 1: Determinar la capacidad de la mesa en función del tipo
        int capacidad = asignarCapacidadMesa(tipo);

        // Paso 2: Crear el código de ubicación usando los primeros 3 caracteres de la ubicación en mayúsculas
        String codigoUbicacion = ubicacion.substring(0, 3).toUpperCase();

        // Paso 3: Obtener el siguiente número único de mesa para esa ubicación y tipo
        int numeroMesa = obtenerSiguienteNumeroMesa(ubicacion, tipo);

        // Paso 4: Formatear el número de mesa en 3 dígitos y construir el código final
        String numeroMesaFormatted = String.format("%03d", numeroMesa);

        return codigoUbicacion + "-" + capacidad + "-" + numeroMesaFormatted;
    }
    /**
     * Obtiene el siguiente número de mesa único para una combinación de ubicación y tipo específicos.
     *
     * @param ubicacion La ubicación de la mesa.
     * @param tipo El tipo de la mesa.
     * @return El siguiente número de mesa único.
     */
    private int obtenerSiguienteNumeroMesa(String ubicacion, String tipo) {
        // Supongamos que tienes un DAO que accede a las mesas en la base de datos
        MesaDAO mesaDAO = new MesaDAO();

        // Obtener el último número de mesa asignado para esta ubicación y tipo
        Integer ultimoNumero = mesaDAO.obtenerUltimoNumeroMesa(ubicacion, tipo);

        // Si no hay un número previo, comenzamos con 1; si hay, lo incrementamos en 1
        return (ultimoNumero != null) ? ultimoNumero + 1 : 1;
    }
    /**
     * Asigna el tipo de mesa basado en el número de personas que puede acomodar.
     *
     * @param numeroPersonas El número de personas a acomodar.
     * @return El tipo de mesa (pequeña, mediana, grande).
     */
    public String asignarTipoMesa(int numeroPersonas){
        switch (numeroPersonas) {
            case 1,2:
                return "Pequeña";
            case 3,4:
                return "Mediana";
            case 5,6,7,8:
                return "Grande";
            default:
                throw new AssertionError();
        }
    }
    /**
     * Asigna la capacidad de una mesa basándose en su tipo.
     *
     * @param tipoMesa El tipo de la mesa.
     * @return La capacidad de la mesa (número de personas).
     */
    public int asignarCapacidadMesa(String tipoMesa){
        switch (tipoMesa) {
            case "Pequeña":
                return 2;
            case "Mediana":
                return 4;
            case "Grande":
                return 8;
            default:
                throw new AssertionError();
        }
    }
    /**
     * Elimina todas las filas de un modelo de tabla.
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
     * Carga los datos de todas las mesas en un modelo de tabla para su visualización.
     *
     * @param model El modelo de tabla (DefaultTableModel) donde se cargarán las mesas.
     */
    public void cargarTablaMesa(DefaultTableModel model){
        java.util.List<Mesa> listaMesas = mesaDAO.obtenerTodasLasMesas();
        
        for (Mesa mesas : listaMesas) {
        model.addRow(new Object[]{
            mesas.getCodigo(),
            mesas.getTipo(),
            mesas.getUbicacion()
            });
        }
    }
}
