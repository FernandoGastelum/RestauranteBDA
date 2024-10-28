
package DAO;

import Dto.CancelacionDTO;
import Dto.ReservaDTO;
import Entidades.Cancelacion;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;


/**
 * La clase CancelacionDAO se encarga de gestionar la persistencia de las cancelaciones
 * en la base de datos, utilizando el patrón DAO (Data Access Object) para
 * realizar operaciones CRUD (crear y consultar) sobre los registros de cancelaciones.
 * Utiliza la API JPA (Java Persistence API) para interactuar con la base de datos.
 */
public class CancelacionDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
    /**
     * Registra una nueva cancelación en la base de datos.
     *
     * @param cancelacionDTO Objeto de tipo CancelacionDTO que contiene la información
     *                       de la cancelación que se desea registrar. Este objeto debe
     *                       incluir la fecha de cancelación, el monto de la multa y la
     *                       reserva asociada.
     */
    public void registrarCancelacion(CancelacionDTO cancelacionDTO){
        EntityManager em = emf.createEntityManager();
        Cancelacion cancelacion = new Cancelacion(cancelacionDTO.getFecha(), cancelacionDTO.getMulta(), cancelacionDTO.getReserva());
        em.getTransaction().begin();
        em.persist(cancelacion);
        em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "La cancelacion ha sido creada con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
    }
    /**
     * Busca y devuelve una lista de cancelaciones asociadas a una reserva específica.
     *
     * @param id El identificador único de la reserva cuyos registros de cancelación
     *           se desean obtener.
     * @return Una lista de objetos Cancelacion correspondientes a la reserva especificada.
     *         Si no hay cancelaciones asociadas, la lista estará vacía.
     */
    public List<Cancelacion> buscarCancelacionesPorReservas(Long id){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cancelacion> query = em.createQuery("SELECT c FROM Cancelacion c WHERE c.reserva.id = :id", Cancelacion.class);
        query.setParameter("id", id);
        List<Cancelacion> listaCancelacion = query.getResultList();
        return listaCancelacion;
    }
    /**
     * Obtiene y devuelve una lista de todas las cancelaciones registradas en la base de datos.
     *
     * @return Una lista de objetos Cancelacion que representan todas las cancelaciones
     *         registradas. Si no hay cancelaciones, la lista estará vacía.
     */
    public List<Cancelacion> buscarCancelaciones(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Cancelacion> query = em.createQuery("SELECT c FROM Cancelacion c ", Cancelacion.class);
        List<Cancelacion> listaCancelacion = query.getResultList();
        return listaCancelacion;
    }
    
}
