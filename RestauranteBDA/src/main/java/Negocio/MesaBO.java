package Negocio;

import Dto.MesaDTO;
import Persistencia.Mesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DAO.MesaDAO;
import java.util.ArrayList;

public class MesaBO {

    private MesaDAO mesaDAO;

    public MesaBO() {
        this.mesaDAO = new MesaDAO();  // Instancia DAO para manejar las operaciones de la base de datos
    }

    public void registrarMesa(MesaDTO mesaDTO) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Mesa mesa = new Mesa(mesaDTO.getTipo(), mesaDTO.getCapacidad(), mesaDTO.getUbicacion(), mesaDTO.getCodigo(), new ArrayList<>());

            mesaDAO.registrarMesa(mesa);

            em.getTransaction().commit();
            System.out.println("Mesa registrada con éxito.");

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw e;

        } finally {

            em.close();
            emf.close();
        }
    }
}
