package BOs;

import Dto.MesaDTO;
import Entidades.Mesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DAO.MesaDAO;
import java.util.ArrayList;
import java.util.List;

public class MesaBO {

    private MesaDAO mesaDAO;

    public MesaBO() {
        this.mesaDAO = new MesaDAO();
    }

    public void registrarMesas(int cantidadPequenas, int cantidadMedianas, int cantidadGrandes, String ubicacion) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu_restaurante");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            generarMesas(em, cantidadPequenas, 2, "Pequeña", ubicacion);

            generarMesas(em, cantidadMedianas, 4, "Mediana", ubicacion);

            generarMesas(em, cantidadGrandes, 8, "Grande", ubicacion);

            em.getTransaction().commit();
            System.out.println("Mesas registradas con éxito.");

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    private void generarMesas(EntityManager em, int cantidad, int capacidad, String tipo, String ubicacion) throws Exception {
        for (int i = 1; i <= cantidad; i++) {

            String codigo = generarCodigoMesa(ubicacion, capacidad, i);

            Mesa mesa = new Mesa(tipo, capacidad, ubicacion, codigo, new ArrayList<>());
            mesaDAO.registrarMesa(mesa);
        }
    }

    public String generarCodigoMesa(String ubicacion, int capacidad, int numero) {

        String codigoUbicacion = ubicacion.substring(0, 3).toUpperCase();

        String numeroMesa = String.format("%03d", numero);

        return codigoUbicacion + "-" + capacidad + "-" + numeroMesa;
    }
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
}
