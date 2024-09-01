package cl.ipchile.jira.service;

import cl.ipchile.jira.entity.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateful
public class UsuarioService {

    @PersistenceContext(unitName = "Persistencia")
    private EntityManager em;

    public void createUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario findUsuarioById(Long id) {
        return em.find(Usuario.class, id);
    }

    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = findUsuarioById(id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public List<Usuario> findAllUsuarios() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
    
    public Usuario loginUser(String email, String password) {
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.contrasena = :password", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null si no se encuentra un usuario con las credenciales dadas
        }
    }
}
