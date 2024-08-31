package cl.ipchile.jira.servlets;

import cl.ipchile.jira.entity.Usuario;
import cl.ipchile.jira.service.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "svUsuarios", urlPatterns = {"/svUsuarios"})
public class UsuarioController extends HttpServlet {    

    UsuarioService usuarioService = new UsuarioService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            request.getRequestDispatcher("/crearUsuario.jsp").forward(request, response);
        }
        if ("list".equals(action)) {
            request.setAttribute("usuarios", usuarioService.getAllUsuarios());
            request.getRequestDispatcher("/mostrarUsuarios.jsp").forward(request, response);
        }
        else if ("edit".equals(action)) {
            Long id = Long.valueOf(request.getParameter("id"));
            Usuario usuario = usuarioService.getUsuarioById(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/editarUsuario.jsp").forward(request, response);

        } 
        else if ("view".equals(action)) {
            Long id = Long.valueOf(request.getParameter("id"));
            Usuario usuario = usuarioService.getUsuarioById(id);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/verUsuario.jsp").forward(request, response);
        }
        else if("delete".equals(action)){
            Long id = Long.valueOf(request.getParameter("id"));
            usuarioService.deleteUsuario(id);
            response.sendRedirect("svUsuarios?action=list");

        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("create".equals(action)) {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String edad = request.getParameter("edad");
            Usuario usuario = new Usuario(nombre, email, Integer.parseInt(edad));
            usuarioService.createUsuario(usuario);
            response.sendRedirect("svUsuarios?action=list");
        } else if ("update".equals(action)) {
            Long id = Long.valueOf(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String edad = request.getParameter("edad");
            Usuario usuario = new Usuario(nombre, email, Integer.parseInt(edad));
            usuario.setId(id);
            usuarioService.updateUsuario(usuario);
            response.sendRedirect("svUsuarios?action=list");
        } else if ("delete".equals(action)) {
            Long id = Long.valueOf(request.getParameter("id"));
            usuarioService.deleteUsuario(id);
            response.sendRedirect("svUsuarios?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }        
    }
}