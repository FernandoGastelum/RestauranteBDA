package DAO;

import BOs.MesaBO;
import Entidades.Mesa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 * La clase MesaDAO gestiona las operaciones de acceso a datos relacionadas con las mesas,
 * permitiendo realizar operaciones CRUD (crear, buscar y listar mesas) en la base de datos.
 * Utiliza JPA (Java Persistence API) para la interacción con la base de datos.
 */

public class MesaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;
    private MesaBO mesaBO;
    /**
     * Constructor de la clase MesaDAO. Inicializa el EntityManagerFactory para crear
     * instancias de EntityManager y gestionar la conexión a la base de datos.
     */
    public MesaDAO() {
        
        this.emf = Persistence.createEntityManagerFactory("pu_restaurante");
    }
    /**
     * Registra una nueva mesa en la base de datos.
     *
     * @param mesa Objeto de tipo Mesa que contiene la información de la mesa a registrar.
     * @throws Exception Si ocurre algún error durante la transacción de registro.
     */
    public void registrarMesa(Mesa mesa) throws Exception {
        EntityManager em = null;
        try {

            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(mesa);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * Busca una mesa en la base de datos por su código único.
     *
     * @param codigo El código único de la mesa a buscar.
     * @return Un objeto Mesa correspondiente al código proporcionado, o null si no se encuentra.
     */
    public Mesa buscarMesaPorCodigo(String codigo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m WHERE m.codigo = :codigo", Mesa.class);
        query.setParameter("codigo", codigo);
        return query.getSingleResult();
        
    }
    /**
     * Busca una mesa disponible en la base de datos según la capacidad y ubicación especificadas.
     *
     * @param capacidad  La capacidad de la mesa a buscar.
     * @param ubicacion  La ubicación de la mesa a buscar.
     * @return Una instancia de Mesa que coincida con los criterios de búsqueda, o null si no hay mesas disponibles.
     */
    public Mesa buscarMesaGeneral(int capacidad, String ubicacion) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();

        try {
            // Determina el tipo de mesa según la capacidad
            mesaBO = new MesaBO();
            String tipo = mesaBO.asignarTipoMesa(capacidad);

            // Consulta JPQL para encontrar la primera mesa disponible
            TypedQuery<Mesa> query = em.createQuery(
                "SELECT m FROM Mesa m " +
                "WHERE m.tipo = :tipo AND m.ubicacion = :ubicacion " +
                "AND NOT EXISTS (" +
                "    SELECT r FROM Reserva r " +
                "    WHERE r.mesa = m AND r.fechaHora > CURRENT_TIMESTAMP" +
                ")", Mesa.class);

            query.setParameter("tipo", tipo);
            query.setParameter("ubicacion", ubicacion);

            // Retorna el primer resultado disponible o null si no hay mesas disponibles
            return query.setMaxResults(1).getResultStream().findFirst().orElse(null);

        } finally {
            em.close();
            emf.close();
        }
    }
    /**
     * Obtiene una lista de todas las mesas registradas en la base de datos.
     *
     * @return Una lista de objetos Mesa que representan todas las mesas registradas. La lista estará vacía si no hay mesas.
     */
    public List<Mesa> obtenerTodasLasMesas() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String queryJpql = "SELECT m FROM Mesa m";
        TypedQuery query = em.createQuery(queryJpql, Mesa.class);
        List<Mesa> mesas = query.getResultList();
        em.getTransaction().commit();
        return mesas;
    }
    /**
     * Obtiene el número más alto de mesa registrado en la base de datos para una ubicación y tipo específicos.
     *
     * @param ubicacion La ubicación de la mesa a buscar.
     * @param tipo      El tipo de la mesa a buscar.
     * @return El número más alto de mesa encontrado, o 0 si no hay mesas registradas con esos criterios.
     */
    public Integer obtenerUltimoNumeroMesa(String ubicacion, String tipo) {
        EntityManager em = emf.createEntityManager();
        try {
            // Consulta para obtener todos los códigos de mesa que coincidan con la ubicación y el tipo
            List<String> codigos = em.createQuery(
                "SELECT m.codigo FROM Mesa m WHERE m.ubicacion = :ubicacion AND m.tipo = :tipo", String.class)
                .setParameter("ubicacion", ubicacion)
                .setParameter("tipo", tipo)
                .getResultList();

            int maxNumero = 0;

            // Iterar sobre los códigos y obtener el número más alto
            for (String codigo : codigos) {
                // Suponiendo que el número está en los últimos tres caracteres del código
                String numeroStr = codigo.substring(codigo.lastIndexOf('-') + 1);

                try {
                    int numero = Integer.parseInt(numeroStr);
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Código de mesa con formato inesperado: " + codigo);
                }
            }

            // Retornar el siguiente número disponible
            return maxNumero;
        } finally {
            em.close();
        }
    }
    /**
     * Cierra la conexión del EntityManagerFactory.
     * Debe ser llamado al finalizar el uso de la clase para liberar los recursos.
     */
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
