package cl.ipchile.jira.service;

import cl.ipchile.jira.entity.Tarea;
import cl.ipchile.jira.entity.Usuario;
import cl.ipchile.jira.persistence.TareaJpaController;
import java.util.List;

public class TareaService {
    private TareaJpaController tareaJpaController;

    public TareaService() {
        this.tareaJpaController = new TareaJpaController(); // Asegúrate de que el controlador esté correctamente configurado
    }

    public void createTarea(Tarea tarea) {
        tareaJpaController.create(tarea);
    }

    public Tarea getTareaById(Long id) {
        return tareaJpaController.findTarea(id);
    }

    public List<Tarea> getAllTareas() {
        return tareaJpaController.findAllTareas();
    }
    
    public List<Tarea> getAllTareasByUser(Usuario user) {
        return tareaJpaController.findUsuarioByEmailAndPassword(user);
    }

    public void updateTarea(Tarea tarea) {
        tareaJpaController.update(tarea);
    }

    public void deleteTarea(Long id) {
        tareaJpaController.delete(id);
    }
}
