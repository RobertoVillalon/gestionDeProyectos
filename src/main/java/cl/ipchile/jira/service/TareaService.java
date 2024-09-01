package cl.ipchile.jira.service;

import cl.ipchile.jira.entity.Tarea;
import cl.ipchile.jira.entity.Usuario;
import jakarta.ejb.Stateful;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateful
public class TareaService {

    @PersistenceContext(unitName = "Persistencia")
    private EntityManager em;

    public void createTarea(Tarea tarea) {
        em.persist(tarea);
    }

    public Tarea findTareaById(Long id) {
        return em.find(Tarea.class, id);
    }

    public void updateTarea(Tarea tarea) {
        em.merge(tarea);
    }

    public void deleteTarea(Long id) {
        Tarea tarea = findTareaById(id);
        if (tarea != null) {
            em.remove(tarea);
        }
    }

    public List<Tarea> findAllTareas() {
        TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t", Tarea.class);
        return query.getResultList();
    }

    public List<Tarea> findTareasByUsuario(Usuario usuario) {
        TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t WHERE t.usuario = :usuario", Tarea.class);
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }
}
