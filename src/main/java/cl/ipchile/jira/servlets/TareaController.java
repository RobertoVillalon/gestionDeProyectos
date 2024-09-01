package cl.ipchile.jira.servlets;

import cl.ipchile.jira.entity.Tarea;
import cl.ipchile.jira.entity.Usuario;
import cl.ipchile.jira.service.TareaService;
import cl.ipchile.jira.service.UsuarioService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/svTareas")
public class TareaController extends HttpServlet {
    
    @EJB
    private TareaService tareaService;
    @EJB
    private UsuarioService usuarioService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        System.out.println(action);
        
        if ("create".equals(action)) {
            request.setAttribute("usuarios", usuarioService.findAllUsuarios());
            request.getRequestDispatcher("/crearTarea.jsp").forward(request, response);
        }
        if ("list".equals(action)) {
            List<Tarea> tareas = tareaService.findAllTareas();
            request.setAttribute("tareas", tareas);
            request.getRequestDispatcher("/mostrarTareas.jsp").forward(request, response);
        }
        else if ("edit".equals(action)) {
            List<Usuario> usuarios = usuarioService.findAllUsuarios();
            request.setAttribute("usuarios", usuarios);
            Long id = Long.valueOf(request.getParameter("id"));
            Tarea tarea = tareaService.findTareaById(id);
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher("/editarTarea.jsp").forward(request, response);

        } 
        else if ("view".equals(action)) {
            Long id = Long.valueOf(request.getParameter("id"));
            Tarea tarea = tareaService.findTareaById(id);
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher("/verTarea.jsp").forward(request, response);
        }
        else if("delete".equals(action)){
            Long id = Long.valueOf(request.getParameter("id"));
            tareaService.deleteTarea(id);
            response.sendRedirect("svTareas?action=list");

        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (null == action) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        } else switch (action) {
            case "create":
                {
                    String estado = request.getParameter("estado");
                    String titulo = request.getParameter("titulo");
                    String descripcion = request.getParameter("descripcion");
                    String prioridad =  request.getParameter("prioridad");
                    String idUsuario = request.getParameter("usuario");
                    Usuario usuario = usuarioService.findUsuarioById(Long.valueOf(idUsuario));
                    Tarea tarea = new Tarea(estado, titulo, descripcion, prioridad, usuario);
                    tareaService.createTarea(tarea);
                    response.sendRedirect("svTareas?action=list");
                    break;
                }
            case "update":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    String estado = request.getParameter("estado");
                    String titulo = request.getParameter("titulo");
                    String descripcion = request.getParameter("descripcion");
                    String prioridad = request.getParameter("prioridad");
                    String idUsuario = request.getParameter("usuario");
                    Usuario usuario = usuarioService.findUsuarioById(Long.valueOf(idUsuario));
                    Tarea tarea = new Tarea(estado, titulo, descripcion, prioridad, usuario);
                    tarea.setId(id);
                    tareaService.updateTarea(tarea);
                    response.sendRedirect("svTareas?action=list");
                    break;
                }
            case "delete":
                {
                    Long id = Long.valueOf(request.getParameter("id"));
                    tareaService.deleteTarea(id);
                    response.sendRedirect("svTareas?action=list");
                    break;
                }
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
                break;
        }
    }
}