package cl.ipchile.jira.persistence;

import cl.ipchile.jira.entity.Tarea;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TareaJpaController implements Serializable {

    private EntityManagerFactory emf;

    public TareaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Persistencia");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear Tarea
    public void create(Tarea tarea) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(tarea);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    // Leer Tarea por ID
    public Tarea findTarea(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    // Leer todas las Tareas
    public List<Tarea> findAllTareas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t", Tarea.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Actualizar Tarea
    public void update(Tarea tarea) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(tarea);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    // Eliminar Tarea
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            Tarea tarea = em.find(Tarea.class, id);
            if (tarea != null) {
                em.remove(tarea);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }
}
