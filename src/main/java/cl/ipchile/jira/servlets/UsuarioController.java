package cl.ipchile.jira.servlets;

import cl.ipchile.jira.entity.Usuario;
import cl.ipchile.jira.service.UsuarioService;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "svUsuarios", urlPatterns = {"/svUsuarios"})
public class UsuarioController extends HttpServlet {    
    @EJB
    UsuarioService usuarioService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        // Asegúrate de que la inyección haya sido realizada correctamente
        if (usuarioService == null) {
            throw new ServletException("UsuarioService no inyectado correctamente");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
                        
        if (null == action) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
        else switch (action) {
            case "create":
                request.getRequestDispatcher("/crearUsuario.jsp").forward(request, response);
                break;
            case "list":
                request.setAttribute("usuarios", usuarioService.findAllUsuarios());
                request.getRequestDispatcher("/mostrarUsuarios.jsp").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            case "edit":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    Usuario usuarioEdit = usuarioService.findUsuarioById(id);
                    request.setAttribute("usuario", usuarioEdit);
                    request.getRequestDispatcher("/editarUsuario.jsp").forward(request, response);
                    break;
                }
            case "view":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    Usuario usuarioView = usuarioService.findUsuarioById(id);
                    request.setAttribute("usuarioView", usuarioView);
                    request.getRequestDispatcher("/verUsuario.jsp").forward(request, response);
                    break;
                }
            case "delete":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    usuarioService.deleteUsuario(id);
                    response.sendRedirect("svUsuarios?action=list");
                    break;
                }
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
                break;
        }
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
                System.out.println(action);

        
        if (null == action) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        } else switch (action) {
            case "create":
                {
                    String nombre = request.getParameter("nombre");
                    String email = request.getParameter("email");
                    String edad = request.getParameter("edad");
                    String contrasena = request.getParameter("password");
                    Usuario usuario = new Usuario(nombre, email, Integer.parseInt(edad), contrasena);
                    usuarioService.createUsuario(usuario);
                    response.sendRedirect("svUsuarios?action=list");
                    break;
                }
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Usuario usuario = usuarioService.loginUser(email, password);
                if (usuario != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioActual", usuario);
                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("errorMessage", "Email o contraseña incorrectos");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                break;
            case "update":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    String nombre = request.getParameter("nombre");
                    String emailUpdate = request.getParameter("email");
                    String edad = request.getParameter("edad");
                    String contrasena = request.getParameter("password");
                    Usuario usuarioUpdate = new Usuario(nombre, emailUpdate, Integer.parseInt(edad), contrasena);
                    usuarioUpdate.setId(id);
                    usuarioService.updateUsuario(usuarioUpdate);
                    response.sendRedirect("svUsuarios?action=list");
                    break;
                }
            case "delete":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    usuarioService.deleteUsuario(id);
                    response.sendRedirect("svUsuarios?action=list");
                    break;
                }
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
                break;
        }        
    }
}