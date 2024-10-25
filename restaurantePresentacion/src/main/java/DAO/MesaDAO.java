package DAO;

import Persistencia.Mesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MesaDAO {

    private EntityManagerFactory emf;

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

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
