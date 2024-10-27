package DAO;

import BOs.MesaBO;
import Entidades.Mesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MesaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;
    private MesaBO mesaBO;
    public MesaDAO() {
        
        this.emf = Persistence.createEntityManagerFactory("pu_restaurante");
    }

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
    
    public Mesa buscarMesaPorCodigo(String codigo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m WHERE m.codigo = :codigo", Mesa.class);
        query.setParameter("codigo", codigo);
        return query.getSingleResult();
        
    }
    
    public Mesa buscarMesaGeneral(int capacidad, String ubicacion){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();
        mesaBO = new MesaBO();
        String tipo = mesaBO.asignarTipoMesa(capacidad);
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m WHERE m.tipo = :tipo AND m.ubicacion = :ubicacion", Mesa.class);
        query.setParameter("tipo", tipo);
        query.setParameter("ubicacion", ubicacion);
        
        return query.getSingleResult();
        
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
