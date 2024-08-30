/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.ipchile.jira.servlets;

import cl.ipchile.jira.entity.Tarea;
import cl.ipchile.jira.service.TareaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/svTareas")
public class TareaController extends HttpServlet {

    private TareaService tareaService = new TareaService();;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("list".equals(action)) {
            List<Tarea> tareas = tareaService.getAllTareas();
            request.setAttribute("tareas", tareas);
            request.getRequestDispatcher("/mostrarTareas.jsp").forward(request, response);
        }
        else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Tarea tarea = tareaService.getTareaById(id);
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher("/editarTarea.jsp").forward(request, response);

        } 
        else if ("view".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Tarea tarea = tareaService.getTareaById(id);
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher("/verTarea.jsp").forward(request, response);
        }
        else if("delete".equals(action)){
            Long id = Long.parseLong(request.getParameter("id"));
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
        
        if ("create".equals(action)) {
            String estado = request.getParameter("estado");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String prioridad =  request.getParameter("prioridad");
            Tarea tarea = new Tarea(estado, titulo, descripcion, prioridad);
            tareaService.createTarea(tarea);
            response.sendRedirect("svTareas?action=list");
        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            String estado = request.getParameter("estado");
            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");
            String prioridad = request.getParameter("prioridad");
            Tarea tarea = new Tarea(estado, titulo, descripcion, prioridad);
            tarea.setId(id);
            tareaService.updateTarea(tarea);
            response.sendRedirect("svTareas?action=list");
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            tareaService.deleteTarea(id);
            response.sendRedirect("svTareas?action=list");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida.");
        }
    }
}