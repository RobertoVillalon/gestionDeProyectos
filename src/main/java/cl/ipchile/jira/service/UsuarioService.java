package cl.ipchile.jira.service;

import cl.ipchile.jira.entity.Usuario;
import cl.ipchile.jira.persistence.UsuarioJpaController;
import java.util.List;

public class UsuarioService {
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();

    
    public void createUsuario(Usuario usuario) {
        usuarioJpaController.create(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioJpaController.findUsuario(id);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioJpaController.findAllUsuarios();
    }

    public void updateUsuario(Usuario tarea) {
        usuarioJpaController.update(tarea);
    }

    public void deleteUsuario(Long id) {
        usuarioJpaController.delete(id);
    }
}
